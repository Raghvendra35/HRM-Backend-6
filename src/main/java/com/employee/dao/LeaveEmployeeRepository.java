package com.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.employee.entities.LeaveEmployee;

public interface LeaveEmployeeRepository extends JpaRepository<LeaveEmployee, Integer>
{

}
