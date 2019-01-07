package view;
import net.sf.json.JSONArray;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class groupChatWindow extends JFrame implements WindowListener{
    private String groupId;
    private String groupname;
    private String imgStr;

    private static JTextArea textArea;
    private static JLabel name ;
    private static JButton head;

    public groupChatWindow(String groupId, String groupname, String imgStr)
    {
        this.groupId = groupId;
        this.groupname = groupname;
        this.imgStr = imgStr;
        init();
        setBounds(400,100,600,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    public void addMsg(Msg msg,String speakName)
    {

        String str = "\n" +speakName + "\t"
                + new Date().toLocaleString() + "\n" + msg.getMsg() + "\n";

        textArea.setText(textArea.getText() + str);
        textArea.setSelectionStart(textArea.getText().toString().length());
        textArea.setSelectionEnd(textArea.getText().toString().length());

    }


    public void addMyMsg(Msg msg)
    {
        String str = "\n" + JSONObject.fromObject(Config.my_json_data).getString("name")
                + "\t" + new Date().toLocaleString() + "\n" + msg.getMsg() + "\n";

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

        name = new JLabel(this.groupname);
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
                String gid;   //比对群信息中的群id
                String groupList;
                int i=0;

                try {
                    Msg msg = new Msg();
                    msg.setCode(System.currentTimeMillis() + "");
                    msg.setMyUID(groupId);
                    msg.setMsg(inputBox.getText());
                    msg.setMyName(JSONObject.fromObject(Config.my_json_data).getString("name").toString());
                    //群的标识
                    msg.setType("group");

                    ////////////////////////////  获取群员信息
                    groupList=Config.Group_json_data;
                    try {
                        for (i = 0; i < 10; i++) {
                            gid = JSONArray.fromObject(groupList).getJSONObject(i).getString("groupid").toString();
                            if(gid.equals(groupId))
                            { break;}

                        }
                    }catch (Exception aa){}

                    groupList=JSONArray.fromObject(groupList).getJSONObject(i).getString("groupmember").toString();
                    String regex="[^0-9]+";
                    Pattern p = Pattern.compile(regex);
                    Matcher m = p.matcher(groupList);
                    String string = m.replaceAll(" ").trim();
                    String[] strArr = string.split(" ");
                    //遍历数组转换数据类型输出
                    for(String toid:strArr){
                        System.out.println(Integer.parseInt(toid) +"\n");
                        msg.setToUID(toid);

                        String json = JSONObject.fromObject(msg).toString();
                        byte[] bytes = json.getBytes();
                        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length,
                                InetAddress.getByName(Config.IP), 4003);
                        Config.datagramSocket_client.send(datagramPacket);
                        inputBox.setText("");

                    }
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
