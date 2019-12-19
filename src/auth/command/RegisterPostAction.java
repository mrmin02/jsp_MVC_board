package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DBBean;
import main.action.Action;

public class RegisterPostAction implements Action{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String id = request.getParameter("user_id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String eamil = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		DBBean db = DBBean.getInstance();
		db.addUser(id, pwd, name, eamil,phone);
		System.out.println("새로운 사용자 등록 완료");
		return "/auth/registerOk.jsp";
//		response.sendRedirect("/");
	}
	
}
