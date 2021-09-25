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
                    //Add a new contact
                    break;
                case(3):
                    //Search for a contact by name
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
            contactName = sc.nextLine();
            contactNum = sc.nextLine();
            //parsing to Long should throw an error if there are non numeric characters in there but we also don't want numbers wih more than 10 digits (yeah, country code is a thing IRL but I'm assuming we're only calling people in our own country). Have not tested yet though
            if (Long.parseLong(contactNum) > numericallyHighestPossiblePhoneNumber){
                throw new IllegalArgumentException();
            }
            Files.writeString(contactsPath, contactName + " : " + contactNum, StandardOpenOption.APPEND);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }


    //TODO: Search by Name


    //TODO: Delete existing contact


}
