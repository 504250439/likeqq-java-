package loginWindow;

import javax.swing.*;

public class registerWindow extends JFrame{
    public registerWindow()
    {
        init();
        setBounds(300,300,400,300);
        setVisible(true);
        setTitle("注册界面");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    void init()
    {
        setLayout(null);

        JLabel nameL= new JLabel("name:");
        nameL.setBounds(80,10,100,30);
        add(nameL);

        JLabel emailL = new JLabel("Email:");
        emailL.setBounds(80,60,100,30);
        add(emailL);

        JLabel passwordL = new JLabel("密码:");
        passwordL.setBounds(80,120,100,30);
        add(passwordL);

        JTextField name=new JTextField(15);
        name.setBounds(130,10,150,30);
        add(name);


        JTextField number=new JTextField(15);
        number.setBounds(130,60,150,30);
        add(number);

        JPasswordField password=new JPasswordField(15);
        password.setBounds(130,120,150,30);
        add(password);

        JButton register=new JButton("注册");
        register.setBounds(100,180,100,40);
        add(register);


    }

}
