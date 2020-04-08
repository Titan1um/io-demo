package com.jun.iodemo.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        String message = "Hello mtfk!";
        byte[] buffer = message.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
        socket.send(packet);
        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getData().length);
            if (response != null) {
                System.out.println("receive:" + response);
                socket.close();
                break;
            }
        }
    }
}
