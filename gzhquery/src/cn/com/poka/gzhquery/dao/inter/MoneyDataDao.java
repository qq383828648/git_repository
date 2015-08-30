package cn.com.poka.gzhquery.dao.inter;

import java.util.List;

import cn.com.poka.gzhquery.domain.MoneyData;
import cn.com.poka.gzhquery.domain.MoneyDataInfo;

public interface MoneyDataDao {

	/**
	 * get money data by mon
	 * @param mon ：money data code
	 * @return	： money data list
	 * @throws Exception
	 */
	public List<MoneyDataInfo> findMoneyDataList( String mon ) throws Exception;
	
	public List<MoneyDataInfo> findMoneyDataListByLike( String mon ) throws Exception;

	public List<MoneyDataInfo> findMoneyDataListPage(String page, String rows, String dealNo) throws Exception;

	public int countResult(String dealNo) throws Exception;

	public int getCount() throws Exception;
	
}
