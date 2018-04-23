package com.synapse.lock.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synapse.lock.models.Response;
import com.synapse.lock.models.VingCardRequest;
import com.synapse.lock.payload.GenericPayload;
import com.synapse.lock.service.SocketServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author patrick
 */
@RestController
@RequestMapping("/request")
public class RequestController {

    private final Logger LOG = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    SocketServiceImpl socketService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    Environment env;
    
    
    @ApiOperation(value = "Direct Vision Call",
            notes = "Call Vision from Hotelia Spring Boot\n"
            + "Will execute the .exeon client machine.",
            response = Response.class,
            responseContainer = "Object")
    @RequestMapping(value = "/visionCall", method = RequestMethod.POST)
    public ResponseEntity<?> openVisionSoftwarre(@RequestBody VingCardRequest request) {
        ResponseEntity<?> res = null;
        Response response = null;
        response = new Response();
        System.out.println("Received Request>>"  +request.getCommandId());
        if(request.getCommandId().equalsIgnoreCase("openvision"))
        {
            try{
                
                 
                Process process = new ProcessBuilder(env.getProperty("datasource.visionPath")).start();
                 int waitFlag = process.waitFor();
                 
                 if(waitFlag == 0){
                     response.setResponseCode("00001");
                      response.setResponseDescription("Vision Called and exited successfully with exit code " + process.exitValue());
                 }
                
            }
            catch(Exception e){
                e.printStackTrace();
                response = new Response();
                response.setResponseCode("05");
                response.setResponseDescription("Error occured " + e.getMessage());
                res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
            }
            
        }

       

        res = new ResponseEntity<>(response, HttpStatus.OK);

        return res;
        
    }

    @ApiOperation(value = "Check in a guest",
            notes = "Requires Room Name Room List User Type User Group Check In time Check Out Time \n"
            + "Allows both new cards and card copies to be made. If there are no existing keycards with coincident time windows, it will create a new database entry and keycard. If there is an existing keycard with a coincident time window, it will produce a copy of the existing keycard and update the database accordingly.",
            response = Response.class,
            responseContainer = "Object")
    @RequestMapping(value = "/checkInAGuest", method = RequestMethod.POST)
    public ResponseEntity<?> checkInAGuest(@RequestBody GenericPayload request) {
        ResponseEntity<?> res = null;
        Response response = null;
        try {
            if (!request.isUsePostedCommand()) {//check if request wants to use the command that came with payload or not
                request.setCommandId("I");
            }
            LOG.info("Posing checkInAGuest:" + mapper.writeValueAsString(request));
            LOG.info("Posing Payload:" + socketService.getTCPIPPayloadToSend(request));
            response = socketService.sendRequestToEmulator(socketService.getTCPIPPayloadToSend(request));
            res = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response = new Response();
            response.setResponseCode("05");
            response.setResponseDescription("Error occured " + e.getMessage());
            res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "Check out a guest",
            notes = "Requires Room and Name User Type or PMS ID \n"
            + " Checks Out (removes from the database) the specified guest from the specified room.",
            response = Response.class,
            responseContainer = "Object")
    @RequestMapping(value = "/checkOutAGuest", method = RequestMethod.POST)
    public ResponseEntity<?> checkOutAGuest(@RequestBody GenericPayload request) {
        ResponseEntity<?> res = null;
        Response response = null;
        try {
            if (!request.isUsePostedCommand()) {//check if request wants to use the command that came with payload or not
                request.setCommandId("B");
            }
            LOG.info("Posing checkOutAGuest:" + mapper.writeValueAsString(request));
            LOG.info("Posing Payload:" + socketService.getTCPIPPayloadToSend(request));
            response = socketService.sendRequestToEmulator(socketService.getTCPIPPayloadToSend(request));
            res = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response = new Response();
            response.setResponseCode("05");
            response.setResponseDescription("Error occured " + e.getMessage());
            res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "Change Guest Status",
            notes = "Requres Room Name and User Type or PMS ID "
            + "Changes the data associated with the specified guest card, updates the VingCard database and causes a new card to be encoded / printed. As a precaution, the new card will override the old card (which might still be in circulation or lost) and all currently valid guest cards in the guest room lock. However, to replace cards reported as lost, you should use either the Replace (D) or Replace User Id (F) commands\n"
            + "The set of fields used depends on whether the PMS, 1) identifies the guest using a combination of Room Name, User Type and guest names or 2) identifies the guest using a unique PMS ID (originally allocated by the PMS at Check In)",
            response = Response.class,
            responseContainer = "Object")
    @RequestMapping(value = "/changeGuestStatus", method = RequestMethod.POST)
    public ResponseEntity<?> changeGuestStatus(@RequestBody GenericPayload request) {
        ResponseEntity<?> res = null;
        Response response = null;
        try {
            if (!request.isUsePostedCommand()) {//check if request wants to use the command that came with payload or not
                request.setCommandId("C");
            }
            LOG.info("Posing changeGuestStatus:" + mapper.writeValueAsString(request));
            LOG.info("Posing Payload:" + socketService.getTCPIPPayloadToSend(request));
            response = socketService.sendRequestToEmulator(socketService.getTCPIPPayloadToSend(request));
            res = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response = new Response();
            response.setResponseCode("05");
            response.setResponseDescription("Error occured " + e.getMessage());
            res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "Replace Guest Card",
            notes = "Requires Room Name and User Type or PMS ID\n"
            + "Locates the specified card in the VingCard database, updates the database with a new issue time and causes a new card to be encoded / printed. The new card will override the old card (which might still be in circulation or lost) and all currently valid guest cards in the guest room lock. The override works on the grounds of the updated issue time, not  by using Unique User IDs. The Replace User ID Command (F) is preferred if the VingCard system you are interfacing to utilises Unique User IDs \n"
            + "The set of fields used depends on whether the PMS, 1) identifies the guest using a combination of Room Name, User Type and guest names or 2) identifies the guest using a unique PMS ID (originally allocated by the PMS at Check In).",
            response = Response.class,
            responseContainer = "Object")
    @RequestMapping(value = "/replaceGuestCard", method = RequestMethod.POST)
    public ResponseEntity<?> replaceGuestCard(@RequestBody GenericPayload request) {
        ResponseEntity<?> res = null;
        Response response = null;
        try {
            if (!request.isUsePostedCommand()) {//check if request wants to use the command that came with payload or not
                request.setCommandId("D");
            }
            LOG.info("Posing replaceGuestCard:" + mapper.writeValueAsString(request));
            LOG.info("Posing Payload:" + socketService.getTCPIPPayloadToSend(request));
            response = socketService.sendRequestToEmulator(socketService.getTCPIPPayloadToSend(request));
            res = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response = new Response();
            response.setResponseCode("05");
            response.setResponseDescription("Error occured " + e.getMessage());
            res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "Verify Guest Card",
            notes = "NB No payload requeired.Reads a keycard using the specified encoder and returns information about it to the PMS.",
            response = Response.class,
            responseContainer = "Object")
    @RequestMapping(value = "/verifyGuestCard", method = RequestMethod.POST)
    public ResponseEntity<?> verifyGuestCard() {
        ResponseEntity<?> res = null;
        Response response = null;
        try {
            GenericPayload request = new GenericPayload();
            request.setCommandId("E");
            LOG.info("Posing verifyGuestCard:" + mapper.writeValueAsString(request));
            LOG.info("Posing Payload:" + socketService.getTCPIPPayloadToSend(request));
            response = socketService.sendRequestToEmulator(socketService.getTCPIPPayloadToSend(request));
            res = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response = new Response();
            response.setResponseCode("05");
            response.setResponseDescription("Error occured " + e.getMessage());
            res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "Replace Guest User ID",
            notes = "Requires you to set Room Name\n"
            + "User Type and PMS ID Locates the specified card in the VingCard database, updates the database with a new unique user id and causes a new card to be encoded / printed. The new card will override the old card (which might still be in circulation or lost) but NOT other currently valid guest cards in the guest room lock. The override works on the grounds of the updated user ID. This command is preferred to the ‘Replace User’ (D) Command if the VingCard system you are interfacing to utilises Unique User IDs",
            response = Response.class,
            responseContainer = "Object")
    @RequestMapping(value = "/replaceGuestUserId", method = RequestMethod.POST)
    public ResponseEntity<?> replaceGuestUserId(@RequestBody GenericPayload request) {
        ResponseEntity<?> res = null;
        Response response = null;
        try {
            if (!request.isUsePostedCommand()) {//check if request wants to use the command that came with payload or not
                request.setCommandId("F");
            }
            LOG.info("Posing replaceGuestUserId:" + mapper.writeValueAsString(request));
            LOG.info("Posing Payload:" + socketService.getTCPIPPayloadToSend(request));
            response = socketService.sendRequestToEmulator(socketService.getTCPIPPayloadToSend(request));
            res = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response = new Response();
            response.setResponseCode("05");
            response.setResponseDescription("Error occured " + e.getMessage());
            res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "Pre-Check In Guest",
            notes = "Requires you to set Room Name Room List User Type User Group Check In time Check Out Time \n"
            + "Makes keycards that will be valid during a future time window (this is known to as ‘Pre-Check In’ or ‘Pre-registering’). It will create a new database entry and a keycard valid between the Start Time (D field) and End Time (O field) specified by the PMS.",
            response = Response.class,
            responseContainer = "Object")
    @RequestMapping(value = "/preCheckInGuest", method = RequestMethod.POST)
    public ResponseEntity<?> preCheckInGuest(@RequestBody GenericPayload request) {
        ResponseEntity<?> res = null;
        Response response = null;
        try {
            if (!request.isUsePostedCommand()) {//check if request wants to use the command that came with payload or not
                request.setCommandId("G");
            }
            LOG.info("Posing preCheckInGuest:" + mapper.writeValueAsString(request));
            LOG.info("Posing Payload:" + socketService.getTCPIPPayloadToSend(request));
            response = socketService.sendRequestToEmulator(socketService.getTCPIPPayloadToSend(request));
            res = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response = new Response();
            response.setResponseCode("05");
            response.setResponseDescription("Error occured " + e.getMessage());
            res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "Add Guest",
            notes = "Requires you to set Room Name\n"
            + "User Type and User Group\n"
            + "Adds a guest to a room with immediate effect and issues a keycard. The added card will inherit Start, Issue and End Times from the existing card, but may have different User Group, names, PMS ID etc.",
            response = Response.class,
            responseContainer = "Object")
    @RequestMapping(value = "/addGuest", method = RequestMethod.POST)
    public ResponseEntity<?> addGuest(@RequestBody GenericPayload request) {
        ResponseEntity<?> res = null;
        Response response = null;
        try {
            if (!request.isUsePostedCommand()) {//check if request wants to use the command that came with payload or not
                request.setCommandId("H");
            }
            LOG.info("Posing addGuest:" + mapper.writeValueAsString(request));
            LOG.info("Posing Payload:" + socketService.getTCPIPPayloadToSend(request));
            response = socketService.sendRequestToEmulator(socketService.getTCPIPPayloadToSend(request));
            res = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            response = new Response();
            response.setResponseCode("05");
            response.setResponseDescription("Error occured " + e.getMessage());
            res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "Check Out Old, Check In New",
            notes = "Requires you to set Room Name Room List User Type User Group Check In time Check Out Time "
            + "Checks in a guest with immediate effect and issues a keycard. Any existing keycards will be overridden as soon as the new card is used in the lock.",
            response = Response.class,
            responseContainer = "Object")
    @RequestMapping(value = "/checkOutOldGuestCheckInNewGuest", method = RequestMethod.POST)
    public ResponseEntity<?> checkOutOldGuestCheckInNewGuest(@RequestBody GenericPayload request) {
        ResponseEntity<?> res = null;
        Response response = null;
        try {
            if (!request.isUsePostedCommand()) {//check if request wants to use the command that came with payload or not
                request.setCommandId("I");
            }
            LOG.info("Posing checkOutOldGuestCheckInNewGuest:" + mapper.writeValueAsString(request));
            LOG.info("Posing Payload:" + socketService.getTCPIPPayloadToSend(request));
            response = socketService.sendRequestToEmulator(socketService.getTCPIPPayloadToSend(request));
            res = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response = new Response();
            response.setResponseCode("05");
            response.setResponseDescription("Error occured " + e.getMessage());
            res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "Write to Card",
            notes = "Optionally you can set Database Check Out Time"
            + "Causes the VingCard system to remove all guest records from its database. This applies to expired, current and future (pre-checked in) guest cards.",
            response = Response.class,
            responseContainer = "Object")
    @RequestMapping(value = "/writeToCard", method = RequestMethod.POST)
    public ResponseEntity<?> writeToCard(@RequestBody GenericPayload request) {
        ResponseEntity<?> res = null;
        Response response = null;
        try {
            if (!request.isUsePostedCommand()) {//check if request wants to use the command that came with payload or not
                request.setCommandId("!");
            }
            LOG.info("Posing writeToCard:" + mapper.writeValueAsString(request));
            LOG.info("Posing Payload:" + socketService.getTCPIPPayloadToSend(request));
            response = socketService.sendRequestToEmulator(socketService.getTCPIPPayloadToSend(request));
            res = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response = new Response();
            response.setResponseCode("05");
            response.setResponseDescription("Error occured " + e.getMessage());
            res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "Start the localhost Simulated server",
            notes = "Runs on ip 127.0.0.1 port 25000"
            + "This is to simulate a server connection that responds with values. To prove that the application can receiver responses. Before one tests One should set the lockIp to 127.0.0.1, lockPort 25000 and receiveResponseFromServer 1, Restart the application,Click the button Below.the simulate any request above.you should receive responses. You can also check via a network tool to confirm this(Optional) ",
            response = String.class,
            responseContainer = "Object")
    @RequestMapping(value = "/zstartLocalhostSimulatedServer", method = RequestMethod.POST)
    public ResponseEntity<?> startLocalhostSimulatedServer() {
        ResponseEntity<?> res = null;
        Response response = null;
        try {

            LOG.info("Starting server:");
            socketService.simulatedEmulatorToShowApplicationCanReceiveResponses();
            res = new ResponseEntity<>("server 127.0.0.1 is now up", HttpStatus.OK);

        } catch (Exception e) {
            response = new Response();
            response.setResponseCode("05");
            response.setResponseDescription("Error occured " + e.getMessage());
            res = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return res;
    }

}
