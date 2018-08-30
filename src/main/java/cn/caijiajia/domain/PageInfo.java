package cn.caijiajia.domain;

import java.util.List;

public class PageInfo<T> {
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	//当前页
	private Integer pageNum;
	//每页显示的行数
	private Integer pageSize;
	//总数
	private Long total;
	//分页后的数据
	private List<T> list;
	
}
