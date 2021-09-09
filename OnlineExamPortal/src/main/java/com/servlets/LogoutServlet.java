package com.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session=req.getSession();
		session.removeAttribute("username");
		session.removeAttribute("password");
		try {
			res.sendRedirect("index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

