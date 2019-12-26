package article.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AnswerDataBean;
import bean.DBBean;
import main.action.Action;

public class AnswerDeleteAction implements Action{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("AddAnswerAction");
		String article_id = request.getParameter("article_id");
		String answer_id = request.getParameter("answer_id");
		DBBean db = DBBean.getInstance();
		int state = db.deleteAnswer(answer_id);
		String result = "성공";
		if(state == -1) {
			result = "실패";
		}
		ArrayList<AnswerDataBean> article = db.answerList(article_id);
		request.setAttribute("article", article);
		return "/article/addAnswerOk.jsp";
	}

}
