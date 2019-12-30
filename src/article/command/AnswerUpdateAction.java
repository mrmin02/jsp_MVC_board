package article.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AnswerDataBean;
import bean.DBBean;
import main.action.Action;

public class AnswerUpdateAction implements Action{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("AnswerUpdateAction");
		String article_id = request.getParameter("article_id");
		String answer_id = request.getParameter("answer_id");
		String answer = request.getParameter("newAnswer");
		DBBean db = DBBean.getInstance();

		int state = db.updateAnswer(answer_id,answer);
		String result = "성공";
		if(state == -1) {
			result = "실패";
		}
		ArrayList<AnswerDataBean> answerList = db.answerList(article_id);
		request.setAttribute("answer", answerList);
		return "/article/addAnswerOk.jsp";
	}

}
