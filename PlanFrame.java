import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PlanFrame extends JFrame {
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbms";
private static final String USERNAME = "root";
private static final String PASSWORD = "root";
private JPanel contentPane;
private JTextField textField;
private JLabel lblNewLabel_1;

/**
* Launch the application.
*/
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
PlanFrame frame = new PlanFrame();
frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}

/**
* Create the frame.
*/
public PlanFrame() {
setTitle("Planner Login");
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
setBounds(100, 100, 450, 300);
contentPane = new JPanel();
contentPane.setBackground(new Color(255, 255, 240));
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

setContentPane(contentPane);
contentPane.setLayout(null);

JLabel lblNewLabel = new JLabel("Enter you PID");
lblNewLabel.setBounds(54, 32, 135, 25);
lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
contentPane.add(lblNewLabel);

textField = new JTextField();
textField.setBounds(216, 32, 122, 26);
textField.setColumns(10);
contentPane.add(textField);

JButton btnNewButton = new JButton("View your assigned weddings");
btnNewButton.setBounds(57, 110, 301, 41);
btnNewButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {


               String pid = textField.getText();
               if (isValidPID(pid)) {
                   Plan planner = new Plan(pid); // Pass the pid parameter to the Plan constructor
                   planner.setVisible(true);
                   dispose();
               } else {
                   JOptionPane.showMessageDialog(null, "Invalid PID! Please enter a valid PID.", "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
});
btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
contentPane.add(btnNewButton);

lblNewLabel_1 = new JLabel(" ");
lblNewLabel_1.setIcon(new ImageIcon(PlanFrame.class.getResource("/images/user1.jpg")));
lblNewLabel_1.setBounds(21, 3, 436, 263);
contentPane.add(lblNewLabel_1);
}
private boolean isValidPID(String pid) {
       boolean isValid = false;
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       
       try {
           conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
           String query = "SELECT COUNT(*) FROM planner WHERE pid = ?";
           pstmt = conn.prepareStatement(query);
           pstmt.setString(1, pid);
           rs = pstmt.executeQuery();
           if (rs.next()) {
               int count = rs.getInt(1);
               isValid = count > 0;
           }
       } catch (SQLException ex) {
           ex.printStackTrace();
       } finally {
           try {
               if (rs != null) rs.close();
               if (pstmt != null) pstmt.close();
               if (conn != null) conn.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       }
       
       return isValid;
   }
private boolean checkPIDExists(String pid) {
boolean exists = false;
try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM planner WHERE PID = '" + pid + "'")) {

if (rs.next()) {
exists = true; // PID exists
}
} catch (SQLException ex) {
ex.printStackTrace();
}
return exists;
}

}
