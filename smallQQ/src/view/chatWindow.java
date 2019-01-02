package view;
import javax.swing.*;


/**
 *  聊天对话界面
 *
 *
 */

public class chatWindow extends JFrame{
    private String uidStr;
    private String nameStr;
    private String imgStr;





    public chatWindow(String uidStr, String nameStr, String imgStr)
    {
        this.uidStr = uidStr;
        this.nameStr = nameStr;
        this.imgStr = imgStr;
        init();
        setBounds(400,100,600,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }


    void init()
    {
        setLayout(null);

        JButton head=new JButton();
        head.setIcon(new ImageIcon("src/pictures/dog.jpg"));
        head.setBounds(10,10,50,50);
        add(head);

        JLabel name = new JLabel(this.nameStr);
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
