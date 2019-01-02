package view;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.swing.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Config;

/**
 *  好友列表
 *
 *
 *
 */


public class FriendList extends JPanel{


    public FriendList()
    {
        setLayout(null);
        upData();

    }

    private Hashtable<String, FriendDataJpsnel> list = new Hashtable();
    public void upData() {
        String friend_list = Config.friend_json_data;

        JSONArray jsonArray = JSONArray.fromObject(friend_list);


        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            list.put(jsonObject.getString("uid"), new FriendDataJpsnel(jsonObject.getString("uid"),
                    jsonObject.getString("name"), jsonObject.getString("uid")));


        }

        Collection<FriendDataJpsnel> faceJPanels = list.values();
        List<FriendDataJpsnel> list = new ArrayList(faceJPanels);
//        Collections.sort(list);

        int i = 0;
        for (FriendDataJpsnel friendDataJpsnel : list) {
            friendDataJpsnel.setBounds(0, i++ * 50, 546, 59);
            this.add(friendDataJpsnel);
        }

        this.setPreferredSize(new Dimension(0, 40 * list.size()));
        this.updateUI();




    }



}
