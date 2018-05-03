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
public class JNALocksUnregisterPMS {
    public static void main(String[] args)
    {
       
        JNALocksInterface.LockLibrary INSTANCE = JNALocksInterface.LockLibrary.INSTANCE;
      
        System.out.println("Registerring >> " + INSTANCE.PMSifUnregister()) ;
    }
}
