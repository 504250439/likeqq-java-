package server;

import net.sf.json.JSONObject;
import util.Config;
import view.loginWindow;
import view.registerWindow;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class registerServer {

    private Socket socket = null;
    private InputStream input = null;
    private OutputStream output = null;
    public int status;

    public registerServer(String uid, String name, String password) throws IOException{
        status=getreturn(uid, name, password);
    }



    public int getreturn(String uid, String name, String password) throws IOException, NullPointerException {
        socket = new Socket(Config.IP, Config.REGISTER_POST);
        input = socket.getInputStream();
        output = socket.getOutputStream();
        String json_str = "{\"uid\":\"" + uid + "\",\"name\":\"" + name + "\",\"password\":\"" + password + "\"}";

        // 开始与服务传递消息
        output.write(json_str.getBytes());
        output.flush();

        System.out.print(json_str);


        // 等待服务器回执消息
        byte[] bytes = new byte[1024];
        int len = input.read(bytes);

        System.out.print("服务器发回信息啦");

        json_str = new String(bytes, 0, len + 1);
        JSONObject json = JSONObject.fromObject(json_str);
        int sta=json.getInt("state");

        System.out.print(json_str);


        return sta;
    }
}