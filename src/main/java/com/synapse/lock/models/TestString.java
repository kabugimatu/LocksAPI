/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.models;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.BitSet;
import okio.ByteString;

/**
 *
 * @author Kabugi
 */
public class TestString {

    public static void main(String[] args) throws UnsupportedEncodingException {
       String str = "0101A";
       
       byte[] bytes = str.getBytes("UTF-8");
       
        for (int i = 0; i < bytes.length; i++) {

            String s1 = String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0');
            System.out.println(s1);
           
        }
       
        

    }

}
