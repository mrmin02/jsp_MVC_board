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
		
		
		
		int login = db.userLogin(id, pwd);//�α��� �õ�   1: ����, 0: ��й�ȣ X  , -1: ���̵� ���� X
		if(login==1) {
			
			Map<String, String> userInfo = db.getUserInfo(id); // ���������� �ƴ���  0 : �Ϲ�ȸ�� , 1 : ������
			
			request.setAttribute("id", id);
			request.setAttribute("name", userInfo.get("name"));
			request.setAttribute("admin", userInfo.get("admin"));
			
			return "/auth/loginOk.jsp";
			
		}else if(login==0) {
			request.setAttribute("message", "��й�ȣ�� �߸��Ǿ����ϴ�.");
		}else {
			request.setAttribute("message", "���̵� �������� �ʽ��ϴ�.");
		}
		
		
		return "/auth/loginError.jsp";
	}

}
