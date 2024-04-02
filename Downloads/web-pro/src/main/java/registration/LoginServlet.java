package registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//AES Encrypting
import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String uemail= request.getParameter("username");
		String up= request.getParameter("password");
		
		HttpSession session= request.getSession();
		RequestDispatcher dispatcher =  null;
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false","root","");
			PreparedStatement pst= con.prepareStatement("select * from users where uemail = ?");
			pst.setString(1, uemail);

			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				String hashedPassword = rs.getString("upwd");

				// Verify the password
				boolean isPasswordValid = BCrypt.checkpw(up, hashedPassword);
				if (isPasswordValid) {
					session.setAttribute("name", rs.getString("uname"));
					dispatcher = request.getRequestDispatcher("listFiles.jsp");
				} else {
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("login.jsp");
				}
			}
			else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
