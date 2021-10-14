package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        while (true) {
            try (DatagramSocket datagramSocket = new DatagramSocket()) {

                Scanner scanner = new Scanner(System.in);

                System.out.println("\nEnter message: ");
                String message = scanner.nextLine();

                InetAddress address = InetAddress.getByName("localhost");
                byte[] buffer = message.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, address, 1081);
                datagramSocket.send(datagramPacket);
                System.out.println("Message sent");

                buffer = new byte[256];
                datagramPacket = new DatagramPacket(buffer, buffer.length, address, 1000);
                System.out.println("Waiting for response...");
                datagramSocket.receive(datagramPacket);

                System.out.println("Response: ");
                System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
