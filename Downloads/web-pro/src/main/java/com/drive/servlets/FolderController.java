package com.drive.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class folderController
 */
@WebServlet("/FolderController")
public class FolderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FolderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String path= request.getParameter("path");
		String action = request.getParameter("action");
		String folder = request.getParameter("folder");
		
		try {
			if(action.equalsIgnoreCase("delete")) {
				File folderName = new File(path + File.separator + folder);
				FileUtils.cleanDirectory(folderName);
				FileUtils.deleteDirectory(folderName);
			}
			else if(action.equalsIgnoreCase("create")) {
				File folderName = new File(path + File.separator + folder);
				if(!folderName.exists())
					folderName.mkdir();
			}
			
		}catch(Exception e){
			System.out.print(e.toString());
		}finally {
			request.setAttribute("path", path);
			getServletContext().getRequestDispatcher("/FileController").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
