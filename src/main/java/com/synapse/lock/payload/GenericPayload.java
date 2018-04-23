/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synapse.lock.payload;

import com.synapse.lock.models.UnspecifiedFields;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 *
 * @author patrick
 */
public class GenericPayload {

    public String commandId;
    public String room_Name;
    public String room_List;
    public String user_Type;
    public String user_Group;
    public String check_In_time;
    public String check_Out_Time;
    public String first_Name;
    public String family_Name;
    public String access_Points;
    public String card_Copy;
    public String pMS_ID;
    public String track_1_data;
    public String track_2_data;
    public String track_3_data;
    public String print_Information;
    public String generic_Field;
    public String database;
    public String rFID_card_sub_type;
    public String card_Serial_Number;
    public String vingCard_Code;
    public boolean usePostedCommand;
    public List<UnspecifiedFields> unspecifiedFields;

    public String getTrack_3_data() {
        return track_3_data;
    }

    public void setTrack_3_data(String track_3_data) {
        this.track_3_data = track_3_data;
    }

    public boolean isUsePostedCommand() {
        return usePostedCommand;
    }

    public void setUsePostedCommand(boolean usePostedCommand) {
        this.usePostedCommand = usePostedCommand;
    }

    public List<UnspecifiedFields> getUnspecifiedFields() {
        return unspecifiedFields;
    }

    public void setUnspecifiedFields(List<UnspecifiedFields> unspecifiedFields) {
        this.unspecifiedFields = unspecifiedFields;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getRoom_Name() {
        return room_Name;
    }

    public void setRoom_Name(String room_Name) {
        this.room_Name = room_Name;
    }

    public String getRoom_List() {
        return room_List;
    }

    public void setRoom_List(String room_List) {
        this.room_List = room_List;
    }

    public String getUser_Type() {
        return user_Type;
    }

    public void setUser_Type(String user_Type) {
        this.user_Type = user_Type;
    }

    public String getUser_Group() {
        return user_Group;
    }

    public void setUser_Group(String user_Group) {
        this.user_Group = user_Group;
    }

    public String getCheck_In_time() {
        return check_In_time;
    }

    public void setCheck_In_time(String check_In_time) {
        this.check_In_time = check_In_time;
    }

    public String getCheck_Out_Time() {
        return check_Out_Time;
    }

    public void setCheck_Out_Time(String check_Out_Time) {
        this.check_Out_Time = check_Out_Time;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getFamily_Name() {
        return family_Name;
    }

    public void setFamily_Name(String family_Name) {
        this.family_Name = family_Name;
    }

    public String getAccess_Points() {
        return access_Points;
    }

    public void setAccess_Points(String access_Points) {
        this.access_Points = access_Points;
    }

    public String getCard_Copy() {
        return card_Copy;
    }

    public void setCard_Copy(String card_Copy) {
        this.card_Copy = card_Copy;
    }

    public String getpMS_ID() {
        return pMS_ID;
    }

    public void setpMS_ID(String pMS_ID) {
        this.pMS_ID = pMS_ID;
    }

    public String getTrack_1_data() {
        return track_1_data;
    }

    public void setTrack_1_data(String track_1_data) {
        this.track_1_data = track_1_data;
    }

    public String getTrack_2_data() {
        return track_2_data;
    }

    public void setTrack_2_data(String track_2_data) {
        this.track_2_data = track_2_data;
    }

    public String getPrint_Information() {
        return print_Information;
    }

    public void setPrint_Information(String print_Information) {
        this.print_Information = print_Information;
    }

    public String getGeneric_Field() {
        return generic_Field;
    }

    public void setGeneric_Field(String generic_Field) {
        this.generic_Field = generic_Field;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getrFID_card_sub_type() {
        return rFID_card_sub_type;
    }

    public void setrFID_card_sub_type(String rFID_card_sub_type) {
        this.rFID_card_sub_type = rFID_card_sub_type;
    }

    public String getCard_Serial_Number() {
        return card_Serial_Number;
    }

    public void setCard_Serial_Number(String card_Serial_Number) {
        this.card_Serial_Number = card_Serial_Number;
    }

    public String getVingCard_Code() {
        return vingCard_Code;
    }

    public void setVingCard_Code(String vingCard_Code) {
        this.vingCard_Code = vingCard_Code;
    }

}
