package com.tts.JavaProject.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tts.JavaProject.Model.Address;



public interface AddressRepository extends CrudRepository<Address, Long> {

	Address findByEmailAddress(String emailAddress);
	List<Address> findByFirstNameContainingIgnoreCase(String firstName);
	List<Address> findByLastNameContainingIgnoreCase(String lastName);
	List<Address> findByPhoneNumberContainingIgnoreCase(String phoneNumber);
	List<Address> findByEmailAddressContainingIgnoreCase(String emailAddress);

	
}
