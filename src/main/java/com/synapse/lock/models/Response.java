/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.models;

/**
 *
 * @author patrick
 */
public class Response {
    String responseCode;
    String responseDescription;
    String payloadSent;
    String responseFromServer;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getPayloadSent() {
        return payloadSent;
    }

    public void setPayloadSent(String payloadSent) {
        this.payloadSent = payloadSent;
    }

    public String getResponseFromServer() {
        return responseFromServer;
    }

    public void setResponseFromServer(String responseFromServer) {
        this.responseFromServer = responseFromServer;
    }
    
}
