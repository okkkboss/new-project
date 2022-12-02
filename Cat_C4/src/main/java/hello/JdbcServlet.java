package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcServlet
 */
@WebServlet("/JdbcServlet")
public class JdbcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JdbcServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        String name = request.getParameter("Name");  
        String pass = request.getParameter("Pass");  
        String lang = request.getParameter("Lang");
        
        try {
      	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BatchB","root","omkar@123");
			System.out.println("Connection created");
			PreparedStatement ps = con.prepareStatement("insert into Jsp values(?,?,?);");
			ps.setString(1, name);
			ps.setString(2, pass);
			ps.setString(3, lang);
	
			
			int i = ps.executeUpdate();  
          if(i>0) {
          	response.sendRedirect("success.html");
          }
			}
        catch (Exception e1) {
				e1.printStackTrace();
			}
        out.close();  

	}

}
