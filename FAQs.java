import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextArea;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class FAQs extends JFrame {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbms";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection conn;

    private JPanel contentPane;
    private JLabel lblMostBookedVenue;
    private JLabel lblMostBookedDate;
    private JLabel lblCateringInfo;
    private JLabel lblMostAssignedPlanner;
    private JLabel lblMostPlannedEvent;
    private JLabel topCateringLabel;
    private JLabel lblDecorationStyle;
    private JLabel lblNewLabel;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FAQs frame = new FAQs();
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
    public FAQs() {
        setTitle("Frequently Asked Questions");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1291, 702);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(95, 158, 160));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Establish JDBC connection
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Buttons and action listeners
        JButton btnNewButton = new JButton("Most Booked Venue");
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            String mostFrequentVenue = "";
       int maxCount = 0;
       try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "root");
                    Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT venue, COUNT(*) AS count FROM customer GROUP BY venue ORDER BY count DESC LIMIT 1");

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
                lblMostBookedVenue .setText(mostFrequentVenue);
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBounds(87, 242, 261, 50);
        contentPane.add(btnNewButton);


        JButton btnHighlyAssignedPlanner = new JButton("Most Assigned Planner");
        btnHighlyAssignedPlanner.setBackground(Color.WHITE);
        btnHighlyAssignedPlanner.setFont(new Font("Tahoma", Font.BOLD, 19));
        btnHighlyAssignedPlanner.setBounds(87, 379, 261, 48);
        contentPane.add(btnHighlyAssignedPlanner);
        btnHighlyAssignedPlanner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            try {
                    PreparedStatement pstmt = conn.prepareStatement("SELECT pname, COUNT(*) AS assignment_count FROM wedding GROUP BY pname ORDER BY assignment_count DESC LIMIT 1");
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        String plannerName = rs.getString("pname");
                        int assignmentCount = rs.getInt("assignment_count");
                        String resultText =  plannerName  ;
                        lblMostAssignedPlanner.setText(resultText);
                    }
                    rs.close();
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton btnMostPlannedEvent = new JButton("Most Planned Event");
        btnMostPlannedEvent.setBackground(Color.WHITE);
        btnMostPlannedEvent.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnMostPlannedEvent.setBounds(87, 512, 261, 48);
        contentPane.add(btnMostPlannedEvent);
        btnMostPlannedEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            try {
                    PreparedStatement pstmt = conn.prepareStatement("SELECT e1name, COUNT(*) AS planning_count FROM wedding GROUP BY e1name ORDER BY planning_count DESC LIMIT 1");
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        String eventName = rs.getString("e1name");
                        int planningCount = rs.getInt("planning_count");
                        // Set the text of the JLabel with the most planned event
                        lblMostPlannedEvent.setText( eventName);
                    }
                    rs.close();
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


		JButton btnMostPlannedEvent_2 = new JButton("Most Wanted Decoration Styles");
		btnMostPlannedEvent_2.setBackground(Color.WHITE);
		btnMostPlannedEvent_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnMostPlannedEvent_2.setBounds(701, 242, 333, 50);
		contentPane.add(btnMostPlannedEvent_2);
		btnMostPlannedEvent_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            PreparedStatement pstmt = conn.prepareStatement("SELECT dname, COUNT(*) AS demand_count FROM wedding GROUP BY dname ORDER BY demand_count DESC LIMIT 1");
		            ResultSet rs = pstmt.executeQuery();
		            if (rs.next()) {
		                String decorationStyle = rs.getString("dname");
		                int demandCount = rs.getInt("demand_count");
		                // Set the text of the JLabel with the fetched decoration style
		                lblDecorationStyle.setText( decorationStyle );
		            }
		            rs.close();
		            pstmt.close();
		        }  catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		
		JButton btnMostPlannedEvent_3 = new JButton("Top Catering");
		btnMostPlannedEvent_3.setBackground(Color.WHITE);
		btnMostPlannedEvent_3.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnMostPlannedEvent_3.setBounds(706, 378, 328, 50);
		contentPane.add(btnMostPlannedEvent_3);
		btnMostPlannedEvent_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            PreparedStatement pstmt = conn.prepareStatement("SELECT ctname, COUNT(*) AS demand_count FROM wedding GROUP BY ctname ORDER BY demand_count DESC LIMIT 1");
		            ResultSet rs = pstmt.executeQuery();
		            if (rs.next()) {
		                String cateringName = rs.getString("ctname");
		                int demandCount = rs.getInt("demand_count");
		                lblCateringInfo .setText(cateringName);
		             
		            }
		            rs.close();
		            pstmt.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		       
		       
		    }
		});
		
		JButton btnMostPlannedEvent_4 = new JButton("Most Wanted Theme");
		btnMostPlannedEvent_4.setBackground(Color.WHITE);
		btnMostPlannedEvent_4.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnMostPlannedEvent_4.setBounds(706, 511, 328, 48);
		contentPane.add(btnMostPlannedEvent_4);
		
		//JLabel to display most booked venue
		lblMostBookedVenue = new JLabel("");
		lblMostBookedVenue.setForeground(Color.WHITE);
		lblMostBookedVenue.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		lblMostBookedVenue.setBounds(407, 242, 250, 50);
		contentPane.add(lblMostBookedVenue);
		
		lblMostBookedDate = new JLabel(); // Initialize the JLabel
		lblMostBookedDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMostBookedDate.setBounds(1027, 57, 221, 41);
		contentPane.add(lblMostBookedDate);
		
		//JLabel to display catering information
		lblCateringInfo = new JLabel();
		lblCateringInfo.setForeground(Color.WHITE);
		lblCateringInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		lblCateringInfo.setBounds(1088, 379, 237, 50);
		contentPane.add(lblCateringInfo);
		
		//Inside the constructor
		lblMostAssignedPlanner = new JLabel();
		lblMostAssignedPlanner.setForeground(Color.WHITE);
		lblMostAssignedPlanner.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		lblMostAssignedPlanner.setBounds(396, 380, 261, 48);
		contentPane.add(lblMostAssignedPlanner);
		
		lblMostPlannedEvent = new JLabel("");
		lblMostPlannedEvent.setForeground(Color.WHITE);
		lblMostPlannedEvent.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		lblMostPlannedEvent.setBounds(389, 512, 237, 48);
		contentPane.add(lblMostPlannedEvent);
		
		topCateringLabel = new JLabel();
		topCateringLabel.setForeground(Color.WHITE);
		topCateringLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		topCateringLabel.setBounds(1103, 512, 274, 48);
		contentPane.add(topCateringLabel);
		
		lblDecorationStyle = new JLabel("");
		lblDecorationStyle.setForeground(Color.WHITE);
		lblDecorationStyle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
		lblDecorationStyle.setBounds(1085, 242, 240, 50);
		contentPane.add(lblDecorationStyle);
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(FAQs.class.getResource("/images/faq.jpg")));
		lblNewLabel.setBounds(0, 0, 1536, 835);
		contentPane.add(lblNewLabel);
		
	
		
		
		btnMostPlannedEvent_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            PreparedStatement pstmt = conn.prepareStatement("SELECT theme, COUNT(*) AS demand_count FROM customer GROUP BY theme ORDER BY demand_count DESC LIMIT 1");
		            ResultSet rs = pstmt.executeQuery();
		            if (rs.next()) {
		                String mostWantedTheme = rs.getString("theme");
		                int demandCount = rs.getInt("demand_count");
		                // Set the text of the JLabel with the fetched theme
		                topCateringLabel.setText(mostWantedTheme);
		            }
		            rs.close();
		            pstmt.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		}
}