package com.capgemini.javastream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {
	private List<Contact> addressList = new ArrayList<Contact>();

	public void addContact(Contact contactObj) {
		addressList.add(contactObj);
		System.out.println("Contact Added.");
		System.out.println(contactObj);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AddressBookMain addressObj = new AddressBookMain();

		System.out.println("Add Person Details:");
		System.out.println("First Name:");
		String firstName = sc.nextLine();
		System.out.println("Last Name:");
		String lastName = sc.nextLine();
		System.out.println("Address:");
		String address = sc.nextLine();
		System.out.println("City:");
		String city = sc.nextLine();
		System.out.println("State:");
		String state = sc.nextLine();
		System.out.println("Zip:");
		String zip = sc.nextLine();
		System.out.println("Phone no:");
		String phoneNo = sc.nextLine();
		System.out.println("Email");
		String email = sc.nextLine();

		Contact contactObj = new Contact(firstName, lastName, address, city, state, zip, phoneNo, email);
		addressObj.addContact(contactObj);
	}
}
