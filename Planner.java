
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import java.awt.Font;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import javax.swing.JTextField;

import javax.swing.JComboBox;

import javax.swing.JButton;

import java.awt.Color;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Planner extends JFrame {

	private JPanel contentPane;

	private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_8;

	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					Planner frame = new Planner("511","125");

					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

					frame.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}


	public Planner(String pid, String cid) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Planner.class.getResource("/images/Wood backdrop for photography.jpg")));

		setTitle("Planning");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 1495, 844);

		contentPane = new JPanel();

		contentPane.setBackground(new Color(169, 169, 169));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(null);

		// Query the database to get the name associated with the PID

		String pname = "";

		String vname = "";

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT pname,vname FROM planner WHERE pid = ?");

			preparedStatement.setString(1, pid);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				pname = resultSet.getString("pname");

				vname = resultSet.getString("vname");

			}

			resultSet.close();

			preparedStatement.close();

			connection.close();

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		String bname = "";

		String gname = "";

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT bride_name,groom_name FROM customer WHERE cid = ?");

			preparedStatement.setString(1, cid);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				bname = resultSet.getString("bride_name");

				gname = resultSet.getString("groom_name");

			}

			resultSet.close();

			preparedStatement.close();

			connection.close();

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		JLabel lblNewLabel = new JLabel("Event 1");
		lblNewLabel.setForeground(new Color(255, 228, 181));

		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));

		lblNewLabel.setBounds(204, 364, 128, 29);

		contentPane.add(lblNewLabel);

		JLabel lblEvent = new JLabel("Decoration");
		lblEvent.setForeground(new Color(245, 222, 179));

		lblEvent.setFont(new Font("Tahoma", Font.BOLD, 32));

		lblEvent.setBounds(902, 443, 189, 46);

		contentPane.add(lblEvent);

		JLabel lblNewLabel_1 = new JLabel("is planning wedding of ");
		lblNewLabel_1.setForeground(new Color(72, 209, 204));

		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 32));

		lblNewLabel_1.setBounds(386, 199, 382, 40);

		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("and");
		lblNewLabel_2.setForeground(new Color(72, 209, 204));

		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 32));

		lblNewLabel_2.setBounds(952, 199, 74, 40);

		contentPane.add(lblNewLabel_2);

		JLabel lblDecoratoi = new JLabel("Catering");
		lblDecoratoi.setForeground(new Color(245, 222, 179));

		lblDecoratoi.setFont(new Font("Tahoma", Font.BOLD, 32));

		lblDecoratoi.setBounds(921, 351, 146, 42);

		contentPane.add(lblDecoratoi);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));

		comboBox.setBounds(401, 361, 160, 46);

		contentPane.add(comboBox);

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");

			PreparedStatement preparedStatement = connection.prepareStatement("SELECT e1name FROM event1");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String e1name = resultSet.getString("e1name");

				comboBox.addItem(e1name);

			}

			resultSet.close();

			preparedStatement.close();

			connection.close();

		} catch (SQLException ex) {

			ex.printStackTrace();

		}


		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 17));

		comboBox_1.setBounds(401, 449, 160, 46);

		contentPane.add(comboBox_1);

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");

			PreparedStatement preparedStatement = connection.prepareStatement("SELECT e2name FROM event2");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String e2name = resultSet.getString("e2name");

				comboBox_1.addItem(e2name);

			}

			resultSet.close();

			preparedStatement.close();

			connection.close();

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 17));

		comboBox_2.setBounds(1143, 451, 160, 42);

		contentPane.add(comboBox_2);

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");

			PreparedStatement preparedStatement = connection.prepareStatement("SELECT dname FROM decoration");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String dname = resultSet.getString("dname");

				comboBox_2.addItem(dname);

			}

			resultSet.close();

			preparedStatement.close();

			connection.close();

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		JLabel lblEvent_1 = new JLabel("Event 2");
		lblEvent_1.setForeground(new Color(255, 228, 181));

		lblEvent_1.setFont(new Font("Tahoma", Font.BOLD, 28));

		lblEvent_1.setBounds(204, 448, 126, 40);

		contentPane.add(lblEvent_1);

		JComboBox<String> comboBox_2_1 = new JComboBox<String>();
		comboBox_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));

		comboBox_2_1.setBounds(1143, 347, 160, 46);

		contentPane.add(comboBox_2_1);

		JButton btnNewButton = new JButton("Plan");
		btnNewButton.setBackground(new Color(255, 192, 203));
		btnNewButton.setForeground(new Color(139, 0, 0));
		
		btnNewButton.setFont(new Font("Myanmar Text", Font.BOLD, 39));

		btnNewButton.setBounds(673, 560, 209, 55);

		contentPane.add(btnNewButton);

		JLabel lblNewLabel_3 = new JLabel("Venue");
		lblNewLabel_3.setForeground(new Color(224, 255, 255));
		lblNewLabel_3.setFont(new Font("Viner Hand ITC", Font.BOLD, 50));
		lblNewLabel_3.setBounds(537, 273, 155, 34);

		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel(pname);
        lblNewLabel_4.setForeground(new Color(255, 182, 193));
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        lblNewLabel_4.setBounds(169, 193, 209, 46);
        contentPane.add(lblNewLabel_4);
//		
//		
//		
		
        
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 32));
		lblNewLabel_6.setForeground(new Color(255, 182, 193));
		lblNewLabel_6.setBounds(783, 199, 206, 40);
		lblNewLabel_6.setText(bname);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 32));
		lblNewLabel_7.setForeground(new Color(255, 182, 193));
		lblNewLabel_7.setBounds(1026, 199, 263, 46);
		lblNewLabel_7.setText(gname);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel(""); // Use vname here
		lblNewLabel_8.setForeground(new Color(152, 251, 152));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 32));
		lblNewLabel_8.setText(vname);
		lblNewLabel_8.setBounds(702, 261, 389, 46);
		//lblNewLabel_8.setText(vname);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_5 = new JLabel("Plan");
		lblNewLabel_5.setIcon(new ImageIcon(Planner.class.getResource("/images/Wood backdrop for photography.jpg")));
		lblNewLabel_5.setBounds(0, 0, 1526, 864);
		contentPane.add(lblNewLabel_5);
        
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String pname = lblNewLabel_4.getText();
		        String venue = lblNewLabel_8.getText(); // Get venue from textField_3
		        String e1name = comboBox.getSelectedItem().toString(); // Get e1name from comboBox
		        String e2name = comboBox_1.getSelectedItem().toString(); // Get e2name from comboBox_1
		        String dname = comboBox_2.getSelectedItem().toString(); // Get dname from comboBox_2
		        String ctname = comboBox_2_1.getSelectedItem().toString();

		        // Query the database to get the event_date associated with cid
		        String eventDate = "";

		        try {
		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root","root");
		            PreparedStatement preparedStatement = connection.prepareStatement("SELECT event_date FROM customer WHERE cid = ?");
		            preparedStatement.setString(1, cid);
		            ResultSet resultSet = preparedStatement.executeQuery();
		            if (resultSet.next()) {
		                eventDate = resultSet.getString("event_date");
		            }
		            resultSet.close();
		            preparedStatement.close();
		            connection.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }

		        // Insert data into wedding table
		        try {
		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root","root");
		            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO wedding VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)");
		            preparedStatement.setString(1, cid);
		            preparedStatement.setString(2, pid);
		            preparedStatement.setString(3, pname);
		            preparedStatement.setString(4, eventDate);
		            preparedStatement.setString(5, venue);
		            preparedStatement.setString(6, e1name);
		            preparedStatement.setString(7, e2name);
		            preparedStatement.setString(8, dname);
		            preparedStatement.setString(9, ctname);
		            int rowsInserted = preparedStatement.executeUpdate();
		            if (rowsInserted > 0) {
		                System.out.println("A new wedding plan was inserted successfully!");
		                int option = JOptionPane.showOptionDialog(contentPane, "You have successfully planned the wedding", "Success",
		                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
		                        new Object[] { "Manage Guests" }, "Manage Guests");
		                if (option == JOptionPane.YES_OPTION) {
		                    ManageGuests manage = new ManageGuests(pid,cid);
		                    manage.setExtendedState(JFrame.MAXIMIZED_BOTH);
		                    manage.setVisible(true);
		                    dispose(); // Close the current frame
		                }
		            }
		            preparedStatement.close();
		            connection.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});


		

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");

			PreparedStatement preparedStatement = connection.prepareStatement("SELECT ctname FROM catering");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String ctname = resultSet.getString("ctname");

				comboBox_2_1.addItem(ctname);

			}

			resultSet.close();

			preparedStatement.close();

			connection.close();

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

	}
}