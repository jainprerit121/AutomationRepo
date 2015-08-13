/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : SystemAdminUsersPageTest.java
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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.LocatorConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.enums.UsersTypeDropDown;
import com.pearson.uitest.pageobjects.UsersBasePage;

/**
 * All the System Admin tests related to Users page are defined here.
 */
public class SystemAdminUsersPageTest extends SystemDistrictAdminUsersPageBaseTest {

    public SystemAdminUsersPageTest() {
        log = Logger.getLogger( SystemAdminUsersPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME );
        orgHelper.createOrganization( UsersConstants.USER_ORG_NAME, UsersConstants.USER_ORG_ID );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 401, description = "Entry Criteria : A district organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- All Groups --, the 'Go' button is clicked. </br> Execution Criteria : Verify the "
            + "presence of the 'Remove' button on the left side of the screen, on the same bar as the 'Transfer Students' button. </br> Exit Criteria : User is in Users landing page." )
    public void checkPresenceOfRemoveButton() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).clickUsersGoBtn();
        assertNotNull( ( (UsersBasePage) page ).getUsersRemoveBtn(), "Remove button is not present" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 402, dataProvider = "getResultCountInFooterText", description = "Entry Criteria : A district organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  selecting a User from "
            + "the drop down, the 'Go' button is clicked. </br> Execution Criteria : Verify the results label <# of system or district administrators associated to the district org> Users Found</br> Exit Criteria : User is in Users landing page." )
    public void checkResultsRowsCountInFooterLabelTextAllUsersAndAdmins( UsersTypeDropDown userType, String errorMessage, String verificationSuccessMsg ) {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();

        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( userType.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        assertEquals( ( (UsersBasePage) page ).getRowsCountFromFooter(), ( (UsersBasePage) page ).getRowsCountFromUsersTable(), errorMessage );
        log.info( verificationSuccessMsg );
    }

    @DataProvider ( name = "getResultCountInFooterText" )
    public Object[][] getResultCountInFooterText() {
        Object[][] inputArgs = { { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.FOOTER_RESULT_COUNT_MISMATCH, UsersConstants.VERIFICATION_SUCCESS },
                { UsersTypeDropDown.ALL_ADMIN, UsersConstants.FOOTER_RESULT_COUNT_MISMATCH, UsersConstants.VERIFICATION_SUCCESS } };

        return inputArgs;
    }

    @Test ( priority = 404, dataProvider = "forNoRecordsFoundDistrict", description = "Entry Criteria : A district organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and selecting a User from the Users drop down, "
            + "the 'Go' button is clicked. </br> Execution Criteria : Verify the results label <# of system or district administrators associated to the district org> Users Found</br> Exit Criteria : User is in Users landing page." )
    public void checkNoRecordsFoundForDistrict( UsersTypeDropDown userType, String errorMessage, String verificationSuccessMsg ) {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();

        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_TEACHER.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        assertEquals( ( (UsersBasePage) page ).getRowsCountFromFooter(), ( (UsersBasePage) page ).getRowsCountFromUsersTable(), errorMessage );
        log.info( verificationSuccessMsg );
    }

    @DataProvider ( name = "forNoRecordsFoundDistrict" )
    public Object[][] forNoRecordsFoundDistrict() {

        Object[][] inputArgs = { { UsersTypeDropDown.ALL_TEACHER, UsersConstants.NO_RECORDS_TEXT_NOT_FOUND, UsersConstants.VERIFICATION_SUCCESS },
                { UsersTypeDropDown.ALL_ACTIVE_STUDENTS, UsersConstants.NO_RECORDS_TEXT_NOT_FOUND, UsersConstants.VERIFICATION_SUCCESS } };
        return inputArgs;
    }

    @Test ( priority = 405, description = "Entry Criteria : A district organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- All User Types --, the 'Go' button is clicked. </br> Execution Criteria : Verify the search "
            + "results table shows all users associated to the district organization </br> Exit Criteria : User is in Users landing page." )
    public void checkResultsRowsForAllUsersUnderDistrict() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();

        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_USER_TYPE.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        //assertEquals( ( (UsersBasePage) page ).getRowsCountFromFooter(), ( (UsersBasePage) page ).getRowsCountFromUsersTable(), "Count in Footer does not match" );
        // TODO : SQL for data validation of all users pertaining to District needed.
    }

    @Test ( priority = 407, dataProvider = "forZeroUsersFoundDistrict", description = "Entry Criteria : A district organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and a User is selected from the Users Drop Down, "
            + "then 'Go' button is clicked. </br> Execution Criteria : Verify the footer text \"0 Users Found\" appears at the bottom of the search results </br> Exit Criteria : User is in Users landing page." )
    public void checkZeroUsersFoundForDistrict( UsersTypeDropDown userType, String errorMessage ) {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();

        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_ACTIVE_STUDENTS.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        assertEquals( ( (UsersBasePage) page ).getNumOfUsersFoundFooterText(), appProperties.getProperty( "UsersZeroUsersFooterText" ), "Footer text does not match " );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @DataProvider ( name = "forZeroUsersFoundDistrict" )
    public Object[][] forZeroUsersFoundDistrict() {

        Object[][] inputArgs = { { UsersTypeDropDown.ALL_TEACHER, UsersConstants.NON_ZERO_FOOTER_TEXT }, { UsersTypeDropDown.ALL_ACTIVE_STUDENTS, UsersConstants.NON_ZERO_FOOTER_TEXT } };
        return inputArgs;
    }

    @Test ( priority = 409, description = "Entry Criteria :A district organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- All Active Students --, the 'Go' button is clicked. </br> Execution Criteria : Verify the "
            + "presence of the 'Add Users to Group' button to the left of the 'Remove' button. </br> Exit Criteria : User is in Users landing page.  " )
    public void checkPresenceOfAddUsersToGroupButtonForDistrictAllActiveStudents() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();

        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_ACTIVE_STUDENTS.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        assertNotNull( ( (UsersBasePage) page ).getUsersAddUserToGroupBtn(), "Add User To Group button was not found" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 410, description = "Entry Criteria :A district organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- All Active Students --, the 'Go' button is clicked. </br> Execution Criteria : Verify the "
            + "text of the 'Add Users to Group' button to the left of the 'Remove' button. </br> Exit Criteria : User is in Users landing page.  " )
    public void checkTextOfAddUsersToGroupButtonForDistrictAllActiveStudents() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_ACTIVE_STUDENTS.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        assertEquals( ( (UsersBasePage) page ).getUsersAddUserToGroupBtn().getText(), appProperties.getProperty( "UsersAddUserToGroupBtnText" ), "Mismatch in the \"Add Users to Group\" button Text. " );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 411, description = "Entry Criteria :A district organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- All Active Students --, the 'Go' button is clicked. </br> Execution Criteria : Verify "
            + "the 'Add Users to Group' button is in inactive state </br> Exit Criteria : User is in Users landing page.  " )
    public void checkIfDisabledAddUsersToGroupButtonForDistrictAllActiveStudents() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();

        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_ACTIVE_STUDENTS.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        assertEquals( ( (UsersBasePage) page ).getAttributeAddUserToGroupBtn(), LocatorConstants.ADD_USER_BTN_DISABLED, "Add User To Group button is not in Disabled state" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 412, description = "Entry Criteria : Click on the Add Users button. </br> Execution Criteria: Verify if an alert with proper text appears and dismiss the alert. </br> Exit Criteria: User is in Users landing page. " )
    public void clickOnAddUserBtn() {
        Map<String, String> actualPageDetails = ( (UsersBasePage) page ).clickUsersAddUserBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "AddUsersPageUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
        ( (UsersBasePage) page ).getCancelButtonUsersPage().click();

    }

    @Test ( priority = 413, description = "Entry Criteria: User is in users landing page.</br>Execution Criteria: Logout button is clicked and navigation is verified from groups landing page.</br>Exit Criteria: User is in Login page." )
    public void checkLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );

    }

}
