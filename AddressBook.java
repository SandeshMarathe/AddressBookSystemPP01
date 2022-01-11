package com.bridgelabz.addressbooksystem;

import java.util.*;


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
            System.out.println("Enter First Name :");
            fName = sc.nextLine();
            System.out.println("Enter Last Name :");
            lName = sc.nextLine();
            duplicateName = checkDuplicateName(fName, lName);
        }
        while (duplicateName);

        System.out.println("Enter The Address :");
        String address = sc.nextLine();
        System.out.println("Enter The City :");
        String city = sc.nextLine();
        System.out.println("Enter The State :");
        String state = sc.nextLine();
        System.out.println("Enter The Zip No :");
        long zip = sc.nextLong();
        System.out.println("Enter the phone No :");
        long phoneNo = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter The Email-ID :");
        String email = sc.nextLine();

        ContactDetails contact = new ContactDetails(fName, lName, address, city, state, zip, phoneNo, email);
        return contact;
    }

    public boolean checkDuplicateName(String fName, String lName) {

        boolean nameExists = contactDetailList.stream()
                .anyMatch(n -> n.firstName.equals(fName) && n.lastName.equals(lName));

        if (nameExists == true) {
            System.out.println("Contact with this Name Already exists. Try Again..!");
        }
        return nameExists;
    }

    public void viewContacts() {
        if (contactDetailList.size() == 0) {
            System.out.println("No Contacts Added..!");
        } else {
            for(int i = 0; i<contactDetailList.size(); i++) {
                int num = i+1;
                System.out.println("Showing Contact Details Contact No :"+ num);
                System.out.println(contactDetailList.get(i).firstName + " " + contactDetailList.get(i).lastName);
                System.out.println(contactDetailList.get(i).address+ " "+ contactDetailList.get(i).city + " "+
                        contactDetailList.get(i).state+ " " + contactDetailList.get(i).zip);
                System.out.println(contactDetailList.get(i).phoneNo);
                System.out.println(contactDetailList.get(i).email);
            }
        }
    }
    public void editContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name of Contact to Edit It :");
        String cName = sc.nextLine();

        if (nameToContactDetailsMap.containsKey(cName)) {
            ContactDetails editContact = nameToContactDetailsMap.get(cName);
            System.out.println("Which Details you would Like to Edit?");
            System.out.println("Press - 1 For First Name \n2 For Last Name \n"
                    + "3 For Address \n4 For City \n5 For State \n6 For Zip \n"
                    + "7 For Phone No. \n8 For Email");

            int num = sc.nextInt();
            switch(num) {
                case 1: System.out.println("Enter New First Name :");
                    sc.nextLine();
                    String fName = sc.nextLine();
                    editContact.setFirstName(fName);
                    break;
                case 2: System.out.println("Enter New Last Name :");
                    sc.nextLine();
                    String lName = sc.nextLine();
                    editContact.setLastName(lName);
                    break;
                case 3: System.out.println("Enter New Address :");
                    sc.nextLine();
                    String add = sc.nextLine();
                    editContact.setLastName(add);
                    break;
                case 4: System.out.println("Enter New City :");
                    sc.nextLine();
                    String city = sc.nextLine();
                    editContact.setCity(city);
                    break;
                case 5: System.out.println("Enter New State :");
                    sc.nextLine();
                    String state = sc.nextLine();
                    editContact.setState(state);
                    break;
                case 6: System.out.println("Enter New Zip No :");
                    long zip = sc.nextLong();
                    editContact.setZip(zip);
                    break;
                case 7: System.out.println("Enter new Mobile No :");
                    long phNum = sc.nextLong();
                    editContact.setPhoneNo(phNum);
                    break;

                case 8: System.out.println("Enter new New Email-ID :");
                    sc.nextLine();
                    String email = sc.nextLine();
                    editContact.setEmail(email);
                    break;
                default: System.out.println("No Edits");
                    return;
            }
        } else {
            System.out.println("No such contact");
        }

    }
    public void deleteContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name of Contact to Delete It :");
        String cName = sc.nextLine();

        if (nameToContactDetailsMap.containsKey(cName)) {
            ContactDetails conInfo = nameToContactDetailsMap.get(cName);
            nameToContactDetailsMap.remove(cName, conInfo);
            contactDetailList.remove(conInfo);
        } else {
            System.out.println("No such contact to Delete..!");
        }
    }

    public AddressBook addressBookOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Address Book System. Choose your Option");
        AddressBook addBook = new AddressBook();
        boolean runLoop = true;
        while(runLoop) {
            System.out.println("Press 1 for adding contact \nPress 2 to view contacts "
                    + "\nPress 3 to edit a contact \nPress 4 to delete a contact \nPress 5 to exit");
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

                case 5: System.out.println("exit");
                    runLoop = false;
                    break;

                default: System.out.println("No correct option chosen");
            }
        }
        return addBook;
    }
}
