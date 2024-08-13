
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdminLoginFrame extends JFrame {

	private JPanel contentPane;

	private JTextField textField;

	private JTextField textField_2;

	private JPasswordField passwordField;

	/**
	 * 
	 * Launch the application.
	 * 
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					AdminLoginFrame frame = new AdminLoginFrame();

					frame.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}

	/**
	 * 
	 * Create the frame.
	 * 
	 */

	public AdminLoginFrame() {

		setTitle("Admin Login");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 612, 376);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Admin ID");
		lblNewLabel.setBackground(new Color(255, 192, 203));
		lblNewLabel.setForeground(Color.BLACK);

		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 20));

		lblNewLabel.setBounds(141, 40, 99, 38);

		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBackground(new Color(255, 192, 203));
		lblNewLabel_1.setForeground(Color.BLACK);

		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 20));

		lblNewLabel_1.setBounds(136, 96, 118, 38);

		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("E-mail");
		lblNewLabel_2.setBackground(new Color(255, 192, 203));
		lblNewLabel_2.setForeground(Color.BLACK);

		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.BOLD, 22));

		lblNewLabel_2.setBounds(141, 168, 81, 25);

		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(255, 192, 203));
		btnNewButton.setForeground(new Color(0, 0, 0));

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Get the entered credentials

				String adminID = textField.getText();

				String password = String.valueOf(passwordField.getPassword());

				String email = textField_2.getText();

				// Perform validation

				if (isValidEmail(email) && isValidAdmin(adminID, password) && isValidPassword(password)) {

					// Open the admin page if all credentials are valid

					Admin admin = new Admin();

					admin.setExtendedState(JFrame.MAXIMIZED_BOTH);

					admin.setVisible(true);
					
					dispose();

				} else {

					// Show a dialog box for invalid credentials

					JOptionPane.showMessageDialog(contentPane, "Invalid credentials. Please try again.",
							"Invalid Credentials", JOptionPane.ERROR_MESSAGE);

					// Clear text fields

					textField.setText("");

					passwordField.setText("");

					textField_2.setText("");

				}

			}

		});

		btnNewButton.setFont(new Font("Script MT Bold", Font.PLAIN, 22));

		btnNewButton.setBounds(304, 221, 92, 38);

		contentPane.add(btnNewButton);

		textField = new JTextField();

		textField.setBounds(278, 40, 118, 32);

		contentPane.add(textField);

		textField.setColumns(10);

		textField_2 = new JTextField();

		textField_2.setColumns(10);

		textField_2.setBounds(278, 161, 118, 32);

		contentPane.add(textField_2);

		passwordField = new JPasswordField();

		passwordField.setBounds(278, 102, 118, 32);

		contentPane.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel(" ");
		lblNewLabel_3.setIcon(new ImageIcon(AdminLoginFrame.class.getResource("/images/download (2).jpg")));
		lblNewLabel_3.setBounds(69, 0, 588, 339);
		contentPane.add(lblNewLabel_3);

	}

	// Method for validating email using regex

	private boolean isValidEmail(String email) {
	    // Regex pattern for email validation
	    String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z]+\\.(com|in)$";
	    return email.matches(regex);
	}


	// Method for validating admin ID and password

	private boolean isValidAdmin(String adminID, String password) {

		// Replace this with your actual validation logic

		return adminID.equals("admin@123") && password.equals("pass@123");

	}

	// Method for validating password

	private boolean isValidPassword(String password) {

		// Password should have at least 6 characters

		return password.length() >= 6;

	}
}