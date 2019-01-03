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




    public String login(String id,String password)
            throws UsernameNotFoundException, PasswordException, SQLException
            //异常:找不到姓名 密码错误 数据库连接异常
    {
        Connection conn=null;
        String sql="SELECT * FROM users where uid=?";
        try
        {
            conn=DBManager.getConnection();
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,id);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                if(rs.getString("password").equals(password))
                {
                    return rs.getString(1);
                }
                else
                {
                    throw new PasswordExpiredException();
                }
            }
            else
            {
                throw new UsernameNotFoundException();
            }
        }
        catch (SQLException e)
        {
            throw e;
        }
        finally
        {
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
     * @param uid
     * @return
     * @throws SQLException
     */
    public Vector<UserInfo> getFriendList(String uid) throws SQLException
    {
        Connection conn=null;
        try
        {
            conn=DBManager.getConnection();
            PreparedStatement pst=conn.prepareStatement("sELECT u.uid,u.img,u.name from friend f  join users u on u.uid=f.friendid and f.uid=? "
                   );
            pst.setString(1,uid);
            ResultSet rs=pst.executeQuery();
            Vector<UserInfo> vector=new Vector();
            while(rs.next())
            {
                UserInfo userInfo=new UserInfo();
                userInfo.setUid(rs.getString(1));
                userInfo.setImg(rs.getString(2));
                userInfo.setname(rs.getString(3));
                //System.out.print(rs.getString(3));

                vector.add(userInfo);
            }

            return vector;
        }
        catch(SQLException e)
        {
            throw e;
        }
        finally {
            conn.close();
        }

    }

}


//    public static void main(String[] args) {
//        try {
//            new UserService().login("2","2");
//            System.out.println("成功");
//            String iddd="2";
//            Vector<UserInfo> userinfos=new UserService().getFriendList(iddd);
//            System.out.print(JSONArray.fromObject(userinfos).toString().getBytes());
//
//
//        } catch (UsernameNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (PasswordException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//
//
//    }

