import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class WeddingPage extends JFrame {

private static final long serialVersionUID = 1L;
private JPanel contentPane;
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbms";

private static final String USERNAME = "root";

private static final String PASSWORD = "root";
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
	WeddingPage frame = new WeddingPage();
frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}

public WeddingPage() {
setTitle("Wedding Page");
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
setBounds(100, 100, 1361, 759);
contentPane = new JPanel();
contentPane.setBackground(new Color(245, 245, 220));
contentPane.setBorder(new EmptyBorder(0,0,0,0));

setContentPane(contentPane);
contentPane.setLayout(null);

JLabel lblNewLabell = new JLabel("WELCOME TO OUR WEDDING PAGE!");
        lblNewLabell.setForeground(new Color(139, 69, 19));
        lblNewLabell.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
   lblNewLabell.setBounds(419, 41, 572, 34);
   contentPane.add(lblNewLabell);
   
   JLabel lblNewLabel_2 = new JLabel("With years of experience and a passion for creating unforgettable moments,");
   lblNewLabel_2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
   lblNewLabel_2.setBounds(364, 159, 891, 48);
   contentPane.add(lblNewLabel_2);
   
   JLabel lblNewLabel_3 = new JLabel("our dedicated team is here to turn your dreams into reality.");
   lblNewLabel_3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
   lblNewLabel_3.setBounds(432, 217, 692, 34);
   contentPane.add(lblNewLabel_3);
   
   JLabel lblNewLabel_4 = new JLabel("We specialize in crafting bespoke weddings that reflect your unique style and personality.");
   lblNewLabel_4.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
   lblNewLabel_4.setBounds(280, 263, 1044, 34);
   contentPane.add(lblNewLabel_4);
   
   JLabel lblNewLabel_5 = new JLabel("We have the expertise and creativity to bring your vision to life.");
   lblNewLabel_5.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
   lblNewLabel_5.setBounds(463, 328, 725, 34);
   contentPane.add(lblNewLabel_5);
   
   JLabel lblNewLabel_6 = new JLabel("Let's handle every detail with precision and care, from venue selection to day-of management,");
   lblNewLabel_6.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
   lblNewLabel_6.setBounds(264, 384, 1073, 44);
   contentPane.add(lblNewLabel_6);
   
   JLabel lblNewLabel_7 = new JLabel("so you can relax and enjoy every moment of your special day.");
   lblNewLabel_7.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
   lblNewLabel_7.setBounds(416, 438, 839, 49);
   contentPane.add(lblNewLabel_7);
   
   JLabel lblNewLabel_8 = new JLabel("Contact us today to schedule and begin the journey to your happily ever after!");
   lblNewLabel_8.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
   lblNewLabel_8.setBounds(346, 496, 991, 39);
   contentPane.add(lblNewLabel_8);
   
   JButton btnNewButton = new JButton("Login as Admin");
   btnNewButton.setBackground(new Color(255, 204, 204));
   btnNewButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    // Open a new frame or dialog for admin login
                AdminLoginFrame admin=new AdminLoginFrame();
                admin.setVisible(true);
    }
   });
   btnNewButton.setFont(new Font("Segoe Print", Font.BOLD, 19));
   btnNewButton.setBounds(715, 553, 208, 48);
   contentPane.add(btnNewButton);
   
   JButton btnNewButton_1 = new JButton("Login as User");
   btnNewButton_1.setBackground(new Color(255, 204, 204));
   btnNewButton_1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    UserLoginFrame user=new UserLoginFrame();
    //
   // user.setExtendedState(JFrame.MAXIMIZED_BOTH);

                user.setVisible(true);
    }
   });
   btnNewButton_1.setFont(new Font("Segoe Print", Font.BOLD, 19));
   btnNewButton_1.setBounds(715, 611, 208, 44);
   contentPane.add(btnNewButton_1);
   
   JLabel lblNewLabel_1 = new JLabel("We believe that Your Wedding Day is one of the most important moments of your life.");
   contentPane.add(lblNewLabel_1);
   lblNewLabel_1.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
   lblNewLabel_1.setBounds(283, 100, 1017, 49);

JLabel lblNewLabel__1 = new JLabel("");
lblNewLabel__1.setBounds(-40, 0, 549, 811);
contentPane.add(lblNewLabel__1);
lblNewLabel__1.setIcon(new ImageIcon(WeddingPage.class.getResource("/images/p1.jpg")));

JButton btnNewButton_2 = new JButton("Login as Planner");
btnNewButton_2.setBackground(new Color(255, 204, 204));
btnNewButton_2.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
	PlanFrame planner = new PlanFrame();
	planner.setVisible(true);
}
});
btnNewButton_2.setFont(new Font("Segoe Print", Font.BOLD, 19));
btnNewButton_2.setBounds(715, 670, 208, 42);
contentPane.add(btnNewButton_2);

JButton btnNewButton_3 = new JButton("FAQs");
btnNewButton_3.setBackground(new Color(255, 192, 203));
btnNewButton_3.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
	FAQs faq = new FAQs();
	faq.setExtendedState(JFrame.MAXIMIZED_BOTH);
	faq.setVisible(true);
}
});
btnNewButton_3.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 22));
btnNewButton_3.setBounds(1174, 611, 126, 44);
contentPane.add(btnNewButton_3);

JLabel lblNewLabel_9 = new JLabel("New label");
lblNewLabel_9.setIcon(new ImageIcon(WeddingPage.class.getResource("/images/p1.jpg")));
lblNewLabel_9.setBounds(1174, 0, 412, 811);
contentPane.add(lblNewLabel_9);

JLabel lblNewLabel = new JLabel("New label");
lblNewLabel.setIcon(new ImageIcon(WeddingPage.class.getResource("/images/download.jpeg")));
lblNewLabel.setBounds(463, -8, 731, 811);
contentPane.add(lblNewLabel);
}
}
