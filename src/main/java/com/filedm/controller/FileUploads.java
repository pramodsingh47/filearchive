package com.filedm.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.filedm.dto.ArchiveRequest;
import com.filedm.dto.ArchiveResponse;
import com.filedm.entity.ArchiveFileSystem;
import com.filedm.service.FileUploadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;




@RestController
@RequestMapping("/v1/api")
@Tag(name = "Archive", description = "Documents Management System!")
@SecurityRequirement(name = "Authorization")
public class FileUploads 
{

	Logger logger = LoggerFactory.getLogger(FileUploads.class);

	@Value("${archive.format}")
	private List<String> format;

	@Autowired
	private FileUploadService fileUpload;

	@RequestMapping( path = "/upload-file", method = RequestMethod.POST, consumes="multipart/form-data; charset=utf-8")
	@Operation(summary = "Get Archive the File.", description = "Archive the file and return unique identifier.",method = "POST", tags = {"Archive"})
	//	@Parameters(value = {@Parameter(name = "document_name", example = "xyz,abc etc", required = false),
	//			@Parameter(name = "document_format", example = "word, rtf, email, excel, tif, jpg, gif, pdf etc..", required = false),
	//			@Parameter(name = "document_type", example = "contracts, quote, invoice, consent, identity proof, etc..", required = true),
	//			@Parameter(name = "app_source", example = "Vlocity OM, SFDC, etc", required = true),
	//			@Parameter(name = "retention_days", example = "90 etc", required = true)})
	@ApiResponses(value = {	@ApiResponse(responseCode = "201", description = "File has been stored"),
			@ApiResponse(responseCode = "401", description = "The request did not include an authentication token or the authentication token was expired."),
			@ApiResponse(responseCode = "403", description = "The client did not have permission to access the requested resource."),
			@ApiResponse(responseCode = "415", description = " unsupported media type postman multipart/form-data"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<ArchiveResponse> uploadFile(//@RequestParam("file") MultipartFile file, 
			@ModelAttribute @Valid ArchiveRequest request)	
	/*
	 * @RequestParam(value ="document_name", required = false) String docName, 
	 * @RequestParam(value = "document_format",required = false) String docformate, 
	 * @RequestParam("document_type") String docType,  @RequestParam("app_source") String appSrc,
	 * @RequestParam("retention_days") int retdays) */
	{
		try 
		{
			if (!request.getFile().isEmpty()) 
			{
				String ext = FilenameUtils.getExtension(request.getFile().getOriginalFilename());
				logger.info("File ext :-"+ext);
				if(format.contains(ext))
				{
					ArchiveFileSystem stats = fileUpload.archiveFile(request.getFile(), request.getDocumentName(), request.getDocumentFormat(), request.getDocumentType(), request.getAppSource(), request.getRetentionDays());
					logger.info(request.getFile().getOriginalFilename());
					if (stats != null) 
					{
						ArchiveResponse archiveResponse = new ArchiveResponse(HttpStatus.CREATED.value(),ServletUriComponentsBuilder.fromCurrentContextPath().path("/static/files/")
								.path(request.getFile().getOriginalFilename()).toUriString(),stats.getArchiveID(), "File is created");					
						return new ResponseEntity<ArchiveResponse>(archiveResponse, HttpStatus.CREATED);
					} 
					else {
						return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				else
				{
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
			else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/upload-files") 
	public ResponseEntity<String> uploadFiles() 
	{
		return ResponseEntity.ok("File Upload Successfully");
	}


}
