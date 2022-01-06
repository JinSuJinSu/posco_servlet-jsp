package base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calculation")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String operation = request.getParameter("operation");
		int number1 = Integer.valueOf(request.getParameter("number1"));
		int number2 = Integer.valueOf(request.getParameter("number2"));
		int result=0;
				
		if(operation.equals("divide") && number2==0) {
			RequestDispatcher rd = request.getRequestDispatcher("/jspsrc/errorResult.jsp");
			rd.forward(request, response);
		}
		else {
			switch(operation) {
			case "plus":
				result=number1+number2;
				break;
			case "minus":
				result=number1-number2;
				break;
			case "multiply":
				result=number1*number2;
				break;
			case "divide":
				result=number1/number2;
				break;
			default:
				break;	
			}
			request.setAttribute("number", result);
			RequestDispatcher rd = request.getRequestDispatcher("/jspsrc/calcResultEL.jsp");
			rd.forward(request, response);	
		}		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
