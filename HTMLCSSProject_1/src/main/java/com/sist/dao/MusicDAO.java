package com.sist.dao;

import java.util.*;

import com.sist.vo.MusicVO;

import java.sql.*;

public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static MusicDAO dao;
	private final String URL="jdbc:oracle:thin:@localhost:1521:xe";
	
	public MusicDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {}
		
	}
	public static MusicDAO newInstance()
	{
		if(dao == null)
			dao = new MusicDAO();
		return dao;
	}
	
	public void getConnection()
	{
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {}
	}
	
	public void disConnection()
	{
		try {
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
		} catch (Exception e) {}
	}

	// 클래스화 -> 라이브러리(MyBatis/JPA)
	/*
		public List<MusicVO> musicListData(int cno)
		{
			return my.selectList(1);
		}
		-> List<MusicVO> findAll();
						 --------
						 findByCno
						 findByBetween
	*/
	public List<MusicVO> musicListData(int cno)
	{
		List<MusicVO> list = new ArrayList<MusicVO>();
		
		try {
			getConnection();
			String sql = "SELECT no, cno, poster, title, singer "
					+ "FROM genie_music "
					+ "WHERE cno=? "
					+ "ORDER BY no";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				MusicVO vo = new MusicVO();
				vo.setNo(rs.getInt(1));
				vo.setCno(rs.getInt(2));
				vo.setPoster(rs.getString(3));
				vo.setTitle(rs.getString(4));
				vo.setSinger(rs.getString(5));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
}

