/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : SchoolAdminUsersPageTest.java
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
import static org.testng.Assert.assertTrue;

import java.util.List;
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
 * All the School Admin tests related to Users page are defined here.
 */
public class SchoolAdminUsersPageTest extends UsersLandingBaseTest {
    private static String searchString;

    public SchoolAdminUsersPageTest() {
        log = Logger.getLogger( SchoolAdminUsersPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME );
        orgHelper.createOrganization( UsersConstants.USER_ORG_NAME, UsersConstants.USER_ORG_ID );
        loginPage.login( AdminUIConstants.SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 401, description = "Entry Criteria: A School from the Organizations drop down is selected and then -- Unassigned Students -- drop down value is selected from the filter list box, searched for a user with a keyword and then the 'Go' button is clicked. </br>Execution Criteria: "
            + "Verifies the search results table if it reverts to " + "show all users based on the filter list box selection -- Unassigned Students -- and the search users text field is cleared.  </br>Exit Criteria: User is in Users landing page." )
    public void checkUnassignedStudentsAndSearchTextIsCleared() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( UsersConstants.SCHOOL_NAME );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.UNASSIGNED_STUDENTS.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        List<String> firstNameList = ( (UsersBasePage) page ).getItemsFromTableColumn( 1 );
        ( (UsersBasePage) page ).enterSearchTextInSearchInput( UsersConstants.LASTNAME );
        ( (UsersBasePage) page ).clickUsersSearchBtn();
        ( (UsersBasePage) page ).clickUsersGoBtn();
        List<String> firstNameListAfterSearchAndGo = ( (UsersBasePage) page ).getItemsFromTableColumn( 1 );
        assertEquals( firstNameListAfterSearchAndGo, firstNameList, "Mismatch in the Unassigned Students List" );
        assertTrue( ( (UsersBasePage) page ).getDefaultSelectedItemInUsersTypeDropDown().equals( UsersTypeDropDown.UNASSIGNED_STUDENTS.getType() ), "Unassigned Students was not found" );
        searchString = ( (UsersBasePage) page ).getEnteredTextInSearchInput();
        assertTrue( "".equals( searchString ), UsersConstants.SEARCH_TEXT_IS_NOT_CLEARED );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 402, description = "Entry Criteria : A School organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- Unassigned Students --, the 'Go' button is clicked. </br> Execution Criteria :Verify "
            + "the presence of the 'Assign Students to a Teacher' button. </br> Exit Criteria : User is in Users landing page." )
    public void checkPresenceOfAssignStudentsToTeacherSchool() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( UsersConstants.SCHOOL_NAME );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.UNASSIGNED_STUDENTS.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        assertNotNull( ( (UsersBasePage) page ).getUsersAssignStudentsToTeacherButton(), "\"Assign Students To Teacher\" button was not found" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 403, description = "Entry Criteria : A School organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- Unassigned Students --, the 'Go' button is clicked. </br> Execution Criteria :Verify the text "
            + "of the 'Assign Students to a Teacher' button. </br> Exit Criteria : User is in Users landing page." )
    public void checkTextOfAssignStudentsToTeacherSchool() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( UsersConstants.SCHOOL_NAME );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.UNASSIGNED_STUDENTS.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        assertEquals( ( (UsersBasePage) page ).getUsersAssignStudentsToTeacherButton().getText(), appProperties.getProperty( "UsersAddStudentsToTeacher" ), "Mismatch in the \"Assign Students to a Teacher button\"" );
    }

    @Test ( priority = 404, description = "Entry Criteria : A School organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- Unassigned Students --, the 'Go' button is clicked. </br> Execution Criteria :Verify "
            + "if the 'Assign Students to a Teacher' button is in inactive state. </br> Exit Criteria : User is in Users landing page." )
    public void checkIfInactiveAssignStudentsToTeacherSchool() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( UsersConstants.SCHOOL_NAME );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.UNASSIGNED_STUDENTS.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        assertEquals( ( (UsersBasePage) page ).getElementAttribute( ( (UsersBasePage) page ).getUsersAssignStudentsToTeacherButton(), "class" ), LocatorConstants.ADD_USER_BTN_DISABLED, "\"Assign Students to a Teacher\" button is not in Disabled state" );
    }

    @Test ( priority = 405, description = "Entry Criteria : A School organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- Unassigned Students --, the 'Go' button is clicked. </br> Execution Criteria :Verify if "
            + "Tapping 'Assign Students to a Teacher' button shows a message box \"Select at least one user\" </br> Exit Criteria : User is in Users landing page." )
    public void checkIfAlertAppeasrsOnClickAssignStudentsToTeacherSchool() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( UsersConstants.SCHOOL_NAME );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.UNASSIGNED_STUDENTS.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        ( (UsersBasePage) page ).clickUsersAssignStudentsToTeacherButton();
        //TODO Page is under construction. Needs to be modified once the actual implementation is complete.

    }

    @Test ( priority = 406, description = "Entry Criteria : A School organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- Unassigned Students --, the 'Go' button is clicked. </br> Execution Criteria :Verify if "
            + "Tapping 'Assign Students to a Teacher' button shows a message box \"Select at least one user\" </br> Exit Criteria : User is in Assign Students to teacher window" )
    public void checkNavigationToAssignStudentsToTeacherSchool() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( UsersConstants.SCHOOL_NAME );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.UNASSIGNED_STUDENTS.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        ( (UsersBasePage) page ).selectUserInUsersTable();
        ( (UsersBasePage) page ).clickUsersAssignStudentsToTeacherButton();
        //TODO Page is under construction. Needs to be modified once the actual implementation is complete.

    }

    @Test ( priority = 407, dataProvider = "searchAndSortDataProvider", description = "Entry Criteria: Search is done For User Group 'Unassigned Students'.</br>Execution Criteria: FirstName, LastName, UserId is verified with sorting order through LastName.</br>"
            + "Exit Criteria: User still is in Users landing page." )
    public void checkForSearchUsersForUnassignedStudentsAndSortByColumn( String searchInput, UsersTypeDropDown userType, boolean sortType, String sortingColumn, String errorMessage ) {
        page.clickHomeTabBtn();
        ( (UsersBasePage) page ).selectItemFromDropDown( ( (UsersBasePage) page ).getOrganizationDropDown(), UsersConstants.SCHOOL_NAME );
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( userType.getType() );
        ( (UsersBasePage) page ).enterSearchTextInSearchInput( searchInput );
        ( (UsersBasePage) page ).clickUsersSearchBtn();
        assertTrue(
                ( (UsersBasePage) page ).checkForStringInColumnOfUsersGroupTable( 1, searchInput ) || ( (UsersBasePage) page ).checkForStringInColumnOfUsersGroupTable( 2, searchInput )
                        || ( (UsersBasePage) page ).checkForStringInColumnOfUsersGroupTable( 5, searchInput ), UsersConstants.SEARCH_DID_NOT_MATCH_ANYCOLUMN );
        assertEquals( ( (UsersBasePage) page ).getSortedValuesOfTableColumn( sortingColumn, sortType ), 1, errorMessage );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @DataProvider ( name = "searchAndSortDataProvider" )
    public Object[][] searchAndSortDataProvider() {
        Object[][] input = { { UsersConstants.FIRSTNAME, UsersTypeDropDown.UNASSIGNED_STUDENTS, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.LASTNAME, UsersTypeDropDown.UNASSIGNED_STUDENTS, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.USER_ID, UsersTypeDropDown.UNASSIGNED_STUDENTS, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED } };

        return input;
    }

    @Test ( priority = 408, description = "Entry Criteria : Click on the Add Users button. </br> Execution Criteria: Verify if an alert with proper text appears and dismiss the alert. </br> Exit Criteria: User is in Users landing page. " )
    public void clickOnAddUserBtn() {
        Map<String, String> actualPageDetails = ( (UsersBasePage) page ).clickUsersAddUserBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "AddUsersPageUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
        ( (UsersBasePage) page ).getCancelButtonUsersPage().click();
        //TODO Implementation is not complete. Will be handled once the implementation is complete

    }

    @Test ( priority = 409, description = "Entry Criteria: User is in users landing page.</br>Execution Criteria: Logout button is clicked and navigation is verified from groups landing page.</br>Exit Criteria: User is in Login page." )
    public void checkLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );

    }

}