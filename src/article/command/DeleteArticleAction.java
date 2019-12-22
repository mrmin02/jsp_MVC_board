package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DBBean;
import main.action.Action;

public class DeleteArticleAction implements Action{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		DBBean db = DBBean.getInstance();
		int state = db.deleteArticle(id);
		
		if(state==1) {
			System.out.println("UpdateArticleAction 에러 감지 (글 수정에 에러가 발생함)");
		}
		
		return "/article/index.jsp";
	}
	
}
