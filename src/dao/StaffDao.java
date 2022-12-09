package user.dao;

import user.JDBCUtil;
import user.StaffFrame;
import user.domin.StaffItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {

    public List<StaffItem> queryAllData() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<StaffItem> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from user";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                StaffItem f = new StaffItem();
                f.setStaffNum(rs.getString("staffNum"));
                f.setStaffName(rs.getString("staffName"));
                f.setPasswd(rs.getString("passwd"));
                f.setType(rs.getString("type"));
                list.add(f);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        JDBCUtil.release(stmt, rs, conn);
        return list;
    }

    public void addStaffItem(StaffItem staffItem) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int a = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into user values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, staffItem.getStaffNum());
            pstmt.setString(2, staffItem.getStaffName());
            pstmt.setString(3, staffItem.getPasswd());
            pstmt.setString(4, staffItem.getType());
            a = pstmt.executeUpdate();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        JDBCUtil.release(pstmt, null, conn);
    }

    public void deleteStaff(String staffNum) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int a = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "delete from user where staffNum=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, staffNum);
            a = pstmt.executeUpdate();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        JDBCUtil.release(pstmt, null, conn);
    }

    public void updateStaffItem(StaffItem staffItem) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int a = 0;
        try {
            conn = JDBCUtil.getConnection();
            // 获得Statement对象
            stmt = conn.createStatement();
            // 发送SQL语句
            String sql = "UPDATE user set staffNum='" + staffItem.getStaffNum()
                    + "',staffName='" + staffItem.getStaffName() + "',passwd='"
                    + staffItem.getPasswd() + "',type='" + staffItem.getType()
                    + "' WHERE staffNum=" + staffItem.getStaffNum();
            a = stmt.executeUpdate(sql);

        } catch (Throwable e) {
            e.printStackTrace();
        }
        JDBCUtil.release(stmt, rs, conn);
    }

    public List<StaffItem> query() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<StaffItem> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = StaffFrame.getOrderS();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                StaffItem f = new StaffItem();
                f.setStaffNum(rs.getString("staffNum"));
                f.setStaffName(rs.getString("staffName"));
                f.setPasswd(rs.getString("passwd"));
                f.setType(rs.getString("type"));
                list.add(f);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        JDBCUtil.release(stmt, rs, conn);
        return list;
    }
}