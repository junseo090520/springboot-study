package com.mirim.board.util;

import java.sql.*;

public class Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/board";

        try(Connection conn = DriverManager.getConnection(url,"root","password")){
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM posts WHERE id = ?");
            pstmt.setLong(1,1L);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("title"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
