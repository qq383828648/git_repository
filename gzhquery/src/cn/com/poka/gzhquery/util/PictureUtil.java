package cn.com.poka.gzhquery.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class PictureUtil {

	Connection conn = null;
	String sql = "";
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	public byte[] GetImgByteByMon(String mon) {
		byte[] data = null;
		try {
			conn = dataSource.getConnection();
			//conn = JdbcUtil.getConnection();
			sql = "select imagesno from moneydata where mon =" + "'"+mon+"'"+"and rownum=1";

			Statement stat = conn.createStatement();
			ResultSet res = stat.executeQuery(sql);

			if (res.next()) {
				// 获取BLOB字段'imagesno'照片信息
				java.sql.Blob blob = res.getBlob("imagesno");
				InputStream inStream = blob.getBinaryStream();
				try {
					long nLen = blob.length();
					int nSize = (int) nLen;
					data = new byte[nSize];
					inStream.read(data);
					inStream.close();
				} catch (IOException e) {
					System.out.println("获取图片数据失败,原因:" + e.getMessage());
				}
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
}
