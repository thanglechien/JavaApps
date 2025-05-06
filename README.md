# JavaApps
- This java apps is a simple integrated development which demonstrates the use of Java's graphic user interface, networking, database interaction, thread, inheritance, interface, and text file management.

- In this app, the client uses a Client window to interact with a simple database of movies that is hosted in a local server. The client can look up, update, insert, or delete a movie. All these choices will be reflected in the change in the database. In addition, a Server window is developed to enable network connection and disconnection and to display all the interactions the client has with the database and the interaction logs are also saved in a text file. 

- The development features a three-tier development: Presentation Tier, Network and Business Logic Tier, and Data Tier as in the below picture:

![image](https://github.com/user-attachments/assets/c0ba2684-5677-49ce-b30c-8a581892fa1e)



- The picture is a simplified UML for the apps, and only the class names and key variables are highlined to show connections between different classes in the entire development.

1. Presentation Tier
   
   This tier has two classes GUIClient and GUIServer. Both of them are JFrame Form classes.
   The GUIClient instance creates a Client Form as follows:
   
   ![image](https://github.com/user-attachments/assets/339a953f-1b1b-4e05-bfd3-ec923f2bd7f6)

   A client can use this form to select, update, insert, and delete a movie from the MovieData database which is hosted in the localhost server

   The GUIServer instance creates a Server SQL Logs Form as follows:

   ![image](https://github.com/user-attachments/assets/2bf048bf-bb07-49ef-ae73-a660ea627576)



   This form can be used to Start and Stop connection with the server and to display the logs of interaction between the Client and the Server

3. Network and Business Tier
- This tier processes the request from the client. At the beginning, a user starts the GUIClient form, which will kick-start both the GUIClient window and the GUIServer window to show up.
- The user will then click on the Start button on the GUIServer window to start the server. The start() method from the Server instance will be called. It will set up a server socket with the provided port, then it creates the below loop: a/ It sets the server ready waiting for the client to connect by calling server.accept()
- Next, when a user uses the GUIClient in the Presentation tier to send a request to the server, methods from a Client instance are called to create select, insert, update, and delete SQL commands. After a command is created, GUIClient will use the sendCommand() method of Client instance to send the command to the server. The sendCommand() will do as follows. First, it uses the helper method connectToServer() to connect to the server. When connected with the server with IO pathways established, it sents the command request created in the GUIClient to the server. When the server finishes the process of the request and sends back a response, this method will read the input stream. Then, it checks if the response is for a select request, it will display info to GUIClient using the helper method sendToGUI(). Finally, it disconnects from the server using the helper method disconnectFromServer() and displays a successful message to GUIClient.
- When the server receives a request from the client, the server loop continues from above with b) tt creates an instance of ProcessSQL processor and asks the Executor to excute the processor. (Note: when this method is called from an Server instance, the Sever constructor has created an Executor responses with a pool of 100 threads). The processor's run() is called to initialize the server's i/o streams. The server is ready for incoming request from the client (if client is also set up with its i/o streams), and if the request comes, the server will read the input stream. Depending on the type of requests (select, delete, insert, or update), the processor will create a related SQLService instance, and call doWork() method on that instance to process. The doWork() method creates an instance of SongDAO and call a relevant method on this instance to "CRUD" data from the database. If it is a lookup/select method, the doWork() method creates a command to respond to the client. It then uses the output.writeObject(command) to send back info to the client. The control is now returned to the processor. The processor creates an instance of SaveLogsService, and calls the methods doWork() on this instance (which in turn uses the FileHandler class methods) to save and display the SQL logs to the text file and the GUI server respectively. c/ The processor completes its job for this loop and returns the control to the server, which kicks off the next loop, the process repeats unitl the server is stopped.

3. Data Tier
- The data tier includes Movie and Query classes to model the real instances of movies and queries. It also contains the Data Access Object (DAO) classes (MovieDAO and FileHandler) to allow the server to interact with the database and the text files.
- The database for this project can be created in SQL Server using the script in the file MovieDatabase.sql
- It is important to note that we need to add the JDBC library to the project library to work with database. This can be download from the official website.
- The file LogsOfQueries.txt is also provided to save the logs of interactions of a user with the database


