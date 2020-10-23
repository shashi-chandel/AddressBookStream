package com.capgemini.javastream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddressBookIOService {
	private static String HOME;
	private HashMap<String, List<Contact>> addressBookMap;

	public AddressBookIOService() {
		HOME = "C:\\Users\\shashi7\\eclipse-workspace\\AddressBookStream\\src\\com\\capgemini\\javastream\\Address Contacts";
		addressBookMap = new HashMap<String, List<Contact>>();
		readDataFromAddressBook();
	}

	public void readDataFromAddressBook() {
		try {
			Files.walk(Paths.get(HOME)).filter(Files::isRegularFile).forEach(file -> {
				List<Contact> contactList = new ArrayList<Contact>();
				try {
					Files.lines(file.toAbsolutePath()).forEach(lines -> {
						String data = lines.toString();
						String[] dataArr = data.split(",");
						for (int position = 0; position < dataArr.length; position++) {
							String firstName = dataArr[position];
							position++;
							String lastName = dataArr[position];
							position++;
							String address = dataArr[position];
							position++;
							String city = dataArr[position];
							position++;
							String state = dataArr[position];
							position++;
							String zip = dataArr[position];
							position++;
							String phone = dataArr[position];
							position++;
							String email = dataArr[position];
							Contact contact = new Contact(firstName, lastName, address, city, state, zip, phone, email);
							contactList.add(contact);
						}
					    });
					String fileName = file.toAbsolutePath().toString();
					addressBookMap.put(fileName, contactList);
				} catch (IOException e) {
					e.printStackTrace();
				}

			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	public boolean addAddressBook(String bookName) {
		Path addressBooks = Paths.get(HOME + "/" + bookName + ".txt");
		if (Files.notExists(Paths.get(HOME + "/" + bookName + ".txt"))) {
			try {
				Files.createFile(addressBooks);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public void writeContactToAddressBook(Contact contactObj, String addressBookName) {
		StringBuffer contactsBuffer = new StringBuffer();
		String contactData = contactObj.toString();
		try {
			Files.lines(Paths.get(HOME + "/" + addressBookName + ".txt")).forEach(lines -> {
				String data = lines.toString().concat("\n");
				contactsBuffer.append(data);
			});
			contactsBuffer.append(contactData);
			Files.write(Paths.get(HOME + "/" + addressBookName + ".txt"), contactsBuffer.toString().getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void print() {
		addressBookMap.entrySet().stream().map(entry -> entry.getValue()).forEach(System.out::println);
	}
	
	public static String getHOME() {
		return HOME;
	}

	public static void setHOME(String hOME) {
		HOME = hOME;
	}

	public HashMap<String, List<Contact>> getAddressBookMap() {
		return addressBookMap;
	}

	public void setAddressBookMap(HashMap<String, List<Contact>> addressBookMap) {
		this.addressBookMap = addressBookMap;
	}
}
