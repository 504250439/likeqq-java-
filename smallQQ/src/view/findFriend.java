package view;

import util.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 查找好友界面
 *
 *
 */

public class findFriend extends JFrame implements ActionListener {
    private JTextField findId;


    public findFriend()
    {
        init();
        setBounds(600,200,400,400);
        setVisible(true);
        setTitle("查找好友");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    void init()
    {
        JLabel idL= new JLabel("id:");
        idL.setBounds(10,10,100,30);
        add(idL);

        findId=new JTextField(15);
        findId.setBounds(120,10,150,30);
        add(findId);

        JButton find=new JButton("查找");
        find.setBounds(280,10,80,30);
        add(find);
        find.addActionListener(this);



        JLabel label1= new JLabel("查找结果");
        label1.setBounds(10,70,100,30);
        add(label1);

        JPanel result = new JPanel();
        result.setBounds(10,100,390,260);
        add(result);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        String find_id = findId.getText().trim();
//        Config.find_friend_id=find_id;

    }

}
