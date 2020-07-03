package org.baifei.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	public static final int privateType1 = 1;
	public static final int privateType2 = 2;
	/**
	 * 
	 * distinctByKey:自定义拉姆达表达式去重
	 * test
	 * @param keyExtractor
	 * @return Predicate<T>
	 */
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
	}
	
	public static Map<String, Object> objectToMap(Object obj,Map<String, Object> map) throws Exception {    
		if(obj == null)  
	       return null;      
		if(map == null) {
			map = new HashMap<String, Object>();   
		}
	
	    BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
	    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
	    for (PropertyDescriptor property : propertyDescriptors) {    
	       String key = property.getName();    
	       if (key.compareToIgnoreCase("class") == 0) {   
	           continue;  
	       }  
	       Method getter = property.getReadMethod();  
	       Object value = getter!=null ? getter.invoke(obj) : null;  
	       if(property.getPropertyType().getSimpleName().equals("Integer") || property.getPropertyType().getSimpleName().equals("Long") || property.getPropertyType().getSimpleName().equals("Double") || property.getPropertyType().getSimpleName().equals("Integer") || property.getPropertyType().getSimpleName().equals("long") || property.getPropertyType().getSimpleName().equals("double") || property.getPropertyType().getSimpleName().equals("int")) {
	    	   if(value != null) {
	    		   value = value.toString().equals("0") || value.toString().equals("0.0")?null:value;
	    	   }
	       }
	       if(value != null) {
	    	   map.put(key, value); 
	       }
	    }    
	
	    return map;  
	} 
	
	public static String DateToStr(Date time,String pattern) {
		if(pattern == null || pattern.length() <= 0 || time == null) {
			return null;
		}
		try {
			SimpleDateFormat f = new SimpleDateFormat(pattern);
			return f.format(time);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Date getSpecifiedTime(int diff,DateType dateType) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			if(dateType.getIndex() == 1) {
				cal.add(Calendar.DATE, diff);
			}else if(dateType.getIndex() == 2) {
				cal.add(Calendar.HOUR, diff);
			}else if(dateType.getIndex() == 3) {
				cal.add(Calendar.MINUTE, diff);
			}else if(dateType.getIndex() == 4) {
				cal.add(Calendar.SECOND, diff);
			}
			
			return cal.getTime();
		}catch (Exception e) {
			return null;
		}
	}
	
	public static Date getSpecifiedTime(Date d,int diff,DateType dateType) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			if(dateType.getIndex() == 1) {
				cal.add(Calendar.DATE, diff);
			}else if(dateType.getIndex() == 2) {
				cal.add(Calendar.HOUR, diff);
			}else if(dateType.getIndex() == 3) {
				cal.add(Calendar.MINUTE, diff);
			}else if(dateType.getIndex() == 4) {
				cal.add(Calendar.SECOND, diff);
			}
			
			return cal.getTime();
		}catch (Exception e) {
			return null;
		}
	}
	
	 public static double strToDouble(String str) {
        double d = 0.0;
        if (null == str || str.length() <= 0 || str.equals("null") || str.equals("NULL")) {
            return d;
        }
        str = str.trim();
        try {
			d= Double.valueOf(str).doubleValue();
		} catch (Exception e) {
			d= 0.0;
		}
        return d;
    }
	 
    public static int strToInt(String str) {
        int i = 0;
        if (null == str || str.length() <= 0 || str.equals("null") || str.equals("NULL")) {
            return i;
        }
        str = str.trim();
        try {
			i= Integer.valueOf(str).intValue();
		} catch (Exception e) {
			i= 0;
		}
        return i;
    }
    
    public static long strToLong(String str) {
    	long l = 0l;
        if (null == str || str.length() <= 0 || str.equals("null") || str.equals("NULL")) {
            return l;
        }
        str = str.trim();
        try {
			l= Long.valueOf(str).longValue();
		} catch (Exception e) {
			l= 0l;
		}
        return l;
    }
    
    public static String nullToStr(String str) {
    	 if (null == str || str.length() <= 0 || str.equals("null") || str.equals("NULL")) {
             return "";
         }
    	 return str;
    }
    
    /**
	 * 验证时间字符串格式输入是否正确:
	 * @param timeStr
	 * @return
	 */
	public static boolean isCheckDate(String timeStr, String pattern) {
		 if (null == timeStr || timeStr.length() <= 0 || timeStr.equals("null") || timeStr.equals("NULL")) {
	            return false;
		 }
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			simpleDateFormat.parse(timeStr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
    
    /**
     * 描述：
     * @author 牛凯凯(kk.niu@qq.com)
     * @Date 2018年5月15日
     * @param str 需要转date的字符串
     */
    public static Date StringToDate(String str, String pattern) {
    	try {
    		if(!isCheckDate(str, pattern)) {
    			return null;
    		}
    		return new SimpleDateFormat(pattern).parse(str);
		} catch (Exception e) {
			return null;
		}
	}
    
    /**
     * 描述：将Thu, 11 Oct 2018 10:51:23 +0000转换成date
     * @author 牛凯凯(kk.niu@qq.com)
     * @Date 2018年10月12日
     * @return the Date
     */
    public static Date transferTime(String time_str){
		Date date = new Date();
		try{
			String timeStr = time_str;
			timeStr = timeStr.toLowerCase();
			if(timeStr.indexOf(",")>=0){timeStr = timeStr.substring(timeStr.indexOf(",")+1);}
			if(timeStr.indexOf("+")>=0){timeStr = timeStr.substring(0,timeStr.indexOf("+"));}
			timeStr = timeStr.replace("jan","-01-").replace("feb","-02-").replace("mar","-03-").replace("apr","-04-").replace("may","-05-").replace("jun","-06-").replace("jul","-07-").replace("aug","-08-").replace("sep","-09-").replace("oct","-10-").replace("nov","-11-").replace("dec","-12-").replace(" ","").trim();
			for(int year=2008;year<=2100;year++){
				timeStr = timeStr.replace(""+year, ""+year+" ");
			}
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			try {
				date = df.parse(timeStr);
			} catch (ParseException e) {
				date = new Date();
			}
		}catch(Exception e){
			date = new Date();
		}
		
		return date;
	}
    
    /**
     * 描述：获取几天前的时间
     * @author 牛凯凯(kk.niu@qq.com)
     * @Date 2018年6月19日
     * @return the LocalDateTime
     */
    public static LocalDateTime strToOtherDate(String str, int amountToSubtract, ChronoUnit unit) {
    	if(null == str || str.isEmpty() || str.length()!=19) {
    		return null;
    	}
    	DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	LocalDateTime date = LocalDateTime.parse(str, f);
    	if(amountToSubtract > 0 && unit !=null) {
    		date = date.minus(amountToSubtract, unit);
    	}
    	return date;
	}
    
    /**
     * 描述：LocalDateTime时间转换成字符串
     * @author 牛凯凯(kk.niu@qq.com)
     * @Date 2018年6月19日
     * @return the String
     */
    public static String localDateTimeToStr(LocalDateTime date, String pattern) {
    	if(null == date) {
    		return null;
    	}
    	DateTimeFormatter f = DateTimeFormatter.ofPattern(pattern);
    	return date.format(f);
    }
    
    /**
     * 描述：格式化小数
     * @author 牛凯凯(kk.niu@qq.com)
     * @Date 2018年7月4日
     * @return the Double
     */
    public static Double formatDouble(Double rate, String pattern) {
    	if(rate==null) {
    		return 0D;
    	}
    	if(isNull(pattern)) {
    		pattern = "#0.00";
    	}
    	DecimalFormat df = new DecimalFormat(pattern);
		return strToDouble(df.format(rate));
	}
    
    /**
     * 描述：判断是否是空值
     * @author 牛凯凯(kk.niu@qq.com)
     * @Date 2018年7月2日
     * @return the boolean
     */
    public static boolean isNull(String str) {
        if (null == str || str.length() <= 0 || str.equals("null")) {
            return true;
        }
        return false;
    }
	public static boolean isNotNull(String str) {//判定是否为空值
		//      System.out.println("!!!");
		if (null == str || str.length() <= 0 || str.equals("null")) {
			return false;
		}
		return true;
	}
    public static String isCheckNull(String str) {//判定是否为空值
        //      System.out.println("!!!");
        if (null == str || str.length() <= 0 || str.equals("null") || str.equals("NULL")) {
            return "";
        }
        str = str.trim();
        return str;
    }
	public static Integer isCheckNull(Integer str) {//判定是否为空值
		if (null == str) {
			return 0;
		}
		return str;
	}
	public static BigDecimal isCheckNull(BigDecimal str) {//判定是否为空值
		if (null == str) {
			return BigDecimal.valueOf(0);
		}
		return str;
	}



	public static Double isCheckNull(Double str) {//判定是否为空值
		//      System.out.println("!!!");
		if (str == null) {
			str = 0D;
		}
		return str;
	}
    public static boolean isDigit(String Str) {//判断是否为数字
        for (int i = 0; i < Str.length(); i++) {
            if (!Character.isDigit(Str.charAt(i)))
                return false;
        }
        return true;
    }
    public static Integer DoubleToInt(Double d) {
    	if(d==null) {
    		return 0;
    	}
    	DecimalFormat format = new DecimalFormat("0");
    	String str = format.format(d);
    	if(str==null) {
    		return 0;
    	}
    	if(str.length()>=9) {
    		str = str.substring(0, 9);
    	}
		return strToInt(str);
	}
    
    public static boolean isCheckJson(String str) {
		try {
			JSONObject.parseObject(Util.nullToStr(str));
			return true;
		} catch (Exception e) {
			try {
				JSONArray.parseArray(Util.nullToStr(str));
				return true;
			} catch (Exception e2) {
				return false;
			}
		}
	}
    
    public static String sortStr(String str) {
    	String result = ",";
    	try{
    		str = str.replace("，", ",");
            while(str.indexOf(",,")>=0){
            	str = str.replace(",,", ",");
            }
            
            if(",".equals(str) == true)return ",";
            
            if(str.indexOf(",") == 0){
                str = str.substring(1);
            }
            
            if(str.lastIndexOf(",") == (str.length() -1)){
                str = str.substring(0,str.length()-1);
            }
            
            if(str.length() > 0){
                String[] strs = str.split(",");
                Arrays.sort(strs);
                for(int i = 0;i < strs.length;i++){
                    if(Util.isNull(strs[i]) == false){
                    	if(result.indexOf(","+strs[i]+",")<0){
                    		result = result + strs[i] +",";
                    	}else{
                    		;//已存在
                    	}
                    }
                }
            }
            if(result.length()>=100){result = result.substring(0,99);}//不超过99字节
    	}catch(Exception e){
    		;
    	}
    	result = ","+result+",";
    	result = result.replace(",,", ",");
        return result;
    }
    
    /**
	 * 
	 * 描述：克隆对象
	 * @author 牛凯凯(kk.niu@qq.com)
	 * @Date 2018年7月2日
	 * @return the Object
	 */
	public static Object objClone(Object object) {
		return JSONObject.parseObject(JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue), object.getClass());
	}
	
	/**
	 * 描述：克隆到其他对象
	 * @author 牛凯凯(kk.niu@qq.com)
	 * @Date 2018年7月2日
	 * @return the Object
	 */
	public static Object objCloneToOther(Object object,Class<?> c) {
		return JSONObject.parseObject(JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue), c);
	}
	
	public static List<?> arrCloneToOther(Object object,Class<?> c) {
		return JSONArray.parseArray(JSONArray.toJSONString(object, SerializerFeature.WriteMapNullValue), c);
	}
	
	/**
     * 比较两个java.util.Date的日期（年月日）是否相同（忽略时、分、秒）
     */
	public static boolean sameDate(Date d1, Date d2) {
    	try {
    		LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault()).toLocalDate();
            LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault()).toLocalDate();
            return localDate1.isEqual(localDate2);
    	}catch (Exception e) {
    		return false;
		}
    }
	
	/**
     * 比较两个java.util.Date的日期（年月日）大小（忽略时、分、秒）
     * d1小于d2 return true
     * d1大于d2 return false
     */
	public static boolean compareDate(Date d1, Date d2) {
    	try {
    		LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault()).toLocalDate();
            LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault()).toLocalDate();
            return localDate1.isBefore(localDate2);
    	}catch (Exception e) {
    		return false;
		}
    }
	
	public static void main(String[] args) {
		//System.out.println(DateToStr(getSpecifiedTime(-1, DateType.DATE), "yyyy-MM-dd HH:mm:ss"));
//		Date d1 = StringToDate("2018-07-22 12:12:13","yyyy-MM-dd HH:mm:ss");
//		Date d2 = StringToDate("2018-07-17 12:12:12","yyyy-MM-dd HH:mm:ss");
//		Date d3 = StringToDate("2018-07-27 12:12:12","yyyy-MM-dd HH:mm:ss");
//		Date d4 = StringToDate("2018-07-22 12:12:13","yyyy-MM-dd HH:mm:ss");
//		System.out.println(compareDate(d1, d2));
//		System.out.println(compareDate(d1, d3));
//		System.out.println(sameDate(d1, d4));
	}
	
	/**
	 * 获取指定区间内时间List
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @param pattern 时间格式
	 * @return list
	 */
	public static List<String> createTimeList(Date timeStart,Date timeEnd,String pattern){
		try {
			List<String> list = new ArrayList<String>();
			Calendar calBegin = Calendar.getInstance();
			calBegin.setTime(timeEnd);//
			while (timeStart.after(calBegin.getTime()))  {
				if(!list.contains(Util.DateToStr(calBegin.getTime(), pattern))) {
					// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
					list.add(Util.DateToStr(calBegin.getTime(), pattern));
				}
				calBegin.add(Calendar.DAY_OF_MONTH, 1);
			}
			String lastTime = Util.DateToStr(timeStart, pattern);
			if(!list.contains(lastTime)) {
				list.add(lastTime);
			}
			return list;
		}catch (Exception e) {
			return null;
		}
	}
    
	private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";// 中文正则
    public static String matcherChinese(String str) {
    	if(isNull(str)) {
    		return "";
    	}
        // 去除中文
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(str);
        return mat.replaceAll("");
    }
    
    private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7','8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    // MD5加密
	public static String getMD5(String source) {
		if (StringUtils.isEmpty(source))
			return "";
		try {
			byte[] strTemp = source.getBytes("UTF-8");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (byte byte0 : md) {
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			return "";
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	public static String getMD5Str(String str){
		String md5str = "";
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
			byte[] byteArray = messageDigest.digest();
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
			md5str = md5StrBuff.toString();
			//System.out.println(md5str);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("NoSuchAlgorithmException:"+e.getMessage());
			md5str = str;
		}catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("UnsupportedEncodingException:"+e.getMessage());
			md5str = str;
		}

		return md5str;
	}
	//获取当前时间如 20180917180000
	public static String getCtime() {
        Calendar dt = Calendar.getInstance();
        dt.setTime(new Date());
        String tmp = String.valueOf(dt.get(Calendar.YEAR));
        int m = dt.get(Calendar.MONTH) + 1;
        String mm = "";
        if (m < 10)
            mm += "0";
        mm += String.valueOf(m);

        int d = dt.get(Calendar.DAY_OF_MONTH);
        String dd = "";
        if (d < 10)
            dd += "0";
        dd += String.valueOf(d);

        int h = dt.get(Calendar.HOUR_OF_DAY);
        String hh = "";
        if (h < 10)
            hh += "0";
        hh += String.valueOf(h);

        int min = dt.get(Calendar.MINUTE);
        String minute = "";
        if (min < 10)
            minute += "0";
        minute += String.valueOf(min);

        int s = dt.get(Calendar.SECOND);
        String ss = "";
        if (s < 10)
            ss += "0";
        ss += String.valueOf(s);

        return tmp + mm + dd + hh + minute + ss;
    }
	public static String getCtime2() {//返回类似2008-3-13 15:51:23
		Calendar dt = Calendar.getInstance();
		dt.setTime(new Date());
		String tmp = String.valueOf(dt.get(Calendar.YEAR));
		int m = dt.get(Calendar.MONTH) + 1;
		String mm = "";
		mm += String.valueOf(m);

		int d = dt.get(Calendar.DAY_OF_MONTH);
		String dd = "";
		dd += String.valueOf(d);

		int h = dt.get(Calendar.HOUR_OF_DAY);
		String hh = "";
		hh += String.valueOf(h);

		int min = dt.get(Calendar.MINUTE);
		String minute = "";
		minute += String.valueOf(min);

		int s = dt.get(Calendar.SECOND);
		String ss = "";
		ss += String.valueOf(s);

		return tmp + "-" + mm + "-" + dd + " " + hh + ":" + minute + ":" + ss;
	}

	/**
	 * 描述：增加或减少时间
	 * @author 牛凯凯(kk.niu@qq.com)
	 * @Date 2018年10月12日
	 * @param time 需要增加或减少的时间
	 * @param amountToSubtract 需要增加或减少的数值
	 * @param amountToSubtract 时间增加或减少的单位,例如Calendar.DATE->天;Calendar.HOUR->小时
	 * @return the Date
	 */
	public static Date addAndReduceDate(Date time, int amountToSubtract, int calendarType) {
		if(time == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		
		calendar.add(calendarType, amountToSubtract);
		return calendar.getTime();
	}
	/**
	 * 描述：增加或减少时间
	 * @author 牛凯凯(kk.niu@qq.com)
	 * @Date 2018年10月12日
	 * @param time 需要增加或减少的时间
	 * @param amountToSubtract 需要增加或减少的数值
	 * @param amountToSubtract 时间增加或减少的单位,例如Calendar.DATE->天;Calendar.HOUR->小时
	 * @param pattern 需要转换字符串的格式,例如:yyyy-MM-dd HH:mm:ss
	 * @return the Date
	 */
	public static String addAndReduceDate(Date time, int amountToSubtract, int calendarType, String pattern) {
		if(time == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(calendarType, amountToSubtract);
		return DateToStr(calendar.getTime(), pattern);
	}
	
	/**
	 * 检测是否有emoji字符
	 * 
	 * @param source
	 * @return 一旦含有就抛出
	 */
	public static boolean containsEmoji(String source) {
		if (Util.isNull(source)) {
			return false;
		}
 
		int len = source.length();
 
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
 
			if (isEmojiCharacter(codePoint)) {
				// do nothing，判断到了这里表明，确认有表情字符
				return true;
			}
		}
 
		return false;
	}
 
	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}
	
	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
 
		if (!containsEmoji(source)) {
			return source;// 如果不包含，直接返回
		}
		// 到这里铁定包含
		StringBuilder buf = null;
 
		int len = source.length();
 
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
 
			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}
 
				buf.append(codePoint);
			} 
		}
 
		if (buf == null) {
			return source;// 如果没有找到 emoji表情，则返回源字符串
		} else {
			if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
	}
	
	public static String filterEmoji2(String source) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
					Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll("");
				return source;
			}
			return source;
		}
		return source;
	}
	//国家全称转简称
	public static String getCountryJC(String countrycode) {
		if("unitedkingdom".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GB";}
		if("palestine".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BL";}
		if("bosniaherzegovina".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BA";}
		if("czechrepublic".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CZ";}
		if("therepublicofcroatia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "HR";}
		if("guam".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GU";}
		if("maldives".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MDV";}
		if("maldives".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MV";}
		if("montenegro".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "ME";}
		if("unitedstates".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "US";}
		if("antilles".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AN";}
		if("macedonia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MK";}
		if("newcaledonia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "NC";}
		if("frenchpolynesia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "PF";}
		if("saintpierreandmiquelon".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "PM";}
		if("puertorico".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "PR";}
		if("reunion".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "RE";}
		if("serbia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "RS";}
		if("elsalvador".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SV";}
		if("trinidadandtobago".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "TT";}
		if("unitedarabemirates".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AE";}
		if("afghanistan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AF";}
		if("antiguaandbarbuda".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AG";}
		if("albania".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AL";}
		if("armenia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AM";}
		if("angola".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AO";}
		if("argentina".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AR";}
		if("austria".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AT";}
		if("andorra".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AD";}
		if("australia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AU";}
		if("azerbaijanrepublic".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "AZ";}
		if("barbados".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BB";}
		if("bangladesh".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BD";}
		if("belgium".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BE";}
		if("burkinafaso".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BF";}
		if("bulgaria".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BG";}
		if("bahrein".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BH";}
		if("burundi".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BI";}
		if("benin".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BJ";}
		if("bruneidarussalam".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BN";}
		if("bolivia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BO";}
		if("brazil".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BR";}
		if("bahamas".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BS";}
		if("botswana".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BW";}
		if("belarus".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BY";}
		if("belize".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "BZ";}
		if("canada".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CA";}
		if("centralafrica".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CF";}
		if("congo".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CG";}
		if("switzerland".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CH";}
		if("cotedivoire".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CI";}
		if("chile".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CL";}
		if("cameroon".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CM";}
		if("china".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CN";}
		if("colombia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CO";}
		if("costarica".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CR";}
		if("czechrepubic".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CS";}
		if("cuba".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CU";}
		if("capeverde".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CV";}
		if("cyprus".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "CY";}
		if("germany".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "DE";}
		if("denmark".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "DK";}
		if("dominicanrepublic".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "DO";}
		if("algeria".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "DZ";}
		if("ecuador".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "EC";}
		if("estonia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "EE";}
		if("egypt".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "EG";}
		if("spain".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "ES";}
		if("ethiopia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "ET";}
		if("finland".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "FI";}
		if("fiji".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "FJ";}
		if("faroeislands".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "FO";}
		if("faroe".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "FO";}
		if("france".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "FR";}
		if("gabon".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GA";}
		if("unitedkingdom".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GB";}
		if("grenada".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GD";}
		if("georgia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GE";}
		if("frenchguiana".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GF";}
		if("ghana".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GH";}
		if("gibraltar".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GI";}
		if("greenland".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GL";}
		if("guinea".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GN";}
		if("guadeloup".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GP";}
		if("greece".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GR";}
		if("guatemala".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "GT";}
		if("hongkong".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "HK";}
		if("honduras".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "HN";}
		if("hungary".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "HU";}
		if("indonesia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "ID";}
		if("ireland".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "IE";}
		if("israel".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "IL";}
		if("india".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "IN";}
		if("iraq".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "IQ";}
		if("iran".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "IR";}
		if("iceland".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "IS";}
		if("italy".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "IT";}
		if("jamaica".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "JM";}
		if("jordan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "JO";}
		if("japan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "JP";}
		if("kenya".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "KE";}
		if("kyrgyzstan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "KG";}
		if("kampucheacambodia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "KH";}
		if("saintkittsandnevis".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "KN";}
		if("republicofkorea".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "KR";}
		if("caymanislands".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "KY";}
		if("coteoivoire".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "KT";}
		if("kuwati".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "KW";}
		if("kazakhstan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "KZ";}
		if("laos".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "LA";}
		if("lebanon".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "LB";}
		if("saintlueia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "LC";}
		if("liechtenstein".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "LI";}
		if("srilanka".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "LK";}
		if("liberia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "LR";}
		if("lithuania".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "LT";}
		if("luxembourg".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "LU";}
		if("latvia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "LV";}
		if("libyan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "LY";}
		if("morocco".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MA";}
		if("monaco".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MC";}
		if("moldovarepublicof".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MD";}
		if("madagascar".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MG";}
		if("mali".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "ML";}
		if("burma".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MM";}
		if("mongolia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MN";}
		if("macau".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MO";}
		if("martiniqu".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MQ";}
		if("malta".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MT";}
		if("mauritius".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MU";}
		if("malawi".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MW";}
		if("mexico".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MX";}
		if("malaysia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MY";}
		if("mozambique".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "MZ";}
		if("namibia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "NA";}
		if("niger".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "NE";}
		if("nigeria".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "NG";}
		if("nicaragua".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "NI";}
		if("netherlands".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "NL";}
		if("norway".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "NO";}
		if("nepal".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "NP";}
		if("newzealand".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "NZ";}
		if("oman".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "OM";}
		if("panama".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "PA";}
		if("peru".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "PE";}
		if("papuanewguinea".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "PG";}
		if("philippines".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "PH";}
		if("pakistan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "PK";}
		if("poland".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "PL";}
		if("portugal".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "PT";}
		if("paraguay".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "PY";}
		if("qatar".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "QA";}
		if("romania".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "RO";}
		if("russianfederation".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "RU";}
		if("saudiarabia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SA";}
		if("seychelles".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SC";}
		if("sudan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SD";}
		if("sweden".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SE";}
		if("singapore".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SG";}
		if("slovenia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SI";}
		if("slovakia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SK";}
		if("sierraleone".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SL";}
		if("sanmarino".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SM";}
		if("senegal".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SN";}
		if("somalia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SO";}
		if("suriname".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SR";}
		if("syria".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SY";}
		if("swaziland".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "SZ";}
		if("turksandcaicosislands".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "TC";}
		if("chad".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "TD";}
		if("togo".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "TG";}
		if("thailand".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "TH";}
		if("tajikistan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "TJ";}
		if("turkmenistan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "TM";}
		if("tunisia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "TN";}
		if("turkey".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "TR";}
		if("taiwan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "TW";}
		if("tanzania".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "TZ";}
		if("ukraine".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "UA";}
		if("uganda".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "UG";}
		if("unitedstates".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "US";}
		if("uruguay".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "UY";}
		if("vatican".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "UA";}
		if("uzbekistan".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "UZ";}
		if("virginislands,u.s.".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "UI";}
		if("virginislands(u.s.)".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "UI";}
		if("venezuela".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "VE";}
		if("virginislands,british".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "VG";}
		if("vietnam".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "VN";}
		if("samoa".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "WS";}
		if("rwanda".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "RW";}
		if("yemen".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "YE";}
		if("yugoslavia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "YU";}
		if("southafrica".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "ZA";}
		if("zambia".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "ZM";}
		if("zaire".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "ZR";}
		if("zimbabwe".equals(countrycode.toLowerCase().replaceAll("\\s", "")) == true){return "ZW";}
		return  "NOT";
	}
	public static String getCountryCodeFromThreeCode(String countryname) {
    	if(Util.isNull(countryname) == true){return "";}
    	if(countryname != null && countryname.length()>0){countryname = countryname.toUpperCase();}//转换成小写,便于比较
    	if("AFG".equals(countryname) == true){return "AF";}
    	if("ALA".equals(countryname) == true){return "AX";}
    	if("ALB".equals(countryname) == true){return "AL";}
    	if("DZA".equals(countryname) == true){return "DZ";}
    	if("ASM".equals(countryname) == true){return "AS";}
    	if("AND".equals(countryname) == true){return "AD";}
    	if("AGO".equals(countryname) == true){return "AO";}
    	if("AIA".equals(countryname) == true){return "AI";}
    	if("ATA".equals(countryname) == true){return "AQ";}
    	if("ATG".equals(countryname) == true){return "AG";}
    	if("ARG".equals(countryname) == true){return "AR";}
    	if("ARM".equals(countryname) == true){return "AM";}
    	if("ABW".equals(countryname) == true){return "AW";}
    	if("AUS".equals(countryname) == true){return "AU";}
    	if("AUT".equals(countryname) == true){return "AT";}
    	if("AZE".equals(countryname) == true){return "AZ";}
    	if("BHS".equals(countryname) == true){return "BS";}
    	if("BHR".equals(countryname) == true){return "BH";}
    	if("BGD".equals(countryname) == true){return "BD";}
    	if("BRB".equals(countryname) == true){return "BB";}
    	if("BLR".equals(countryname) == true){return "BY";}
    	if("BEL".equals(countryname) == true){return "BE";}
    	if("BLZ".equals(countryname) == true){return "BZ";}
    	if("BEN".equals(countryname) == true){return "BJ";}
    	if("BMU".equals(countryname) == true){return "BM";}
    	if("BTN".equals(countryname) == true){return "BT";}
    	if("BOL".equals(countryname) == true){return "BO";}
    	if("BIH".equals(countryname) == true){return "BA";}
    	if("BWA".equals(countryname) == true){return "BW";}
    	if("BVT".equals(countryname) == true){return "BV";}
    	if("BRA".equals(countryname) == true){return "BR";}
    	if("IOT".equals(countryname) == true){return "IO";}
    	if("BRN".equals(countryname) == true){return "BN";}
    	if("BGR".equals(countryname) == true){return "BG";}
    	if("BFA".equals(countryname) == true){return "BF";}
    	if("BDI".equals(countryname) == true){return "BI";}
    	if("KHM".equals(countryname) == true){return "KH";}
    	if("CMR".equals(countryname) == true){return "CM";}
    	if("CAN".equals(countryname) == true){return "CA";}
    	if("CPV".equals(countryname) == true){return "CV";}
    	if("CYM".equals(countryname) == true){return "KY";}
    	if("CAF".equals(countryname) == true){return "CF";}
    	if("TCD".equals(countryname) == true){return "TD";}
    	if("CHL".equals(countryname) == true){return "CL";}
    	if("CHN".equals(countryname) == true){return "CN";}
    	if("CXR".equals(countryname) == true){return "CX";}
    	if("CCK".equals(countryname) == true){return "CC";}
    	if("COL".equals(countryname) == true){return "CO";}
    	if("COM".equals(countryname) == true){return "KM";}
    	if("COG".equals(countryname) == true){return "CG";}
    	if("COD".equals(countryname) == true){return "CD";}
    	if("COK".equals(countryname) == true){return "CK";}
    	if("CRI".equals(countryname) == true){return "CR";}
    	if("CIV".equals(countryname) == true){return "CI";}
    	if("HRV".equals(countryname) == true){return "HR";}
    	if("CUB".equals(countryname) == true){return "CU";}
    	if("CYP".equals(countryname) == true){return "CY";}
    	if("CZE".equals(countryname) == true){return "CZ";}
    	if("DNK".equals(countryname) == true){return "DK";}
    	if("DJI".equals(countryname) == true){return "DJ";}
    	if("DMA".equals(countryname) == true){return "DM";}
    	if("DOM".equals(countryname) == true){return "DO";}
    	if("ECU".equals(countryname) == true){return "EC";}
    	if("EGY".equals(countryname) == true){return "EG";}
    	if("SLV".equals(countryname) == true){return "SV";}
    	if("GNQ".equals(countryname) == true){return "GQ";}
    	if("ERI".equals(countryname) == true){return "ER";}
    	if("EST".equals(countryname) == true){return "EE";}
    	if("ETH".equals(countryname) == true){return "ET";}
    	if("FRO".equals(countryname) == true){return "FO";}
    	if("FLK".equals(countryname) == true){return "FK";}
    	if("FJI".equals(countryname) == true){return "FJ";}
    	if("FIN".equals(countryname) == true){return "FI";}
    	if("FRA".equals(countryname) == true){return "FR";}
    	if("GUF".equals(countryname) == true){return "GF";}
    	if("PYF".equals(countryname) == true){return "PF";}
    	if("ATF".equals(countryname) == true){return "TF";}
    	if("GAB".equals(countryname) == true){return "GA";}
    	if("GMB".equals(countryname) == true){return "GM";}
    	if("GEO".equals(countryname) == true){return "GE";}
    	if("DEU".equals(countryname) == true){return "DE";}
    	if("GHA".equals(countryname) == true){return "GH";}
    	if("GIB".equals(countryname) == true){return "GI";}
    	if("GBR".equals(countryname) == true){return "GB";}
    	if("GRC".equals(countryname) == true){return "GR";}
    	if("GRL".equals(countryname) == true){return "GL";}
    	if("GRD".equals(countryname) == true){return "GD";}
    	if("GLP".equals(countryname) == true){return "GP";}
    	if("GUM".equals(countryname) == true){return "GU";}
    	if("GTM".equals(countryname) == true){return "GT";}
    	if("GGY".equals(countryname) == true){return "GG";}
    	if("GIN".equals(countryname) == true){return "GN";}
    	if("GNB".equals(countryname) == true){return "GW";}
    	if("GUY".equals(countryname) == true){return "GY";}
    	if("HTI".equals(countryname) == true){return "HT";}
    	if("HMD".equals(countryname) == true){return "HM";}
    	if("HND".equals(countryname) == true){return "HN";}
    	if("HKG".equals(countryname) == true){return "HK";}
    	if("HUN".equals(countryname) == true){return "HU";}
    	if("ISL".equals(countryname) == true){return "IS";}
    	if("IND".equals(countryname) == true){return "IN";}
    	if("IDN".equals(countryname) == true){return "ID";}
    	if("IRN".equals(countryname) == true){return "IR";}
    	if("IRQ".equals(countryname) == true){return "IQ";}
    	if("IRL".equals(countryname) == true){return "IE";}
    	if("IMN".equals(countryname) == true){return "IM";}
    	if("ISR".equals(countryname) == true){return "IL";}
    	if("ITA".equals(countryname) == true){return "IT";}
    	if("JAM".equals(countryname) == true){return "JM";}
    	if("JPN".equals(countryname) == true){return "JP";}
    	if("JEY".equals(countryname) == true){return "JE";}
    	if("JOR".equals(countryname) == true){return "JO";}
    	if("KAZ".equals(countryname) == true){return "KZ";}
    	if("KEN".equals(countryname) == true){return "KE";}
    	if("KIR".equals(countryname) == true){return "KI";}
    	if("PRK".equals(countryname) == true){return "KP";}
    	if("KOR".equals(countryname) == true){return "KR";}
    	if("KWT".equals(countryname) == true){return "KW";}
    	if("KGZ".equals(countryname) == true){return "KG";}
    	if("LAO".equals(countryname) == true){return "LA";}
    	if("LVA".equals(countryname) == true){return "LV";}
    	if("LBN".equals(countryname) == true){return "LB";}
    	if("LSO".equals(countryname) == true){return "LS";}
    	if("LBR".equals(countryname) == true){return "LR";}
    	if("LBY".equals(countryname) == true){return "LY";}
    	if("LIE".equals(countryname) == true){return "LI";}
    	if("LTU".equals(countryname) == true){return "LT";}
    	if("LUX".equals(countryname) == true){return "LU";}
    	if("MAC".equals(countryname) == true){return "MO";}
    	if("MKD".equals(countryname) == true){return "MK";}
    	if("MDG".equals(countryname) == true){return "MG";}
    	if("MWI".equals(countryname) == true){return "MW";}
    	if("MYS".equals(countryname) == true){return "MY";}
    	if("MDV".equals(countryname) == true){return "MV";}
    	if("MLI".equals(countryname) == true){return "ML";}
    	if("MLT".equals(countryname) == true){return "MT";}
    	if("MHL".equals(countryname) == true){return "MH";}
    	if("MTQ".equals(countryname) == true){return "MQ";}
    	if("MRT".equals(countryname) == true){return "MR";}
    	if("MUS".equals(countryname) == true){return "MU";}
    	if("MYT".equals(countryname) == true){return "YT";}
    	if("MEX".equals(countryname) == true){return "MX";}
    	if("FSM".equals(countryname) == true){return "FM";}
    	if("MDA".equals(countryname) == true){return "MD";}
    	if("MCO".equals(countryname) == true){return "MC";}
    	if("MNG".equals(countryname) == true){return "MN";}
    	if("MNE".equals(countryname) == true){return "ME";}
    	if("MSR".equals(countryname) == true){return "MS";}
    	if("MAR".equals(countryname) == true){return "MA";}
    	if("MOZ".equals(countryname) == true){return "MZ";}
    	if("MMR".equals(countryname) == true){return "MM";}
    	if("NAM".equals(countryname) == true){return "NA";}
    	if("NRU".equals(countryname) == true){return "NR";}
    	if("NPL".equals(countryname) == true){return "NP";}
    	if("NLD".equals(countryname) == true){return "NL";}
    	if("ANT".equals(countryname) == true){return "AN";}
    	if("NCL".equals(countryname) == true){return "NC";}
    	if("NZL".equals(countryname) == true){return "NZ";}
    	if("NIC".equals(countryname) == true){return "NI";}
    	if("NER".equals(countryname) == true){return "NE";}
    	if("NGA".equals(countryname) == true){return "NG";}
    	if("NIU".equals(countryname) == true){return "NU";}
    	if("NFK".equals(countryname) == true){return "NF";}
    	if("MNP".equals(countryname) == true){return "MP";}
    	if("NOR".equals(countryname) == true){return "NO";}
    	if("OMN".equals(countryname) == true){return "OM";}
    	if("PAK".equals(countryname) == true){return "PK";}
    	if("PLW".equals(countryname) == true){return "PW";}
    	if("PSE".equals(countryname) == true){return "PS";}
    	if("PAN".equals(countryname) == true){return "PA";}
    	if("PNG".equals(countryname) == true){return "PG";}
    	if("PRY".equals(countryname) == true){return "PY";}
    	if("PER".equals(countryname) == true){return "PE";}
    	if("PHL".equals(countryname) == true){return "PH";}
    	if("PCN".equals(countryname) == true){return "PN";}
    	if("POL".equals(countryname) == true){return "PL";}
    	if("PRT".equals(countryname) == true){return "PT";}
    	if("PRI".equals(countryname) == true){return "PR";}
    	if("QAT".equals(countryname) == true){return "QA";}
    	if("REU".equals(countryname) == true){return "RE";}
    	if("ROU".equals(countryname) == true){return "RO";}
    	if("RUS".equals(countryname) == true){return "RU";}
    	if("RWA".equals(countryname) == true){return "RW";}
    	if("SHN".equals(countryname) == true){return "SH";}
    	if("KNA".equals(countryname) == true){return "KN";}
    	if("LCA".equals(countryname) == true){return "LC";}
    	if("SPM".equals(countryname) == true){return "PM";}
    	if("VCT".equals(countryname) == true){return "VC";}
    	if("WSM".equals(countryname) == true){return "WS";}
    	if("SMR".equals(countryname) == true){return "SM";}
    	if("STP".equals(countryname) == true){return "ST";}
    	if("SAU".equals(countryname) == true){return "SA";}
    	if("SEN".equals(countryname) == true){return "SN";}
    	if("SRB".equals(countryname) == true){return "RS";}
    	if("SYC".equals(countryname) == true){return "SC";}
    	if("SLE".equals(countryname) == true){return "SL";}
    	if("SGP".equals(countryname) == true){return "SG";}
    	if("SVK".equals(countryname) == true){return "SK";}
    	if("SVN".equals(countryname) == true){return "SI";}
    	if("SLB".equals(countryname) == true){return "SB";}
    	if("SOM".equals(countryname) == true){return "SO";}
    	if("ZAF".equals(countryname) == true){return "ZA";}
    	if("SGS".equals(countryname) == true){return "GS";}
    	if("ESP".equals(countryname) == true){return "ES";}
    	if("LKA".equals(countryname) == true){return "LK";}
    	if("SDN".equals(countryname) == true){return "SD";}
    	if("SUR".equals(countryname) == true){return "SR";}
    	if("SJM".equals(countryname) == true){return "SJ";}
    	if("SWZ".equals(countryname) == true){return "SZ";}
    	if("SWE".equals(countryname) == true){return "SE";}
    	if("CHE".equals(countryname) == true){return "CH";}
    	if("SYR".equals(countryname) == true){return "SY";}
    	if("TWN".equals(countryname) == true){return "TW";}
    	if("TJK".equals(countryname) == true){return "TJ";}
    	if("TZA".equals(countryname) == true){return "TZ";}
    	if("THA".equals(countryname) == true){return "TH";}
    	if("TLS".equals(countryname) == true){return "TL";}
    	if("TGO".equals(countryname) == true){return "TG";}
    	if("TKL".equals(countryname) == true){return "TK";}
    	if("TON".equals(countryname) == true){return "TO";}
    	if("TTO".equals(countryname) == true){return "TT";}
    	if("TUN".equals(countryname) == true){return "TN";}
    	if("TUR".equals(countryname) == true){return "TR";}
    	if("TKM".equals(countryname) == true){return "TM";}
    	if("TCA".equals(countryname) == true){return "TC";}
    	if("TUV".equals(countryname) == true){return "TV";}
    	if("UGA".equals(countryname) == true){return "UG";}
    	if("UKR".equals(countryname) == true){return "UA";}
    	if("ARE".equals(countryname) == true){return "AE";}
    	if("GBR".equals(countryname) == true){return "GB";}
    	if("USA".equals(countryname) == true){return "US";}
    	if("UMI".equals(countryname) == true){return "UM";}
    	if("URY".equals(countryname) == true){return "UY";}
    	if("UZB".equals(countryname) == true){return "UZ";}
    	if("VUT".equals(countryname) == true){return "VU";}
    	if("VAT".equals(countryname) == true){return "VA";}
    	if("VEN".equals(countryname) == true){return "VE";}
    	if("VNM".equals(countryname) == true){return "VN";}
    	if("VGB".equals(countryname) == true){return "VG";}
    	if("VIR".equals(countryname) == true){return "VI";}
    	if("WLF".equals(countryname) == true){return "WF";}
    	if("ESH".equals(countryname) == true){return "EH";}
    	if("YEM".equals(countryname) == true){return "YE";}
    	if("ZMB".equals(countryname) == true){return "ZM";}
    	if("ZWE".equals(countryname) == true){return "ZW";}
    	if(countryname.length()>=8){countryname = countryname.substring(0,8);}
        return countryname.toUpperCase();//如果实在不知道什么国家,就返回全称
    }
	//根据英文国家简称获取国家中文名
	public static String getCountryCN(String countrycode) {
		if(Util.isNull(countrycode) == true){countrycode = "";}
		if(countrycode != null && countrycode.length()>0){countrycode = countrycode.toUpperCase();}//转换成大写
		if("UK".equals(countrycode) == true){return "英国";}
		if("BA".equals(countrycode) == true){return "波黑";}
		if("CZ".equals(countrycode) == true){return "捷克";}
		if("CRO".equals(countrycode) == true){return "克罗地亚";}
		if("HR".equals(countrycode) == true){return "克罗地亚";}
		if("GU".equals(countrycode) == true){return "关岛";}
		if("MDV".equals(countrycode) == true){return "马尔代夫";}
		if("MV".equals(countrycode) == true){return "马尔代夫";}
		if("ME".equals(countrycode) == true){return "黑山共和国";}
		if("MNE".equals(countrycode) == true){return "黑山共和国";}
		if("AA".equals(countrycode) == true){return "美国";}
		if("AN".equals(countrycode) == true){return "安的列斯";}
		if("MK".equals(countrycode) == true){return "马其顿";}
		if("NC".equals(countrycode) == true){return "新喀里多尼亚";}
		if("PF".equals(countrycode) == true){return "波利尼西亚";}
		if("PM".equals(countrycode) == true){return "圣皮埃尔和密克隆";}
		if("PR".equals(countrycode) == true){return "波多黎各";}
		if("RE".equals(countrycode) == true){return "留尼旺岛";}
		if("RS".equals(countrycode) == true){return "塞尔维亚";}
		if("SV".equals(countrycode) == true){return "萨尔瓦多";}
		if("TT".equals(countrycode) == true){return "特立尼达和多巴哥";}
		if("AE".equals(countrycode) == true){return "阿联酋";}
		if("AF".equals(countrycode) == true){return "阿富汗";}
		if("AG".equals(countrycode) == true){return "安提瓜和巴布达";}
		if("AL".equals(countrycode) == true){return "阿尔巴尼亚";}
		if("AM".equals(countrycode) == true){return "亚美尼亚";}
		if("AO".equals(countrycode) == true){return "安哥拉";}
		if("AR".equals(countrycode) == true){return "阿根廷";}
		if("AT".equals(countrycode) == true){return "奥地利";}
		if("AU".equals(countrycode) == true){return "澳大利亚";}
		if("AZ".equals(countrycode) == true){return "阿塞拜疆";}
		if("BB".equals(countrycode) == true){return "巴巴多斯";}
		if("BD".equals(countrycode) == true){return "孟加拉";}
		if("BE".equals(countrycode) == true){return "比利时";}
		if("BF".equals(countrycode) == true){return "布基纳法索";}
		if("BG".equals(countrycode) == true){return "保加利亚";}
		if("BH".equals(countrycode) == true){return "巴林";}
		if("BI".equals(countrycode) == true){return "布隆迪";}
		if("BJ".equals(countrycode) == true){return "贝宁";}
		if("BL".equals(countrycode) == true){return "巴勒斯坦";}
		if("BN".equals(countrycode) == true){return "文莱";}
		if("BO".equals(countrycode) == true){return "玻利维亚";}
		if("BR".equals(countrycode) == true){return "巴西";}
		if("BS".equals(countrycode) == true){return "巴哈马";}
		if("BW".equals(countrycode) == true){return "博茨瓦纳";}
		if("BY".equals(countrycode) == true){return "白俄罗斯";}
		if("BZ".equals(countrycode) == true){return "伯利兹";}
		if("CA".equals(countrycode) == true){return "加拿大";}
		if("CF".equals(countrycode) == true){return "中非";}
		if("CG".equals(countrycode) == true){return "刚果";}
		if("CH".equals(countrycode) == true){return "瑞士";}
		if("CI".equals(countrycode) == true){return "科特迪瓦";}
		if("CL".equals(countrycode) == true){return "智利";}
		if("CM".equals(countrycode) == true){return "喀麦隆";}
		if("CN".equals(countrycode) == true){return "中国";}
		if("CO".equals(countrycode) == true){return "哥伦比亚";}
		if("CR".equals(countrycode) == true){return "哥斯达黎加";}
		if("CS".equals(countrycode) == true){return "捷克";}
		if("CU".equals(countrycode) == true){return "古巴";}
		if("CV".equals(countrycode) == true){return "佛得角";}
		if("CY".equals(countrycode) == true){return "塞浦路斯";}
		if("DE".equals(countrycode) == true){return "德国";}
		if("DK".equals(countrycode) == true){return "丹麦";}
		if("DO".equals(countrycode) == true){return "多米尼加共和国";}
		if("DZ".equals(countrycode) == true){return "阿尔及利亚";}
		if("EC".equals(countrycode) == true){return "厄瓜多尔";}
		if("EE".equals(countrycode) == true){return "爱沙尼亚";}
		if("EG".equals(countrycode) == true){return "埃及";}
		if("ES".equals(countrycode) == true){return "西班牙";}
		if("ET".equals(countrycode) == true){return "埃塞俄比亚";}
		if("FI".equals(countrycode) == true){return "芬兰";}
		if("FJ".equals(countrycode) == true){return "斐济";}
		if("FO".equals(countrycode) == true){return "法罗群岛";}
		if("FR".equals(countrycode) == true){return "法国";}
		if("GA".equals(countrycode) == true){return "加蓬";}
		if("GB".equals(countrycode) == true){return "英国";}
		if("GD".equals(countrycode) == true){return "格林纳达";}
		if("GE".equals(countrycode) == true){return "格鲁吉亚";}
		if("GF".equals(countrycode) == true){return "法属圭亚那";}
		if("GH".equals(countrycode) == true){return "加纳";}
		if("GI".equals(countrycode) == true){return "直布罗陀";}
		if("GL".equals(countrycode) == true){return "格陵兰";}
		if("GN".equals(countrycode) == true){return "几内亚";}
		if("GP".equals(countrycode) == true){return "瓜德罗普";}
		if("GR".equals(countrycode) == true){return "希腊";}
		if("GT".equals(countrycode) == true){return "危地马拉";}
		if("HK".equals(countrycode) == true){return "香港特别行政区";}
		if("HN".equals(countrycode) == true){return "洪都拉斯";}
		if("HU".equals(countrycode) == true){return "匈牙利";}
		if("ID".equals(countrycode) == true){return "印度尼西亚";}
		if("IE".equals(countrycode) == true){return "爱尔兰";}
		if("IL".equals(countrycode) == true){return "以色列";}
		if("IN".equals(countrycode) == true){return "印度";}
		if("IQ".equals(countrycode) == true){return "伊拉克";}
		if("IR".equals(countrycode) == true){return "伊朗";}
		if("IS".equals(countrycode) == true){return "冰岛";}
		if("IT".equals(countrycode) == true){return "意大利";}
		if("JM".equals(countrycode) == true){return "牙买加";}
		if("JO".equals(countrycode) == true){return "约旦";}
		if("JP".equals(countrycode) == true){return "日本";}
		if("KE".equals(countrycode) == true){return "肯尼亚";}
		if("KG".equals(countrycode) == true){return "吉尔吉斯坦";}
		if("KH".equals(countrycode) == true){return "柬埔寨";}
		if("KN".equals(countrycode) == true){return "圣基茨和尼维斯";}
		if("KR".equals(countrycode) == true){return "韩国";}
		if("KY".equals(countrycode) == true){return "开曼群岛";}
		if("KT".equals(countrycode) == true){return "科特迪瓦共和国";}
		if("KW".equals(countrycode) == true){return "科威特";}
		if("KZ".equals(countrycode) == true){return "哈萨克";}
		if("LA".equals(countrycode) == true){return "老挝";}
		if("LB".equals(countrycode) == true){return "黎巴嫩";}
		if("LC".equals(countrycode) == true){return "圣卢西亚";}
		if("LI".equals(countrycode) == true){return "列支敦士登";}
		if("LK".equals(countrycode) == true){return "斯里兰卡";}
		if("LR".equals(countrycode) == true){return "利比里亚";}
		if("LT".equals(countrycode) == true){return "立陶宛";}
		if("LU".equals(countrycode) == true){return "卢森堡";}
		if("LV".equals(countrycode) == true){return "拉脱维亚";}
		if("LY".equals(countrycode) == true){return "利比亚";}
		if("MA".equals(countrycode) == true){return "摩洛哥";}
		if("MC".equals(countrycode) == true){return "摩纳哥";}
		if("MD".equals(countrycode) == true){return "摩尔多瓦";}
		if("MG".equals(countrycode) == true){return "马达加斯加";}
		if("ML".equals(countrycode) == true){return "马里";}
		if("MM".equals(countrycode) == true){return "缅甸";}
		if("MN".equals(countrycode) == true){return "蒙古";}
		if("MO".equals(countrycode) == true){return "澳门地区";}
		if("MQ".equals(countrycode) == true){return "马提尼克";}
		if("MT".equals(countrycode) == true){return "马耳他";}
		if("MU".equals(countrycode) == true){return "毛里求斯";}
		if("MW".equals(countrycode) == true){return "马拉维";}
		if("MX".equals(countrycode) == true){return "墨西哥";}
		if("MY".equals(countrycode) == true){return "马来西亚";}
		if("MZ".equals(countrycode) == true){return "莫桑比克";}
		if("NA".equals(countrycode) == true){return "纳米比亚";}
		if("NE".equals(countrycode) == true){return "尼日尔";}
		if("NG".equals(countrycode) == true){return "尼日利亚";}
		if("NI".equals(countrycode) == true){return "尼加拉瓜";}
		if("NL".equals(countrycode) == true){return "荷兰";}
		if("NO".equals(countrycode) == true){return "挪威";}
		if("NP".equals(countrycode) == true){return "尼泊尔";}
		if("NZ".equals(countrycode) == true){return "新西兰";}
		if("OM".equals(countrycode) == true){return "阿曼";}
		if("PA".equals(countrycode) == true){return "巴拿马";}
		if("PE".equals(countrycode) == true){return "秘鲁";}
		if("PG".equals(countrycode) == true){return "巴布亚新几内亚";}
		if("PH".equals(countrycode) == true){return "菲律宾";}
		if("PK".equals(countrycode) == true){return "巴基斯坦";}
		if("PL".equals(countrycode) == true){return "波兰";}
		if("PT".equals(countrycode) == true){return "葡萄牙";}
		if("PY".equals(countrycode) == true){return "巴拉圭";}
		if("QA".equals(countrycode) == true){return "卡塔尔";}
		if("RO".equals(countrycode) == true){return "罗马尼亚";}
		if("RU".equals(countrycode) == true){return "俄罗斯";}
		if("SA".equals(countrycode) == true){return "沙特阿拉伯";}
		if("SC".equals(countrycode) == true){return "塞舌尔";}
		if("SD".equals(countrycode) == true){return "苏丹";}
		if("SE".equals(countrycode) == true){return "瑞典";}
		if("SG".equals(countrycode) == true){return "新加坡";}
		if("SI".equals(countrycode) == true){return "斯洛文尼亚";}
		if("SK".equals(countrycode) == true){return "斯洛伐克";}
		if("SL".equals(countrycode) == true){return "塞拉利昂";}
		if("SM".equals(countrycode) == true){return "圣马力诺";}
		if("SN".equals(countrycode) == true){return "塞内加尔";}
		if("SO".equals(countrycode) == true){return "索马里";}
		if("SR".equals(countrycode) == true){return "苏里南";}
		if("SRB".equals(countrycode) == true){return "塞尔维亚";}
		if("SY".equals(countrycode) == true){return "叙利亚";}
		if("SZ".equals(countrycode) == true){return "斯威士兰";}
		if("TC".equals(countrycode) == true){return "特克斯和凯科斯群岛";}
		if("TD".equals(countrycode) == true){return "乍得";}
		if("TG".equals(countrycode) == true){return "多哥";}
		if("TH".equals(countrycode) == true){return "泰国";}
		if("TJ".equals(countrycode) == true){return "塔吉克斯坦";}
		if("TM".equals(countrycode) == true){return "土库曼";}
		if("TN".equals(countrycode) == true){return "突尼斯";}
		if("TR".equals(countrycode) == true){return "土耳其";}
		if("TW".equals(countrycode) == true){return "台湾省";}
		if("TZ".equals(countrycode) == true){return "坦桑尼亚";}
		if("UA".equals(countrycode) == true){return "乌克兰";}
		if("UG".equals(countrycode) == true){return "乌干达";}
		if("US".equals(countrycode) == true){return "美国";}
		if("UY".equals(countrycode) == true){return "乌拉圭";}
		if("UA".equals(countrycode) == true){return "梵蒂冈";}
		if("UZ".equals(countrycode) == true){return "乌兹别克";}
		if("UI".equals(countrycode) == true){return "美属维尔京群岛";}
		if("VE".equals(countrycode) == true){return "委内瑞拉";}
		if("VG".equals(countrycode) == true){return "英属维尔京群岛";}
		if("VN".equals(countrycode) == true){return "越南";}
		if("WS".equals(countrycode) == true){return "萨摩亚";}
		if("YE".equals(countrycode) == true){return "也门";}
		if("YU".equals(countrycode) == true){return "南斯拉夫联盟";}
		if("ZA".equals(countrycode) == true){return "南非";}
		if("ZM".equals(countrycode) == true){return "赞比亚";}
		if("ZR".equals(countrycode) == true){return "扎伊尔";}
		if("ZW".equals(countrycode) == true){return "津巴布韦";}

		return "未知国家";
	}

	//根据英文国家简称,获取英文国家全称
	public static String getCountryEN(String countrycode) {
		if(Util.isNull(countrycode) == true){countrycode = "";}
		if(countrycode != null && countrycode.length()>0){countrycode = countrycode.toUpperCase();}//转换成大写
		if("UK".equals(countrycode) == true){return "united kingdom";}
		if("BL".equals(countrycode) == true){return "palestine";}
		if("BA".equals(countrycode) == true){return "bosnia herzegovina";}
		if("CZ".equals(countrycode) == true){return "czech republic";}
		if("CRO".equals(countrycode) == true){return "the republic of croatia";}
		if("HR".equals(countrycode) == true){return "the republic of croatia";}
		if("GU".equals(countrycode) == true){return "guam";}
		if("MDV".equals(countrycode) == true){return "maldives";}
		if("MV".equals(countrycode) == true){return "maldives";}
		if("ME".equals(countrycode) == true){return "montenegro";}
		if("MNE".equals(countrycode) == true){return "montenegro";}
		if("AA".equals(countrycode) == true){return "united states";}
		if("AN".equals(countrycode) == true){return "antilles";}
		if("MK".equals(countrycode) == true){return "macedonia";}
		if("NC".equals(countrycode) == true){return "new caledonia";}
		if("PF".equals(countrycode) == true){return "french polynesia";}
		if("PM".equals(countrycode) == true){return "saint pierre and miquelon";}
		if("PR".equals(countrycode) == true){return "puerto rico";}
		if("RE".equals(countrycode) == true){return "reunion";}
		if("RS".equals(countrycode) == true){return "serbia";}
		if("SV".equals(countrycode) == true){return "el salvador";}
		if("TT".equals(countrycode) == true){return "trinidad and tobago";}
		if("AE".equals(countrycode) == true){return "united arab emirates";}
		if("AF".equals(countrycode) == true){return "afghanistan";}
		if("AG".equals(countrycode) == true){return "antigua and barbuda";}
		if("AL".equals(countrycode) == true){return "albania";}
		if("AM".equals(countrycode) == true){return "armenia";}
		if("AO".equals(countrycode) == true){return "angola";}
		if("AR".equals(countrycode) == true){return "argentina";}
		if("AT".equals(countrycode) == true){return "austria";}
		if("AU".equals(countrycode) == true){return "australia";}
		if("AZ".equals(countrycode) == true){return "azerbaijan republic";}
		if("BB".equals(countrycode) == true){return "barbados";}
		if("BD".equals(countrycode) == true){return "bangladesh";}
		if("BE".equals(countrycode) == true){return "belgium";}
		if("BF".equals(countrycode) == true){return "burkinafaso";}
		if("BG".equals(countrycode) == true){return "bulgaria";}
		if("BH".equals(countrycode) == true){return "bahrein";}
		if("BI".equals(countrycode) == true){return "burundi";}
		if("BJ".equals(countrycode) == true){return "benin";}
		if("BN".equals(countrycode) == true){return "bruneidarussalam";}
		if("BO".equals(countrycode) == true){return "bolivia";}
		if("BR".equals(countrycode) == true){return "brazil";}
		if("BS".equals(countrycode) == true){return "bahamas";}
		if("BW".equals(countrycode) == true){return "botswana";}
		if("BY".equals(countrycode) == true){return "belarus";}
		if("BZ".equals(countrycode) == true){return "belize";}
		if("CA".equals(countrycode) == true){return "canada";}
		if("CF".equals(countrycode) == true){return "centralafrica";}
		if("CG".equals(countrycode) == true){return "congo";}
		if("CH".equals(countrycode) == true){return "switzerland";}
		if("CI".equals(countrycode) == true){return "cote divoire";}
		if("CL".equals(countrycode) == true){return "chile";}
		if("CM".equals(countrycode) == true){return "cameroon";}
		if("CN".equals(countrycode) == true){return "china";}
		if("CO".equals(countrycode) == true){return "colombia";}
		if("CR".equals(countrycode) == true){return "costarica";}
		if("CS".equals(countrycode) == true){return "czechrepubic";}
		if("CU".equals(countrycode) == true){return "cuba";}
		if("CV".equals(countrycode) == true){return "cape verde";}
		if("CY".equals(countrycode) == true){return "cyprus";}
		if("DE".equals(countrycode) == true){return "germany";}
		if("DK".equals(countrycode) == true){return "denmark";}
		if("DO".equals(countrycode) == true){return "dominicanrepublic";}
		if("DZ".equals(countrycode) == true){return "algeria";}
		if("EC".equals(countrycode) == true){return "ecuador";}
		if("EE".equals(countrycode) == true){return "estonia";}
		if("EG".equals(countrycode) == true){return "egypt";}
		if("ES".equals(countrycode) == true){return "spain";}
		if("ET".equals(countrycode) == true){return "ethiopia";}
		if("FI".equals(countrycode) == true){return "finland";}
		if("FJ".equals(countrycode) == true){return "fiji";}
		if("FO".equals(countrycode) == true){return "faroe islands";}
		if("FR".equals(countrycode) == true){return "france";}
		if("GA".equals(countrycode) == true){return "gabon";}
		if("GB".equals(countrycode) == true){return "united kingdom";}
		if("GD".equals(countrycode) == true){return "grenada";}
		if("GE".equals(countrycode) == true){return "georgia";}
		if("GF".equals(countrycode) == true){return "french guiana";}
		if("GH".equals(countrycode) == true){return "ghana";}
		if("GI".equals(countrycode) == true){return "gibraltar";}
		if("GL".equals(countrycode) == true){return "greenland";}
		if("GN".equals(countrycode) == true){return "guinea";}
		if("GP".equals(countrycode) == true){return "guadeloup";}
		if("GR".equals(countrycode) == true){return "greece";}
		if("GT".equals(countrycode) == true){return "guatemala";}
		if("HK".equals(countrycode) == true){return "hongkong";}
		if("HN".equals(countrycode) == true){return "honduras";}
		if("HU".equals(countrycode) == true){return "hungary";}
		if("ID".equals(countrycode) == true){return "indonesia";}
		if("IE".equals(countrycode) == true){return "ireland";}
		if("IL".equals(countrycode) == true){return "israel";}
		if("IN".equals(countrycode) == true){return "india";}
		if("IQ".equals(countrycode) == true){return "iraq";}
		if("IR".equals(countrycode) == true){return "iran";}
		if("IS".equals(countrycode) == true){return "iceland";}
		if("IT".equals(countrycode) == true){return "italy";}
		if("JM".equals(countrycode) == true){return "jamaica";}
		if("JO".equals(countrycode) == true){return "jordan";}
		if("JP".equals(countrycode) == true){return "japan";}
		if("KE".equals(countrycode) == true){return "kenya";}
		if("KG".equals(countrycode) == true){return "kyrgyzstan";}
		if("KH".equals(countrycode) == true){return "kampuchea cambodia";}
		if("KN".equals(countrycode) == true){return "saint kitts and nevis";}
		if("KR".equals(countrycode) == true){return "republicofkorea";}
		if("KY".equals(countrycode) == true){return "cayman islands";}
		if("KT".equals(countrycode) == true){return "coteo ivoire";}
		if("KW".equals(countrycode) == true){return "kuwati";}
		if("KZ".equals(countrycode) == true){return "kazakhstan";}
		if("LA".equals(countrycode) == true){return "laos";}
		if("LB".equals(countrycode) == true){return "lebanon";}
		if("LC".equals(countrycode) == true){return "saintlueia";}
		if("LI".equals(countrycode) == true){return "liechtenstein";}
		if("LK".equals(countrycode) == true){return "srilanka";}
		if("LR".equals(countrycode) == true){return "liberia";}
		if("LT".equals(countrycode) == true){return "lithuania";}
		if("LU".equals(countrycode) == true){return "luxembourg";}
		if("LV".equals(countrycode) == true){return "latvia";}
		if("LY".equals(countrycode) == true){return "libyan";}
		if("MA".equals(countrycode) == true){return "morocco";}
		if("MC".equals(countrycode) == true){return "monaco";}
		if("MD".equals(countrycode) == true){return "moldova republicof";}
		if("MG".equals(countrycode) == true){return "madagascar";}
		if("ML".equals(countrycode) == true){return "mali";}
		if("MM".equals(countrycode) == true){return "burma";}
		if("MN".equals(countrycode) == true){return "mongolia";}
		if("MO".equals(countrycode) == true){return "macau";}
		if("MQ".equals(countrycode) == true){return "martiniqu";}
		if("MT".equals(countrycode) == true){return "malta";}
		if("MU".equals(countrycode) == true){return "mauritius";}
		if("MW".equals(countrycode) == true){return "malawi";}
		if("MX".equals(countrycode) == true){return "mexico";}
		if("MY".equals(countrycode) == true){return "malaysia";}
		if("MZ".equals(countrycode) == true){return "mozambique";}
		if("NA".equals(countrycode) == true){return "namibia";}
		if("NE".equals(countrycode) == true){return "niger";}
		if("NG".equals(countrycode) == true){return "nigeria";}
		if("NI".equals(countrycode) == true){return "nicaragua";}
		if("NL".equals(countrycode) == true){return "netherlands";}
		if("NO".equals(countrycode) == true){return "norway";}
		if("NP".equals(countrycode) == true){return "nepal";}
		if("NZ".equals(countrycode) == true){return "newzealand";}
		if("OM".equals(countrycode) == true){return "oman";}
		if("PA".equals(countrycode) == true){return "panama";}
		if("PE".equals(countrycode) == true){return "peru";}
		if("PG".equals(countrycode) == true){return "papuanewguinea";}
		if("PH".equals(countrycode) == true){return "philippines";}
		if("PK".equals(countrycode) == true){return "pakistan";}
		if("PL".equals(countrycode) == true){return "poland";}
		if("PT".equals(countrycode) == true){return "portugal";}
		if("PY".equals(countrycode) == true){return "paraguay";}
		if("QA".equals(countrycode) == true){return "qatar";}
		if("RO".equals(countrycode) == true){return "romania";}
		if("RU".equals(countrycode) == true){return "russian federation";}
		if("SA".equals(countrycode) == true){return "saudiarabia";}
		if("SC".equals(countrycode) == true){return "seychelles";}
		if("SD".equals(countrycode) == true){return "sudan";}
		if("SE".equals(countrycode) == true){return "sweden";}
		if("SG".equals(countrycode) == true){return "singapore";}
		if("SI".equals(countrycode) == true){return "slovenia";}
		if("SK".equals(countrycode) == true){return "slovakia";}
		if("SL".equals(countrycode) == true){return "sierra leone";}
		if("SM".equals(countrycode) == true){return "sanmarino";}
		if("SN".equals(countrycode) == true){return "senegal";}
		if("SO".equals(countrycode) == true){return "somalia";}
		if("SR".equals(countrycode) == true){return "suriname";}
		if("SRB".equals(countrycode) == true){return "serbia";}
		if("SY".equals(countrycode) == true){return "syria";}
		if("SZ".equals(countrycode) == true){return "swaziland";}
		if("TC".equals(countrycode) == true){return "turks and caicos islands";}
		if("TD".equals(countrycode) == true){return "chad";}
		if("TG".equals(countrycode) == true){return "togo";}
		if("TH".equals(countrycode) == true){return "thailand";}
		if("TJ".equals(countrycode) == true){return "tajikistan";}
		if("TM".equals(countrycode) == true){return "turkmenistan";}
		if("TN".equals(countrycode) == true){return "tunisia";}
		if("TR".equals(countrycode) == true){return "turkey";}
		if("TW".equals(countrycode) == true){return "taiwan";}
		if("TZ".equals(countrycode) == true){return "tanzania";}
		if("UA".equals(countrycode) == true){return "ukraine";}
		if("UG".equals(countrycode) == true){return "uganda";}
		if("US".equals(countrycode) == true){return "united states";}
		if("UY".equals(countrycode) == true){return "uruguay";}
		if("UA".equals(countrycode) == true){return "vatican";}
		if("UZ".equals(countrycode) == true){return "uzbekistan";}
		if("UI".equals(countrycode) == true){return "virgin islands,U.S.";}
		if("VE".equals(countrycode) == true){return "venezuela";}
		if("VG".equals(countrycode) == true){return "virgin islands, british";}
		if("VN".equals(countrycode) == true){return "vietnam";}
		if("WS".equals(countrycode) == true){return "samoa";}
		if("YE".equals(countrycode) == true){return "yemen";}
		if("YU".equals(countrycode) == true){return "yugoslavia";}
		if("ZA".equals(countrycode) == true){return "southafrica";}
		if("ZM".equals(countrycode) == true){return "zambia";}
		if("ZR".equals(countrycode) == true){return "zaire";}
		if("ZW".equals(countrycode) == true){return "zimbabwe";}
		return countrycode;//如果实在不知道什么国家,就返回缩写
	}
	@SuppressWarnings("deprecation")
	public static Date getTimeBeforeMinite(Date date,int diffmin){//获得diffmin分钟前的yyyymmddhhmmss
        if(diffmin <= 0){diffmin = 0;}
        Date stime = new Date();
        if(date != null){
        	stime.setTime(date.getTime());
        }
        stime.setMinutes(stime.getMinutes()-diffmin);//提前diffmin分钟
        return stime;
    }
	public static Date stringToDate(String StringDate) {
        if (StringDate == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = df.parse(StringDate);
        } catch (ParseException e) {
            ;
        }
        return d;
    }
	 public static String getDateLate(int days) {//获取当前日期days后的日期
        Calendar dt = Calendar.getInstance();
        dt.setTime(new Date());
        dt.add(Calendar.DAY_OF_MONTH, days);
        String tmp = String.valueOf(dt.get(Calendar.YEAR));
        int m = dt.get(Calendar.MONTH) + 1;
        String mm = "";
        if (m < 10)
            mm += "0";
        mm += String.valueOf(m);

        int d = dt.get(Calendar.DAY_OF_MONTH);
        String dd = "";
        if (d < 10)
            dd += "0";
        dd += String.valueOf(d);

        return tmp + mm + dd;
    }
}
