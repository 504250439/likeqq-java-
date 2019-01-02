package Server;

public class start {

    public static void main(String[] args)
    {
        new Thread() {

            public void run() {
                try {
                    System.out.println("登录服务器启动成功!");
                    loginServer.openServer();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();

        new Thread() {

            public void run() {
                try {
                    System.out.println("信息中转服务器启动成功!");
                    UDPMessageServer.openServer();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();

        new Thread() {

            public void run() {
                try {
                    System.out.println("注册服务器!");
                    RegisterServer.openServer();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();

    }


}
