package Mypack;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/getbalanceservlet")
public class getbalanceservlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banklogin?useSSL=false","root","root");
			PreparedStatement stat=con.prepareStatement("select balance from user where account_no=?");
	        stat.setString(1,request.getParameter("t1"));
	        ResultSet rs=stat.executeQuery();
	        if(rs.next()) 
	        	out.println("<h1>Your current balance is:"+rs.getInt(1));
	        else 
	        	out.println("Account number is not found");
	        rs.close();
	        con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
		out.println("</body></html>");
		out.close();
}
}

