/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.models;

import com.sun.jna.*;

/**
 *
 * @author Kabugi
 */
public class JNALocksInterface {
    
    public static String visionPath;
 
    public interface LockLibrary extends Library {

        LockLibrary INSTANCE = (LockLibrary) Native.loadLibrary(visionPath, LockLibrary.class);

        public int PMSifRegister(String license, String appName);

        public int PMSifUnregister();

        public void PMSifEncodeKcdLcl(Pointer ff, Pointer dta, boolean debug, String szOpid, String szOpFirst, String szOpLast);

    }
    
  
    
}
