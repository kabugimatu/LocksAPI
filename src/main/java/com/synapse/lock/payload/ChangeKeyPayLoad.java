/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.payload;

/**
 *
 * @author Kabugi
 */
public class ChangeKeyPayLoad {
    String roomName;
    String roomType;
    String guestLastName;
    String guestFirstName;
    String newDepartureDate;

    public String getNewDepartureDate() {
        return newDepartureDate;
    }

    public void setNewDepartureDate(String newDepartureDate) {
        this.newDepartureDate = newDepartureDate;
    }
    
    
    

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getGuestLastName() {
        return guestLastName;
    }

    public void setGuestLastName(String guestLastName) {
        this.guestLastName = guestLastName;
    }

    public String getGuestFirstName() {
        return guestFirstName;
    }

    public void setGuestFirstName(String guestFirstName) {
        this.guestFirstName = guestFirstName;
    }
    
    
    
    
}
