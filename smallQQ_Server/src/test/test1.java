package test;

import net.sf.json.JSONObject;
import serverDb.DBManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.mysql.cj.exceptions.PasswordExpiredException;
import com.mysql.cj.xdevapi.SqlDataResult;
import net.sf.json.JSONArray;
import serverDb.UserInfo;

import java.beans.PersistenceDelegate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * 注册用户登记
 *
 *
 *  * @param uid
 *  * @param name
 * @return
 * @throws SQLException
 */
public class test1  {

    private Socket socket=null;



    public static void main(String[] args){
        // 登录操作
        Connection conn = null;
        InputStream in = null;
        OutputStream out = null;


        System.out.print("aaaaaaaaa"+"\n");

            try {

                conn = DBManager.getConnection();
//                PreparedStatement pst = conn.prepareStatement("insert into users(uid,name,password) values(99,99,99)");
                PreparedStatement pst5 = conn.prepareStatement("select uid from users");
                ResultSet rs5 = pst5.executeQuery();

                System.out.print(rs5.getString(1)+"\n");

                System.out.print("aaaaaaaaa"+"\n");


                System.out.print("登录成功"+"\n");


            }catch (SQLException a){
                System.out.print("无法连接数据库"+"\n");
            }catch (Exception e) {
                System.out.print("未知错误"+"\n");
            }

         finally {
            try
            {
            }catch(Exception e2){

            }

        }

    }


}