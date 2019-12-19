package main.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import main.action.Action;

@WebServlet(
		urlPatterns = {
				"/Controller",
				"*.do"
		},
		initParams = {
				@WebInitParam(name = "propertyConfig", value = "Mapping.properties")
		})
public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> commandMap = new HashMap<String, Object>();
	
	
	public Controller(){
		super();
	}
	
	public void init(ServletConfig config) throws ServletException{
		String props = config.getInitParameter("propertyConfig");
		String realFolder = "/property"; 

		ServletContext context = config.getServletContext();

		String realPath = context.getRealPath(realFolder) +File.separator+props;
							    

		Properties pr = new Properties();
		FileInputStream f = null;
		try{
			f = new FileInputStream(realPath); 
			pr.load(f);
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (f != null) try { f.close(); } catch(IOException ex) {}
		}
		
		Iterator<?> keyIter = pr.keySet().iterator();
		
		while( keyIter.hasNext() ) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try{
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.getDeclaredConstructor().newInstance();
				commandMap.put(command, commandInstance);
			}catch (ClassNotFoundException|NoSuchMethodException|InvocationTargetException e) {
				e.printStackTrace();
			}catch (InstantiationException|IllegalAccessException e) {
				e.printStackTrace();
			}
		}	
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestProcess(request, response);
	}
	
	
	private void requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String view=null;
		Action com=null;
		try {
			String command=request.getRequestURI();
			System.out.println(command);
			if(command.indexOf(request.getContextPath())==0) {
				command=command.substring(request.getContextPath().length());
			}
			com=(Action)commandMap.get(command);
			if(com==null) {view="index.jsp";}
			else {view=com.requestProcess(request, response);}
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		request.setAttribute("cont", view);
	    RequestDispatcher dispatcher = 
	    		//주소창에 아무것도 없다  localhost/    --> index.jsp 로딩
	       request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
