package view;

import util.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.sf.json.JSONObject;

/**
 * qq主界面
 *
 *
 */

public class mainWindow extends JFrame implements ActionListener {
    JButton head;
    JButton find;
    JButton quit;
    public mainWindow()
    {
        init();
        setBounds(800,50,250,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    void init()
    {
        setLayout(null);
        head=new JButton();
        head.setIcon(new ImageIcon("src/pictures/dog.jpg"));
        head.setBounds(10,10,50,50);
        add(head);
        head.addActionListener(this);

        JLabel name = new JLabel(JSONObject.fromObject(Config.my_json_data).getString("name"));
        name.setBounds(80,15,100,30);
        add(name);



        //设置好友选项卡
        JTabbedPane tabbedPane=new JTabbedPane();

//        FriendList friendlist=new FriendList();
//        JScrollPane scrollPane = new JScrollPane();
//        friendlist.add(scrollPane);
//        tabbedPane.add("好友",friendlist);
//        //tabbedPane.add("群",null);
//        tabbedPane.setBounds(0,70,240,540);
//        add(tabbedPane);
        tabbedPane.setBounds(0,70,240,540);
        add(tabbedPane);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        tabbedPane.addTab("我的好友", null, panel, null);

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().add(new FriendList());




        JButton set=new JButton("设置");
        set.setBounds(10,620,60,30);
        add(set);

        find=new JButton("查找");
        find.setBounds(80,620,60,30);
        add(find);
        find.addActionListener(this);

        quit=new JButton("退出");
        quit.setBounds(150,620,60,30);
        add(quit);
        quit.addActionListener(this);


    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==head)
        {
            personData persondata=new personData();

        }
        if(e.getSource()==find)
        {
            findFriend findfriend=new findFriend();
        }
        if(e.getSource()==quit)
        {
            System.exit(0);
        }

    }


}
