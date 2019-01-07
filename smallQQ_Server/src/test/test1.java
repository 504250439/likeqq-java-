package test;


import net.sf.json.JSONArray;
import java.util.regex.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class test1 {
    public static void main(String[] args) {

        String s="[{\"groupid\":\"600\",\"groupmember\":[\"1\",\"3\",null,null,null,null,null,null,null,null],\"groupname\":\"这个群有鬼\"},{\"groupid\":\"602\",\"groupmember\":[\"1\",null,null,null,null,null,null,null,null,null],\"groupname\":\"非洲旅游\"}]";
        char[] ff;
        String ss;

        JSONArray a=new JSONArray();
        System.out.print(s+"\n");
        System.out.print(JSONArray.fromObject(s).getJSONObject(0).getString("groupmember").toString()  +"\n");

        //////////////////  frienfid
        ss=JSONArray.fromObject(s).getJSONObject(0).getString("groupmember").toString();
        String regex="[^0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(ss);
        String string = m.replaceAll(" ").trim();
        String[] strArr = string.split(" ");
        //遍历数组转换数据类型输出
        for(String dd:strArr){
            System.out.println(Integer.parseInt(dd));
        }

        ////////////////////////////////

        String ggg;
        try {
            for (int i = 0; i < 10; i++) {
                ggg = JSONArray.fromObject(s).getJSONObject(i).getString("groupid").toString();


                System.out.println(Integer.parseInt(ggg));
            }
        }catch (Exception e){}









    }

}



