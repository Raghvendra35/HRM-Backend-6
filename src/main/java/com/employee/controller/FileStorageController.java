package com.employee.controller;

//FileStorege only for Save Image and Resume With Employee Not Leave Image


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.employee.service.FileStorageService;

import ch.qos.logback.core.util.ContentTypeUtil;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Path;

@RestController
@RequestMapping("api")
public class FileStorageController
{
	
		 
	@Autowired
	private FileStorageService fileStorageService;
	


	
	
    //Save Multiple files with EmployeeId
	@PostMapping("/uploadmultiple/{employeeId}/{typeoffile}")
	public ResponseEntity<?> uploadMultipartFiles(@PathVariable ("employeeId") int employeeId,@PathVariable ("typeoffile") String typeoffile, @RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException
	{
		System.out.println("Controller ++++++++++++++++++++++++++++++++");
        System.out.println(typeoffile);
        System.out.println(" Controller============================================");
	    String path=this.fileStorageService.multipleFile(employeeId,typeoffile, files);
		return ResponseEntity.status(HttpStatus.OK).body(path);
	}

	
	
	
	
	
	
	   //Get fileId (or)
		@GetMapping("getfile/{empId}/{types}")
		public ResponseEntity<?> getImageWithType(@PathVariable ("empId") int empId, @PathVariable ("types") String types) throws IOException
		{
	      int fileId=this.fileStorageService.getOnlyFileId(empId, types);
	            
	      byte[] imageData=this.fileStorageService.downloadImageFromFileSystem(fileId);
	           
			return ResponseEntity.status(HttpStatus.OK)
					                                 .contentType(MediaType.IMAGE_JPEG)
					                                 .body(imageData);
		}
		
		
		
		
		
		//Download Resume 
		@GetMapping("/downloadfile/{empId}/{types}")
		public ResponseEntity<Resource> downloadFile(@PathVariable ("empId") int empId, @PathVariable ("types") String types) throws MalformedURLException
		{
			
		  int fileId=this.fileStorageService.getOnlyFileId(empId, types);	
		  
	      Resource resourceFile=this.fileStorageService.downloadResume(fileId);  
		                  
		  return ResponseEntity.ok()
				                   .contentType(MediaType.APPLICATION_PDF)
				                   .header(HttpHeaders.CONTENT_DISPOSITION,"attachment")
				                   .body(resourceFile);
				                 
		}
		
		
		
		
	
	
	//Get Image by passing fileId (or) imageId
	@GetMapping("/getimage/{imageId}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable long imageId) throws IOException 
	{
		byte[] imageData=this.fileStorageService.downloadImageFromFileSystem(imageId);
		
		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("image/int"))
				.contentType(MediaType.IMAGE_JPEG)
				.body(imageData);

	}

	
	
	//Get fileId Only by passing employeeId 
	@GetMapping("/getfileid/{empId}")
	public int getId(@PathVariable ("empId") int emplId)
	{
	  
	int id=this.fileStorageService.getFileId(emplId);
	   
	System.out.println("Inside Controller..............................");
	System.out.println(id);
	
		return id;
	}
	
	
	
	
	
	//Get FileId and Image both 
	@GetMapping("/getfiles/{empId}")
	public ResponseEntity<?> getIdAndImage(@PathVariable ("empId") int emplId) throws IOException
	{
	  
	  int id=this.fileStorageService.getFileId(emplId);
	  System.out.println("Inside Controller..............................");
	  System.out.println(id);
	  
     byte[] imageData=this.fileStorageService.downloadImageFromFileSystem(id);
		
     return ResponseEntity.status(HttpStatus.OK)
    		                                 .contentType(MediaType.IMAGE_JPEG)
    		                                 .body(imageData);
	
	}
	
	
	
	
	
	
		
	
	//Save Image
	@PostMapping("/filesystem")
	public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file) throws IOException 
	{
		String uploadImage = this.fileStorageService.uploadImageToFileSystem(file);
		
		System.out.println("Inside Controller ");
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	
	}

	
	
	
	
	private Logger logger=LoggerFactory.getLogger(FileStorageController.class);
	
//	//Save Multiple files
//	@PostMapping("/uploadmultiple")
//	public ResponseEntity<?> uploadMultipartFiles(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException
//	{
//		//System.out.println(files.length);
//	
//	 String path=this.imageAndFileService.multipleFile(files);
//		
//		return ResponseEntity.status(HttpStatus.OK).body(path);
//	}
	
	
	
			
		
		
	    //Save Multiple files without employeeId
		
			@PostMapping("/uploadmultiple")
			public ResponseEntity<?> uploadMultipartFilesOnly(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException
			{
				//System.out.println(files.length);
			    String path=this.fileStorageService.multipleFileOnly(files);
				return ResponseEntity.status(HttpStatus.OK).body(path);
			}
		
	

			
}
