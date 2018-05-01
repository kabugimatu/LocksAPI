/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.payload;

import java.nio.ByteBuffer;

/**
 *
 * @author Kabugi
 */
public class VinguadRegistrationPayload {
     public int synch1;
    public int synch2;
    public short version;
    public int commandId;
    public int bodySize;
}
