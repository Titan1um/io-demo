package com.jun.iodemo.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer implements Runnable {

    private ServerSocket serverSocket;
    private Socket socket;
    private boolean connectFlag;
    private InputStream is;
    private OutputStream os;
    private byte[] buffer = new byte[1024];

    public TcpServer() {
    }

    public TcpServer(Socket socket) {
        this.socket = socket;
    }

    public void execute() {
        while (true) {
            try {
                socket = serverSocket.accept();
                new Thread(new TcpServer(socket)).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean initServer() {
        try {
            serverSocket = new ServerSocket(5008);
            connectFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connectFlag;
    }

    @Override
    public void run() {
        try {
            String host = socket.getInetAddress().toString();
            int port = socket.getPort();
            os = socket.getOutputStream();
            is = socket.getInputStream();
            while (true) {
                if (socket.isClosed()) {
                    os.flush();
                    os.close();
                    is.close();
                    return;
                }
                int len = is.read(buffer);
                if (len != -1) {
                    System.out.println("host: " + host + " port:" + port + "\nmessage:" + new String(buffer, 0, len));
                    os.write("copy that".getBytes());
                    os.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        TcpServer server = new TcpServer();
        if (server.initServer()) {
            server.execute();
        } else {
            System.out.println("Error: mistake when connect");
        }
    }
}
