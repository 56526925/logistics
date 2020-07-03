package org.baifei.common.util;

import java.util.HashMap;
import java.util.Map;

/**
* 创建时间：2018年8月1日 下午5:00:55  
* @author 牛凯凯
* @version 1.0   
* 描述：  
*/
public class ConstantConfig {

	public static String PDF_ABSOLUTE_PATH = "F:";
	public static String PDF_URL_PATH = "http://110.249.144.42:8888";

	public static String LogisticsUrl = "http://122.51.68.40:8008";

	public static String getOrderStatus(String status){
		if(!status.isEmpty()){
			Map<String, String> map = new HashMap<String, String>();
			map.put("1", "新订单");
			map.put("2", "已支付");
			map.put("3", "配货中");
			map.put("4", "已发货");
			map.put("5", "已完成");
			map.put("6", "作废");
			if(map.get(status) != null){
				return map.get(status);
			}else{
				return "";
			}
		}else{
			return "";
		}
	}

	public static final String error_auth = "error_auth";//过期标志

	public static final String tiem_out_1 = "time";//超时

	public static final String tiem_out_2 = "out";//超时

	public static final String tiem_out_3 = "connection";//超时

	public static final String tiem_out_4 = "MERCHANT.WISH.COM";//也是超时

	public static final String error0 = "api.unauthorized";//令牌错误

	public static final String error1 = "unauthorized request";//长令牌过期

	public static final String error2 = "this access token has been revoked";//短令牌过期

	public static final String error3 = "your access token has expired";//短令牌过期

	public static final String error4 = "Invalid credentials given";

	/**成功*/
	public static final int code0 = 0;
	/**信息缺失*/
	public static final int code1 = 1;
	/**同步状态失败*/
	public static final int code4 = 4;
	/**调取接口超时的*/
	public static final int code888 = 888;
	/**授权过期*/
	public static final int code999 = 999;
}
