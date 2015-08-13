/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : SystemAdminGroupsLandingPageTest.java
 * 
 * Description : TestObject for groups Landing page UI functionalities for
 * school admin.
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

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.GroupsConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.enums.UsersTypeDropDown;
import com.pearson.uitest.pageobjects.GroupsLandingPage;
import com.pearson.uitest.pageobjects.UsersBasePage;

/**
 * Groups landing page UI functionalities will be tested using this class.
 */
public class SchoolAdminGroupsLandingPageTest extends GroupsBaseTest {
    public SchoolAdminGroupsLandingPageTest() {
        log = Logger.getLogger( SchoolAdminGroupsLandingPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME );
        userHelper.deleteAdmin( UsersConstants.USER_SCHOOL_ADMIN );
        orgHelper.deleteOrganization( GroupsConstants.GROUP_LESS_SCHOOL_NAME );
        userHelper.deleteAdmin( GroupsConstants.GROUP_SCHOOL_ADMIN_USERNAME );
        Integer orgId1 = orgHelper.createOrganization( UsersConstants.USER_ORG_NAME, UsersConstants.USER_ORG_ID );
        Integer orgId2 = orgHelper.createOrganization( GroupsConstants.GROUP_LESS_SCHOOL_NAME, GroupsConstants.GROUP_LESS_SCHOOL_ID );
        Integer teacherId = userHelper.createTeacher( AdminUIConstants.TEACHER_FIRSTNAME, AdminUIConstants.TEACHER_LASTNAME, AdminUIConstants.TEACHER_USERNAME, AdminUIConstants.DEFAULT_PASSWORD, orgId1 );
        userHelper.createGroup( GroupsConstants.GROUP_NAME, GroupsConstants.GROUP_TYPE_ID, teacherId, GroupsConstants.GROUP_DESCRIPTION );
        userHelper.createGroup( GroupsConstants.GROUP_NAME_SECOND, GroupsConstants.GROUP_TYPE_ID, teacherId, GroupsConstants.GROUP_DESCRIPTION_SECOND );
        userHelper.createSchoolAdmin( GroupsConstants.GROUP_SCHOOL_ADMIN_FIRSTNAME, GroupsConstants.GROUP_SCHOOL_ADMIN_LASTNAME, GroupsConstants.GROUP_SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_ENCRYPTED_PASSWORD, orgId2 );
        userHelper.createSchoolAdmin( AdminUIConstants.SCHOOL_ADMIN_FIRSTNAME, AdminUIConstants.SCHOOL_ADMIN_LASTNAME, UsersConstants.USER_SCHOOL_ADMIN, AdminUIConstants.DEFAULT_ENCRYPTED_PASSWORD, orgId1 );
        loginPage.login( UsersConstants.USER_SCHOOL_ADMIN, AdminUIConstants.DEFAULT_PASSWORD );

    }

    @Test ( priority = 401, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: Welcome message is verified in groups landing page. </br>Exit Criteria: User is in groups landing page." )
    public void checkWelcomeWithDefaultTypeAndGradeForSchool() {
        ( (GroupsLandingPage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_GROUPS.getType() );
        ( (GroupsLandingPage) page ).clickUsersGoBtn();
        String welcomeText = appProperties.getProperty( "GroupWelcomeText" );
        welcomeText = welcomeText.replaceFirst( "\"Dynamic\"", UsersConstants.STRING_DELIM + UsersConstants.USER_ORG_NAME + UsersConstants.STRING_DELIM );
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersWelcomeText(), welcomeText, "Expected Welcome message is not displayed!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 402, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: Add group button and navigation is verified.</br>Exit Criteria: User is in add group page." )
    public void clickOnAddGroupBtn() {
        Map<String, String> actualPageDetails = ( (GroupsLandingPage) page ).clickUsersAddGroupBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "UserAddGroupUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 403, description = "Entry Criteria: User is in add group page.</br>Execution Criteria: Logout button is clicked and navigation is verified from groups landing page.</br>Exit Criteria: User is in Login page." )
    public void checkLogout() {
        ( (GroupsLandingPage) page ).getAddGroupCancelBtn().click();
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 404, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: No result text is verified after searching for groups for school without any groups.</br>Exit Criteria: User is in groups landing page." )
    public void checkNoRecordsFoundForSchoolWithoutAnyGroup() {
        loginPage.login( GroupsConstants.GROUP_SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        page.clickUsrTabBtn();
        ( (GroupsLandingPage) page ).clickUsersGoBtn();
        Assert.assertEquals( ( (GroupsLandingPage) page ).getGroupsNoResultText(), appProperties.getProperty( "UserGroupNoResult" ), "No result text is not present!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 406, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: 0 groups found text is verified after searching for groups for school without any groups.</br>Exit Criteria: User is in groups landing page." )
    public void checkNoGroupsFoundWhenNoGroupsArePresentInGroupTable() {
        Assert.assertEquals( ( (GroupsLandingPage) page ).getGroupsNoOfGroupsLabel(), appProperties.getProperty( "UsersNoGroupsLabel" ), "Mismatch is observed in the No Groups found label!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

}
