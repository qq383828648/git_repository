package cn.com.poka.gzhquery.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.com.poka.gzhquery.dao.impl.MoneyDataDaoImpl;
import cn.com.poka.gzhquery.domain.MoneyDataInfo;
import cn.com.poka.gzhquery.service.GZHQueryService;

public class IndexServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public IndexServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String dealNo = "L2Z8655799";
		MoneyDataDaoImpl moneyData = new MoneyDataDaoImpl();
		try {
			List<MoneyDataInfo> moneyDataList = moneyData.findMoneyDataListPage(page, rows, dealNo);
			GZHQueryService gzhQueryService = new GZHQueryService();
			int total = gzhQueryService.getCount();
			Map map = new HashMap<Integer, String>();
			map.put("total", total);
			map.put("rows", moneyDataList);
			Gson gson = new Gson();
			String msg = gson.toJson(map);
			response.getWriter().write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public void init() throws ServletException {
	}

}
