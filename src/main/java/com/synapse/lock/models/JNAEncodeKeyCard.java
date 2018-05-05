/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.models;

import com.sun.jna.Memory;
import com.synapse.lock.payload.GenericPayload;

/**
 *
 * @author Kabugi
 */
public class JNAEncodeKeyCard {
static JNALocksInterface.LockLibrary INSTANCE;
    public static String encodeCard(String lockSoftPath,String pmsLicense,String pmsName,String commandCode,GenericPayload payLoadSample) {
        String response = null;
        
        try{

            JNALocksInterface.visionPath = lockSoftPath;
            INSTANCE = JNALocksInterface.LockLibrary.INSTANCE;
            String data = getPayloadToSend(payLoadSample);
            System.out.println("Data >> " + data);
            Memory commandCodeMemory = new Memory(commandCode.length() + 1);
            commandCodeMemory.setString(0, commandCode);
            Memory dataMemory = new Memory(data.length() + 1);
            dataMemory.setString(0, data);
             System.out.println(" Hotelia Registration Status >> " + INSTANCE.PMSifRegister(pmsLicense,pmsName));
            INSTANCE.PMSifEncodeKcdLcl(commandCodeMemory, dataMemory, false, payLoadSample.getUserName(),
                    payLoadSample.getUserFirstName(), payLoadSample.getUserLastName());
            
            response = commandCodeMemory.getString(0);
            System.out.println("Vision FF Response >>  " + response);
            System.out.println("Vision DTA Response >>  " + dataMemory.getString(0));
           
            INSTANCE.PMSifUnregister();
            
            dataMemory = null;
            commandCodeMemory = null;
            
            freeMemory();
        }
        catch(Exception ex)
        {
            response = null;
            ex.printStackTrace();
        }
        
        return response;
    }
    public static void freeMemory(){
        
       

       Runtime.getRuntime().gc();
           
        
    }

    public static String getPayloadToSend(GenericPayload thisPayload) {
        String fieldSeparator = "\u001e";

        String payload = fieldSeparator;

        if (thisPayload.room_Name != null && !thisPayload.room_Name.equals("string") && thisPayload.room_Name.length() > 0) {
            payload += "R" + thisPayload.room_Name + fieldSeparator;
        }
        if (thisPayload.room_List != null && !thisPayload.room_List.equals("string") && thisPayload.room_List.length() > 0) {
            payload += "L" + thisPayload.room_List + fieldSeparator;
        }
        if (thisPayload.user_Type != null && !thisPayload.user_Type.equals("string") && thisPayload.user_Type.length() > 0) {
            payload += "T" + thisPayload.user_Type + fieldSeparator;
        }
        if (thisPayload.family_Name != null && !thisPayload.family_Name.equals("string") && thisPayload.family_Name.length() > 0) {
            payload += "N" + thisPayload.family_Name + fieldSeparator;
        }
        if (thisPayload.first_Name != null && !thisPayload.first_Name.equals("string") && thisPayload.first_Name.length() > 0) {
            payload += "F" + thisPayload.first_Name + fieldSeparator;
        }
        if (thisPayload.user_Group != null && !thisPayload.user_Group.equals("string") && thisPayload.user_Group.length() > 0) {
            payload += "U" + thisPayload.user_Group + fieldSeparator;
        }

        if (thisPayload.access_Points != null && !thisPayload.access_Points.equals("string") && thisPayload.access_Points.length() > 0) {
            payload += "A" + thisPayload.access_Points + fieldSeparator;
        }
        if (thisPayload.card_Copy != null && !thisPayload.card_Copy.equals("string") && thisPayload.card_Copy.length() > 0) {
            payload += "C" + thisPayload.card_Copy + fieldSeparator;
        }
        if (thisPayload.pMS_ID != null && !thisPayload.pMS_ID.equals("string") && thisPayload.pMS_ID.length() > 0) {
            payload += "P" + thisPayload.pMS_ID + fieldSeparator;
        }
        if (thisPayload.track_1_data != null && !thisPayload.track_1_data.equals("string") && thisPayload.track_1_data.length() > 0) {
            payload += "1" + thisPayload.track_1_data + fieldSeparator;
        }
        if (thisPayload.track_2_data != null && !thisPayload.track_2_data.equals("string") && thisPayload.track_2_data.length() > 0) {
            payload += "2" + thisPayload.track_2_data + fieldSeparator;
        }
        if (thisPayload.track_3_data != null && !thisPayload.track_3_data.equals("string") && thisPayload.track_3_data.length() > 0) {
            payload += "3" + thisPayload.track_3_data + fieldSeparator;
        }
        if (thisPayload.print_Information != null && !thisPayload.print_Information.equals("string") && thisPayload.print_Information.length() > 0) {
            payload += "I" + thisPayload.print_Information + fieldSeparator;
        }
        if (thisPayload.generic_Field != null && !thisPayload.generic_Field.equals("string") && thisPayload.generic_Field.length() > 0) {
            payload += "?" + thisPayload.generic_Field + fieldSeparator;
        }
        if (thisPayload.database != null && !thisPayload.database.equals("string") && thisPayload.database.length() > 0) {
            payload += "B" + thisPayload.database + fieldSeparator;
        }
        if (thisPayload.rFID_card_sub_type != null && !thisPayload.rFID_card_sub_type.equals("string") && thisPayload.rFID_card_sub_type.length() > 0) {
            payload += "J" + thisPayload.rFID_card_sub_type + fieldSeparator;
        }
        if (thisPayload.card_Serial_Number != null && !thisPayload.card_Serial_Number.equals("string") && thisPayload.card_Serial_Number.length() > 0) {
            payload += "S" + thisPayload.card_Serial_Number + fieldSeparator;
        }
        if (thisPayload.vingCard_Code != null && !thisPayload.vingCard_Code.equals("string") && thisPayload.vingCard_Code.length() > 0) {
            payload += "V" + thisPayload.vingCard_Code + fieldSeparator;
        }
        if (thisPayload.check_In_time != null && !thisPayload.check_In_time.equals("string") && thisPayload.check_In_time.length() > 0) {
            payload += "D" + thisPayload.check_In_time + fieldSeparator;
        }
        if (thisPayload.check_Out_Time != null && !thisPayload.check_Out_Time.equals("string") && thisPayload.check_Out_Time.length() > 0) {
            payload += "O" + thisPayload.check_Out_Time + fieldSeparator;
        }

        return payload.substring(0, payload.length() - 1);
    }

}
