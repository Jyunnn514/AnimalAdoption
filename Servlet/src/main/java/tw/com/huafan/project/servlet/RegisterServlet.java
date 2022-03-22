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

@WebServlet("/rs")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("-----------------");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		User us = new User();

		if (password.equals(password2)) {

			us.setName(name);
			us.setAccount(account);
			us.setPassword(password);

			String MSG = "";
			UserDaoImpl UDI = new UserDaoImpl();
			User uu = UDI.findUser(us.getName());

			boolean flag = false;
			if (uu == null) {
				flag = UDI.register(us);

			}

			if (flag) {
				MSG = "success";
			} else {
				MSG = "failed";
			}
			if (uu != null) {

				MSG = "already exists";
			}

			PrintWriter out = response.getWriter();
			out.println(MSG);
			out.flush();
			out.close();

			System.out.println(password + password2);

		} else {
			System.out.println("兩次密碼不相同");
			System.out.println(password + password2);
			PrintWriter out = response.getWriter();
			out.println("not same");
			
		}

	}

}
