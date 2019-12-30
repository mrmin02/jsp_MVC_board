package article.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AnswerDataBean;
import bean.DBBean;
import main.action.Action;

public class AddAnswerAction implements Action{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("AddAnswerAction");
		String article_id = request.getParameter("article_id");
		String user_id = request.getParameter("user_id");
		String answer = request.getParameter("answer");
		
		DBBean db = DBBean.getInstance();
		int state = db.addAnswer(article_id,user_id,answer);
		String result = "성공";
		if(state == -1) {
			result = "실패";
		}
		ArrayList<AnswerDataBean> answerList = db.answerList(article_id);
		request.setAttribute("answer", answerList);
		return "/article/addAnswerOk.jsp";
	}
	
}
