package Mypack;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/regservlet")
public class regservlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banklogin?useSSL=false","root","root");
			PreparedStatement stat=con.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
			stat.setString(1,request.getParameter("n1"));
			stat.setString(2,request.getParameter("n2"));
			stat.setString(3,request.getParameter("n3"));
			stat.setString(4,request.getParameter("n4"));
			stat.setString(5,request.getParameter("n5"));
			stat.setString(6,request.getParameter("n6"));
			stat.setString(7,request.getParameter("n7"));
			stat.executeUpdate();
			out.println("<h1>Account open Successfully</h1>");
			con.close();
		}
		catch(Exception e) {
		out.println(e);	
		}
		out.println("</body></html>");
		out.close();	
	}

}
