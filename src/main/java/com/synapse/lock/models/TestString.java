/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.models;

/**
 *
 * @author Kabugi
 */
public class TestString {
    
    public static void main(String[] args)
    {
        String testStr = "zachary";
        
        byte[] strBytes = testStr.getBytes();
        
        for (int i = 0; i < strBytes.length; i++) {
             System.out.println((char)strBytes[i]);
        }
     
        
        
    }
    
}
