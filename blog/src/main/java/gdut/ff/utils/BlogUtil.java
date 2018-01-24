package gdut.ff.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author liuffei
 *
 */
public class BlogUtil {

	public static String filterHtml(String html) {
		StringBuffer stringBuffer = new StringBuffer("");
		String str2 = "";
		Pattern pattern = Pattern.compile("(.*)(<li class=\"clearfix\" data-type=\"blog\")(.*?)(>)(.*?)(</li>)(.*)");
	    Matcher m = pattern.matcher(html);
	    if(m.matches()) {	
	    	str2 = m.group(5);
	    	html = m.group(1);
			
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
}
