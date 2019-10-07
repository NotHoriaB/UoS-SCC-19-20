import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;


public class SCC1_Email
{

    public static void main(String[] args) throws IOException
    {
        Scanner scan = new Scanner(System.in);      //create scanner for user input

        System.out.println("Please insert ID: ");

        String id = scan.next();        //get id from user

        URL address = new URL("https://www.ecs.soton.ac.uk/people/" + id);      //create the URL from user input

        BufferedReader br = new BufferedReader(new InputStreamReader(address.openStream()));        //create page reader

        for(int i = 0; i<7; i++)        //skip to line 8 where the name is
            br.readLine();

        String name = br.readLine();        //remember the line the program is after

        name = name.substring(11, (name.indexOf('|') - 1));         // cut the name that starts at character 11
                                                                    // and ends at 1 character before the first '|'

        if (name.equals("People"))      //if id is invalid web page will display "People" in that spot
        {
            System.out.print("Invalid ID");
        }
        else                            //if id checks out print the name
        {
            System.out.print("ID Belongs To: " + name);
        }
    }
}
