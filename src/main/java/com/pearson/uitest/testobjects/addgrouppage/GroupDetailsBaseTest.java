/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : GroupDetailsBaseTest.java
 * 
 * Description : TestObject for group details page UI functionalities for school
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

import org.testng.annotations.Test;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.GroupsConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.pageobjects.GroupDetailsPage;

/**
 * This class tests the UI functionalities of Group details page
 */
public class GroupDetailsBaseTest extends ManageGroupPageTest {

    protected void initialize( String browser ) {
        super.initialize( browser );
        page = new GroupDetailsPage( driver );
    }

    @Test ( priority = 201, description = "Entry Criteria: User is Users landing page.</br>Execution Criteria: Navigation to group details page is verified.</br>Exit Criteria: User is in group details landing page." )
    public void addGroup() {
        page.clickHomeTabBtn();
        ( (GroupDetailsPage) page ).selectItemFromDropDown( ( (GroupDetailsPage) page ).getOrganizationDropDown(), UsersConstants.USER_ORG_NAME );
        page.clickUsrTabBtn();
        ( (GroupDetailsPage) page ).clickUsersAddGroupBtn();
        ( (GroupDetailsPage) page ).getGroupNameInput().sendKeys( GroupsConstants.ADD_GROUP_NAME );
        ( (GroupDetailsPage) page ).selectAnItemFromGroupDropDown( AdminUIConstants.TEACHER_FIRSTNAME + " " + AdminUIConstants.TEACHER_LASTNAME );
        ( (GroupDetailsPage) page ).getGroupDescriptionInput().sendKeys( GroupsConstants.ADD_GROUP_DESCRIPTION );
        Map<String, String> actualLogoutPageDetails = ( (GroupDetailsPage) page ).clickGroupSaveBtn();
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "GroupDetailsUrl" ) );
    }

    @Test ( priority = 202, description = "Entry Criteria: User is in 'Group Add To' page.</br>Execution Criteria: Label of Name is verified.</br>Exit Criteria: User is in 'Group Add To' page." )
    public void verifyPresenceOfLabelForName() {
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsNameLabel(), appProperties.getProperty( "GroupNameLabel" ), "Mismatch in Name Label" );
    }

    @Test ( priority = 203, description = "Entry Criteria: User is in 'Group Add To' page.</br>Execution Criteria: Value of Name is verified.</br>Exit Criteria: User is in 'Group Add To' page." )
    public void verifyPresenceOfValueForName() {
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsNameText(), GroupsConstants.ADD_GROUP_NAME, "Mismatch in Name Value" );
    }

    @Test ( priority = 204, description = "Entry Criteria: User is in 'Group Add To' page.</br>Execution Criteria: Label of Owner is verified.</br>Exit Criteria: User is in 'Group Add To' page." )
    public void verifyPresenceOfLabelForOwner() {

        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsOwnerLabel(), appProperties.getProperty( "GroupOwnerLabel" ), "Mismatch in Owner Label" );
    }

    @Test ( priority = 205, description = "Entry Criteria: User is in 'Group Add To' page.</br>Execution Criteria: Value of Owner is verified.</br>Exit Criteria: User is in 'Group Add To' page." )
    public void verifyPresenceOfValueForOwner() {
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsOwnerText(), GroupsConstants.GROUP_TITLE + AdminUIConstants.TEACHER_LASTNAME + ", " + AdminUIConstants.TEACHER_FIRSTNAME, "Mismatch in Owner Value" );
    }

    @Test ( priority = 206, description = "Entry Criteria: User is in 'Group Add To' page.</br>Execution Criteria: Label of Description is verified.</br>Exit Criteria: User is in 'Group Add To' page." )
    public void verifyPresenceOfLabelForDescription() {
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsDescriptionLabel(), appProperties.getProperty( "GroupDescriptionLabel" ), "Mismatch in Description Label" );
    }

    @Test ( priority = 207, description = "Entry Criteria: User is in 'Group Add To' page.</br>Execution Criteria: Value of Description is verified.</br>Exit Criteria: User is in 'Group Add To' page." )
    public void verifyPresenceOfValueForDescription() {
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsDescriptionText(), GroupsConstants.ADD_GROUP_DESCRIPTION, "Mismatch in Description Value" );
    }

    @Test ( priority = 208, description = "Entry Criteria: User is in 'Group Add To' page.</br>Execution Criteria: Value of 'Edit Group' button is verified.</br>Exit Criteria: User is in 'Group Add To' page." )
    public void verifyEditGroupButtonPresent() {
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsEditGroupBtn().getText(), appProperties.getProperty( "GroupEditGroupButton" ), "Mismatch in Button Text Value" );
    }

    @Test ( priority = 209, description = "Entry Criteria: User is in 'Group Add To' page.</br>Execution Criteria: Value of 'Add User(s) to Group' button is verified.</br>Exit Criteria: User is in 'Group Add To' page." )
    public void verifyAddUsersToGroupButtonPresent() {
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsAddUsersToGroupBtn().getText(), appProperties.getProperty( "GroupAddUserToButton" ), "Mismatch in Button Text Value" );
    }

    @Test ( priority = 210, description = "Entry Criteria: User is in 'Group Add To' page.</br>Execution Criteria: Value of 'Add Another Group' button is verified.</br>Exit Criteria: User is in 'Group Add To' page." )
    public void verifyAddAnotherGroupButtonPresent() {
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsAddAnotherGroupBtn().getText(), appProperties.getProperty( "GroupAddAnotherGroup" ), "Mismatch in Button text value" );
    }

    @Test ( priority = 211, enabled = false, description = "Entry Criteria: User is in 'Group Add To' page.</br>Execution Criteria: Value of 'Exit Add Group' button is verified.</br>Exit Criteria: User is in 'Group Add To' page." )
    public void verifyExitAddGroupButtonPresent() {
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsExitAddGroupBtn().getText(), appProperties.getProperty( "GroupExitAddGroup" ), "Mismatch in Button text value" );
    }

    //TODO: Feature is not complete.
    //@Test ( priority = 212, description = "Entry Criteria: User is Users landing page.</br>Execution Criteria: Verify page after click on 'Add User(s) to Group'.</br>Exit Criteria: User is in Groups landing page." )
    public void verifyClickOnAddUserToGroupButton() {
        //String actualPageDetails = ( (GroupDetailsPage) page ).clickGroupDetailsAddUsersToGroupBtn();
        //assertEquals( "", "Add Another Group", "Mismatch in description value" );
    }

    //TODO: Feature is not complete.
    //@Test ( priority = 213, description = "Entry Criteria: User is Users landing page.</br>Execution Criteria: Verify page after click on 'Edit Group'.</br>Exit Criteria: User is in Groups landing page." )
    public void verifyClickOnEditGroupButton() {
        //Map<String, String> actualPageDetails = ( (GroupDetailsPage) page ).clickGroupDetailsExitAddGroupBtn();
        //assertEquals( ( (GroupDetailsPage) page ).clickGroupDetailsExitAddGroupBtn(), "Edit Add Group", "Mismatch in description value" );
    }

    @Test ( priority = 214, description = "Entry Criteria: User is Users landing page.</br>Execution Criteria: Verify page after click on 'Add Another Group'.</br>Exit Criteria: User is in Groups landing page." )
    public void verifyClickOnAddAnotherGroupButton() {
        Map<String, String> actualPageDetails = ( (GroupDetailsPage) page ).clickGroupDetailsAddAnotherGroupBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "UserAddGroupUrl" ) );
    }

    @Test ( priority = 215, description = "Entry Criteria: User is in 'Add Group' page.</br>Execution Criteria: Alert message is verified for duplicate entry.</br>Exit Criteria: User is in 'Group Added To' page." )
    public void verifyGroupAddedForSameOwnerOfSameOrganization() {
        ( (GroupDetailsPage) page ).getGroupNameInput().sendKeys( GroupsConstants.ADD_GROUP_NAME );
        ( (GroupDetailsPage) page ).selectAnItemFromGroupDropDown( AdminUIConstants.TEACHER_FIRSTNAME + " " + AdminUIConstants.TEACHER_LASTNAME );
        ( (GroupDetailsPage) page ).getGroupDescriptionInput().sendKeys( GroupsConstants.GROUP_DESCRIPTION );
        ( (GroupDetailsPage) page ).clickGroupSaveBtn();
        assertEquals( ( (GroupDetailsPage) page ).switchToAlert(), appProperties.getProperty( "AlertMessageForSameName" ), "Mismatch in the alert message." );
    }

    @Test ( priority = 217, description = "Entry Criteria: User is in alert box of 'Add Group' page.</br>Execution Criteria: Alert message is verified for special character.</br>Exit Criteria: User is in 'Add Group' page." )
    public void verifyNameContainsSpecialCharacters() {
        ( (GroupDetailsPage) page ).getGroupNameInput().clear();
        ( (GroupDetailsPage) page ).getGroupDescriptionInput().clear();
        ( (GroupDetailsPage) page ).getGroupNameInput().sendKeys( GroupsConstants.GROUP_NAME_SPECIAL );
        ( (GroupDetailsPage) page ).selectAnItemFromGroupDropDown( AdminUIConstants.TEACHER_FIRSTNAME + " " + AdminUIConstants.TEACHER_LASTNAME );
        ( (GroupDetailsPage) page ).getGroupDescriptionInput().sendKeys( GroupsConstants.GROUP_DESCRIPTION );
        ( (GroupDetailsPage) page ).clickGroupSaveBtn();
        assertEquals( ( (GroupDetailsPage) page ).switchToAlert(), appProperties.getProperty( "AlertMessageForSpecialCharacter" ), "Mismatch in the alert message." );
    }

    @Test ( priority = 218, description = "Entry Criteria: User is in 'Add Group' page.</br>Execution Criteria: Name, Owner and Description Details are saved successfully.</br>Exit Criteria: User is in 'Group Added To' page." )
    public void verifyGroupAddedForDifferentOwnerOfSameOrganization() {
        ( (GroupDetailsPage) page ).getGroupNameInput().clear();
        ( (GroupDetailsPage) page ).getGroupDescriptionInput().clear();

        ( (GroupDetailsPage) page ).getGroupNameInput().sendKeys( GroupsConstants.ADD_GROUP_NAME );
        ( (GroupDetailsPage) page ).selectAnItemFromGroupDropDown( GroupsConstants.TEACHER_FIRSTNAME_SAME_ORG + " " + GroupsConstants.TEACHER_LASTNAME_SAME_ORG );
        ( (GroupDetailsPage) page ).getGroupDescriptionInput().sendKeys( GroupsConstants.GROUP_DESCRIPTION );
        ( (GroupDetailsPage) page ).clickGroupSaveBtn();
        String welcomeText = appProperties.getProperty( "GroupAddedToWelcomeText" );
        welcomeText = welcomeText.replaceFirst( "Dynamic", GroupsConstants.TEACHER_LASTNAME_SAME_ORG + ", " + GroupsConstants.TEACHER_FIRSTNAME_SAME_ORG );
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsWelcomeText(), welcomeText, "Mismatch in the Welcome message." );
    }

    @Test ( priority = 219, description = "Entry Criteria: User is in 'Group Added To' page.</br>Execution Criteria: Name, Owner and Description Details are saved successfully.</br>Exit Criteria: User is in 'Group Added To' page." )
    public void verifyGroupAddedForSameNameOfCapsWithSameOwner() {
        ( (GroupDetailsPage) page ).clickGroupDetailsAddAnotherGroupBtn();
        ( (GroupDetailsPage) page ).getGroupNameInput().sendKeys( GroupsConstants.ADD_GROUP_NAME.toUpperCase() );
        ( (GroupDetailsPage) page ).selectAnItemFromGroupDropDown( AdminUIConstants.TEACHER_FIRSTNAME + " " + AdminUIConstants.TEACHER_LASTNAME );
        ( (GroupDetailsPage) page ).getGroupDescriptionInput().sendKeys( GroupsConstants.GROUP_DESCRIPTION );
        ( (GroupDetailsPage) page ).clickGroupSaveBtn();
        String welcomeText = appProperties.getProperty( "GroupAddedToWelcomeText" );
        welcomeText = welcomeText.replaceFirst( "Dynamic", AdminUIConstants.TEACHER_LASTNAME + ", " + AdminUIConstants.TEACHER_FIRSTNAME );
        assertEquals( ( (GroupDetailsPage) page ).getGroupDetailsWelcomeText(), welcomeText, "Mismatch in the Welcome message." );
    }

    @Test ( priority = 221, description = "Entry Criteria: User is in 'Group Added To' page.</br>Execution Criteria: Verify page after click on 'Exit Add Group'.</br>Exit Criteria: User is in Users landing page." )
    public void verifyClickOnExitAddGroupButton() {
        Map<String, String> actualPageDetails = ( (GroupDetailsPage) page ).clickGroupDetailsExitAddGroupBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "UsersPageUrl" ) );
    }

}
