package com.drive.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileController
 */
@WebServlet("/FileController")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARENT_PATH="C:\\test";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String path = request.getParameter("path");
		
		if(path==null || path.isEmpty())
			path=PARENT_PATH;
		
	    listFiles(path,request,response);
		
	}

	private void listFiles(String path, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		File filePath =  new File(path);
		File fileList[]= filePath.listFiles();
		
		ArrayList <String> folders = new ArrayList<String>();
		ArrayList <String> files = new ArrayList<String>();
		
		for(File file : fileList) {
			if(file.isDirectory())
				folders.add(file.getName());
			else 
				files.add(file.getName());
		}
		
		request.setAttribute("path", path);  
		request.setAttribute("folders", folders);  
		request.setAttribute("files", files);
		
		try {
			getServletContext().getRequestDispatcher("/listFiles.jsp").forward(request, response);
		}catch(ServletException | IOException e) {
			e.printStackTrace();
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
