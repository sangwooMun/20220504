package co.micol.notice.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.notice.dao.DataSource;
import co.micol.notice.service.NoticeService;
import co.micol.notice.service.NoticeVO;

public class NoticeSeriveImpl implements NoticeService {
	// 사용하기 위해 나에게 연결
	private DataSource dao = DataSource.getInstance();
	private Connection conn; // = dao.getConnection();
	
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<NoticeVO> noticeSelectList() {
		// 전체 조회
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		NoticeVO vo;
		String sql = "SELECT * FROM NOTICE";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new NoticeVO();
				vo.setId(rs.getInt("id"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setWdate(rs.getDate("wdate"));
				vo.setHit(rs.getInt("hit"));
				list.add(vo);
				
				hitUpate(vo.getId()); // 조회수 증가
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	@Override
	public NoticeVO noticeSelect(NoticeVO vo) {
		// 글 세부 조회(하나의 글을 읽음)
		String sql = "SELECT * FROM NOTICE WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo = new NoticeVO();
				vo.setId(rs.getInt("id"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setSubject(rs.getString("subject"));
				vo.setWdate(rs.getDate("wdate"));
				vo.setHit(rs.getInt("hit"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}

	@Override
	public int noticeINsert(NoticeVO vo) {
		// 글 등록
		int n = 0;
		String sql = "INSERT INTO NOTICE VALUES(B_ID.NEXTVAL,?,?,?,SYSDATE,0";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getWriter());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getSubject());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int noticeUpdate(NoticeVO vo) {
		// 글 수정
		int u = 0;
		String sql = "UPDATE notice set subject = ? WHERE id = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getSubject());
			psmt.setInt(2, vo.getId());
			u = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public int noticeDelete(NoticeVO vo) {
		// 글 삭제
		String sql = "";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private void close() { // 연결의 반대, 닫을때
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();;
		}
	}
	
	private void hitUpate(int id) { // 조회수 증가
		String sql = "UPDATE NOTICE SET HIT = HIT + 1 WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
