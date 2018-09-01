package gdut.ff.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * @author liuffei
 * @date 2018-01-27
 */
@WebFilter(urlPatterns = "/*")
public class HttpFilter implements Filter {


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
        chain.doFilter(request, res);
	}

	@Override
	public void destroy() {

	}

}
