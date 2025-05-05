/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

import java.io.ObjectOutputStream;

/**
 *
 * @author lctha
 */
public class SQLSelectService extends Service{
    //select requires an outputstream so it can send a message back to client
    private ObjectOutputStream output;
    //instance of SongDAO
    private MovieDAO md;
    
    //constructor
    public SQLSelectService(String command, ObjectOutputStream output)
    {
        super(command);
        this.output = output;
    }

    @Override
    public void doWork() 
    {
        //split the command for select (#select|id)
        String[] commandArray = command.split("\\|", 2);
        
        //initiate a DAO instance
        md = new MovieDAO();
        
        //change id to int
        int movieID = Integer.parseInt(commandArray[1]);
        
        //query database by id
        Movie movie = md.getMovieById(movieID);
        
        //create a command string back to the client
        String command =    movie.getMovieID() + "|" + 
                            movie.getTitle() + "|" +
                            movie.getDirector() + "|" +
                            movie.getYearReleased() + "|" +
                            movie.getDescription() + "|" +
                            movie.getGenreID();
        
        //send back the command to client
        try 
        {
            output.writeObject(command); //back to Client line 98
        } 
        catch (Exception e) 
        {
            System.out.println("Error in select service");
        }
    }
    
}
