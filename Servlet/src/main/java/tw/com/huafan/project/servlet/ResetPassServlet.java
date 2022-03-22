package tw.com.huafan.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.huafan.project.dao.daoimpl.UserDaoImpl;
import tw.com.huafan.project.projo.User;


@WebServlet("/rp")
public class ResetPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		UserDaoImpl udi = new UserDaoImpl();
		
		User us = new User();
		

		String account = request.getParameter("account");
		String oldPass = request.getParameter("oldPass");
		String password = request.getParameter("newPass");
		
		boolean login = udi.login(account, oldPass);
		
		
		if (login) {
		
			us.setAccount(account);
			us.setPassword(password);


		udi.updatePass(us);
		
		System.out.println(account + "  " +oldPass + "  " + password +"   ");
		
		PrintWriter out = response.getWriter();
		out.println("success");
		out.flush();
		out.close();
		
		}else {
			
			PrintWriter out = response.getWriter();
			out.println("輸入錯誤");
			out.flush();
			out.close();
			
		}
		
		
		
		
		
	}

}
