package view;
import util.Config;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


/**
 *  更新好友列表 显示
 *
 *
 */
public class FriendDataJpsnel extends JPanel implements MouseListener {
    private String image;
    private String name;
    private String uid;
    private JLabel label_image;
    private JLabel label_name;


    public FriendDataJpsnel(String image, String name, String uid)
    {

        this.image = image;
        this.name = name;
        this.uid = uid;

        this.setLayout(null);

        label_image = new JLabel(new ImageIcon("src/pictures/dog.jpg"));
        label_image.setBounds(4, 4, 48, 48);
        add(label_image);
        setImage(image);


        label_name = new JLabel();
        label_name.setBounds(58, 4, 478, 25);
        add(label_name);
        label_name.setText(name);

        this.addMouseListener(this);
    }
    public void setname(String name) {
        label_name.setText(name);
        this.name = name;
    }

    public void setImage(String image) {
        label_image.setIcon(new ImageIcon("src/pictures/dog.jpg"));
        this.image = image;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 2) {
            chatWindow chat=new chatWindow(uid, name, image);
        }

    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override

    public void mouseReleased(MouseEvent e) {
    }
    @Override

    public void mouseEntered(MouseEvent e) {
    }
    @Override

    public void mouseExited(MouseEvent e) {
    }


}