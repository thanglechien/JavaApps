/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

/**
 *
 * @author lctha
 */
public class SQLDeleteService extends Service{
    //instance of MovieDAO
    private MovieDAO md;
    
    public SQLDeleteService(String command)
    {
        super(command);
    }

    @Override
    public void doWork() {
        //1. split the command for select (#select|id)
        String[] commandArray = command.split("\\|", 2);
        
        //2. initiate the DAO instance
        md = new MovieDAO();
        
        //3. change id to int
        int movieID = Integer.parseInt(commandArray[1]);
        
        //4. call the MovieDAO's delete method to delete a movie by id
        md.deleteMovieById(movieID);
    }   
}
