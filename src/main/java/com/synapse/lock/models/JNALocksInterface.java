/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.models;

import com.sun.jna.*;
import com.synapse.lock.payload.GenericPayload;
import com.synapse.lock.service.LockServiceImpl;

/**
 *
 * @author Kabugi
 */
public class JNALocksInterface {
    public static native int PMSifRegister(String license ,String appName);
  
    public static native void PMSifEncodeKcdLcl(String commandCode,String pmsData,boolean debugMode,String user,String userFirstName,String userLastName);
    static {
        Native.register("win32-x86-64pmsif.dll");
    }

    public static void main(String[] args) {
        
      //  System.out.println("Int response >> " + PMSifRegister("42860149","BatchClient"));
        GenericPayload payLoadSample = new GenericPayload();
        payLoadSample.setRoom_Name("101");
        payLoadSample.setRoom_List("101");
        payLoadSample.setUser_Type("GUEST");
        payLoadSample.setUser_Group("GUEST");
        payLoadSample.setCheck_In_time("");
        payLoadSample.setCheck_Out_Time("");
        payLoadSample.setFamily_Name("Matu");
        payLoadSample.setFirst_Name("ZACHARY");
        payLoadSample.setpMS_ID("121212");
        
        LockServiceImpl lockService = new LockServiceImpl();
        String data = lockService.getPayloadToSend(payLoadSample);

        
        PMSifEncodeKcdLcl("I", data, true, "zkmatu", "Zachary", "Matu");
    }
}
