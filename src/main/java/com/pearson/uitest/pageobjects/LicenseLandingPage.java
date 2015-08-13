package com.pearson.uitest.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.LicenseConstants;
import com.pearson.uitest.helper.CommonHelper;

/**
 */

public class LicenseLandingPage extends LicenseBasePage {

    private By addLicenseBtn = By.id( "lsAddLsgBtn" );
    private By licenseWelcomeText = By.id( "contentBar" );
    private By licenseDisplayDropDownTitle = By.id( "lsDisplayFilterText" );
    private By licenseDisplayDropDown = By.id( "lsDisplayFilterSelect" );
    private By licenseTable = By.xpath( "//*[@id='orgTableBody']/div[contains(@id,'orgTableBodytr')]" );
    private By licenseTableHeader = By.xpath( "//*[@id='lsTableHeader']/div[not(@class='clear')]" );
    private By licenseTableDataLoc = By.xpath( "//*[@id='orgTableBody']/div/div[%s]" );
    private By licenseTableRow = By.xpath( "//*[@id='orgTableBody']/div/div[1]" );
    private By licenseSeatsCheck = By.xpath( "//*[@id='orgTableBodyTd30' and text()='" + LicenseConstants.CONCURRENT_USER + "']" );
    private By transferLink = By.xpath( "//div[@id='lsContainer']//a[contains(.,'Transfer')]" );
    private By licenseNameRowData = By.id( "orgTableBodyTd10" );
    private By expDateRowData = By.id( "orgTableBodyTd20" );
    private By seatsRowData = By.id( "orgTableBodyTd30" );

    public LicenseLandingPage( WebDriver driver ) {
        super( driver );
        log = Logger.getLogger( LicenseLandingPage.class );
    }

    //Get Add License Button
    public WebElement getAddLicenseBtn() {
        return getElement( addLicenseBtn );
    }

    // Get welcome text for the logged in user from the License page
    public String getLicenseLandWelcomeText() {
        return getElement( licenseWelcomeText ).getText();
    }
    
    public WebElement getLicenseLandWelcomeWebElement(){
        return getElement (licenseWelcomeText);
    }

    //Get Display drop-down label
    public String getLicenseLandDisplayDropTitle() {
        return getElement( licenseDisplayDropDownTitle ).getText();
    }

    //Get Display drop-down
    public WebElement getLicenseDisplayDropdown() {
        return getElement( licenseDisplayDropDown );
    }

    //Get License Table row text
    public WebElement getLicenseTableRow() {
        return getElement( licenseTableRow );
    }

    //Get Number of Added Seats
    public WebElement getAddedLicensesSeatsValue() {
        return getElement( licenseSeatsCheck );
    }

    //Get Transfer Link
    public WebElement getTransferLink() {
        return getElement( transferLink );
    }

    public String getLicenseNameRowData() {
        return getElement( licenseNameRowData ).getText();
    }

    public String getExpDateRowData() {
        return getElement( expDateRowData ).getText();
    }

    public String getSeatsRowData() {
        return getElement( seatsRowData ).getText();
    }

    //Get Display drop-down size
    public String getLicenseLandDisplayDropdownSize() {
        Select dispDrop = new Select( getElement( licenseDisplayDropDown ) );
        log.info( "License  dropdown list size " + dispDrop.getOptions().size() );
        return Integer.toString( dispDrop.getOptions().size() );
    }

    public boolean checkLicenseDropDownInAscendingOrder( String removeItem ) {
        return checkDropdownValuesInAscendingOrder( getElement( licenseDisplayDropDown ), removeItem );
    }

    public boolean checkDropdownValuesInAscendingOrder( WebElement dropdownElement, String removeItem ) {
        List<String> actualOrgNames = getDropDownValues( dropdownElement );
        if ( !"".equals( removeItem ) ) {
            actualOrgNames.remove( removeItem );
        }
        List<String> expectedOrgNames = new ArrayList<String>( actualOrgNames );
        CommonHelper.sortAscendingWithIgnoreCase( expectedOrgNames );
        log.info( "Expected: " + expectedOrgNames.toString() );
        log.info( "Actual: " + actualOrgNames.toString() );
        return actualOrgNames.equals( expectedOrgNames );
    }

    public void selOrgUnderLicenseDropdown( String item ) {
        selectItemFromDropDown( getElement( licenseDisplayDropDown ), item );
    }
    
    public List<String> getOrgDropdownValues() {
        return getDropDownValues( getElement( licenseDisplayDropDown ) );
    }

    public Integer getLicenseTableSize() {
        return getElements( licenseTable ).size();
    }

    public Integer getLicenseTableHeaderSize() {
        return getElements( licenseTableHeader ).size();
    }

    public List<WebElement> getLicenseTableHeaderWebElements() {
        return getElements( licenseTableHeader );
    }

    public List<String> getTableLicenseName( int index ) {
        By licenseNameTable = CommonHelper.getLocatorFormatInt( licenseTableDataLoc, index );
        return getTableData( licenseNameTable );

    }
    
    public WebElement getTableLicenseNameElement( String val ) {
        By licenseNameTable = CommonHelper.getLocatorFormatString( licenseTableDataLoc, val );
        return getElement( licenseNameTable );

    }

    public List<String> getTableLicenseExpDate( int index ) {
        By licenseExpDateTable = CommonHelper.getLocatorFormatInt( licenseTableDataLoc, index );
        return getTableData( licenseExpDateTable );
    }

    public List<String> getTableLicenseNoOfSeats( int index ) {
        By licenseNoOfSeatsTable = CommonHelper.getLocatorFormatInt( licenseTableDataLoc, index );
        return getTableData( licenseNoOfSeatsTable );
    }
    
    public List<String> getTableLicenseInUse( int index ) {
        By licenseInUseTable = CommonHelper.getLocatorFormatInt( licenseTableDataLoc, index );
        return getTableData( licenseInUseTable );
    }
    
    public List<String> getTableLicenseDenials( int index ) {
        By licenseDenialsTable = CommonHelper.getLocatorFormatInt( licenseTableDataLoc, index );
        return getTableData( licenseDenialsTable );
    }
    
    public List<String> getTableLicenseOptions( int index ) {
        By licenseDenialsTable = CommonHelper.getLocatorFormatInt( licenseTableDataLoc, index );
        return getTableData( licenseDenialsTable );
    }
    
    public WebElement getTableLicenseInUseElement( int index ) {
        By licenseInUseTable = CommonHelper.getLocatorFormatInt( licenseTableDataLoc, index );
        return getElement( licenseInUseTable );
    }
    
    public WebElement getTableLicenseDenialsElement( int index ) {
        By licenseDenialsTable = CommonHelper.getLocatorFormatInt( licenseTableDataLoc, index );
        return getElement( licenseDenialsTable );
    }
    
    public WebElement getTableLicenseOptionsElement( int index ) {
        By licenseDenialsTable = CommonHelper.getLocatorFormatInt( licenseTableDataLoc, index );
        return getElement( licenseDenialsTable );
    }

    //Get default selection for Organization drop-down
    public boolean getDefaultOrgSelection( String licenseNameDropDown ) {
        WebElement select = this.getLicenseDisplayDropdown();
        Select sel = new Select( select );
        sel.selectByIndex( 0 );
        CommonHelper.nap();
        WebElement option = select.findElement( By.xpath( "//option[contains(text(),'" + licenseNameDropDown + "')]" ) );
        log.info( option.getAttribute( "text" ) + option.isSelected() + option.isDisplayed() + "\t//option[contains(text(),'" + licenseNameDropDown + "')]" );
        return option.getAttribute( "text" ).equals( licenseNameDropDown ) || option.getAttribute( "text" ).contains( licenseNameDropDown );
    }
}
