/**
 * JOpenIGTClient.java
 * 
 * @author Pranav Lakshminarayanan
 */
package main;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import org.medcare.igtl.messages.StringMessage;

import org.medcare.igtl.util.Header;

/**
 *
 * @author Pranav
 */
public class JOpenIGTClient {

    public static void main(String[] args) {

        // MODIFY SERVER SETTINGS HERE
        //final String SERVER_IP = "10.0.0.6";
        final String SERVER_IP = "192.168.1.10";
        //final String SERVER_IP = "127.0.0.1";
        //final String SERVER_IP = "localhost";
        final int SERVER_PORT = 8099;

        System.out.println("Will connect to - "
                + SERVER_IP + ":" + SERVER_PORT + "\n");

        String toServ;
        Scanner s = new Scanner(System.in);

        Socket clientSocket;
        DataOutputStream outToServer;
        DataInputStream inFromServer;

        System.out.print("INPUT: ");
        toServ = s.nextLine();

        // CONNECT TO SERVER
        try {
            clientSocket = new Socket(
                    InetAddress.getByName(SERVER_IP), SERVER_PORT);

            outToServer = new DataOutputStream(
                    clientSocket.getOutputStream());

            inFromServer = new DataInputStream(clientSocket.getInputStream());
            System.out.println("Connected to - "
                    + SERVER_IP + ":" + SERVER_PORT);

            // WRITE TO SERVER AND RECEIVE MESSAGE
            outToServer.writeBytes(toServ);
            outToServer.write(0x00);    //EOF character
            outToServer.flush();

            byte[] b = readToByteArray(inFromServer);

            StringMessage rec = new StringMessage(new Header(b), b);

            System.out.println("FROM SERVER: \n" + rec.getMessage());

            s.close();
            clientSocket.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static byte[] readToByteArray(DataInputStream is) throws IOException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();
    }

}
