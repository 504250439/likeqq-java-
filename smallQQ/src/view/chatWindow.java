package view;
import net.sf.json.JSONObject;
import util.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;


/**
 *  聊天对话界面
 *
 *
 */

public class chatWindow extends JFrame implements WindowListener {
    private String uidStr;
    private String nameStr;
    private String imgStr;

    private static JTextArea textArea;
    private static JLabel name ;
    private static JButton head;


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

    public void addMsg(Msg msg)
    {

        String str = "\n" + JSONObject.fromObject(Config.my_json_data).getString("name") + "\t"
                + new Date().toLocaleString() + "\n" + msg.getMsg() + "\n";

        textArea.setText(textArea.getText() + str);
        textArea.setSelectionStart(textArea.getText().toString().length());
        textArea.setSelectionEnd(textArea.getText().toString().length());

    }




    public void addMyMsg(Msg msg)
    {
        String str = "\n" + nameStr + "\t" + new Date().toLocaleString() + "\n" + msg.getMsg() + "\n";

        textArea.setText(textArea.getText() + str);
        textArea.setSelectionStart(textArea.getText().toString().length());
        textArea.setSelectionEnd(textArea.getText().toString().length());
    }




    void init()
    {
        setLayout(null);

        head=new JButton();
        head.setIcon(new ImageIcon("src/pictures/dog.jpg"));
        head.setBounds(10,10,50,50);
        add(head);

        name = new JLabel(this.nameStr);
        name.setBounds(80,15,100,30);
        add(name);

        textArea = new JTextArea();
        JTextArea inputBox=new JTextArea();
        JScrollPane messageBox=new JScrollPane();
        messageBox.setViewportView(textArea);
        JSplitPane window=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messageBox,inputBox);
        window.setDividerLocation(350);
        window.setBounds(0,70,600,460);
        add(window);

        JButton set=new JButton("发送");
        set.setBounds(480,530,100,30);
        add(set);

        set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

                // {“type”:”msg”,”toUID”:””,”MyUID”:””,”msg”:””,”code”:””}

                try {
                    Msg msg = new Msg();
                    msg.setCode(System.currentTimeMillis() + "");
                    msg.setMyUID(JSONObject.fromObject(Config.my_json_data).getString("uid"));
                    msg.setToUID(uidStr);
                    msg.setMsg(inputBox.getText());
                    msg.setType("msg");

                    String json = JSONObject.fromObject(msg).toString();
                    byte[] bytes = json.getBytes();

                    DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length,
                            InetAddress.getByName(Config.IP), 4003);
                    Config.datagramSocket_client.send(datagramPacket);
                    inputBox.setText("");

                    addMyMsg(msg);

                } catch (Exception e2) {
                    e2.printStackTrace();
                }

            }
        });

    }
    @Override
    public void windowClosing(WindowEvent e) {
        this.dispose();
    }
    @Override
    public void windowOpened(WindowEvent e) { }
    @Override
    public void windowClosed(WindowEvent e) { }
    @Override
    public void windowIconified(WindowEvent e) { }
    @Override
    public void windowDeiconified(WindowEvent e) { }
    @Override
    public void windowActivated(WindowEvent e) { }
    @Override
    public void windowDeactivated(WindowEvent e) { }

}
