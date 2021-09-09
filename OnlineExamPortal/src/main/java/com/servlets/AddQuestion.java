package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pojo.Question;

public class AddQuestion extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session=req.getSession();
		List<Question> qList;
		if(req.getRequestURI().endsWith("addQuestion")) {
		if(session.getAttribute("qList")!=null) {
			qList=(ArrayList<Question>) session.getAttribute("qList");
		}else {
			qList=new ArrayList<>();
		}
		
		Question que=new Question();
		que.setqName(req.getParameter("question"));
		que.setOptionA(req.getParameter("optionA"));
		que.setOptionB(req.getParameter("optionB"));
		que.setOptionC(req.getParameter("optionC"));
		que.setOptionD(req.getParameter("optionD"));
		que.setAnswer(req.getParameter("answer"));
		qList.add(que);
		
	}else {
		int que=Integer.parseInt(req.getParameter("index"));
		qList=(ArrayList<Question>) session.getAttribute("qList");
		qList.remove(que);
	}
		session.setAttribute("qList", qList);
		RequestDispatcher send=req.getRequestDispatcher("addTest.jsp");
		try {
			send.forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
