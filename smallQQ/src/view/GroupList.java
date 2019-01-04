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
 *
 * 组的列表
 *
 */


public class GroupList extends JPanel{
    public GroupList()
    {
        setLayout(null);
        upData();

    }

    private Hashtable<String, GrouupDataJpsnel> list = new Hashtable();
    public void upData() {
        String group_list = Config.Group_json_data;


        JSONArray jsonArray = JSONArray.fromObject(group_list);


        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            list.put(jsonObject.getString("groupid"), new GrouupDataJpsnel(jsonObject.getString("groupid"),
                    jsonObject.getString("groupname")));


        }

        Collection<GrouupDataJpsnel> faceJPanels = list.values();
        List<GrouupDataJpsnel> list = new ArrayList(faceJPanels);

        int i = 0;
        for (GrouupDataJpsnel groupDataJpsnel : list) {
            groupDataJpsnel.setBounds(0, i++ * 50, 546, 59);
            this.add(groupDataJpsnel);
        }

        this.setPreferredSize(new Dimension(0, 40 * list.size()));
        this.updateUI();




    }


}
