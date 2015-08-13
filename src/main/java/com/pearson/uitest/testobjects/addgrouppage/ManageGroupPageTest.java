/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : ManageGroupPageTest.java
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

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.uitest.constants.GroupsConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.pageobjects.GroupDetailsPage;
import com.pearson.uitest.pageobjects.ManageGroupPage;
import com.pearson.uitest.testobjects.AdminBaseTest;

/**
 * This class tests the UI functionalities of Add Group page
 */
public class ManageGroupPageTest extends AdminBaseTest {

    protected void initialize( String browser ) {
        super.initialize( browser );
        page = new ManageGroupPage( driver );
    }

    @Test ( priority = 95, description = "Entry Criteria: User is in Users page.</br>Execution Criteria: Navigation to Add Org page is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkUserNavigationToAddGroupPage() {
        page.clickUsrTabBtn();
        Map<String, String> actualPageDetails = ( (ManageGroupPage) page ).clickUsersAddGroupBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( ADMIN_PAGE_TITLE ), appProperties.getProperty( "UserAddGroupUrl" ) );
    }

    @Test ( priority = 102, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Required field text is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkRequiredFieldLabel() {
        Assert.assertEquals( ( (ManageGroupPage) page ).getGroupRequiredFieldText(), appProperties.getProperty( "ManageGrpReqFieldLabel" ), "Mismatch is observed in Required field text" );
    }

    @Test ( priority = 102, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: group name label is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkNameLabel() {
        Assert.assertEquals( ( (ManageGroupPage) page ).getGroupNameLabel(), appProperties.getProperty( "ManageGrpNameLabel" ), "Mismatch is observed in name label" );
    }

    @Test ( priority = 103, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: presence of name input is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkPresenceOfNameInput() {
        Assert.assertNotNull( ( (ManageGroupPage) page ).getGroupNameInput(), "Group name input is missing!!" );
    }

    @Test ( priority = 104, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Group owner label is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkGroupOwnerLabel() {
        Assert.assertEquals( ( (ManageGroupPage) page ).getGroupOwnerLabel(), appProperties.getProperty( "ManageGrpOwnerLabel" ), "Mismatch in group owner label!!" );
    }

    @Test ( priority = 105, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Presence of group owner dropdown is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkPresenceOfGroupOwnerDropDOwn() {
        Assert.assertNotNull( ( (ManageGroupPage) page ).getGroupOwnerDropDown(), "Group Owner dropdown is missing!!" );
    }

    @Test ( priority = 106, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Description label is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkDescriptionLabel() {
        Assert.assertEquals( ( (ManageGroupPage) page ).getGroupDescriptionLabel(), appProperties.getProperty( "ManagegrpDescLabel" ), "Mismatch ingroup description label" );
    }

    @Test ( priority = 107, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Description input field presence is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkPresenceOfDescriptionInput() {
        Assert.assertNotNull( ( (ManageGroupPage) page ).getGroupDescriptionInput(), "Description input is missing!!" );
    }

    @Test ( priority = 108, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Presence of Save button is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkPresenceOfSaveBtn() {
        Assert.assertNotNull( ( (ManageGroupPage) page ).getGroupSaveBtn(), "Save button is missing!!" );
    }

    @Test ( priority = 109, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Save button is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkSaveBtnText() {
        Assert.assertEquals( ( (ManageGroupPage) page ).getGroupSaveBtn().getText(), appProperties.getProperty( "saveBtnText" ), "Mismatch is observed in Save button text!!" );
    }

    @Test ( priority = 110, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Presence of Cancel button is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkPresenceOfCancelBtn() {
        Assert.assertNotNull( ( (ManageGroupPage) page ).getGroupCancelBtn(), "Cancel button is missing!!" );
    }

    @Test ( priority = 111, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Cancel button text is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkCancelBtnText() {
        Assert.assertEquals( ( (ManageGroupPage) page ).getGroupCancelBtn().getText(), appProperties.getProperty( "CancelBtnText" ), "Mismatch in Cancel button text!!" );
    }

    @Test ( priority = 112, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Allowed characters in name input is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkAllowedCharactersInNameInput() {
        ( (ManageGroupPage) page ).getGroupNameInput().sendKeys( UsersConstants.USER_ALPHA_NUM_SPECIAL_CHAR_SEARCH );
        Assert.assertEquals( ( (ManageGroupPage) page ).getElementAttribute( ( (ManageGroupPage) page ).getGroupNameInput(), "value" ), UsersConstants.USER_ALPHA_NUM_SPECIAL_CHAR_SEARCH,
                "Retrieved value from Search Input doesn't match with actual value entered." );
    }

    @Test ( priority = 113, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Name input character limit is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkCharacterLimitInNameInput() {
        ( (ManageGroupPage) page ).getGroupNameInput().sendKeys( GroupsConstants.GROUP_NAME_INPUT_EXCEEDING_35_CHAR );
        Assert.assertEquals( ( (ManageGroupPage) page ).getElementAttribute( ( (ManageGroupPage) page ).getGroupNameInput(), "value" ).length(), 35, "Mismatch in the group name length!!" );
    }

    @Test ( priority = 114, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Sorting of owner list is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkOwnerListSortedByLastName() {
        page.clickHomeTabBtn();
        ( (GroupDetailsPage) page ).selectItemFromDropDown( ( (GroupDetailsPage) page ).getOrganizationDropDown(), UsersConstants.USER_ORG_NAME );
        page.clickUsrTabBtn();
        ( (GroupDetailsPage) page ).clickUsersAddGroupBtn();
        Assert.assertTrue( ( (ManageGroupPage) page ).checkSortingOfOwnersListByLastName( ( (ManageGroupPage) page ).getGroupOwnerDropDown() ), "Owner list is not sorted alphabetically by lastname!!" );
    }

}
