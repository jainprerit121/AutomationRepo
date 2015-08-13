/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : DistrictAdminUsersLandingPageTest.java
 * 
 * Description : TestObject for Users Landing page UI functionalities for System
 * Administrator.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/
package com.pearson.uitest.testobjects.userspage;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.pageobjects.UsersBasePage;

/**
 * Users landing page UI functionalities are tested after logging in as District
 * Administrator.
 */
public class DistrictAdminUsersLandingPageTest extends SystemDistrictAdminUsersBaseTest {

    public DistrictAdminUsersLandingPageTest() {
        log = Logger.getLogger( DistrictAdminUsersLandingPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME );
        orgHelper.createOrganization( UsersConstants.USER_ORG_NAME, UsersConstants.USER_ORG_ID );
        loginPage.login( AdminUIConstants.DISTRICT_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 401, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Absence of Add Group button in Users landing page is verified.</br>Exit Criteria: User is in Users landing page." )
    public void checkAbsenceOfAddGroupBtn() {
        Assert.assertFalse( ( (UsersBasePage) page ).getUsersAddGroupBtn().isDisplayed(), "Users page Add Group button is present!!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 402, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Absence of Add User button in Users landing page is verified..</br>User in Users landing page." )
    public void checkAbsenceOfAddUserBtn() {
        Assert.assertFalse( ( (UsersBasePage) page ).getUsersAddUserBtn().isDisplayed(), "Users page Add User button is present!!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 403, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Logout button is clicked and navigation is verified from Users landing page.</br>Exit Criteria: User is in Login page." )
    public void checkLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }
}
