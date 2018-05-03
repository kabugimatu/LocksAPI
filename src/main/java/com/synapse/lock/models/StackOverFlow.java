/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.models;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author zkmatu
 */
public class StackOverFlow {
    
    public static void main(String[] args) throws UnsupportedEncodingException
    {
         JNALocksInterface.LockLibrary INSTANCE = JNALocksInterface.LockLibrary.INSTANCE;
        String dta = "*R101*L101*TSingle Room*NMatu*FZachary*URegular Guest*D201805021347*O201805030111";
        String ff = "A";
        byte[] dataBytes = new byte[dta.length() + 1];
        System.arraycopy(dta.getBytes("UTF-8"), 0, dataBytes, 0, dta.length());
        dataBytes[dta.length()] = 0;

        byte[] dtaByteArray = new byte[dta.length() + 1];

        byte[] ffByteArray = ff.getBytes("UTF-8");

       

        for (int i = 0; i < dataBytes.length; i++) {

            String s1 = String.format("%8s", Integer.toBinaryString(dataBytes[i] & 0xFF)).replace(' ', '0');
         //   System.out.println(s1);
         
           if((char)dataBytes[i] == '*')
           {
               dtaByteArray[i] = 30;
           }
           else{
                int val = Integer.parseInt(s1, 2);
               byte b = (byte) val;
               dtaByteArray[i] = b;
           }
           
           
        }
        byte[] commandCodeFinal = new byte[1];
        for (int i = 0; i < ffByteArray.length; i++) {
            String s2 = String.format("%8s", Integer.toBinaryString(ffByteArray[i] & 0xFF)).replace(' ', '0');
            System.out.println(s2);

            int val = Integer.parseInt(s2, 2);
            byte b = (byte) val;
            commandCodeFinal[i] = b;
        }

       

        String userNameBytes = "zkmatu";
        String userFirstNameBytes = "Zack";
        String userLastNameBytes = "Matu";

        INSTANCE.PMSifEncodeKcdLcl(commandCodeFinal, dtaByteArray, false, userNameBytes, userFirstNameBytes, userLastNameBytes);

    }
    
}
