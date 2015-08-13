/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : DistrictAdminUsersPageTest.java
 * 
 * Description : TestObject for Users Landing page UI functionalities.
 * 
 * Written By : Harisha Prabhu, Gururaj Bhat, Kamal Kant Srivastav
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/

package com.pearson.uitest.testobjects.userspage;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.UsersConstants;

/**
 * All the District Admin tests related to Users page are defined here.
 */
public class DistrictAdminUsersPageTest extends SystemDistrictAdminUsersPageBaseTest {

    public DistrictAdminUsersPageTest() {
        log = Logger.getLogger( DistrictAdminUsersPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME );
        orgHelper.createOrganization( UsersConstants.USER_ORG_NAME, UsersConstants.USER_ORG_ID );
        loginPage.login( AdminUIConstants.DISTRICT_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 401, description = "Entry Criteria: User is in users landing page.</br>Execution Criteria: Logout button is clicked and navigation is verified from groups landing page.</br>Exit Criteria: User is in Login page." )
    public void checkLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );

    }

}