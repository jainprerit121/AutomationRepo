package com.pearson.uitest.testobjects.licensepage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
import com.pearson.uitest.pageobjects.AddLicensePage;
import com.pearson.uitest.pageobjects.LicenseLandingPage;
import com.pearson.uitest.testobjects.AdminBaseTest;

/**
 * Tests for verification of the Add License Page elements
 */

public class SystemAdminAddLicensePageTest extends AdminBaseTest {

    public String getLicenseKey = "";
    String[] licenseKeyArr;

    public SystemAdminAddLicensePageTest() {
        log = Logger.getLogger( SystemAdminAddLicensePageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        super.initialize( browser );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME );
        orgHelper.createOrganization( UsersConstants.USER_ORG_NAME, UsersConstants.USER_ORG_ID );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME2 );
        orgHelper.createOrganization( UsersConstants.USER_ORG_NAME2, UsersConstants.USER_ORG_ID2 );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME3_WITHOUT_LICENSE );
        orgHelper.createOrganization( UsersConstants.USER_ORG_NAME3_WITHOUT_LICENSE, UsersConstants.USER_ORG_ID3 );  
        page = new AddLicensePage( driver );
    }

    @Test ( priority = 301 )
    public void selectOrgValueFromDropDown() {
        page.getLicenseTabBtn().click();
        Select val = new Select( ( (LicenseLandingPage) page ).getLicenseDisplayDropdown() );
        val.selectByVisibleText( UsersConstants.USER_ORG_NAME );
        String getval = val.getFirstSelectedOption().getText();
        Assert.assertEquals( getval, UsersConstants.USER_ORG_NAME );
        CommonHelper.nap();
    }

    @Test ( priority = 302 )
    public void clickOnAddLicenceButton() {
        Assert.assertTrue( ( (LicenseLandingPage) page ).getAddLicenseBtn().isEnabled(), "Add License button is disabled" );
        ( (LicenseLandingPage) page ).getAddLicenseBtn().click();
    }

    @Test( priority = 303 )
    public void verifyAndClickXButton(){

        Assert.assertNotNull( ( (AddLicensePage) page ).getXButtonWebElement() ,"X button is not displayed for Add licnese pop up");
        log.info( "X button is displayed for Add License Pop Up" );
        ( (AddLicensePage) page ).getXButtonWebElement().click();

        //License Landing page verification
        Assert.assertEquals( ( (LicenseLandingPage) page ).getLicenseLandWelcomeText(), appProperties.getProperty( "LicLandWelcomeText" ),"License Landing page verification failed" );
        log.info( "License Landing page verification successfull" );
        ( (LicenseLandingPage) page ).getAddLicenseBtn().click();
    }

    @Test( priority = 304 )
    public void verifyDefaultValueOfLicenseKeyField(){
        String[] sm = new String[1];
        sm[0] = "SuccessMaker Mathematics"; 

        getLicenseKey = CommonHelper.generateKey(sm,LicenseConstants.SUBSCRIPTION_LICENSE_TYPE);
        setLicenseKey( getLicenseKey );

        ( (AddLicensePage) page ).getAddLicenseCancelBtn().click();
        CommonHelper.nap();    

        ( (LicenseLandingPage) page ).getAddLicenseBtn().click();

        Assert.assertEquals( ( (AddLicensePage) page ).getaddLicenseTextBox().getAttribute( "placeholder" ),  appProperties.getProperty( "AddLicenseTextBoxText" ),"'Enter license key here' text is not displayed in license key field");
        log.info( "'Enter license key here' text is displayed in license key field" );
    }

    @Test( priority = 305 )
    public void verifyErrorPopUpForExpLicenceKey(){
        setLicenseKey( appProperties.getProperty("ExpiredLicense") );
        ( (AddLicensePage) page ).getAddLicenseOkBtn().click();
        CommonHelper.nap();         
        CommonHelper.nap();

        Assert.assertEquals( ( (AddLicensePage) page ).getPopUpMsg().getText().replaceAll( "\n", "" ).replaceAll( "  ", " " ).trim(), appProperties.getProperty( "LicenseAlertText" ) ,"Error pop up message not displayed properly");
        log.info( "Expired License Key Pop-up message text: " + ( (AddLicensePage) page ).getPopUpMsg().getText() );
        ( (AddLicensePage) page ).getPopUpOkBtn().click();

        ( (AddLicensePage) page ).getaddLicenseTextBox().clear();
    }

    @Test ( priority = 306, dataProvider = "checkElementsDataProvider" )
    public void checkAddLicenseElementsTextTest( String actualText, String expectedText, String testDesc ) {
        log.info( "Test for " + testDesc );
        Assert.assertEquals( actualText, expectedText, "Failed in verifying  " + testDesc + ", actual = " + actualText + ", expected = " + expectedText );
    }

    @DataProvider ( name = "checkElementsDataProvider" )
    public Object[][] checkElementsDataProvider() {
        Object[][] inputData = { { ( (AddLicensePage) page ).getAddLicenseBtn().getText(), appProperties.getProperty( "LicLandAddLicenseBtnText" ), "Verified Add License Button Text" },
                { ( (AddLicensePage) page ).getAddLicenseDialogTitle(), appProperties.getProperty( "AddLicenseDialogTitle" ), "Verified Add License Dialog Title" },
                { ( (AddLicensePage) page ).getAddLicenseDialogText(), appProperties.getProperty( "AddLicenseDialogText" ), "Verified Add License Dialog Text" },
                { ( (AddLicensePage) page ).getAddLicenseLabelText(), appProperties.getProperty( "AddLicenseDialogLabel" ), "Verified Add License Label Text" },
                { ( (AddLicensePage) page ).getAddLicenseMandatoryCheck(), appProperties.getProperty( "AddLicenseMandatoryCheck" ), "Verified Add License Mandatory * Mark" },
                { ( (AddLicensePage) page ).getaddLicenseTextBox().getAttribute( "placeholder" ), appProperties.getProperty( "AddLicenseTextBoxText" ), "Verified Add License Text Box Text" } };
        return inputData;
    }

    @Test ( priority = 307, dataProvider = "checkAddLicenseElementsPresenceDataProvider" )
    public void checkAddLicenseElementsPresence(WebElement webEle, String testDesc){
        log.info( "Test for " + testDesc);
        Assert.assertNotNull(webEle, "Failed in verifying " + testDesc );
    }

    @DataProvider ( name = "checkAddLicenseElementsPresenceDataProvider" )
    public Object[][] checkAddLicenseElementsPresenceDataProvider() {

        Object[][] inputData = {
                { ((AddLicensePage) page ).getAddLicenseHelpBtn(), "Presence of Help button on the Add License Dialog window" },
                { ((AddLicensePage) page ).getAddLicenseOkBtn(), "Presence of Ok button on the Add License Dialog window" },
                { ((AddLicensePage) page ).getAddLicenseCancelBtn(), "Presence of Cancel button on the Add License Dialog window" },
                { ((AddLicensePage) page).getaddLicenseTextBox(), "Presence of Add License Text box on the Add License Dialog window" },
        };
        return inputData;         
    }    

    @Test ( priority = 308 )
    public void checkAlertMessageWithoutProvidingLicenseKey() {
        ( (AddLicensePage) page ).getAddLicenseOkBtn().click();
        CommonHelper.nap();
        Assert.assertEquals( ( (AddLicensePage) page ).getPopUpMsg().getText().replaceAll( "\n", "" ).replaceAll( "  ", " " ).trim(), appProperties.getProperty( "LicenseAlertText" ) );
        log.info( "ADD License Pop-up message text: " + ( (AddLicensePage) page ).getPopUpMsg().getText() );
        CommonHelper.nap();
        ( (AddLicensePage) page ).getPopUpOkBtn().click();
        CommonHelper.nap();
    }

    @Test ( priority = 309, dataProvider = "generateValidLicenseKeysDataProvider" )
    public void addAndVerifyValidLicenseKeys(String licenseKey, String expectedExpDate, String licenseName){
        int intSeatsColumnNo = 0;
        int intLicenseColumnNo = 0;
        int intLicenseTableSize = 0;
        int intExpDateColumnNo=0;
        int intInUseColumnNo=0;
        int intDenialsColumnNo=0;
        String actualExpDate="";
        String licenseInUseActual="";
        String licenseDenialsActual="";
        List<String> tableLicenseExpDate= new ArrayList<String>();
        List<String> tableLicenseInUse= new ArrayList<String>();
        List<String> tableLicenseDenails = new ArrayList<String>();
        String val = "";
        
        addLicenseKeyCheck(licenseKey);
      
        CommonHelper.nap();
        CommonHelper.nap();
        CommonHelper.nap();
        CommonHelper.nap();
        //Identify the Seats Column number        
        List<WebElement> lstWe = ( (LicenseLandingPage) page ).getLicenseTableHeaderWebElements();
        intSeatsColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "SeatsColumnHeader" ) );
        intLicenseColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "LicenseColumnHeader" ) );
        intLicenseTableSize = ( (LicenseLandingPage) page ).getLicenseTableSize();
        List<String> tableLicenseName = ( (LicenseLandingPage) page ).getTableLicenseName( intLicenseColumnNo );
        List <String> tableLicenseNoOfSeats = ( (LicenseLandingPage) page ).getTableLicenseNoOfSeats( intSeatsColumnNo );        

        intExpDateColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "ExpDateColumnHeader" ) );
        tableLicenseExpDate = ( (LicenseLandingPage) page ).getTableLicenseExpDate( intExpDateColumnNo );
        actualExpDate = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseExpDate, licenseName );

        intInUseColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "InUseColumnHeader" ) );
        tableLicenseInUse = ( (LicenseLandingPage) page ).getTableLicenseInUse( intInUseColumnNo );
        licenseInUseActual = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseInUse, licenseName );

        intDenialsColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "DenialsHeaderColumn" ) );
        tableLicenseDenails = ( (LicenseLandingPage) page ).getTableLicenseInUse( intDenialsColumnNo );
        licenseDenialsActual = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseDenails, licenseName );
        val = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseNoOfSeats, licenseName );
        
        Assert.assertEquals( actualExpDate, expectedExpDate ,"Verfication failed for licence expiry date");
        Assert.assertNotEquals( val, LicenseConstants.CONCURRENT_USER, "Number of licenses added do not match" );
        Assert.assertEquals( licenseInUseActual, LicenseConstants.LICENSE_IN_USE ,"Verfication failed for licence in use details");
        Assert.assertEquals( licenseDenialsActual, LicenseConstants.LICENSE_DENIALS ,"Verfication failed for licence denials");

    }

    @DataProvider (name = "generateValidLicenseKeysDataProvider")
    public Object[][] checkGenerateValidLicenseKeysDataProvider(){
        String[] flex = new String[2];
        flex[0] = "SuccessMaker Mathematics";
        flex[1] = "SuccessMaker Language Arts";
        String[] sm = new String[1];  
        sm[0] = "SuccessMaker Mathematics";   
        String[] sla = new String[1];
        sla[0] = "SuccessMaker Language Arts";

        String expiryDate = CommonHelper.getExpirationDate();

        //Generating Licence key for each combo
        String flexExpLicense = CommonHelper.generateKey(flex,LicenseConstants.SUBSCRIPTION_LICENSE_TYPE);
        log.info("Flexible License Key for Subscription type: " + flexExpLicense);   

        String smExpLicense = CommonHelper.generateKey(sm,LicenseConstants.SUBSCRIPTION_LICENSE_TYPE);
        log.info("Mathematics License Key for Subscription type: " + smExpLicense);   

        String slaExpLicense = CommonHelper.generateKey(sla,LicenseConstants.SUBSCRIPTION_LICENSE_TYPE);
        log.info("Language Arts License Key for Subscription type: " + slaExpLicense);   

        String flexPerpLicense = CommonHelper.generateKey(flex,LicenseConstants.EXP_TYPE_NONE);
        log.info("Flexible License Key for Perpetual type: " + flexPerpLicense);   

        String smPerpLicense = CommonHelper.generateKey(sm,LicenseConstants.EXP_TYPE_NONE);
        log.info("Mathematics License Key for Perpetual type: " + smPerpLicense);   

        String slaPerpLicense = CommonHelper.generateKey(sla,LicenseConstants.EXP_TYPE_NONE);
        log.info("Language Arts License Key for Perpetual type: " + slaPerpLicense);
        getLicenseKey = smExpLicense;

        Object[][] inputData = {
                {flexExpLicense,expiryDate , LicenseConstants.LICENSE_FLEX1_SUBSCRIPTION},
                {smExpLicense,expiryDate,  LicenseConstants.LICENSE_MATHS1_SUBSCRIPTION},   
                {slaExpLicense, expiryDate, LicenseConstants.TRANSFER_SLA_SUBSCRIPTION},
                {flexPerpLicense, LicenseConstants.PERPETUAL_LICENSE_EXP_DATE, LicenseConstants.LICENSE_ORG_FLEX2_PERPETUAL},
                {smPerpLicense, LicenseConstants.PERPETUAL_LICENSE_EXP_DATE ,LicenseConstants.LICENSE_ORG_MATHS2_PERPETUAL},  
                {slaPerpLicense, LicenseConstants.PERPETUAL_LICENSE_EXP_DATE,LicenseConstants.TRANSFER_SLA_PERPETUAL}
        };
        return inputData;
    }

    @Test ( priority = 310 )
    public void checkAddingOfSameLicenseKeyToSameOrg() {
        ( (LicenseLandingPage) page ).getAddLicenseBtn().click();
        ( (AddLicensePage) page ).getaddLicenseTextBox().sendKeys( getLicenseKey );
        CommonHelper.nap();
        ( (AddLicensePage) page ).getAddLicenseOkBtn().click();
        CommonHelper.nap();
        CommonHelper.nap();
        Assert.assertEquals( ( (AddLicensePage) page ).getPopUpMsg().getText().replaceAll( "\n", "" ).replaceAll( "  ", " " ).trim(), appProperties.getProperty( "DuplicateLicenseAlertText" ) );
        log.info( "Duplicate License Key Pop-up message text: " + ( (AddLicensePage) page ).getPopUpMsg().getText() );
        ( (AddLicensePage) page ).getPopUpOkBtn().click();
        ( (AddLicensePage) page ).getAddLicenseCancelBtn().click();
    }
    
    @Test (priority = 311)
    public void checkHyphenAgainstExpiredLicense(){
        int intLicenseTableSize=0;
        int intLicenseColumnNo=0;
        int intOptionsColumnNo=0;
        String val="";
        LicenseSqlHelper.updateLicenseKeyWithExpiredDate( getLicenseKey );
        page.clickHomeTabBtn();
        CommonHelper.nap();
        page.clickLicTabBtn();
        CommonHelper.nap();
        CommonHelper.selectDropDownValueByVisibleText( ((LicenseLandingPage) page).getLicenseDisplayDropdown(), UsersConstants.USER_ORG_NAME );
        CommonHelper.nap();
        
        List<WebElement> lstWe = ( (LicenseLandingPage) page ).getLicenseTableHeaderWebElements();        
        intLicenseColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "LicenseColumnHeader" ) );
        intOptionsColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "OptionsColumnHeader" ) );
        intLicenseTableSize = ( (LicenseLandingPage) page ).getLicenseTableSize();
        List<String> tableLicenseName = ( (LicenseLandingPage) page ).getTableLicenseName( intLicenseColumnNo );
        List <String> tableLicenseOptions = ( (LicenseLandingPage) page ).getTableLicenseNoOfSeats( intOptionsColumnNo );
        val = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseOptions, LicenseConstants.LICENSE_MATHS1_SUBSCRIPTION );
        log.info( "***Options Value for expired license***" + val + "***");
        Assert.assertEquals( val.trim(), appProperties.getProperty( "HyphenCheck" ),"'-' is not displayed for expired license" );
    }

    @Test ( priority = 312 )
    public void checkURLPostAddLicensePageHelpButtonClick() {
        ( (LicenseLandingPage) page ).getAddLicenseBtn().click();
        Map<String, String> actualPageDetails = ( (AddLicensePage) page ).launchAddLicenseHelp();
        CommonHelper.nap();
        CommonHelper.nap();     
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AddLicenseHelpTitle" ), appProperties.getProperty( "AddLicHelpUrl" ) );
        CommonHelper.nap();
        ( (AddLicensePage) page ).getAddLicenseCancelBtn().click();
        CommonHelper.nap();
    }

    @Test (priority = 313)
    public void checkLogoutFromLicensePage() {
        loginPage.logoutAdmin();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ), "User has successfully logged out" );
    }

    private void addLicenseKeyCheck(String keyArr){
        setLicenseKey( keyArr );
        ( (AddLicensePage) page ).getAddLicenseOkBtn().click();
        CommonHelper.nap();           

    }

    private void setLicenseKey(String keyArr){

        if( !( (AddLicensePage) page ).getAddLicenseDialogTitleWebElement().isDisplayed()){
            ( (LicenseLandingPage) page ).getAddLicenseBtn().click();      
        }
        CommonHelper.nap();
        ( (AddLicensePage) page ).getaddLicenseTextBox().sendKeys( keyArr );
        CommonHelper.nap();
    }
}
