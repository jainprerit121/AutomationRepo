/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : SchoolAdminUsersLandingPageTest.java
 * 
 * Description : TestObject for Users Landing page UI functionalities for School
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

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.LocatorConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.UsersBasePage;

/**
 * Users landing page UI functionalities for School Administrator is tested
 * using this class.
 */
public class SchoolAdminUsersLandingPageTest extends UsersBaseTest {

    public SchoolAdminUsersLandingPageTest() {
        log = Logger.getLogger( SchoolAdminUsersLandingPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME );
        userHelper.deleteAdmin( UsersConstants.USER_SCHOOL_ADMIN );
        Integer orgId = orgHelper.createOrganization( UsersConstants.USER_ORG_NAME, UsersConstants.USER_ORG_ID );
        userHelper.createSchoolAdmin( AdminUIConstants.SCHOOL_ADMIN_FIRSTNAME, AdminUIConstants.SCHOOL_ADMIN_LASTNAME, UsersConstants.USER_SCHOOL_ADMIN, AdminUIConstants.DEFAULT_ENCRYPTED_PASSWORD, orgId );
        loginPage.login( UsersConstants.USER_SCHOOL_ADMIN, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 100, description = "Entry Criteria: User is Users landing page.</br>Execution Criteria: Default organization selected is validated.</br>Exit Criteria: User is in Users landing page. " )
    public void checkDefaultOrgSelectedInDisplayDropDown() {
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInOrgDropDown(), UsersConstants.USER_ORG_NAME, "Default selected org is not equals " + UsersConstants.USER_ORG_NAME + "" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 102, description = "Entry Criteria: User is in Userslanding page</br>Execution Criteria: Welcome message is verified for logged in User.</br>Exit Criteria: User is in Users landing page." )
    public void checkUsersWelcomeMessage() {
        String welcomeText = appProperties.getProperty( "UsersWelcomeText" );
        welcomeText = welcomeText.replaceFirst( "\"Dynamic\"", UsersConstants.STRING_DELIM + UsersConstants.USER_ORG_NAME + UsersConstants.STRING_DELIM );
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersWelcomeText(), welcomeText, "Expected Welcome message is not displayed!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 401, description = "Entry Criteria: User is Users landing page After logging in as School Admin.</br>Execution Criteria:Transfer Students button status is verified in Users landing page.</br>Exit Criteria: User is Users landing page." )
    public void checkTransferStudentsBtnInactive() {
        CommonHelper.nap();
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersTransferStudentBtnStatus(), LocatorConstants.BUTTON_DISABLED_CLASS, "Transfer Students button is enabled!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 402, description = "Entry Criteria: User is in Users landing page.</br>Execution: Display list box values are verified.</br>Exit Criteria: User is in Users landing page." )
    public void checkDisplayDropDownValues() {
        List<String> expectedOrgNames = orgHelper.getOrganizationNames( UsersConstants.USER_SCHOOL_ADMIN );
        List<String> actualOrgNames = ( (UsersBasePage) page ).getAllDropDownItemsInOrgDropDown();
        Assert.assertEquals( actualOrgNames.size(), 1, "Mismatch in number of organizations listed and actual number of organizations." );
        actualOrgNames.removeAll( expectedOrgNames );
        Assert.assertEquals( actualOrgNames.size(), 0, "Extra organizations are present" );
    }

    @Test ( priority = 403, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Presence of Add Group button in Users landing page is verified.</br>Exit Criteria: User is in Users landing page." )
    public void checkPresenceOfAddGroupBtn() {
        Assert.assertNotNull( ( (UsersBasePage) page ).getUsersAddGroupBtn(), "Users page Add Group button is not present." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 404, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Add Group button is verified in Users landing page.</br>User in Users landing page." )
    public void checkUsersAddGroupBtnText() {
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersAddGroupBtn().getText(), appProperties.getProperty( "UsersAddGroupBtnText" ), "Mismatch is observed in Add Group button label." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 405, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Presence of Add User button in Users landing page is verified..</br>User in Users landing page." )
    public void checkPresenceOfAddUserBtn() {
        Assert.assertNotNull( ( (UsersBasePage) page ).getUsersAddUserBtn(), "Users page Add User button is not present." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 406, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Add User button is verified in Users landing page.</br>User in Users landing page." )
    public void checkUsersAddUserBtnText() {
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersAddUserBtn().getText(), appProperties.getProperty( "UsersAddUserBtnText" ), "Mismatch is observed in Add User button label" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 407, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Logout button is clicked and navigation is verified from Users landing page.</br>Exit Criteria: User is in Login page." )
    public void checkLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

}
