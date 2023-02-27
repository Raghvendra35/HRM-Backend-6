package com.employee.dao;

//FileStorege only for Save Image and Resume With Employee Not Leave Image

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.entities.FileStorage;


public interface FileStorageRepository extends JpaRepository<FileStorage, Long> 
{

	 
	 
	 //Get Single Id
	 @Query(value="Select file_id from file_storage  where employee_employee_id=?",nativeQuery = true)
	 int getFileId(int id);
	 
	 
	 //Get fileId and here we will pass two things first employeeId and type of Files like-> image or resume
	 @Query(value="Select file_id from file_storage  where employee_employee_id=? and type_of_files=?",nativeQuery = true)
	 public int getFiles(int emplId, String typeOfFiles);
}
