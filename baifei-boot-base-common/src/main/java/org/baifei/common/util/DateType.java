package org.baifei.common.util;

public enum DateType {
	DATE(1),HOUR(2),MINUTE(3),SECOND(4);
	// 成员变量  
    private int index;  
    // 构造方法  
    private DateType(int index) {  
        this.index = index;  
    }  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }  
}
