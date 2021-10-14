package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        while (true) {
            try (DatagramSocket datagramSocket = new DatagramSocket(1081)) {

                Scanner scanner = new Scanner(System.in);

                byte[] buffer = new byte[256];
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                System.out.println("Waiting for response...");
                datagramSocket.receive(datagramPacket);

                System.out.println("Response: ");
                System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
                System.out.println("\nEnter message: ");
                String message = scanner.nextLine();

                buffer = message.getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, datagramPacket.getAddress(), datagramPacket.getPort());
                datagramSocket.send(datagramPacket);
                System.out.println("Message sent");

                buffer = new byte[256];

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
