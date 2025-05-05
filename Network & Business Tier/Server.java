/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

import java.net.ServerSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author lctha
 */
public class Server {
    //object to connect to Server side
    private ServerSocket server;
    private ExecutorService responses;
    private final int HTTP_PORT = 3202;
    private boolean running = false;
    //reference to GUIServer
    private GUIServer gs;
    private ProcessSQL processor;
    //constructor
    
    public Server(GUIServer gs)
    {
        this.gs = gs;
        
        //ExecutorService responses to start a new threadpool of 100 threads
        responses = Executors.newFixedThreadPool(100);
    }
    
    //Methods
    
    /*
    This method is called from a Server instance in the GUIServer when the Start button is clicked.
    It will set up a server socket with the provided port, then it creates the below loop:
    1/ It sets the server ready waiting for the client to connect by calling server.accept()
    2/ It creates an instance of ProcessSQL processor and asks the Executor to excute the processor.
    (Note: when this method is called from an Server instance, the Sever constructor has created 
    an Executor responses with a pool of 100 threads).
    The processor's run() is called to initialize the server's i/o streams.
    The server is ready for incoming request from the client (if client is also set up with its i/o streams), 
    and if the request comes, the server will read the input stream.
    Depending on the type of requests (select, delete, insert, or update), the processor will create a related SQLService instance,
    and call doWork() method on that instance to process.
    The doWork() method creates an instance of SongDAO and call a relevant method on this instance to "CRUD" data from the database.
    If it is a lookup/select method, the doWork() method creates a command to respond to the client. 
    It then uses the output.writeObject(command) to send back info to the client. The control is now returned to the processor.
    The processor creates an instance of SaveLogsService, and calls the methods doWork() on this instance 
    (which in turn uses the FileHandler class methods) to save and display the SQL logs to the text file and the GUI server respectively.
    3/ The processor completes its job for this loop and returns the control to the server, which kicks off the next loop, the process
    repeats unitl the server is stopped.
    */
    
    public void start()
    {
        running = true;
        
        try
        {
            //sets up a new serversocket with the port 3202 and the backlog of 100
            server = new ServerSocket(HTTP_PORT, 100);    
        }
        catch (Exception e)
        {
            System.out.println("Error occured: "+ e.toString());
        }
        
        //display the message to GUI
        gs.setTxtQueryLogs("Wait for the client to connect...\n");
        //loop where the server instance listens for incoming connections while it is not disconnected
        //this through a SocketException to catch
        while (running)
        {
            try 
            {
                //initialize a instance of ProcessSQL
                processor = new ProcessSQL(server.accept(), gs);
                //pass it to responses to run on its own thread
                responses.execute(processor);
            } 
            catch (SocketException se)
            {
                if (!running) 
                {
                System.out.println("Server socket was closed. Server stopped.");
                } 
                else {
                    System.out.println("SocketException while accepting connection: " + se.getMessage());
                }
            }       
            catch (Exception e) 
            {
                System.out.println("Error occured: "+ e.toString());
            }
        }
    }
    
    
    //this method is called by an Server instance in the GUIServer when the Stop button is clicked
    public void disconnectFromServer()
    {
        running = false;
        try 
        {
            server.close();
        } catch (Exception e) 
        {
            System.out.println("Error occured: "+ e.toString());
        }
    }
}