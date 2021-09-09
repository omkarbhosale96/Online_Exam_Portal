package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.JDBCConnection;
import com.pojo.Question;
import com.pojo.Test;

public class LoginServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String dbPassword="";
		String query="select * from loginInfo where username='"+username+"'";
		RequestDispatcher send;
		try {
			Connection conn= JDBCConnection.getJdbcConnection();
			Statement stat=conn.createStatement();
			ResultSet result=stat.executeQuery(query);
			while(result.next()) {
				dbPassword=result.getString("password");
			}
			
			conn.close();
			stat.close();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		if(password!=null && password!="" &&  password.equals(dbPassword)) {
			HttpSession session=request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			try {
				if(username.equalsIgnoreCase("teacher")) {
					request.setAttribute("tests", getTestList());
					send=request.getRequestDispatcher("teacher.jsp");
					send.forward(request, response);
				}
				else if(username.equalsIgnoreCase("student")) {
					request.setAttribute("tests", getTestList());
					send=request.getRequestDispatcher("student.jsp");
					send.forward(request, response);
				}
				else
					response.sendRedirect("index.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				response.sendRedirect("index.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
//	private List<Question> getQuestionList(){
//		List<Question> questions=new ArrayList<>();
//		Question que;
//		try {
//			Connection conn=JDBCConnection.getJdbcConnection();
//			Statement stat=conn.createStatement();
//			String query = "select * from questions";
//			ResultSet out = stat.executeQuery(query);
//			while(out.next()) {
//				que=new Question();
//				que.setQestionId(out.getInt("q_id"));
//				que.setqName(out.getString("q_name"));
//				que.setOptionA(out.getString("optionA"));
//				que.setOptionB(out.getString("optionB"));
//				que.setOptionC(out.getString("optionC"));
//				que.setOptionD(out.getString("optionD"));
//				questions.add(que);
//			}
//			return questions;
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	private List<Test> getTestList(){
		List<Test> tests=new ArrayList<>();
		Test test;
		try {
			Connection conn=JDBCConnection.getJdbcConnection();
			Statement stat=conn.createStatement();
			String query = "select * from tests";
			ResultSet out = stat.executeQuery(query);
			while(out.next()) {
				test=new Test();
				test.setTestId(out.getInt("t_id"));
				test.settName(out.getString("t_name"));
				tests.add(test);
			}
			return tests;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
