package Mypack;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banklogin?useSSL=false","root","root");
			PreparedStatement stat=con.prepareStatement("select * from user where u_id=? and password=?");
			stat.setString(1,request.getParameter("a"));
			stat.setString(2,request.getParameter("b"));
			ResultSet rs=stat.executeQuery();
			if(rs.next())
			{
				out.println("<form method='post'action='getbalanceservlet'>");
				out.println("enter account number:<input type='text' name='t1'><br><br>");
				out.println("<input type='submit' value='submit'");
				out.println("</form>");
			}
			else
			{
				out.println("<h1>Login Failed</h1>"); 	
				rs.close();
				con.close();
			}
			
		}
		catch(Exception e)
		{
			out.println(e);
		}
		out.println("</body></html>");
		out.close();
	}

}
