/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.models;

import com.sun.jna.*;
import com.sun.jna.win32.StdCallLibrary;

/**
 *
 * @author Kabugi
 */
public class JNALocksInterface {

    public interface VinguardLibrary extends StdCallLibrary {

        
        VinguardLibrary INSTANCE = (VinguardLibrary) Native.loadLibrary("C:\\Program Files (x86)\\VingCard\\Vision\\pmsif.dll", VinguardLibrary.class);

        VinguardLibrary SYNC_INSTANCE = (VinguardLibrary) Native.synchronizedLibrary(INSTANCE);

        public int PMSifRegister(String license, String appName);

        public int PMSifUnregister();
        
        public void PMSifEncodeKcdLcl(String b,byte[] dta,boolean debug,String szOpid,String szOpFirst,String szOpLast);


    }

   

}
