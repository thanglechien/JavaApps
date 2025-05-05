/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 *
 * @author lctha
 */
public class SaveLogsService extends Service {
    
    private GUIServer gs;
    
    // Query array to hold the logs read from the txt file
    private Query[] logs;
    
    private Query query;
    
    //constructor
    public SaveLogsService(String command, GUIServer gs)
    {
        super(command);
        this.gs = gs;
        //get data from the txt file and save them to a Query array
        this.logs = FileHandler.getData("LogsOfQueries.txt");
    }
    
    //methods
    @Override
    public void doWork() {
        //save Logs to txt file
        saveLogs(command);
        
        //display logs to GUISever
        displayToGuiServer();
    }
    
    //this method takes the query command string, create a new Query object, add it to the Query array and save the new array to the txt file.
    public void saveLogs(String command)
    {
        //create a new log
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        if(command.startsWith("#"))
        {
            command = command.substring(1);
        }
        query = new Query(timeStamp, command);
        //add the new log to the logs array
        //a.create a temparary logs array
        Query[] tempLogs = new Query[logs.length+1];
        //b.copy the existing logs to the temporary logs
        System.arraycopy(logs, 0, tempLogs, 0, logs.length);
        //c.add the new log to the end of the temporary array
        tempLogs[logs.length] = query;
        //d. update the existing logs with new values
        logs = tempLogs;
        //save the logs to text file
        FileHandler.save(logs);
    }
    
    //this method is called from the doWork() method to display the query logs to the GUIServer
    public void displayToGuiServer()
    {
        String content ="";
        for (Query log: logs)
        {
            //convert TimeStamp to daily-used time
            String timeStamp = log.getTimeStamp();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            LocalDateTime datetime = LocalDateTime.parse(timeStamp, formatter);
            
            //build the content to show to GUI Server
            content = String.format(datetime+" ---- command: "+log.getQueryDetails()+"\n");
        }
        //display to GUI
        gs.setTxtQueryLogs(content);
        
        //display to Console to check
        System.out.print(logs[logs.length-1].getTimeStamp());
        System.out.println(logs[logs.length-1].getQueryDetails());
    }    
}
