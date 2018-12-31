package view;
import javax.swing.*;


/**
 *  聊天对话界面
 *
 *
 */

public class chatWindow extends JFrame{
    public chatWindow()
    {
        init();
        setBounds(400,100,600,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    void init()
    {
        setLayout(null);

        JButton head=new JButton();
        head.setIcon(new ImageIcon("src/pictures/dog.jpg"));
        head.setBounds(10,10,50,50);
        add(head);

        JLabel name = new JLabel("name");
        name.setBounds(80,15,100,30);
        add(name);


        JTextArea inputBox=new JTextArea();
        JScrollPane messageBox=new JScrollPane();
        JSplitPane window=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messageBox,inputBox);
        window.setDividerLocation(350);
        window.setBounds(0,70,600,460);
        add(window);

        JButton set=new JButton("发送");
        set.setBounds(480,530,100,30);
        add(set);


    }

}
