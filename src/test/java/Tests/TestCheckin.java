/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import com.google.gson.Gson;
import com.synapse.lock.payload.CheckinPayload;

/**
 *
 * @author zkmatu
 */
public class TestCheckin {

    public static void main(String[] args) {
        try {

            CheckinPayload checkinPayload = new CheckinPayload();
            checkinPayload.setCheckinTime("201805040940");
            checkinPayload.setCheckoutTime("201805290940");
            checkinPayload.setGuestFirstName("Zachary");
            checkinPayload.setGuestLastName("Matu");
            checkinPayload.setRoomName("101");
            checkinPayload.setRoomType("Single Room");
            checkinPayload.setUserGroup("Regular Guest");
            checkinPayload.setUserCheckin("zkmatu");
            checkinPayload.setUserFirstName("Zachary");
            checkinPayload.setUserLastName("Matu");
           
            //checkinPayload.set

            String jsonRequest = new Gson().toJson(checkinPayload);
            System.out.println("Request >> " + jsonRequest);
            String response = new HTTPCaller().makeHTTPCall(jsonRequest, "http://192.168.0.23:8095/lock/request/checkInAGuest");
            System.out.println("Response >> " + response);

        } catch (Exception ex) {
            if (ex instanceof java.net.SocketTimeoutException) {

            } else {
                ex.printStackTrace();
            }
        }
    }

}
