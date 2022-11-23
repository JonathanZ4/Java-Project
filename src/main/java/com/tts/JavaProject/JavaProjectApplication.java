package com.tts.JavaProject;

import java.util.Scanner;


import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tts.JavaProject.Model.Address;
import com.tts.JavaProject.Repository.AddressRepository;

@SpringBootApplication
public class JavaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}
	@Bean
	CommandLineRunner start(AddressRepository repository) {
		
		return (args) -> {
			Scanner selection = new Scanner (System.in);
			String prompt = " \n1) Add an entry \n2) Remove an entry\n3) Search for a specific entry \n4) Print Address Book\n5) Delete Book\n6) Quit\n\nPlease choose what you'd like to do with the database: ";
			int answer;
			System.out.print(prompt);
			answer = selection.nextInt();
			
			while (answer != 6) {
				switch (answer) {
				case 1:
					System.out.print("\nFirst Name: ");
					String firstName = selection.next();
					System.out.print("\nLast Name: ");
					String lastName = selection.next();
					System.out.print("\nPhone Number: ");
					String phoneNumber = selection.next();
					System.out.print("\nEmail Address: ");
					String emailAddress = selection.next();
					repository.save(new Address(firstName, lastName, phoneNumber, emailAddress));
					System.out.print("\nAdded new entry");
					System.out.println();
					repository.findAll().forEach((a) -> {
						System.out.println(a.toString());
					});
					break;
				case 2:
					System.out.print("Enter an entry's email to remove: ");
					String entryToDelete = selection.next();
					System.out.print("\nDeleted the following entry\n");
					System.out.print(repository.findByEmailAddress(entryToDelete));
					repository.delete(repository.findByEmailAddress(entryToDelete));
					System.out.println();
					break;
				case 3:
					System.out.print("\n1) First Name \n2) Last Name\n3) Phone Number\n4) Email Address\n\nChoose a search type: ");
					int secondarySelection = selection.nextInt();
					switch (secondarySelection) {
					case 1:
						System.out.print("\nEnter your search: ");
						String fname = selection.next();
						System.out.print(repository.findByFirstNameContainingIgnoreCase(fname).toString());
						break;
					case 2:
						System.out.print("\nEnter your search: ");
						String lname = selection.next();
						System.out.print(repository.findByLastNameContainingIgnoreCase(lname).toString());
						break;
					case 3:
						System.out.print("\nEnter your search: ");
						String phonenum = selection.next();
						System.out.print(repository.findByPhoneNumberContainingIgnoreCase(phonenum).toString());
						break;
					case 4:
						System.out.print("\nEnter your search: ");
						String email = selection.next();
						System.out.print(repository.findByEmailAddressContainingIgnoreCase(email));
						break;
					}
					break;
				case 4:
					repository.findAll().forEach((a) -> {
						System.out.println(a.toString());
					});
					break;
				case 5:
					repository.deleteAll();
					System.out.print("\nAddress book cleared!\n");
					break;
				case 6:
					break;
				default:
					System.out.println("Invalid Response");
					break;
				}
				System.out.print(prompt);
				answer = selection.nextInt();
			}
			System.out.println("\nGoodbye\n");
		
		
		};
		
	}	

}
