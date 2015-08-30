package cn.com.poka.gzhquery.util;

import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**JDBCUtil with C3P0
 * @author Finder
 * @time 2013-07-12 14:47:21
 */
public final class JdbcUtil {
	//C3P0 auto find c3p0-config.xml from src/
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//every thread has own independent Connection
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	//驱动类型（用来区分是mysql还是Oracle）----update for liangb
	public static String DriverType = "";
	
	//get dataSource
	public static ComboPooledDataSource getDataSource() {
		DriverType = dataSource.getDriverClass();
		return dataSource;
	}
	
	/**
	 * get a free connection from C3P0
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			conn = dataSource.getConnection();
			tl.set(conn);
		}
		
		return conn;
	}

	/**
	 * close connection object
	 * @param conn
	 * @throws SQLException
	 */
	public static void close(Connection conn) throws SQLException{
		if(conn!=null){
			conn.close();
		}
	}

	/**
	 * begin transaction
	 * @throws SQLException
	 */
	public static void begin() throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		conn.setAutoCommit(false);
	}
	
	/**
	 * commit transaction
	 * @throws SQLException
	 */
	public static void commit() throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		conn.commit();
	}
	
	/**
	 * roll back transaction
	 * @throws SQLException
	 */
	public static void rollback() throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		conn.rollback();
	}

	/**
	 * close connection object
	 * @throws SQLException
	 */
	public static void closeConnection() throws SQLException {
		Connection conn = JdbcUtil.getConnection();
		JdbcUtil.close(conn);
		tl.remove();
	}
}
