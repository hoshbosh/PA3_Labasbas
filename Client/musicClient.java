import java.net.*;
import java.io.*;
public class musicClient {
    public static void main(String[]args) throws Exception{
        // Integer that holds the user mood
        int mood=0;
        try{
            // Initialize a new socket on the passed in port at the passed in address
            Socket connection=new Socket(args[0], Integer.parseInt(args[1]));
            // Buffered reader is created from the connection input stream
            BufferedReader fromServer=new BufferedReader(
                new InputStreamReader(connection.getInputStream())
            );
            // Output stream created from the connection
            DataOutputStream output=new DataOutputStream(connection.getOutputStream());
            // Initial greeting for the user
            System.out.println("Howdy-do! How are you feeling today?");
            // While the user has not chosen to exit
            while(mood!=7){
                // If user has chosen to exit then break out of the loop
                if(mood==7)
                    break;
                // Run the function that gets the mood from the user
                mood=getMood();
                // Write to the server the mood
                output.write(mood);
                // Reads in the number of lines from the server
                int lineCont=fromServer.read();
                // For each line coming in from the server, print the line
                for(int i=0;i<lineCont;i++){
                    System.out.println(fromServer.readLine());
                }
            }
            // Close all datastreams and socket
            output.close();
            fromServer.close();
            connection.close();
            System.out.println("Bye-bye!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // Function to get the mood from the user
    public static int getMood() throws Exception{
        // Integer that holds the user's choice
        int userChoice=0;
        System.out.println("");
        // Input method from the command
        BufferedReader inFromUser=new BufferedReader(new InputStreamReader(System.in));
        // While the user has chosen an invalid input
        while(userChoice<1||userChoice>7){
            // String array that holds all the options
            String[] menuOptions={"1. Happy", "2. Melancholy", "3. Angry", "4. Sad", "5. Calm", "6. Motivated", "7. Exit"};
            // For each loop to print out each option
            for(String mood : menuOptions){
                System.out.println(mood);
            }
            // Read the user's choice from the command line
            userChoice=Integer.parseInt(inFromUser.readLine());
            // If the user chose a valid option, break out of the while loop
            if(userChoice>=1&&userChoice<=7){
                break;
            }
            System.out.println("Incorrect Input!");
        }
        // Return the user choice
        return userChoice;

    }
} 

