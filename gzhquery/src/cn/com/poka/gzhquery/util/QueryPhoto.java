package cn.com.poka.gzhquery.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryPhoto {

			private static Connection conn = null;
			private Statement stmt = null;
			private ResultSet rs = null;

			static {
				try {
					// 加载Oracle驱动
					Class.forName("oracle.jdbc.driver.OracleDriver");
					// 获得连接
					conn = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:orcl", "system",
							"root");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			/**
			 * 关闭所有与数据库相关的连接
			 * 
			 * @param conn
			 * @param stmt
			 * @param rs
			 */
			public void closeAll(ResultSet rs, Statement stmt, Connection conn) {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			/**
			 * 向数据库中插入图片
			 */
			public void inputImage() {
				try {
					stmt = conn.createStatement();
					conn.setAutoCommit(false);// 取消自动提交功能
					OutputStream os = null;
					// 插入一个空对象empty_blob()
					stmt.executeUpdate("insert into moneydata (mon,coltime, imagesno) values ('888888',to_date('2013-05-10 00:00:00','YYYY-MM-DD HH24:MI:SS'), empty_blob())");
					// 锁定数据行进行更新，注意"for update"语句
					rs = stmt.executeQuery("select imagesno from moneydata where mon= '222222' for update");
					if (rs.next()) {
						// 得到java.sql.Blob对象后强制转换为oracle.sql.BLOB
						oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob("imagesno");
						// 通过getBinaryOutputStream()方法获得向数据库中插入图片的"管道"
						os = blob.getBinaryOutputStream();
						// 读取想要存储的图片文件
						InputStream is = new FileInputStream("F:\\冠字号图片1.bmp");
						// 依次读取流字节,并输出到已定义好的数据库字段中.
						int i = 0;
						while ((i = is.read()) != -1) {
							os.write(i);
						}
					}
					os.flush();
					os.close();
					conn.commit();
					conn.setAutoCommit(true);// 恢复现场
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// 关闭相应数据库连接
					closeAll(rs, stmt, conn);
				}
			}

			/**
			 * 从数据库里检索出图片
			 */
			public void outputImage() {
				try {
					String sql = "select image from t_image where id=1";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						oracle.sql.BLOB b = (oracle.sql.BLOB) rs.getBlob(1);
						InputStream is = b.getBinaryStream();
						FileOutputStream fos = new FileOutputStream("E:\\outputImage.jpg");
						int i = 0;
						while ((i = is.read()) != -1) {
							fos.write(i);
						}
						fos.flush();
						fos.close();
						is.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					closeAll(rs, stmt, conn);
				}
			}
			
			public static void main(String[] args) {
				// 从硬盘提取图片插入到数据库中
				 new QueryPhoto().inputImage();
				// 从数据库中检索图片到硬盘
				//new QueryPhoto().outputImage();
			}
		
	}
