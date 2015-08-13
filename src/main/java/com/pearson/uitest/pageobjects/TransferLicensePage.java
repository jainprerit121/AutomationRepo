package com.pearson.uitest.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.pearson.uitest.helper.CommonHelper;

/**
 * This class defines the Transfer License elements 
 */

public class TransferLicensePage extends LicenseLandingPage {

    private By transferLicenseDialogTitle = By.xpath("//*[@id='confirmBoxHeaderTitle'][normalize-space(text())='Transfer License']");
    private By transferLicenseDialogText = By.id("transferLsTitleText");
    private By transferLicenseHelpBtn = By.id("transferLsLinkBtn");
    private By transferLicenseExpDateLabel = By.id("transferLsExpLabel");
    private By transferTotalNumberofUsersLabel = By.id("transferLsUsersLabel");
    private By transferNumberOfUserstoTransferLabel = By.id("transferLsNoUserLabel");
    private By transferNumberOfUsersMandatoryMark = By.id("noOfUserStarMark");
    private By transferNumberOfUsersTextBox = By.id("transferLsUsersInput");
    private By transferNote = By.id("transferLsUsersNoteLabel");
    private By transferFromLabel = By.id("LsTransferFromLabel");
    private By transferToLabel = By.id("LsTransferToLable");
    private By transferOrgSelectDropDown = By.id("lsTransferToSelect");
    private By transferOkBtn = By.id("transferLsBtns0");
    private By transferCancelBtn = By.id("transferLsBtns1");
    //private By transferLink = By.id("orgTableBodyTdLink0");
    private By transferLink = By.xpath("//*[@id='orgTableBody']//*[contains(.,'%s')]//a[contains(text(),'Transfer')]");
    private By transferExpDateValueFromDialog = By.id("transferLsExpDateValue");
    private By totalNumberOfUsersFromDialog = By.id("transferLsUsersValue");
    private By transferFromValueFromDialog = By.id("LsTransferFromValue");	
    private By alertOkBtn = By.id("okBtn");
    private By popUpMsg = By.id("popUpMsg");
    private By xButton = By.id( "confirmBoxHeaderCloseImg" );

    public TransferLicensePage(WebDriver driver) {
        super(driver);
        log = Logger.getLogger( TransferLicensePage.class );
    }

    //Get Transfer Link
    /*public WebElement getTransferLink(String strLicense){
		return getElement(transferLink);
	}*/

    public WebElement getTransferLink(String strLicense){
        By licenseNameTable = CommonHelper.getLocatorFormatString( transferLink, strLicense );
        return getElement(licenseNameTable);
    }

    public List<String> getTableLicenseName( String strLicense ) {
        By licenseNameTable = CommonHelper.getLocatorFormatString( transferLink, strLicense );
        return getTableData( licenseNameTable );
    }

    //Get Transfer License Dialog Title
    public String getTransferLicenseDialogTitle(){
        return getElement( transferLicenseDialogTitle ).getText();
    }

    public WebElement getTransferLicenseDialogTitleWebElement(){
        return getElement (transferLicenseDialogTitle);
    }

    //Get Transfer License Dialog Text
    public String getTransferLicenseDialogText(){
        return getElement( transferLicenseDialogText ).getText();
    }

    //Get Transfer License Help Button
    public WebElement getTransferLicenseHelpBtn(){
        return getElement( transferLicenseHelpBtn );
    }

    //Get Transfer License Exp Date Label
    public String getTransferLicenseExpDateLabel(){
        return getElement( transferLicenseExpDateLabel ).getText();
    }

    //Get Transfer License Total Number of Users Label
    public String getTransferTotalNumberofUsersLabel(){
        return getElement( transferTotalNumberofUsersLabel ).getText();
    }

    //Get Transfer License Total Number of Users to Transfer Label
    public String getTransferNumberOfUserstoTransferLabel(){
        return getElement( transferNumberOfUserstoTransferLabel ).getText();
    }

    //Get Transfer License Total Number of Users Mandatory Mark
    public String getTransferNumberOfUsersMandatoryMark(){
        return getElement( transferNumberOfUsersMandatoryMark ).getText();
    }

    //Get Transfer License Number of Users Text Box
    public WebElement getTransferNumberOfUsersTextBox(){
        return getElement( transferNumberOfUsersTextBox );
    }

    //Get Transfer License Note
    public String getTransferNote(){
        return getElement( transferNote ).getText();
    }

    //Get Transfer From Label
    public String getTransferFromLabel(){
        return getElement( transferFromLabel ).getText();
    }

    //Get Transfer To Label
    public String getTransferToLabel(){
        return getElement( transferToLabel ).getText();
    }

    //Get Transfer Org Drop-Down
    public WebElement getTransferOrgSelectDropDown(){
        return getElement( transferOrgSelectDropDown );
    }

    //Get Transfer License Ok Button
    public WebElement getTransferOkBtn(){
        return getElement( transferOkBtn );
    }

    //Get Cancel button
    public WebElement getTransferCancelBtn(){
        return getElement( transferCancelBtn );

    }

    //Click Cancel button
    public Map<String, String> clickCancelButton() {
        return getPageDetailsAfterClick( getTransferCancelBtn() );
    }

    //Get Transfer value from Dialog
    public String getTransferExpDateValueFromDialog(){
        return getElement(transferExpDateValueFromDialog).getText();
    }

    //Get Transfer Users Value From Dialog
    public String getTotalNumberOfUsersFromDialog(){
        return getElement(totalNumberOfUsersFromDialog).getText();
    }

    //Get Transfer From Value From Dialog
    public String getTransferFromValueFromDialog(){
        return getElement(transferFromValueFromDialog).getText();
    }

    public List<String> getTransferToDropdownValues() {
        return getDropDownValues( getElement( transferOrgSelectDropDown ) );
    }

    public Map<String, String> launchTransferHelp() {
        getTransferLicenseHelpBtn().click();
        CommonHelper.nap();
        return getNewPageDetails();
    }
    //Get OK Alert button
    public WebElement getPopUpOkBtn(){
        return getElement(alertOkBtn);
    }

    //Get Pop up message from the Alert
    public WebElement getPopUpMsg(){
        return getElement(popUpMsg);
    }
    
  //X button to close the Transfer license window
    public WebElement getXButtonWebElement() {
        return getElement( xButton );
    }
}
