import java.sql.*;
import javax.swing.*;

public class sqliteConnection {
	Connection conn = null;
	
	public static Connection dbConnector()
	{
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\workspace\\StudentActivities\\StudentsActivity.sqlite");//Data base file path
//			JOptionPane.showMessageDialog(null, "SQLite connected");
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
