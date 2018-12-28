package mainWindow;

import javax.swing.*;

public class findFriend extends JFrame{
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
        JLabel name= new JLabel("name:");
        name.setBounds(10,10,100,30);
        add(name);

        JTextField findName=new JTextField(15);
        findName.setBounds(120,10,150,30);
        add(findName);

        JLabel label1= new JLabel("查找结果");
        label1.setBounds(10,70,100,30);
        add(label1);

        JPanel result = new JPanel();
        result.setBounds(10,100,390,260);
        add(result);


    }


}
