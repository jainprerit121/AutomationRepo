/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : SystemAdminGroupsLandingPageTest.java
 * 
 * Description : TestObject for groups Landing page UI functionalities for
 * system admin.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/
package com.pearson.uitest.testobjects.grouppage;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.GroupsConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.pageobjects.GroupsLandingPage;
import com.pearson.uitest.testobjects.userspage.SystemAdminUsersLandingPageTest;

/**
 * Groups landing page UI functionalities will be tested using this class.
 */
public class SystemAdminGroupsLandingPageTest extends SystemDistrictAdminGroupsBaseTest {

    public SystemAdminGroupsLandingPageTest() {
        log = Logger.getLogger( SystemAdminUsersLandingPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME );
        orgHelper.deleteOrganization( GroupsConstants.GROUP_LESS_SCHOOL_NAME );
        Integer orgId1 = orgHelper.createOrganization( UsersConstants.USER_ORG_NAME, UsersConstants.USER_ORG_ID );
        orgHelper.createOrganization( GroupsConstants.GROUP_LESS_SCHOOL_NAME, GroupsConstants.GROUP_LESS_SCHOOL_ID );
        Integer teacherId = userHelper.createTeacher( AdminUIConstants.TEACHER_FIRSTNAME, AdminUIConstants.TEACHER_LASTNAME, AdminUIConstants.TEACHER_USERNAME, AdminUIConstants.DEFAULT_PASSWORD, orgId1 );
        userHelper.createGroup( GroupsConstants.GROUP_NAME, GroupsConstants.GROUP_TYPE_ID, teacherId, GroupsConstants.GROUP_DESCRIPTION );
        userHelper.createGroup( GroupsConstants.GROUP_NAME_SECOND, GroupsConstants.GROUP_TYPE_ID, teacherId, GroupsConstants.GROUP_DESCRIPTION_SECOND );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 401, description = "Entry Criteria: User is in  group page.</br>Execution Criteria: Add group button is clicked and navigation is verified from groups landing page.</br>Exit Criteria: User is in Add group page." )
    public void clickOnAddGroupBtn() {
        Map<String, String> actualPageDetails = ( (GroupsLandingPage) page ).clickUsersAddGroupBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "UserAddGroupUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 402, description = "Entry Criteria: User is in add group page.</br>Execution Criteria: Logout button is clicked and navigation is verified from groups landing page.</br>Exit Criteria: User is in Login page." )
    public void checkLogout() {
        ( (GroupsLandingPage) page ).getAddGroupCancelBtn().click();
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

}
