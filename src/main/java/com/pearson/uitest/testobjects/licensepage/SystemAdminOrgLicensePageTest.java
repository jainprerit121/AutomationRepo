package com.pearson.uitest.testobjects.licensepage;

import java.util.ArrayList;
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
import com.pearson.uitest.database.LicenseSqlHelper;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.HomePage;
import com.pearson.uitest.pageobjects.LicenseLandingPage;
import com.pearson.uitest.pageobjects.LicenseOrgPage;
import com.pearson.uitest.pageobjects.TransferLicensePage;


/**
 * Tests for the verification of Licenses wrt Organization tab for SystemAdmin
 */

public class SystemAdminOrgLicensePageTest extends TransferBasePageTest {
    public List<String> orgNamesFromTransferToDropdown = new ArrayList<String>();
    public String usersAfterTransfer="";
    public int intSeatsColumnNo;
    public int intLicenseColumnNo;
    public int intLicenseTableSize;

    public SystemAdminOrgLicensePageTest(){
        log = Logger.getLogger( SystemAdminOrgLicensePageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        super.initialize( browser );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        page = new LicenseOrgPage( driver );
    }

    @Test (priority = 501)
    public void clickLicenseTabViaOrgPage(){
        page.getOrgTabBtn().click();
        ((LicenseOrgPage) page).selOrgPageOrgFromDropdown( UsersConstants.USER_ORG_NAME2 );
        ((LicenseOrgPage) page).getOrgName().click();
        log.info("Clicked on organization link");        
        CommonHelper.nap();
        ((LicenseOrgPage) page).getLicenseTabViaOrg().click();
        CommonHelper.nap();
        Assert.assertTrue( ((LicenseOrgPage) page).getLicenseTabActiveStatus().isEnabled(), "License Tab is inactive");
    }

    @Test (priority = 502)
    public void checkExitEditOrganizationButtonPresence(){
        Assert.assertNotNull( ((LicenseOrgPage) page ).getExitEditOrganizationButton(), "Exit Edit Organization button is not present");
    }

    @Test ( priority = 503, dataProvider = "checkTransferElementsDataProvider" )
    public void checkTransferLicenseElementsTextTest(String actualText, String expectedText, String testDesc) {
        log.info( "Test for " + testDesc);
        Assert.assertEquals( actualText, expectedText, "Failed in verifying  " + testDesc + ", actual = " + actualText + ", expected = " + expectedText );
    }

    @DataProvider ( name = "checkTransferElementsDataProvider" )
    public Object[][] checkTransferElementsDataProvider() {     
        ((TransferLicensePage) page).getTransferLink(LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION).click(); 
        
        Object[][] inputData = {
                { ((TransferLicensePage) page).getTransferLink(LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION).getText(), appProperties.getProperty( "TransferLink" ), "Verified Transfer Link Text" },
                { ((TransferLicensePage) page).getTransferLicenseDialogTitle(), appProperties.getProperty( "TransferLicenseDialogTitle" ), "Verified  Transfer License Dialog Title" },
                { ((TransferLicensePage) page).getTransferLicenseExpDateLabel(), appProperties.getProperty( "TransferLicenseExpDateLabel" ), "Verified Transfer License Exp Date label"  },
                { ((TransferLicensePage) page).getTransferTotalNumberofUsersLabel(), appProperties.getProperty( "TransferTotalNumberofUsersLabel" ), "Verified Total Number of Users label"},
                { ((TransferLicensePage) page).getTransferNumberOfUserstoTransferLabel(), appProperties.getProperty( "TransferNumberOfUserstoTransferLabel" ), "Verified Total Number of Users to be transferred label" },
                { ((TransferLicensePage) page).getTransferNumberOfUsersMandatoryMark(), appProperties.getProperty( "TransferNumberOfUsersMandatoryMark" ), "Verified Mandatory * mark "},      
                { ((TransferLicensePage) page).getTransferNote(), appProperties.getProperty( "TransferNote" ), "Verified Transfer Note"},
                { ((TransferLicensePage) page).getTransferFromLabel(), appProperties.getProperty( "TransferFromLabel" ), "Verified Transfer From label"},
                { ((TransferLicensePage) page).getTransferToLabel(), appProperties.getProperty( "TransferToLabel" ), "Verified Transfer To label"},
                { CommonHelper.getFirstSelectedOptionFromDropDown( ((TransferLicensePage) page).getTransferOrgSelectDropDown()), appProperties.getProperty( "DefaultTransferToSelection" ), "Verified Transfer To Default selected Text drop-down value"},
                { ((TransferLicensePage) page).getTransferLicenseDialogText(), LicenseConstants.TRANSFER_LICENSE_TITLE+LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION, "Verification for Transfer License title"},
                { ((TransferLicensePage) page).getTransferFromValueFromDialog(), LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION, "Verification for prepopulated Transfer from"},
        };
        return inputData;         
    }
    
    @Test (priority = 504)
    public void checkOrgNameNotInTransferToDropdown(){
        checkSourceOrgNameNotInTransferToDropdown( UsersConstants.USER_ORG_NAME2 );
    }
    
    @Test ( priority=505 )
    public void verifyTransferToOrgList(){
        List<String> orgNamesActual = ((TransferLicensePage) page).getTransferToDropdownValues();
        orgNamesActual.remove( appProperties.getProperty( "DefaultTransferToSelection" ) );
        List<String> expectedOrgList = orgNamesActual;
        CommonHelper.sortAscendingWithIgnoreCase( expectedOrgList );
        Assert.assertTrue( CommonHelper.compareLists( orgNamesActual, expectedOrgList ) ,"Organization list is not sorted alphanumerically");
    }

    @Test ( priority = 506, dataProvider = "checkTransferLicenseElementsPresenceDataProvider" )
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
    
    //@Test (priority = 507)
    public void checkCancelButtonClick() {      
        Map<String, String> actualPageDetails = ((TransferLicensePage) page).clickCancelButton();
        CommonHelper.nap();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LicPageUrl" ) );
        CommonHelper.nap();
        ((TransferLicensePage) page).getTransferLink(LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION).click(); 
    }
    
    @Test (priority = 508)
    public void checkTransferWithoutOrgSelect(){
        checkTransferErrorPopUpMsg("TransferPopUpMsgWithoutOrgSelect");
        Assert.assertTrue( ((TransferLicensePage) page).getTransferLicenseDialogTitleWebElement().isDisplayed(), "Transfer license window is not displayed after clicking on Ok button under Enter a valid number of users to transfer pop-up");
        CommonHelper.nap();
    }
    
    @Test (priority = 509)
    public void checkTransferToExceedValueInput(){
          checkTransferToExceedValue();
    }
    
    @Test (priority = 510)
    public void checkTransferToNonNumericValueInput(){
       checkTransferToNonNumericValue();
    }
    
    @Test (priority = 511)
    public void checkDefaultTransferToOrgSelectValue(){     
        String defaultDropDownValue = CommonHelper.getFirstSelectedOptionFromDropDown( ((TransferLicensePage) page).getTransferOrgSelectDropDown() );
        Assert.assertEquals(defaultDropDownValue, appProperties.getProperty( "DefaultTransferToSelection" ), appProperties.getProperty( "DefaultTransferToSelection" ) + " is not displayed as default value for Transfer To drop-down under Transfer Dialog");
    }
    
    
    @Test (priority = 512)
    public void checkTransferWithoutAnyUserValue(){
        ((TransferLicensePage) page).getTransferNumberOfUsersTextBox().clear();
        checkTransferErrorPopUpMsg("TransferPopMsgUpWithoutNoOfUsers");
        Assert.assertTrue( ((TransferLicensePage) page).getTransferLicenseDialogTitleWebElement().isDisplayed(), "Transfer license window is not displayed after clicking on Ok button under Enter a valid number of users to transfer pop-up");
        CommonHelper.nap();     
    }
    
   // @Test( priority = 513 )
    public void verifyAndClickXButton(){

        Assert.assertNotNull( ((TransferLicensePage) page).getXButtonWebElement() ,"X button is not displayed for Transfer licnese pop up");
        log.info( "X button is displayed for Transfer License Pop Up" );
        ((TransferLicensePage) page).getXButtonWebElement().click();

        //Transfer License page verification
        Assert.assertTrue( ((TransferLicensePage) page).getTransferLicenseDialogTitleWebElement().isDisplayed(), "Transfer license window is not displayed after clicking on Ok button under Enter a valid number of users to transfer pop-up");
        CommonHelper.nap();
        
        ((TransferLicensePage) page).getTransferLink(LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION).click();
    }

    @Test (priority = 514)
    public void checkURLPostLicensePageHelpButtonClick(){
        ((TransferLicensePage) page).getTransferLink(LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION).click();
        CommonHelper.nap();    
        Map<String, String> actualPageDetails = ((TransferLicensePage) page).launchTransferHelp();
        CommonHelper.nap();
        CommonHelper.nap();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "TransferHelpTitle" ), appProperties.getProperty( "TransferHelpURL" ) );
        CommonHelper.nap();     
    }

    @Test (priority = 515)
    public void checkTransferFromOrgPage(){   
        
        transferLicense( LicenseConstants.TRANSFER_VALUE, UsersConstants.USER_ORG_NAME );
        CommonHelper.nap();
        ((LicenseOrgPage) page).getTransferLink(LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION).click();
        transferLicense( LicenseConstants.TRANSFER_VALUE, UsersConstants.USER_ORG_NAME );
        
        String seatsVal = "";              
        List<WebElement> lstWe = ((LicenseLandingPage)page).getLicenseTableHeaderWebElements();
        intSeatsColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "SeatsColumnHeader" ) );
        intLicenseColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "LicenseColumnHeader" ) );
        intLicenseTableSize = ((LicenseLandingPage)page).getLicenseTableSize();
        List<String> tableLicenseName = ((LicenseLandingPage)page).getTableLicenseName( intLicenseColumnNo );
        List <String> tableLicenseNoOfSeats = ((LicenseLandingPage)page).getTableLicenseNoOfSeats( intSeatsColumnNo );
        seatsVal = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseNoOfSeats, LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION );
        
        usersAfterTransfer = String.valueOf(LicenseConstants.TRANSFER_VALUE - LicenseConstants.TRANSFER_VALUE);
        Assert.assertEquals( seatsVal.toString(), usersAfterTransfer, "The selected users have not been deducted from the new organization" );
        log.info( "Total Number of users before transfer: " + LicenseConstants.TRANSFER_VALUE*2 );
        log.info( "Number of users after transfer: " + usersAfterTransfer );
        page.getOrgTabBtn().click();
        ((LicenseOrgPage) page).selOrgPageOrgFromDropdown( UsersConstants.USER_ORG_NAME );
        ((LicenseOrgPage) page).getOrgName().click();
        log.info("Clicked on organization link");        
        CommonHelper.nap();
        ((LicenseOrgPage) page).getLicenseTabViaOrg().click();
        CommonHelper.nap();
        CommonHelper.nap();
        intLicenseTableSize = ((LicenseLandingPage)page).getLicenseTableSize();
        tableLicenseName = ((LicenseLandingPage)page).getTableLicenseName( intLicenseColumnNo );
        tableLicenseNoOfSeats = ((LicenseLandingPage)page).getTableLicenseNoOfSeats( intSeatsColumnNo );

        tableLicenseName = ((LicenseLandingPage)page).getTableLicenseName( intLicenseColumnNo );
        String seatsValueforNewOrg = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseNoOfSeats, LicenseConstants.LICENSE_FLEX1_SUBSCRIPTION );
        Assert.assertEquals( seatsValueforNewOrg, String.valueOf( LicenseConstants.CONCURRENT_USER ), "The users are not transferred back to the old organization" );
    }

    @Test (priority = 516)
    public void checkTransferAllSeatsZeroValue(){
        page.getOrgTabBtn().click();
        ((LicenseOrgPage) page).selOrgPageOrgFromDropdown( UsersConstants.USER_ORG_NAME2 );
        ((LicenseOrgPage) page).getOrgName().click();
        CommonHelper.nap();
        ((LicenseOrgPage) page).getLicenseTabViaOrg().click();
        CommonHelper.nap();

        List<WebElement> lstWe = ((LicenseLandingPage)page).getLicenseTableHeaderWebElements();
        intSeatsColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "SeatsColumnHeader" ) );
        intLicenseColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "LicenseColumnHeader" ) );
        intLicenseTableSize = ((LicenseLandingPage)page).getLicenseTableSize();
        List<String> tableLicenseName = ((LicenseLandingPage)page).getTableLicenseName( intLicenseColumnNo );
        List <String> tableLicenseNoOfSeats = ((LicenseLandingPage)page).getTableLicenseNoOfSeats( intSeatsColumnNo );
        String seatsValueforNewOrg = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseNoOfSeats, LicenseConstants.LICENSE_FLEX2_SUBSCRIPTION );
        Assert.assertEquals( seatsValueforNewOrg, usersAfterTransfer, "All users are not transferred from the transferred organization" );
    }

    
    //Commented due to duplicate id's conflict on Exit Edit Organization button under Organization tab
    // @Test (priority = 517)
    public void checkExitEditOrganizationButtonClickManageLink(){
        page.clickHomeTabBtn();
        log.info( "Clicked on Home tab" );
        
        log.info( "Selected " + UsersConstants.USER_ORG_NAME +" organization from the drop-down under Home tab" );
        ((HomePage)page).getOrgManageLicLink().click();
        Assert.assertTrue( ((LicenseOrgPage) page).getLicenseTabActiveStatus().isEnabled(), "License Tab is inactive");
        ((LicenseOrgPage) page).getExitEditOrganizationButton().click();
        log.info( "Clicked on Exit Edit Organization button" );
        CommonHelper.nap();
        Assert.assertEquals(((LicenseOrgPage) page).getDisplayOption().getText(), UsersConstants.USER_ORG_NAME,"Unexpected Org is displayed under Org tab when clicked on 'Exit Edit Organization' button from Org -> Licenses tab");        
    }
    
   @Test (priority = 518)
    public void checkExitEditOrganizationButtonClick(){
        page.clickHomeTabBtn();
        log.info( "Clicked on Home tab" );
        page.clickOrgTabBtn();
        
        ((LicenseOrgPage) page).selOrgPageOrgFromDropdown( UsersConstants.USER_ORG_NAME );
        ((LicenseOrgPage) page).getOrgName().click();
        CommonHelper.nap();
        ((LicenseOrgPage) page).getLicenseTabViaOrg().click();
        CommonHelper.nap();
        Assert.assertTrue( ((LicenseOrgPage) page).getLicenseTabActiveStatus().isEnabled(), "License Tab is inactive");
        CommonHelper.nap();
        CommonHelper.nap();
        ((LicenseOrgPage) page).getExitEditOrganizationButton().click();
        log.info( "Clicked on Exit Edit Organization button" );
        CommonHelper.nap();
        Assert.assertEquals(((LicenseOrgPage) page).getDisplayOption().getText(), appProperties.getProperty( "DefaultSelection" ),"Unexpected Org is displayed under Org tab when clicked on 'Exit Edit Organization' button from Org -> Licenses tab");        
    }
    
    @Test(priority=519)
    public void checkPrepopulatedValues(){
        ((LicenseOrgPage) page).selOrgPageOrgFromDropdown( UsersConstants.USER_ORG_NAME );
        ((LicenseOrgPage) page).getOrgName().click();
        CommonHelper.nap();
        ((LicenseOrgPage) page).getLicenseTabViaOrg().click();
        CommonHelper.nap();
        Assert.assertTrue( ((LicenseOrgPage) page).getLicenseTabActiveStatus().isEnabled(), "License Tab is inactive");
        verifyPrepopulatedTextOnTransferLincensePage();
    }
    
    @Test( priority = 520 , dataProvider="checkNumberOfSeatsCanBeEnteredUpToMaxLimitDataProvider")
    public void checkNumberOfSeatsCanBeEnteredUpToMaxLimit(int noOfUsers){
        CommonHelper.nap();
        ((TransferLicensePage) page).getTransferNumberOfUsersTextBox().clear();
        ((TransferLicensePage) page).getTransferNumberOfUsersTextBox().sendKeys(String.valueOf(noOfUsers));
        CommonHelper.nap();
        Assert.assertEquals( ((TransferLicensePage) page).getTransferNumberOfUsersTextBox().getAttribute( "value" ), String.valueOf( noOfUsers ));
    }
    
    @DataProvider ( name = "checkNumberOfSeatsCanBeEnteredUpToMaxLimitDataProvider" )
    public Object[][] checkNumberOfSeatsCanBeEnteredUpToMaxLimitDataProvider() {    
        Object[][] inputData = {{LicenseConstants.LICENSE_USER_MIN_VALUE},{LicenseConstants.TRANSFER_VALUE},{LicenseConstants.CONCURRENT_USER}};
        return inputData;
    }
    
    @Test (priority = 521)
    public void checkOrgSpecificDetails(){
        ((TransferLicensePage) page).clickCancelButton();
        page.clickHomeTabBtn();
        log.info( "Clicked on Home tab" );
        page.clickOrgTabBtn();
        
        ((LicenseOrgPage) page).selOrgPageOrgFromDropdown( UsersConstants.USER_ORG_NAME );
        ((LicenseOrgPage) page).getOrgName().click();
        CommonHelper.nap();
        ((LicenseOrgPage) page).getLicenseTabViaOrg().click();
        CommonHelper.nap();
        Assert.assertTrue( ((LicenseOrgPage) page).getLicenseTabActiveStatus().isEnabled(), "License Tab is inactive");
        
        List<WebElement> lstWe = ( (LicenseLandingPage) page ).getLicenseTableHeaderWebElements();
        intLicenseColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "LicenseColumnHeader" ) );
        
        List<Object[]> orgSpecificDetails = new ArrayList<Object[]>();
        orgSpecificDetails = LicenseSqlHelper.getSchoolSpecificData( UsersConstants.USER_ORG_NAME );
        
        for(Object[] orgArr:orgSpecificDetails){
           
            String licenseName = (String) orgArr[0];
            CommonHelper.nap();
            if(!licenseName.contains( "Mathematics" )){
                Assert.assertNotNull( ( (LicenseLandingPage) page ).getTableLicenseNameElement( "contains(.,'"+licenseName+"')" ),licenseName+" License not displayed for "+ UsersConstants.USER_ORG_NAME);
            }else{
                Assert.assertNotNull( ( (LicenseLandingPage) page ).getTableLicenseNameElement( "contains(.,'"+licenseName.split( "-" )[0]+"')" ),licenseName+" License not displayed for "+ UsersConstants.USER_ORG_NAME);
            }
            log.info( licenseName +" License name displayed for "+ UsersConstants.USER_ORG_NAME);
        }           
        
    }

    @Test (priority = 522) 
    public void checkLogoutFromLicenseOrgPage() {
        loginPage.logoutAdmin();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ), "User has successfully logged out" );
    }

}
