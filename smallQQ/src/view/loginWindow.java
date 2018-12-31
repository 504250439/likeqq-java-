package view;

import server.NetService;
import util.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.sf.json.JSONObject;


import util.Config;
/**
 *  登陆界面
 *
 *
 */


public class loginWindow extends JFrame implements ActionListener {
    private JPasswordField password;
    private JTextField username;



    public static void main(String[] args)
    {
        loginWindow a=new loginWindow();


    }







    public loginWindow()
    {

        init();
        setBounds(300,300,450,350);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

    public void init()
    {
        setLayout(null);

        JLabel id1 = new JLabel("账号:");
        id1.setBounds(80,120,100,30);
        add(id1);

        JLabel pwLabel = new JLabel("密码:");
        pwLabel.setBounds(80,170,100,30);
        add(pwLabel);

        username=new JTextField(15);
        username.setBounds(130,120,150,30);
        add(username);

        password=new JPasswordField(15);
        password.setBounds(130,170,150,30);
        add(password);

        JButton register=new JButton("注册");
        register.setBounds(30,220,100,40);
        add(register);
        register.addActionListener(this);

        JButton loginbutton=new JButton("登陆");
        loginbutton.setBounds(250,220,100,40);
        loginbutton.addActionListener(this);
        add(loginbutton);




    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand()=="注册")
        {
            registerWindow registerWindow=new registerWindow();
        }
        if(e.getActionCommand()=="登陆")
        {
            String username_str = username.getText().trim();
            String password_str = password.getText().trim();
            if (username_str.trim().equals("") || password_str.trim().equals("")) {
                javax.swing.JOptionPane.showMessageDialog(loginWindow.this, "用户名和密码必须填写!");
                return;
            }
            Config.username = username_str;
            Config.password = password_str;
            try {
                JSONObject json = NetService.getNetService().login();

                if (json.getInt("state") == 0) {
                    //登录成功后 显示好友列表
                    new mainWindow().setVisible(true);
                    loginWindow.this.setVisible(false);
                    loginWindow.this.dispose();
                }else{
                    javax.swing.JOptionPane.showMessageDialog(loginWindow.this, json.get("msg"));
                }

            } catch (Exception e1) {
                e1.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(loginWindow.this, "网络连接失败!");
            }

        }


    }
}
