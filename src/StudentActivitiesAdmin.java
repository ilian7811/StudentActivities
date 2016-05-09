import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import java.awt.Color;

import javax.security.auth.Refreshable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class StudentActivitiesAdmin extends JFrame {

	private JPanel contentPane;
	private JTable tableAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentActivitiesAdmin frame = new StudentActivitiesAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	}

	Connection connection = null;
	

	public void refreshTable(){
		try {
			
			String query = "select * from Students";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet res = pst.executeQuery();
			tableAdmin.setModel(DbUtils.resultSetToTableModel(res));
		
			pst.close();		// Close connection with data base
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	/**
	 * Create the frame.
	 */
	public StudentActivitiesAdmin() {
		setResizable(false);
		connection=sqliteConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 911, 537);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministrator = new JLabel("Administrator");
		lblAdministrator.setBounds(352, 11, 190, 44);
		lblAdministrator.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblAdministrator);
		
		JButton btnLogoutAdmin = new JButton("Logout");
		btnLogoutAdmin.setBounds(776, 28, 89, 23);
		btnLogoutAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				LoginFrame login = new LoginFrame();
				login.frame.setVisible(true);
			}
		});
		contentPane.add(btnLogoutAdmin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 78, 706, 430);
		contentPane.add(scrollPane);
		
		tableAdmin = new JTable();
		scrollPane.setViewportView(tableAdmin);
		
		JButton btnStudent = new JButton("Edit Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EditStudents editQ = new  EditStudents();
				editQ.frame.setVisible(true);
			}
		});
		btnStudent.setBounds(10, 75, 135, 23);
		contentPane.add(btnStudent);
		
		JButton btnPlan = new JButton("Edit Plan");
		btnPlan.setBounds(10, 110, 136, 23);
		contentPane.add(btnPlan);
		
		JButton btnSubject = new JButton("Edit Subject");
		btnSubject.setBounds(10, 144, 136, 23);
		contentPane.add(btnSubject);
		
		JButton btnGrade = new JButton("Edit Grade");
		btnGrade.setBounds(10, 178, 136, 23);
		contentPane.add(btnGrade);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(10, 212, 136, 23);
		contentPane.add(btnNewButton);
		
		refreshTable();
			
	}
}
