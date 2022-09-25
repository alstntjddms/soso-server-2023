package com.soso_server.tmp.Board;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO_jdbc {

    private DataSource dataSource;

    public void init() {
        try {
            System.out.println("PostDAO.init");
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/RadiusXADS");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insert(PostDTO postDTO) {

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = -1;
        try {
            Long datetime = System.currentTimeMillis();

            System.out.println("PostDAO1.save");
            System.out.println("postDTO = " + postDTO);
            String SQL2 = "SELECT MAX(NUM) FROM TEST.POST";
            String SQL1 = "INSERT INTO TEST.POST(NUM, TITLE, CATEGORYNUM, WRITEBY, TEXT, WRITEAT) values (POST_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(SQL1);

            pstmt.setString(1, postDTO.getTitle());
            pstmt.setInt(2, postDTO.getCategorynum());
            pstmt.setString(3, postDTO.getWriteBy());
            pstmt.setString(4, postDTO.getText());
            pstmt.setTimestamp(5, new Timestamp(datetime));
            pstmt.executeUpdate();

            pstmt = connection.prepareStatement(SQL2);
            rs = pstmt.executeQuery();

            while (rs.next()){
                result = rs.getInt(1);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }finally {
            if (rs != null) try {
                rs.close();
            } catch(Exception e) {
            }

            if (pstmt != null) try {
                pstmt.close();
            } catch(Exception e) {

            }
            if (connection != null) try {
                connection.close();
            } catch(Exception e) {

            }
        }

    }

    public void delete(Integer postNum) {
        try {
            System.out.println("PostDAO.delete");
            String SQL1 = "DELETE FROM TEST.comment WHERE NUM = " + postNum;
            String SQL2 = "DELETE FROM TEST.post WHERE NUM = " + postNum;
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL1);
            pstmt.execute();
            pstmt.close();

            pstmt = connection.prepareStatement(SQL2);
            pstmt.execute();
            pstmt.close();

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(PostDTO postDTO) {
        try {
            System.out.println("PostDAO.update");
            System.out.println("postDTO = " + postDTO);
            String SQL = "UPDATE TEST.post SET title=?, categorynum=?, writeBy=?, text=?, writeAt=? WHERE NUM = " + postDTO.getNum();
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);

            pstmt.setString(1, postDTO.getTitle());
            pstmt.setInt(2, postDTO.getCategorynum());
            pstmt.setString(3, postDTO.getWriteBy());
            pstmt.setString(4, postDTO.getText());
            Long datetime = System.currentTimeMillis();
            pstmt.setTimestamp(5, new Timestamp(datetime));

            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PostDTO> findAll() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM TEST.post ORDER BY NUM ASC"
            );
            ResultSet rs = pstmt.executeQuery();
            List<PostDTO> posts = new ArrayList<>();
            while (rs.next()) {
                PostDTO postDTO = new PostDTO();
                postDTO.setNum(rs.getInt("num"));
                postDTO.setTitle(rs.getString("title"));
                postDTO.setCategorynum(rs.getInt("categorynum"));
                postDTO.setWriteBy(rs.getString("writeBy"));
                postDTO.setText(rs.getString("text"));
                postDTO.setWriteAt(rs.getTimestamp("writeAt"));
                posts.add(postDTO);

            }
            rs.close();
            connection.close();
            pstmt.close();
            System.out.println("posts = " + posts);
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PostDTO findOne(Integer postNum) {
        try {
            System.out.println("PostDAO.findOne");
            System.out.println("postNum = " + postNum);
            String SQL = "SELECT * FROM TEST.post WHERE NUM = " + postNum;
            System.out.println("SQL = " + SQL);
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            PostDTO postDTO = new PostDTO();
            while (rs.next()) {
                postDTO.setNum(rs.getInt("num"));

                postDTO.setTitle(rs.getString("title"));
                postDTO.setCategorynum(rs.getInt("categorynum"));

                postDTO.setWriteBy(rs.getString("writeBy"));
                postDTO.setText(rs.getString("text"));
                postDTO.setWriteAt(rs.getTimestamp("writeAt"));
            }

            System.out.println("postDTO = " + postDTO);
            rs.close();
            connection.close();
            pstmt.close();
            return postDTO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
