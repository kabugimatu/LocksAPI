/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.models;

import com.sun.jna.Native;
import com.synapse.lock.payload.GenericPayload;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Kabugi
 */
public class JNAEncodeKeyCard {

    public static void main(String[] args) throws UnsupportedEncodingException {

        JNALocksInterface.LockLibrary INSTANCE = JNALocksInterface.LockLibrary.INSTANCE;
        GenericPayload payLoadSample = new GenericPayload();
        payLoadSample.setRoom_Name("101");
        //  payLoadSample.setRoom_List("101");
        payLoadSample.setUser_Type("Single Room");
        payLoadSample.setFamily_Name("Matu");
        payLoadSample.setFirst_Name("Zachary");
        payLoadSample.setUser_Group("Regular Guest");
        payLoadSample.setCheck_In_time("201805021347");
        payLoadSample.setCheck_Out_Time("201805030111");

        // payLoadSample.setpMS_ID("121212");
        String data = getPayloadToSend(payLoadSample);

        System.out.println("Data >> " + data);
        String commandCode = "I";

        byte[] dataBytes = new byte[data.length() + 1];
        System.arraycopy(data.getBytes("UTF-8"), 0, dataBytes, 0, data.length());
        dataBytes[data.length()] = 0;

        byte[] bitByteArray = new byte[data.length() + 1];

        byte[] commanCodeBytesConv = commandCode.getBytes("UTF-8");

        for (int i = 0; i < dataBytes.length; i++) {

            String s1 = String.format("%8s", Integer.toBinaryString(dataBytes[i] & 0xFF)).replace(' ', '0');
            //   System.out.println(s1);

//            if ((char) dataBytes[i] == '*') {
//                bitByteArray[i] = 30;
//            } 
            
        
                int val = Integer.parseInt(s1, 2);
                byte b = (byte) val;
                bitByteArray[i] = b;
           // }

        }
        byte[] commandCodeFinal = new byte[1];
        for (int i = 0; i < commanCodeBytesConv.length; i++) {
            String s2 = String.format("%8s", Integer.toBinaryString(commanCodeBytesConv[i] & 0xFF)).replace(' ', '0');
            System.out.println(s2);

            int val = Integer.parseInt(s2, 2);
            byte b = (byte) val;
            commandCodeFinal[i] = b;
        }

        String userNameBytes = "zkmatu";
        String userFirstNameBytes = "Zack";
        String userLastNameBytes = "Matu";

        INSTANCE.PMSifEncodeKcdLcl(commandCodeFinal, bitByteArray, false, userNameBytes, userFirstNameBytes, userLastNameBytes);

        String ffResponse = Native.toString(commandCodeFinal, "UTF-8");
        String dtaResponse = Native.toString(bitByteArray, "UTF-8");

        System.out.println("FF Response >>  " + ffResponse);
        System.out.println("DTA Response >>  " + dtaResponse);
    }

    public static String getPayloadToSend(GenericPayload thisPayload) {
        String fieldSeparator = "*";//Character.toString ((char) 30);

        String payload = fieldSeparator;

        if (thisPayload.room_Name != null && !thisPayload.room_Name.equals("string") && thisPayload.room_Name.length() > 0) {
            payload += "R" + thisPayload.room_Name + fieldSeparator;
        }

        if (thisPayload.user_Type != null && !thisPayload.user_Type.equals("string") && thisPayload.user_Type.length() > 0) {
            payload += "T" + thisPayload.user_Type + fieldSeparator;
        }

        if (thisPayload.first_Name != null && !thisPayload.first_Name.equals("string") && thisPayload.first_Name.length() > 0) {
            payload += "F" + thisPayload.first_Name + fieldSeparator;
        }

        if (thisPayload.family_Name != null && !thisPayload.family_Name.equals("string") && thisPayload.family_Name.length() > 0) {
            payload += "N" + thisPayload.family_Name + fieldSeparator;
        }
        if (thisPayload.user_Group != null && !thisPayload.user_Group.equals("string") && thisPayload.user_Group.length() > 0) {
            payload += "U" + thisPayload.user_Group + fieldSeparator;
        }

        if (thisPayload.check_In_time != null && !thisPayload.check_In_time.equals("string") && thisPayload.check_In_time.length() > 0) {
            payload += "D" + thisPayload.check_In_time + fieldSeparator;
        }
        if (thisPayload.check_Out_Time != null && !thisPayload.check_Out_Time.equals("string") && thisPayload.check_Out_Time.length() > 0) {
            payload += "O" + thisPayload.check_Out_Time + fieldSeparator;
        }
        if (thisPayload.room_List != null && !thisPayload.room_List.equals("string") && thisPayload.room_List.length() > 0) {
            payload += "L" + thisPayload.room_List + fieldSeparator;
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

        return payload.substring(0, payload.length() - 1);
    }

}
