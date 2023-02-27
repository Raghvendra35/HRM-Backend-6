package com.employee.service;

    //FileStorege only for Save Image and Resume With Employee Not Leave Image

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.employee.dao.FileStorageRepository;
import com.employee.dao.ImageAndFileRepository;
import com.employee.entities.Employee;
import com.employee.entities.FileStorage;
import com.employee.entities.ImageAndFile;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Path;

@Service
public class FileStorageService 
{

	@Autowired
	private ImageAndFileRepository imageAndFileRepo;
	@Autowired
	private FileStorageRepository fileStorageRepo;
	@Autowired
	private EmployeeService employeeService;
	
	

	 //Save multipartFile and EmployeeId 
	 
	   @Value("${project.files}")
	   private String path2;
	
	   public String multipleFile(int employeeId,String fileTypes, MultipartFile[] files) throws IllegalStateException, IOException
	   {
                  

		       String type=fileTypes;
				  
			  // String imageFilePath2=path2;
			   FileStorage fileStorage2=new FileStorage();
			 
			   Employee employee= this.employeeService.getEmployeeById(employeeId).get();
			  // fileStorage2.setEmployee(employee);
			    List<FileStorage> fileList2=new ArrayList<FileStorage>();
				
			    
				for(MultipartFile file2: files)
				{
					
			    fileStorage2.setEmployee(employee);
			    String imageFilePath2=path2+file2.getOriginalFilename();
				//String type=file2.getContentType();
				
				
				if(type.equals("image") ||type.equals("image/jpeg") || type.equals("image/png") || type.equals("image/jpg"))
			  	 {
			        FileStorage fileStorage=this.fileStorageRepo.save(fileStorage2.builder()
					 	                                           .name(file2.getOriginalFilename())
						                                           .fileType(file2.getContentType())
						                                           .filePath(imageFilePath2)
						                                           .employee(employee)
						                                           .typeOfFiles(fileTypes).build());
						                                          
						                                        
				         file2.transferTo(new File(imageFilePath2));
				         System.out.println("Inside Service ++++++++++++++++++++++++++");
				 }
				 else
				  {
					 
				    FileStorage fileStorage=this.fileStorageRepo.save(fileStorage2.builder()
                                                                              .name(file2.getOriginalFilename())
                                                                              .fileType(file2.getContentType())
                                                                              .filePath(imageFilePath2)
                                                                              .employee(employee)
                                                                              .typeOfFiles("resume").build());
				       file2.transferTo(new File(imageFilePath2));
			  }
//				
				}
		
				return "Saved Data !!!";
	   }
	   
	   

	   
	
	   
	  
	   //Fetch Image
	    public byte[] downloadImageFromFileSystem(long imageId) throws IOException {
	        Optional<FileStorage> fileData =this.fileStorageRepo.findById(imageId);
	        String filePath=fileData.get().getFilePath();
	        byte[] images = Files.readAllBytes(new File(filePath).toPath());
	        return images;
	    }
	    
	    
	    
	    
	    //Download Resume
	    public Resource downloadResume(long fileId) throws MalformedURLException
	    {
	       Optional<FileStorage> fileData=this.fileStorageRepo.findById(fileId);
	       String filePath=fileData.get().getFilePath();
	       Path file = (Path) Paths.get(filePath);  
	       
	       Resource resource=(Resource) new UrlResource(((java.nio.file.Path) file).toUri());
			return resource; 
			
	    	
	    }
	    
	    
	    
	    
	    //Here we pass employeeId  and get fileId
	     public int getFileId(int emplId)
	     {
	    	int id=this.fileStorageRepo.getFileId(emplId);
	    	System.out.println("Inside Service Id-------------------------------");
	    	System.out.println(id);
	    	 return id;
	     }
	   
	    
	    
	    //Here we pass employeeId and typeOfFiles and then we get fileId
	     public int getOnlyFileId(int empId, String typeOfFiles)
	     {
	       int fileId=this.fileStorageRepo.getFiles(empId, typeOfFiles);
	       return fileId;
	     }
	    
	    
	    
	    
	    
	    
	    
	       
	    //Save Image only
	    @Value("${project.emlimage}")
	    private String path;
	
	    public String uploadImageToFileSystem(MultipartFile file) throws IOException
	    {
	    	
	        String imageFilePath=path+file.getOriginalFilename();
	        System.out.println("Path "+imageFilePath);
           
	        
	        ImageAndFile fileData=this.imageAndFileRepo.save(ImageAndFile.builder()
	                                                                     .name(file.getOriginalFilename())
	                                                                     .imageType(file.getContentType())
	                                                                     .filePath(imageFilePath).build());

	        file.transferTo(new File(imageFilePath));

	        System.out.println("Inside Service ");
	        
	        
	        
	        if (fileData != null) 
	        {
	            return "file uploaded successfully : " + imageFilePath;
	        }
	        return null;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    // Upload Only Files
		   @Value("${project.files}")
		   private String path3;
		   //Save multipart
		   public String multipleFileOnly(MultipartFile[] files) throws IllegalStateException, IOException
		   {

				 System.out.println("inside Service +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				 System.out.println(files.length);
				  
				  // String imageFilePath2=path2;
				   FileStorage fileStorage2=new FileStorage();
				  // fileStorage2.setEmployee(employee);
				 				 
				   List<FileStorage> fileList2=new ArrayList<FileStorage>();
					
					for(MultipartFile file2: files)
					{
					
					String imageFilePath2=path2+file2.getOriginalFilename();
					
				    FileStorage fileStorage=this.fileStorageRepo.save(fileStorage2.builder()
							                                           .name(file2.getOriginalFilename())
							                                           .fileType(file2.getContentType())
							                                           .filePath(imageFilePath2)
							                                           .build());
							                                          
							                                        
					
					file2.transferTo(new File(imageFilePath2));
					System.out.println("Inside Service ++++++++++++++++++++++++++");
					System.out.println(fileStorage.getName());
					
					}
					
					//this.fileStorageRepo.saveAll(fileList2);
					return "Saved Data !!!";
		   }
		   


		   
		   
		   
			// private final String FOLDER_PATH="C:\\Users\\Lenovo\\eclipse-workspace\\HRManagements\\src\\main\\resources\\MyFiles\\";
			 
		   
			 //Save multipartFile and EmployeeId without it's image or resume
//			 
//			   @Value("${project.files}")
//			   private String path2;
		//	
//			   public String multipleFile(int employeeId, MultipartFile[] files) throws IllegalStateException, IOException
//			   {
//		                     
//				   
//					 System.out.println("inside Service +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//					 System.out.println(files.length);
//					  
//					  // String imageFilePath2=path2;
//					   FileStorage fileStorage2=new FileStorage();
//					 
//					   Employee employee= this.employeeService.getEmployeeById(employeeId).get();
//					  // fileStorage2.setEmployee(employee);
//					 
//							 
//					   List<FileStorage> fileList2=new ArrayList<FileStorage>();
//						
//						for(MultipartFile file2: files)
//						{
//							 fileStorage2.setEmployee(employee);
//						System.out.println("Checking image type +++++++++++++++++++++++++++++++++++++++");
//						String type=file2.getContentType();
//					    System.out.println(type);		 
//						String imageFilePath2=path2+file2.getOriginalFilename();
//						
//						
//					    FileStorage fileStorage=this.fileStorageRepo.save(fileStorage2.builder()
//								                                           .name(file2.getOriginalFilename())
//								                                           .fileType(file2.getContentType())
//								                                           .filePath(imageFilePath2)
//								                                           .employee(employee)
//								                                           .typeOfFiles("image").build());
//								                                          
//								                                        
//						
//						file2.transferTo(new File(imageFilePath2));
//						
//						System.out.println("Inside Service ++++++++++++++++++++++++++");
//						System.out.println(fileStorage.getName());
//						
//						}
//						//this.fileStorageRepo.saveAll(fileList2);
//						return "Saved Data !!!";
//			   }
//			   
			
}
