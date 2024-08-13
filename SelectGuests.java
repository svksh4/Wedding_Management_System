import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import javax.swing.JList;

import javax.swing.JScrollPane;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class SelectGuests extends JFrame {

private JPanel contentPane;

private JList<String> list;

private JComboBox<String> comboBox;

private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbms";

private static final String USERNAME = "root";

private static final String PASSWORD = "root";

public static void main(String[] args) {

EventQueue.invokeLater(new Runnable() {

public void run() {

try {

SelectGuests frame = new SelectGuests(101);

frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

frame.setVisible(true);

} catch (Exception e) {

e.printStackTrace();

}

}

});

}

public SelectGuests(int cid) {

setTitle("Select Guests");

setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

setBounds(100, 100, 1318, 878);

contentPane = new JPanel();

contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

setContentPane(contentPane);

contentPane.setLayout(null);

JLabel lblNewLabel = new JLabel("Enter Guest Name:");
lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));

lblNewLabel.setBounds(376, 258, 283, 56);

contentPane.add(lblNewLabel);

JButton btnAdd = new JButton("Add");
btnAdd.setForeground(new Color(253, 245, 230));
btnAdd.setBackground(new Color(199, 21, 133));
btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 33));

btnAdd.setBounds(1022, 266, 115, 48);

contentPane.add(btnAdd);

btnAdd.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e) {

// Get the selected guest name from the combo box

String selectedGuest = (String) comboBox.getSelectedItem();

// Insert the selected guest into the invites table

insertGuest(cid, selectedGuest);

// Update the guest list

updateGuestList(cid);

}

});

JLabel lblGuestList = new JLabel("Guest List:");
lblGuestList.setFont(new Font("Times New Roman", Font.BOLD, 34));

lblGuestList.setBounds(592, 372, 170, 42);

contentPane.add(lblGuestList);

JScrollPane scrollPane = new JScrollPane();

scrollPane.setBounds(622, 441, 445, 216);

contentPane.add(scrollPane);

list = new JList<>();
scrollPane.setViewportView(list);
list.setFont(new Font("Tahoma", Font.PLAIN, 18));

JButton btnRemove = new JButton("Remove");
btnRemove.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
// Get the selected guest name from the list
                String selectedGuest = list.getSelectedValue();
               
                // Delete the selected guest from the invites table
                deleteGuest(cid, selectedGuest);
               
                // Update the guest list
                updateGuestList(cid);
}
});
btnRemove.setBackground(new Color(255, 0, 0));
btnRemove.setFont(new Font("Tahoma", Font.BOLD, 21));

btnRemove.setBounds(995, 690, 142, 42);

contentPane.add(btnRemove);

comboBox = new JComboBox<>();
comboBox.setForeground(new Color(245, 255, 250));
comboBox.setBackground(new Color(0, 0, 139));
comboBox.setFont(new Font("Verdana", Font.BOLD, 24));

comboBox.setBounds(705, 261, 254, 56);

contentPane.add(comboBox);

JButton btnNewButton = new JButton("Done");
btnNewButton.setForeground(new Color(255, 255, 255));
btnNewButton.setBackground(new Color(0, 100, 0));

btnNewButton.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e) {



dispose();

}

});

btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));

btnNewButton.setBounds(572, 689, 100, 42);

contentPane.add(btnNewButton);

populateComboBox();

// Get the planner ID associated with the given CID

int pid = getPlannerID(cid);

// Get the planner's name based on the retrieved pid

String plannerName = getPlannerName(pid);

// Display the planner's name on the frame
JLabel lblYourID = new JLabel("Your ID: " + cid);
lblYourID.setForeground(new Color(0, 0, 128));
lblYourID.setFont(new Font("Monotype Corsiva", Font.BOLD, 43));
lblYourID.setBounds(851, 171, 480, 56);
contentPane.add(lblYourID);

JLabel lblPlanner = new JLabel("Planner: " + plannerName);
lblPlanner.setForeground(new Color(0, 0, 128));
lblPlanner.setFont(new Font("Monotype Corsiva", Font.BOLD, 43));

lblPlanner.setBounds(362, 171, 480, 56);

contentPane.add(lblPlanner);

JLabel lblNewLabel_1 = new JLabel("New label");
lblNewLabel_1.setIcon(new ImageIcon(SelectGuests.class.getResource("/images/guests.jpg")));
lblNewLabel_1.setBounds(0, 0, 1540, 1045);
contentPane.add(lblNewLabel_1);

}

private int getPlannerID(int cid) {

Connection conn = null;

PreparedStatement stmt = null;

ResultSet rs = null;

int plannerID = -1; // Default value if planner ID is not found

try {

conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

String sql = "SELECT pid FROM plans WHERE cid = ?";

stmt = conn.prepareStatement(sql);

stmt.setInt(1, cid);

rs = stmt.executeQuery();

if (rs.next()) {

plannerID = rs.getInt("pid");

}

} catch (SQLException ex) {

ex.printStackTrace();

} finally {

try {

if (rs != null) {

rs.close();

}

if (stmt != null) {

stmt.close();

}

if (conn != null) {

conn.close();

}

} catch (SQLException ex) {

ex.printStackTrace();

}

}

return plannerID;

}

private String getPlannerName(int pid) {

Connection conn = null;

PreparedStatement stmt = null;

ResultSet rs = null;

String plannerName = "";

try {

conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

String sql = "SELECT pname FROM planner WHERE pid = ?";

stmt = conn.prepareStatement(sql);

stmt.setInt(1, pid);

rs = stmt.executeQuery();

if (rs.next()) {

plannerName = rs.getString("pname");

}

} catch (SQLException ex) {

ex.printStackTrace();

} finally {

try {

if (rs != null) {

rs.close();

}

if (stmt != null) {

stmt.close();

}

if (conn != null) {

conn.close();

}

} catch (SQLException ex) {

ex.printStackTrace();

}

}

return plannerName;

}

private void populateComboBox() {

Connection conn = null;

PreparedStatement stmt = null;

ResultSet rs = null;

try {

conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

String sql = "SELECT family_name FROM guests";

stmt = conn.prepareStatement(sql);

rs = stmt.executeQuery();

while (rs.next()) {

String family_name = rs.getString("family_name");

comboBox.addItem(family_name);

}

} catch (SQLException ex) {

ex.printStackTrace();

} finally {

try {

if (rs != null) {

rs.close();

}

if (stmt != null) {

stmt.close();

}

if (conn != null) {

conn.close();

}

} catch (SQLException ex) {

ex.printStackTrace();

}

}

}

private void insertGuest(int cid, String guestName) {

Connection conn = null;

PreparedStatement stmt = null;

try {

conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

String sql = "INSERT INTO invites (cid, gid) SELECT ?, gid FROM guests WHERE family_name = ?";

stmt = conn.prepareStatement(sql);

stmt.setInt(1, cid);

stmt.setString(2, guestName);

int rowsAffected = stmt.executeUpdate();

if (rowsAffected > 0) {

System.out.println("Guest inserted successfully.");

} else {

System.out.println("Failed to insert guest.");

}

} catch (SQLException ex) {

ex.printStackTrace();

} finally {

try {

if (stmt != null) {

stmt.close();

}

if (conn != null) {

conn.close();

}

} catch (SQLException ex) {

ex.printStackTrace();

}

}

}

private void updateGuestList(int cid) {
   Connection conn = null;
   PreparedStatement stmt = null;
   ResultSet rs = null;
   DefaultListModel<String> model = new DefaultListModel<>();
   try {
       conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
       String sql = "SELECT family_name FROM guests WHERE gid IN (SELECT gid FROM invites WHERE cid = ?)";
       stmt = conn.prepareStatement(sql);
       stmt.setInt(1, cid);
       rs = stmt.executeQuery();
       while (rs.next()) {
           String familyName = rs.getString("family_name");
           model.addElement(familyName);
       }
       list.setModel(model);
       list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Set selection mode
   } catch (SQLException ex) {
       ex.printStackTrace();
   } finally {
       try {
           if (rs != null) {
               rs.close();
           }
           if (stmt != null) {
               stmt.close();
           }
           if (conn != null) {
               conn.close();
           }
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
   }
}
private void deleteGuest(int cid, String guestName) {
   Connection conn = null;
   PreparedStatement stmt = null;
   try {
       conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
       String sql = "DELETE FROM invites WHERE cid = ? AND gid IN (SELECT gid FROM guests WHERE family_name = ?)";
       stmt = conn.prepareStatement(sql);
       stmt.setInt(1, cid);
       stmt.setString(2, guestName);
       int rowsAffected = stmt.executeUpdate();
       if (rowsAffected > 0) {
           System.out.println("Guest(s) deleted successfully.");
       } else {
           System.out.println("No guest(s) found to delete.");
       }
   } catch (SQLException ex) {
       ex.printStackTrace();
   } finally {
       try {
           if (stmt != null) {
               stmt.close();
           }
           if (conn != null) {
               conn.close();
           }
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
   }
}
}
