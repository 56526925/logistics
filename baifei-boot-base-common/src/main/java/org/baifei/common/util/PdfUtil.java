package org.baifei.common.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Map;

import static org.baifei.common.util.ConstantConfig.PDF_ABSOLUTE_PATH;


@Component
public class PdfUtil {


    public static ResultCode doGetHttpClient(String url, Map<String, Object> header,int timeout,String PDF_ABSOLUTE_PATH,String platformOrderId) {
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
            //inputStream = method.getResponseBodyAsStream();
            //br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String str = "";
//            while ((str = br.readLine()) != null) {
//                stringBuffer.append(str);
//            }
//            if(httpCode != 200) {
//                rt.setAck(1000);
//                rt.setMsg(stringBuffer.toString());
//                return rt;
//            }
//            if(Util.isNull(stringBuffer.toString())) {
//                rt.setAck(1000);
//                rt.setMsg("返回值为空!");
//                return rt;
//            }



            if(httpCode== 200){
                FileOutputStream fos = null;
                try {
                    //判断目录存在自动创建
                    if(new File(PDF_ABSOLUTE_PATH).isDirectory()==false){
                        new File(PDF_ABSOLUTE_PATH).mkdirs();
                    }
                } catch (Exception e) {
                    rt.setAck(1000);
                    rt.setMsg("标签目录创建Exception异常:"+e.getMessage());
                    return rt;
                }
                File file = new File(PDF_ABSOLUTE_PATH +File.separator+"label"+File.separator+"pdf"+File.separator+platformOrderId+".pdf");
                inputStream = method.getResponseBodyAsStream();
                try {
                    fos = new FileOutputStream(file);
                    byte[] buff= readInputStream(inputStream);
                    fos.write(buff);
                    fos.close();
                    inputStream.close();
                } catch (Exception e) {
                    rt.setAck(1000);
                    rt.setMsg("写出标签出现Exception异常"+e.getMessage());
                    System.out.println(e.getMessage());
                }

                if((file.length()/1024)<1){
                    rt.setAck(444);
                    rt.setMsg("PDF文件大小为"+file.length()/1024+"KB");
                }else{
                    ResultCode ro = new ResultCode();
                    String Path = ConstantConfig.PDF_URL_PATH +File.separator+"pdf"+File.separator+"pdf"+File.separator+platformOrderId+".pdf";
                    rt.setAck(0);
                    rt.setMsg("调取成功!");
                    rt.setData(Path);//标签路径
                }
                if((file.length()/1024)<5){
                    rt.setAck(444);
                    rt.setMsg("PDF文件大小为"+file.length()/1024+"KB");
                }else{
                    ;
                }
            }else {
                rt.setAck(1000);
                rt.setMsg("接口返回错误,错误代码："+httpCode);
                System.out.println("获取标签");
            }

//            rt.setAck(0);
//            rt.setMsg("调取成功!");
//            rt.setData(stringBuffer.toString());
//            stringBuffer.setLength(0);
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

    public static ResultCode doPostHttpClient(String  url, Map<String, Object> header, Map<String, Object> param, String requestJson, int timeout,String PDF_ABSOLUTE_PATH,String platformOrderId) {
        ResultCode rt = new ResultCode();
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setSoTimeout(timeout);
        httpClient.getParams().setConnectionManagerTimeout(timeout);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        PostMethod method = new PostMethod(url);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(2,false)); //如果失败,重试2次
        if(url.indexOf("https:") >= 0) {
            Protocol myhttps = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);//支持https
            Protocol.registerProtocol("https", myhttps);
        }
        InputStream inputStream = null;
        BufferedReader br = null;
        try {
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
            String resp= method.getResponseBodyAsString();
            if(httpCode != 200) {
                rt.setAck(1000);
                rt.setMsg("返回http状态码是："+httpCode);
                return rt;
            }

            if(httpCode== 200){
                FileOutputStream fos = null;
                try {
                    //判断目录存在自动创建
                    if(new File(PDF_ABSOLUTE_PATH).isDirectory()==false){
                        new File(PDF_ABSOLUTE_PATH).mkdirs();
                    }
                } catch (Exception e) {
                    rt.setAck(1000);
                    rt.setMsg("标签目录创建Exception异常:"+e.getMessage());
                    return rt;
                }
                File file = new File(PDF_ABSOLUTE_PATH +File.separator+"label"+File.separator+"pdf"+File.separator+platformOrderId+".pdf");
                inputStream = method.getResponseBodyAsStream();
                try {
                    fos = new FileOutputStream(file);
                    byte[] buff= readInputStream(inputStream);
                    fos.write(buff);
                    fos.close();
                    inputStream.close();
                } catch (Exception e) {
                    rt.setAck(1000);
                    rt.setMsg("写出标签出现Exception异常"+e.getMessage());
                    System.out.println(e.getMessage());
                }

                if((file.length()/1024)<5){
                    rt.setAck(444);
                    rt.setMsg("PDF文件大小为"+file.length()/1024+"KB");
                }else{
                    ResultCode ro = new ResultCode();
                    String Path = ConstantConfig.PDF_URL_PATH +File.separator+"pdf"+File.separator+"pdf"+File.separator+platformOrderId+".pdf";
                    rt.setAck(0);
                    rt.setMsg("调取成功!");
                    rt.setData(Path);//标签路径
                }
                if((file.length()/1024)<5){
                    rt.setAck(444);
                    rt.setMsg("PDF文件大小为"+file.length()/1024+"KB");
                }else{
                   ;
                }
            }else {
                rt.setAck(1000);
                rt.setMsg("接口返回错误,错误代码："+httpCode);
                System.out.println("获取标签");
            }


        } catch (IOException e) {
            e.printStackTrace();
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

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len=0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len=inputStream.read(buffer)) !=-1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }













    public static ResultCode rotatePDF(String oldPDFPath, String newPDFPath)
    {
        ResultCode rt = new ResultCode();
        PdfReader reader = null;
        try
        {
            reader = new PdfReader(oldPDFPath);
        }
        catch (IOException e)
        {
            rt.setAck(1000);
            rt.setMsg(e.getMessage());
            return rt;
        }
        Document document = new Document();

        PdfCopy p = null;
        try
        {
            p = new PdfSmartCopy(document, new FileOutputStream(newPDFPath));
        }
        catch (Exception e)
        {
            rt.setAck(1000);
            rt.setMsg(e.getMessage());
            return rt;
        }
        document.open();
        int n = reader.getNumberOfPages();
        for (int j = 1; j <= n; j++)
        {
            PdfDictionary pd = reader.getPageN(j);
            pd.put(PdfName.ROTATE, new PdfNumber(90));
        }
        for (int page = 0; page < n;) {
            try
            {
                p.addPage(p.getImportedPage(reader, ++page));
                rt.setAck(0);
            }
            catch (IOException e)
            {
                rt.setAck(1000);
                rt.setMsg(e.getMessage());
                break;
            }
            catch (Exception e2)
            {
                rt.setAck(1000);
                rt.setMsg(e2.getMessage());
                break;
            }
        }
        document.close();
        reader.close();
        File file = new File(oldPDFPath);
        rt.setMsg("标签旋转成功，删除旧标签" + file.delete());
        return rt;
    }
    public static void decoderBase64File(String base64Code,String platformOrderId)
            throws Exception, IOException {
        File file = new File(PDF_ABSOLUTE_PATH +File.separator+"label"+File.separator+"pdf");
        if(!file.exists()){
            file.mkdirs();
        }
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(file.getAbsolutePath()+File.separator+platformOrderId+".pdf");
        out.write(buffer);
        out.close();
    }

    public static void bytetoFile(String orderid, byte[] pdfBytes)throws IOException, FileNotFoundException {
        FileOutputStream fos = null;
        try {
            //判断目录存在自动创建
            if(new File(ConstantConfig.PDF_ABSOLUTE_PATH).isDirectory()==false){
                new File(ConstantConfig.PDF_ABSOLUTE_PATH).mkdirs();
            }
        } catch (Exception e) {

        }
        File file = new File(ConstantConfig.PDF_ABSOLUTE_PATH +File.separator+"label"+File.separator+"pdf"+File.separator+orderid+".pdf");
        try {
            fos = new FileOutputStream(file);
            fos.write(pdfBytes);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }finally {
            fos.flush();
            fos.close();
        }

    }

}
