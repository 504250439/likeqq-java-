package view;
import util.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 *  群的列表信息
 *
 *
 */

public class GrouupDataJpsnel extends JPanel implements MouseListener{
    private String image;
    private String groupname;
    private String groupid;
    private JLabel label_image;
    private JLabel label_name;

    public GrouupDataJpsnel(String groupid,String groupname)
    {
        this.groupname = groupname;
        this.groupid = groupid;
        this.setLayout(null);


        label_image = new JLabel(new ImageIcon("src/pictures/dog.jpg"));
        label_image.setBounds(4, 4, 48, 48);
        add(label_image);

        label_name = new JLabel();
        label_name.setBounds(58, 4, 478, 25);
        add(label_name);
        label_name.setText(groupname);
        this.addMouseListener(this);
    }

    public void setname(String groupname) {
        label_name.setText(groupname);
        this.groupname = groupname;
    }

    public void setImage(String image) {
        label_image.setIcon(new ImageIcon("src/pictures/dog.jpg"));
        this.image = image;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            Config.showGroupWindow(groupid,groupname,image);
        }




    }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}
