package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * qq主界面
 *
 *
 */

public class mainWindow extends JFrame implements ActionListener {
    JButton head;
    JButton find;
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



        JLabel name = new JLabel("name");
        name.setBounds(80,15,100,30);
        add(name);



        JTabbedPane friend=new JTabbedPane();
        friend.add("我的好友",new JPanel());
        friend.setBounds(0,70,240,540);
        add(friend);



        JButton set=new JButton("设置");
        set.setBounds(10,620,60,30);
        add(set);

        find=new JButton("查找");
        find.setBounds(80,620,60,30);
        add(find);
        find.addActionListener(this);

        JButton quit=new JButton("退出");
        quit.setBounds(150,620,60,30);
        add(quit);
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

    }


}
