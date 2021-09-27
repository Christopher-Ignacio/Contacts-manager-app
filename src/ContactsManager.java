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

    public static void contactsManagerApp(){
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
                    searchByName();
                    break;
                case(4):
                    //Delete and existing contact
                    break;
                case(5):
                    keepRunning = false;
                    break;
                default:
                    System.out.println("This should never happen if we wrote this correctly.");
                    //keepRunning = false;
                    break;
            }

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
        for (String line : linesInTheFile){
            System.out.println(line);
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
            Files.writeString(contactsPath, "\n"+ contactName + " | " + contactNum, StandardOpenOption.APPEND);
        } catch (IOException ioe){
            ioe.printStackTrace();

            System.out.println("Writing to file failed (I think).");
        } catch (NumberFormatException nfe){
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

    public static void searchByName() {
        boolean foundMatches = false;

        System.out.println("Type in a name to search: ");
        String searchTerm = sc.nextLine();
        List<String> linesInTheFile = new ArrayList<>();
        try {
            linesInTheFile = Files.readAllLines(contactsPath);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        for (String line : linesInTheFile) {
            if (line.contains(searchTerm)) {
                foundMatches = true;
                System.out.println("Hey we found a match to your entry!");
                System.out.println(line);
            }
        }
        if(!foundMatches){
            System.out.println("Sorry we did not find any matches :( ");
        }
    }


    //TODO: Delete existing contact


}
