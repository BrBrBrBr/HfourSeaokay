/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hfourseaokay.admin;

import hfourseaokay.ConnectionManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author brinlee
 */
public class AddPharmacistPanel extends JPanel{
    
    JTextField usernameField;
    JPasswordField passwordField;
    JTextField nameField;
    JTextField surnameField;
    JComboBox isAdminBox;

    public AddPharmacistPanel() {
        
        JLabel nameLbl = new JLabel("Name: ");
        JLabel scheduleLbl = new JLabel("Schedule: ");
        JLabel priceLbl = new JLabel("Price: ");
        JLabel quantityLbl = new JLabel("Quantity: ");
        JLabel dteLbl = new JLabel("Days Until Expriry: ");
        
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        
        labelsPanel.add(nameLbl);
        labelsPanel.add(scheduleLbl);
        labelsPanel.add(priceLbl);
        labelsPanel.add(quantityLbl);
        labelsPanel.add(dteLbl);
        
        DefaultComboBoxModel isAdminModel = new DefaultComboBoxModel();

        isAdminModel.addElement("TRUE");
        isAdminModel.addElement("FALSE");
        
        isAdminBox = new JComboBox(isAdminModel);    
        isAdminBox.setSelectedIndex(0);
      
        
        usernameField = new JTextField("Username");
        //nameField.setMinimumSize(new Dimension(50, 28));
        passwordField = new JPasswordField("Schedule");
        nameField = new JTextField("Name");
        surnameField = new JTextField("Surname");
        
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        
        fieldsPanel.add(usernameField);
        fieldsPanel.add(passwordField);
        fieldsPanel.add(nameField);
        fieldsPanel.add(surnameField);
        fieldsPanel.add(isAdminBox);
        
        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try{
            
                    ConnectionManager connectionManager = ConnectionManager.getInstance();
            
                    Connection connection = connectionManager.getConnection();
                    
                    PreparedStatement preparedStatement = 
                    connection.prepareStatement("INSERT INTO PharmacistData "
                            + "VALUES('" + usernameField.getText() + "', " 
                            + "'" + new String(passwordField.getPassword()) + "', "
                            + "'" + nameField.getText() + "', "
                            + "'" + surnameField.getText() + "', "
                            + (String)isAdminBox.getSelectedItem()
                            + ")");
                    
                    preparedStatement.executeUpdate();
                    
                    System.out.println("Inserted record");

            
                }catch(SQLException error){
                    error.printStackTrace();
                }
            } 
        });

        
        
        fieldsPanel.add(addBtn);
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(fieldsPanel);
        
        
    }
    
    
    
  
    
}
