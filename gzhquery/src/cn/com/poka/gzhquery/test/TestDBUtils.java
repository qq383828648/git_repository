package cn.com.poka.gzhquery.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.poka.gzhquery.domain.User;
import cn.com.poka.gzhquery.util.JdbcUtil;

public class TestDBUtils {

	public static void main(String[] args) throws Exception {
	/*	List<User> empList = new ArrayList<User>();
		QueryRunner runner = new QueryRunner( JdbcUtil.getDataSource() );
		String sql = "select * from userinfo";
		empList = (List<User>)runner.query(sql, new BeanListHandler(User.class));
		for( User emp:empList ){
			System.out.println(emp.getUsername());
			System.out.println(emp.getUserpassword());
			System.out.println(emp.getAgencyno());
		}
		String a = "中文";
		System.out.println(a);*/
		QueryRunner runner = new QueryRunner( JdbcUtil.getDataSource() );
		String sql = "insert into emp(empno,ename) values( 1001,'蒙克斌' )";
		runner.update(sql);
		System.out.println("success");
		/*QueryRunner runner2 = new QueryRunner( JdbcUtil.getDataSource() );
		EMP emp = new EMP();
		Integer id = 7521;
		String sql2 = "select * from emp where empno=7521";
		emp = (EMP) runner2.query(sql2,new BeanHandler(EMP.class));
		System.out.println(emp.getEmpNo());
		System.out.println(emp.geteName());
		System.out.println(emp.getJob());*/
		
	}
}
