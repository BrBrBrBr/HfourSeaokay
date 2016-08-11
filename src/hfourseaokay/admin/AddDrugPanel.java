
package hfourseaokay.admin;

import hfourseaokay.AdminPage;
import hfourseaokay.ConnectionManager;
import hfourseaokay.Dispensory;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddDrugPanel extends JPanel{
    
    JTextField nameField;
    JTextField scheduleField;
    JTextField priceField;
    JTextField quantityField;
    JTextField dteField;

    public AddDrugPanel() {
        
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
        
        nameField = new JTextField("Name");
        //nameField.setMinimumSize(new Dimension(50, 28));
        scheduleField = new JTextField("Schedule");
        priceField = new JTextField("Price");
        quantityField = new JTextField("Quantity");
        dteField = new JTextField("Days until expiry");
        
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        
        fieldsPanel.add(nameField);
        fieldsPanel.add(scheduleField);
        fieldsPanel.add(priceField);
        fieldsPanel.add(quantityField);
        fieldsPanel.add(dteField);
        
        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try{
            
                    ConnectionManager connectionManager = ConnectionManager.getInstance();
            
                    Connection connection = connectionManager.getConnection();
                    
                    PreparedStatement preparedStatement = 
                    connection.prepareStatement("INSERT INTO PRODUCTS "
                            + "VALUES('" + nameField.getText() + "', " 
                            + scheduleField.getText() + ", "
                            + "'" + priceField.getText() + "', "
                            + dteField.getText() + ", "
                            + quantityField.getText()
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
