
package hfourseaokay.admin;

import hfourseaokay.ConnectionManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RemoveDrugPanel extends JPanel{
    
    private DefaultTableModel productsModel;
    private JTable productsTable;

    public RemoveDrugPanel() {
        
        productsModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        productsTable = new JTable();
        productsTable.setModel(productsModel);
        
        Object columnHeaders[] = new Object[]{"Name"};
        productsModel.setColumnIdentifiers(columnHeaders);
        
        populateDataTable("SELECT * FROM Products");
        
        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try{
            
                    ConnectionManager connectionManager = ConnectionManager.getInstance();
            
                    Connection connection = connectionManager.getConnection();
                    
                    PreparedStatement preparedStatement = 
                    connection.prepareStatement("DELETE FROM Products WHERE Name='" 
                            + productsModel.getValueAt
                            (productsTable.getSelectedRow(), 0) + "'");
                    
                    preparedStatement.executeUpdate();
                    
                    System.out.println("Deleted record");
                    populateDataTable("SELECT * FROM Products");

            
                }catch(SQLException error){
                    error.printStackTrace();
                }
            } 
        });
        
        setLayout(new BorderLayout());
       
        add(productsTable, BorderLayout.CENTER);
        add(removeBtn, BorderLayout.SOUTH);
        
        
    }
    
    
    private void populateDataTable(String query){
        
        if (productsModel.getRowCount() > 0) {
            for (int i = productsModel.getRowCount() - 1; i > -1; i--) {
                productsModel.removeRow(i);
            }
        }   
        
        
        Object rowData[] = new Object[1];
        try {

            Connection connection;
            connection = ConnectionManager.getInstance().getConnection();

            PreparedStatement preparedStatement 
                    = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                rowData[0] = resultSet.getString("Name");
               

                productsModel.addRow(rowData);
            }

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }
        
        productsTable.repaint();
        productsTable.revalidate();
        
    }
    
    
    
}
