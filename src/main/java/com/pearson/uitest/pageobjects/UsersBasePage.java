/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : UsersBasePage.java
 * 
 * Description : Page object for Users Landing page UI functionalities.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/
package com.pearson.uitest.pageobjects;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.SortConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.helper.CommonHelper;

/**
 * All the common UI elements of Users page are located and accessed in this
 * class.
 */
public class UsersBasePage extends AdminBasePage {

    private static final String TD = "Td";

    private By usersWelcomeLabel = By.id( "contentBar" );
    private By usersDisplayLabel = By.id( "orgDisplayFilterText" );
    private By usersOrgDropDown = By.xpath( "//select[@id='orgDisplayFilterSelect' and not(contains(@class,'ng-hide'))]" );
    private By usersGradeDropDown = By.id( "userGradeSelect" );
    private By usersUserTypeDropDown = By.id( "userUserSelect" );
    private By usersGoBtn = By.id( "userGoBtn" );
    private By usersSearchLabel = By.id( "userSearchText" );
    private By usersSearchInput = By.id( "userSearchInput" );
    private By usersSearchBtn = By.id( "userSearchBtn" );
    private By usersTransferStudentBtn = By.id( "transferStudentBtn" );
    private By usersAddGroupBtn = By.id( "addGroupBtn" );
    private By usersAddUserBtn = By.id( "addUserBtn" );
    private By orgDropDown = By.id( "homeorganisationfilterSelect" );
    private By usersAddUserToGroupBtn = By.id( "addUserGroupBtn" );
    private By usersRemoveBtn = By.id( "removeUserBtn" );
    private By userSelectAllCheckButton = By.id( "userSelectAll" );
    private By footerText = By.id( "footerText" );
    private By noRecFoundText = By.id( "userTableBodyTd2" );
    private By assignStdBtn = By.id( "assignStdBtn" );
    private By addUserCancel = By.id( "addUserCancel" );

    public UsersBasePage( WebDriver driver ) {
        super( driver );
        log = Logger.getLogger( UsersBasePage.class );
    }

    /**
     * Get welcome message in Users page
     */
    public String getUsersWelcomeText() {
        return getElement( usersWelcomeLabel ).getText();
    }

    /**
     * Get Org dropdown
     */
    public WebElement getOrganizationDropDown() {
        return getElement( orgDropDown );
    }

    /**
     * Get Display label in Users page
     */
    public String getUsersDisplayLabel() {
        return getElement( usersDisplayLabel ).getText();

    }

    /**
     * Get Org dropdown in Users page
     */
    public WebElement getUsersOrgDropDown() {
        return getElement( usersOrgDropDown );
    }

    /**
     * Get default selection in Org dropdown
     */
    public String getDefaultSelectedItemInOrgDropDown() {
        return getDefaultSelectedOption( getUsersOrgDropDown() );
    }

    /**
     * Select an organization from dropdown
     */
    public void selectItemFromOrgDropDown( String optionToSelect ) {
        selectItemFromDropDown( getUsersOrgDropDown(), optionToSelect );
    }

    /**
     * Get all the options in Org dropdown
     */
    public List<String> getAllDropDownItemsInOrgDropDown() {
        return getDropDownValues( getUsersOrgDropDown() );
    }

    /**
     * Get grade dropdown in Users page
     */
    public WebElement getUsersGradeDropDown() {
        return getElement( usersGradeDropDown );
    }

    /**
     * Get default selection in grade dropdown
     */
    public String getDefaultSelectedItemInGradeDropDown() {
        return getDefaultSelectedOption( getUsersGradeDropDown() );
    }

    /**
     * Select an grade from dropdown
     */
    public void selectItemFromGradeDropDown( String optionToSelect ) {
        selectItemFromDropDown( getUsersGradeDropDown(), optionToSelect );
    }

    /**
     * Get all the options in grade dropdown
     */
    public List<String> getAllDropDownItemsInGradeDropDown() {
        return getDropDownValues( getUsersGradeDropDown() );
    }

    /**
     * Get Users type dropdown in Users page
     */
    public WebElement getUsersTypeDropDown() {
        return getElement( usersUserTypeDropDown );
    }

    /**
     * Get default selection in Users type dropdown
     */
    public String getDefaultSelectedItemInUsersTypeDropDown() {
        return getDefaultSelectedOption( getUsersTypeDropDown() );
    }

    /**
     * Select a user type from dropdown
     */
    public void selectItemFromUsersTypeDropDown( String optionToSelect ) {
        selectItemFromDropDown( getUsersTypeDropDown(), optionToSelect );
    }

    /**
     * Get all the options in User Type dropdown
     */
    public List<String> getAllDropDownItemsInUsersTypeDropDown() {
        return getDropDownValues( getUsersTypeDropDown() );
    }

    /**
     * Get Go button in users page
     */
    public WebElement getUsersGoBtn() {
        return getElement( usersGoBtn );
    }

    /**
     * Click on Go button in Users page
     */
    public String clickUsersGoBtn() {
        return clickOnButton( getUsersGoBtn() );
    }

    /**
     * Get Search Users label in Users page
     */
    public String getUsersSearchLabel() {
        return getElement( usersSearchLabel ).getText();
    }

    /**
     * Get Search Input in Users page
     */
    public WebElement getUsersSearchInput() {
        return getElement( usersSearchInput );
    }

    /**
     * Enter text in Search Users input field
     */
    public void enterSearchTextInSearchInput( String searchText ) {
        getUsersSearchInput().sendKeys( searchText );
    }

    /**
     * Get entered text from Search Input
     */
    public String getEnteredTextInSearchInput() {
        return getElementAttribute( getUsersSearchInput(), "value" );
    }

    /**
     * Get Search btn in Users page
     */
    public WebElement getUsersSearchBtn() {
        return getElement( usersSearchBtn );
    }

    /**
     * Click on Search btn in users page
     */
    public String clickUsersSearchBtn() {
        return clickOnButton( getUsersSearchBtn() );
    }

    /**
     * Get Transfer Students btn in Users page
     */
    public WebElement getUsersTransferStudentBtn() {
        return getElement( usersTransferStudentBtn );
    }

    /**
     * Get status of Transfer Student button
     */
    public String getUsersTransferStudentBtnStatus() {
        return getElementAttribute( getUsersTransferStudentBtn(), "class" );
    }

    /**
     * Click on Transfer Student btn in users page
     */
    public String clickUsersTransferStudentBtn() {
        return clickOnButton( getUsersTransferStudentBtn() );
    }

    /**
     * Get Add User btn in Users page
     */
    public WebElement getUsersAddGroupBtn() {
        return getElement( usersAddGroupBtn );
    }

    /**
     * Click on Add Group btn in users page
     */
    public Map<String, String> clickUsersAddGroupBtn() {
        return getPageDetailsAfterClick( getUsersAddGroupBtn() );
    }

    /**
     * Get Add User btn in Users page
     */
    public WebElement getUsersAddUserBtn() {
        return getElement( usersAddUserBtn );
    }

    /**
     * Click on Add User btn in users page
     */
    public Map<String, String> clickUsersAddUserBtn() {
        return getPageDetailsAfterClick( getUsersAddUserBtn() );
    }

    /**
     * Get Add User to Group button
     */
    public WebElement getUsersAddUserToGroupBtn() {
        return getElement( usersAddUserToGroupBtn );
    }

    public String getAttributeAddUserToGroupBtn() {
        return getUsersAddUserToGroupBtn().getAttribute( "class" );
    }

    /**
     * Click on Add User to Group button
     */
    public String clickAddUserToGroupBtn() {
        return clickOnButton( getUsersAddUserToGroupBtn() );
        //TODO Page is under construction. Needs to be modified once the actual implementation is complete.

    }

    /**
     * Get Remove button in Users page
     */
    public WebElement getUsersRemoveBtn() {
        return getElement( usersRemoveBtn );
    }

    /**
     * Click on Remove button in Users
     */
    public String clickUserRemoveBtn() {
        return clickOnButton( getUsersRemoveBtn() );
    }

    /**
     * Check Org dropdown values ascending
     */
    public boolean checkOrgDropdownValuesInAscendingOrder( WebElement dropdownElement ) {
        List<String> actualOrgNames = getDropDownValues( dropdownElement );
        List<String> expectedOrgNames = new ArrayList<String>( actualOrgNames );
        CommonHelper.sortAscendingWithIgnoreCase( expectedOrgNames );
        log.info( "Expected: " + expectedOrgNames.toString() );
        log.info( "Actual: " + actualOrgNames.toString() );
        return actualOrgNames.equals( expectedOrgNames );
    }

    public List<String> getItemsFromTableColumn( int colIndex ) {
        List<WebElement> licListElements = driver.findElements( By.xpath( "//div[contains(@id, 'userTableBodyTd" + colIndex + "\')]" ) );
        ArrayList<String> licList = new ArrayList<String>();
        for ( WebElement element : licListElements ) {
            if ( element.getText().length() > 0 ) {
                licList.add( element.getText() );
            }
        }
        log.info( licList );
        return licList;
    }

    public int getSortedValuesOfTableColumn( String colId, boolean ascendFalseDescendTrue ) {
        ArrayList<String> userNameId = new ArrayList<String>();
        int i = 0;
        String rules = "";
        String columnId = colId.replaceAll( "Column", "" );

        List<WebElement> divIds = driver.findElements( By.xpath( "//div[@id='userTableBody']/div[not(contains(@class,'ng-hide'))]/div[contains(@id,'userTable')]" ) );
        List<WebElement> hdr = driver.findElements( By.xpath( "//div[@id='userTableHeader']/div[contains(@id,'userTableHeaderth')]" ) );
        int moduloVal = hdr.size();

        String[] userIds = new String[divIds.size() / moduloVal];
        for ( WebElement webElement : divIds ) {
            if ( webElement.getAttribute( "id" ).contains( TD + columnId ) ) {
                if ( webElement.getText() != null && webElement.getText().length() > 0 ) {
                    userNameId.add( webElement.getText() );
                    userIds[i] = webElement.getText();
                    i++;
                }
            }
        }

        // This rules can be changed as per what case-insensitive,
        // case-sensitive changes
        if ( !ascendFalseDescendTrue ) { // false=ascending
            // < 9 ... < 1 < 0 < a,A < ... < z,Z
            rules = SortConstants.CASE_SENSITIVE_SORTRULE_ASCENDING;
        } else {
            // < z,Z ... < b,B < a,A < 9 < ... < 0
            rules = SortConstants.CASE_SENSITIVE_SORTRULE_DESCENDING;
        }
        int result = 0;
        RuleBasedCollator ruleBasedCollator;
        try {
            ruleBasedCollator = new RuleBasedCollator( rules );
            if ( userIds.length > 1 ) {
                for ( i = 0; i < userIds.length - 1; i++ ) {
                    result = ruleBasedCollator.compare( userIds[i], userIds[i + 1] );
                }

            } else {
                // Only one element in Dropdown. No sorting required.
                result = 1;
            }
        } catch ( ParseException e ) {
            log.info( "Not parsed" );
        } catch ( IllegalArgumentException e ) {
            log.info( "Not parsed" );
        }
        if ( result == 0 ) {
            result = 1; //Strings are same.
        }
        return result;
    }

    public boolean checkForStringInColumnOfUsersGroupTable( int colId, String searchText ) {
        List<WebElement> test = driver.findElements( By.xpath( "//div[@id='userTableBody']/div[not(contains(@class,'ng-hide'))]/div[contains(@id,'userTable')]" ) );
        List<WebElement> hdr = driver.findElements( By.xpath( "//div[@id='userTableHeader']/div[contains(@id,'userTableHeaderth')]" ) );
        int moduloVal = hdr.size();
        int i = 0;
        while ( i < test.size() ) {
            if ( i % moduloVal == colId ) {
                if ( test.get( i ).getText().toLowerCase().contains( searchText.toLowerCase() ) || test.get( 2 ).getText().equals( UsersConstants.NO_RECORDS_FOUND ) ) {
                    return true;
                }
            }
            i++;
        }
        return false;

    }

    public WebElement getSelectAllCheckButton() {
        return getElement( userSelectAllCheckButton );
    }

    public List<String> getColumnHeaders() {

        List<WebElement> hdr = driver.findElements( By.xpath( "//div[@id='userTableHeader']/div[contains(@id,'userTableHeaderth')]" ) );
        log.info( "Column Header Web Elements " + hdr );
        List<String> hdrNames = new ArrayList<String>();
        for ( WebElement ele : hdr ) {
            if ( !"".equals( ele.getText() ) ) {
                hdrNames.add( ele.getText() );
            }

        }
        return hdrNames;

    }

    public int getRowsCountFromFooter() {
        log.info( "getRowsCountFromFooter() " + getElement( footerText ).getText().split( " " )[0] );
        return Integer.parseInt( getElement( footerText ).getText().split( " " )[0] );
    }

    public int getRowsCountFromUsersTable() {
        List<WebElement> rowList = driver.findElements( By.xpath( "//div[@id='userTableBody']/div[contains(@class,'tableContentRow') and not(contains(@class,'ng-hide'))]" ) );
        log.info( "getRowsCountFromUsersTable() " + rowList.size() );
        if ( rowList.size() == 1 ) {
            for ( WebElement ele : rowList ) {
                if ( ele.getText().equals( UsersConstants.NO_RECORDS_FOUND ) ) {
                    return 0;
                }
            }
        }
        return rowList.size();
    }

    public String getNumOfUsersFoundFooterText() {
        CommonHelper.nap();
        CommonHelper.nap();
        CommonHelper.nap();
        return getElement( footerText ).getText();
    }

    public String getNoRecFoundText() {
        return getElement( noRecFoundText ).getText();
    }

    public WebElement getUsersAssignStudentsToTeacherButton() {
        return getElement( assignStdBtn );
    }

    public String clickUsersAssignStudentsToTeacherButton() {
        return clickOnButton( getUsersAssignStudentsToTeacherButton() );
        //TODO Page is under construction. Needs to be modified once the actual implementation is complete.
    }

    public String selectUserInUsersTable() {
        List<WebElement> usersCheck = driver.findElements( By.xpath( "//input[contains(@id,'userSelect') and not(contains(@id,'userSelectAll'))]" ) );
        for ( WebElement chk : usersCheck ) {
            if ( Integer.parseInt( chk.getAttribute( "id" ).replaceAll( "userSelect", "" ) ) % 2 == 0 ) {
                chk.click();
            }
        }
        return "";
    }

    public WebElement sortTableColumn( String columnName, boolean sortAscendingFalseDescendingTrue ) {
        WebElement columnHeader = driver.findElement( By.xpath( "//div[@id='userTableHeader']/div[contains(@id,'userTableHeaderth') and text() = '" + columnName + "']" ) );
        String sortOrder = columnHeader.getAttribute( "class" );
        if ( sortOrder.contains( "backgroundNone" ) || sortOrder.contains( "background" ) ) {
            columnHeader.click();
        }
        sortOrder = columnHeader.getAttribute( "class" );
        if ( sortOrder.contains( "backgroundtrue" ) && !sortAscendingFalseDescendingTrue ) { // descending
            columnHeader.click();
        }
        if ( sortOrder.contains( "backgroundfalse" ) && sortAscendingFalseDescendingTrue ) { // ascending
            columnHeader.click();
        }
        columnHeader = driver.findElement( By.xpath( "//div[@id='userTableHeader']/div[contains(@id,'userTableHeaderth') and text() = '" + columnName + "']" ) );
        log.info( "sortTableColumn() " + columnHeader.getAttribute( "class" ) );
        CommonHelper.nap();
        return columnHeader;
    }

    public WebElement getCancelButtonUsersPage() {
        return getElement( addUserCancel );
    }
}
