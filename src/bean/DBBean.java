package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
		String sql="insert into users(user_id,password,name,email,admin,phone) values(?,?,?,?,0,?)";
		
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
	public Map<String, String> getUserInfo(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = getConnection();
		String sql="select * from users where user_id =?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		rs.next();
		
		Map<String, String> user = new HashMap<String, String>();
		
		user.put("admin", rs.getString("admin"));
		user.put("name", rs.getString("name"));
		user.put("email", rs.getString("email"));
		user.put("phone", rs.getString("phone"));
		
		return user;
	}
	public void getArticleList()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = getConnection();
		String sql="select * from articles order by created_time DESC";
		
//		pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, id);
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
		
		String sql="insert into articles(title,content,user_id,created_time) values(?,?,?,?)";
		
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
}
