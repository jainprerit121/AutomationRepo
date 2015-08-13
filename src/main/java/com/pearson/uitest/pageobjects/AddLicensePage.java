package com.pearson.uitest.pageobjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;
import com.pearson.uitest.helper.CommonHelper;

/**
 * All the UI elements wrt to Add Licenses page are located here
 */

public class AddLicensePage extends LicenseLandingPage{

	private By addLicenseDialogTitle = By.xpath("//*[@id='confirmBoxHeaderTitle'][normalize-space(text())='License Key']");
	private By addLicenseDialogText = By.id("addLsTitleText");
	private By addLicenseHelpBtn = By.id("lsAddLsgHelpBtn");
	private By addLicenseLabelText = By.id("addLsKeyLabel");
	private By addLicenseMandatoryCheck = By.id("addOrgIdSpan");
	private By addLicenseOkBtn = By.id("addLsBtns0");
	private By addLicenseCancelBtn = By.id("addLsBtns1");
	private By addLicenseTextBox = By.id("addLicenseKeyInput");
	private By alertOkBtn = By.id("okBtn");
	private By popUpMsg = By.id("popUpMsg");
	private By xButton = By.id( "confirmBoxHeaderCloseImg" );
	
	public AddLicensePage(WebDriver driver) {
		super(driver);
		log=Logger.getLogger( AddLicensePage.class );
	}
	
	//Get License Dialog title
    public String getAddLicenseDialogTitle() {
        return getElement( addLicenseDialogTitle ).getText();
    }
    
 //Get License Dialog web element
    public WebElement getAddLicenseDialogTitleWebElement() {
        return getElement( addLicenseDialogTitle );
    }

    //Get License Dialog text
    public String getAddLicenseDialogText() {
        return getElement( addLicenseDialogText ).getText();
    }
    
    // Get Add License Help button
    public WebElement getAddLicenseHelpBtn() {
        return getElement( addLicenseHelpBtn );
    }
    
    // Get Add License Label Text
    public String getAddLicenseLabelText() {
    	return getElement( addLicenseLabelText ).getText();
    }
    
   // Get Add License Mandatory Check 
    public String getAddLicenseMandatoryCheck() {
    	return getElement( addLicenseMandatoryCheck ).getText();
    }
    
    // Get Add License Ok button
    public WebElement getAddLicenseOkBtn(){
    	return getElement( addLicenseOkBtn );
    }
    
    // Get Add License Cancel button
    public WebElement getAddLicenseCancelBtn(){
    	return getElement( addLicenseCancelBtn );
    }
    
    // Get Add License Textbox text
    public WebElement getaddLicenseTextBox(){
    	return getElement( addLicenseTextBox );
    }
    
    //Get OK Alert button
    public WebElement getPopUpOkBtn(){
    	return getElement(alertOkBtn);
    }
    
  //Get Pop up message from the Alert
    public WebElement getPopUpMsg(){
    	return getElement(popUpMsg);
    }
    
    //X button to close the add license window
    public WebElement getXButtonWebElement() {
        return getElement( xButton );
    }
    
    public Map<String, String> launchAddLicenseHelp() {
        getAddLicenseHelpBtn().click();
        CommonHelper.nap();
        return getNewPageDetails();
    }   
    
}
