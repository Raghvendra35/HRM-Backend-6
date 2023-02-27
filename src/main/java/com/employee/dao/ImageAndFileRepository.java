package com.employee.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entities.ImageAndFile;

public interface ImageAndFileRepository extends JpaRepository<ImageAndFile, Long> 
{

	Optional<ImageAndFile> findById(long imageId);
}
