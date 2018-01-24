package gdut.ff.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 抓取博客园首页的数据
 * @author liuffei
 *
 */
public class TestBlogsService {

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
	
	public static String filterHtml(String html) {
		StringBuffer stringBuffer = new StringBuffer("");
		String str2 = "";
		Pattern pattern = Pattern.compile("(.*)(<li class=\"clearfix\" data-type=\"blog\")(.*?)(>)(.*?)(</li>)(.*)");
	    Matcher m = pattern.matcher(html);
	    if(m.matches()) {
	    	
	    	str2 = m.group(5);
	    	html = m.group(1);
	    	
	    	try {
				FileOutputStream fos = new FileOutputStream(new File("F:\\云收藏项目修改\\favorites-web-master\\src\\main\\resources\\blog3.html"));
			    PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos,"UTF-8"));
			    pw.write(html);
			    pw.flush();
			    pw.close();
			    fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	    	/*
	    	 * <div class="list_con">                    
	    	 *     <h2 class="csdn-tracking-statistics" 
	    	 *         data-mod="popu_459" 
	    	 *         data-poputype="feed" 
	    	 *         data-feed-show="false" 
	    	 *         data-dsm="post">                       
	    	 *         <a strategy="recommend" 
	    	 *            href="http://blog.csdn.net/qq_40027052/article/details/79094608" 
	    	 *            target="_blank">                            
	    	 *               如何成为一名自然语言处理工程师                        
	    	 *         </a>                    
	    	 *      </h2>                 
	    	 */
	    	pattern = Pattern.compile("(.*)(<div class=\"list_con\">)(.*?)(<h2 class=\"csdn-tracking-statistics\")(.*?)(data-dsm=\"post\">)(.*?)(</h2>)(.*)");
	    	m = pattern.matcher(str2);
	    	if(m.matches()) {
	    		str2 = m.group(7);
	    		pattern = Pattern.compile("(.*)(<a strategy=)(.*?)( href=\")(.*?)(\" target=\"_blank\">)(.*?)(</a>)(.*)");
			    m = pattern.matcher(str2);
			    if(m.matches()) {
			    	str2 = m.group(5);
			    	stringBuffer.append(str2.trim());
			    	str2 = m.group(7);
		    		stringBuffer.append(str2.trim());
			    }	    	    
	    	}
	    	
	    	//遍历
	    	if("" != html.trim()) {
	    		System.out.println(filterHtml(html));
	    	}
	    }
		return stringBuffer.toString();
	}
	
	public static void main(String args[]) {
		String html = httpRequest("https://www.csdn.net/");
	   
		String str1 = "";

		Pattern pattern = Pattern.compile("(.*)(<ul class=\"feedlist_mod\" id=\"feedlist_id\")(.*?)(>)(.*)(</ul>)(.*)");
		Matcher m = pattern.matcher(html);
		if(m.matches()) {
			str1 = m.group(5);
		}	
		/*
		try {
			FileOutputStream fos = new FileOutputStream(new File("F:\\云收藏项目修改\\favorites-web-master\\src\\main\\resources\\blog2.html"));
		    PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos,"UTF-8"));
		    pw.write(str1);
		    pw.flush();
		    pw.close();
		    fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		System.out.println(filterHtml(str1));
	}
}
