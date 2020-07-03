package org.baifei.modules.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;

@Component
public class RestTemplateUtil {
	@Autowired
	private RestTemplate restTemplate;

	public <T> ResulstCodeWeb<T> getDataObjForPost(Class<T> clazz,Object obj,String lang,String url){
		ResulstCodeWeb<T> rc = new ResulstCodeWeb<>();
		HttpHeaders requestHeaders = new HttpHeaders();  
		if(!StringUtils.isEmpty(lang)) {
			requestHeaders.add("lang", lang); 
		}
        requestHeaders.add("Content-type", "application/json;charset=utf-8"); 
        JSONObject jo = new JSONObject();
        try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
		    for (PropertyDescriptor property : propertyDescriptors) {    
		       String key = property.getName();    
		       if (key.compareToIgnoreCase("class") == 0) {   
		           continue;  
		       }  
		       Method getter = property.getReadMethod();  
		       Object value = getter!=null ? getter.invoke(obj) : null;  
		       if(value != null) {
		    	   jo.put(key, value);
		       }
		    } 
		    HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(jo,requestHeaders);
		    rc = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ResulstCodeWeb<T>>() {}).getBody();
		    if(rc.getData() != null) {
		    	rc.setData(JSONObject.parseObject(JSONObject.toJSONString(rc.getData()), clazz));
		    }
		} catch (IntrospectionException e) {
			rc.setAck(1000);
			rc.setMsg("IntrospectionException:"+e.getMessage());
		} catch (IllegalAccessException e) {
			rc.setAck(1000);
			rc.setMsg("IllegalAccessException:"+e.getMessage());
		} catch (IllegalArgumentException e) {
			rc.setAck(1000);
			rc.setMsg("IllegalArgumentException:"+e.getMessage());
		} catch (InvocationTargetException e) {
			rc.setAck(1000);
			rc.setMsg("InvocationTargetException:"+e.getMessage());
		} catch (Exception e) {
			rc.setAck(1000);
			rc.setMsg("Exception:"+e.getMessage());
		}    
	    
		return rc;
	}
	
	public <E> ResulstCodeWeb<List<E>> getDataListForPost(Class<E> clazz,Object obj,String lang,String url){
		ResulstCodeWeb<List<E>> rc = new ResulstCodeWeb<>();
		try {
        	HttpHeaders requestHeaders = new HttpHeaders();  
        	if(!StringUtils.isEmpty(lang)) {
        		requestHeaders.add("lang", lang); 
        	}
        	requestHeaders.add("Content-type", "application/json;charset=utf-8"); 
        	JSONObject jo = new JSONObject();
        	
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
		    for (PropertyDescriptor property : propertyDescriptors) {    
		       String key = property.getName();    
		       if (key.compareToIgnoreCase("class") == 0) {   
		           continue;  
		       }  
		       Method getter = property.getReadMethod();  
		       Object value = getter!=null ? getter.invoke(obj) : null;  
		       if(value != null) {
		    	   jo.put(key, value);
		       }
		    } 
		    HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(jo,requestHeaders);
		    rc = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ResulstCodeWeb<List<E>>>() {}).getBody();
		    if(rc.getData() != null) {
		    	rc.setData(JSONArray.parseArray(JSONArray.toJSONString(rc.getData()), clazz));
		    }
		} catch (IntrospectionException e) {
			rc.setAck(1000);
			rc.setMsg("IntrospectionException:"+e.getMessage());
		} catch (IllegalAccessException e) {
			rc.setAck(1000);
			rc.setMsg("IllegalAccessException:"+e.getMessage());
		} catch (IllegalArgumentException e) {
			rc.setAck(1000);
			rc.setMsg("IllegalArgumentException:"+e.getMessage());
		} catch (InvocationTargetException e) {
			rc.setAck(1000);
			rc.setMsg("InvocationTargetException:"+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			rc.setAck(1000);
			rc.setMsg("Exception:"+e.getMessage());
		}    
	    
		return rc;
	}
	
	public <T> ResulstCodeWeb<T> getDataObjForPostERP(Class<T> clazz,Object obj,String lang,String url){
		ResulstCodeWeb<T> rc = new ResulstCodeWeb<>();
//		HttpHeaders requestHeaders = new HttpHeaders();  
//		if(!StringUtils.isEmpty(lang)) {
//			requestHeaders.add("lang", lang); 
//		}
//        requestHeaders.add("Content-type", "application/json;charset=utf-8"); 
        JSONObject jo = new JSONObject();
        try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
		    for (PropertyDescriptor property : propertyDescriptors) {    
		       String key = property.getName();    
		       if (key.compareToIgnoreCase("class") == 0) {   
		           continue;  
		       }  
		       Method getter = property.getReadMethod();  
		       Object value = getter!=null ? getter.invoke(obj) : null;  
		       if(value != null) {
		    	   jo.put(key, value);
		       }
		    } 
		    HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(jo);
		    rc = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ResulstCodeWeb<T>>() {}).getBody();
		    if(rc.getData() != null) {
		    	rc.setData(JSONObject.parseObject(JSONObject.toJSONString(rc.getData()), clazz));
		    }
		} catch (IntrospectionException e) {
			rc.setAck(1000);
			rc.setMsg("IntrospectionException:"+e.getMessage());
		} catch (IllegalAccessException e) {
			rc.setAck(1000);
			rc.setMsg("IllegalAccessException:"+e.getMessage());
		} catch (IllegalArgumentException e) {
			rc.setAck(1000);
			rc.setMsg("IllegalArgumentException:"+e.getMessage());
		} catch (InvocationTargetException e) {
			rc.setAck(1000);
			rc.setMsg("InvocationTargetException:"+e.getMessage());
		} catch (Exception e) {
			rc.setAck(1000);
			rc.setMsg("Exception:"+e.getMessage());
		}    
	    
		return rc;
	}
	
	public <E> ResulstCodeWeb<List<E>> getDataListForPostERP(Class<E> clazz,Object obj,String lang,String url){
		ResulstCodeWeb<List<E>> rc = new ResulstCodeWeb<>();
		try {
//        	HttpHeaders requestHeaders = new HttpHeaders();  
//        	if(!StringUtils.isEmpty(lang)) {
//        		requestHeaders.add("lang", lang); 
//        	}
//        	requestHeaders.add("Content-type", "application/json;charset=utf-8"); 
        	JSONObject jo = new JSONObject();
        	
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
		    for (PropertyDescriptor property : propertyDescriptors) {    
		       String key = property.getName();    
		       if (key.compareToIgnoreCase("class") == 0) {   
		           continue;  
		       }  
		       Method getter = property.getReadMethod();  
		       Object value = getter!=null ? getter.invoke(obj) : null;  
		       if(value != null) {
		    	   jo.put(key, value);
		       }
		    } 
		    HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(jo);
		    rc = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ResulstCodeWeb<List<E>>>() {}).getBody();
		    if(rc.getData() != null) {
		    	rc.setData(JSONArray.parseArray(JSONArray.toJSONString(rc.getData()), clazz));
		    }
		} catch (IntrospectionException e) {
			rc.setAck(1000);
			rc.setMsg("IntrospectionException:"+e.getMessage());
		} catch (IllegalAccessException e) {
			rc.setAck(1000);
			rc.setMsg("IllegalAccessException:"+e.getMessage());
		} catch (IllegalArgumentException e) {
			rc.setAck(1000);
			rc.setMsg("IllegalArgumentException:"+e.getMessage());
		} catch (InvocationTargetException e) {
			rc.setAck(1000);
			rc.setMsg("InvocationTargetException:"+e.getMessage());
		} catch (Exception e) {
			rc.setAck(1000);
			rc.setMsg("Exception:"+e.getMessage());
		}    
	    
		return rc;
	}
}
