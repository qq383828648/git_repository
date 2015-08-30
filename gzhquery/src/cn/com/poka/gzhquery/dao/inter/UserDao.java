package cn.com.poka.gzhquery.dao.inter;

import cn.com.poka.gzhquery.domain.User;

public interface UserDao {

	public User findUserByNameAndPass( String usercode,String userpasswd ) throws Exception;
	
}
