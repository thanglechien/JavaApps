/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author lctha
 */
public class ProcessSQL implements Runnable {
    private Socket connection;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String command;
    private GUIServer gs;
    
    //constructor
    public ProcessSQL(Socket connection, GUIServer gs)
    {
        this.connection = connection;
        this.gs = gs;
    }
    
    //run method
    @Override
    public void run() {
        
        try
        {
            // initialize io streams
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            
            input = new ObjectInputStream(connection.getInputStream());
            //hangs waiting
            //get incoming command message from a client (from Client 90)
            command = (String)input.readObject();
             
            //check if the command string start with #select
            if(command.indexOf("#select") == 0)
            {
                //create an instanace of SQLSelectService
                Service select = new SQLSelectService(command, output);
                //call the doWork method
                select.doWork();
            }
            //check if the command string start with #update
            else if(command.indexOf("#update") ==0)
            {
                //create an instance of SQLUpdateService
                Service update = new SQLUpdateService(command);
                //call the doWork method
                update.doWork();
            }
            //check if the command string start with #insert
            else if(command.indexOf("#insert") ==0)
            {
                //create an instance of SQLInsertService
                Service insert = new SQLInsertService(command);
                //call the doWork method
                insert.doWork();
            }
            //check if the command string start with #delete
            else if(command.indexOf("#delete") ==0)
            {
                //create an instance of SQLDeleteService
                Service delete = new SQLDeleteService(command);
                //call the doWork method
                delete.doWork();
            }
            //create an instance of SaveLogsService class and pass it a command and a GUIServer reference
            Service saveDisplay = new SaveLogsService(command, gs);
            //call the doWork method to save the log to the txt file and display it on GUIServer
            saveDisplay.doWork();
            
        }
        catch (Exception e)
        {
            System.out.println("Error occured: "+ e.toString());
        }
    }
}
