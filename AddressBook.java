package com.bridgelabz.addressbooksystem;

import java.util.*;
import java.util.stream.Collectors;


public class AddressBook
{
    public ArrayList<ContactDetails> contactDetailList;
    public Map<String, ContactDetails> nameToContactDetailsMap;
    public Map<String, ArrayList<String> > cityToPersonsMap;
    private Map<String, ArrayList<String> > stateToPersonsMap;

    public AddressBook() {
        contactDetailList = new ArrayList<ContactDetails>();
        nameToContactDetailsMap = new HashMap<>();
        cityToPersonsMap = new HashMap<>();
        stateToPersonsMap = new HashMap<>();
    }

    public void addContacts(ContactDetails contactPerson) {
        System.out.println("-------Adding a Contact---- " + contactPerson.firstName);
        contactDetailList.add(contactPerson);
        nameToContactDetailsMap.put(contactPerson.firstName, contactPerson);

        cityToPersonsMap.computeIfAbsent(contactPerson.city, k -> new ArrayList<>()).add(contactPerson.firstName);
        stateToPersonsMap.computeIfAbsent(contactPerson.state, k -> new ArrayList<>()).add(contactPerson.firstName);
    }

    public ContactDetails getContactInfo() {
        Scanner sc = new Scanner(System.in);
        String fName= "", lName = "";

        boolean duplicateName = false;

        do {
            System.out.println("Enter first name:");
            fName = sc.nextLine();
            System.out.println("Enter last name:");
            lName = sc.nextLine();
            duplicateName = checkDuplicateName(fName, lName);
        }
        while (duplicateName);

        System.out.println("Enter the address:");
        String address = sc.nextLine();
        System.out.println("Enter the city");
        String city = sc.nextLine();
        System.out.println("Enter the state:");
        String state = sc.nextLine();
        System.out.println("zip");
        long zip = sc.nextLong();
        System.out.println("Enter the phone no:");
        long phoneNo = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter the email:");
        String email = sc.nextLine();

        ContactDetails contact = new ContactDetails(fName, lName, address, city, state, zip, phoneNo, email);
        return contact;
    }

    public boolean checkDuplicateName(String fName, String lName) {

        boolean nameExists = contactDetailList.stream()
                .anyMatch(n -> n.firstName.equals(fName) && n.lastName.equals(lName));

        if (nameExists == true) {
            System.out.println("Contact with this name already exists. Try again.");
        }
        return nameExists;
    }

    public void viewContacts() {
        if (contactDetailList.size() == 0) {
            System.out.println("No contacts added.");
        } else {
            for(int i = 0; i<contactDetailList.size(); i++) {
                int num = i+1;
                System.out.println("Showing contact details contact no. "+ num);
                System.out.println(contactDetailList.get(i).firstName + " " + contactDetailList.get(i).lastName);
                System.out.println(contactDetailList.get(i).address+ " "+ contactDetailList.get(i).city + " "+
                        contactDetailList.get(i).state+ " " + contactDetailList.get(i).zip);
                System.out.println(contactDetailList.get(i).phoneNo);
                System.out.println(contactDetailList.get(i).email);
            }
        }
    }
    public void viewSortedByNames() {
        List<ContactDetails> sortedDetails = contactDetailList.stream()
                .sorted(Comparator.comparing(n->n.toString()))
                .peek(n -> System.out.println(n))
                .collect(Collectors.toList());
    }

    public void viewSortedByCity() {
        List<ContactDetails> sortedDetailsByCity = contactDetailList.stream()
                .sorted((ab1, ab2) -> ab1.getCity().compareTo(ab2.getCity()))
                .peek(addBook -> System.out.println(addBook))
                .collect(Collectors.toList());
    }
    public void viewSortedByState() {
        List<ContactDetails> sortedDetailsByCity = contactDetailList.stream()
                .sorted((ab1, ab2) -> ab1.getState().compareTo(ab2.getState()))
                .peek(addBook -> System.out.println(addBook))
                .collect(Collectors.toList());
    }
    public void editContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name of contact to edit it");
        String cName = sc.nextLine();

        if (nameToContactDetailsMap.containsKey(cName)) {
            ContactDetails editContact = nameToContactDetailsMap.get(cName);
            System.out.println("Which details you would like to edit?");
            System.out.println("Press - 1 for first name \n2 for last name \n"
                    + "3 for address \n4 for city \n5 for state \n6 for zip \n"
                    + "7 for phone no. \n8 for email");

            int num = sc.nextInt();
            switch(num) {
                case 1: System.out.println("Enter new first name");
                    sc.nextLine();
                    String fName = sc.nextLine();
                    editContact.setFirstName(fName);
                    break;
                case 2: System.out.println("Enter new last name");
                    sc.nextLine();
                    String lName = sc.nextLine();
                    editContact.setLastName(lName);
                    break;
                case 3: System.out.println("Enter new address");
                    sc.nextLine();
                    String add = sc.nextLine();
                    editContact.setLastName(add);
                    break;
                case 4: System.out.println("Enter new city");
                    sc.nextLine();
                    String city = sc.nextLine();
                    editContact.setCity(city);
                    break;
                case 5: System.out.println("Enter new state");
                    sc.nextLine();
                    String state = sc.nextLine();
                    editContact.setState(state);
                    break;
                case 6: System.out.println("Enter new zip");
                    long zip = sc.nextLong();
                    editContact.setZip(zip);
                    break;
                case 7: System.out.println("Enter new zip");
                    long phNum = sc.nextLong();
                    editContact.setPhoneNo(phNum);
                    break;

                case 8: System.out.println("Enter new zip");
                    sc.nextLine();
                    String email = sc.nextLine();
                    editContact.setEmail(email);
                    break;
                default: System.out.println("No edits");
                    return;
            }
        } else {
            System.out.println("No such contact");
        }

    }
    public void deleteContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name of contact to delete it");
        String cName = sc.nextLine();

        if (nameToContactDetailsMap.containsKey(cName)) {
            ContactDetails conInfo = nameToContactDetailsMap.get(cName);
            nameToContactDetailsMap.remove(cName, conInfo);
            contactDetailList.remove(conInfo);
        } else {
            System.out.println("No such contact to delete");
        }
    }

    public AddressBook addressBookOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the address book system. Choose your option");
        AddressBook addBook = new AddressBook();
        boolean runLoop = true;
        while(runLoop) {
            System.out.println("Press 1 for adding contact \nPress 2 to view contacts "
                    + "\nPress 3 to edit a contact \nPress 4 to delete a contact"
                    + " \nPress 5 to view sorted address book \nPress 6 to exit");
            int ch = sc.nextInt();

            switch(ch) {
                case 1: System.out.println("---- Add contact details ---- ");
                    ContactDetails contactPerson = addBook.getContactInfo();
                    addBook.addContacts(contactPerson);
                    System.out.println("Contact added for " + contactPerson.firstName + " " + contactPerson.lastName);
                    break;

                case 2: System.out.println("----view contacts --- ");
                    addBook.viewContacts();

                    break;

                case 3: System.out.println("---- Editing contacts---");
                    addBook.editContact();
                    break;

                case 4: System.out.println("---- Delete a contact---");
                    addBook.deleteContact();
                    break;

                case 5: System.out.println("------Sorted by name----");
                    addBook.viewSortedByNames();
                    System.out.println("------Sorted by city----");
                    addBook.viewSortedByCity();
                    break;

                case 6: System.out.println("exit");
                    runLoop = false;
                    break;

                default: System.out.println("No correct option chosen");


            }
        }
        return addBook;
    }
}
