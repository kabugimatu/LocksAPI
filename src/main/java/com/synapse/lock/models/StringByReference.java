/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.models;

import com.sun.jna.ptr.ByReference;

/**
 *
 * @author Kabugi
 */
public class StringByReference extends ByReference {
    public StringByReference() {
        this(0);
    }

    public StringByReference(int size) {
        super(size < 4 ? 4 : size);
        getPointer().clear(size < 4 ? 4 : size);
    }

    public StringByReference(String str) {
        super(str.length() < 4 ? 4 : str.length() + 1);
        setValue(str);
    }

    private void setValue(String str) {
        getPointer().setString(0, str);
    }

    public String getValue() {
        return getPointer().getString(0);
    }
}
