/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hfourseaokay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author brinlee
 */
public class ConnectionManager implements Runnable{

    private static ConnectionManager connectionManager = new ConnectionManager();
    
    private Connection connection;
    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";
    private String databaseName = "usr/database";
    private String url = "jdbc:derby://localhost:1527/database ";

    public ConnectionManager() {
    }

    

    public Connection getConnection() {
        return connection;
    }

    public void connect(){

    }
    
    public static ConnectionManager getInstance(){
        return connectionManager;
    }

    // Concurrent connection method
    @Override
    public void run() {
        try {
            Class.forName(driver).newInstance();

            connection = DriverManager.getConnection(url, "brinlee", "brinlee");
            System.out.println("Established connection to database");

            

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "SQL Exception");
        }catch (ClassNotFoundException cnfExcep){
            cnfExcep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "Class Not Found Exception");
        }catch (Exception excep){
            excep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "Exception");
        }
    }

    private void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(null, errorMessage);
    }
}