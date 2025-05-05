/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author lctha
 */
public class FileHandler {
    
    // arrays to hold a group of SQL queries
    private static Query[] queries;
    
    // scanner object to read a file
    private static Scanner input;
    
    /*
    This method accepts a file name and reads data from the file
    ---------------------------------------------------------------
    String fileName - the name of the file from which data are read
    ---------------------------------------------------------------
    Return an array of queries
    */
    
    public static Query[] getData(String fileName)
    {
        //attemp to create an instance of File and then create an instance of Scanner to read that File object 
        try 
        {
            input = new Scanner(new File(fileName));
        }
        //catch possible exceptions during that procees, from most specific to least specific exceptions
        catch (FileNotFoundException fnfe)
        {
            System.out.println("File Not Found.");
        }
        catch (IOException ioe)
        {
            System.out.println("Unknown IO error occurred.");
        }
        catch (Exception e)
        {
            System.out.println("An unknown error occurred");
        }
        
        //attempts to read first piece of data from the file and use that info to initiate an array of bank accounts
        try
        {
            
            //int size = input.nextInt();
            int size = Integer.parseInt(input.nextLine().trim());
            queries = new Query[size];  
            //System.out.println(size);
        }
        //catch possible exceptions
        catch (NoSuchElementException e)
        {
            System.out.println("File Format Error");
        }
        catch (IllegalStateException ise)
        {
            System.out.println("Error Reading Error.");
        }
        
        //loop through the file, check if there are data available, use them to populate the arrays of query logs, until the end of file
        int index = 0;
        try 
        {
            while (input.hasNextLine())
            {
                //read a line from the file
                String line = input.nextLine().trim();
                //split the line into two parts using the delimiter #, results are stored in the string arrays
                String[] parts = line.split("#", 2);
                if (parts.length == 2)
                {
                    //get the data from the string array to create an instance of Query and add it to an array of querty.
                    String timestamp = parts[0].trim();
                    String queryDetails = parts[1].trim();
                    queries[index] = new Query(timestamp, queryDetails);
                    index++;
                }
            }
            //print the last query added
            System.out.println("Log Added.");
            
        } catch (Exception e) {
        }
        
        //return an array of bank accounts populated with data from the file
        return queries;
    }
    
    /*
    This method accepts an array of Query and save them to a txt file
    ---------------------------------------------------------------------------
    Query[] queriesToSave - the array of the queries to be written
    ---------------------------------------------------------------------------
    Return nothing but save the content of the array to a text file 
    */
    
    public static void save(Query[] queriesToSave)
    {
        
        try 
        {
            //create an instance of FileWriter/false means overwritten
            FileWriter fw = new FileWriter("LogsOfQueries.txt", false);
            
            // write the number of record, then a break line
            fw.write(Integer.toString(queriesToSave.length));
            fw.write(System.lineSeparator());
            
            // write each record one by one to file
            for (int i =0; i<queriesToSave.length; i++) 
            {
                fw.write(queriesToSave[i].writeAsRecord());
                fw.write(System.lineSeparator());
            }
            fw.close();
        }
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }
}
