package org.baifei.common.util;

public class ResultCode {
	private int ack;
	private String msg;
	private Object data;
	
	public ResultCode(){
		this.ack=-1;
		this.msg="初始化";
	}

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
