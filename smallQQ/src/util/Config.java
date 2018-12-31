package util;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import static util.Config.friend_json_data;

/**
 *  记录服务器登陆信息
 *
 *
 */
public class Config {

    public static final String IP="192.168.1.103";
    public static final  int LOGIN_POST=4001;

    public static String username;
    public static String password;



    public static String friend_json_data="";

    public static String resolve_friend_list_data="";


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



}
