package com.pearson.uitest.testobjects.licensepage;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.LicenseConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.LicenseBasePage;
import com.pearson.uitest.pageobjects.LicenseLandingPage;
import com.pearson.uitest.pageobjects.TransferLicensePage;

/**
 * Tests for verification of the Transfer License Page elements
 */

public class SystemAdminTransferLicensePageTest extends TransferBasePageTest {
    private String usersAfterTransfer="";
    
	public SystemAdminTransferLicensePageTest() {
        log = Logger.getLogger( SystemAdminTransferLicensePageTest.class );
    }
	
	@BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        super.initialize( browser );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        page = new TransferLicensePage( driver );
        ((LicenseBasePage) page).clickLicTabBtn();        
    }
		
	@Test ( priority = 401 )
    public void selectOrgValueFromDropDown(){
		page.clickLicTabBtn();
		CommonHelper.nap();
		CommonHelper.nap();
		CommonHelper.selectDropDownValueByVisibleText( ((LicenseLandingPage) page).getLicenseDisplayDropdown(), UsersConstants.USER_ORG_NAME );
    	String getval = CommonHelper.getFirstSelectedOptionFromDropDown(((LicenseLandingPage) page).getLicenseDisplayDropdown());    	
    	Assert.assertEquals(getval, UsersConstants.USER_ORG_NAME);
    	CommonHelper.nap();
    }
		
	@Test ( priority = 402, dataProvider = "checkTransferElementsDataProvider" )
	public void checkTransferLicenseElementsTextTest(String actualText, String expectedText, String testDesc) {
	    log.info( "Test for " + testDesc);
	    Assert.assertEquals( actualText, expectedText, "Failed in verifying  " + testDesc + ", actual = " + actualText + ", expected = " + expectedText );
	}
	 
	@DataProvider ( name = "checkTransferElementsDataProvider" )
	public Object[][] checkTransferElementsDataProvider() {		
		((TransferLicensePage) page).getTransferLink(LicenseConstants.LICENSE_FLEX1_SUBSCRIPTION).click();		
	    Object[][] inputData = {
	    { ( (TransferLicensePage) page ).getTransferLink(LicenseConstants.LICENSE_FLEX1_SUBSCRIPTION).getText(), appProperties.getProperty( "TransferLink" ), "Verified Transfer Link Text" },
	    { ( (TransferLicensePage) page ).getTransferLicenseDialogTitle(), appProperties.getProperty( "TransferLicenseDialogTitle" ), "Verified  Transfer License Dialog Title" },
	    { ( (TransferLicensePage) page ).getTransferLicenseExpDateLabel(), appProperties.getProperty( "TransferLicenseExpDateLabel" ), "Verified Transfer License Exp Date label"  },
        { ( (TransferLicensePage) page ).getTransferTotalNumberofUsersLabel(), appProperties.getProperty( "TransferTotalNumberofUsersLabel" ), "Verified Total Number of Users label"},
        { ( (TransferLicensePage) page ).getTransferNumberOfUserstoTransferLabel(), appProperties.getProperty( "TransferNumberOfUserstoTransferLabel" ), "Verified Total Number of Users to be transferred label" },
        { ( (TransferLicensePage) page ).getTransferNumberOfUsersMandatoryMark(), appProperties.getProperty( "TransferNumberOfUsersMandatoryMark" ), "Verified Mandatory * mark "},      
        { ( (TransferLicensePage) page ).getTransferNote(), appProperties.getProperty( "TransferNote" ), "Verified Transfer Note"},
        { ( (TransferLicensePage) page ).getTransferFromLabel(), appProperties.getProperty( "TransferFromLabel" ), "Verified Transfer From label"},
        { ( (TransferLicensePage) page ).getTransferToLabel(), appProperties.getProperty( "TransferToLabel" ), "Verified Transfer To label"},
        { CommonHelper.getFirstSelectedOptionFromDropDown( ((TransferLicensePage) page ).getTransferOrgSelectDropDown()), appProperties.getProperty( "DefaultTransferToSelection" ), "Verified Transfer To Default selected Text drop-down value"},
	    };
	     return inputData;         
	  }
	
	@Test ( priority = 403, dataProvider = "checkTransferLicenseElementsPresenceDataProvider" )
	public void checkTransferLicenseElementPresence(WebElement webEle, String testDesc){
	    log.info( "Test for " + testDesc);
	    Assert.assertNotNull(webEle, "Failed in verifying " + testDesc );
	}
	
	@DataProvider ( name = "checkTransferLicenseElementsPresenceDataProvider" )
	public Object[][] checkTransferLicenseElementsPresenceDataProvider() {
             
        Object[][] inputData = {
        { ((TransferLicensePage) page).getTransferNumberOfUsersTextBox(), "Presence of Transfer Number of Users Textbox on the Transfer Dialog window" },
        { ((TransferLicensePage) page).getTransferOrgSelectDropDown(), "Presence of Transfer To Drop-down on the Transfer Dialog window" },
        { ((TransferLicensePage) page).getTransferOkBtn(), "Presence of Ok button on the Transfer Dialog window" },
        { ((TransferLicensePage) page).getTransferCancelBtn(), "Presence of Cancel button on the Transfer Dialog window" },
        };
         return inputData;         
      }
	
	@Test (priority = 404)
	public void checkTransferDialogData(){
		String licenseName = ( (TransferLicensePage) page ).getTransferFromValueFromDialog();
		String expDate = ( (TransferLicensePage) page ).getTransferExpDateValueFromDialog();
		String seats = ( (TransferLicensePage) page ).getTotalNumberOfUsersFromDialog();
		String numberOfUsersToTransfer = ( (TransferLicensePage) page ).getTransferNumberOfUsersTextBox().getAttribute("value");		
		Assert.assertEquals(licenseName,((LicenseLandingPage) page).getLicenseNameRowData());
		Assert.assertEquals(expDate,((LicenseLandingPage) page).getExpDateRowData());
		Assert.assertEquals(seats,((LicenseLandingPage) page).getSeatsRowData());		
		Assert.assertEquals(seats,((LicenseLandingPage) page).getSeatsRowData());		
		Assert.assertEquals(numberOfUsersToTransfer,((LicenseLandingPage) page).getSeatsRowData());		
	}	
	
	@Test (priority = 405)
	public void checkOrgNameNotInTransferToDropdown(){
	    
	    checkSourceOrgNameNotInTransferToDropdown( UsersConstants.USER_ORG_NAME );
	}
	
	@Test (priority = 406)
	public void checkDefaultTransferToOrgSelectValue(){	    
	    String defaultDropDownValue = CommonHelper.getFirstSelectedOptionFromDropDown( ((TransferLicensePage) page).getTransferOrgSelectDropDown() );
	    Assert.assertEquals(defaultDropDownValue, appProperties.getProperty( "DefaultTransferToSelection" ), appProperties.getProperty( "DefaultTransferToSelection" ) + " is not displayed as default value for Transfer To drop-down under Transfer Dialog");
	}
	
	@Test (priority = 407)
	public void checkTransferWithoutOrgSelect(){
	    checkTransferErrorPopUpMsg("TransferPopUpMsgWithoutOrgSelect");
	}
	
	@Test (priority = 408)
	public void checkTransferWithoutAnyUserValue(){
		((TransferLicensePage) page).getTransferNumberOfUsersTextBox().clear();
		checkTransferErrorPopUpMsg("TransferPopMsgUpWithoutNoOfUsers");
		Assert.assertTrue( ((TransferLicensePage) page).getTransferLicenseDialogTitleWebElement().isDisplayed(), "Transfer license window is not displayed after clicking on Ok button under Enter a valid number of users to transfer pop-up");
		CommonHelper.nap();		
	}
	
	@Test (priority = 409)
	public void checkTransferToExceedValueInput(){
	    checkTransferToExceedValue();
	}
	
	@Test (priority = 410)
	public void checkTransferToNonNumericValueInput(){
    checkTransferToNonNumericValue();
	}
	
	@Test (priority = 411)
	public void checkOrgNamesAlphanumericallyListedUnderTransferToDropDown(){
	    checkOrgNamesAlphanumericallyListedUnderTransferToDropDown( UsersConstants.USER_ORG_NAME );
	}
	
	@Test (priority = 412)
	public void checkTransferUsersToNewOrg(){	      
	    transferLicense( LicenseConstants.TRANSFER_VALUE, UsersConstants.USER_ORG_NAME2 );
	    checkSourceOrgNameNotInTransferToDropdown( UsersConstants.USER_ORG_NAME );
	    ((TransferLicensePage) page).getTransferLink(LicenseConstants.LICENSE_FLEX1_SUBSCRIPTION).click();
	    transferLicense( LicenseConstants.TRANSFER_VALUE, UsersConstants.USER_ORG_NAME2 );
	    
	    int intSeatsColumnNo = 0;
        int intLicenseColumnNo = 0;
        int intLicenseTableSize = 0;
        String seatsVal = "";
        Assert.assertTrue( ((LicenseLandingPage) page).getLicenseLandWelcomeWebElement().isDisplayed(), "After Transferring the users, License Landing page is not displayed" );
        List<WebElement> lstWe = ((LicenseLandingPage) page).getLicenseTableHeaderWebElements();
        intSeatsColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "SeatsColumnHeader" ) );
        intLicenseColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "LicenseColumnHeader" ) );
        intLicenseTableSize = ((LicenseLandingPage) page).getLicenseTableSize();
        CommonHelper.nap();    
        List<String> tableLicenseName = ((LicenseLandingPage) page).getTableLicenseName( intLicenseColumnNo );
        List <String> tableLicenseNoOfSeats = ((LicenseLandingPage) page).getTableLicenseNoOfSeats( intSeatsColumnNo );
        seatsVal = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseNoOfSeats, LicenseConstants.LICENSE_FLEX1_SUBSCRIPTION );
        usersAfterTransfer = String.valueOf(LicenseConstants.CONCURRENT_USER - LicenseConstants.TRANSFER_VALUE*2);
        Assert.assertEquals( seatsVal.toString(), usersAfterTransfer, "The selected users are not deducted from the old organization as expected" );
        log.info( "Total Number of users before transfer: " + LicenseConstants.CONCURRENT_USER );
        log.info( "Number of users after transfer: " + usersAfterTransfer );
        CommonHelper.selectDropDownValueByVisibleText( ((LicenseLandingPage) page).getLicenseDisplayDropdown(), UsersConstants.USER_ORG_NAME2 );
        CommonHelper.nap();
        intLicenseTableSize = ((LicenseLandingPage) page).getLicenseTableSize();
        CommonHelper.nap();    
        tableLicenseName = ((LicenseLandingPage) page).getTableLicenseName( intLicenseColumnNo );
        tableLicenseNoOfSeats = ((LicenseLandingPage) page).getTableLicenseNoOfSeats( intSeatsColumnNo );
        String seatsValueforNewOrg = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseNoOfSeats, LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION );
        Assert.assertTrue( intLicenseTableSize>0,"No new rows added after transfer in "+UsersConstants.USER_ORG_NAME2 );
        Assert.assertEquals( seatsValueforNewOrg, String.valueOf( LicenseConstants.TRANSFER_VALUE*2 ), "The users have been successfully transferred to the new organization" );
	}
	
	@Test (priority = 413)
    public void checkURLPostLicensePageHelpButtonClick(){
	 ((TransferLicensePage) page).getTransferLink(LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION).click();
     CommonHelper.nap();    
     Map<String, String> actualPageDetails = ( (TransferLicensePage) page ).launchTransferHelp();
     CommonHelper.nap();
     CommonHelper.nap();
     checkPageDetails( actualPageDetails, appProperties.getProperty( "TransferHelpTitle" ), appProperties.getProperty( "TransferHelpURL" ) );
     CommonHelper.nap();     
    }
    	
	@Test (priority = 414)
	public void checkCancelButtonClick() {	    
        Map<String, String> actualPageDetails = ((TransferLicensePage) page).clickCancelButton();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LicPageUrl" ) );
    }
	
	@Test(priority=415)
    public void checkPrepopulatedValues(){
        CommonHelper.selectDropDownValueByVisibleText( ((LicenseLandingPage) page).getLicenseDisplayDropdown(), UsersConstants.USER_ORG_NAME );
        verifyPrepopulatedTextOnTransferLincensePage();
    }
		
	@Test (priority = 416) 
	public void checkLogoutFromTransferPage() {
	 loginPage.logoutAdmin();
	 Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ), "User has successfully logged out" );
	}
		
}
