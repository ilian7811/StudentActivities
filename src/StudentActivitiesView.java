import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentActivitiesView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentActivitiesView frame = new StudentActivitiesView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	/**
	 * Create the frame.
	 */
	public StudentActivitiesView() {
		setResizable(false);
		connection=sqliteConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 522);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblStudents = new JLabel("Students");
		lblStudents.setBounds(306, 14, 123, 30);
		lblStudents.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblStudents);
		
		
		JButton btnLogoutStudent = new JButton("Logout");
		btnLogoutStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				LoginFrame login = new LoginFrame();
				login.frame.setVisible(true);
			}
			
		});
		btnLogoutStudent.setBounds(612, 24, 89, 23);
		contentPane.add(btnLogoutStudent);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 699, 391);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		try {
			String query = "select Type,FirstName, LastName,Number,Plan, Grade from StudentsDatabaseInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet res = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(res));
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}	
	}
}
