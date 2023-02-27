package com.employee.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entities.ImageAndFile;
import com.employee.entities.LeaveImage;


public interface LeaveImageRepository extends JpaRepository<LeaveImage, Integer> 
{
	Optional<LeaveImage> findByName(String imageName);


}
