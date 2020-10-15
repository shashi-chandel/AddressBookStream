package com.capgemini.javastream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {
	Scanner sc = new Scanner(System.in);
	private static List<Contact> addressList = new ArrayList<Contact>();
	HashMap<String, List<Contact>> addressBookMap = new HashMap<String, List<Contact>>();

	/**
	 * UC2
	 * 
	 * @param contactObj
	 */
	public void addContact(Contact contactObj) {
		addressList.add(contactObj);
		System.out.println("Contact Added.\n" + contactObj);
	}

	/**
	 * UC3
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public boolean editDetails(String firstName, String lastName) {
		Contact editObj;
		boolean contactFound = false;
		for (int i = 0; i < addressList.size(); i++) {
			editObj = (Contact) addressList.get(i);
			if ((editObj.getFirstName().equals(firstName)) && (editObj.getLastName().equals(lastName))) {
				System.out.println("Enter new Address:");
				editObj.setAddress(sc.nextLine());
				System.out.println("Enter new City");
				editObj.setCity(sc.nextLine());
				System.out.println("Enter new State");
				editObj.setState(sc.nextLine());
				System.out.println("Enter new Zip");
				editObj.setZip(sc.nextLine());
				System.out.println("Enter new Phone no");
				editObj.setPhoneNo(sc.nextLine());
				System.out.println("Enter new Email");
				editObj.setEmail(sc.nextLine());
				contactFound = true;
				break;
			}
		}
		return contactFound;
	}

	/**
	 * UC4
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public boolean removeDetails(String firstName, String lastName) {
		Contact removeObj;
		boolean contactFound = false;
		for (int i = 0; i < addressList.size(); i++) {
			removeObj = (Contact) addressList.get(i);
			if ((removeObj.getFirstName().equals(firstName)) && (removeObj.getLastName().equals(lastName))) {
				addressList.remove(i);
				contactFound = true;
				break;
			}
		}
		return contactFound;
	}

	/**
	 * UC6
	 * 
	 * @param listName
	 */
	public void addAddressList(String listName) {
		List<Contact> newAddressList = new LinkedList<Contact>();
		addressBookMap.put(listName, newAddressList);
		System.out.println("Address Book added");
	}

	/**
	 * UC7
	 * @param first
	 * @param last
	 * @return
	 */
	public static boolean checkForDuplicate(String first, String last) {
		if (addressList.stream().anyMatch(obj -> obj.getFirstName().equals(first))
				&& addressList.stream().anyMatch(obj -> obj.getLastName().equals(last))) {
			System.out.println("This contact already exists, try again!!");
			return true;
		} else
			return false;
	}

	/**
	 * UC8
	 * @param searchPerson
	 * @param searchChoice
	 * @param cityOrState
	 */
	private void searchPersonAcrossCityState(String searchPerson,int searchChoice, String cityOrState) {
		for (Map.Entry<String, List<Contact>> entry : addressBookMap.entrySet()) {
			List<Contact> list = entry.getValue();
			if (searchChoice == 1)
				list.stream().filter(obj -> ((obj.getCity().equals(cityOrState))&&(obj.getFirstName().equals(searchPerson)))).forEach(System.out::println);
			else if(searchChoice == 2)
				list.stream().filter(obj -> ((obj.getState().equals(cityOrState))&&(obj.getFirstName().equals(searchPerson)))).forEach(System.out::println);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AddressBookMain addressObj = new AddressBookMain();
		int choice = 0;

		while (choice != 7) {
			if (addressObj.addressBookMap.isEmpty()) {
				System.out.println("Please add an address book to begin");
				System.out.println("Enter the name of address book that u want to add:");
				String listName = sc.nextLine();
				addressObj.addAddressList(listName);
			}

			System.out.println("Enter the name of the address book you want to access");
			String listName = sc.nextLine();
			if (addressObj.addressBookMap.containsKey(listName)) {
				addressObj.addressList = addressObj.addressBookMap.get(listName);
			}

			else {
				System.out.println("Address list with name " + listName + " not present. Please add it first.");
			}

			System.out.println(
					"Enter a choice: \n 1)Add a new contact \n 2)Edit a contact \n 3)Delete Contact \n 4)Add Address Book \n 5)View Address Book Contacts \n 6)Search person in a city or state across the multiple Address Books \n 7Exit");
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1: {
				System.out.println("Add Person Details:");
				System.out.println("First Name:");
				String firstName = sc.nextLine();
				System.out.println("Last Name:");
				String lastName = sc.nextLine();
				if (checkForDuplicate(firstName, lastName))
					continue;
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
				break;
			}
			case 2: {
				System.out.println(
						"Enter first name, press Enter key, and then enter last name of person to edit details:");
				String firstName = sc.nextLine();
				String lastName = sc.nextLine();
				boolean contactFound = addressObj.editDetails(firstName, lastName);
				if (contactFound == true)
					System.out.println("Details successfully edit");
				else
					System.out.println("Contact not found");
				break;
			}
			case 3: {
				System.out.println(
						"Enter first name, press Enter key, and then enter last name of person to delete data");
				String firstName = sc.nextLine();
				String lastName = sc.nextLine();
				boolean contactFound = addressObj.removeDetails(firstName, lastName);
				if (contactFound == true)
					System.out.println("Details successfully deleted");
				else
					System.out.println("Contact not found");
				break;
			}
			case 4: {
				System.out.println("Enter the name of address book that u want to add:");
				listName = sc.nextLine();
				addressObj.addAddressList(listName);
				break;
			}
			case 5: {
				System.out.println(" " + addressObj.addressList);
				break;
			}
			case 6: {
				System.out.println("Enter first name of person to search");
				String searchPerson = sc.nextLine();
				System.out.println("Enter the name of city or state");
				String cityOrState = sc.nextLine();
				System.out.println("Enter 1 if you entered name of a city \nEnter 2 if you entered name of a state");
				int searchChoice = Integer.parseInt(sc.nextLine());
				addressObj.searchPersonAcrossCityState(searchPerson,searchChoice, cityOrState);
			}
			case 7: {
				System.out.println("Thank you for using the application");
			}
			}
		}
	}
}