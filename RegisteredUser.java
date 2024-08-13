import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisteredUser extends JFrame {

		private JPanel contentPane;
		private JTextField textField;
		private JTable table;
		private JTable plannerTable;
		
		public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
		try {
		RegisteredUser frame = new RegisteredUser();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		
		});
		}
		
		public RegisteredUser() {
			setTitle("Already Registered User");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 1289, 615);
			contentPane = new JPanel();
			contentPane.setForeground(new Color(0, 0, 0));
			contentPane.setBackground(new Color(216, 191, 216));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 228, 225));
			panel.setBounds(0, 134, 1547, 66);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Enter CID");
			lblNewLabel.setBackground(new Color(139, 0, 0));
			lblNewLabel.setForeground(new Color(139, 0, 0));
			lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 30));
			lblNewLabel.setBounds(621, 11, 197, 46);
			panel.add(lblNewLabel);
			
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
			textField.setBounds(806, 11, 135, 46);
			panel.add(textField);
			textField.setColumns(10);
			
			JButton button = new JButton("View Details");
			button.setForeground(new Color(255, 245, 238));
			button.setBackground(new Color(0, 139, 139));
			button.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
			button.setBounds(670, 247, 196, 54);
			contentPane.add(button);
			
			// Table to display data
			table = new JTable();
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(0, 326, 1547, 83);
			contentPane.add(scrollPane);
			
			// Action listener for the button
			button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String cidText = textField.getText();
			try {
			int cid = Integer.parseInt(cidText);
			fetchDataAndPopulateTable(cid);
			} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(contentPane, "Please enter a valid CID (integer).", "Invalid CID",
			JOptionPane.ERROR_MESSAGE);
			}
			}
			});
		}
		
		private void fetchDataAndPopulateTable(int cid) {
		try {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root","root");
		PreparedStatement preparedStatement = connection.prepareStatement(
		"SELECT c.cid, c.bride_name, c.groom_name, c.venue, c.theme, c.num_guests, c.event_date, p.pname FROM customer c INNER JOIN planner p ON c.venue = p.vname WHERE c.cid = ?");
		preparedStatement.setInt(1, cid);
		ResultSet resultSet = preparedStatement.executeQuery();
		
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
		boolean found = false;
		while (resultSet.next()) {
		found = true;
		Object[] rowData = new Object[columnCount];
		for (int i = 1; i <= columnCount; i++) {
		rowData[i - 1] = resultSet.getObject(i);
		}
		tableModel.addRow(rowData);
		}
		
		if (!found) {
		JOptionPane.showMessageDialog(contentPane, "No record found for CID: " + cid, "No Record Found",
		JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Set the table model to the JTable
		table.setModel(tableModel);
		
		resultSet.close();
		preparedStatement.close();
		
		// Fetch planner details
		PreparedStatement plannerStatement = connection
		.prepareStatement("SELECT * FROM planner WHERE vname = (SELECT venue FROM customer WHERE cid = ?)");
		plannerStatement.setInt(1, cid);
		ResultSet plannerResultSet = plannerStatement.executeQuery();
		
		// Create a DefaultTableModel to hold the planner data
		DefaultTableModel plannerTableModel = new DefaultTableModel();
		
		// Get metadata for column names
		ResultSetMetaData plannerMetaData = plannerResultSet.getMetaData();
		int plannerColumnCount = plannerMetaData.getColumnCount();
		String[] plannerColumnNames = new String[plannerColumnCount];
		for (int i = 1; i <= plannerColumnCount; i++) {
		plannerColumnNames[i - 1] = plannerMetaData.getColumnName(i);
		}
		plannerTableModel.setColumnIdentifiers(plannerColumnNames);
		
		// Add rows to the planner table model
		while (plannerResultSet.next()) {
		Object[] plannerRowData = new Object[plannerColumnCount];
		for (int i = 1; i <= plannerColumnCount; i++) {
		plannerRowData[i - 1] = plannerResultSet.getObject(i);
		}
		plannerTableModel.addRow(plannerRowData);
		}
		
		// Set the planner table model to the JTable
		plannerTable = new JTable(plannerTableModel);
		JScrollPane plannerScrollPane = new JScrollPane(plannerTable);
		plannerScrollPane.setBounds(0, 420, 1547, 83);
		contentPane.add(plannerScrollPane);
		
		plannerResultSet.close();
		plannerStatement.close();
		
		connection.close();
		
		} catch (SQLException ex) {
		ex.printStackTrace();
		}
}
}
