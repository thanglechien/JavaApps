/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

/**
 *
 * @author lctha
 */
public class SQLInsertService extends Service {
    //instance of MovieDAO
    private MovieDAO md;
    
    public SQLInsertService(String command)
    {
        super(command);
    }
    
    @Override
    public void doWork() {
        //1. split the string for the insert method (#insert|title|director|year|description|genreId)
        String[] commandArray = command.split("\\|", 6);
        
        //2. initiate the MovieDAO instance
        md = new MovieDAO();
        
        //3. create a new Movie instance
        Movie movie = new Movie(commandArray[1], commandArray[2], Integer.parseInt(commandArray[3]), commandArray[4], Integer.parseInt(commandArray[5]));
        
        //4. call the insert method from MovieDAO and pass the movie to it
        md.insertAMovie(movie);
    }
}

