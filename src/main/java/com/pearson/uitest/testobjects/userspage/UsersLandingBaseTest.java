/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : UsersBaseTest.java
 * 
 * Description : TestObject for Users Landing page UI functionalities.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
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

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.google.common.collect.Lists;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.enums.UsersTypeDropDown;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.UsersBasePage;
import com.pearson.uitest.testobjects.AdminBaseTest;

/**
 * Users landing page UI functionalities will be tested using this class.
 */
public class UsersLandingBaseTest extends AdminBaseTest {
    private String searchString;

    @BeforeClass
    @Parameters ( "browser" )
    protected void initialize( @Optional ( "Firefox-Win7" ) String browser ) {
        super.initialize( browser );
        page = new UsersBasePage( driver );
        log = Logger.getLogger( UsersLandingBaseTest.class );
    };

    @Test ( priority = 101, dataProvider = "searchAndSortDataProvider", description = "Entry Criteria: Search is done For Selected User Groups .</br>Execution Criteria: FirstName, LastName, UserId is verified with sorting order through LastName.</br>"
            + "Exit Criteria: User still is in Users landing page." )
    public void checkForSearchUsersForAllActiveStudentsAndSortByColumn( String searchInput, UsersTypeDropDown userType, boolean sortType, String sortingColumn, String errorMessage ) {
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
        Object[][] input = { { UsersConstants.FIRSTNAME, UsersTypeDropDown.ALL_ACTIVE_STUDENTS, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.LASTNAME, UsersTypeDropDown.ALL_ACTIVE_STUDENTS, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.USER_ID, UsersTypeDropDown.ALL_ACTIVE_STUDENTS, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED } };

        return input;
    }

    @Test ( priority = 104, dataProvider = "searchForEmptyTextInSearch", description = "Entry Criteria:Drop down value is selected from the filter list box, searched for a user with a keyword and then the 'Go' button is clicked. </br>Execution Criteria: Verifies the "
            + "search results table if it reverts to show all users based on the filter list box selection -- All Administrators -- and the search users text field is cleared.  </br>Exit Criteria: User is in Users landing page." )
    public void checkForSearchTextIsCleared( String searchInput, UsersTypeDropDown userType, String errorMessage ) {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( userType.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        List<String> firstNameList = ( (UsersBasePage) page ).getItemsFromTableColumn( 1 );
        ( (UsersBasePage) page ).enterSearchTextInSearchInput( searchInput );
        ( (UsersBasePage) page ).clickUsersSearchBtn();
        ( (UsersBasePage) page ).clickUsersGoBtn();
        List<String> firstNameListAfterSearchAndGo = ( (UsersBasePage) page ).getItemsFromTableColumn( 1 );
        assertEquals( firstNameListAfterSearchAndGo, firstNameList, "Mismatch in the All Administrators List" );
        assertTrue( ( (UsersBasePage) page ).getDefaultSelectedItemInUsersTypeDropDown().equals( userType.getType() ), "Mismatch in default value on the drop down." );
        searchString = ( (UsersBasePage) page ).getEnteredTextInSearchInput();
        assertTrue( "".equals( searchString ), errorMessage );
        log.info( UsersConstants.VERIFICATION_SUCCESS );

    }

    @DataProvider ( name = "searchForEmptyTextInSearch" )
    public Object[][] searchForEmptyTextInSearch() {
        Object[][] input = { { UsersConstants.LASTNAME, UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.SEARCH_TEXT_IS_NOT_CLEARED }, { UsersConstants.LASTNAME, UsersTypeDropDown.ALL_ACTIVE_STUDENTS, UsersConstants.SEARCH_TEXT_IS_NOT_CLEARED },
                { UsersConstants.LASTNAME, UsersTypeDropDown.ALL_TEACHER, UsersConstants.SEARCH_TEXT_IS_NOT_CLEARED }, { UsersConstants.LASTNAME, UsersTypeDropDown.ALL_ADMIN, UsersConstants.SEARCH_TEXT_IS_NOT_CLEARED } };

        return input;
    }

    @Test ( priority = 105, description = "Entry Criteria: School organization from the Display list box is selected, leaving the Grades list box at -- All Grades --  and  the Filter list box at any value other than -- All User Types --, the 'Go' button is clicked. </br>"
            + "Execution Criteria:Verify the presence of the search results table with the following column titles: <Check box>, First Name, Last Name, Middle Name, Username, ID, Type and Organization. </br>Exit Criteria: User is in Users landing page." )
    public void checkPresenceOfColumnHeaders() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( UsersConstants.SCHOOL_NAME );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_ADMIN.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        CommonHelper.nap();
        assertNotNull( ( (UsersBasePage) page ).getSelectAllCheckButton(), "Select All Check Button is not present" );
        List<String> actualColumnHeaders = ( (UsersBasePage) page ).getColumnHeaders();
        String[] expColHeaderStr = appProperties.getProperty( "UsersLandColHeaders" ).split( "," );
        List<String> expectedColHeader = Lists.newArrayList( expColHeaderStr );
        assertEquals( actualColumnHeaders, expectedColHeader, "Mismatch in Column Headers" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 106, description = "Entry Criteria : A School organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- All Active Students --, the 'Go' button is clicked. </br> Execution Criteria :Verify if "
            + "Tapping 'Assign Students to a Teacher' button shows a message box \"Select at least one user\" </br> Exit Criteria : User is in Add Users to Group window" )
    public void checkNavigationToSelectGroupstoAddtoUsersPageSchool() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( UsersConstants.SCHOOL_NAME );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_ACTIVE_STUDENTS.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        ( (UsersBasePage) page ).selectUserInUsersTable();
        ( (UsersBasePage) page ).clickAddUserToGroupBtn();
        //TODO Page is under construction. Needs to be modified once the actual implementation is complete.
    }

    @Test ( priority = 107, description = "Entry Criteria : A School organization is selected from the Display list box, leaving the Grades and Filter list box at -- All Grades --  and -- All Active Students --, the 'Go' button is clicked. </br> Execution Criteria :Verify clicking on "
            + "the Last Name column head will reverse the sort order.  </br> Exit Criteria : User is in Users Landing page" )
    public void checkIfSortedByLastNameSearchAllUserTypeSchool() {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( UsersConstants.SCHOOL_NAME );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_ACTIVE_STUDENTS.getType() );
        ( (UsersBasePage) page ).enterSearchTextInSearchInput( UsersConstants.LASTNAME );
        ( (UsersBasePage) page ).clickUsersSearchBtn();
        assertEquals( ( (UsersBasePage) page ).getSortedValuesOfTableColumn( UsersConstants.COLUMN2, true ), 1, "Sorting through Type in descending order is Failed" );
    }

}
