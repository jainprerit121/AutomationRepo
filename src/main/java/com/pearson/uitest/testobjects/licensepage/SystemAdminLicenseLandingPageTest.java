package com.pearson.uitest.testobjects.licensepage;

import java.util.ArrayList;
import java.util.List;

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
import com.pearson.uitest.constants.LocatorConstants;
import com.pearson.uitest.constants.OrganizationConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.database.LicenseSqlHelper;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.BasePage;
import com.pearson.uitest.pageobjects.HomePage;
import com.pearson.uitest.pageobjects.LicenseBasePage;
import com.pearson.uitest.pageobjects.LicenseLandingPage;
import com.pearson.uitest.pageobjects.LicenseOrgPage;

/**
 * This class contains methods used for the verification of the License Landing
 * Page elements for System Admin user
 */

public class SystemAdminLicenseLandingPageTest extends LicensePageBaseTest {
    public String seatsVar = "Seats";

    public SystemAdminLicenseLandingPageTest() {
        log = Logger.getLogger( SystemAdminLicenseLandingPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        super.initialize( browser );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        page = new LicenseOrgPage( driver );
    }

    @Test ( priority = 201, description = "Entry Criteria: User is in Homepage</br>Execution Criteria: Navigate to License page and highlighting of Licenses tab is verified.</br>Exit Criteria: User is in License landing page" )
    public void checkPageNavigationAndTabHighlight() {
        page.clickLicTabBtn();
        CommonHelper.nap();
        Assert.assertEquals( page.getElementAttribute( page.getLicenseTabBtn(), "class" ), LocatorConstants.HIGHLIGHT_BUTTON_CLASS, "Licenses tab is not highligted." );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 202 )
    public void checkLicenseLandWelcomeText() {
        log.info( "Actual Welcome Text: " + ( (LicenseLandingPage) page ).getLicenseLandWelcomeText() );
        log.info( "Expected Welcome Text: " + appProperties.getProperty( "LicLandWelcomeText" ) );
        Assert.assertEquals( ( (LicenseLandingPage) page ).getLicenseLandWelcomeText(), appProperties.getProperty( "LicLandWelcomeText" ) );
    }

    @Test ( priority = 203 )
    public void checkLicenseLandDisplayDropTitle() {
        Assert.assertEquals( ( (LicenseLandingPage) page ).getLicenseLandDisplayDropTitle(), appProperties.getProperty( "LicLandDisplayLabel" ) );
    }

    @Test ( priority = 204 )
    public void checkLicenseDisplayDropDown() {
        Assert.assertNotNull( ( (LicenseLandingPage) page ).getLicenseDisplayDropdown(), "Organization dropdown list is not present" );
    }

    @Test ( priority = 205 )
    public void checkOrgDropDownValuesDisplayedInAscendingOrder() {
        Assert.assertTrue( ( (LicenseLandingPage) page ).checkLicenseDropDownInAscendingOrder( appProperties.getProperty( "DefaultSelection" ) ), "License dropdown is not in ascending order" );
        log.info( "Verified that License dropdown is in ascending order!" );
    }

    @Test ( priority = 206 )
    public void checkDefaultAllSchoolsSelected() {
        //Assert.assertTrue( ( (OrganizationPage) page ).getDefaultOrgSelection( appProperties.getProperty( "DefaultSelection" ) ) );
        Select val = new Select( ( (LicenseLandingPage) page ).getLicenseDisplayDropdown() );
        String getVal = val.getFirstSelectedOption().getText();
        Assert.assertEquals( getVal, appProperties.getProperty( "DefaultSelection" ) );
        log.info( "Verified --All Schools-- is selected by default under License Org Drop down" );
    }
    
    @Test (priority = 207)
    public void checkAllOrgNamesListedUnderOrgDropDown(){
        log.info( "Verification for checking whether the list of organization names are listed under organization drop-down" );
        List<Object[]> orgNamesFromDB = LicenseSqlHelper.getAllOrgNames();
        List<String> orgNamesFromTransferToDropdown = ((LicenseLandingPage) page).getOrgDropdownValues();
        
        orgNamesFromTransferToDropdown.remove( appProperties.getProperty( "DefaultSelection" ) );
        orgNamesFromTransferToDropdown.remove( appProperties.getProperty( "OrgNameExisting" ) );
        int i=0;
        for(Object[] orgArr:orgNamesFromDB){
            
                Assert.assertEquals(orgNamesFromTransferToDropdown.get( i ),  orgArr[0], "Organization comparison failed");
                log.info("Actual Org: " + orgNamesFromTransferToDropdown.get( i ) + ":: Expected Org: " + orgArr[0]);
                i++; 
        }
    }
    
    @Test( priority=208)
    public void checkColumnsNotSortable(){
        List<WebElement> lstWe = ( (LicenseLandingPage) page ).getLicenseTableHeaderWebElements();
        int inUseColNum = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "InUseColumnHeader" ) );
        int denailsColNum = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "DenialsHeaderColumn" ) );
        int optionsColNum = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "OptionsColumnHeader" ) );
        Assert.assertNull( ((LicenseLandingPage) page ).getTableLicenseInUseElement( inUseColNum ).getAttribute( appProperties.getProperty( "SortClass" ) ),"Unexpected Sort is available for In Use column");
        Assert.assertNull(( (LicenseLandingPage) page ).getTableLicenseDenialsElement( denailsColNum ).getAttribute( appProperties.getProperty( "SortClass" ) ),"Unexpected Sort is available for Denials column");
        Assert.assertNull(( (LicenseLandingPage) page ).getTableLicenseOptionsElement( optionsColNum ).getAttribute( appProperties.getProperty( "SortClass" ) ),"Unexpected Sort is available for Options column");
    }

    @Test ( priority = 209, dataProvider = "checkSortAscendingDataProvider" )
    public void checkSortColumnTest( List<String> actualText, List<String> expectedText, String testDesc ) {
        log.info( "Test for " + testDesc );
        Assert.assertEquals( actualText, expectedText, "Failed in verifying  " + testDesc + ", actual = " + actualText + ", expected = " + expectedText );
    }

    @DataProvider ( name = "checkSortAscendingDataProvider" )
    public Object[][] checkSortAscendingDataProvider() {
        int intSeatsColumnNo = 0;
        int intLicenseColumnNameNo;
        int intLicenseExpDateColumnNo = 0;
        List<WebElement> lstWe = ( (LicenseLandingPage) page ).getLicenseTableHeaderWebElements();
        intSeatsColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "SeatsColumnHeader" ) );
        intLicenseColumnNameNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "LicenseColumnHeader" ) );
        intLicenseExpDateColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "ExpDateColumnHeader" ) );
        
        CommonHelper.nap();
        List<String> actualLicenseNames = ( (LicenseLandingPage) page ).getTableLicenseName( intLicenseColumnNameNo );
        List<String> expectedLicenseNames = new ArrayList<String>( actualLicenseNames );

        ( (LicenseBasePage) page ).getExpDateColumnHeader().click();
        CommonHelper.nap();
        List<String> actualLicenseExpDates = ( (LicenseLandingPage) page ).getTableLicenseExpDate( intLicenseExpDateColumnNo );
        List<String> expectedLicenseExpDates = new ArrayList<String>( actualLicenseExpDates );

        ( (LicenseBasePage) page ).getSeatsColumnHeader().click();
        CommonHelper.nap();
        List<String> actualLicenseNoOfSeats = ( (LicenseLandingPage) page ).getTableLicenseNoOfSeats( intSeatsColumnNo );
        List<String> expectedLicenseNoOfSeats = new ArrayList<String>( actualLicenseNoOfSeats );

        CommonHelper.sortAscendingWithIgnoreCase( expectedLicenseNames );
        CommonHelper.sortAscendingWithIgnoreCase( expectedLicenseExpDates );
        CommonHelper.sortAscendingIntegerList( expectedLicenseNoOfSeats );

        Object[][] inputData = { { actualLicenseNames, expectedLicenseNames, "Verified Sort Ascending on License Name column" }, 
                { actualLicenseExpDates, expectedLicenseExpDates, "Verified Sort Ascending on License Exp Date column" },
                { actualLicenseNoOfSeats, expectedLicenseNoOfSeats, "Verified Sort Ascending on License No. of Seats column" }, };
        return inputData;
    }

    @Test ( priority = 210, dataProvider = "checkDecSortDataProvider" )
    public void checkDescendingSortColumnTest( List<String> actualText, List<String> expectedText, String testDesc ) {
        log.info( "Test for " + testDesc );
        Assert.assertEquals( actualText, expectedText, "Failed in verifying  " + testDesc + ", actual = " + actualText + ", expected = " + expectedText );
    }

    @DataProvider ( name = "checkDecSortDataProvider" )
    public Object[][] checkDescendingSortDataProvider() {
        int intSeatsColumnNo = 0;
        int intLicenseColumnNameNo;
        int intLicenseExpDateColumnNo = 0;
        List<WebElement> lstWe = ( (LicenseLandingPage) page ).getLicenseTableHeaderWebElements();
        intSeatsColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "SeatsColumnHeader" ) );
        intLicenseColumnNameNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "LicenseColumnHeader" ) );
        intLicenseExpDateColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "ExpDateColumnHeader" ) );

        ( (LicenseBasePage) page ).getLicenseColumnNameHeader().click();

        if( !( (LicenseBasePage) page ).getLicenseColumnNameHeader().getAttribute( "class" ).endsWith( "descending" ) ){
            ( (LicenseBasePage) page ).getLicenseColumnNameHeader().click();
        }

        List<String> actualLicenseNames = ( (LicenseLandingPage) page ).getTableLicenseName( intLicenseColumnNameNo );
        List<String> expectedLicenseNames = new ArrayList<String>( actualLicenseNames );
        ( (LicenseBasePage) page ).getExpDateColumnHeader().click();

        if( !( (LicenseBasePage) page ).getExpDateColumnHeader().getAttribute( "class" ).endsWith( "descending" ) ){
            ( (LicenseBasePage) page ).getExpDateColumnHeader().click();
        }

        CommonHelper.nap();
        List<String> actualLicenseExpDates = ( (LicenseLandingPage) page ).getTableLicenseExpDate( intLicenseExpDateColumnNo );
        List<String> expectedLicenseExpDates = new ArrayList<String>( actualLicenseExpDates );

        ( (LicenseBasePage) page ).getSeatsColumnHeader().click();
        if( !( (LicenseBasePage) page ).getSeatsColumnHeader().getAttribute( "class" ).endsWith( "descending" ) ){
            ( (LicenseBasePage) page ).getSeatsColumnHeader().click();
        }

        CommonHelper.nap();
        List<String> actualLicenseNoOfSeats = ( (LicenseLandingPage) page ).getTableLicenseNoOfSeats( intSeatsColumnNo );
        List<String> expectedLicenseNoOfSeats = new ArrayList<String>( actualLicenseNoOfSeats );

        CommonHelper.sortDescendingtWithIgnoreCase( expectedLicenseNames );
        CommonHelper.sortDescendingtWithIgnoreCase( expectedLicenseExpDates );
        CommonHelper.sortDescendingIntegerList( expectedLicenseNoOfSeats );

        Object[][] inputData = { { actualLicenseNames, expectedLicenseNames, "Verified Sort descending on License Name column" }, 
                { actualLicenseExpDates, expectedLicenseExpDates, "Verified Sort descending on License Exp Date column" },
                { actualLicenseNoOfSeats, expectedLicenseNoOfSeats, "Verified Sort descending on License No. of Seats column" }, };
        return inputData;
    }

    @Test ( priority = 211 )
    public void checkNoResultsDisplayedForOrgSelectedAsDistrict() {
        ( (LicenseLandingPage) page ).selectItemFromDropDown( ( (LicenseLandingPage) page ).getLicenseDisplayDropdown(), appProperties.getProperty( "OrgNameExisting" ) );
        String value = ( (LicenseLandingPage) page ).getLicenseTableRow().getText();
        Assert.assertEquals( value, appProperties.getProperty( "NoResultsText" ) );
    }

    @Test ( priority = 212 )
    public void checkAddLicenseBtnDisabledForOrgSelectedAsDistrict() {
        Assert.assertTrue( ( (LicenseLandingPage) page ).getAddLicenseBtn().getAttribute( "class" ).contains( "btnDisable" ), "Add License Button is enabled for the Org selected as District" );
    }

    @Test (priority = 213)
    public void checkNoResultAppearsForSchoolWithoutLicense(){
        ( (LicenseLandingPage) page ).selectItemFromDropDown( ( (LicenseLandingPage) page ).getLicenseDisplayDropdown(), UsersConstants.USER_ORG_NAME3_WITHOUT_LICENSE );
        String noResultsValue = ( (LicenseLandingPage) page ).getLicenseTableRow().getText();
        Assert.assertEquals( noResultsValue, appProperties.getProperty( "NoResultsText" ) );
    }    

    @Test ( priority = 214 )
    public void checkAddLicenseBtnEnabledForSelectedOrg() {
        page.selectItemFromDropDown( ( (LicenseLandingPage) page ).getLicenseDisplayDropdown(), UsersConstants.USER_ORG_NAME );
        CommonHelper.nap();
        Assert.assertTrue( ( (LicenseLandingPage) page ).getAddLicenseBtn().isEnabled(), "Add License Button is disabled for the selected Org" );
    }

    @Test ( priority = 215)
    public void checkTransferLinkforSelectedOrg() {
        Assert.assertNotNull( ( (LicenseLandingPage) page ).getTransferLink(), "Transfer link is not present under License Landing page" );
    }

    @Test (priority = 216)
    public void checkOrgSelectedOnHomeTabAppearsOnLicenseTab(){
        page.clickHomeTabBtn();
        log.info( "Clicked on Home tab" );
        page.selectItemFromDropDown( ( (HomePage) page ).getOrganizationDropDown(), UsersConstants.USER_ORG_NAME );
        log.info( "Selected " + UsersConstants.USER_ORG_NAME +" organization from the drop-down under Home tab" );
        CommonHelper.nap();
        page.clickLicTabBtn();        
        String orgCheck = ( (LicenseLandingPage) page ).getDefaultSelectedOption(((LicenseLandingPage) page).getLicenseDisplayDropdown());
        Assert.assertEquals( orgCheck, UsersConstants.USER_ORG_NAME, "After selecting an Org from Home tab and navigating to Licenses tab, Org selected from Home tab and Org displayed under License Landing page doesn't match" );
        Assert.assertTrue( ( (LicenseLandingPage) page ).getAddLicenseBtn().isEnabled(), "Add License Button is disabled for the selected Org" );
    }

    @Test (priority = 217)
    public void checkManageLicLinkFromHomeTabWithDefaultOrgSelected(){
        page.clickHomeTabBtn();
        CommonHelper.nap();
        log.info( "Clicked on Home tab" );
        ((HomePage) page ).getOrgManageLicLink().click();
        log.info( "Clicked on Manage Licenses link" );
        CommonHelper.nap();
        Assert.assertTrue( ((LicenseOrgPage) page).getLicenseTabActiveStatus().isEnabled(), "License Tab is inactive");
        Assert.assertNotNull(((LicenseOrgPage) page).getNoResultsTextRowData(), "'No Results' text is not displayed for org selected org as District");        
    }    
    
    @Test (priority = 218) 
    public void checkNoResultsDisplayedForOrgWithoutLicense()
    {
        page.clickHomeTabBtn();
        CommonHelper.nap();
        (( BasePage )page).selectItemFromDropDown(((LicenseOrgPage) page).getOrgDisplayDropdown(),UsersConstants.USER_ORG_NAME3_WITHOUT_LICENSE); 
        page.clickLicTabBtn(); 
        String licDropDownValue = CommonHelper.getFirstSelectedOptionFromDropDown(((LicenseLandingPage)page).getLicenseDisplayDropdown());
        Assert.assertEquals( UsersConstants.USER_ORG_NAME3_WITHOUT_LICENSE, licDropDownValue,"Org selected from Home tab is not the same as Org being displayed on the License tab");
        Assert.assertNotNull( ((LicenseLandingPage)page).getTableLicenseNameElement( "contains(.,'No Results')"), "No Result is not shown");
    }
    
    @Test (priority = 219, dataProvider="checkOrgDetailsDataProvider")
    public void checkOrgDetails(List<Object[]> orgDetails, String orgName){
        int intLicenseColumnNo = 0;
        page.clickHomeTabBtn();
        CommonHelper.nap();
        log.info( "Clicked on Home tab" );
        page.clickLicTabBtn();
        CommonHelper.nap();
        log.info( "Clicked on License tab" );
        (( BasePage )page).selectItemFromDropDown(( (LicenseLandingPage) page ).getLicenseDisplayDropdown(),orgName);
       
        CommonHelper.nap();
        
        List<WebElement> lstWe = ( (LicenseLandingPage) page ).getLicenseTableHeaderWebElements();
        intLicenseColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "LicenseColumnHeader" ) );
        List<String> tableLicenseName = ( (LicenseLandingPage) page ).getTableLicenseName( intLicenseColumnNo );
        
        for(Object[] orgArr:orgDetails){
           
            int intLicenseTableSize=0;
            String licenseName = (String) orgArr[0];
            
            tableLicenseName = ( (LicenseLandingPage) page ).getTableLicenseName( intLicenseColumnNo );
            intLicenseTableSize = ( (LicenseLandingPage) page ).getLicenseTableSize();
                        
            int intExpDateColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "ExpDateColumnHeader" ) );
            List<String> tableLicenseExpDate = ( (LicenseLandingPage) page ).getTableLicenseExpDate( intExpDateColumnNo );
            String actualExpDate = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseExpDate, licenseName ).trim();
            if(actualExpDate.equals( LicenseConstants.PERPETUAL_LICENSE_EXP_DATE))
            {
                Assert.assertEquals( actualExpDate , orgArr[1],"Unexpected error: Date mismatch while comparing values from DB for license name: " + licenseName );
            }
            else
            {
                Assert.assertEquals( CommonHelper.formatDateAsPerDB( actualExpDate ), orgArr[1],"Unexpected error: Date mismatch while comparing values from DB for license name: " + licenseName );
            }
            
            int intSeatsColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "SeatsColumnHeader" ) );
            intLicenseColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "LicenseColumnHeader" ) );
            List <String> tableLicenseNoOfSeats = ( (LicenseLandingPage) page ).getTableLicenseNoOfSeats( intSeatsColumnNo );
            String seats = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseNoOfSeats, licenseName );
            Assert.assertEquals( seats, String.valueOf( orgArr[2] ), "Unexpected error: Seats value mismatch while comparing values from DB for license name: " + licenseName);
            
            int intInUseColumnNo = CommonHelper.getTableColumnHeaderIndex( lstWe, appProperties.getProperty( "InUseColumnHeader" ) );
            List <String> tableLicenseInUse = ( (LicenseLandingPage) page ).getTableLicenseInUse( intInUseColumnNo );
            String licenseInUseActual = CommonHelper.getTableColumnValue( intLicenseTableSize, tableLicenseName, tableLicenseInUse, licenseName );
            Assert.assertEquals( licenseInUseActual, String.valueOf( orgArr[3] ), "Unexpected error: In Use value mismatch while comparing values from DB for license name: " + licenseName);
           
        }           
        
    }
    
    @DataProvider ( name = "checkOrgDetailsDataProvider" )
    public Object[][] checkOrgDetailsDataProvider() {
        
        List<Object[]> orgSpecificDetails = new ArrayList<Object[]>();
        orgSpecificDetails = LicenseSqlHelper.getSchoolSpecificData( UsersConstants.USER_ORG_NAME );
        
        List<Object[]> allOrgDetails = new ArrayList<Object[]>();
        allOrgDetails = LicenseSqlHelper.getAllSchoolsData();
        
        Object[][] inputData = { {orgSpecificDetails,UsersConstants.USER_ORG_NAME},
                {allOrgDetails , appProperties.getProperty( "DefaultSelection" )}};
                
        return inputData;
    }

    @Test(priority = 220)
    public void checkLogout() {
        loginPage.logoutAdmin();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ), "User has successfully logged out" );
    }
}
