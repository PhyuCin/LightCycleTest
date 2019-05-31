package phyucin.lightcyclestest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class GameServer extends Thread {

    // for socket
    private DatagramSocket socket;

    // to take any information from the game
    private Game game;

    public GameServer(Game game){
        this.game = game;

        // for listening on that port that the clients are gonna be on for the game
        try {
            this.socket = new DatagramSocket(1331);

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            // receives data
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String message = new String(packet.getData());
            System.out.println("CLIENT [" + packet.getAddress().getHostAddress() + ": " + packet.getPort() + "] > " + message);

            // recieve all data, then if it's "ping" then send back "pong"
            if(message.trim().equalsIgnoreCase("LightCycles game request to start")){
                System.out.println("Returning pong");
                sendData("LightCycle game request approved".getBytes(), packet.getAddress(), packet.getPort());
            }

            // if not "ping" return "no ponggg"
            else{
                System.out.println("No pong to return");
                sendData("LightCycle game request NOT approved".getBytes(), packet.getAddress(), packet.getPort());
            }

        }
    }

    // for sending data to server
    public void sendData(byte[] data, InetAddress ipAddress, int port){
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            this.socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


