
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
//import javax.swing.DefaultComboBoxModel;


public class Admin extends JFrame {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbms";

	private static final String USERNAME = "root";

	private static final String PASSWORD = "root";

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Admin() {
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1127, 679);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		Button button = new Button("View Planners");
		button.setBounds(0, 176, 230, 43);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchPlanners();
			}
		});
		contentPane.setLayout(null);
		button.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(button);
		
		Button button_1 = new Button("View Weddings");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a new frame for displaying the wedding table
		        JFrame weddingFrame = new JFrame("Wedding Table");
		        weddingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        weddingFrame.setBounds(100, 100, 800, 400);

		        // Create the table model
		        DefaultTableModel weddingTableModel = new DefaultTableModel();
		        weddingTableModel.addColumn("Customer ID");
		        weddingTableModel.addColumn("Planner ID");
		        weddingTableModel.addColumn("Planner Name");
		        weddingTableModel.addColumn("Wedding Date");
		        weddingTableModel.addColumn("Venue");
		        weddingTableModel.addColumn("Event 1");
		        weddingTableModel.addColumn("Event 2");
		        weddingTableModel.addColumn("Decoration");
		        weddingTableModel.addColumn("Catering Type");
		        
		        // Add other column headers as needed...

		        // Create the table
		        JTable weddingTable = new JTable(weddingTableModel);
		        JScrollPane scrollPane = new JScrollPane(weddingTable);
		        weddingFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		        
		     // Adjust the frame location to be centered on the screen
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				weddingFrame.setLocation(dim.width / 2 - weddingFrame.getSize().width / 2, dim.height / 2 - weddingFrame.getSize().height / 2);
		        
		        
		        // Populate the table with data from the database
		        try {
		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");
		            Statement statement = connection.createStatement();
		            ResultSet resultSet = statement.executeQuery("SELECT * FROM wedding");

		            while (resultSet.next()) {
		                Object[] rowData = {
		                        resultSet.getString("cid"),
		                        resultSet.getString("pid"),
		                        resultSet.getString("pname"),
		                        resultSet.getString("event_date"),
		                        resultSet.getString("venue"),
		                        resultSet.getString("e1name"),
		                        resultSet.getString("e2name"),
		                        resultSet.getString("dname"),
		                        resultSet.getString("ctname"),
		                        // Add other columns' data as needed...
		                };
		                weddingTableModel.addRow(rowData);
		            }

		            resultSet.close();
		            statement.close();
		            connection.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }

		        // Make the frame visible
		        weddingFrame.setVisible(true);
			}
		});
		button_1.setBounds(0, 248, 230, 43);
		button_1.setFont(new Font("Dubai Light", Font.BOLD, 16));
		contentPane.add(button_1);
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(236, 397, 150, 43);
		contentPane.add(lblNewLabel_2);
		
		
		Button button_2 = new Button("Most Booked Venue");
		button_2.setBounds(0, 397, 230, 43);	
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Connect to the database and retrieve the most frequent venue
                String mostFrequentVenue = "";
                int maxCount = 0;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT venue, COUNT(*) AS count FROM wedding GROUP BY venue ORDER BY count DESC LIMIT 1");

                    if (resultSet.next()) {
                        mostFrequentVenue = resultSet.getString("venue");
                        maxCount = resultSet.getInt("count");
                    }

                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // Display the most frequent venue in lblNewLabel_2
                lblNewLabel_2.setText(mostFrequentVenue);
				 
			}
		});
		button_2.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(button_2);
		
		Button button_3 = new Button("View Event Calender");
		button_3.setBounds(0, 319, 230, 48);							
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 displayEventCalendar();
				
			}
		});
		button_3.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.insets = new Insets(0, 0, 5, 5);
		gbc_button_3.gridx = 0;
		gbc_button_3.gridy = 3;
		gbc_button_3.gridwidth = 2;
		gbc_button_3.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(button_3, gbc_button_3);
		contentPane.add(button_3);
		
		
		Button button_4 = new Button("Add Planner");
		button_4.setBounds(0, 477, 230, 37);							
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddPlanner add = new AddPlanner();
                add.setExtendedState(JFrame.MAXIMIZED_BOTH);
                add.setVisible(true);
                
				
			}
		});
		button_4.setFont(new Font("Dialog", Font.BOLD, 16));
        GridBagConstraints gbc_button_4 = new GridBagConstraints();
        gbc_button_4.insets = new Insets(0, 0, 5, 5);
        gbc_button_4.gridx = 0;
        gbc_button_4.gridy = 3;
        gbc_button_4.gridwidth = 2;
        gbc_button_4.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(button_4, gbc_button_4);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(384, 43, 1149, 704);
		lblNewLabel.setIcon(new ImageIcon(Admin.class.getResource("/images/img3.jpg")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Admin.class.getResource("/images/Preview the Fall 2018 Issue of Inside Weddings!.jpeg")));
		lblNewLabel_1.setBounds(-46, 43, 432, 688);
		contentPane.add(lblNewLabel_1);
		
//		JButton button_4 = new JButton("New button");
//		button_4.setBounds(10, 483, 85, 21);
//		contentPane.add(button_4);
		
		
		
		
	}
	private void fetchPlanners() {
	    try {
	        Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM planner");

	        // Create a new JFrame to display the table
	        JFrame frame = new JFrame("Planner");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setSize(800, 400); // Increased width to accommodate table

	        // Create a DefaultTableModel to hold the data
	        DefaultTableModel tableModel = new DefaultTableModel();

	        // Get metadata for column names
	        ResultSetMetaData metaData = resultSet.getMetaData();
	        int columnCount = metaData.getColumnCount();
	        String[] columnNames = new String[columnCount];
	        for (int i = 1; i <= columnCount; i++) {
	            columnNames[i - 1] = metaData.getColumnName(i);
	        }
	        tableModel.setColumnIdentifiers(columnNames);

	        // Add rows to the table model
	        while (resultSet.next()) {
	            Object[] rowData = new Object[columnCount];
	            for (int i = 1; i <= columnCount; i++) {
	                rowData[i - 1] = resultSet.getObject(i);
	            }
	            tableModel.addRow(rowData);
	        }

	        // Create a JTable with the tableModel
	        JTable table = new JTable(tableModel);

	        // Add the JTable to a JScrollPane
	        JScrollPane scrollPane = new JScrollPane(table);

	        // Add the scroll pane to the frame at the right and center
	        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

	        // Adjust the frame location to be centered on the screen
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

	        frame.setVisible(true);

	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	private void displayEventCalendar() {
	    try {
	        Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(
	                "SELECT c.event_date, c.cid, c.venue, c.theme, p.pid, p.pname " +
	                "FROM customer c " +
	                "INNER JOIN planner p ON c.venue = p.vname"
	            );

	        // Create a new JFrame to display the table
	        JFrame frame = new JFrame("Event Calendar");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setSize(800, 400); // Increased width to accommodate table

	        // Create a DefaultTableModel to hold the data
	        DefaultTableModel tableModel = new DefaultTableModel();

	        // Get metadata for column names
	        ResultSetMetaData metaData = resultSet.getMetaData();
	        int columnCount = metaData.getColumnCount();
	        String[] columnNames = new String[columnCount];
	        for (int i = 1; i <= columnCount; i++) {
	            columnNames[i - 1] = metaData.getColumnName(i);
	        }
	        tableModel.setColumnIdentifiers(columnNames);

	        // Add rows to the table model
	        while (resultSet.next()) {
	            Object[] rowData = new Object[columnCount];
	            for (int i = 1; i <= columnCount; i++) {
	                rowData[i - 1] = resultSet.getObject(i);
	            }
	            tableModel.addRow(rowData);
	        }

	        // Create a JTable with the tableModel
	        JTable table = new JTable(tableModel);

	        // Add the JTable to a JScrollPane
	        JScrollPane scrollPane = new JScrollPane(table);

	        // Add the scroll pane to the frame at the right and center
	        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

	        // Adjust the frame location to be centered on the screen
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

	        frame.setVisible(true);

	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

}