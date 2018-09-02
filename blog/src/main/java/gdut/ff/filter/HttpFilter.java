package gdut.ff.filter;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdut.ff.domain.Ip;
import gdut.ff.service.IpServiceImpl;
import gdut.ff.utils.IpUtil;

/**
 * 
 * @author liuffei
 * @date 2018-01-27
 */
@WebFilter(urlPatterns = "/*")
public class HttpFilter implements Filter {

	@Resource
	IpServiceImpl ipService;
	
	@Resource
	IpUtil ipUtil;
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		//获取request请求头
		String origin = req.getHeader("origin");
		res.addHeader("Access-Control-Allow-Origin", origin);
        res.addHeader("Allow", "GET, POST, DELETE, PUT");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With, token");
        res.setCharacterEncoding("UTF-8");
        res.addHeader("Access-Control-Allow-Credentials", "true");
        
        //以天为单位保存请求的Ip
        saveIp(req);
        
        chain.doFilter(request, res);
	}

	@Override
	public void destroy() {

	}
	
	public String getIpAddress(HttpServletRequest request) { 
	    String ipAddress = request.getHeader("x-forwarded-for");
	    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	    	ipAddress = request.getHeader("Proxy-Client-IP");
	    }
	    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	    	ipAddress = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	    	ipAddress = request.getRemoteAddr();
	    	if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
	    		//根据网卡取本机配置的IP
	    		try {
					InetAddress inet = InetAddress.getLocalHost();
					ipAddress = inet.getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
	    	}
	    }
	    
	    if(ipAddress != null && ipAddress.length() > 15) {
	    	if(ipAddress.indexOf(",") > 0) {
	    		ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
	    	}
	    }
	    
	    return ipAddress;
	}
	
	public void saveIp(HttpServletRequest req) {
		 //记录下请求的IP
        String requestIpAddr = getIpAddress(req);
        Ip findIpCurrentDate = ipService.findOneCurrentDate();
        if(null == findIpCurrentDate) {
        	//如果今天这个Ip没有记录，进行插入，如果已经存在就什么也不做
        	Ip ip = ipUtil.queryIpDetail(requestIpAddr);
        	ipService.insertIp(ip);
        }
	}

}
