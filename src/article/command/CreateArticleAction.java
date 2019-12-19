package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DBBean;
import main.action.Action;

public class CreateArticleAction implements Action{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("CreateArticleAction");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		DBBean db = DBBean.getInstance();
		int state = db.addArticle(title,content,id);
		return "/article/index.jsp";
	}

}
