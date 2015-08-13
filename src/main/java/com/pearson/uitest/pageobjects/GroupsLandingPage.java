/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : GroupsLandingPage.java
 * 
 * Description : PageObject for groups Landing page UI functionalities for
 * district admin.
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
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.GroupsConstants;
import com.pearson.uitest.constants.SortConstants;
import com.pearson.uitest.helper.CommonHelper;

/**
 * Groups landing page UI elements will beidentified using this class.
 */
public class GroupsLandingPage extends UsersBasePage {

    private By grpSelectAllChkBox = By.id( "groupAllSelect" );
    private By grpNameHeader = By.xpath( "//div[@id='groupTableHeader']/div[contains(@id,'groupTableHeader') and text() = 'Name']" );
    private By grpOwnerHeader = By.xpath( "//div[@id='groupTableHeader']/div[contains(@id,'groupTableHeader') and text() = 'Owner']" );
    private By grpDescriptionHeader = By.xpath( "//div[@id='groupTableHeader']/div[contains(@id,'groupTableHeader') and text() = 'Description']" );
    private By grpNoResultLabel = By.cssSelector( "div.tableContentCell.floatLeft.groupCellSecond " );
    private By grpNoOfGroupsLabel = By.id( "footerText" );
    private By addGrpCancel = By.cssSelector( "button#addGroupCancel" );

    public GroupsLandingPage( WebDriver driver ) {
        super( driver );
        log = Logger.getLogger( GroupsLandingPage.class );
    }

    /**
     * Get add grp cancel
     */
    public WebElement getAddGroupCancelBtn() {
        return getElement( addGrpCancel );
    }

    /**
     * Get Select All checkbox in Groups landing page
     */
    public WebElement getGroupSelectAllCheckBox() {
        return getElement( grpSelectAllChkBox );
    }

    /**
     * Get Group name Header in Groups landing page
     */
    public WebElement getGroupsNameHeader() {
        return getElement( grpNameHeader );
    }

    /**
     * Get Group Owner Header in Groups landing page
     */
    public WebElement getGroupsOwnerHeader() {
        return getElement( grpOwnerHeader );
    }

    /**
     * Get Group Description Header in Groups landing page
     */
    public WebElement getGroupsDescriptionHeader() {
        return getElement( grpDescriptionHeader );
    }

    /**
     * Get No result text in Groups table
     */
    public String getGroupsNoResultText() {
        return getElement( grpNoResultLabel ).getText();
    }

    public int getRowsCountFromGroupsTable() {
        List<WebElement> rowList = driver.findElements( By.xpath( "//div[@id='groupTableBody']/div[contains(@class,'tableContentRow') and not(contains(@class,'ng-hide'))]" ) );
        log.info( "getRowsCountFromGroupsTable() " + rowList.size() );
        if ( rowList.size() == 1 ) {
            for ( WebElement ele : rowList ) {
                log.info( ele.getText() );
                if ( ele.getText().equals( GroupsConstants.RECORD_NOT_FOUND ) ) {
                    return 0;
                }
            }
        }
        return rowList.size();
    }

    /**
     * Get <No. of Groups> found label
     */
    public String getGroupsNoOfGroupsLabel() {
        return getElement( grpNoOfGroupsLabel ).getText();
    }

    public int getSortedValuesOfTableColumn( String colId, boolean ascendFalseDescendTrue ) {
        int i = 0;
        String rules = "";
        String columnId = colId.replaceAll( "Column", "" );

        List<WebElement> divIds = driver.findElements( By.xpath( "//div[@id='groupTableBody']/div[not(contains(@class,'ng-hide'))]/div[contains(@id,'groupTable')]" ) );
        List<WebElement> hdr = driver.findElements( By.xpath( "//div[@id='groupTableHeader']/div[contains(@id,'groupTableHeaderth')]" ) );
        int moduloVal = hdr.size();
        String[] userIds = new String[divIds.size() / moduloVal];
        for ( WebElement webElement : divIds ) {
            log.info( webElement.getAttribute( "id" ) );
            if ( webElement.getAttribute( "id" ).contains( "Td" + columnId ) ) {
                if ( webElement.getText() != null && webElement.getText().length() > 0 ) {
                    userIds[i] = webElement.getText();
                    i++;
                }
            }
        }

        // This rules can be changed as per what case-insensitive or case-sensitive  or both changes
        if ( !ascendFalseDescendTrue ) { // false=ascending
            // < 9 ... < 1 < 0 < a,A < ... < z,Z
            rules = SortConstants.CASE_SENSITIVE_SORTRULE_ASCENDING;

        } else { // true=descending
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
        if ( result == 0 ) { //Strings are same.
            result = 1;
        }
        return result;
    }

    public WebElement sortGroupsTableColumn( String columnName, boolean sortAscendingFalseDescendingTrue ) {
        WebElement columnHeader = driver.findElement( By.xpath( "//div[@id='groupTableHeader']/div[contains(@id,'groupTableHeader') and text() = '" + columnName + "']" ) );
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
        columnHeader = driver.findElement( By.xpath( "//div[@id='groupTableHeader']/div[contains(@id,'groupTableHeaderth') and text() = '" + columnName + "']" ) );
        log.info( "sortGroupsTableColumn() " + columnHeader.getAttribute( "class" ) );
        CommonHelper.nap();
        return columnHeader;
    }

}
