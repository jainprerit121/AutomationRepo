package com.pearson.uitest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.log4testng.Logger;

import com.pearson.uitest.helper.CommonHelper;

/**
 * This class defines the Licenses page elements wrt Organization tab 
 */

public class LicenseOrgPage extends TransferLicensePage{

    public LicenseOrgPage(WebDriver driver) {
        super(driver);
        log = Logger.getLogger( LicenseOrgPage.class );
    }
    
    private By orgName = By.xpath( "//*[@id='orgTableBodyTd10']/a" );
    private By licenseTabViaOrg = By.id( "editTabs2Link" );
    private By licenseTabActive = By.xpath("//li[@class='editTabActive']/a[text()='Licenses']");
    private By exitEditOrganizationButton = By.id("orgDetailsCancel");
    private By noResultsTextRow = By.xpath( "//div[contains(@id,'orgTableBodyTd') and text()='No Results']" );
    private By transferLink = By.xpath("//*[*[@id='orgTableBody']//*[contains(.,'%s')]]//a[contains(text(),'Transfer')]");
    private By licenseExpDate = By.xpath( "//*[@id='orgTableBody']/div/div[%s]" );
    private By orgPageOrgDropDown = By.xpath( "//*[@id='orgDisplayFilterSelect' and not(contains(@class,'ng-hide')) ]" );
    private By displayOption = By.id( "orgDisplayFilterOption" );
    private By displayDrop = By.id( "homeOrganisationFilterSelect" );
            
    public WebElement getOrgName(){
        return getElement(orgName);
    }
    
    public WebElement getLicenseTabViaOrg(){
        return getElement(licenseTabViaOrg);
    }
    
    public WebElement getLicenseTabActiveStatus()
    {
        return getElement(licenseTabActive);
    }
    
    public WebElement getExitEditOrganizationButton(){
        return getElement(exitEditOrganizationButton);
    }
    
    public WebElement getNoResultsTextRowData(){
        return getElement(noResultsTextRow);
    }
    
    public WebElement getTransferLink(String strLicense){
        By licenseNameTable = CommonHelper.getLocatorFormatString( transferLink, strLicense );
        return getElement(licenseNameTable);
    }
    
    public List<String> getTableLicenseExpDate( int index ) {
        By licenseExpDateTable = CommonHelper.getLocatorFormatInt( licenseExpDate, index );
        return getTableData( licenseExpDateTable );
    }
    
    public void selOrgPageOrgFromDropdown( String item ) {
        CommonHelper.nap();
        Select orgDrop = new Select( getElement( orgPageOrgDropDown ) );
        orgDrop.selectByVisibleText( item );
    }
    
    public WebElement getDisplayOption(){
        return getElement( displayOption );
    }
    
  //Get Display dropdown
    public WebElement getOrgDisplayDropdown() {
        return getElement( displayDrop );
    }
}
