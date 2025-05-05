/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

/**
 *
 * @author lctha
 */
public class Query {
    private String timeStamp;
    private String queryDetails;
    
    
    //constructor
    public Query(String timeStamp, String queryDetails)
    {
        this.timeStamp = timeStamp;
        this.queryDetails = queryDetails;
    }
    
    //getters and setters

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getQueryDetails() {
        return queryDetails;
    }

    public void setQueryDetails(String queryDetails) {
        this.queryDetails = queryDetails;
    }
    
    /*
    This method writes detailed information of a SQL query to a string form
    ----------------------------------------------------------------------------------------
    There is no parameter in this method
    ----------------------------------------------------------------------------------------
    Return a string of query information in the format it is written in the text file
    */
    
    public String writeAsRecord()
    {
        return timeStamp + "#" +
                queryDetails;
    }
}
