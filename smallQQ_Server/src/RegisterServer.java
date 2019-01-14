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
public class RegisterServer implements Runnable {

    private Socket socket=null;

    public RegisterServer(Socket socket)
    {
        this.socket=socket;
    }


    @Override
    public void run() {
        // 登录操作
        Connection conn = null;
        InputStream in = null;
        OutputStream out = null;


        try{
            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();



                System.out.print("aaaaaaaaa"+"\n");

                // 等待客户端信息
                byte[] bytes = new byte[1024];
                int len = in.read(bytes);
                String json_str = new String(bytes, 0, len);


                System.out.print("aaaaaaaaa"+"\n");
                conn = DBManager.getConnection();


//                PreparedStatement pst = conn.prepareStatement("insert into users(uid,name,password) values(99,99,99)");

                PreparedStatement pst5 = conn.prepareStatement("insert into users(uid,name,password) values(99,99,99)");
                ResultSet rs5 = pst5.executeQuery();



//                System.out.print(rs5.getString(1)+"\n");

                System.out.print("aaaaaaaaa"+"\n");







                System.out.print("登录成功"+"\n");

                out.write("{\"state\":0,\"msg\":\"登录成功!\"}".getBytes());
                out.flush();

            }catch (IOException e){
                System.out.print("写入错误"+"\n");

                out.write("{\"state\":101,\"msg\":\"写入错误\"}".getBytes());
                out.flush();
            }catch (SQLException a){
                System.out.print("无法连接数据库"+"\n");

                out.write("{\"state\":100,\"msg\":\"无法连接数据库\"}".getBytes());
                out.flush();
            }catch (Exception e) {
                System.out.print("未知错误"+"\n");

                out.write("{\"state\":99,\"msg\":\"未知错误\"}".getBytes());
                out.flush();
            }

        }catch (IOException ff){


        } finally {
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
        ExecutorService executor=Executors.newFixedThreadPool(1000);

        ServerSocket server=new ServerSocket(4123);

        while (true)
        {
            Socket socket=server.accept();
            socket.setSoTimeout(10000);
            executor.execute(new RegisterServer(socket));
        }

    }




}