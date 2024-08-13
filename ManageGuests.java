import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.*;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class ManageGuests extends JFrame {

private JPanel contentPane;

private JTable table;

public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
ManageGuests frame = new ManageGuests("505", "122"); // Example pid and cid
frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}

public ManageGuests(String pid, String cid) {
setTitle("Manage Guests");
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
setBounds(100, 100, 1173, 608);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

setContentPane(contentPane);
contentPane.setLayout(null);

JLabel lblNewLabel = new JLabel("Your Managers");
lblNewLabel.setForeground(Color.WHITE);
lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 29));
lblNewLabel.setBounds(382, 156, 214, 54);
contentPane.add(lblNewLabel);

JLabel lblGuests = new JLabel("Guests of your Customer");
lblGuests.setForeground(Color.WHITE);
lblGuests.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 33));
lblGuests.setBounds(757, 147, 448, 48);
contentPane.add(lblGuests);

JComboBox<String> comboBox = new JComboBox<String>();
comboBox.setFont(new Font("Tahoma", Font.BOLD, 23));
comboBox.setBounds(457, 220, 162, 54);
contentPane.add(comboBox);

// Fetch manager names corresponding to the provided pid from the "has" table
try {
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");
PreparedStatement preparedStatement = connection.prepareStatement("SELECT mid FROM has WHERE pid = ?");
preparedStatement.setString(1, pid);
ResultSet resultSet = preparedStatement.executeQuery();
while (resultSet.next()) {
String mid = resultSet.getString("mid");
// Fetch manager names corresponding to the retrieved mid from the "manager"
// table
PreparedStatement managerStatement = connection
.prepareStatement("SELECT mname FROM manager WHERE mid = ?");
managerStatement.setString(1, mid);
ResultSet managerResult = managerStatement.executeQuery();
if (managerResult.next()) {
String mname = managerResult.getString("mname");
comboBox.addItem(mname);
}
managerResult.close();
managerStatement.close();
}
resultSet.close();
preparedStatement.close();
connection.close();
} catch (SQLException ex) {
ex.printStackTrace();
}

JComboBox<String> comboBox_1 = new JComboBox<String>();
comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 23));
comboBox_1.setBounds(923, 220, 168, 54);
contentPane.add(comboBox_1);

JScrollPane scrollPane = new JScrollPane();
scrollPane.setBounds(582, 345, 410, 150);
contentPane.add(scrollPane);

table = new JTable();
scrollPane.setViewportView(table);
DefaultTableModel model = new DefaultTableModel();
model.addColumn("Manager Name");
model.addColumn("Guest Name");
table.setModel(model);

scrollPane.setViewportView(table);
JButton btnNewButton = new JButton("Manage");
btnNewButton.setBackground(Color.WHITE);
btnNewButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
// Get the selected manager name and guest family name
String selectedManager = (String) comboBox.getSelectedItem();
String selectedGuest = (String) comboBox_1.getSelectedItem();

DefaultTableModel model = (DefaultTableModel) table.getModel();
model.addRow(new Object[] { selectedManager, selectedGuest });
// Fetch the corresponding mid and gid from the database
int mid = -1;
int gid = -1;

try {
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root",
"root");

// Fetch mid
PreparedStatement managerStatement = connection
.prepareStatement("SELECT mid FROM manager WHERE mname = ?");
managerStatement.setString(1, selectedManager);
ResultSet managerResult = managerStatement.executeQuery();
if (managerResult.next()) {
mid = managerResult.getInt("mid");
}
managerResult.close();
managerStatement.close();

// Fetch gid
PreparedStatement guestStatement = connection
.prepareStatement("SELECT gid FROM guests WHERE family_name = ?");
guestStatement.setString(1, selectedGuest);
ResultSet guestResult = guestStatement.executeQuery();
if (guestResult.next()) {
gid = guestResult.getInt("gid");
}
guestResult.close();
guestStatement.close();

// Insert into manages table
if (mid != -1 && gid != -1) {
PreparedStatement insertStatement = connection
.prepareStatement("INSERT INTO manages VALUES (?, ?, ?, ?)");
insertStatement.setInt(1, mid);
insertStatement.setString(2, selectedManager);
insertStatement.setInt(3, gid);
insertStatement.setString(4, selectedGuest);
int rowsInserted = insertStatement.executeUpdate();
if (rowsInserted > 0) {
System.out.println("Data inserted into manages table successfully!");
}
insertStatement.close();
}

connection.close();
} catch (SQLException ex) {
ex.printStackTrace();
}
}
});

btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
btnNewButton.setBounds(693, 219, 149, 54);
contentPane.add(btnNewButton);

JButton btnNewButton_1 = new JButton("Go Back To Home");
btnNewButton_1.setBackground(Color.WHITE);
btnNewButton_1.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {

//WeddingPage weddingPage = new WeddingPage();
//weddingPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
//weddingPage.setVisible(true);

dispose();
}
});
btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 25));
btnNewButton_1.setBounds(655, 540, 270, 43);
contentPane.add(btnNewButton_1);

JLabel lblNewLabel_1 = new JLabel("New label");
lblNewLabel_1.setIcon(new ImageIcon(ManageGuests.class.getResource("/images/manageguests.jpg")));
lblNewLabel_1.setBounds(0, 0, 1545, 1515);
contentPane.add(lblNewLabel_1);

// Fetch family names corresponding to the provided cid from the "invites" table
try {
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");
PreparedStatement preparedStatement = connection.prepareStatement("SELECT gid FROM invites WHERE cid = ?");
preparedStatement.setString(1, cid);
ResultSet resultSet = preparedStatement.executeQuery();
while (resultSet.next()) {
String gid = resultSet.getString("gid");
// Fetch family names corresponding to the retrieved gid from the "guests" table
PreparedStatement guestStatement = connection
.prepareStatement("SELECT family_name FROM guests WHERE gid = ?");
guestStatement.setString(1, gid);
ResultSet guestResult = guestStatement.executeQuery();
if (guestResult.next()) {
String familyName = guestResult.getString("family_name");
comboBox_1.addItem(familyName);
}
guestResult.close();
guestStatement.close();
}
resultSet.close();
preparedStatement.close();
connection.close();
} catch (SQLException ex) {
ex.printStackTrace();
}

}
}