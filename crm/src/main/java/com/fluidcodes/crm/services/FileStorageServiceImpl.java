package com.fluidcodes.crm.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import com.fluidcodes.crm.exception.FileStorageException;
import com.fluidcodes.crm.exception.MyFileNotFoundException;
import com.fluidcodes.crm.property.FileStorageProperties;

//@Service
public class FileStorageServiceImpl {
/*
	private final Path fileStorageLocation;

	@Autowired
	public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("File not found", ex);
		}
	}

	@Override
	public String storeFile(MultipartFile file) throws IOException {

		if (!(file.getOriginalFilename().endsWith(".pdf")) || (file.getOriginalFilename().endsWith(".doc"))
				|| (file.getOriginalFilename().endsWith(".docx")) || (file.getOriginalFilename().endsWith(".jpg"))
				|| (file.getOriginalFilename().endsWith(".gif")) || (file.getOriginalFilename().endsWith(".csv")))
			throw new FileStorageException("Invalid File Format");

		File f = new File("/crm/src/main/resources/static/temp/" + file.getOriginalFilename());
		f.createNewFile();
		FileOutputStream fout = new FileOutputStream(f);
		fout.write(file.getBytes());
		fout.close();

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {

			if (fileName.contains("..")) {
				throw new FileStorageException("Invild File Path" + fileName);
			}
			String newFileName = fileName + "_" + System.currentTimeMillis();
			Path targetLocation = this.fileStorageLocation.resolve(newFileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return newFileName;

		} catch (IOException ex) {
			throw new FileStorageException(String.format("Could not store file %s! Please try again.", fileName), ex);
		}
	}

	@Override
	public Resource loadFileAsResource(String fileName) {

		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());

			if (resource.exists())
				return resource;
			else
				throw new MyFileNotFoundException("File Not Found " + fileName);

		} catch (MalformedURLException ex) {

			throw new MyFileNotFoundException("File Not Found " + fileName, ex);

		}

	}
*/
}
