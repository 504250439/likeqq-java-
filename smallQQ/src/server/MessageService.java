package server;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import util.Config;

import net.sf.json.JSONObject;

/**
 * 接收服务器的中转消息
 *
 * @author 凯哥
 *
 */
public class MessageService extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                byte[] bytes = new byte[1024 * 32];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                client.receive(datagramPacket);

                System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getData().length));

            } catch (Exception e) {
            }
        }

    }

    private DatagramSocket client = null;

    public MessageService(DatagramSocket client) {
        this.client = client;
        this.start();
    }
}
