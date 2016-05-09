import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.*;
import javax.swing.*;

public class LoginFrame {

	String userRole ;

	
	public JFrame frame;
	
	private JTextField txtUserName;
	private JPasswordField pwdPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection connection = null;
	
	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
		connection=sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 601, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBackground(new Color(255, 250, 205));
		txtUserName.setBounds(293, 142, 128, 20);
		frame.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBackground(new Color(255, 250, 205));
		pwdPassword.setBounds(293, 173, 128, 20);
		frame.getContentPane().add(pwdPassword);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		Image img = new ImageIcon(this.getClass().getResource("/Ok.png")).getImage();
		btnEnter.setIcon(new ImageIcon(img));
		btnEnter.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")							// dobaveno avtomatichno
			
			
			public void actionPerformed(ActionEvent arg0) {

				try {					

					
					// select *(All) from from StudentsDatabaseInfo(TableName) for Col User and Password
					String query = "select * from users where userName=? and password=?";   
					PreparedStatement pst = connection.prepareStatement(query);

					pst.setString(1, txtUserName.getText());
					pst.setString(2, pwdPassword.getText());
					
					ResultSet res = pst.executeQuery();
					
					int count = 0;
					
					while(res.next())
					{
						count = count + 1;		
						System.out.print(res.getString("password"));
						System.out.print(res.getString("role")); 
						userRole  = res.getString("role");
						// increase counter in each match 
						
					}
					
					if(count == 1){																	// Only one match correct pass and user

						if(userRole.equals("admin")){
							JOptionPane.showMessageDialog(null, "You are admin ");
							frame.dispose();
							StudentActivitiesAdmin  admin = new StudentActivitiesAdmin();
							admin.setVisible(true);
						}
						
						if(userRole.equals("user")){
							JOptionPane.showMessageDialog(null, "You are user ");
							frame.dispose();												//StudentActivitiesView
							StudentActivitiesView  student = new StudentActivitiesView();
							student.setVisible(true);
						}

						int count1 = 0;
						
						while(res.next())
						{
							count1 = count1 + 1;																	// increase counter in each match 
						
						}
											
					} 
					
					if(count == 0)																
					{  
						JOptionPane.showMessageDialog(null, "Incorrect Username or Password. ");
					}
					
					
					res.close();		// Close connection with data base
					pst.close();		// Close connection with data base
					
				} catch (Exception e) { 
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		
		btnEnter.setBounds(293, 233, 105, 33);
		frame.getContentPane().add(btnEnter);
		
		JTextPane txtpnStudentActivities = new JTextPane();
		txtpnStudentActivities.setFont(new Font("Tahoma", Font.BOLD, 25));
		txtpnStudentActivities.setEditable(false);
		txtpnStudentActivities.setText("Student Activities Login");
		txtpnStudentActivities.setBounds(126, 21, 331, 45);
		frame.getContentPane().add(txtpnStudentActivities);
		
		JTextPane txtpnUserName = new JTextPane();
		txtpnUserName.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnUserName.setEditable(false);
		txtpnUserName.setText("User name");
		txtpnUserName.setBounds(220, 142, 63, 20);
		frame.getContentPane().add(txtpnUserName);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnPassword.setEditable(false);
		txtpnPassword.setText("Password");
		txtpnPassword.setBounds(220, 173, 63, 20);
		frame.getContentPane().add(txtpnPassword);
		
		JLabel lblNewLabel = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/Login.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img1));
		lblNewLabel.setBounds(62, 106, 128, 159);
		frame.getContentPane().add(lblNewLabel);
	}
}
