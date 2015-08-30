package cn.com.poka.gzhquery.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.poka.gzhquery.dao.inter.MoneyDataDao;
import cn.com.poka.gzhquery.domain.MoneyDataInfo;
import cn.com.poka.gzhquery.util.JdbcUtil;

@SuppressWarnings("unchecked")
public class MoneyDataDaoImpl implements MoneyDataDao {
	
	/**
	 * 用来获取数据库类型
	 * update liangb
	 * @return
	 */
	public String getFormatType(){
		
		String formatType = "";
		if(JdbcUtil.DriverType.contains("mysql")){
			formatType = "DATE_FORMAT(A.coltime,'%Y-%m-%d %H:%i:%S') as OperDate,";
		}else if(JdbcUtil.DriverType.contains("oracle")){
			formatType = "TO_CHAR(A.coltime,'YYYY-MM-DD HH24:MI:SS') as OperDate,";
		}else{
			
		}
		return formatType;
	}

	@Override
	public List<MoneyDataInfo> findMoneyDataList( String mon ) throws Exception {
		if( mon == null ){
			throw new Exception();
		}
		List<MoneyDataInfo> moneyDataList  = new ArrayList<MoneyDataInfo>();
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		
		
		String sql = "select *"
		+" from"
		+" ("
		+" select A.percode,"
		//+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype ,"
		//+" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype ,"
		//+" TO_CHAR(A.coltime,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
//		+" DATE_FORMAT(A.coltime,'%Y-%m-%d %H:%i:%S') as OperDate,"
		+getFormatType()
		+" A.mon,"
		+" A.monval,"
		+" A.monver,"
		+" A.bundleid,"
		+" A.imagepath,"
		+" C.ipaddr,"
		//+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
		+" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
		+" A.bankno,"
		+" C.bankname,"
		+" A.agencyno,"
		+" D.branchname"
		+" from MONEYDATA A"
		//+" left JOIN PERINFO B"
		//+" on A.percode= B.percode"
		+" left join BANKINFO C"
		+" on A.bankno= C.bankno"
		+" left join BRANCHINFO D"
		+" on A.agencyno= D.agencyno"
		+" where A.mon=?"
		
		+" UNION ALL"
		
		+" select A.percode,"
		//+" case C.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
		//+" case C.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
		//+" TO_CHAR(B.scantime,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
//		+" DATE_FORMAT(B.scantime,'%Y-%m-%d %H:%i:%S') as OperDate,"
		+getFormatType()
		+" A.mon,"
		+" A.monval,"
		+" A.monver,"
		+" A.bundleid,"
		+" A.imagepath,"
		+" D.ipaddr,"
		//+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
		+" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
		+" A.bankno,"
		+" D.bankname,"
		+" A.agencyno,"
		+" E.branchname"
		+" from MONEYDATA A"
		+" inner JOIN BUNDLEINFO B"
		+" on A.bundleid = B.bundleid"
		//+" inner JOIN PERINFO C"
		//+" on B.percode= C.percode"
		+" inner join BANKINFO D"
		+" on A.bankno= D.bankno"
		+" left join BRANCHINFO E"
		+" on A.agencyno= E.agencyno"
		+" where A.mon=?"
		+" AND (B.FLAG ='0'"
		+" OR B.FLAG ='1')"
		
		+" UNION ALL"
		
		+" select A.PERCODE,"
		//+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
		//+" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
		//+" TO_CHAR(E.OPERTIME,'YYYY-MM-DD HH24:MI:SS') as OPerdate,"
//		+" DATE_FORMAT(E.OPERTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
		+getFormatType()
		+" A.MON,"
		+" A.MONVAL,"
		+" A.monver,"
		+" A.BUNDLEID,"
		+" A.imagepath,"
		+" C.ipaddr,"
		//+" case E.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
		+" case E.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
		+" A.BANKNO,"
		+" C.BANKNAME,"
		+" A.AGENCYNO,"
		+" D.BRANCHNAME"
		+" from MONEYDATA A"
		+" inner join WITHDRAWINFO E"
		+" on E.SCANID = A.BUNDLEID"
		//+" inner join PERINFO B"
		//+" on A.PERCODE = B.PERCODE"
		+" inner join BANKINFO C"
		+" on A.bankno = C.bankno"
		+" inner join BRANCHINFO D"
		+" on A.agencyno = D.agencyno"
		+" where A.mon=?"
		
		+" UNION ALL"
		
		+" select A.PERCODE,"
		//+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
		//+" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
		//+" TO_CHAR(F.OPERTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
//		+" DATE_FORMAT(F.OPERTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
		+getFormatType()
		+" A.MON,"
		+" A.MONVAL,"
		+" A.monver,"
		+" A.BUNDLEID,"
		+" A.imagepath,"
		+" C.ipaddr,"
		//+" case F.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
		+" case F.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
		+" A.BANKNO,"
		+" C.BANKNAME,"
		+" A.AGENCYNO,"
		+" D.BRANCHNAME"
		+" from MONEYDATA A"
		+" inner join PACKAGEINFO E"
		+" on A.BUNDLEID =E.BUNDLEID"
		+" inner join WITHDRAWINFO F"
		+" on E.PACKAGEID = F.SCANID"
		//+" inner join PERINFO B"
		//+" on A.PERCODE = B.PERCODE"
		+" inner join BANKINFO C"
		+" on A.Bankno = C.bankno"
		+" inner join BRANCHINFO D"
		+" on A.agencyno = D.agencyno"
		+" where A.mon=?"
		
		//+" UNION ALL"
		//
		//+" select A.PERCODE,"
		////+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
		//+" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
		////+" TO_CHAR(A.COLTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
		//+" DATE_FORMAT(A.COLTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
		//+" A.MON,"
		//+" A.MONVAL,"
		//+" A.MONVER,"
		//+" A.BUNDLEID,"
		//+" A.imagepath,"
		//+" C.ipaddr,"
		////+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
		//+" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
		//+" B.BANKNO,"
		//+" C.BANKNAME,"
		//+" B.AGENCYNO,"
		//+" D.BRANCHNAME"
		//+" from V_MONBOXOFPACKAGEPUSHATM A"
		//+" inner join PERINFO B"
		//+" on A.PERCODE=B.percode"
		//+" inner join BANKINFO C"
		//+" on B.BANKNO=C.BANKNO"
		//+" inner join BRANCHINFO D"
		//+" on B.AGENCYNO=D.AGENCYNO"
		//+" where A.mon=?"
		//
		//+" UNION ALL"
		
		//+" select A.PERCODE,"
		////+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
		//+" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
		////+" TO_CHAR(A.COLTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
		//+" DATE_FORMAT(A.COLTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
		//+" A.MON,"
		//+" A.MONVAL,"
		//+" A.MONVER,"
		//+" A.BUNDLEID,"
		//+" A.imagepath,"
		//+" C.ipaddr,"
		////+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
		//+" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
		//+" B.BANKNO,"
		//+" C.BANKNAME,"
		//+" B.AGENCYNO,"
		//+" D.BRANCHNAME"
		//+" from V_MONBOXOFBUNDLEPUSHATM A"
		//+" inner join PERINFO B"
		//+" on A.PERCODE=B.percode"
		//+" inner join BANKINFO C"
		//+" on B.BANKNO=C.BANKNO"
		//+" inner join BRANCHINFO D"
		//+" on B.AGENCYNO=D.AGENCYNO"
		//+" where A.mon=?"
		+" ) T"
		+" order by OperDate asc";
//		+" limit 0,500 ";

		moneyDataList = (List<MoneyDataInfo>) runner.query(sql, new Object[]{mon,mon,mon,mon},new BeanListHandler(MoneyDataInfo.class));
		return moneyDataList;
	}
	
	
	@Override
	public List<MoneyDataInfo> findMoneyDataListByLike( String mon ) throws Exception {
		if( mon == null ){
			throw new Exception();
		}
		List<MoneyDataInfo> moneyDataList  = new ArrayList<MoneyDataInfo>();
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select *"
				+" from"
				+" ("
				+" select A.percode,"
				//+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype ,"
//				+" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype ,"
				//+" TO_CHAR(A.coltime,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
//				+" DATE_FORMAT(A.coltime,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+getFormatType()
				+" A.mon,"
				+" A.monval,"
				+" A.monver,"
				+" A.bundleid,"
				+" A.imagepath,"
				+" C.ipaddr,"
				//+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+" A.bankno,"
				+" C.bankname,"
				+" A.agencyno,"
				+" D.branchname"
				+" from MONEYDATA A"
//				+" left JOIN PERINFO B"
//				+" on A.percode= B.percode"
				+" left join BANKINFO C"
				+" on A.bankno= C.bankno"
				+" left join BRANCHINFO D"
				+" on A.agencyno=D.agencyno"
				+" where A.mon like ?"

				+" UNION ALL"

				+" select A.percode,"
				//+" case C.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
//				+" case C.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				//+" TO_CHAR(B.scantime,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
//				+" DATE_FORMAT(B.scantime,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+getFormatType()
				+" A.mon,"
				+" A.monval,"
				+" A.monver,"
				+" A.bundleid,"
				+" A.imagepath,"
				+" D.ipaddr,"
				//+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+" A.bankno,"
				+" D.bankname,"
				+" A.agencyno,"
				+" E.branchname"
				+" from MONEYDATA A"
				+" inner JOIN BUNDLEINFO B"
				+" on A.bundleid= B.bundleid"
//				+" inner JOIN PERINFO C"
//				+" on B.percode= C.percode"
				+" inner join BANKINFO D"
				+" on A.bankno= D.bankno"
				+" left join BRANCHINFO E"
				+" on A.agencyno=E.agencyno"
				+" where A.mon like ?"
				+" AND (B.FLAG ='0'"
				+" OR B.FLAG ='1')"

				+" UNION ALL"

				+" select A.PERCODE,"
				//+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
//				+" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				//+" TO_CHAR(E.OPERTIME,'YYYY-MM-DD HH24:MI:SS') as OPerdate,"
//				+" DATE_FORMAT(E.OPERTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+getFormatType()
				+" A.MON,"
				+" A.MONVAL,"
				+" A.monver,"
				+" A.BUNDLEID,"
				+" A.imagepath,"
				+" C.ipaddr,"
				//+" case E.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+" case E.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+" A.BANKNO,"
				+" C.BANKNAME,"
				+" A.AGENCYNO,"
				+" D.BRANCHNAME"
				+" from MONEYDATA A"
				+" inner join WITHDRAWINFO E"
				+" on E.SCANID = A.BUNDLEID"
//				+" inner join PERINFO B"
//				+" on A.PERCODE = B.PERCODE"
				+" inner join BANKINFO C"
				+" on A.bankno = C.bankno"
				+" inner join BRANCHINFO D"
				+" on A.agencyno = D.agencyno"
				+" where A.mon like ?"

				+" UNION ALL"

				+" select A.PERCODE,"
				//+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
//				+" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				//+" TO_CHAR(F.OPERTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
//				+" DATE_FORMAT(F.OPERTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+getFormatType()
				+" A.MON,"
				+" A.MONVAL,"
				+" A.monver,"
				+" A.BUNDLEID,"
				+" A.imagepath,"
				+" C.ipaddr,"
				//+" case F.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+" case F.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+" A.BANKNO,"
				+" C.BANKNAME,"
				+" A.AGENCYNO,"
				+" D.BRANCHNAME"
				+" from MONEYDATA A"
				+" inner join PACKAGEINFO E"
				+" on A.BUNDLEID = E.BUNDLEID"
				+" inner join WITHDRAWINFO F"
				+" on E.PACKAGEID = F.SCANID"
//				+" inner join PERINFO B"
//				+" on A.PERCODE = B.PERCODE"
				+" inner join BANKINFO C"
				+" on A.Bankno = C.bankno"
				+" inner join BRANCHINFO D"
				+" on A.agencyno = D.agencyno"
				+" where A.mon like ?"

//				+" UNION ALL"
//
//				+" select A.PERCODE,"
//				//+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
//				+" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
//				//+" TO_CHAR(A.COLTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
//				+" DATE_FORMAT(A.COLTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
//				+" A.MON,"
//				+" A.MONVAL,"
//				+" A.MONVER,"
//				+" A.BUNDLEID,"
//				+" A.imagepath,"
//				+" C.ipaddr,"
//				//+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
//				+" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
//				+" B.BANKNO,"
//				+" C.BANKNAME,"
//				+" B.AGENCYNO,"
//				+" D.BRANCHNAME"
//				+" from V_MONBOXOFPACKAGEPUSHATM A"
//				+" inner join PERINFO B"
//				+" on A.PERCODE=B.percode"
//				+" inner join BANKINFO C"
//				+" on B.BANKNO=C.BANKNO"
//				+" inner join BRANCHINFO D"
//				+" on B.AGENCYNO=D.AGENCYNO"
//				+" where A.mon like ?"
//
//				+" UNION ALL"
//
//				+" select A.PERCODE,"
//				//+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
//				+" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
//				//+" TO_CHAR(A.COLTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
//				+" DATE_FORMAT(A.COLTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
//				+" A.MON,"
//				+" A.MONVAL,"
//				+" A.MONVER,"
//				+" A.BUNDLEID,"
//				+" A.imagepath,"
//				+" C.ipaddr,"
//				//+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
//				+" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
//				+" B.BANKNO,"
//				+" C.BANKNAME,"
//				+" B.AGENCYNO,"
//				+" D.BRANCHNAME"
//				+" from V_MONBOXOFBUNDLEPUSHATM A"
//				+" inner join PERINFO B"
//				+" on A.PERCODE=B.percode"
//				+" inner join BANKINFO C"
//				+" on B.BANKNO=C.BANKNO"
//				+" inner join BRANCHINFO D"
//				+" on B.AGENCYNO=D.AGENCYNO"
//				+" where A.mon like ?"
				+" ) T"
				+" order by OperDate asc";
//				+" limit 0,500 ";

		
		moneyDataList = (List<MoneyDataInfo>) runner.query(sql, new Object[]{mon,mon,mon,mon},new BeanListHandler(MoneyDataInfo.class));
		
		return moneyDataList;
	}
	

	@Override
	public List<MoneyDataInfo> findMoneyDataListPage(String page, String rows, String dealNo) throws Exception {
		int _page = Integer.parseInt(page);
		int _rows = Integer.parseInt(rows);
		int begin = (_page-1)*_rows;
		int end = begin + _rows;
		List<MoneyDataInfo> moneyDataList  = new ArrayList<MoneyDataInfo>();
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from("
//				+" select ROWNUM as N,r.* from(" 
				+" select r.* from(" 
				+" select * from("
//				+" select A.percode,case B.pertype when '00' then '点钞机' when '01' then 'ATM' when '02' then '清分机' end as PERTYPE,"
				+" select A.percode,case B.pertype when '00' then '00' when '01' then '01' when '02' then '02' end as PERTYPE,"
//				+" TO_CHAR(A.coltime,'YYYY-MM-DD HH24:MI:SS') as OperDate,A.mon,A.monval,A.monver,A.bundleid, b.bankno, c.bankname, b.agencyno, d.branchname"
				+" DATE_FORMAT(A.coltime,'%Y-%m-%d %H:%i:%S') as OperDate,A.mon,A.monval,A.monver,A.bundleid, B.bankno, C.bankname, B.agencyno, D.branchname"
				+" from moneydata A inner JOIN perinfo B on A.percode= B.percode"
				+" inner join bankinfo C on B.bankno= C.bankno inner join BranchInfo D on B.agencyno=D.agencyno"
				+" union all"
//				+" select B.percode,case C.pertype when '00' then '点钞机' when '01' then 'ATM' when '02' then '清分机' end as PERTYPE,"
				+" select B.percode,case C.pertype when '00' then '00' when '01' then '01' when '02' then '02' end as PERTYPE,"
//				+" TO_CHAR(B.scantime,'YYYY-MM-DD HH24:MI:SS') as OperDate,A.mon,A.monval,A.monver,A.bundleid, c.bankno, d.bankname, c.agencyno, e.branchname"
				+" DATE_FORMAT(B.scantime,'%Y-%m-%d %H:%i:%S') as OperDate,A.mon,A.monval,A.monver,A.bundleid, C.bankno, D.bankname, C.agencyno, E.branchname"
				+" from moneydata A inner JOIN bundleinfo B on A.bundleid= B.bundleid inner JOIN perinfo C on B.percode= C.percode"
				+" inner join bankinfo D on C.bankno= D.bankno inner join BranchInfo E on C.agencyno=E.agencyno"
				+" ) T order by OperDate asc "
				+" ) r )  limit ?,?";
		//String sql = "select percode,TO_CHAR(coltime,'YYYY-MM-DD HH24:MI:SS') as coltime,mon,montype,monval,monver,trueflag,quanlity,imagesno,operatorid,TO_CHAR(operdate,'YYYY-MM-DD HH24:MI:SS') as operdate from (select t.*,ROWNUM as n from moneydata t where mon=?) where n>? and n<=?";
		moneyDataList = (List<MoneyDataInfo>)runner.query(sql,new Object[]{begin,end},new BeanListHandler(MoneyDataInfo.class));
		return moneyDataList;
	}

	@Override
	public int countResult(String dealNo) throws Exception {
		int total = 0;
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select count(*) from MONEYDATA where mon=?";
		Long temp = (Long) runner.query(sql, dealNo,new ScalarHandler());
		total = temp.intValue();
		return total;
	}

	@Override
	public int getCount() throws Exception {
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		//String sql = "select count(*) as count from moneydata";
		String sql =  "select count(*)"
		  +" from"
	      +" (select A.percode as percode"
	      +" from MONEYDATA A"
	      +" inner JOIN perinfo B"
	      +" on A.percode= B.percode"
	      +" inner join bankinfo C"
	      +" on B.bankno= C.bankno"
	      +" inner join BranchInfo D"
	      +" on B.agencyno=D.agencyno"
	      +" union all"
	      +" select A.bundleid as bundleid"
	      +" from MONEYDATA A"
	      +" inner JOIN bundleinfo B"
	      +" on A.bundleid= B.bundleid"
	      +" inner JOIN perinfo C"
	      +" on B.percode= C.percode"
	      +" inner join bankinfo D"
	      +" on C.bankno= D.bankno"
	      +" inner join BranchInfo E"
	      +" on C.agencyno=E.agencyno"
	      +" )";
		Long total = (Long)runner.query(sql, new ResultSetHandler()  {
			
			@Override
			public Object handle(ResultSet rs) throws SQLException {
				if( rs.next() ){
					return rs.getLong(1);
				}else{
					return 0L;
				}
			}
		});
		return total.intValue();
	}
}
