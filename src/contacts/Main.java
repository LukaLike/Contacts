package contacts;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = null;
        Scanner scanner = new Scanner(System.in);
        Contacts contacts;

        if (args.length == 0) {
            contacts = new Contacts();
        } else {
            fileName = args[1];
            File file = new File(fileName);
            if (file.length() == 0 || !file.exists()) {
                contacts = new Contacts();
                SerializationUtils.serialize(contacts, fileName);
            }
            contacts = (Contacts) SerializationUtils.deserialize(fileName);
        }


        String option = null;
        while (!"exit".equals(option)) {
            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
            switch (option = scanner.nextLine()) {
                case "add":
                    contacts.addRecord();
                    break;
                case "list":
                    contacts.printContacts();
                    contacts.list();
                    break;
                case "search":
                    contacts.search();
                    break;
                case "count":
                    contacts.count();
                    break;
            }

            if (fileName != null) {
                try {
                    SerializationUtils.serialize(contacts, fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
