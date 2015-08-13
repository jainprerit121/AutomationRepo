/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : SystemDistrictAdminUsersPageBaseTest.java
 * 
 * Description : TestObject for System Admin, District Admin for Users UI.
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
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.enums.UsersTypeDropDown;
import com.pearson.uitest.pageobjects.UsersBasePage;

/**
 * Reused code for System Admin and District Admin in Users landing page UI
 * functionalities will be tested using this class.
 */
public abstract class SystemDistrictAdminUsersPageBaseTest extends UsersLandingBaseTest {
    private String districtName;

    @Test ( priority = 201, description = "Entry Criteria: User is Users landing page.</br>Execution Criteria: Welcome text is verified in Users landing page.</br>Exit Criteria: User is in Users landing page." )
    public void checkWelcomeMessageAfterSelectingOrgInHomeAndNavigatingToUsersLandingPage() {
        districtName = orgHelper.getAllDistrictsList().get( 0 );
        page.clickHomeTabBtn();
        ( (UsersBasePage) page ).selectItemFromDropDown( ( (UsersBasePage) page ).getOrganizationDropDown(), districtName );
        page.clickUsrTabBtn();
        String welcomeText = appProperties.getProperty( "UsersWelcomeText" );
        welcomeText = welcomeText.replaceFirst( "\"Dynamic\"", UsersConstants.STRING_DELIM + districtName + UsersConstants.STRING_DELIM );
        assertEquals( ( (UsersBasePage) page ).getUsersWelcomeText(), welcomeText, "Mismatch in the Welcome message." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 202, dataProvider = "searchAndSortDataProvider", description = "Entry Criteria: Search is done through 'FirstName' For User Group 'All User Types'.</br>Execution Criteria: FirstName, LastName, UserId is verified with sorting order through LastName.</br>"
            + "Exit Criteria: User still is in Users landing page." )
    public void checkForSearchUsersAndSortByColumn( String searchInput, UsersTypeDropDown userType, boolean sortType, String sortingColumn, String errorMessage ) {
        page.clickHomeTabBtn();
        ( (UsersBasePage) page ).selectItemFromDropDown( ( (UsersBasePage) page ).getOrganizationDropDown(), districtName );
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
        Object[][] input = { { UsersConstants.FIRSTNAME, UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.FIRSTNAME, UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.FIRSTNAME, UsersTypeDropDown.ALL_ADMIN, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.FIRSTNAME, UsersTypeDropDown.ALL_TEACHER, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.LASTNAME, UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.LASTNAME, UsersTypeDropDown.ALL_ADMIN, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.LASTNAME, UsersTypeDropDown.ALL_TEACHER, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.PART_OF_FIRSTNAME, UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.PART_OF_LASTNAME, UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED },
                { UsersConstants.PART_OF_USER_ID, UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.SORT_ASCENDING, UsersConstants.COLUMN2, UsersConstants.SORTING_BY_LASTNAME_FAILED } };

        return input;
    }

    @Test ( priority = 212, dataProvider = "columnSortingProvider", description = "Entry Criteria: User selects 'All User Types' and click on 'LastName' header.</br>Execution Criteria: Value is sorted in ascending order through 'LastName'.</br>Exit Criteria: User still is in Users landing page." )
    public void checkForSortByColumnIsSortingcorrectly( UsersTypeDropDown userType, String columnName, String columnNumber, boolean sortType, String errorMessage ) {
        page.clickHomeTabBtn();
        ( (UsersBasePage) page ).selectItemFromDropDown( ( (UsersBasePage) page ).getOrganizationDropDown(), districtName );
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( userType.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        ( (UsersBasePage) page ).sortTableColumn( columnName, sortType );
        assertEquals( ( (UsersBasePage) page ).getSortedValuesOfTableColumn( columnNumber, sortType ), 1, errorMessage );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @DataProvider ( name = "columnSortingProvider" )
    public Object[][] columnSortingProvider() {
        Object[][] input = { { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_FIRSTNAME, UsersConstants.COLUMN1, UsersConstants.SORT_ASCENDING, "Sorting through FirstName in ascending order is Failed" },
                { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_FIRSTNAME, UsersConstants.COLUMN1, UsersConstants.SORT_DESCENDING, "Sorting through FirstName in ascending order is Failed" },
                { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_LASTNAME, UsersConstants.COLUMN2, UsersConstants.SORT_ASCENDING, "Sorting through LastName in ascending order is Failed" },
                { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_LASTNAME, UsersConstants.COLUMN2, UsersConstants.SORT_DESCENDING, "Sorting through LastName in ascending order is Failed" },
                { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_MIDDLENAME, UsersConstants.COLUMN3, UsersConstants.SORT_ASCENDING, "Sorting through MiddleName in ascending order is Failed" },
                { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_MIDDLENAME, UsersConstants.COLUMN3, UsersConstants.SORT_DESCENDING, "Sorting through MiddleName in ascending order is Failed" },
                { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_USERNAME, UsersConstants.COLUMN4, UsersConstants.SORT_ASCENDING, "Sorting through Username in ascending order is Failed" },
                { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_USERNAME, UsersConstants.COLUMN4, UsersConstants.SORT_DESCENDING, "Sorting through Username in ascending order is Failed" },
                { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_ID, UsersConstants.COLUMN5, UsersConstants.SORT_ASCENDING, "Sorting through ID in ascending order is Failed" },
                { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_ID, UsersConstants.COLUMN5, UsersConstants.SORT_DESCENDING, "Sorting through ID in ascending order is Failed" },
                { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_TYPE, UsersConstants.COLUMN6, UsersConstants.SORT_ASCENDING, "Sorting through Type in ascending order is Failed" },
                { UsersTypeDropDown.ALL_USER_TYPE, UsersConstants.COLUMN_TYPE, UsersConstants.COLUMN6, UsersConstants.SORT_DESCENDING, "Sorting through Type in ascending order is Failed" } };
        return input;
    }

    @Test ( priority = 224, description = "Entry Criteria: User selects 'All User Types' and click on 'Organization' header.</br>Execution Criteria: Value is Not changed while click on 'Organization' header.</br>Exit Criteria: User still is in Users landing page." )
    public void checkForOrganizationSelectedAndVerifyNoSortingCapabilities() {
        page.clickHomeTabBtn();
        ( (UsersBasePage) page ).selectItemFromDropDown( ( (UsersBasePage) page ).getOrganizationDropDown(), districtName );
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_USER_TYPE.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        String beforeClickForSort = ( (UsersBasePage) page ).sortTableColumn( UsersConstants.COLUMN_ORGANIZATION, UsersConstants.SORT_ASCENDING ).getAttribute( "class" );
        String afterClickForSort = ( (UsersBasePage) page ).sortTableColumn( UsersConstants.COLUMN_ORGANIZATION, UsersConstants.SORT_DESCENDING ).getAttribute( "class" );
        assertEquals( beforeClickForSort, afterClickForSort, "Sorting through Organization in enabled." );
        assertTrue( !beforeClickForSort.contains( "background" ) && !afterClickForSort.contains( "background" ), "Organization has sort enabled!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    //to do (feature is not implements)
    @Test ( priority = 225, description = "Entry Criteria: User selects 'All User Types' and click on 'Organization' header.</br>Execution Criteria: Value is sorted in descending order through 'Organization'.</br>Exit Criteria: User still is in Users landing page." )
    public void verifyClickOnTransferStudentButton() {
        page.clickHomeTabBtn();
        ( (UsersBasePage) page ).selectItemFromDropDown( ( (UsersBasePage) page ).getOrganizationDropDown(), districtName );
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_USER_TYPE.getType() );
        ( (UsersBasePage) page ).clickUsersTransferStudentBtn();
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 226, dataProvider = "userTypeDataProvider", description = "Entry Criteria : A district organization is selected from the Display list box, leaving the Grades list box at -- All Grades --, the 'Go' button is clicked. </br> "
            + "Execution Criteria : Verify the results label <# of system or district administrators associated to the district org> Users Found</br> Exit Criteria : User is in Users landing page." )
    public void checkResultsRowsCountInFooterLabelText( UsersTypeDropDown userType ) {
        page.clickHomeTabBtn();
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( districtName );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( userType.getType() );
        ( (UsersBasePage) page ).clickUsersGoBtn();
        assertEquals( ( (UsersBasePage) page ).getRowsCountFromFooter(), ( (UsersBasePage) page ).getRowsCountFromUsersTable(), "Count in Footer does not match" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @DataProvider ( name = "userTypeDataProvider" )
    public Object[][] userTypeDataProvider() {
        Object[][] data = { { UsersTypeDropDown.ALL_USER_TYPE }, { UsersTypeDropDown.ALL_ADMIN }, { UsersTypeDropDown.ALL_TEACHER }, { UsersTypeDropDown.ALL_ACTIVE_STUDENTS } };
        return data;
    }

}
