/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

/**
 *
 * @author lctha
 */
public abstract class Service {
    public String command;
    public Service(String command)
    {
        this.command = command;
    }
    //abstract method that will be overriden by subclasses
    public abstract void doWork();
}