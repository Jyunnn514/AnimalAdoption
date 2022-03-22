package tw.com.huafan.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import tw.com.huafan.project.dao.daoimpl.UserDaoImpl;


@WebServlet("/ls")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		response.setCharacterEncoding("UTF-8");
		
		UserDaoImpl udi = new UserDaoImpl();
		
		boolean login = udi.login(account, password);
		
		String MSG = "";
		
		if (login) {
			
			MSG = "success"; 				

			
		}else {
			
			MSG = "failed";
			
		}
		
		
		
		PrintWriter out = response.getWriter();
		out.println(MSG);
		out.flush();
		out.close();
		
	
	}

}
