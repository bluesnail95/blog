package gdut.ff.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取天气预报
 * @author liuffei
 * @date 2018年1月16日
 * @description
 */
public class TemperatureService {

	private static String httpRequest(String requestUrl) {
		StringBuffer stringBuffer = null;
		BufferedReader bufferReader = null;
		InputStreamReader inputStreamReader = null;
		InputStream inputStream = null;
		HttpURLConnection httpUrlConn = null;
		
		try {
			URL url = new URL(requestUrl);
			httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputStreamReader);
			stringBuffer = new StringBuffer();
			String str = null;
			while((str = bufferReader.readLine()) != null) {
				stringBuffer.append(str);
			}
			
			if(null != bufferReader) {
				try {
					bufferReader.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(null != inputStreamReader) {
				try {
					inputStreamReader.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(null != inputStream) {
				try {
					inputStream.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(null != httpUrlConn) {
				try {
					httpUrlConn.disconnect();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
	private static String htmlFilter(String html) {
		StringBuffer buffer = new StringBuffer();
		String str1 = "";
		String str2 = "";
		
		Pattern p = Pattern.compile("(.*)(<li class=\"sky skyid lv2 on\">)(.*?)(</li>)(.*)");
		Matcher m = p.matcher(html);
		if(m.matches())		
			str1 = m.group(3);
		    p = Pattern.compile("(.*)(<h1>)(.*?)(</h1>)(.*)");
		    m = p.matcher(str1);
		    if(m.matches()) {
		    	str2 = m.group(3);
		    	buffer.append(str2);
		    	buffer.append("\n天气:");
		    }
		    p = Pattern.compile("(.*)(<p title=)(.*?)(class=\"wea\">)(.*?)(</p>)(.*)");
		    m = p.matcher(str1);
		    if(m.matches()) {
		    	str2 = m.group(5);
		    	buffer.append(str2);
		    	buffer.append("\n温度:");
		    }
		    p = Pattern.compile("(.*)(<p class=\"tem\"><span>)(.*?)(</span>)(.*?)(<i>)(.*)");
		    m = p.matcher(str1);
		    if(m.matches()) {
		    	str2 = m.group(3);
		    	buffer.append(str2);
		    	buffer.append("\n风力:");
		    }
		    p = Pattern.compile("(.*)(<p class=\"win\"><em>)(.*?)(</em><i>)(.*?)(</i>)(.*)");
		    m = p.matcher(str1);
		    if(m.matches()) {
		    	str2 = m.group(5);
		    	buffer.append(str2);
		    }
		return buffer.toString();
	}
	
	public static void main(String args[]) {
		String html = TemperatureService.httpRequest("http://www.weather.com.cn/weather/101280101.shtml");
		//写入到一个文件
		/*
		try {
			FileOutputStream fos = new FileOutputStream(new File("F:\\云收藏项目修改\\favorites-web-master\\src\\main\\resources\\weather.html"));
		    PrintWriter pw = new PrintWriter(fos);
		    pw.write(html);
		    pw.flush();
		    pw.close();
		    fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		System.out.println(htmlFilter(html));
	}
}
