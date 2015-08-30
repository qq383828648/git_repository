package cn.com.poka.gzhquery.service;

import java.util.List;

import cn.com.poka.gzhquery.dao.inter.MoneyDataDao;
import cn.com.poka.gzhquery.dao.inter.UserDao;
import cn.com.poka.gzhquery.daofactory.DaoFactory;
import cn.com.poka.gzhquery.domain.MoneyData;
import cn.com.poka.gzhquery.domain.MoneyDataInfo;
import cn.com.poka.gzhquery.domain.User;

public class GZHQueryService {

	private UserDao userDao;
	private MoneyDataDao moneyDataDao;
	public GZHQueryService() throws Exception{
		try {
			userDao = DaoFactory.newInstance().getUserDao();
			moneyDataDao = DaoFactory.newInstance().getMoneyDataDao();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public User login( User user ) throws Exception{
		if( user  == null ){
			throw new Exception();
		}
		return userDao.findUserByNameAndPass(user.getUsercode(), user.getUserpassword());
	}
	
	public List<MoneyDataInfo> getMoneyDataList( String mon ) throws Exception {
		if( mon == null ){
			throw new Exception();
		}
		return moneyDataDao.findMoneyDataList(mon);
	}
	
	public List<MoneyDataInfo> getMoneyDataListByLike( String mon ) throws Exception {
		if( mon == null ){
			throw new Exception();
		}
		return moneyDataDao.findMoneyDataListByLike(mon);
	}

	public List<MoneyDataInfo> getMoneyDataListPage(String page, String rows, String dealNo) throws Exception {
		if( page == null || rows == null || dealNo == null){
			throw new Exception();
		}
		return moneyDataDao.findMoneyDataListPage(page,rows,dealNo);
	}

	public int getResultCount( String dealNo ) throws Exception {
		return moneyDataDao.countResult(dealNo);
	}
	
	public int getCount() throws Exception{
		return moneyDataDao.getCount();
	}
}
