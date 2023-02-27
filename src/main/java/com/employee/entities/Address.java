package com.employee.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Entity
public class Address
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	private int  pinCode;
	private String houseNumber,city,state, typeOfAddress;
	
	
	//@OneToMany(targetEntity = Employee.class)
    //private List<Employee> employee;
	
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

  
	


	







	public int getAddressId() {
		return addressId;
	}


	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	public int getPinCode() {
		return pinCode;
	}


	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}


	public String getHouseNumber() {
		return houseNumber;
	}


	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getTypeOfAddress() {
		return typeOfAddress;
	}


	public void setTypeOfAddress(String typeOfAddress) {
		this.typeOfAddress = typeOfAddress;
	}


	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", pinCode=" + pinCode + ", houseNumber=" + houseNumber + ", city="
				+ city + ", state=" + state + ", typeOfAddress=" + typeOfAddress + "]";
	}

	
   

	


	

}
