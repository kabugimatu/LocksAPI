/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.service;

import com.synapse.lock.models.TransactionShell;
import com.synapse.lock.models.TransactionTypes;
import org.springframework.stereotype.Service;

/**
 *
 * @author patrick
 */
@Service("globalFunctions")
public class GlobalFunctions {
    
    public String GenerateIsoString(String requShell,String type){
        switch(type){
            case TransactionTypes.REGISTER_SINGLE:
                break;
            case TransactionTypes.REGISTER_MULTIPLE:
                break;
            case TransactionTypes.PRECHECK_SINGLE_GUEST:
                break;
             
                
        }
        return "";
    }
    
}
