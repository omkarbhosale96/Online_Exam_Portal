package com.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.jdbc.JDBCConnection;
import com.pojo.Question;

public class AddTest extends HttpServlet{
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session=req.getSession();
		List<Question> qList=(ArrayList<Question>) session.getAttribute("qList");
		System.out.println(req.getParameter("testName")+"@@@");
		
		try {
			Connection conn=JDBCConnection.getJdbcConnection();
			String query="insert into tests(t_name) values (?)";
			String testName=req.getParameter("testName");
			PreparedStatement stat=conn.prepareStatement(query);
			stat.setString(1, testName);
			stat.executeUpdate();
			int testId=getTestId(conn, testName);
			insertQuestions(conn,qList,testId);
			session.removeAttribute("qList");
			System.out.println("Test added succesfully...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private int getTestId(Connection conn, String testName) throws SQLException {
		Statement stat=conn.createStatement();
		String query="select * from tests where t_name='"+testName+"';";
		ResultSet out=stat.executeQuery(query);
		
		while(out.next()) {
			return out.getInt("t_id");
		}
		return 0;
	}

	private void insertQuestions(Connection conn, List<Question> qList,int testId) throws SQLException {
		String query="insert into questions(q_name,optionA,optionB,optionC,optionD,answer,test_id) values(?,?,?,?,?,?,?)";
		PreparedStatement stat;
		for(Question que : qList){
			stat=conn.prepareStatement(query);
			stat.setString(1, que.getqName());
			stat.setString(2, que.getOptionA());
			stat.setString(3, que.getOptionB());
			stat.setString(4, que.getOptionC());
			stat.setString(5, que.getOptionD());
			stat.setString(6, que.getAnswer());
			stat.setInt(7, testId);
			stat.execute();
		}
		
	}

}
