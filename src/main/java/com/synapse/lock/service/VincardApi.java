/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.service;

/**
 *
 * @author patrick
 */
//haven't found a use case for this interface
//Done it just in case as the documentation requires
//Once use found implementation will be done
public interface VincardApi {
    
    int PMSifRegister(String szLicense, String szApplName);
    
    int PMSifUnregister();
    
    boolean PMSifShutdownVision ();
    
    String PMSifGetUserGroups(String Dta, int nCount);
    
    String PMSifGetDefUserGroup(String Dta);
    
    String PMSifGetKeycardTypes (String Dta ,int nCount);
    
    String PMSifGetDefKeycardType (String Dta);
    
    String PMSifGetAddress(String Dta, String szWorkstation);
    
    void PMSifEncodeKcdLcl(String ff, String Dta, boolean Dbg, String szOpId, String szOpFirst, String szOpLast);
    
    void PMSifEncodeKcdRmt (String ff, String Dta, String dd, String ss, boolean Dbg, String szOpId, String szOpFirst, String szOpLast);
    
    String PMSifReturnKcdLcl(String ff, String Dta, boolean Dbg, String szOpID, String szOpFirst, String szOpLast);
    
    String PMSifTransformKeycode (String Dta);
    
}
