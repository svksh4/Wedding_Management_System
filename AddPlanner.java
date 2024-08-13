import java.awt.EventQueue;

import java.awt.Font;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JTextField;

import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Color;



public class AddPlanner extends JFrame {



    private JPanel contentPane;

    private JTextField textField;

    private JTextField textField_1;

    private JTextField textField_2;



    /**

     * Launch the application.

     */

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {

                    AddPlanner frame = new AddPlanner();

                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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

    public AddPlanner() {

        setTitle("Add Planner");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setBounds(100, 100, 1317, 653);

        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));



        setContentPane(contentPane);

        contentPane.setLayout(null);



        JLabel lblNewLabel = new JLabel("Enter the details of New Planner");
        lblNewLabel.setForeground(new Color(255, 215, 0));

        lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 39));

        lblNewLabel.setBounds(493, 77, 810, 54);

        contentPane.add(lblNewLabel);



        JLabel lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setForeground(new Color(250, 250, 210));

        lblNewLabel_1.setFont(new Font("Script MT Bold", Font.PLAIN, 45));

        lblNewLabel_1.setBounds(702, 225, 113, 48);

        contentPane.add(lblNewLabel_1);



        JLabel lblNewLabel_1_1 = new JLabel("Phone No.");
        lblNewLabel_1_1.setForeground(new Color(250, 250, 210));

        lblNewLabel_1_1.setFont(new Font("Script MT Bold", Font.BOLD, 45));

        lblNewLabel_1_1.setBounds(650, 328, 203, 42);

        contentPane.add(lblNewLabel_1_1);



        JLabel lblNewLabel_1_2 = new JLabel("City");
        lblNewLabel_1_2.setForeground(new Color(250, 250, 210));

        lblNewLabel_1_2.setFont(new Font("Script MT Bold", Font.BOLD, 45));

        lblNewLabel_1_2.setBounds(720, 431, 113, 48);

        contentPane.add(lblNewLabel_1_2);



        textField = new JTextField();

        textField.setBounds(885, 225, 203, 48);

        contentPane.add(textField);

        textField.setColumns(10);



        textField_1 = new JTextField();

        textField_1.setColumns(10);

        textField_1.setBounds(885, 328, 203, 48);

        contentPane.add(textField_1);



        textField_2 = new JTextField();

        textField_2.setColumns(10);

        textField_2.setBounds(885, 431, 203, 48);

        contentPane.add(textField_2);



        JButton btnNewButton = new JButton("Add");
        btnNewButton.setBackground(new Color(175, 238, 238));

        btnNewButton.setFont(new Font("Monotype Corsiva", Font.BOLD, 51));

        btnNewButton.setBounds(792, 573, 161, 54);

        contentPane.add(btnNewButton);
       
        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_2.setIcon(new ImageIcon(AddPlanner.class.getResource("/images/plannerimage.jpg")));
        lblNewLabel_2.setBounds(-26, -94, 1555, 1010);
        contentPane.add(lblNewLabel_2);



        // Action listener for the "Add" button

        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // Retrieve data from text fields

                String pname = textField.getText();

                String phno = textField_1.getText();

                String city = textField_2.getText();



                // Insert data into the planner table

                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");

                    String query = "INSERT INTO planner (pname, phno, vname) VALUES (?, ?, ?)";

                    PreparedStatement preparedStatement = connection.prepareStatement(query);

                    preparedStatement.setString(1, pname);

                    preparedStatement.setString(2, phno);

                    preparedStatement.setString(3, city);

                    preparedStatement.executeUpdate();

                    preparedStatement.close();

                    connection.close();

                    System.out.println("Planner added successfully.");

                    // Show success message

                    JOptionPane.showMessageDialog(contentPane, "Planner added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Close current frame and go back to Admin page

                    dispose();
                

                } catch (SQLException ex) {

                    ex.printStackTrace();

                }

            }

        });

    }
}