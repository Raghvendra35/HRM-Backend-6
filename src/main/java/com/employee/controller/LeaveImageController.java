
package com.employee.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.employee.entities.LeaveEmployee;
import com.employee.entities.LeaveImage;
import com.employee.service.LeaveImageService;



@RestController
@RequestMapping("/api/leaveimage")
public class LeaveImageController
{
	@Value("${project.image}")
	private String path;
	

	@Autowired
	private LeaveImageService leaveImageService;
	
	@PostMapping()
	public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
		int uploadImage = this.leaveImageService.uploadImageToFileSystem(file);
		
		System.out.println("Inside Controller ");
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}


	
	
	
	//Get Image by Id    
    @GetMapping("/by/{imageId}")
    public ResponseEntity<?> findImage(@PathVariable ("imageId") int imageId) throws IOException
    {
     byte[]	imageObj=this.leaveImageService.findImageById(imageId);
     
     //System.out.println(imageObj.getLeaveId());
     //System.out.println(imageObj.getName());
     
     //System.out.println(imageObj);
     return ResponseEntity.status(HttpStatus.OK)
    		 .contentType(MediaType.valueOf("image/jpg"))
    		 .body(imageObj);
    }
    
	
	
    
    // Get image by passing image Name
    @GetMapping("/{image}")
    public ResponseEntity<?> downloadFile(@PathVariable ("image") String image) throws IOException
    {
    	
     byte[] imageData=this.leaveImageService.downloadImageFormFileSystem(image);
          
     return ResponseEntity.status(HttpStatus.OK)
                                		 .contentType(MediaType.valueOf("image/jpg"))
                                		 .body(imageData);
     
    }
	
    
    
    
    
 
    
    
    
    
    
    
    @GetMapping("")
    public List<LeaveImage>
    getAllImage()
    {
       List<LeaveImage> listImage=this.leaveImageService.getAllImages();
                
            //listImage.name
                 
    	return listImage;
    }
	
	
   
}













