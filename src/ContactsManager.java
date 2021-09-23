import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

public class ContactsManager {
    private static Path contactsPath = Paths.get("src/contacts.txt");
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
    }

    //TODO: Add Contact


    //TODO: Search by Name


    //TODO: Delete existing contact


}
