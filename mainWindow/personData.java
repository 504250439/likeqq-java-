package mainWindow;

import javax.swing.*;

public class personData extends JFrame{
    public personData()
    {
        init();
        setBounds(600,50,400,400);
        setVisible(true);
        setTitle("个人资料");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }


    void init()
    {
        setLayout(null);

        JButton head=new JButton();
        head.setIcon(new ImageIcon("src/pictures/dog.jpg"));
        head.setBounds(10,10,50,50);
        add(head);

        JLabel name = new JLabel("name");
        name.setBounds(70,10,100,30);
        add(name);

        ButtonGroup sex=new ButtonGroup();
        JRadioButton sex1=new JRadioButton("男");
        JRadioButton sex2=new JRadioButton("女");
        sex.add(sex1);
        sex.add(sex2);
        sex1.setBounds(10,80,60,60);
        sex2.setBounds(80,80,60,60);
        add(sex1);
        add(sex2);

    }

}
