package base;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String pid = request.getParameter("pid");
		
		if(session.getAttribute("object") == null) {
			session.setAttribute("object", new ProductVO());
		}
		
		ProductVO product = (ProductVO)session.getAttribute("object");
		switch(pid){
		case "p001":
			product.setAppleCount(1);
			break;
		case "p002":
			product.setBananaCount(1);
			break;
		case "p003":
			product.setHalabongCount(1);
			break;
		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/jspsrc/productView.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
