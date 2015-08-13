/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : UsersTypeDropDown.java
 * 
 * Description : Constants related to Users type dropdown are defined here.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/

package com.pearson.uitest.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Users type dropdown values are stored as enum here.
 */
public enum UsersTypeDropDown {

    ALL_GROUPS( "-- All Groups --" ),
    ALL_USER_TYPE( "-- All User Types --" ),
    ALL_ADMIN( "-- All Administrators --" ),
    ALL_TEACHER( "-- All Teachers --" ),
    ALL_ACTIVE_STUDENTS( "-- All Active Students --" ),
    UNASSIGNED_STUDENTS( "-- Unassigned students --" );

    private String userType;

    private UsersTypeDropDown( String usrType ) {
        userType = usrType;
    }

    public String getType() {
        return userType;
    }

    public static List<String> getUserTypes() {
        List<String> values = new ArrayList<String>();
        for ( UsersTypeDropDown types : UsersTypeDropDown.values() ) {
            values.add( types.userType );
        }
        return values;
    }

}
