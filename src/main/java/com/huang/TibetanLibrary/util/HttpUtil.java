package com.huang.TibetanLibrary.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	 public static String sendPost(String pathUrl, String raw){
		 String result = "";
	        try{
	            URL url = new URL(pathUrl);
	            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

	            //设置连接属性
	            httpConn.setDoOutput(true);// 使用 URL 连接进行输出
	            httpConn.setDoInput(true);// 使用 URL 连接进行输入
	            httpConn.setUseCaches(false);// 忽略缓存
	            httpConn.setRequestMethod("POST");// 设置URL请求方法

	            // 设置请求属性
	            // 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
	            byte[] requestStringBytes = raw.getBytes("utf-8");
	            httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);
	            httpConn.setRequestProperty("Content-Type", "application/octet-stream");
	            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
	            httpConn.setRequestProperty("Charset", "UTF-8");

	            // 建立输出流，并写入数据
	            OutputStream outputStream = httpConn.getOutputStream();
	            outputStream.write(requestStringBytes);
	            outputStream.close();
	            // 获得响应状态
	            int responseCode = httpConn.getResponseCode();

	            if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
	                // 当正确响应时处理数据
	                StringBuffer sb = new StringBuffer();
	                String readLine;
	                BufferedReader responseReader;
	                // 处理响应流，必须与服务器响应流输出的编码一致
	                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "utf-8"));
	                while ((readLine = responseReader.readLine()) != null) {
	                    sb.append(readLine).append("\n");
	                }
	                responseReader.close();
	                result = sb.toString();
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
			return result;
	    }
	 public static void main(String[] args){
		 sendPost("http://eu.api.3dbinpacking.com/packer/pack" , "{ \"bins\":[  { \"w\":128, \"h\":80, \"d\":56, \"id\":\"Bin1\"  } ], \"items\": [  { \"w\": 48, \"h\": 40, \"d\": 24, \"q\": 5, \"vr\": 1, \"id\": \"ITEM - 1\", \"wg\": 0  },  { \"w\": 32, \"h\": 32, \"d\": 24, \"q\": 5, \"vr\": 1, \"id\": \"ITEM - 2\", \"wg\": 0  },  { \"w\": 48, \"h\": 24, \"d\": 16, \"q\": 5, \"vr\": 1, \"id\": \"ITEM - 2\", \"wg\": 0  },  { \"w\": 48, \"h\": 32, \"d\": 32, \"q\": 5, \"vr\": 1, \"id\": \"ITEM - 2\", \"wg\": 0  }], \"username\":\"huangshijie01\", \"api_key\":\"dbfbd3518e57a2431a2bfd8b6f0b4555\", \"params\":{  \"images_background_color\":\"16,78,139\",  \"images_bin_border_color\":\"59,59,59\",  \"images_bin_fill_color\":\"0,204,255\",  \"images_item_border_color\":\"155,237,254\",  \"images_item_fill_color\":\"18,172,203\",  \"images_item_back_border_color\":\"215,103,103\",  \"images_sbs_last_item_fill_color\":\"218,160,21\",  \"images_sbs_last_item_border_color\":\"255,233,155\",  \"images_width\":250,  \"images_height\":250,  \"images_source\":\"file\",  \"images_sbs\":1,  \"stats\":1,  \"item_coordinates\":1,  \"images_complete\":1,  \"images_separated\":1 }}");
	 }
}
