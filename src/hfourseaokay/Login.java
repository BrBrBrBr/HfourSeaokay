package hfourseaokay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Login {
    
    
    private String dbUsername = "";
    private String dbPassword = "";
    private String dbName = "";
    private String dbSurname = "";
    
    private String isAdmin = "";
    
    public boolean login(String username, String password){

        boolean loginStatus = false;
        
        try{
            
            ConnectionManager connectionManager = ConnectionManager.getInstance();
            
            Connection connection = connectionManager.getConnection();

         
            

            //"SELECT * FROM MEMBERS WHERE ID=" + user + " AND PASSWORD='" + pass + "'";
            // String SQLadmin = "SELECT * FROM PHARMACISTDATA WHERE USERNAME=" + user + " AND PASSWORD='" + pass + "AND ISADMIN="+ isAdmin;
            
            
            PreparedStatement preparedStatement = 
                    connection.prepareStatement("SELECT * FROM "
                            + "BRINLEE.PHARMACISTDATA WHERE Username='" + username + "'");
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                dbUsername = resultSet.getString("Username");
                dbPassword  = resultSet.getString("Password");
                dbName = resultSet.getString("Name");
                dbSurname = resultSet.getString("Surname");
                isAdmin = resultSet.getString("IsAdmin");
            }
                   
            if(password.equals(dbPassword)){
                System.out.println("Login successful");
                loginStatus = true;
                
                if(isAdmin.equals("FALSE")){
                    Dispensory dispensory = new Dispensory();
                    dispensory.setVisible(true);
                }else if(isAdmin.equals("TRUE")){
                    AdminPage adminPage = new AdminPage();
                    adminPage.setVisible(true);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Login Unsuccessful");
            }
                
                
        }
        
        catch(SQLException error){
            error.printStackTrace();
        }
       
        return loginStatus;
        
    }
    
}
