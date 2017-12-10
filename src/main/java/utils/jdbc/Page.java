package utils.jdbc;

import java.util.List;

public class Page<T> {
	
	//当前第几页
	private int pageNo;
	
	//当前页的list
	private List<T> list;
	
	//每页显示多少条记录
	private int pageSize = 5;
	
	//共有多少条记录
	private long totalItemNumber;
	
	
	public Page(int pageNo) {
		super();
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	//需校验
	public int getPageNo() {
		
		if(pageNo <= 0) {
			pageNo = 1;
		}
		if(pageNo > getTotalPageNumber()) {
			pageNo = getTotalPageNumber();
		}
		
		return pageNo;
	}


	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}
	
	//获取总页数
	public int getTotalPageNumber() {
		int totalPageNumber = (int)totalItemNumber / pageSize;
		if(totalItemNumber % pageSize != 0) {
			totalPageNumber++;
		}
		return totalPageNumber;
	}
	
	public long getTotalItemNumber() {
		return totalItemNumber;
	}
	
	public void setTotalItemNumber(long totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}
	//是否有下一页
	public boolean isHasNext() {
		
		if(getPageNo() < getTotalPageNumber()) {
			return true;
		}
		
		return false;
	}
	//是否有上一页
	public boolean isHasPrev() {
		
		if(getPageNo() > 1) {
			return true;
		}
		
		return false;
	}
	//获取下一页页码
	public int getNextPage() {
		if(isHasNext()) {
			return getPageNo() + 1;
		}
		return getPageNo();
	}
	
	//获取上一页一页页码
	public int getPrevPage() {
		if(isHasPrev()) {
			return getPageNo() - 1;
		}
		return getPageNo();
	}
	
}
