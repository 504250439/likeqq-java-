package server;

import util.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.*;
import java.net.Socket;
import net.sf.json.JSONObject;

import static util.Config.friend_json_data;


/**
 *  登陆服务
 *  与服务器的状态
 *
 *
 */


public class NetService implements Runnable{


    //建立单例类
    private NetService(){}

    private static NetService netService = new NetService();

    public static NetService getNetService()
    {
        return netService;
    }




    @Override
    public void run()
    {
        try {

            //获取所有好友列表
            output.write("U0001".getBytes());
            output.flush();
            byte[] bytes = new byte[1024 * 10];
            int len = input.read(bytes);
            String jsonstr = new String(bytes, 0, len);
            ////////////////////////////////////////////
            // 解析好友列表
            Config.resolve_friend_json_data(jsonstr);
            System.out.println("好友资料:" + Config.friend_json_data);

            while (run) {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            }
        } catch (Exception e) {
            run = false;
        }
    }

    private Socket socket = null;
    private InputStream input = null;
    private OutputStream output = null;
    private Thread thread = null;
    private boolean run = false;

    public JSONObject login() throws IOException,NullPointerException
    {
        socket =new Socket(Config.IP,Config.LOGIN_POST);
        input = socket.getInputStream();
        output=socket.getOutputStream();
        String json_str = "{\"username\":\"" + Config.username + "\",\"password\":\"" + Config.password + "\"}";


        // 开始与服务传递消息
        output.write(json_str.getBytes());
        output.flush();

        // 等待服务器回执消息
        byte[] bytes = new byte[1024];
        int len = input.read(bytes);

        json_str = new String(bytes, 0, len+1);
        JSONObject json = JSONObject.fromObject(json_str);


        if ((json.getInt("state")== 0)){
            // 开启持续的网络连接服务
            if (thread != null) {
                // 询问线程是否还活着
                if (thread.getState() == Thread.State.RUNNABLE) {
                    run = false;// 终止线程运行
                    try {
                        thread.stop();
                    } catch (Exception e) {
                    }
                }
            }

            // 重新开线程与服务器保持通讯
            thread = new Thread(this);
            run = true;
            thread.start();
        }

        return json;
    }

}
