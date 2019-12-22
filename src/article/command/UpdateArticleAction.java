package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DBBean;
import main.action.Action;

public class UpdateArticleAction implements Action{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("UpdateArticleAction");
		String id = request.getParameter("id");
		String title= request.getParameter("title");
		String content= request.getParameter("content");
		DBBean db = DBBean.getInstance();
		int state = db.updateArticle(title,content,id);
		
		if(state==1) {
			System.out.println("UpdateArticleAction 에러 감지 (글 수정에 에러가 발생함)");
		}
		return "/article/show.jsp?id="+id;
	}

}
