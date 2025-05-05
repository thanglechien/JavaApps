/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

/**
 *
 * @author lctha
 */
public class SQLUpdateService extends Service {
    //instance of MovieDAO
    private MovieDAO md;
    
    public SQLUpdateService(String command) {
        super(command);
    }

    @Override
    public void doWork() {
        //1. split the string for the update method (#update|id|title|director|year|description|genreId)
        String[] commandArray = command.split("\\|", 7);
        
        //2. initiate the MovieDAO instance
        md = new MovieDAO();
        
        //3. create a new Movie instance (note: choose the right constructor, one with genreId parameter last)
        Movie movie = new Movie(Integer.parseInt(commandArray[1]), commandArray[2], commandArray[3], Integer.parseInt(commandArray[4]), commandArray[5], Integer.parseInt(commandArray[6]));
        
        //4. call the update method from MovieDAO and pass the movie to it
        md.updateMovieById(movie);
    }
    
}
