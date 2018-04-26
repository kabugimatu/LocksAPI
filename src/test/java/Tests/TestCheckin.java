/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import com.google.gson.Gson;
import com.synapse.lock.payload.GenericPayload;

/**
 *
 * @author zkmatu
 */
public class TestCheckin {
    
    public static void main(String[] args)
    {
        try{
            
            GenericPayload payLoadSample = new GenericPayload();
            payLoadSample.setRoom_Name("101");
            payLoadSample.setRoom_List("101");
            payLoadSample.setUser_Type("SingleStandard");
            payLoadSample.setUser_Group("GUEST");
            payLoadSample.setCheck_In_time("");
            payLoadSample.setCheck_Out_Time("");
            payLoadSample.setFamily_Name("Matu");
            payLoadSample.setFirst_Name("ZACHARY");
            payLoadSample.setpMS_ID("121212");
            
            String jsonRequest = new Gson().toJson(payLoadSample);
            System.out.println("Request >> " + jsonRequest);
            String response = new HTTPCaller().makeHTTPCall(jsonRequest, "");
            
            
            
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
