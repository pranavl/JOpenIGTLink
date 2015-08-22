/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.neuronrobotics.sdk.addons.kinematics.math.RotationNR;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import org.medcare.igtl.messages.PositionMessage;
import org.medcare.igtl.messages.StringMessage;

import org.medcare.igtl.messages.TransformMessage;

/**
 *
 * @author Pranav
 */
public class JOpenIGTServer {

    public static void main(String[] args) throws IOException {

        double[] pos = {10.0, 10.0, 10.0};
        double[][] rot = {{1.0, 1.0, 1.0}, {1.0, 1.0, 1.0}, {1.0, 1.0, 1.0}};

//        PositionMessage m = new PositionMessage("Test server", pos, new RotationNR(rot));
//        byte[] mess = m.PackBody();
//        System.out.println(mess.length);
        
        StringMessage m = new StringMessage("Test server", "TEST MESSAGE");
        byte[] mess = m.PackBody();
        System.out.println(mess.length);
        
        ServerSocket servSock = new ServerSocket(18946);
        
        try {
            Socket clntSock = servSock.accept();
            System.out.println("Recieved connection from: " 
                    + clntSock.getInetAddress()
                    + ":"
                    + clntSock.getLocalPort());
            InputStream is = clntSock.getInputStream();
            
            is.read();
            DataOutputStream outToClient = new DataOutputStream(
                    clntSock.getOutputStream());
            outToClient.write(mess);
            outToClient.flush();
            outToClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

