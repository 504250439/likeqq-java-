package test;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class test1 {
    public static void main(String[] args) {


        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        try {


        // 等待客户端信息
//        byte[] bytes = new byte[1024];
//        int len = in.read(bytes);

            String username="dwadaw";
            String password="dwadaw";

            String json_str = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";

        ////////////////////////////////////////////
            //JsonObject json= new JsonParser().parse(json_str).getAsJsonObject();

            System.out.print(json_str);

//            JsonParser parser = new JsonParser();
//            JsonElement jsonEl = parser.parse(json_str);
//            JsonObject json= null;
//            json= jsonEl.getAsJsonObject();//转换成Json对象
//            String username1 = json.get("\"username\"").getAsString();
//            String password1 = json.get("\"username\"").getAsString();


        }catch (Exception e) {}

    }
}



