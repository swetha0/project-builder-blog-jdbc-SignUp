package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface{
	
	ConnectionManager cm = new ConnectionManager();

	@Override
	public int signUp(User user) throws Exception {
		Connection con = null;
		con = cm.getConnection();
		if(con!=null) {
			System.out.println("...Connection Established");
			
			String email=user.getEmail();
			String password=user.getPassword();
			LocalDate localdate=user.getDate();
			System.out.println(email+" "+password+" "+localdate);
			String sql="insert into userdata(email,password,localdate)VALUES(?,?,?)";
			
			PreparedStatement st=con.prepareStatement(sql);
			
			st.setString(1,email);
			st.setString(2,password);
			st.setDate(3,Date.valueOf(localdate));
			st.executeQuery();

		}
		else
			System.out.println("Check your Connection");
		return 0;
	}

	@Override
	public boolean loginUser(User user) throws SQLException, Exception {
		String email=user.getEmail();
		String password=user.getPassword();
		
		Statement st = cm.getConnection().createStatement();
		
		ResultSet rs=st.executeQuery("SELECT *  from  USERDATA");
		
		while(rs.next()) {
			if(email.equals(rs.getString("email"))&& password.equals(rs.getString("PASSWORD"))) {
				cm.getConnection().close();
				return true;
			}
		}
		cm.getConnection().close();
		return false;
		
	}
	
}