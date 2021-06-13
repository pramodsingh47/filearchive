package com.filedm.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filedm.entity.ArchiveFileSystem;
import com.filedm.helper.GlobalUtil;
import com.filedm.repo.ArchiveRepository;

@Service
public class FileUploadService {

	Logger logger = LoggerFactory.getLogger(FileUploadService.class);

	@Autowired
	private ArchiveRepository repository;

	@Value("${archive.file-upload-location}")
	private String filePath;

	public boolean uploadFile(MultipartFile file)
	{
		boolean status = false;
		try
		{
			file.transferTo(Paths.get(filePath+file.getOriginalFilename()));
			status = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public ArchiveFileSystem archiveFile(MultipartFile file, String docName, String docformate, String docType, 
			String appSrc, int retdays) 
	{
		ArchiveFileSystem system = new ArchiveFileSystem();
		try 
		{
			boolean status = uploadFile(file);
			if (status) 
			{
				Date date = new Date();
				Timestamp time = new Timestamp(date.getTime());
				String fileUrl = filePath + File.separator + file.getOriginalFilename();
				Files.copy(file.getInputStream(), Paths.get(filePath + File.separator + file.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				system.setAppSource(appSrc != null ? appSrc : "NA");
				system.setDocType(docType != null ? docType : "NA");
				system.setDocFormate(docformate != null ? docformate : "NA");
				system.setDocName(docName != null ? docName : "NA");
				system.setFileUrl(fileUrl != null ? fileUrl : "NA");
				system.setDocCreationDate(time);
				system.setRetenDate(GlobalUtil.addDays(time, retdays));
				system = repository.save(system);
			}
			else {
				return system;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return system;
	}

}
