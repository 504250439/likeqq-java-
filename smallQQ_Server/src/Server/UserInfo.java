package Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class UserInfo {
    private String uid;
    private Socket socket;
    private String udpip;
    private int udpport;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUdpip() {
        return udpip;
    }

    public void setUdpip(String udpip) {
        this.udpip = udpip;
    }

    public int getUdpport() {
        return udpport;
    }

    public void setUdpport(int udpport) {
        this.udpport = udpport;
    }


}