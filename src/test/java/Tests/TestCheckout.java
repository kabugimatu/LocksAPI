/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import com.google.gson.Gson;
import com.synapse.lock.payload.CheckoutPayload;

/**
 *
 * @author Kabugi
 */
public class TestCheckout {
    
    public static void main(String[] args)
    {
        
        try{
        CheckoutPayload checkoutPayLoad = new CheckoutPayload();
        checkoutPayLoad.setRoomName("101");
        checkoutPayLoad.setRoomType("Standard");
        checkoutPayLoad.setUserCheckin("zkmatu");
        checkoutPayLoad.setUserFirstName("Zack");
        checkoutPayLoad.setUserLastName("Matu");
          String jsonRequest = new Gson().toJson(checkoutPayLoad);
            System.out.println("Request >> " + jsonRequest);
            String response = new HTTPCaller().makeHTTPCall(jsonRequest, "http://localhost:8095/lock/request/checkOutAGuest");
            System.out.println("Response >> " + response);
        }
        catch(Exception ex)
        {
            
        }
    }
    
}
