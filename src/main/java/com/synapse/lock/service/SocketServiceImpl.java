package com.synapse.lock.service;

import com.synapse.lock.models.Response;
import com.synapse.lock.models.UnspecifiedFields;
import com.synapse.lock.payload.GenericPayload;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author patrick
 */
@Service("SocketServiceImpl")
public class SocketServiceImpl {

    private final Logger LOG = LoggerFactory.getLogger(SocketServiceImpl.class);

    @Autowired
    Environment env;

    private static Socket socket;
    
    public void registerPMS(){
        try{
            String inputRegister = "";
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);

                bw.write(inputRegister);
                bw.flush();
                LOG.info("Registration Message sent to the server : " + inputRegister + "\n");
                
               InputStream is = socket.getInputStream();
               InputStreamReader isr = new InputStreamReader(is);
               BufferedReader br = new BufferedReader(isr);
               String message = br.readLine();
               System.out.println("Response received from the server : " + message);
               
               
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean openConnection() {
        boolean connected = false;
        try {
            if (socket == null || !socket.isBound() || socket.isClosed() || !socket.isConnected() || socket.isInputShutdown() || socket.isOutputShutdown()) {
             
                String host = env.getProperty("datasource.lockIp");
                int port = Integer.parseInt(env.getProperty("datasource.lockPort"));
                LOG.info("Connecting to Host:" + host + " port:" + port);

                InetAddress address = InetAddress.getByName(host);
                socket = new Socket(address, port);
                socket.setKeepAlive(true);
                connected = true;
                LOG.info("NEW CONNECTION , NOW Registering...");
                
                //register herer
                registerPMS();
                
            } else {
                LOG.info("CONNECTION IS ALREADY UP");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connected;
    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(SocketServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Response sendRequestToEmulator(String input) {
        Response response = new Response();
        try {
            openConnection();//Detects if connection is open if not it opens if open it reuses the connection
            PrintWriter out = null;
            BufferedReader in = null;

            //Send the message to the server
            LOG.info("Input is;" + input + " Length is " + input.length());
            if (!input.equals("")) {
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);

                bw.write(input);
                bw.flush();
                LOG.info("Message sent to the server : " + input + "\n");

                response.setResponseCode("00");
                response.setResponseDescription("Message sent successfully");
                response.setPayloadSent(input);
                //Receiving response from server if the flag is set
                //When set and server does not respond it may cause hagging as application waits for response
                //SET ONLY WHEN SURE SERVER IS RESPONDING
                if (env.getProperty("datasource.receiveResponseFromServer")!=null && env.getProperty("datasource.receiveResponseFromServer").equals("1")) {

                    InputStream is = socket.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    String message = br.readLine();
                    LOG.info("Message received from the server : " + message);
                    response.setResponseFromServer(message);

                } else {
                    response.setResponseFromServer("Config receiveResponseFromServer not set.(Ignore)");
                }

            }
        } catch (Exception exception) {
            exception.printStackTrace();
            response.setResponseCode("05");
            response.setResponseDescription("Error Occured: " + exception.getMessage());
            LOG.error("Error {} " + exception.getMessage(), exception);
        }
        return response;
    }

    public void simulatedEmulatorToShowApplicationCanReceiveResponses() {

        try {
            Socket simulatedSocket;
            int port = 3015;
            ServerSocket serverSocket = new ServerSocket(port);
            LOG.info("Server Started and listening to the port 3015");

            //Server is running always. This is done using this while(true) loop
            while (true) {
                //Reading the message from the client
                simulatedSocket = serverSocket.accept();
                InputStream is = simulatedSocket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String request = br.readLine();
                LOG.info("Message received from client is " + request);

                String returnMessage;
                try {
                    returnMessage = String.valueOf("SERVER OK: OUR RESPONSE IS YOUR REQUEST" + request) + "\n";
                } catch (NumberFormatException e) {
                    //Input was not a number. Sending proper message back to client.
                    returnMessage = "Please send a proper number\n";
                }

                //Sending the response back to the client.
                OutputStream os = simulatedSocket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
                LOG.info("Message sent to the client is " + returnMessage);
                bw.flush();
            }
        } catch (IOException ex) {
            LOG.error("Error " + ex.getMessage());
            java.util.logging.Logger.getLogger(SocketServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void formulateRegisterCommand()
    {
        
    }
    
     public String getTCPIPPayloadToSend(GenericPayload thisPayload) {
        String tcpIpPayload = "";
        String fieldSeparator = env.getProperty("datasource.fieldSepartor") == null ? "*" : env.getProperty("datasource.fieldSepartor");

        if (thisPayload.commandId != null && !thisPayload.commandId.equals("string") && !thisPayload.commandId.equals("string") && thisPayload.commandId.length() > 0) {
            tcpIpPayload += "" + thisPayload.commandId + fieldSeparator;
        }
        if (thisPayload.room_Name != null && !thisPayload.room_Name.equals("string") && !thisPayload.commandId.equals("string") && thisPayload.room_Name.length() > 0) {
            tcpIpPayload += "R" + thisPayload.room_Name + fieldSeparator;
        }
        if (thisPayload.room_List != null && !thisPayload.room_List.equals("string") && thisPayload.room_List.length() > 0) {
            tcpIpPayload += "L" + thisPayload.room_List + fieldSeparator;
        }
        if (thisPayload.user_Type != null && !thisPayload.user_Type.equals("string") && thisPayload.user_Type.length() > 0) {
            tcpIpPayload += "T" + thisPayload.user_Type + fieldSeparator;
        }
        if (thisPayload.user_Group != null && !thisPayload.user_Group.equals("string") && thisPayload.user_Group.length() > 0) {
            tcpIpPayload += "U" + thisPayload.user_Group + fieldSeparator;
        }
        if (thisPayload.first_Name != null && !thisPayload.first_Name.equals("string") && thisPayload.first_Name.length() > 0) {
            tcpIpPayload += "F" + thisPayload.first_Name + fieldSeparator;
        }
        if (thisPayload.family_Name != null && !thisPayload.family_Name.equals("string") && thisPayload.family_Name.length() > 0) {
            tcpIpPayload += "N" + thisPayload.family_Name + fieldSeparator;
        }
        if (thisPayload.access_Points != null && !thisPayload.access_Points.equals("string") && thisPayload.access_Points.length() > 0) {
            tcpIpPayload += "A" + thisPayload.access_Points + fieldSeparator;
        }
        if (thisPayload.card_Copy != null && !thisPayload.card_Copy.equals("string") && thisPayload.card_Copy.length() > 0) {
            tcpIpPayload += "C" + thisPayload.card_Copy + fieldSeparator;
        }
        if (thisPayload.pMS_ID != null && !thisPayload.pMS_ID.equals("string") && thisPayload.pMS_ID.length() > 0) {
            tcpIpPayload += "P" + thisPayload.pMS_ID + fieldSeparator;
        }
        if (thisPayload.track_1_data != null && !thisPayload.track_1_data.equals("string") && thisPayload.track_1_data.length() > 0) {
            tcpIpPayload += "1" + thisPayload.track_1_data + fieldSeparator;
        }
        if (thisPayload.track_2_data != null && !thisPayload.track_2_data.equals("string") && thisPayload.track_2_data.length() > 0) {
            tcpIpPayload += "2" + thisPayload.track_2_data + fieldSeparator;
        }
        if (thisPayload.track_3_data != null && !thisPayload.track_3_data.equals("string") && thisPayload.track_3_data.length() > 0) {
            tcpIpPayload += "3" + thisPayload.track_3_data + fieldSeparator;
        }
        if (thisPayload.print_Information != null && !thisPayload.print_Information.equals("string") && thisPayload.print_Information.length() > 0) {
            tcpIpPayload += "I" + thisPayload.print_Information + fieldSeparator;
        }
        if (thisPayload.generic_Field != null && !thisPayload.generic_Field.equals("string") && thisPayload.generic_Field.length() > 0) {
            tcpIpPayload += "?" + thisPayload.generic_Field + fieldSeparator;
        }
        if (thisPayload.database != null && !thisPayload.database.equals("string") && thisPayload.database.length() > 0) {
            tcpIpPayload += "B" + thisPayload.database + fieldSeparator;
        }
        if (thisPayload.rFID_card_sub_type != null && !thisPayload.rFID_card_sub_type.equals("string") && thisPayload.rFID_card_sub_type.length() > 0) {
            tcpIpPayload += "J" + thisPayload.rFID_card_sub_type + fieldSeparator;
        }
        if (thisPayload.card_Serial_Number != null && !thisPayload.card_Serial_Number.equals("string") && thisPayload.card_Serial_Number.length() > 0) {
            tcpIpPayload += "S" + thisPayload.card_Serial_Number + fieldSeparator;
        }
        if (thisPayload.vingCard_Code != null && !thisPayload.vingCard_Code.equals("string") && thisPayload.vingCard_Code.length() > 0) {
            tcpIpPayload += "V" + thisPayload.vingCard_Code + fieldSeparator;
        }
        if (thisPayload.check_In_time != null && !thisPayload.check_In_time.equals("string") && thisPayload.check_In_time.length() > 0) {
            tcpIpPayload += "D" + thisPayload.check_In_time + fieldSeparator;
        }
        if (thisPayload.check_Out_Time != null && !thisPayload.check_Out_Time.equals("string") && thisPayload.check_Out_Time.length() > 0) {
            tcpIpPayload += "O" + thisPayload.check_Out_Time + fieldSeparator;
        }
        if (thisPayload.unspecifiedFields != null && thisPayload.unspecifiedFields.size() > 0) {
            for (UnspecifiedFields field : thisPayload.unspecifiedFields) {
                if (field.getFieldIdentifier() != null && !field.getFieldIdentifier().equals("string") && field.getFieldIdentifier().length() > 0) {
                    tcpIpPayload += field.getFieldIdentifier() + field.getFieldValue() + fieldSeparator;
                }
            }
        }
        return tcpIpPayload.substring(0, tcpIpPayload.length() - 1);
    }

}
