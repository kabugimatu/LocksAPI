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
public class JNARegisterPMS {
    
    public static void main(String[] args)
    {
       
        JNALocksInterface.VinguardLibrary INSTANCE = JNALocksInterface.VinguardLibrary.INSTANCE;
      
        System.out.println("Registerring >> " + INSTANCE.PMSifRegister("42860149", "BatchClient")) ;
    }
    
}
