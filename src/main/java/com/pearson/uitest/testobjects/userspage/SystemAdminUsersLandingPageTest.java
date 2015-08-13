/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : SystemAdminUsersLandingPageTest.java
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
 * Users landing page UI functionalities are tested after logging in as System
 * Administrator.
 */
public class SystemAdminUsersLandingPageTest extends SystemDistrictAdminUsersBaseTest {

    public SystemAdminUsersLandingPageTest() {
        log = Logger.getLogger( SystemAdminUsersLandingPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME );
        orgHelper.createOrganization( UsersConstants.USER_ORG_NAME, UsersConstants.USER_ORG_ID );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 401, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Presence of Add Group button in Users landing page is verified.</br>Exit Criteria: User is in Users landing page." )
    public void checkPresenceOfAddGroupBtn() {
        Assert.assertNotNull( ( (UsersBasePage) page ).getUsersAddGroupBtn(), "Users page Add Group button is not present." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 402, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Add Group button is verified in Users landing page.</br>User in Users landing page." )
    public void checkUsersAddGroupBtnText() {
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersAddGroupBtn().getText(), appProperties.getProperty( "UsersAddGroupBtnText" ), "Mismatch is observed in Add Group button label." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 403, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Presence of Add User button in Users landing page is verified..</br>User in Users landing page." )
    public void checkPresenceOfAddUserBtn() {
        Assert.assertNotNull( ( (UsersBasePage) page ).getUsersAddUserBtn(), "Users page Add User button is not present." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 404, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Add User button is verified in Users landing page.</br>User in Users landing page." )
    public void checkUsersAddUserBtnText() {
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersAddUserBtn().getText(), appProperties.getProperty( "UsersAddUserBtnText" ), "Mismatch is observed in Add User button label" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 405, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Logout button is clicked and navigation is verified from Users landing page.</br>Exit Criteria: User is in Login page." )
    public void checkLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

}
