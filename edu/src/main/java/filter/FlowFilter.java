package filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/firstfilter" })
public class FlowFilter implements Filter {

    public FlowFilter() {
        // TODO Auto-generated constructor stub
    	System.out.println("FlowFilter 객체 생성");
    }

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("FlowFilter 객체 해제");
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("서브릿 수행 전처리");
		chain.doFilter(request, response);
		System.out.println("서브릿 수행 후처리");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("객체 초기화");
	}

}
