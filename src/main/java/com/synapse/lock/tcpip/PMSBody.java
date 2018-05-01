/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.tcpip;

import java.io.Serializable;

/**
 *
 * @author Kabugi
 */
public class PMSBody implements Serializable{
    public PMSHeader hdr;
    
    public String appName;
    public  String appLicense;
    public int ret;
    
    
}
