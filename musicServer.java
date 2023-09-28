import java.io.*;
import java.net.*;

public class musicServer{
    public static void main(String[]args) throws Exception{
        // A welcome socket is initialized using the passed in args
        ServerSocket welcomeSocket=new ServerSocket(Integer.parseInt(args[0]));
        // Arralist to hold the times from file retrieval
        while(true){
            try{
                // The client socket is created after the client has been accepted by the welcome socket
                Socket clientSocket=welcomeSocket.accept();
                System.out.println("Server started on port: "+args[0]);
                // The input and output from the connection sockets are made
                BufferedReader fromClient=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                DataOutputStream toClient=new DataOutputStream(clientSocket.getOutputStream());
                // This integer contains the number that pretains to a mood on the user end
                int mood=0;
                // String array that holds all the data to be sent over to the user
                String[] recs={
                    "That's great to hear! If you would like to keep up this feeling, I suggest you listen to a very upbeat band! "+'\n'+
                    "My suggestion for this would be Good Kid, they recently released an EP that would be perfect for you!",
                    "I understand that feeling, I would like to suggest the genre shoegaze. The harsh wall of noise feeling from the "+'\n'+
                    "heavily distorted guitars really makes you feel like you are in a dream, a band I would suggest is known as Trauma Ray"+'\n'+
                    "their self-titled album really encapsulates their sound.",
                    "I am sorry to hear that! I would suggest some more angry music to really express the way you feel"+'\n'+
                    "A band I suggest is Slipknot, specifically their album titled Iowa, it is full of angry breakdowns and driving"+'\n'+
                    "guitar riffs.",
                    "I am sorry to hear that! I suggest you listen to some classical music, I would suggest Camille Saint Saens' The Swan",
                    "I see. Really you can go anywhere here in terms of music, I would suggest something you probably have never listed to, "+'\n'+
                    "new experiences are always welcomed right? I suggest The Garden, their penultimate album Kiss My Superbowl Ring has a lot of fun "+'\n'+
                    "sections and groovy yest challenging sound scapes",
                    "Alright! Time to get some work done right? For you I would recommend the album Manic by Wage War,"+'\n'+
                    "they have lots of driving guitar riffs and motivating choruses. The perfect music to set to loud and lock in and do some work!" 
                };
                // While the user has not exited
                while(mood!=7){
                    // Read the mood in from the client
                    mood=fromClient.read();
                    // If the user chose to exit, then break out of the loop
                    if(mood==7)
                        break;
                    // Integer array contains the number of lines in each option
                    int[] recLines={2, 3, 3, 1, 3, 2};
                    // Write to the output stream the number of lines and the actual string data
                    toClient.write(recLines[mood-1]);
                    toClient.writeBytes(recs[mood-1]+'\n');
                }
                // Close the IO streams and the connection socket
                fromClient.close();
                toClient.close();
                clientSocket.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}