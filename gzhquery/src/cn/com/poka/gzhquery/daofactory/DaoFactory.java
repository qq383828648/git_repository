package cn.com.poka.gzhquery.daofactory;

import java.io.InputStream;
import java.util.Properties;

import cn.com.poka.gzhquery.dao.inter.MoneyDataDao;
import cn.com.poka.gzhquery.dao.inter.UserDao;

public class DaoFactory {

	private static DaoFactory instance;
	private Properties props;
	private DaoFactory () throws Exception {
		try {
			ClassLoader cl = DaoFactory.class.getClassLoader();
			InputStream is = cl.getResourceAsStream("cn/com/poka/gzhquery/config/dao.properties");
			props = new Properties();
			props.load(is);
		} catch (Exception e) {
			throw e;
		}
	}
	public static DaoFactory newInstance() throws Exception{
		synchronized (DaoFactory.class) {
			if( instance == null ){
				synchronized (DaoFactory.class) {
					instance = new DaoFactory();
				}
			}
		}
		return instance;
	}
	
	public UserDao getUserDao() throws Exception{
		String userDaoClass =  props.getProperty("userDaoClass");
		UserDao userDao = (UserDao) Class.forName(userDaoClass).newInstance();
		return userDao;
	}
	
	public MoneyDataDao getMoneyDataDao() throws Exception{
		String moneyDataDaoClass = props.getProperty("moneyDataDaoClass");
		MoneyDataDao moneyDataDao = (MoneyDataDao) Class.forName(moneyDataDaoClass).newInstance();
		return moneyDataDao;
	}
	
}
