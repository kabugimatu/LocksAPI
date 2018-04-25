package com.synapse.lock;

import com.synapse.lock.models.JNALocksInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
                JNALocksInterface locksInterface = new JNALocksInterface();
                System.out.println("Registering Locks Interface...");
                int response= JNALocksInterface.PMSifRegister("42860149","BatchClient");
                if( response==0)
                {
                    System.out.println("Hurray Zack..Registered successfully....");
                }
                else{
                    System.out.println("Registration Failed; Response Code :: " +response);
                }
                
	}
}
