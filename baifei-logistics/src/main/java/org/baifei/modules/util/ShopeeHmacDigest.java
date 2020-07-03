package org.baifei.modules.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 创建时间：2018年8月2日 上午10:36:02 项目名称：mabang-order-shopee
 * 
 * @author 牛凯凯
 * @version 1.0 描述：
 */
public class ShopeeHmacDigest {
	private static final String CHAR_UTF_8 = "UTF-8";
	private static final String CHAR_ASCII = "ASCII";
	public static String HASH_ALGORITHM = "HmacSHA256";
	
	public static String hmacDigest(String msg, String keyString) {
		 String digest = null;
		 try {
			 SecretKeySpec key = new SecretKeySpec((keyString).getBytes(CHAR_UTF_8), HASH_ALGORITHM);
			 Mac mac = Mac.getInstance(HASH_ALGORITHM);
			 mac.init(key);
			 final byte[] bytes = mac.doFinal(msg.getBytes(CHAR_ASCII));
			 StringBuffer hash = new StringBuffer();
			 for (int i = 0; i < bytes.length; i++) {
				 String hex = Integer.toHexString(0xFF & bytes[i]);
				 if (hex.length() == 1) {
				    hash.append('0');
				 }
				 hash.append(hex);
			 }
			 digest = hash.toString();
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 } catch (InvalidKeyException e) {
			 e.printStackTrace();
		 } catch (NoSuchAlgorithmException e) {
			 e.printStackTrace();
		 }
		 return digest;
	 }
}
