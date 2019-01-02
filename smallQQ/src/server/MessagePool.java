package server;

import java.util.HashMap;
import java.util.LinkedList;


import net.sf.json.JSONObject;
import util.Config;
import view.Msg;
import view.chatWindow;

/**
 * 消息池
 * 会把所有的消息接收到池里进行存储
 *
 *
 *
 */
public class MessagePool {

    private MessagePool() {
    }

    private static MessagePool messagePool = new MessagePool();

    public static MessagePool getMessagePool() {
        return messagePool;
    }


    // 不管是给谁消息 都应该在池里存储起来
    public void addMessage(String json) {

        JSONObject jsonObject = JSONObject.fromObject(json);
        String toUID = jsonObject.getString("toUID");
        String myUID = jsonObject.getString("myUID");
        String msg = jsonObject.getString("msg");
        String type = jsonObject.getString("type");
        String code = jsonObject.getString("code");

        // 把接收好的消息 包装在Msg对象内
        Msg msgObj = new Msg();
        msgObj.setCode(code);
        msgObj.setMsg(msg);
        msgObj.setMyUID(myUID);
        msgObj.setToUID(toUID);
        msgObj.setType(type);

        try {
            chatWindow chatwindow = Config.chatwindow.get(myUID);
            if (chatwindow.isVisible()) {
                chatwindow.addMsg(msgObj);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
        }


    }
}
