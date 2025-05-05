# JavaApps
- This java apps is a simple integrated development which demonstrates the use of Java's graphic user interface, networking, database interaction, thread, inheritance, interface, and text file management.

- In this app, the client uses a Client window to interact with a simple database of movies that is hosted in a local server. The client can look up, update, insert, or delete a movie. All these choices will be reflected in the change in the database. In addition, a Server window is developed to enable network connection and disconnection and to display all the interactions the client has with the database and the interaction logs are also saved in a text file. 

- The development features a three-tier development: Presentation Tier, Network and Business Logic Tier, and Data Tier as in the below picture

![image](https://github.com/user-attachments/assets/9cf52e2a-97e3-4d4b-a1f8-ee07b9b3b411)

- The picture is a simplified UML for the apps, and only the class names and key variables are highlined to show connections between different classes in the entire development.

1. Presentation Tier
   
   This tier has two classes GUIClient and GUIServer. Both of them are JFrame Form classes.
   The GUIClient instance creates a Client Form as follows:
   
   ![image](https://github.com/user-attachments/assets/e9ac535f-d598-4245-94b8-da7075351f43)

   A client can use this form to select, update, insert, and delete a movie from the MovieData database which is hosted in the localhost server

   The GUIServer instance creates a Server SQL Logs Form as follows:

   ![image](https://github.com/user-attachments/assets/fdb9fa1b-26d3-4f98-a130-3711587c7450)

   This form can be used to Start and Stop connection with the server and to display the logs of interaction between the Client and the Server

2. Network and Business Tier

This tier 
  
   

