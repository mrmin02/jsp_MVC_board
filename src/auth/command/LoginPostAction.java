package auth.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DBBean;
import main.action.Action;

public class LoginPostAction implements Action{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		DBBean db = DBBean.getInstance();
		
		
		
		int login = db.userLogin(id, pwd);//로그인 시도   1: 성공, 0: 비밀번호 X  , -1: 아이디 존재 X
		if(login==1) {
			
			Map<String, String> userInfo = db.getUserInfo(id); // 관리자인이 아닌지  0 : 일반회원 , 1 : 관리자
			
			request.setAttribute("id", id);
			request.setAttribute("name", userInfo.get("name"));
			request.setAttribute("admin", userInfo.get("admin"));
			
			return "/auth/loginOk.jsp";
			
		}else if(login==0) {
			request.setAttribute("message", "비밀번호가 잘못되었습니다.");
		}else {
			request.setAttribute("message", "아이디가 존재하지 않습니다.");
		}
		
		
		return "/auth/loginError.jsp";
	}

}
