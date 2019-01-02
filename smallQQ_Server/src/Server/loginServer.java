package Server;
import net.sf.json.JSONArray;
import serverDb.PasswordException;
import serverDb.UserInfo;
import serverDb.UsernameNotFoundException;
import serverDb.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.*;
import net.sf.json.JSONObject;

/**
 * 登陆服务器
 *
 *
 */


public class loginServer implements Runnable{

    private Socket socket=null;

    public loginServer(Socket socket)
    {
        this.socket=socket;
    }


    @Override
    public void run()
    {
        // 登录操作
        InputStream in = null;
        OutputStream out = null;
        try
        {
            in = socket.getInputStream();
            out = socket.getOutputStream();

            // 等待客户端信息
            byte[] bytes = new byte[1024];
            int len = in.read(bytes);
            String json_str = new String(bytes, 0, len);


            //  json解析器
            JSONObject json = JSONObject.fromObject(json_str);
            String username = json.getString("username");
            String password = json.getString("password");



            try
            {
                String uid = null;
                uid = new UserService().login(username, password);

                out.write("{\"state\":0,\"msg\":\"登录成功!\"}".getBytes());
                out.flush();


                while (true)
                {
                    bytes = new byte[1024];
                    len = in.read(bytes);
                    String command = new String(bytes, 0, len);
                    //循环等待信息
                    if (command.equals("U0001")) {
                        // 更新好友列表
                        Vector<UserInfo> userinfos=new UserService().getFriendList(uid);
                        out.write(JSONArray.fromObject(userinfos).toString().getBytes());
                        out.flush();
                    }else if (command.equals("U0003")) {
                        UserInfo userinfo2 = new UserService().getUserinfo(uid);
                        out.write(JSONObject.fromObject(userinfo2).toString().getBytes());
                        out.flush();
                    }
                    else if (command.equals("EXIT")) {
                        // 退出用户登录
                        return;
                    }
                }

            }catch (UsernameNotFoundException e) {
                out.write("{\"state\":2,\"msg\":\"账户名错误!\"}".getBytes());
                out.flush();
                return;
            } catch (PasswordException e) {
                out.write("{\"state\":1,\"msg\":\"用户密码错误!\"}".getBytes());
                out.flush();
                return;
            } catch (SQLException e) {
                out.write("{\"state\":4,\"msg\":\"未知错误!\"}".getBytes());
                out.flush();
                return;
            }


        }catch(Exception e) {

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
        ExecutorService executor=Executors.newFixedThreadPool(1000);

        ServerSocket server=new ServerSocket(4001);

        while (true)
        {
            Socket socket=server.accept();
            socket.setSoTimeout(10000);
            executor.execute(new loginServer(socket));
        }

    }




}
