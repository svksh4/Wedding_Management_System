
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
//import java.awt.Color;
import javax.swing.JButton;
//import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;



public class UserLoginFrame extends JFrame {

    private JPanel contentPane;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLoginFrame frame = new UserLoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UserLoginFrame() {
        setTitle("User Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 444, 300);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("New Registration");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setExtendedState(JFrame.MAXIMIZED_BOTH);
                user.setVisible(true);
                
                dispose();
            }
        });
        btnNewButton.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        btnNewButton.setBounds(144, 44, 170, 48);
        contentPane.add(btnNewButton);

        JButton btnAlreadyAnUser = new JButton("Already an user");
        btnAlreadyAnUser.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RegisteredUser user = new RegisteredUser();
        		user.setExtendedState(JFrame.MAXIMIZED_BOTH);;
        		user.setVisible(true);
        		
        		dispose();
        	}
        });
        btnAlreadyAnUser.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        btnAlreadyAnUser.setBounds(144, 125, 170, 42);
        contentPane.add(btnAlreadyAnUser);
        JLabel lblNewLabel_3 = new JLabel(" ");
		lblNewLabel_3.setIcon(new ImageIcon(AdminLoginFrame.class.getResource("/images/download (2).jpg")));
		lblNewLabel_3.setBounds(0, 0, 588, 339);
		contentPane.add(lblNewLabel_3);
        
    }}