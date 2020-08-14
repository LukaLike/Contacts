package contacts;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Contacts implements Serializable {
    private static final long serialVersionUID = 1L;

    private final ArrayList<Record> contacts = new ArrayList<>();
    private transient Scanner scanner = new Scanner(System.in);

    public Contacts() { }

    public void addRecord() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();

        Record record;
        if ("person".equals(type)) {
            record = new Person();
        } else if ("organization".equals(type)) {
            record = new Organization();
        } else {
            return;
        }

        for (String value : record.getFields().split(", ")) {
            System.out.print("Enter the " + value + ": ");
            record.changeField(value, scanner.nextLine());
        }

        contacts.add(record);
        System.out.println("The record added.");
    }

    public void list() {
        String option = null;
        while (!"back".equals(option)) {
            System.out.print("\n[list] Enter action ([number], back): ");
            option = scanner.nextLine();

            if (option.matches("\\d+")) {
                if (recordInfo(contacts.get(Integer.parseInt(option) - 1))) {
                    break;
                }
            }
        }
    }

    public void search() {
        System.out.print("Enter search query: ");
        String pattern = "(?i).*" + scanner.nextLine() + ".*";

        ArrayList<Record> searchResults = new ArrayList<>();
        for (Record value : contacts) {
            if (value.getDetailedInfo().matches(pattern)) {
                searchResults.add(value);
            }
        }

        System.out.println("Found " + searchResults.size() + " results: ");
        for (int i = 0; i < searchResults.size(); i++) {
            System.out.println((i + 1) + ". " + searchResults.get(i).getInfo());
        }

        String option = null;
        while (!"back".equals(option)) {
            System.out.print("\n[search] Enter action ([number], back, again): ");

            option = scanner.nextLine();
            if (option.matches("\\d+")) {
                if (recordInfo(searchResults.get(Integer.parseInt(option) - 1))) {
                    break;
                }
            } else if ("again".equals(option)) {
                break;
            }
        }

        if ("again".equals(option)) {
            search();
        }
    }

    public boolean recordInfo(Record record) {
        System.out.println(record.toString());
        while (true) {
            System.out.print("\n[record] Enter action (edit, delete, menu): ");
            switch (scanner.nextLine()) {
                case "edit":
                    System.out.print("Select a field (" + record.getFields() + "): ");
                    String field = scanner.nextLine();

                    System.out.print("Enter the " + field + ": ");
                    record.changeField(field, scanner.nextLine());
                    record.setLastEdited(LocalDateTime.now());
                    System.out.println("Saved");
                    break;
                case "delete":
                    contacts.remove(record);
                    return true;
                case "menu":
                    return true;
            }
        }
    }

    public void count() {
        System.out.println("The Phone Book has " + contacts.size() + " records.");
    }

    public void printContacts() {
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i).getInfo());
        }
    }

    private void readObject(ObjectInputStream ois) throws Exception {
        ois.defaultReadObject();
        scanner = new Scanner(System.in);
    }
}
