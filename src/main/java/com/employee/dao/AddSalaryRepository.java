package com.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.employee.entities.AddSalary;

public interface AddSalaryRepository extends JpaRepository<AddSalary, Integer>
{

}
