package tw.com.huafan.project.dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tw.com.huafan.project.dao.UserDao;
import tw.com.huafan.project.dbutils.DBUtils;
import tw.com.huafan.project.projo.User;

public class UserDaoImpl implements UserDao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public boolean login(String account, String password) {

		try {

			conn = DBUtils.getDB().getConnection();
			String sql = "select * from members where account =? and password =?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, account);
			ps.setString(2, password);

			System.out.println("password =>> " + account);
			System.out.println("account =>> " + password);

			if (ps.executeQuery().next()) {

				return true;

			}

		} catch (SQLException throwables) {

			throwables.printStackTrace();

		} finally {
			try {

				DBUtils.getDB().close(ps);
				DBUtils.getDB().close(conn);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean register(User us) {

		try {
			conn = DBUtils.getDB().getConnection();
			String saveSQL = "INSERT INTO members(name, account, password)value(?, ?, ?)";
			ps = conn.prepareStatement(saveSQL);

			ps.setString(1, us.getName());
			ps.setString(2, us.getAccount());
			ps.setString(3, us.getPassword());

			int value = ps.executeUpdate();

			if (value > 0) {
				return true;
			}

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {

				DBUtils.getDB().close(ps);
				DBUtils.getDB().close(conn);

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return false;
	}

	@Override
	public User findUser(String name) {

		String sql = "select * from members where name = ?";

		conn = DBUtils.getDB().getConnection();

		User user = null;

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int id = rs.getInt(1);
				String namedb = rs.getString(2);
				String accountdb = rs.getString(3);
				String passworddb = rs.getString(4);

				user = new User(id, namedb, accountdb, passworddb);
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			try {
				DBUtils.getDB().close(ps);
				DBUtils.getDB().close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return user;
	}

	@Override
	public boolean updateName(User us) {
		boolean rowUpdated = false;

		String sql = "update members set name =? where account =? and password =?";

		conn = DBUtils.getDB().getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, us.getName());
			ps.setString(2, us.getAccount());
			ps.setString(3, us.getPassword());


			rowUpdated = ps.executeUpdate() > 0;

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {

				DBUtils.getDB().close(ps);
				DBUtils.getDB().close(conn);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return rowUpdated;
	}

	@Override
	public boolean updatePass(User us) {
		boolean rowUpdated = false;

		String sql = "update members set password =? where account =?";

		conn = DBUtils.getDB().getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, us.getPassword());
			ps.setString(2, us.getAccount());

			System.out.println("password =>> " + us.getPassword());
			System.out.println("account =>> " + us.getAccount());

			rowUpdated = ps.executeUpdate() > 0;

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {

				DBUtils.getDB().close(ps);
				DBUtils.getDB().close(conn);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return rowUpdated;
	}

	public String getName(String account) {
		
		String name = null;
		
		
		conn = DBUtils.getDB().getConnection();
		String sql = "select * from members where account =?";
		
		try {
			
			ps = conn.prepareStatement(sql);

			

			System.out.println("1====>" + account);
			
			ps.setString(1, account);
			
			
			rs = ps.executeQuery();
			
			while (rs.next()) {

				name = rs.getString(2);
				
				return name;
			}

		} catch (SQLException throwables) {

			throwables.printStackTrace();

		} finally {
			try {

				DBUtils.getDB().close(ps);
				DBUtils.getDB().close(conn);

			} catch (SQLException e) 
			{ // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return name;

	}
	
	

}
