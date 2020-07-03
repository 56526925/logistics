package org.baifei.modules.util;


public class ResulstCodeWeb<T> {
	private int ack;
	private String msg;
	private T data;
	private Object pop;
	private int totalNum;
	private int totalPage;
	private int pageSize;
	
	public int getAck() {
		return ack;
	}
	public void setAck(int ack) {
		this.ack = ack;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Object getPop() {
		return pop;
	}
	public void setPop(Object pop) {
		this.pop = pop;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
