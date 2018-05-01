/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.tcpip;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Kabugi
 */
public class TCPIPTest2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        
        PMSBody pmsBody = new PMSBody();

        PMSHeader pmsHeader = new PMSHeader();

        pmsHeader.synch1 = 1431655765;
        pmsHeader.synch2 = 11184810;
        pmsHeader.version = 1;
        pmsHeader.commandid = 1;
        pmsHeader.bodySize = 62;

        pmsBody.hdr = pmsHeader;

        pmsBody.appName = "BatchClient\0";
        pmsBody.appLicense = "42860149\0";
        
        
        Socket socket = new Socket("127.0.0.1", 3015);
        
        OutputStream out = socket.getOutputStream();
        
      
       //
        out.flush();
        
         ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
       
        
        ois.close();
        out.close();
        socket.close();
        
    }
    
}
