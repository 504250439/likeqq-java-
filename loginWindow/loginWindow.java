package loginWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginWindow extends JFrame implements ActionListener {



    public loginWindow()
    {

        init();
        setBounds(300,300,450,350);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

    void init()
    {
        setLayout(null);

        JLabel email = new JLabel("Email:");
        email.setBounds(80,120,100,30);
        add(email);

        JLabel pwLabel = new JLabel("密码:");
        pwLabel.setBounds(80,170,100,30);
        add(pwLabel);

        JTextField number=new JTextField(15);
        number.setBounds(130,120,150,30);
        add(number);

        JPasswordField password=new JPasswordField(15);
        password.setBounds(130,170,150,30);
        add(password);

        JButton register=new JButton("注册");
        register.setBounds(30,220,100,40);
        add(register);
        register.addActionListener(this);



        JButton login=new JButton("登陆");
        login.setBounds(250,220,100,40);
        add(login);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand()=="注册")
        {
            registerWindow registerWindow=new registerWindow();
        }


    }
}
