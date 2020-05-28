package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface
{

	@Override
	public int signUp(User user) throws SQLException, Exception
	{
		String emailid = user.getEmail();
		String password = user.getPassword();
		LocalDate date1 = user.getDate();
		
		ConnectionManager cm = new ConnectionManager();
		System.out.println("");
		String sql = "INSERT into FIRSTBLOG(emailid, password, date1) VALUES (?,?,?)";
		
		PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
		System.out.println("Statement Prepared");
		st.setString(1, emailid);
		st.setString(2, password);
		st.setDate(3, java.sql.Date.valueOf(date1));
		st.executeUpdate();
		System.out.println("Data Inserted");
		ConnectionManager.getConnection().close();
		return 0;
	}

	@Override
	public boolean loginUser(User user) throws SQLException, Exception 
	{
		String email = user.getEmail();
		String pass = user.getPassword();
		
		ConnectionManager con = new ConnectionManager();
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT * from FIRSTBLOG");
		while(rs.next())
		{
			String tempEmail = rs.getString("EMAILID");
			String tempPass = rs.getString("PASSWORD");
			if(tempEmail.equals(email) && tempPass.equals(pass))
				return true;
		}
		ConnectionManager.getConnection().close();
		return false;
	}

}