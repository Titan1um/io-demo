package com.jun.iodemo.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;

public class UdpServer implements Runnable {

    private DatagramSocket socket;
    private DatagramPacket packet;

    public UdpServer(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }

    public static void main(String[] args) throws IOException {
        AtomicInteger numThreads = new AtomicInteger(0);
        DatagramSocket socket = new DatagramSocket(5000);
        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            Thread thread = new Thread(new UdpServer(socket, packet));
            thread.start();
            numThreads.incrementAndGet();
        }
    }

    @Override
    public void run() {
        String message = new String(this.packet.getData(), 0, this.packet.getData().length);
        int port = packet.getPort();
        InetAddress address = packet.getAddress();
        System.out.println("host: " + address + " port:" + port + "\nmessage:" + message);
        String response = "well done,dude";
        DatagramPacket resPacket = new DatagramPacket(response.getBytes(), response.getBytes().length, address, port);
        try {
            socket.send(resPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
