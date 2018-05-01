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
public class PMSHeader implements Serializable {
    
    public int synch1;
    public int synch2;
    public short version = 1;
    public int commandid ;
     public int bodySize ;
    

   

   

}
