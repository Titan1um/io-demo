package com.jun.iodemo.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {

    private Socket socket;
    private boolean connectFlag;
    private InputStream in;
    private OutputStream out;
    private byte[] buffer = new byte[1024];

    public static void main(String args[]) {
        TcpClient tcpClient = new TcpClient();
        if (tcpClient.initSocket()) {
            tcpClient.execute();
        }
    }

    private boolean initSocket() {
        try {
            socket = new Socket("127.0.0.1", 5008);
            socket.setSoTimeout(2000);
            connectFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connectFlag;
    }

    private void execute() {
        String message = "yo, mtfk.";
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();

            int len;
            out.write(message.getBytes());
            out.flush();

            while (true) {
                len = in.read(buffer);
                if (len != -1) {
                    String response = new String(buffer, 0, len);
                    System.out.println("receieve: " + response);
                } else {
                    System.out.println("done");
                    in.close();
                    out.close();
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            try {
                socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
