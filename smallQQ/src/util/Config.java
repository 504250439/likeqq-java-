package util;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import view.FriendList;
import view.chatWindow;

import java.net.DatagramSocket;
import java.util.Hashtable;

import static util.Config.friend_json_data;

/**
 *  记录服务器登陆信息
 *
 *
 */
public class Config {

    public static final String IP="192.168.1.109";
    public static final  int LOGIN_POST=4001;
    public static final int REGISTER_POST=4123;
    public static String username;
    public static String password;


    public static String resolve_friend_list_data="";
    public static String friend_json_data="";


    public static String resolve_Group_list_data="";
    public static String Group_json_data="";


    public static String my_json_data="";

    // UDP发送和接收 以及心跳端
    public static DatagramSocket datagramSocket_client = null;

    //解析获取用户uid
    public static void resolve_friend_json_data(String friend_json_data)
    {
        Config.friend_json_data=friend_json_data;
        JSONArray json=JSONArray.fromObject(friend_json_data);

        StringBuffer stringBuffer = new StringBuffer();

        for(int i=0;i<json.size();i++) {

            JSONObject jsonObject=(JSONObject) json.get(i);
            stringBuffer.append(jsonObject.getString("uid"));
            stringBuffer.append(",");

        }
        resolve_friend_list_data=stringBuffer.toString();
    }


    // 聊天窗口登记
    public  static Hashtable<String, chatWindow> chatwindow = new Hashtable<String, chatWindow>();

    // 显示聊天窗口
    public static void showChatWindow(String uid, String name, String img) {

        if (chatwindow.get(uid) == null) {
            chatWindow chat = new chatWindow(uid, name, img);
            chatwindow.put(uid, chat);
        } else {
            chatwindow.get(uid).setAlwaysOnTop(true);
            chatwindow.get(uid).setVisible(true);
        }

    }





    }

