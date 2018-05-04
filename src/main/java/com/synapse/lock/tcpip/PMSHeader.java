/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.tcpip;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kabugi
 */
public class PMSHeader extends Structure {
    
    public NativeLong synch1;
    public NativeLong synch2;
    public int version = 1;
    public NativeLong commandid ;
     public NativeLong bodySize ;

    @Override
    protected List<String> getFieldOrder() {
     return  Arrays.asList(new String[] { "synch1", "synch2", "version", "commandid","bodySize" });
    }
    

   

   

}
