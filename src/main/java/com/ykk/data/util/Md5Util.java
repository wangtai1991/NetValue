package com.ykk.data.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



/**
 * @author felix
 *
 */
public class Md5Util {

	     /*利用MD5进行加密
	     * @param str  待加密的字符串
	     * @return  加密后的字符串
	     * @throws UnsupportedEncodingException 
	     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
	     * @throws UnsupportedEncodingException  
	     */
	    public static String EncoderByMd5(String str) {
	    	  String re_md5 = new String();
	          try {
	              MessageDigest md = MessageDigest.getInstance("MD5");
	              md.update(str.getBytes());
	              byte b[] = md.digest();
	   
	              int i;
	   
	              StringBuffer buf = new StringBuffer("");
	              for (int offset = 0; offset < b.length; offset++) {
	                  i = b[offset];
	                  if (i < 0)
	                      i += 256;
	                  if (i < 16)
	                      buf.append("0");
	                  buf.append(Integer.toHexString(i));
	              }
	   
	              re_md5 = buf.toString();
	   
	          } catch (NoSuchAlgorithmException e) {
	              e.printStackTrace();
	          }
	          return re_md5;
	    }
	    
	    //使用Md5,Sha1二种加密方式
	    public static String EncoderByMd5AndSha1(String str) {
	    	return SHA1(EncoderByMd5(str));
	    }
	    
	    public static String SHA1(String decript) {
	    	 String sha1 = new String();
	        try {
	            MessageDigest digest = java.security.MessageDigest
	                    .getInstance("SHA-1");
	            digest.update(decript.getBytes());
	            byte messageDigest[] = digest.digest();
	            // Create Hex String
	            StringBuffer hexString = new StringBuffer();
	            // 字节数组转换为 十六进制 数
	            for (int i = 0; i < messageDigest.length; i++) {
	                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
	                if (shaHex.length() < 2) {
	                    hexString.append(0);
	                }
	                hexString.append(shaHex);
	            }
	            sha1= hexString.toString();
	 
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return sha1;
	    }
	    
	    /**判断用户密码是否正确
	     * @param newpasswd  用户输入的密码
	     * @param oldpasswd  数据库中存储的密码－－用户密码的摘要
	     * @return
	     * @throws NoSuchAlgorithmException
	     * @throws UnsupportedEncodingException
	     */
	    public static boolean checkpassword(String newpasswd,String oldpasswd){
	        
	    	if(EncoderByMd5AndSha1(newpasswd).equals(oldpasswd))
	            return true;
	        else
	            return false;
	    }
	    
	 public static void main(String agrs[]) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		 //Md5Util md51 = new Md5Util();
		 String string="123456";
		 String md5code = Md5Util.EncoderByMd5(string);
	     System.out.println(md5code);
	     
	 }
}
