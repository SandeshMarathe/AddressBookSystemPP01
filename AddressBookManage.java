package com.bridgelabz.addressbooksystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBookManage {

    private Map<String, AddressBook> nameToAddressBookMap;
    public List<String> valuePrinted;
    private int countPerson;

    public AddressBookManage( ) {
        nameToAddressBookMap = new HashMap<>();
        valuePrinted = new ArrayList<>();
    }

    public void addAddressBook(String addBookName, AddressBook addBook) {
        nameToAddressBookMap.put(addBookName, addBook);
    }

    public boolean createAddBooks() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the num of address books to create");
        int num = sc.nextInt();
        sc.nextLine();

        for (int i =0; i< num ; i++) {
            int serialNo = i+1;
            System.out.println("Enter the name of add. book num. " + serialNo);
            String aBookName = sc.nextLine();
            AddressBook addBookObj = new AddressBook();
            addBookObj = addBookObj.addressBookOption();
            addAddressBook(aBookName, addBookObj);
        }
        return true;
    }


    public void viewAddBooks() {
        for(int i=0; i< nameToAddressBookMap.size(); i++) {
            System.out.println(i + "Name of add. book are : "+ nameToAddressBookMap);
        }
    }
    public void findPersonByCity(String cityName) {
        try {
            nameToAddressBookMap.forEach((key, addresBookValue) -> {

                valuePrinted = addresBookValue.cityToPersonsMap.get(cityName).stream()
                        .peek(n ->
                                System.out.println("person names: "+ n))
                        .collect(Collectors.toList());
            });
            countPerson = valuePrinted.size();
        } catch (NullPointerException e) {
            System.out.println("no person in the city");
        }
    }

    public void findPersonByState(String stateName) {
        try {
            nameToAddressBookMap.forEach((key, addresBookValue) -> {
                valuePrinted = addresBookValue.cityToPersonsMap.get(stateName).stream()
                        .peek(n ->
                                System.out.println("person names: "+ n))
                        .collect(Collectors.toList());
            });
            countPerson = valuePrinted.size();
        } catch (NullPointerException e) {
            System.out.println("no person in the state");
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("welcome and create address books ");
        AddressBookManage addBookManage = new AddressBookManage();
        boolean created = addBookManage.createAddBooks();
        System.out.println("Successfully created address books");
        addBookManage.viewAddBooks();

        if (created) {
            System.out.println("Enter 1 to find by state \nEnter 2 to find by city");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1: System.out.println("Enter the name of state: ");
                    String stateName = sc.nextLine();
                    addBookManage.findPersonByState(stateName);
                    break;

                case 2: System.out.println("Enter the name of city: ");
                    String cityName = sc.nextLine();
                    addBookManage.findPersonByCity(cityName);
                    break;

                default:
                    break;
            }
        }
        System.out.println("Number of persons found = " + addBookManage.countPerson);
    }
}