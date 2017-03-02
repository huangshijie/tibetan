package com.huang.TibetanLibrary.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class MutilThreadUtil {
	private static String URL = "http://eu.api.3dbinpacking.com/packer/pack" ;
	private static String RAW = "{ \"bins\":[  { \"w\":128, \"h\":80, \"d\":56, \"id\":\"Bin1\"  } ], \"items\": [  { \"w\": 48, \"h\": 40, \"d\": 24, \"q\": 5, \"vr\": 1, \"id\": \"ITEM - 1\", \"wg\": 0  },  { \"w\": 32, \"h\": 32, \"d\": 24, \"q\": 5, \"vr\": 1, \"id\": \"ITEM - 2\", \"wg\": 0  },  { \"w\": 48, \"h\": 24, \"d\": 16, \"q\": 5, \"vr\": 1, \"id\": \"ITEM - 2\", \"wg\": 0  },  { \"w\": 48, \"h\": 32, \"d\": 32, \"q\": 5, \"vr\": 1, \"id\": \"ITEM - 2\", \"wg\": 0  }], \"username\":\"huangshijie01\", \"api_key\":\"dbfbd3518e57a2431a2bfd8b6f0b4555\", \"params\":{  \"images_background_color\":\"16,78,139\",  \"images_bin_border_color\":\"59,59,59\",  \"images_bin_fill_color\":\"0,204,255\",  \"images_item_border_color\":\"155,237,254\",  \"images_item_fill_color\":\"18,172,203\",  \"images_item_back_border_color\":\"215,103,103\",  \"images_sbs_last_item_fill_color\":\"218,160,21\",  \"images_sbs_last_item_border_color\":\"255,233,155\",  \"images_width\":250,  \"images_height\":250,  \"images_source\":\"file\",  \"images_sbs\":1,  \"stats\":1,  \"item_coordinates\":1,  \"images_complete\":1,  \"images_separated\":1 }}";
	
	class DownloadImg extends Thread{
		private String url;
		private String filename;
		
		public DownloadImg(String url, String filename){
			this.url = url;
			this.filename = filename;
		}
		
		public void run(){
			try{
				HttpURLConnection conn;
		        URL addr = new URL(this.url);
		        conn = (HttpURLConnection) addr.openConnection();
		        conn.setRequestMethod("POST");
		        conn.setDoOutput(true);
		        conn.connect();
		        InputStream is = conn.getInputStream();
		        byte[] bs = new byte[1024];
		        int len;
		        File im = new File(this.filename);
		        OutputStream os = new FileOutputStream(im);
		        while ((len = is.read(bs)) != -1) {
		          os.write(bs, 0, len);
		        }
		        System.out.println("Img-URL:"+filename);
		        os.close();
		        is.close();
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
	
	public static void main(String[] args){
		String result = HttpUtil.sendPost(URL, RAW);
		
		JSONObject resultJSON = new JSONObject(result);
		JSONObject jsonResponse = resultJSON.getJSONObject("response");
		JSONArray binsPackedArray = jsonResponse.getJSONArray("bins_packed");
		JSONArray jsonItemArray = new JSONArray();
		for(int i=0 ; i<binsPackedArray.length(); i++){
			JSONObject item = binsPackedArray.getJSONObject(i);
			if(item.keySet().contains("items")){
				jsonItemArray = item.getJSONArray("items");
				break;
			}
		}
	
	}
	
}
