package org.baifei.common.util;


import org.apache.http.*;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于进行Https请求的HttpClient 
 * @ClassName: SSLClient 
 * @Description: TODO
 * @author Devin <xxx> 
 * @date 2017年2月7日 下午1:42:07 
 *  
 */
@Component
public class SSLClient {
	private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;
    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2","SSLv2Hello"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(1000);//max connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * httpClient post请求
     * @param url 请求url
     * @param header 头部信息
     * @param param 请求参数 form提交适用
     * @param entity 请求实体 json/xml提交适用
     * @return 可能为空 需要处理
     * @throws Exception
     *
     */
    public ResultCode doPost(String  url, Map<String, Object> header, Map<String, Object> param, HttpEntity entity, Integer timeout) {
    	ResultCode rt = new ResultCode();
        CloseableHttpClient httpClient = null;
        try {
            httpClient = getHttpClient(1000*60*3);
            HttpPost httpPost = new HttpPost(url);
            // 设置过期时间
 			if(timeout == null || timeout.intValue()<=0){
 				timeout = 120*1000;
 			}
			httpPost.setConfig(RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build());
            // 设置头信息
            if (null!=header && header.size()>0) {
                for (Map.Entry<String, Object> entry : header.entrySet()) {
                    httpPost.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
            // 设置请求参数
            if (null!=param && param.size()>0) {
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    //给参数赋值
                    formparams.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
                httpPost.setEntity(urlEncodedFormEntity);
            }
            // 设置实体 优先级高
            if (entity != null) {
                httpPost.setEntity(entity);
            }
			//开始请求
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = httpResponse.getEntity();
                rt.setAck(0);
                rt.setMsg("调取成功!");
                rt.setData(EntityUtils.toString(resEntity));
            } else if(statusCode == HttpStatus.SC_NOT_FOUND) {
            	HttpEntity resEntity = httpResponse.getEntity();
            	rt.setAck(999);
                rt.setMsg("请求链接不存在(404),请检查!");
                rt.setData(EntityUtils.toString(resEntity));
            } else if(statusCode == HttpStatus.SC_BAD_REQUEST){
            	HttpEntity resEntity = httpResponse.getEntity();
            	rt.setAck(999);
            	if(resEntity != null){
            		rt.setMsg(EntityUtils.toString(resEntity));
            	}else {
            		rt.setMsg("请求错误400");
            	}
            }else{
            	rt.setAck(statusCode);
                rt.setMsg(readHttpResponse(httpResponse));
            }
        } catch (Exception e) {
        	rt.setAck(1000);
            rt.setMsg(e.getMessage());
        } finally {
            if (httpClient != null) {
            	closeHttpClient(0, httpClient);
            }
        }
        return rt;
    }
    
    /**
     * httpClient post请求
     * @param url 请求url
     * @param header 头部信息
     * @param param 请求参数 form提交适用
     * @param entity 请求实体 json/xml提交适用
     * @return 可能为空 需要处理
     * @throws Exception
     *
     */
    public ResultCode doPut(String  url, Map<String, Object> header, Map<String, Object> param, HttpEntity entity, Integer timeout) {
    	ResultCode rt = new ResultCode();
        CloseableHttpClient httpClient = null;
        try {
            httpClient = getHttpClient(1000*60*30);
            HttpPut httpPost = new HttpPut(url);
            // 设置过期时间
 			if(timeout == null || timeout.intValue()<=0){
 				timeout = 120*1000;
 			}
			httpPost.setConfig(RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build());
            // 设置头信息
            if (null!=header && header.size()>0) {
                for (Map.Entry<String, Object> entry : header.entrySet()) {
                    httpPost.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
            // 设置请求参数
            if (null!=param && param.size()>0) {
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    //给参数赋值
                    formparams.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
                httpPost.setEntity(urlEncodedFormEntity);
            }
            // 设置实体 优先级高
            if (entity != null) {
                httpPost.setEntity(entity);
            }
			//开始请求
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = httpResponse.getEntity();
                rt.setAck(0);
                rt.setMsg("调取成功!");
                rt.setData(EntityUtils.toString(resEntity));
            } else {
            	rt.setAck(999);
                rt.setMsg(readHttpResponse(httpResponse));
            }
        } catch (Exception e) {
        	rt.setAck(1000);
            rt.setMsg(e.getMessage());
        } finally {
            if (httpClient != null) {
            	closeHttpClient(0, httpClient);
            }
        }
        return rt;
    }
    
    public ResultCode httpURLConnectionPut(String url, Map<String, Object> header,String postEntity,int timeout) {
		ResultCode rt = new ResultCode();
		InputStream inputStream = null;
		BufferedReader br = null;
		HttpURLConnection  conn = null;
		try {
			URL u = new URL(url);
			if ("https".equalsIgnoreCase(u.getProtocol())) {
				SslUtils.ignoreSsl();
			}
			conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(timeout);//设置连接时间为5秒
			conn.setReadTimeout(timeout);//设置读取时间为5秒
			conn.setRequestMethod("PUT");//设置请求方式为put
			conn.setDoOutput(true);
			conn.setDoInput(true);
            
			conn.setRequestProperty("Content-Length","0");
			if(header!=null) {
	    		for (Map.Entry<String, Object> entry : header.entrySet()) {
	    			conn.setRequestProperty(entry.getKey(), entry.getValue()==null ? null : entry.getValue().toString());
				}
	    	}
			OutputStream  out = conn.getOutputStream();   
            out.write(postEntity.getBytes("utf-8"));  
            out.close();  
			inputStream = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(inputStream)); 
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			if(conn.getResponseCode() != 200) {
				rt.setAck(conn.getResponseCode());
	            rt.setMsg(stringBuffer.toString());
	            return rt;
			}
			if (Util.isNull(stringBuffer.toString())) {
				rt.setAck(1000);
				rt.setMsg("返回值为空!");
				return rt;
			}
			rt.setAck(0);
			rt.setMsg("调取成功!");
			rt.setData(stringBuffer.toString());
			stringBuffer.setLength(0);
		} catch (IOException e) {
			rt.setAck(1000);
			rt.setMsg(e.getMessage());
		} catch (Exception e) {
			rt.setAck(1000);
			rt.setMsg(e.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return rt;
	}
    
    public ResultCode HttpPut(String postUrl,Map<String, Object> postHeaders,String postEntity,int timeout) throws Exception {  
    	ResultCode rt = new ResultCode();
        URL postURL = new URL(postUrl);
        if ("https".equalsIgnoreCase(postURL.getProtocol())) {
			SslUtils.ignoreSsl();
		}
        HttpURLConnection httpURLConnection = (HttpURLConnection) postURL.openConnection();
        httpURLConnection.setConnectTimeout(timeout);
        httpURLConnection.setReadTimeout(timeout);
        httpURLConnection.setDoOutput(true);                 
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        StringBuilder sbStr = new StringBuilder();
        if(postHeaders != null) {
            for(String pKey : postHeaders.keySet()) {
            	if(postHeaders.get(pKey)!=null) {
            		httpURLConnection.setRequestProperty(pKey, postHeaders.get(pKey).toString());
            	}
            }
        }
        if(postEntity != null) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(),"utf-8"));   
            out.println(postEntity);  
            out.close();  
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection  
                    .getInputStream()));  
              
            String inputLine;
            while ((inputLine = in.readLine()) != null) {  
                sbStr.append(inputLine);  
            }  
            in.close(); 
            if(httpURLConnection.getResponseCode() == 200) {
            	rt.setAck(0);
                rt.setData(new String(sbStr.toString().getBytes(),"utf-8"));
            }else {
            	rt.setAck(httpURLConnection.getResponseCode());
                rt.setMsg(new String(sbStr.toString().getBytes(),"utf-8"));
            }
        }else {
        	rt.setAck(1000);
        	rt.setMsg("参数不能为空");
        }
        httpURLConnection.disconnect();
        return rt;
    }
    
    /**
     * httpClient get请求
     * @param url 请求url
     * @param header 头部信息
     * @param entity 请求实体 json/xml提交适用
     * @return 可能为空 需要处理
     * @throws Exception
     *
     */
    public ResultCode doGet(String  url, Map<String, Object> header, Integer timeout, HttpEntity entity) {
    	ResultCode rt = new ResultCode();
        CloseableHttpClient httpClient = null;
        try {
            httpClient = getHttpClient(1000*60*5);
            HttpGet httpGet = new HttpGet(url);
            // 设置头信息
            if (null!=header && header.size()>0) {
                for (Map.Entry<String, Object> entry : header.entrySet()) {
                	httpGet.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
            // 设置过期时间
 			if(timeout == null || timeout.intValue()<=0){
 				timeout = 2 * 60 * 1000;
 			}
			httpGet.setConfig(RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build());
			//开始请求
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = httpResponse.getEntity();
                rt.setAck(0);
                rt.setMsg("调取成功!");
                rt.setData(EntityUtils.toString(resEntity));
            } else {
            	rt.setAck(statusCode);
                rt.setMsg(readHttpResponse(httpResponse));
            }
        } catch (Exception e) {
        	rt.setAck(1000);
            rt.setMsg(e.getMessage());
        } finally {
            if (httpClient != null) {
            	closeHttpClient(0, httpClient);
            }
        }
        return rt;
    }
    
	public ResultCode httpURLConnectionGet(String url, Map<String, Object> header,int timeout) {
		ResultCode rt = new ResultCode();
		InputStream inputStream = null;
		BufferedReader br = null;
		HttpURLConnection  conn = null;
		try {
			URL u = new URL(url);
			if ("https".equalsIgnoreCase(u.getProtocol())) {
				SslUtils.ignoreSsl();
			}
			conn = (HttpURLConnection) u.openConnection();
			 conn.setRequestProperty("accept", "*/*"); 
	            conn.setRequestProperty("connection", "Keep-Alive"); 
	            conn.setRequestProperty("user-agent", 
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
			conn.setConnectTimeout(timeout);//设置连接时间为5秒
			conn.setReadTimeout(timeout);//设置读取时间为5秒
			conn.setRequestMethod("GET");//设置请求方式为get
			conn.setDoOutput(true);
			conn.setDoInput(true);
            
			if(header!=null) {
	    		for (Map.Entry<String, Object> entry : header.entrySet()) {
	    			conn.setRequestProperty(entry.getKey(), entry.getValue()==null ? null : entry.getValue().toString());
				}
	    	}
			inputStream = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			if(conn.getResponseCode() != 200) {
				rt.setAck(conn.getResponseCode());
	            rt.setMsg(stringBuffer.toString());
	            return rt;
			}
			if (Util.isNull(stringBuffer.toString())) {
				rt.setAck(1000);
				rt.setMsg("返回值为空!");
				return rt;
			}
			rt.setAck(0);
			rt.setMsg("调取成功!");
			rt.setData(stringBuffer.toString());
			stringBuffer.setLength(0);
		} catch (IOException e) {
			rt.setAck(1000);
			rt.setMsg(e.getMessage());
		} catch (Exception e) {
			rt.setAck(1000);
			rt.setMsg(e.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return rt;
	}
	
	public ResultCode httpURLConnectionGet_https(String url, Map<String, Object> header,int timeout) {
		ResultCode rt = new ResultCode();
		InputStream inputStream = null;
		BufferedReader br = null;
		HttpURLConnection  conn = null;
		try {
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setConnectTimeout(timeout);// 设置连接时间为5秒
			conn.setReadTimeout(timeout);// 设置读取时间为5秒
			conn.setRequestMethod("GET");// 设置请求方式为get
			conn.setDoOutput(true);
			conn.setDoInput(true);
            
			if(header!=null) {
	    		for (Map.Entry<String, Object> entry : header.entrySet()) {
	    			conn.setRequestProperty(entry.getKey(), entry.getValue()==null ? null : entry.getValue().toString());
				}
	    	}
			inputStream = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			if(conn.getResponseCode() != 200) {
				rt.setAck(conn.getResponseCode());
	            rt.setMsg(stringBuffer.toString());
	            return rt;
			}
			if (Util.isNull(stringBuffer.toString())) {
				rt.setAck(1000);
				rt.setMsg("返回值为空!");
				return rt;
			}
			rt.setAck(0);
			rt.setMsg("调取成功!");
			rt.setData(stringBuffer.toString());
			stringBuffer.setLength(0);
		} catch (IOException e) {
			rt.setAck(1000);
			rt.setMsg(e.getMessage());
		} catch (Exception e) {
			rt.setAck(1000);
			rt.setMsg(e.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return rt;
	}
	
	public ResultCode httpURLConnectionPost(String url, Map<String, Object> header, Map<String, Object> param, String requestJson,int timeout) {
		ResultCode rt = new ResultCode();
		InputStream inputStream = null;
		BufferedReader br = null;
		HttpURLConnection  conn = null;
		try {
			URL u = new URL(url);
//			if ("https".equalsIgnoreCase(u.getProtocol())) {
//				SslUtils.ignoreSsl();
//			}
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setConnectTimeout(timeout);//设置连接时间为5秒
			conn.setReadTimeout(timeout);//设置读取时间为5秒
			conn.setRequestMethod("POST");//设置请求方式为POST
			conn.setDoOutput(true);
			conn.setDoInput(true);
            
			if(header!=null) {
	    		for (Map.Entry<String, Object> entry : header.entrySet()) {
	    			conn.setRequestProperty(entry.getKey(), entry.getValue()==null ? null : entry.getValue().toString());
				}
	    	}
			 //添加参数
//			OutputStream outputStream = conn.getOutputStream();
	        OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			if (param != null) {
				StringBuffer sb = new StringBuffer();
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					conn.setRequestProperty(entry.getKey(),
							entry.getValue() == null ? null : entry.getValue().toString());
					sb.append(entry.getKey());
					sb.append("=");
					sb.append(entry.getValue().toString());
				}
				osw.write(sb.toString());// 上传参数
			}
			if (requestJson != null && requestJson.length() > 0) {
				osw.write(requestJson);// 上传参数
			}
			inputStream = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			if(conn.getResponseCode() != 200) {
				rt.setAck(conn.getResponseCode());
	            rt.setMsg(stringBuffer.toString());
	            return rt;
			}
			if (Util.isNull(stringBuffer.toString())) {
				rt.setAck(1000);
				rt.setMsg("返回值为空!");
				return rt;
			}
			rt.setAck(0);
			rt.setMsg("调取成功!");
			rt.setData(stringBuffer.toString());
			stringBuffer.setLength(0);
		} catch (IOException e) {
			rt.setAck(1000);
			rt.setMsg(e.getMessage());
		} catch (Exception e) {
			rt.setAck(1000);
			rt.setMsg(e.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return rt;
	}

    public ResultCode doGetHttpClient(String url, Map<String, Object> header,int timeout) {
    	ResultCode rt = new ResultCode();
    	HttpClient httpClient = new HttpClient();
    	InputStream inputStream = null;
    	BufferedReader br = null;
		try {
	    	httpClient.getParams().setSoTimeout(timeout);
	    	httpClient.getParams().setConnectionManagerTimeout(timeout);
	    	httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
	    	HttpMethod method = new GetMethod(url);
	    	method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
	    	        new DefaultHttpMethodRetryHandler(1,false)); //如果失败,重试2次

			if(header!=null) {
	    		for (Map.Entry<String, Object> entry : header.entrySet()) {
	    			method.addRequestHeader(entry.getKey(), entry.getValue()==null ? null : entry.getValue().toString());
				}
	    	}

			if(url.indexOf("https:") >= 0) {
	    		 Protocol myhttps = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);//支持https
	             Protocol.registerProtocol("https", myhttps);
	    	}
			int httpCode = httpClient.executeMethod(method);
			if(httpCode == 204) {
				rt.setAck(204);
	            rt.setMsg("返回http状态码是204");
	            return rt;
			}
			inputStream = method.getResponseBodyAsStream();
			br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			if(httpCode != 200) {
				rt.setAck(1000);
	            rt.setMsg(stringBuffer.toString());
	            return rt;
			}
			if(Util.isNull(stringBuffer.toString())) {
				rt.setAck(1000);
	            rt.setMsg("返回值为空!");
	            return rt;
			}
			rt.setAck(0);
			rt.setMsg("调取成功!");
			rt.setData(stringBuffer.toString());
			stringBuffer.setLength(0);
		} catch (IOException e) {
			rt.setAck(1000);
            rt.setMsg(e.getMessage());
		} catch (Exception e) {
			rt.setAck(1000);
            rt.setMsg(e.getMessage());
		}finally {
			if(inputStream!=null) {
				try { inputStream.close(); } catch (IOException e) {}
			}
			if(br !=null) {
				try { br.close(); } catch (IOException e) { }
			}
		}
    	return rt;
    }

    /**
     * 描述：这个方法调接口失败不会重试
     * @author 牛凯凯(kk.niu@qq.com)
     * @Date 2019年2月26日
     * @return the ResultCode
     */
    public ResultCode doGetHttpClientNoHandler(String url, Map<String, Object> header,int timeout) {
    	ResultCode rt = new ResultCode();
    	HttpClient httpClient = new HttpClient();
    	InputStream inputStream = null;
    	BufferedReader br = null;
		try {
	    	httpClient.getParams().setSoTimeout(timeout);
	    	httpClient.getParams().setConnectionManagerTimeout(timeout);
	    	httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
	    	HttpMethod method = new GetMethod(url);
//	    	method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//	    	        new DefaultHttpMethodRetryHandler(1,false)); //如果失败,重试2次
			if(header!=null) {
	    		for (Map.Entry<String, Object> entry : header.entrySet()) {
	    			method.addRequestHeader(entry.getKey(), entry.getValue()==null ? null : entry.getValue().toString());
				}
	    	}

			if(url.indexOf("https:") >= 0) {
	    		 Protocol myhttps = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);//支持https
	             Protocol.registerProtocol("https", myhttps);
	    	}
			int httpCode = httpClient.executeMethod(method);
			if(httpCode == 204) {
				rt.setAck(204);
	            rt.setMsg("返回http状态码是204");
	            return rt;
			}
			inputStream = method.getResponseBodyAsStream();
			br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			if(httpCode != 200) {
				rt.setAck(1000);
	            rt.setMsg(stringBuffer.toString());
	            return rt;
			}
			if(Util.isNull(stringBuffer.toString())) {
				rt.setAck(1000);
	            rt.setMsg("返回值为空!");
	            return rt;
			}
			rt.setAck(0);
			rt.setMsg("调取成功!");
			rt.setData(stringBuffer.toString());
			stringBuffer.setLength(0);
		} catch (IOException e) {
			rt.setAck(1000);
            rt.setMsg(e.getMessage());
		} catch (Exception e) {
			rt.setAck(1000);
            rt.setMsg(e.getMessage());
		}finally {
			if(inputStream!=null) {
				try { inputStream.close(); } catch (IOException e) {}
			}
			if(br !=null) {
				try { br.close(); } catch (IOException e) { }
			}
		}
    	return rt;
    }


    public ResultCode doPostHttpClient(String  url, Map<String, Object> header, Map<String, Object> param,String requestJson,int timeout) {
    	ResultCode rt = new ResultCode();
    	HttpClient httpClient = new HttpClient();
    	InputStream inputStream = null;
    	BufferedReader br = null;
		try {
			httpClient.getParams().setSoTimeout(timeout);
	    	httpClient.getParams().setConnectionManagerTimeout(timeout);
	    	httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
	    	PostMethod method = new PostMethod(url);
	    	method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
	    	        new DefaultHttpMethodRetryHandler(3,false)); //如果失败,重试2次
	    	if(url.indexOf("https:") >= 0) {
	    		Protocol myhttps = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);//支持https
	    		Protocol.registerProtocol("https", myhttps);
	    	}
			if(header!=null) {
	    		for (Map.Entry<String, Object> entry : header.entrySet()) {
	    			method.addRequestHeader(entry.getKey(), entry.getValue()==null ? null : entry.getValue().toString());
				}
	    	}
	    	if(param!=null) {
	    		org.apache.commons.httpclient.NameValuePair[] parametersBody = new org.apache.commons.httpclient.NameValuePair[param.size()];
	    		int i = 0;
	    		for (Map.Entry<String, Object> entry : param.entrySet()) {
	    			org.apache.commons.httpclient.NameValuePair nameValuePair =
	    					new org.apache.commons.httpclient.NameValuePair(entry.getKey(),entry.getValue()==null ? null : entry.getValue().toString());
	    			parametersBody[i] = nameValuePair;
	    			i++;
				}
				method.setRequestBody(parametersBody);
	    	}
	    	if(requestJson!=null) {
	    		RequestEntity requestEntity = new StringRequestEntity(requestJson, "application/json", "utf-8");
				method.setRequestEntity(requestEntity );
	    	}
			int httpCode = httpClient.executeMethod(method);
			if(httpCode == 204) {
				rt.setAck(204);
	            rt.setMsg("返回http状态码是204");
	            return rt;
			}
			inputStream = method.getResponseBodyAsStream();
			br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			if(httpCode != HttpStatus.SC_OK) {
				rt.setAck(httpCode);
	            rt.setMsg(stringBuffer.toString());
	            return rt;
			}
			if(Util.isNull(stringBuffer.toString())) {
				rt.setAck(1000);
	            rt.setMsg("返回值为空!");
	            return rt;
			}
			rt.setAck(0);
			rt.setMsg("调取成功!");
			rt.setData(stringBuffer.toString());
			stringBuffer.setLength(0);
		} catch (IOException e) {
			rt.setAck(1000);
            rt.setMsg(e.getMessage());
		} catch (Exception e) {
			rt.setAck(1000);
            rt.setMsg(e.getMessage());
		}finally {
			if(inputStream!=null) {
				try { inputStream.close(); } catch (IOException e) {}
			}
			if(br !=null) {
				try { br.close(); } catch (IOException e) { }
			}
		}
    	return rt;
    }
    
    public void closeHttpClient(int i, CloseableHttpClient httpClient) {
    	if(i > 20) {
    		return ;
    	}
    	try {
			httpClient.close();
		} catch (IOException e) {
			try {Thread.sleep(1000);} catch (InterruptedException e1) {}
			i++;
			System.out.println(i);
			closeHttpClient(i, httpClient);
		}
    }
    
    public CloseableHttpClient getHttpClient(Integer timeout){
    	if(null == timeout || timeout.intValue()<=0) timeout = 1000 * 60 * 3;
    	SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(timeout).build();
    	 RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .setConnectionManagerShared(true)
                .setDefaultSocketConfig(socketConfig)
                .setDefaultRequestConfig(defaultConfig)
                .build();
        return httpClient;
    }
    public String readHttpResponse(HttpResponse httpResponse)
            throws ParseException, IOException {
        StringBuilder builder = new StringBuilder();
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        //builder.append("status:" + httpResponse.getStatusLine());
        //builder.append("headers:");
//        HeaderIterator iterator = httpResponse.headerIterator();
//        while (iterator.hasNext()) {
//            builder.append(iterator.next());
//        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            builder.append(responseString);
        }
        return builder.toString();
    }
    
    public static void main(String[] args) {
    	System.out.println(System.getProperty("proxyType"));
    	System.out.println(System.getProperty("proxyPort"));
    	System.out.println(System.getProperty("proxyHost"));
    	System.out.println(System.getProperty("proxySet"));
    	
		//http://api.racoon.stride.one/oauth/token/
//    	SSLClient sslClient = new SSLClient();
//    	Map<String, Object> param = new HashMap<String, Object>();
//    	param.put("grant_type", "password");
//    	param.put("username", "email");
//    	param.put("password", "password");
//    	ResultCode resultCode = sslClient.doPost("http://api.racoon.stride.one/oauth/token/", null, param , null, null);
//    	System.out.println(resultCode.getMsg());
    	
    	
	}
}