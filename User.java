import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class User extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox_1;
    private JComboBox<String> comboBoxBudget; // New combo box for budget
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbms";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    User frame = new User();
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public User() {
        setTitle("User Registration");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1165, 641);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 105, 180));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Registraton Form");
        lblNewLabel.setForeground(Color.PINK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblNewLabel.setBounds(692, 49, 242, 36);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Please enter the details");
        lblNewLabel_1.setForeground(new Color(224, 255, 255));
        lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
        lblNewLabel_1.setBounds(638, 84, 296, 46);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Bride Name");
        lblNewLabel_2.setForeground(new Color(255, 192, 203));
        lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblNewLabel_2.setBounds(648, 148, 137, 28);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_2_1 = new JLabel("Groom Name");
        lblNewLabel_2_1.setForeground(new Color(255, 192, 203));
        lblNewLabel_2_1.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblNewLabel_2_1.setBounds(629, 199, 149, 28);
        contentPane.add(lblNewLabel_2_1);

        JLabel lblNewLabel_2_2 = new JLabel("Venue");
        lblNewLabel_2_2.setForeground(new Color(255, 192, 203));
        lblNewLabel_2_2.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblNewLabel_2_2.setBounds(657, 262, 109, 23);
        contentPane.add(lblNewLabel_2_2);

        JLabel lblNewLabel_2_2_1 = new JLabel("Theme");
        lblNewLabel_2_2_1.setForeground(new Color(255, 192, 203));
        lblNewLabel_2_2_1.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblNewLabel_2_2_1.setBounds(656, 330, 95, 23);
        contentPane.add(lblNewLabel_2_2_1);

        JLabel lblNewLabel_2_2_2 = new JLabel("Number of Guests");
        lblNewLabel_2_2_2.setForeground(new Color(255, 192, 203));
        lblNewLabel_2_2_2.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblNewLabel_2_2_2.setBounds(580, 390, 202, 23);
        contentPane.add(lblNewLabel_2_2_2);

        JLabel lblNewLabel_2_1_1 = new JLabel("Wedding Date");
        lblNewLabel_2_1_1.setForeground(new Color(255, 192, 203));
        lblNewLabel_2_1_1.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblNewLabel_2_1_1.setBounds(604, 457, 178, 23);
        contentPane.add(lblNewLabel_2_1_1);

        JLabel lblNewLabel_2_1_2 = new JLabel("Budget"); // New Label for Budget
        lblNewLabel_2_1_2.setForeground(new Color(255, 192, 203));
        lblNewLabel_2_1_2.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblNewLabel_2_1_2.setBounds(660, 515, 122, 23);
        contentPane.add(lblNewLabel_2_1_2);

        textField = new JTextField();
        textField.setBounds(804, 140, 202, 36);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(804, 199, 202, 36);
        contentPane.add(textField_1);

        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
        comboBox.setBounds(804, 260, 202, 36);
        contentPane.add(comboBox);
        populateComboBox();
        
        String[] themes = { "Floral", "Pastel" };
        comboBox_1 = new JComboBox<>(themes);
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Rajastani Royal", "BollyWood Glam",
        		"Floral Extravenza", "Beach Bliss", "South Indian", "Rustic Chic" }));
        comboBox_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
        comboBox_1.setBounds(804, 328, 202, 36);
        contentPane.add(comboBox_1);

        textField_2 = new JTextField();
        textField_2.setBounds(804, 389, 202, 36);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(804, 456, 202, 36);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        comboBoxBudget = new JComboBox<>();
        comboBoxBudget.setModel(new DefaultComboBoxModel(new String[] { "5-15 Lakhs", "15-25 Lakhs", "25-35 Lakhs", "35-45 Lakhs", "45-50 Lakhs" }));
        comboBoxBudget.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
        comboBoxBudget.setBounds(804, 513, 202, 36);
        contentPane.add(comboBoxBudget);
        
        
        JButton btnNewButton = new JButton("Check Planner Availability");
        btnNewButton.setForeground(new Color(255, 69, 0));
     // Action listener for the "Register" button
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String venue = (String) comboBox.getSelectedItem();
                String eventDate = textField_3.getText();

                if (isPlannerAvailable(venue, eventDate)) {
                    // Planner is available
                    Object[] options = { "Register"};
                    int choice = JOptionPane.showOptionDialog(contentPane, "Planner is available!", "Availability",
                            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Register button clicked
                        String brideName = textField.getText();
                        String groomName = textField_1.getText();
                        String theme = (String) comboBox_1.getSelectedItem();
                        int numGuests = Integer.parseInt(textField_2.getText());
                        String budget = (String) comboBoxBudget.getSelectedItem();

                        if (addDataToDatabase(brideName, groomName, venue, theme, numGuests, eventDate, budget)) {
                            // Show a dialog box with only one option
                            int option = JOptionPane.showOptionDialog(contentPane, "Registered Successfully!", "Success",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                                    new Object[] { "Select Your Guests" }, "Select Your Guests");

                            // Handle the user's choice
                            if (option == 0) { // Check if the "Select Your Guests" option is selected
                                // After registering the customer, get the cid
                                int cid = getCID(brideName, eventDate);

                                // Open a new frame for selecting guests
                                SelectGuests guestsFrame = new SelectGuests(cid);
                                guestsFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                                guestsFrame.setVisible(true);
                                
                                dispose();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(contentPane, "Failed to register.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    // Planner is not available
                    JOptionPane.showMessageDialog(contentPane, "Planner is not available on that date.", "Availability",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });




        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBounds(670, 557, 312, 46);
        contentPane.add(btnNewButton);
        

        JLabel lblNewLabel_3 = new JLabel("New label");
        lblNewLabel_3.setIcon(new ImageIcon(User.class.getResource("/images/userimg.jpeg")));
        lblNewLabel_3.setBounds(517, 6, 570, 859);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("New label");
        lblNewLabel_4.setIcon(new ImageIcon(
                User.class.getResource("/images/Photo By White Reflections - Decorators.jpeg")));
        lblNewLabel_4.setBounds(10, 6, 509, 820);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("New label");
        lblNewLabel_5.setIcon(new ImageIcon(
                User.class.getResource("/images/Photo By White Reflections - Decorators.jpeg")));
        lblNewLabel_5.setBounds(1079, -7, 607, 826);
        contentPane.add(lblNewLabel_5);
    }
    
    private boolean isPlannerAvailable(String venue, String eventDate) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean available = false;

        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM plans WHERE pid = (SELECT pid FROM planner WHERE vname = ?) AND event_date = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, venue);
            stmt.setString(2, eventDate);
            rs = stmt.executeQuery();
            
            // If there are no rows in the result set, it means planner is available
            available = !rs.next();
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

        return available;
    }

    
    private String getAssignedPlanner(String venue) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String plannerName = "";
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "SELECT Pname FROM planner WHERE vname = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, venue);
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
            String sql = "SELECT Vname FROM venue";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String lastName = rs.getString("Vname");
                comboBox.addItem(lastName);
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

    private boolean addDataToDatabase(String bride_name, String groom_name, String venue, String theme,
            int num_guests, String event_date, String budget) { // Add budget parameter
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sqlCustomer = "INSERT INTO customer(bride_name,groom_name,venue,theme,num_guests,event_date, budget) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sqlCustomer);
            stmt.setString(1, bride_name);
            stmt.setString(2, groom_name);
            stmt.setString(3, venue);
            stmt.setString(4, theme);
            stmt.setInt(5, num_guests);
            stmt.setString(6, event_date);
            stmt.setString(7, budget); // Set budget
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
                String sqlPlans = "INSERT INTO plans(cid, pid, event_date) VALUES ((SELECT cid FROM customer WHERE venue = ? AND event_date = ?), (SELECT pid FROM planner WHERE vname = ?), ?)";
                stmt = conn.prepareStatement(sqlPlans);
                stmt.setString(1, venue);
                stmt.setString(2, event_date);
                stmt.setString(3, venue);
                stmt.setString(4, event_date);
                rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Data inserted into plans table successfully.");
                } else {
                    System.out.println("Failed to insert data into plans table.");
                }
            } else {
                System.out.println("Failed to insert data into customer table.");
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
        return success;
    }
    private int getCID(String brideName, String eventDate) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int cid = -1; // Initialize to a default value

        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "SELECT cid FROM customer WHERE bride_name = ? AND event_date = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, brideName);
            stmt.setString(2, eventDate);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cid = rs.getInt("cid");
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

        return cid;
    }

}