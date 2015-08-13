package com.pearson.uitest.pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.log4testng.Logger;

import com.pearson.uitest.helper.CommonHelper;

/**
 * This class defines the page objects of the organization landing page
 * 
 * @author Nagarajan Ganesan
 *
 */
public class OrganizationPage extends AdminBasePage {

    protected static final String TD = "Td";
    private By orgWelcomeText = By.id( "contentBar" );
    private By displayDropTitle = By.id( "orgDisplayFilterText" );
    private By displayDrop = By.id( "orgDisplayFilterSelect" );
    private By displayOption = By.id( "orgDisplayFilterOption" );
    private By orgAddOrgBtn = By.cssSelector( "button.floatLeft" );
    private By addOrgBtn = By.cssSelector( "#addOrgBtn.floatLeft.button" );
    private By addOrgBtnCancel = By.cssSelector( "#orgCancel" );
    private By serSetSpan = By.id( "orgTableBodyTdLink0" );
    private By tableHeader1 = By.id( "orgTableHeaderth1" );
    private By tableHeader2 = By.id( "orgTableHeaderth2" );
    private By tableHeader3 = By.id( "orgTableHeaderth3" );
    private By addOrgNameInput = By.id( "addOrgNameInput" );
    private By addOrgIdInput = By.id( "addOrgIdInput" );
    private By addOrgTypeSelect = By.id( "addOrgTypeSelect" );
    private By addOrgDistrictSelect = By.id( "addOrgDistrictSelect" );
    private By addOrgSaveBtn = By.cssSelector( "#addOrgSave" );
    private By orgDropdown = By.id( "homeorganisationfilterSelect" );
    private By orgPageOrgDropDown = By.xpath( "//*[@id='orgDisplayFilterSelect' and not(contains(@class,'ng-hide')) ]" );
    private By orgTable = By.xpath( "//*[@id='orgTableBody']/div" );
    private By orgTableOrgNames = By.xpath( "//*[@id='orgTableBody']/div/div/a" );
    private By orgTableOrgIDs = By.xpath( "//*[@id='orgTableBody']/div/div[2]" );

    public OrganizationPage( WebDriver driver ) {
        super( driver );
        log = Logger.getLogger( OrganizationPage.class );
    }

    // Get Add Organization button
    public WebElement getOrgLandAddOrgBtn() {
        return getElement( orgAddOrgBtn );
    }

    public WebElement getOrgLandAddOrgCancelBtn() {
        return getElement( addOrgBtnCancel );
    }

    public WebElement getOrgIdTableElement(String orgID) {
    	return  getElement(By.xpath("//*[contains(@id,'orgTableBodyTd2') and contains(text(),'"+ orgID +"')]"));
    }
    
    public String addOrgForTest( String orgName, String orgId, String depType, String dist ) {
        getElement( addOrgBtn ).click();
        getElement( addOrgNameInput ).sendKeys( orgName );
        getElement( addOrgIdInput ).sendKeys( orgId );
        Select selDepType = new Select( getElement( addOrgTypeSelect ) );
        selDepType.selectByVisibleText( depType );
        Select selDistrict = new Select( getElement( addOrgDistrictSelect ) );
        selDistrict.selectByVisibleText( dist );
        getElement( addOrgSaveBtn ).click();
        return switchToAlert();
    }

    public Map<String, String> clickAddOrganizationButton() {
        getOrgLandAddOrgBtn().click();
        CommonHelper.nap();
        return getPageDetails();
    }

    //Get Display dropdown label
    public String getOrgLandDisplayDropTitle() {
        return getElement( displayDropTitle ).getText();
    }

    //Get Display dropdown
    public WebElement getOrgDisplayDropdown() {
        return getElement( displayDrop );
    }

    public WebElement getOrganizationPageOrgdropdown() {
        return getElement( orgPageOrgDropDown );
    }
    
    //Get Display dropdown
    public String getOrgDisplayDropdownSize() {
        Select dispDrop = new Select( getElement( displayDrop ) );
        log.info( "Organization  dropdown list size " + dispDrop.getOptions().size() );
        return Integer.toString( dispDrop.getOptions().size() );
    }

    public WebElement getOrgLandTableNameHeader() {
        return getElement( tableHeader1 );
    }

    public WebElement getOrgLandTableIDHeader() {
        return getElement( tableHeader2 );
    }

    public WebElement getOrgLandTableOptionHeader() {
        return getElement( tableHeader3 );
    }

    // Get Welcome message for the logged in user
    public String getOrgWelcomeMsg() {
        return getElement( orgWelcomeText ).getText();
    }

    //Select All Display dropdown values
    public String selectOrgDisplayDropDown() {
        Select dispDrop = new Select( this.getOrgDisplayDropdown() );
        return this.selDropDown( dispDrop );
    }

    //Select all the drop down values
    public String selDropDown( Select sel ) {
        int i = sel.getOptions().size();
        try {
            for ( int j = 0; j < i; j++ ) {
                sel.selectByIndex( j );
            }
            return "Success";
        } catch ( Exception e ) {
            log.warn( "Selection not found in dropdown " + e.getMessage() );
            return "Failed";
        }
    }

    public String addOrganizationAndCancel() {
        String url;
        getElement( addOrgBtn ).click();
        CommonHelper.nap();
        url = driver.getCurrentUrl();
        log.info( url );
        getElement( addOrgBtnCancel ).click();

        return url;
    }

    //Get server settings for SchoolAdmin
    public WebElement getSerSetforSchoolAdmin() {
        return getElement( serSetSpan );
    }

    //Get server settings for SchoolAdmin
    public WebElement getOrganizationLink( String organizationName ) {
        return getElement( By.linkText( organizationName ) );
    }

    //Get default selction for Organization dropdown
    public boolean getDefaultOrgSelection( String orgNameDropDown ) {

        WebElement select = this.getOrgDisplayDropdown();
        Select sel = new Select( select );
        sel.selectByIndex( 0 );
        CommonHelper.nap();
        WebElement option = select.findElement( By.xpath( "//option[contains(text(),'" + orgNameDropDown + "')]" ) );
        log.info( option.getAttribute( "text" ) + option.isSelected() + option.isDisplayed() + "\t//option[contains(text(),'" + orgNameDropDown + "')]" );
        return option.getAttribute( "text" ).equals( orgNameDropDown ) || option.getAttribute( "text" ).contains( orgNameDropDown );
    }

    //Select any value from Org dropdown
    public void selOrgFromDropdown( String item ) {
        CommonHelper.nap();
        Select orgDrop = new Select( getElement( orgDropdown ) );
        orgDrop.selectByVisibleText( item );
    }

    public void selOrgPageOrgFromDropdown( String item ) {
        CommonHelper.nap();
        Select orgDrop = new Select( getElement( orgPageOrgDropDown ) );
        orgDrop.selectByVisibleText( item );
    }

    public List<String> getAllOrganizationDropDownValues() {
        return getDropDownValues( getElement( orgDropdown ) );
    }

    public List<String> getAllOrgPageOrganizationDropDownValues() {
        return getDropDownValues( getElement( orgPageOrgDropDown ) );
    }

    public String getOrgTableSize() {
        return Integer.toString( driver.findElements( orgTable ).size() );
    }

    public Map<String, String> clickOrgPageHelpButton() {
        getHelpBtn().click();
        return getNewPageDetails();
    }
    
    public Map<String, String> clickServerSettings() {
        getSerSetforSchoolAdmin().click();
        CommonHelper.nap();
        return getNewPageDetails();
    }

    public List<String> getTableOrgNamesList() {
        return getTableData( orgTableOrgNames );
    }

    public List<String> getTableOrgIDsList() {
        return getTableData( orgTableOrgIDs );
    }

    public WebElement getOrgNameRowHeader() {
        return getElement( tableHeader1 );
    }

    public WebElement getOrgIDRowHeader() {
        return getElement( tableHeader2 );
    }
    
    public WebElement getDisplayOption(){
        return getElement( displayOption );
    }
    
    public boolean checkOrganizationDropDownInAscendingOrder(String removeItem) {
    	return checkDropdownValuesInAscendingOrder(getElement(orgPageOrgDropDown), removeItem);
    }
    public boolean checkDropdownValuesInAscendingOrder(WebElement dropdownElement, String removeItem) {
        List<String> actualOrgNames = getDropDownValues( dropdownElement );
        if (!"".equals(removeItem)) { 
        	actualOrgNames.remove( removeItem );
        }
        List<String> expectedOrgNames = new ArrayList<String>( actualOrgNames );
        CommonHelper.sortAscendingWithIgnoreCase( expectedOrgNames );
        log.info( "Expected: " + expectedOrgNames.toString() );
        log.info( "Actual: " + actualOrgNames.toString() );
        return actualOrgNames.equals(expectedOrgNames);
    }
}