package movieapps;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The GUIClient is created by a JFrame Form class
 * The Design view enables to drop and drag GUI components to create the form
 * Below are just important snippets. Auto generated codes are excluded.
 */
public class GUIClient extends javax.swing.JFrame {

    /**
     * Creates new form GUIClient
     */
    //create an instance of Client, passing this GUI as a parameter
    private Client client = new Client(this);
    
    //constructor
    public GUIClient() {
        initComponents();
        //give the window a name
        setTitle("Client Form");
        //set location where it appears
        setLocation(100, 50);
        
        //set the items for the dropdown list
        ddlGenre.addItem("Horror");
        ddlGenre.addItem("Fantasy");
        ddlGenre.addItem("Action");
        ddlGenre.addItem("Drama");
        ddlGenre.addItem("Science Fiction");
        ddlGenre.addItem("Comedy");
        ddlGenre.addItem("Thriller");
        ddlGenre.addItem("Adventure");
        ddlGenre.addItem("Romance");
        ddlGenre.addItem("Crime");
    }
    
    //getters and setters
    public String getTxtDescription() {
        return txtDescription.getText();
    }

    public void setTxtDescription(String description) {
        this.txtDescription.setText(description);
    }

    public String getTxtDirector() {
        return txtDirector.getText();
    }

    public void setTxtDirector(String director) {
        this.txtDirector.setText(director);
    }

    public String getTxtTitle() {
        return txtTitle.getText();
    }

    public void setTxtTitle(String title) {
        this.txtTitle.setText(title);
    }

    public String getTxtYear() {
        return txtYear.getText();
    }

    public void setTxtYear(String year) {
        this.txtYear.setText(year);
    }
    
    public void setBadError(String text) {
        lblError.setForeground(Color.red);
        this.lblError.setText(text);
    }
    
    public void setGoodMessage(String text) {
        lblError.setForeground(Color.green);
        this.lblError.setText(text);
    }

    public void setDdlGenre(int id) {
        ddlGenre.setSelectedIndex(id);
    }

    //methods to handle buttons

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {                                          
        //popup diaglog to ask the movieID to look up
        String movideID = JOptionPane.showInputDialog("Please enter the movie ID");
        //create a select command
        String command = client.createSelectCommand("#select", Integer.parseInt(movideID));
        //send the select command to the server
        client.sendCommand(command);
    }                                         

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        //create update command
        String command = client.createUpdateCommand("#update", getTxtTitle(), getTxtDirector(), getTxtYear(), getTxtDescription(), ddlGenre.getSelectedIndex()+1);
        //send the command to server
        client.sendCommand(command);
    }                                         

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {                                          
        //create insert command
        String command = client.createInsertCommand("#insert", getTxtTitle(), getTxtDirector(), getTxtYear(), getTxtDescription(), ddlGenre.getSelectedIndex()+1);
        
        //send the command to server
        client.sendCommand(command);
        
        //set the text to clear
        txtTitle.setText("");
        txtDirector.setText("");
        txtYear.setText("");
        txtDescription.setText("");
    }                                         

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                          
        //popup asking for id to search
        String id = JOptionPane.showInputDialog("Enter id to delete");
        
        //display a dialog to confirm to delete or not
        int choice = JOptionPane.showConfirmDialog(rootPane, "Do you want to delete?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
            //create delete command
            String command = client.createDeleteCommand("#delete", Integer.parseInt(id));

            //send the command to server
            client.sendCommand(command);
        }
        else if (choice == JOptionPane.NO_OPTION)
        {
            JOptionPane.showMessageDialog(rootPane, "Delete is cancelled!");
        }
    }

    //the main method
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //open the client gui
                new GUIClient().setVisible(true);
                //open the server gui
                new GUIServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnSelect;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> ddlGenre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblDirector;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblGenre;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblYear;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtDirector;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtYear;
    // End of variables declaration                   
  }
