package view;

import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import server.registerServer;
/**
 * 注册界面
 *
 *
 */

public class registerWindow extends JFrame implements ActionListener {

    String uid;
    String name;
    String password;
    JTextField nameT;
    JTextField numberT;
    JPasswordField passwordT;



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

        JLabel id1 = new JLabel("账号:");
        id1.setBounds(80,60,100,30);
        add(id1);

        JLabel passwordL = new JLabel("密码:");
        passwordL.setBounds(80,120,100,30);
        add(passwordL);

        nameT=new JTextField(15);
        nameT.setBounds(130,10,150,30);
        add(nameT);


        numberT=new JTextField(15);
        numberT.setBounds(130,60,150,30);
        add(numberT);

        passwordT=new JPasswordField(15);
        passwordT.setBounds(130,120,150,30);
        add(passwordT);

        JButton register=new JButton("注册");
        register.setBounds(100,180,100,40);
        add(register);
        register.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        uid=numberT.getText();
        name=nameT.getText();
        password=passwordT.getText();
        try {
            registerServer re = new registerServer(uid, name, password);

            if(re.status==98)
            {
                System.out.print("success");

            }







        }
        catch (IOException a){}

    }

}
