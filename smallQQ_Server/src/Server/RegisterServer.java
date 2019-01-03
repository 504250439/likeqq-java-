package Server;

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
public class RegisterServer implements Runnable{

    private Socket socket = null;

    public RegisterServer(Socket socket) {
        this.socket = socket;
    }

    Connection conn = null;
    InputStream in = null;
    OutputStream out = null;
    @Override
    public void run()
    {

            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();

                // 等待客户端信息
                byte[] bytes = new byte[1024];
                int len = in.read(bytes);
                String json_str = new String(bytes, 0, len);



                //  json解析器
                JSONObject json = JSONObject.fromObject(json_str);
                String uid = json.getString("uid");
                String name = json.getString("name");
                String password = json.getString("password");
                String img="Def";
                String sex="Def";
                System.out.print(uid +" "+name+" "+password);

                try {

                    conn = DBManager.getConnection();
                    PreparedStatement pst = conn.prepareStatement("INSERT INTO users (uid,name,password,img,sex) values(?,?,?,?,?)");
//                    PreparedStatement pst = conn.prepareStatement("SELECT u.uid FROM USERS WHERE UID=?");




                    pst.setString(1, uid);
                    pst.setString(2, name);
                    pst.setString(3, password);
                    pst.setString(4, img);
                    pst.setString(5, sex);
                    pst.executeQuery();



                    out.write("{\"state\":98,\"msg\":\"成功录入\"}".getBytes());
                    out.flush();
                } catch (SQLException e) {
                    out.write("{\"state\":100,\"msg\":\"无法连接数据库\"}".getBytes());
                    out.flush();
                } catch (Exception e) {
                    out.write("{\"state\":99,\"msg\":\"未知错误\"}".getBytes());
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally
            {
                try
                {
                    in.close();
                    out.close();
                    socket.close();
                }catch(Exception e2){

                }

            }

    }




    public static void openServer() throws Exception
    {
        //线程池
        ExecutorService executor= Executors.newFixedThreadPool(200);

        ServerSocket server=new ServerSocket(4123);


        while (true)
        {
            Socket socket=server.accept();
            socket.setSoTimeout(10000);
            executor.execute(new RegisterServer(socket));

        }

    }

}