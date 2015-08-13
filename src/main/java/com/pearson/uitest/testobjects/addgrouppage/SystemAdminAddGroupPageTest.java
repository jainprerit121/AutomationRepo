/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : SystemAdminAddGroupPageTest.java
 * 
 * Description : TestObject for add group page UI functionalities for school
 * admin.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/
package com.pearson.uitest.testobjects.addgrouppage;

import static org.testng.Assert.assertEquals;

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
import com.pearson.uitest.pageobjects.GroupDetailsPage;
import com.pearson.uitest.pageobjects.ManageGroupPage;

/**
 * This class tests the UI functionalities of Add Group page after logging in as
 * System Admin.
 */
public class SystemAdminAddGroupPageTest extends GroupDetailsBaseTest {
    private String districtName;

    public SystemAdminAddGroupPageTest() {
        log = Logger.getLogger( SystemAdminAddGroupPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME );
        orgHelper.deleteOrganization( GroupsConstants.GROUP_LESS_SCHOOL_NAME );
        Integer orgId1 = orgHelper.createOrganization( UsersConstants.USER_ORG_NAME, UsersConstants.USER_ORG_ID );
        Integer orgId2 = orgHelper.createOrganization( GroupsConstants.GROUP_LESS_SCHOOL_NAME, GroupsConstants.GROUP_LESS_SCHOOL_ID );
        userHelper.createTeacher( AdminUIConstants.TEACHER_FIRSTNAME, AdminUIConstants.TEACHER_LASTNAME, AdminUIConstants.TEACHER_USERNAME, AdminUIConstants.DEFAULT_PASSWORD, orgId1 );
        userHelper.createTeacher( GroupsConstants.TEACHER_FIRSTNAME_SAME_ORG, GroupsConstants.TEACHER_LASTNAME_SAME_ORG, GroupsConstants.TEACHER_USERNAME_SAME_ORG, AdminUIConstants.DEFAULT_PASSWORD, orgId1 );
        userHelper.createTeacher( GroupsConstants.TEACHER_FIRSTNAME_DIFF_ORG, GroupsConstants.TEACHER_LASTNAME_DIFF_ORG, GroupsConstants.TEACHER_USERNAME_DIFF_ORG, AdminUIConstants.DEFAULT_PASSWORD, orgId2 );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 96, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Default value in name input is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkNameInputDefaultValue() {
        Assert.assertEquals( ( (GroupDetailsPage) page ).getElementAttribute( ( (GroupDetailsPage) page ).getGroupNameInput(), "value" ), "", "Mismatch in the default value!!" );
    }

    @Test ( priority = 97, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Default value in owner dropdown is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkOwnerDropDownDefaultValue() {
        Assert.assertEquals( ( (GroupDetailsPage) page ).getDefaultSelectedInGroupOwnerDropDown(), appProperties.getProperty( "AddGroupDefaultDropValue" ), "Mismatch in default dropdown value!!" );
    }

    @Test ( priority = 98, description = "Entry Criteria: User is in Add Group page</br>Execution Criteria: Help page is launched in new page and verifed.</br>Exit Criteria: User is in Add Group page." )
    public void checkHelp() {
        Map<String, String> actualPageDetails = page.launchHelp();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "HelpTitle" ), appProperties.getProperty( "AddGroupHelp" ) );
    }

    @Test ( priority = 114, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Sorting of owner list is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkOwnerListSortedByLastName() {
        districtName = orgHelper.getAllDistrictsList().get( 0 );
        page.clickHomeTabBtn();
        ( (GroupDetailsPage) page ).selectItemFromDropDown( ( (GroupDetailsPage) page ).getOrganizationDropDown(), districtName );
        page.clickUsrTabBtn();
        ( (GroupDetailsPage) page ).clickUsersAddGroupBtn();
        Assert.assertTrue( ( (ManageGroupPage) page ).checkSortingOfOwnersListByLastName( ( (ManageGroupPage) page ).getGroupOwnerDropDown() ), "Owner list is not sorted alphabetically by lastname!!" );
    }

    @Test ( priority = 301, description = "Entry Criteria: User is in 'Add Group' page.</br>Execution Criteria: Name, Owner and Description Details are saved.</br>Exit Criteria: User is in 'Group Added To' page." )
    public void verifyGroupAddedForDifferentOwnerOfDifferentOrganization() {
        page.clickHomeTabBtn();
        ( (GroupDetailsPage) page ).selectItemFromDropDown( ( (GroupDetailsPage) page ).getOrganizationDropDown(), GroupsConstants.GROUP_LESS_SCHOOL_NAME );
        page.clickUsrTabBtn();
        ( (GroupDetailsPage) page ).clickUsersAddGroupBtn();
        ( (GroupDetailsPage) page ).getGroupNameInput().sendKeys( GroupsConstants.ADD_GROUP_NAME );
        ( (GroupDetailsPage) page ).selectAnItemFromGroupDropDown( GroupsConstants.TEACHER_FIRSTNAME_DIFF_ORG + " " + GroupsConstants.TEACHER_LASTNAME_DIFF_ORG );
        ( (GroupDetailsPage) page ).getGroupDescriptionInput().sendKeys( GroupsConstants.ADD_GROUP_DESCRIPTION );
        ( (GroupDetailsPage) page ).clickGroupSaveBtn();
        String welcomeText = appProperties.getProperty( "GroupAddedToWelcomeText" );
        welcomeText = welcomeText.replaceFirst( "Dynamic", GroupsConstants.TEACHER_LASTNAME_DIFF_ORG + ", " + GroupsConstants.TEACHER_FIRSTNAME_DIFF_ORG );
        log.info( "Data: " + ( (GroupDetailsPage) page ).getGroupDetailsWelcomeText() + "\n" );
        log.info( "Welcome" + welcomeText );
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsWelcomeText(), welcomeText, "Mismatch in the Welcome message." );
    }

    @Test ( priority = 401, description = "Entry Criteria: User is in add group page.</br>Execution Criteria: Logout button is clicked and navigation is verified from groups landing page.</br>Exit Criteria: User is in Login page." )
    public void checkLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }
}
