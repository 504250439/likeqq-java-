package serverDb;
import com.mysql.cj.exceptions.PasswordExpiredException;
import com.mysql.cj.xdevapi.SqlDataResult;
import net.sf.json.JSONArray;

import java.beans.PersistenceDelegate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *  验证登陆
 *
 *
 */

public class UserService {


    public String login(String id, String password)
            throws UsernameNotFoundException, PasswordException, SQLException
    //异常:找不到姓名 密码错误 数据库连接异常
    {
        Connection conn = null;
        String sql = "SELECT * FROM users where uid=?";
        try {
            conn = DBManager.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    return rs.getString(1);
                } else {
                    throw new PasswordExpiredException();
                }
            } else {
                throw new UsernameNotFoundException();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }
    }


    public UserInfo getUserinfo(String uid) throws SQLException {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            pst.setString(1, uid);
            ResultSet rs = pst.executeQuery();
            UserInfo userInfo2 = new UserInfo();
            if (rs.next()) {
                userInfo2.setUid(rs.getString("uid"));
                userInfo2.setname(rs.getString("name"));
                userInfo2.setImg(rs.getString("img"));
            }


            return userInfo2;

        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }

    }


    /**
     * 更新好友列表
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public Vector<UserInfo> getFriendList(String uid) throws SQLException {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement pst = conn.prepareStatement("sELECT u.uid,u.img,u.name from friend f  join users u on u.uid=f.friendid and f.uid=? "
            );
            pst.setString(1, uid);
            ResultSet rs = pst.executeQuery();
            Vector<UserInfo> vector = new Vector();
            while (rs.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setUid(rs.getString(1));
                userInfo.setImg(rs.getString(2));
                userInfo.setname(rs.getString(3));
                //System.out.print(rs.getString(3));

                vector.add(userInfo);
            }

            return vector;
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }

    }


    public Vector<GroupInfo> getGroupList(String uid) throws SQLException {
        Connection conn = null;
        Connection conn2= null;

        try {
            conn = DBManager.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT groupid,groupname FROM grouplist where uid=?");
            pst.setString(1, uid);
            ResultSet rs = pst.executeQuery();
            Vector<GroupInfo> vector = new Vector();


            String[] a=new String[10];
            String s="2";
            int i=0;
            while (rs.next()) {
                GroupInfo groupInfo = new GroupInfo();
                groupInfo.setGroupid(rs.getString(1));
                groupInfo.setGroupname(rs.getString(2));


                //////////////////////
                conn2 = DBManager.getConnection();
                PreparedStatement pst2 = conn2.prepareStatement("SELECT uid FROM grouplist where groupid=? and uid !=?");
                pst2.setString(1,rs.getString(1));
                pst2.setString(2,uid);
                ResultSet rs2 = pst2.executeQuery();
                i=0;
                while (rs2.next()) {
                    //System.out.print(rs2.getString(1)+"\n");
                    groupInfo.setGroupMember(i++,rs2.getString(1));
                }
                /////////////////

                vector.add(groupInfo);
            }



            return vector;
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }

    }


    public UserInfo findFriend(String uid) throws SQLException {
        Connection conn = null;
        try {
            System.out.print("zhixing"+"\n");

            conn = DBManager.getConnection();
            PreparedStatement pst = conn.prepareStatement("select uid,name,img from users where uid=?");
            pst.setString(1, uid);
            ResultSet rs = pst.executeQuery();


            System.out.print("zhixing"+"\n");
            System.out.print(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+"\n");

            UserInfo userInfo = new UserInfo();
            userInfo.setUid(rs.getString(1));
            userInfo.setname(rs.getString(2));
            userInfo.setImg(rs.getString(3));

            System.out.print(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+"\n");

            return userInfo;
        } catch (SQLException e) {
            System.out.print("shujuhu"+"\n");
            throw e;
        } finally {
            conn.close();
        }



    }









}



