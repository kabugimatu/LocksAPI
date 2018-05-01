/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.tcpip;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.tomcat.util.buf.StringUtils;

/**
 *
 * @author Kabugi
 */
public class TCPIPTest {

    public static void main(String[] args) throws IOException {
        int synch1 = 0x55555555;
        int synch2 = 0xaaaaaaaa;
        short version = 1;
        int commandId = 1;
        int bodySize = 100;
        int ret = 0;
        String license = "12345678";
        String appName = "Name";

        ByteBuffer bbuf = ByteBuffer.allocate(62);
        bbuf.order(ByteOrder.LITTLE_ENDIAN);

        bbuf.putInt(synch1);
        bbuf.putInt(synch2);
        bbuf.putShort(version);
        bbuf.putInt(commandId);
        bbuf.putInt(bodySize);
       // String appNameTrunc = appName.substring(0, Math.min(appName.length(), 19));
        bbuf.put( Arrays.copyOf(appName.getBytes(StandardCharsets.US_ASCII), 20));
     //   String licenseTrunc = license.substring(0, Math.min(license.length(), 19));
        bbuf.put(Arrays.copyOf(appName.getBytes(StandardCharsets.US_ASCII), 20));
    
        bbuf.putInt(ret);
        byte[] bytesToSend = bbuf.array();
     
        Socket socket = new Socket("127.0.0.1", 3015);

        OutputStream out = socket.getOutputStream();
      

        out.write(bytesToSend);
        out.flush();

        InputStream in = socket.getInputStream();
       
        byte[] data = new byte[bytesToSend.length];

     
        
        in.read(data);
    }

}
