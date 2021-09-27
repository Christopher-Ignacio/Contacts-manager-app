import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

public class ContactsManager {
    private static Scanner sc = new Scanner(System.in);
    private static Path contactsPath = Paths.get("src/contacts.txt");
    private static ArrayList<Contact> contactArrayList = new ArrayList<>();

    public static void readFileAndCreateObjects(){
        List<String> linesInTheFile = new ArrayList<>();
        try {
            linesInTheFile = Files.readAllLines(contactsPath);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        for (String line : linesInTheFile) {
            String[] contactNameAndNumber = line.split(" \\| ");
            Contact contact = new Contact(contactNameAndNumber[0], contactNameAndNumber[1]);
            contactArrayList.add(contact);
        }
    }

    public static void contactsManagerApp(){
        readFileAndCreateObjects();
        boolean keepRunning = true;
        while (keepRunning){
            int userSelection = 0;
            printMenu();
            try{
                userSelection = getUserSelection();
            }  catch (NumberFormatException nfe){
                nfe.printStackTrace();
                //userSelection = getUserSelection();
            } catch (IllegalArgumentException iae){
                iae.printStackTrace();
                //userSelection = getUserSelection();
            }
            switch (userSelection){
                case(0):
                    break;
                case(1):
                    readFileAndOutput();
                    break;
                case(2):
                    addContact();
                    break;
                case(3):
                    search();
                    break;
                case(4):
                    //Delete and existing contact
                    break;
                case(5):
                    overwriteFile();
                    keepRunning = false;
                    break;
                default:
                    System.out.println("This should never happen if we wrote this correctly.");
                    //keepRunning = false;
                    break;
            }

        }
    }
    public static void overwriteFile(){
        try {
            Files.delete(contactsPath);
            Files.createFile(contactsPath);
            for (int i = 0; i < contactArrayList.size(); i++){
                if(i < contactArrayList.size()-1) {
                    Files.writeString(contactsPath, contactArrayList.get(i).getName() + " | " + contactArrayList.get(i).getNumber() + "\n", StandardOpenOption.APPEND);
                } else{
                    Files.writeString(contactsPath, contactArrayList.get(i).getName() + " | " + contactArrayList.get(i).getNumber(), StandardOpenOption.APPEND);
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void printMenu(){
        System.out.println(
            "1. View contacts.\n" +
            "2. Add a new contact.\n" +
            "3. Search a contact by name.\n" +
            "4. Delete an existing contact.\n" +
            "5. Exit.\n" +
            "Enter an option (1, 2, 3, 4 or 5):");
    }
    public static int getUserSelection() throws IllegalArgumentException{
        int userSelection = Integer.parseInt(sc.nextLine());
        if (userSelection >= 1 && userSelection <= 5){
            return userSelection;
        } else {
            throw new IllegalArgumentException();
        }
    }
    //TODO: Show Contacts
    public static void readFileAndOutput(){
        List<String> linesInTheFile = new ArrayList<>();
        try{
            linesInTheFile = Files.readAllLines(contactsPath);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        System.out.println("Name | Phone number\n---------------");
//        for (String line : linesInTheFile){
//            System.out.println(line);
//        }
//        System.out.println();
        for (Contact contact : contactArrayList){
            System.out.println(contact.getName() + " | " + contact.getNumber());
        }
        System.out.println();
    }

    //TODO: Add Contact
    public static void addContact() throws IllegalArgumentException{
        String contactNum;
        long numericallyHighestPossiblePhoneNumber = 9999999999L;
        String contactName;
        try{
            //need to get conditinonals for throwing and possibly catching errors in here
            System.out.println("Enter a name for this contact entry: ");
            contactName = sc.nextLine();
            System.out.println("Enter a full 10-digit number for this contact: ");
            contactNum = sc.nextLine();
            //parsing to Long should throw an error if there are non numeric characters in there but we also don't want numbers wih more than 10 digits (yeah, country code is a thing IRL but I'm assuming we're only calling people in our own country). Have not tested yet though
            if (Long.parseLong(contactNum) > numericallyHighestPossiblePhoneNumber || Long.parseLong(contactNum) < 1000000000){
                throw new IllegalArgumentException();
            }
            contactArrayList.add(new Contact(contactName, contactNum));
            //Files.writeString(contactsPath, "\n"+ contactName + " | " + contactNum, StandardOpenOption.APPEND);
        }
//        catch (IOException ioe){
//            ioe.printStackTrace();
//            System.out.println("Writing to file failed (I think).");
//        }
        catch (NumberFormatException nfe){
            nfe.printStackTrace();
            System.out.println("That wasn't a proper number. Let's try that again.");
            addContact();
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
            System.out.println("That number is not 10-digits in length. We need a 10-digit phone number");
            addContact();
        }
    }


    //TODO: Search by Name

    public static void search() {
        boolean foundMatches = false;

        System.out.println("Search by name or phone number: ");
        String searchTerm = sc.nextLine();
//        List<String> linesInTheFile = new ArrayList<>();
//        try {
//            linesInTheFile = Files.readAllLines(contactsPath);
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
        for (Contact contact : contactArrayList) {
            if (contact.getName().contains(searchTerm) || contact.getNumber().contains(searchTerm)) {
                foundMatches = true;
                System.out.println("Hey we found a match to your entry!");
                System.out.println(contact.getName() + " | " + contact.getNumber());
            }
        }
        if(!foundMatches){
            System.out.println("Sorry we did not find any matches :( ");
        }
    }


    //TODO: Delete existing contact


}
