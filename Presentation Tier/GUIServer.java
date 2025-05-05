package movieapps;

import javax.swing.JTextArea;

/**
 * The GUIServer is created by a JFrame Form class
 * The Design view enables to drop and drag GUI components to create the form
 * Below are just important snippets. Auto generated codes are excluded.
 */
public class GUIServer extends javax.swing.JFrame {
    
    //create new instance of server and pass reference of the gui
    //this instance has the GUI info, when it calls one of its methods, the method will have GUI input to process
    Server server = new Server(this);
    
    public GUIServer() {
        initComponents();
        //the window a name
        setTitle("Server SQL Logs Form");
        //set the location where it appears on the screen
        setLocation(800, 50);
    }

    //getters and setters
    public JTextArea getTxtQueryLogs() {
    return txtQueryLogs;
    }

    public void setTxtQueryLogs(String logsContent) {
        this.txtQueryLogs.append(logsContent);
    }

    //methods to handle the Start and Stop buttons
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //start the server via a new Thread
        new Thread(() -> server.start()).start();
    }                                        

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {                                        
        //stop the server
        server.disconnectFromServer();
        //display the message to GUI
        this.setTxtQueryLogs("The server is disconnected...\n");
    }                                       

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtQueryLogs;
    // End of variables declaration                   
}
