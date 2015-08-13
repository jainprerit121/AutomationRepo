/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : SystemDistrictAdminGroupsBaseTest.java
 * 
 * Description : TestObject for groups Landing page UI functionalities.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/
package com.pearson.uitest.testobjects.grouppage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.uitest.constants.GroupsConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.enums.UsersTypeDropDown;
import com.pearson.uitest.pageobjects.GroupsLandingPage;
import com.pearson.uitest.pageobjects.UsersBasePage;

/**
 * Groups landing page UI common functionalities will be tested using this class
 * for System and District admins.
 */
public abstract class SystemDistrictAdminGroupsBaseTest extends GroupsBaseTest {
    private String districtName;

    protected void initialize( String browser ) {
        super.initialize( browser );
        page = new GroupsLandingPage( driver );
    };

    @Test ( priority = 301, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: Welcome message is verified in groups landing page. </br>Exit Criteria: User is in groups landing page." )
    public void checkWelcomeWithDefaultTypeAndGradeForDistrict() {
        districtName = orgHelper.getAllDistrictsList().get( 0 );
        ( (GroupsLandingPage) page ).selectItemFromOrgDropDown( districtName );
        ( (GroupsLandingPage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_GROUPS.getType() );
        ( (GroupsLandingPage) page ).clickUsersGoBtn();
        String welcomeText = appProperties.getProperty( "GroupWelcomeText" );
        welcomeText = welcomeText.replaceFirst( "\"Dynamic\"", UsersConstants.STRING_DELIM + districtName + UsersConstants.STRING_DELIM );
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersWelcomeText(), welcomeText, "Expected Welcome message is not displayed!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 302, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: No result text is verified after searching for groups for school without any groups.</br>Exit Criteria: User is in groups landing page." )
    public void checkNoRecordsFoundInGroupTable() {
        Assert.assertEquals( ( (GroupsLandingPage) page ).getGroupsNoResultText(), appProperties.getProperty( "UserGroupNoResult" ), "No result text is not present!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 303, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: 0 groups found text is verified after searching for groups for school without any groups.</br>Exit Criteria: User is in groups landing page." )
    public void checkNoGroupsFoundWhenNoGroupsArePresentInGroupTable() {
        Assert.assertEquals( ( (GroupsLandingPage) page ).getGroupsNoOfGroupsLabel(), appProperties.getProperty( "UsersNoGroupsLabel" ), "Mismatch is observed in the No Groups found label!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 304, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: No result text is verified after searching for groups for school without any groups.</br>Exit Criteria: User is in groups landing page." )
    public void checkNoRecordsFoundForSchoolWithoutAnyGroup() {
        ( (GroupsLandingPage) page ).selectItemFromOrgDropDown( GroupsConstants.GROUP_LESS_SCHOOL_NAME );
        ( (GroupsLandingPage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_GROUPS.getType() );
        ( (GroupsLandingPage) page ).clickUsersGoBtn();
        Assert.assertEquals( ( (GroupsLandingPage) page ).getGroupsNoResultText(), appProperties.getProperty( "UserGroupNoResult" ), "No result text is not present!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 305, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: 0 groups found text is verified after searching for groups for school without any groups.</br>Exit Criteria: User is in groups landing page." )
    public void checkNoGroupsFoundWhenNoGroupsArePresentForSchool() {
        Assert.assertEquals( ( (GroupsLandingPage) page ).getGroupsNoOfGroupsLabel(), appProperties.getProperty( "UsersNoGroupsLabel" ), "Mismatch is observed in the No Groups found label!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

}
