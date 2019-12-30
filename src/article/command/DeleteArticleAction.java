package article.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AnswerDataBean;
import bean.DBBean;
import main.action.Action;

public class DeleteArticleAction implements Action{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String answer_id = request.getParameter("answer_id");
		String article_id = request.getParameter("article_id"); 
		DBBean db = DBBean.getInstance();
		int state = db.deleteArticle(answer_id);
		
		if(state==1) {
			System.out.println("UpdateArticleAction 에러 감지 (글 수정에 에러가 발생함)");
		}
		
		ArrayList<AnswerDataBean> answerList = db.answerList(article_id);
		request.setAttribute("answer", answerList);
		return "/article/addAnswerOk.jsp";
	}
	
}
