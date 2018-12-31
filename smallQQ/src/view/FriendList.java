package view;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Hashtable;

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

         for(int i=0;i<20;i++)
         {
             JPanel panel = new JPanel();
             panel.setLayout(null);
             panel.setBounds(0, i * 50, 550, 60);

             JLabel label = new JLabel(new ImageIcon("src/pictures/dog.jpg"));
             label.setBounds(4, 4, 48, 48);
             panel.add(label);

             JLabel label_1 = new JLabel();
             label_1.setBounds(58, 4, 478, 18);
             panel.add(label_1);
             label_1.setText("New JLabel");

             add(panel);
         }


        //设置大小 自动调整
        this.setPreferredSize(new Dimension(0, 40 * 50));

    }


    public void upData()
    {
        String friend_list= Config.friend_json_data;

        JSONArray jsonArray=JSONArray.fromObject(friend_list);



    }




}
