package com.sist.dao;
import java.sql.*;
import java.util.*;
import javax.sql.*;        // DataSource -> 데이터베이스 정보

import com.sist.vo.FoodDTO;

import javax.naming.*;    // Context -> jdbc/oracle
public class FoodDAO {
    private Connection conn;
    private PreparedStatement ps;
    private static FoodDAO dao;
    
    // 싱글톤 패턴 (인스턴스 생성 및 관리)
    public static FoodDAO newInstance() {
        if(dao == null)
            dao = new FoodDAO();
        return dao;
    }

    // 미리 연결된 Connection 객체 얻기 (JNDI를 통해)
    public void getConnection()
    {
        try {
            Context init = new InitialContext();            
            DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle"); 
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 반환 -> 다시 재사용이 가능
    public void disConnection()
    {
        try {
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace(); // 해제 오류도 확인해야 합니다.
        }
    }
    
    // 기능
    public List<FoodDTO> foodListData(int page){
        List<FoodDTO> list = new ArrayList<FoodDTO>();
        
        try {
            getConnection();
            String sql = "SELECT fno, poster, name, address, type "
                    + "FROM menupan_food "
                    + "ORDER BY fno ASC "
                    + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (page * 20) - 20);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                FoodDTO vo = new FoodDTO();
                vo.setFno(rs.getInt(1));
                vo.setPoster(rs.getString(2));
                vo.setName(rs.getString(3));
                vo.setAddress(rs.getString(4));
                vo.setType(rs.getString(5));
                list.add(vo);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally 
        {
            disConnection();
        }
        return list;
    }
    
    public int foodTotalPage()
    {
        int total = 0;
        
        try {
            getConnection();
            String sql = "SELECT CEIL(COUNT(*)/20.0) "
                        + "FROM menupan_food";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            total = rs.getInt(1);
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disConnection();
        }
        
        return total;
    }
    
}