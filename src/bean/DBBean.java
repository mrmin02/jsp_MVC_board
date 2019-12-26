package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBBean {
	private static DBBean instance = new DBBean();
	
	public static DBBean getInstance() {
		return instance;
	}
	
	private DBBean() {}
	
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx=(Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	public void addUser(String user_id, String pwd, String name, String email, String phone) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		conn = getConnection();
		String sql="insert into users(id,user_id,password,name,email,admin,phone) values(user_incre.NEXTVAL,?,?,?,?,0,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_id);
		pstmt.setString(2, pwd);
		pstmt.setString(3, name);
		pstmt.setString(4, email);
		pstmt.setString(5, phone);
		
		
		pstmt.executeQuery();
		System.out.println("DB 쿼리 완료");
	}
	public int userLogin(String id, String pwd)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=0;
		try {
			conn = getConnection();
			String sql="select * from users where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbPwd = rs.getString("password");
				if(dbPwd.contentEquals(pwd)){
					System.out.println("로그인 성공");
					result=1;
				}else{
					System.out.println("비밀번호 틀림");
					result=0;
				}
				
			}else{
				System.out.println("존재하지 않는 아이디");
				result=-1;
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null) try {rs.close();}catch(SQLException ex) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn !=null) try{conn.close();} catch(SQLException ex) {}
		}
		return result;
	}
	public UserDataBean getUserInfo(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = getConnection();
		String sql="select * from users where user_id =?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		rs.next();
		
//		ArrayList<UserDataBean> userInfo = new ArrayList<UserDataBean>();
		
		UserDataBean data = new UserDataBean();
		data.setName(rs.getString("name"));
		data.setAdmin(rs.getString("admin"));
		data.setEmail(rs.getString("email"));
		data.setPhone(rs.getString("phone"));
		data.setId( Integer.parseInt(rs.getString("id")));
		data.setUser_id(rs.getString("user_id"));
		
//		userInfo.add(data);
		
		return data;
	}

	public int addArticle(String title, String content, String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int state = 0;
		
		conn = getConnection();
		Date date= new Date();
		SimpleDateFormat form = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = form.format(date);
		
		String sql="insert into articles(id,title,content,user_id,created_time) values(article_incre.NEXTVAL,?,?,?,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, id);
		pstmt.setString(4, time);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			state=0;
			System.out.println("새 글 등록 성공");
		}else {
			state = -1;
			System.out.println("새 글 등록 실패");
		}
		return state;
	}
	public int updateArticle(String title, String content,String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = getConnection();
		String sql="update articles set title=?,content=? where id = ? ";
		
		int state= 0;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println("수정 완료");
		}else {
			System.out.println("수정 오류 발생");
			state= 1;
		}
		return state;
	}
	public int deleteArticle(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = getConnection();
		String sql="delete from articles where id=?";
		
		int state= 0;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 오류 발생");
			state= 1;
		}
		return state;
	}
	public int addAnswer(String article_id, String user_id, String answer) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = getConnection();
		String sql="insert into answers(id,user_id, article_id, answer)  values(ans_incre.NEXTVAL,?,?,?)";
		
		int state= 0;
		System.out.println(user_id+"  "+article_id+"  "+answer);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_id);
		pstmt.setString(2, article_id);
		pstmt.setString(3, answer);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println("새로운 댓글 등록 완료");
		}else {
			System.out.println("댓글 등록 실패");
			state= 1;
		}
		return state;
	}
	public ArrayList<AnswerDataBean> answerList(String article_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = getConnection();
		String sql="select * from answers where article_id = ?  order by id ASC";
		
		int state= 0;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, article_id);
		rs = pstmt.executeQuery();
		ArrayList<AnswerDataBean> dataList = new ArrayList<AnswerDataBean>();
		
		while(rs.next()) {
//			System.out.println(rs.getString("id"));
			AnswerDataBean data = new AnswerDataBean();
			data.setId(rs.getString("id"));
			data.setArticle_id(rs.getString("article_id"));
			data.setUser_id(rs.getString("user_id"));
			data.setText(rs.getString("answer"));
			dataList.add(data);
		}
		return dataList;
	}
	public int updateAnswer(String answer_id,String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int state = 0;
		
		try {
			conn = getConnection();
			String sql="update answers set answer=? where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, text);
			pstmt.setString(2, answer_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				state=0;
				System.out.println("새 글 등록 성공");
			}else {
				state = -1;
				System.out.println("새 글 등록 실패");
			}
			return state;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	public int deleteAnswer(String answer_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int state = 0;
		
		try {
			conn = getConnection();
			String sql="delete answers where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, answer_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				state=0;
				System.out.println("새 글 등록 성공");
			}else {
				state = -1;
				System.out.println("새 글 등록 실패");
			}
			return state;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
