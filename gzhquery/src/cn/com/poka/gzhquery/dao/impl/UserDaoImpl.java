package cn.com.poka.gzhquery.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.com.poka.gzhquery.dao.inter.UserDao;
import cn.com.poka.gzhquery.domain.User;
import cn.com.poka.gzhquery.util.JdbcUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public User findUserByNameAndPass(String usercode, String userpasswd)
			throws Exception {
		if( usercode == null || userpasswd == null ){
			throw new Exception();
		}
		User user = null;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from USERINFO where usercode=? and userpassword=?";
		user = (User)runner.query(sql, new Object[]{usercode,userpasswd},new BeanHandler(User.class));
		return user;
	}

}
