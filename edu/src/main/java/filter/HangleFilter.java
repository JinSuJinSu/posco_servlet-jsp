package filter;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/star" })
public class HangleFilter implements Filter {

 
    public HangleFilter() {
      
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("한글필터 실행");
		if(((HttpRequest)request).method().equals("POST")) {
			request.setCharacterEncoding("UTF-8");
		}
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
