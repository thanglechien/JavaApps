package movieapps;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author lctha
 */
public class Client {
    //object to make connection client side
    static Socket client;
    //Declare IO pathways
    static ObjectOutputStream output;
    static ObjectInputStream input;

    //reference to the gui
    GUIClient gc;
    
    //id isnt displayed in gui so we can keep track here
    private int currentID = -1;
    
    //the constructor has GUIClient parameter to initalize gc
    public Client(GUIClient gc) {
        this.gc = gc;
    }

    public int getCurrentID() {
        return currentID;
    }

    public void setCurrentID(int currentID) {
        this.currentID = currentID;
    }
    
    //METHODS
    
    //1. Methods to create SQL command
    /*
    These methods are used in the GUIClient to create select, insert, update, and delete SQL commands.
    After the command is created, GUIClient will use the sendCommand() method of this class to send the command to the server.
    */
    
    //command for select button ex. #select|5
    public String createSelectCommand(String command, int id) {
        return command + "|" + id;
    }
    
    //create Update command for Update button
    public String createUpdateCommand(String command, String title, String director, String year, String description, int genreID)
    {
        return command+"|"+getCurrentID()+"|"+title+"|"+director+"|"+year+"|"+description+"|"+genreID;
    }
    
    //create Delete command for Delete button
    public String createDeleteCommand(String command, int id)
    {
        return command + "|" + id;
    }
    
    //create Insert command for the Insert button
    public String createInsertCommand(String command, String title, String director, String year, String description,int genreID)
    {
        return command+"|"+title+"|"+director+"|"+year+"|"+description+"|"+genreID;
    }
    
    //2. Method to send the command to the server to make a request
    /*
    This method is used in the GUIClient. First, it uses the helper method connectToServer() to connect to the server.
    When connected with the server with IO pathways established, it sents the command request created in the GUIClient to the server.
    When the server finishes the process of the request and sends back a response, this method will read the input stream.
    Then, it checks if the response is for a select request, it will display info to GUIClient using the helper method sendToGUI()
    Finally, it disconnects from the server using the helper method disconnectFromServer() and displays a successful message to GUIClient
    */
    public void sendCommand(String command) {

        try {
            //connect to server
            connectToServer();
            //send command
            output.writeObject(command); //to line 42 ProcessSQL
            //hang for response from server
            //identify the command that was sent
            String[] commandArray = command.split("\\|", 7);
            
            //get sql info back from server if command is "select"
            if(command.indexOf("#select") == 0)
            {
                String messageIn = (String) input.readObject(); //back from SQLSelectService line 52
                //display info in GUI
                sendToGUI(messageIn);
            }
            //disconnect
            disconnectFromServer();
            //set message in GUI that it was successful
            gc.setGoodMessage("Update From Server Successful");
        } 
        catch (Exception e) {
            //error in gui if failed
            gc.setBadError("Error Communicating with Server");
            System.out.println("Send command didnt work");
        }
    }
    
    //3. Helper methods
    /*
    There are three helper methods used by the sendCommand() method: connectToServer(), disconnectFromServer(), and sendToGUI()
    */
    
    //connect to server (used in sendCommand())
    //this throws a ConnectException to catch
    public void connectToServer() {
        try {
            //establish where we want to connect
            client = new Socket(InetAddress.getByName("127.0.0.1"), 3202);
            //establish io pathways
            output = new ObjectOutputStream(client.getOutputStream());
            output.flush();
            input = new ObjectInputStream(client.getInputStream());

            System.out.println("Connected!");
        } 
        catch (ConnectException ce)
        {
            System.out.println("Cannot connect the the server. Is it running?");
            JOptionPane.showMessageDialog(gc, "Server is not working. Start it to continue!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnectFromServer() {
        try {
            client.close();
        } catch (Exception e) {
            System.out.println("disconnect did not work");
        }
    }
    
    //this method is used in the sendCommand to display info to GUI
    public void sendToGUI(String command) {
        //example command back from a select:
        //5|Inception|Christopher Nolan|2010|A thief enters dreams to steal secrets|5
        //cut string into how many we need(6)
        String[] commandArray = command.split("\\|", 6);
        //create new song object
        Movie movie = new Movie(Integer.parseInt(commandArray[0]), commandArray[1], commandArray[2], Integer.parseInt(commandArray[3]), commandArray[4], Integer.parseInt(commandArray[5]));
        //set current id we got from database
        setCurrentID(movie.getMovieID());
        //send to gui
        gc.setTxtTitle(movie.getTitle());
        gc.setTxtDirector(movie.getDirector());
        gc.setTxtDescription(movie.getDescription());
        gc.setTxtYear("" + movie.getYearReleased());
        gc.setDdlGenre(movie.getGenreID() - 1);//-1 as combobox starts from 0
    }
}
