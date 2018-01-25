package gdut.ff.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author liuffei
 *
 */
public class BlogUtil {
	
	public ArrayNode blogs = NodeUtil.crete();

	public ArrayNode filterHtml(String html) {
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
			    	ObjectNode blog = NodeUtil.create();
			    	String url = m.group(5);
			    	blog.put("url",url.trim());
			    	String title = m.group(7);
			    	blog.put("title",title.trim());
			    	blogs.add(blog);
			    }	    	    
	    	}
	    	
	    	//遍历
	    	if("" != html.trim()) {
	    	    filterHtml(html);
	    	}
	    }
		return blogs;
	}
}
