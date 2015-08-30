package cn.com.poka.gzhquery.domain;

import java.util.List;

public class Pagination {
	
	private int total;
	private List<MoneyData> rows;

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<MoneyData> getRows() {
		return rows;
	}
	public void setRows(List<MoneyData> rows) {
		this.rows = rows;
	}
}
