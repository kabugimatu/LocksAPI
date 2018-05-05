package com.synapse.lock.service;

import com.synapse.lock.payload.ChangeKeyPayLoad;
import com.synapse.lock.payload.CheckinPayload;
import com.synapse.lock.payload.CheckoutPayload;
import com.synapse.lock.payload.GenericPayload;
import com.synapse.lock.payload.ReplaceCardPayload;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author patrick
 */
@Service("SocketServiceImpl")
public class LockServiceImpl {

    private final Logger LOG = LoggerFactory.getLogger(LockServiceImpl.class);

    @Autowired
    Environment env;

   
    

    public String checkinPayload(){
        String checkinPayload = "";
        
        return checkinPayload;
    }
    
    
    public GenericPayload generateReplaceCardPayLoad(ReplaceCardPayload payLoad){
        GenericPayload payLoadSample = new GenericPayload();
        payLoadSample.setRoom_Name(payLoad.getRoomName());
        payLoadSample.setUser_Type(payLoad.getRoomType());
        payLoadSample.setFamily_Name(payLoad.getGuestLastName());
        payLoadSample.setFamily_Name(payLoad.getGuestFirstName());
        
        return payLoadSample;
    }
    
    public GenericPayload generateChangeKeyPayLoad(ChangeKeyPayLoad payLoad)
    {
         GenericPayload payLoadSample = new GenericPayload();
        payLoadSample.setRoom_Name(payLoad.getRoomName());
        payLoadSample.setUser_Type(payLoad.getRoomType());
        payLoadSample.setFamily_Name(payLoad.getGuestLastName());
        payLoadSample.setFamily_Name(payLoad.getGuestFirstName());
        payLoadSample.setCheck_Out_Time(payLoad.getNewDepartureDate());
        
        return payLoadSample;
        
    }
     public GenericPayload generateCheckOutPayload(CheckoutPayload payLoad)
    {
        
        
        GenericPayload payLoadSample = new GenericPayload();
        payLoadSample.setRoom_Name(payLoad.getRoomName());
        payLoadSample.setUser_Type(payLoad.getRoomType());
      
       
        return payLoadSample;
        
      
        
    }
    
    public GenericPayload generateCheckInPayload(CheckinPayload payLoad)
    {
      
        GenericPayload payLoadSample = new GenericPayload();
        payLoadSample.setRoom_Name(payLoad.getRoomName());
        payLoadSample.setUser_Type(payLoad.getRoomType());
        payLoadSample.setUser_Group(payLoad.getUserGroup());
        payLoadSample.setCheck_In_time(payLoad.getCheckinTime());
        payLoadSample.setCheck_Out_Time(payLoad.getCheckoutTime());
        payLoadSample.setFamily_Name(payLoad.getGuestLastName());
        payLoadSample.setFirst_Name(payLoad.getGuestFirstName());
        payLoadSample.setpMS_ID(payLoad.getPmsId());
       
        return payLoadSample;
                
    }
 
   

}
