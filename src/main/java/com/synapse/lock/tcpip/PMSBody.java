/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.tcpip;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Kabugi
 */
public class PMSBody extends Structure{
    public PMSHeader hdr;
    
    public byte[] appName;
    public byte[]  appLicense;
    public NativeLong ret;

    @Override
    protected List<String> getFieldOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
