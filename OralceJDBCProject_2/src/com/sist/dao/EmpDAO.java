package com.sist.dao;
// 게시판 = 댓글 : 테이블마다 독립적 -> 재사용 -> 결합

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.sist.vo.*;

public class EmpDAO {
	
	
	private Connection conn;
	private PreparedStatement ps;
	// 변경사항이 없다
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	/*
		jdbc : 업체명:@IP:port:데이터베이스명
		jdbc:mysql:@localhost:3306:mydb
	*/
	// 각 user 당 1개의 DAO를 사용이 가능 -> 싱글톤
	public static EmpDAO dao;
	
	// 드라이버 등록
	public EmpDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
				// ojdbc11 -> oracle.jdbc.OracleDriver
		} catch (Exception e) {}
	}
	
	// 싱글톤 -> Spring : 등록한 모든 클래스가 싱글톤
	public static EmpDAO newInstance()
	{
		if(dao == null)
			dao = new EmpDAO();
		return dao;
	}
	
	// 연결
	public void getConnection()
	{
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {}
	}

	
	// 해제
	public void disConnection()
	{
		try {
			if(ps != null)
				ps.close();		// 송수신 닫기
			if(conn != null)
				conn.close();	// 전화 닫기
		} catch (Exception e) {}
	}
	
	// JOIN
	public List<EmpVO> empListData()
	{
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			getConnection();
			String sql = "SELECT empno, ename, job, hiredate, sal, dname, loc, grade "
					+ "FROM emp, dept, salgrade "
					+ "WHERE emp.deptno = dept.deptno "
					+ "AND sal BETWEEN losal AND hisal";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				EmpVO vo = new EmpVO();
				vo.setEmpno(rs.getInt(1));
				// vo.setEmpno(rs.getInt("empno")) -> MyBatis
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				vo.getDvo().setDname(rs.getString(6));
				vo.getDvo().setLoc(rs.getString(7));
				vo.getSvo().setGrade(rs.getInt(8));
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
	
	// 페이지 나누기 ROWNUM / ROW_NUMBER()
	
}
