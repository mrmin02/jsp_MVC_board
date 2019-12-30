package article.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AnswerDataBean;
import bean.DBBean;
import main.action.Action;

public class AnswerLodingAction implements Action{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("AnswerLodingAction");
		String article_id =request.getParameter("article_id");
	
		DBBean db = DBBean.getInstance();
		
		ArrayList<AnswerDataBean> answerList = db.answerList(article_id);
		request.setAttribute("answer", answerList);
		return "/article/addAnswerOk.jsp";
	}
	
}
