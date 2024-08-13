
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.sql.*;

public class Plan extends JFrame {

	private JPanel contentPane;

	private JTable table;

	private JTextField cidTextField;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					Plan frame = new Plan("");

					frame.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}

	public Plan(String pid) {

		setTitle("Assigned Weddings");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));

		// Create input panel

		JPanel inputPanel = new JPanel();

		contentPane.add(inputPanel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Enter CID:");

		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		inputPanel.add(lblNewLabel);

		cidTextField = new JTextField();

		inputPanel.add(cidTextField);

		cidTextField.setColumns(10);

		JButton planButton = new JButton("Plan Wedding");

		inputPanel.add(planButton);

		// Action listener for the planButton

		planButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Get the selected CID

				String selectedCID = cidTextField.getText();

				// If no CID is provided, do nothing

				if (selectedCID.isEmpty()) {

					return;

				}

				// Now you can perform your logic here to plan the wedding with the selected CID

				// For example, you can show a confirmation message or update the database.

				Planner p = new Planner(pid, selectedCID);

				p.setExtendedState(JFrame.MAXIMIZED_BOTH);

				p.setVisible(true);
				
				dispose();
				
				System.out.println("Wedding is being planned for CID: " + selectedCID);

			}

		});

		// Create a table model to hold the data

		DefaultTableModel tableModel = new DefaultTableModel();

		// Connect to the database and retrieve data

		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT cid, event_date FROM plans WHERE pid = ?");

			preparedStatement.setString(1, pid);

			ResultSet resultSet = preparedStatement.executeQuery();

			// Get metadata for column names

			ResultSetMetaData metaData = resultSet.getMetaData();

			int columnCount = metaData.getColumnCount();

			String[] columnNames = new String[columnCount];

			for (int i = 1; i <= columnCount; i++) {

				columnNames[i - 1] = metaData.getColumnName(i);

			}

			// Add column names to table model

			tableModel.setColumnIdentifiers(columnNames);

			// Add rows to table model

			while (resultSet.next()) {

				Object[] rowData = new Object[columnCount];

				for (int i = 1; i <= columnCount; i++) {

					rowData[i - 1] = resultSet.getObject(i);

				}

				tableModel.addRow(rowData);

			}

			resultSet.close();

			preparedStatement.close();

			connection.close();

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		// Create the JTable and add it to a JScrollPane

		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);

		contentPane.add(scrollPane, BorderLayout.CENTER);

	}

}