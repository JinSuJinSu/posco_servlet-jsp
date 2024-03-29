package base;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/getHTML", "/getXML", "/getJSON", "/getImage" })
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		String filename = "";
		String contentType = "";
		if (uri.endsWith("getHTML")) {
			filename = "c:/Temp/sample.html";	
			contentType = "text/html; charset=utf-8";
		} else if (uri.endsWith("getXML")) {
			filename = "c:/Temp/sample.xml";	
			contentType = "application/xml; charset=utf-8";
		} else if (uri.endsWith("getJSON")) {
			filename = "c:/Temp/sample.json";	
			contentType = "text/json; charset=utf-8";
		} else {
			filename = "c:/Temp/trans_duke.png";	
			contentType = "image/png";
		}
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f); // 바이트스트림 형태로 만들어준다.
		response.setContentType(contentType);
		if(contentType.startsWith("image")) {
			byte[] content = new byte[(int)f.length()];
			ServletOutputStream sos = response.getOutputStream(); 
			// 여기서는 리턴값이 텍스트가 아니라 이미지기 때문에 getWirter를 쓸수없다.
			fis.read(content);
			sos.write(content);			
			sos.close();
		} else {
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			// 바이트스트림을 문자열 스트림으로 변환해주는 기능
			BufferedReader br = new BufferedReader(isr);
			PrintWriter out = response.getWriter();
			String line = null; 
			while((line = br.readLine()) != null) 
				out.println(line);
			out.close();
			br.close();
			isr.close();			
		}		
		fis.close();
	}
}
