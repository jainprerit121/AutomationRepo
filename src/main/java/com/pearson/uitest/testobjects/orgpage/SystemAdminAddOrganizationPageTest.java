package com.pearson.uitest.testobjects.orgpage;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.OrganizationConstants;
import com.pearson.uitest.enums.DeploymentTypes;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.ManageOrganizationPage;
import com.pearson.uitest.pageobjects.OrganizationPage;

/**
 * 
 * This class is used to test add organization feature using system admin login.
 *
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public class SystemAdminAddOrganizationPageTest extends ManageOrganizationBaseTest {

    public SystemAdminAddOrganizationPageTest() {
        log = Logger.getLogger( SystemAdminAddOrganizationPageTest.class );
    }

    /**
     * @BeforeTest ( alwaysRun = true ) public void setRetryAnalyser(
     *             ITestContext context ) { for ( ITestNGMethod method :
     *             context.getAllTestMethods() ) { method.setRetryAnalyzer( new
     *             RetryRule() ); } }
     **/

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        CommonHelper.nap();
        page.getOrgTabBtn().click();
        ( (OrganizationPage) page ).getOrgLandAddOrgBtn().click();
    }

    @Test ( priority = 200 )
    public void checkURLPostOrgPageHelpButtonClick() {
        page.getOrgTabBtn().click();
        ( (OrganizationPage) page ).getOrgLandAddOrgBtn().click();
        Map<String, String> actualPageDetails = ( (OrganizationPage) page ).clickOrgPageHelpButton();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AddOrgHelpTitle" ), appProperties.getProperty( "AddOrgHelpUrl" ) );
    }

    @Test ( priority = 201 )
    public void checkAddOrganizationLabel() {
        Assert.assertEquals( ( (OrganizationPage) page ).getOrgWelcomeMsg(), appProperties.getProperty( "OrgLabel" ) );
    }

    @Test ( priority = 202 )
    public void checkOrganizationNameDefaultContent() {
        Assert.assertTrue( "".equals( ( (ManageOrganizationPage) page ).getOrganizationNameInput().getText() ), "Organization Name input field is not empty by default" );
    }

    @Test ( priority = 203 )
    public void checkOrganizationIDDefaultContent() {
        Assert.assertTrue( "".equals( ( (ManageOrganizationPage) page ).getOrganizationIdInput().getText() ), "Organization ID input field is not empty by default" );
    }

    @Test ( priority = 204 )
    public void checkOrganizationCancelButttonText() {
        Assert.assertEquals( ( (ManageOrganizationPage) page ).getOrganizationCancelButton().getText(), appProperties.getProperty( "OrgCancelBtn" ) );
    }

    @Test ( priority = 205 )
    public void checkOrganizationDeploymentTypeDropdown() {
        List<String> actualList = ( (ManageOrganizationPage) page ).getOrganizationDeploymentDropdownValues();
        List<String> expectedList = DeploymentTypes.getDeploymentTypeNames();
        log.info( "Actual List of Deployment Types: " + actualList.toString() );
        log.info( "Expected: " + expectedList.toString() );
        actualList.removeAll( expectedList );
        Assert.assertTrue( actualList.size() == 0, "Additonal deployment types in " + actualList.toString() );
    }

    @Test ( priority = 122 )
    public void checkOrganizationDistrictDropDownFirstOption() {
        String actualSelection = ( (ManageOrganizationPage) page ).getOrganizationDistrictDefaultSelection();
        Assert.assertEquals( actualSelection, "-- Select a District --" );
    }

    @Test ( priority = 206 )
    public void clickCancelButtonWithoutData() {
        Map<String, String> actualPageDetails = ( (ManageOrganizationPage) page ).clickCancelButtonButton();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "OrgPageUrl" ) );
    }

    @Test ( priority = 207 )
    public void checkAbsenceofDistrictDropdownWhenSelectDistrictDeploymentType() {
        ( (OrganizationPage) page ).getOrgLandAddOrgBtn().click();
        ( (ManageOrganizationPage) page ).selectDeploymentDropdownOption( "District" );
        Assert.assertFalse( ( (ManageOrganizationPage) page ).getOrganizationDistrictDropdown().isDisplayed(), "District Dropdown present" );
    }

    @Test ( priority = 208 )
    public void checkAddingMoreDistrict() {
        String retAlert = addOrganization( "MyOrganization", "MyID", 1, -1 );
        verifyAddOrganizationScenario( retAlert, "OrgDistAdd" );
    }

    @Test ( priority = 209 )
    public void checkAddingDistrictWithNoDistrictNameAndID() {
        String retAlert = addOrganization( "", "", 1, -1 );
        verifyAddOrganizationScenario( retAlert, "OrgNameIdBlank" );
    }

    @Test ( priority = 210 )
    public void checkAddingDistrictWithNoDistrictName() {
        String retAlert = addOrganization( "", "MyID", 1, -1 );
        verifyAddOrganizationScenario( retAlert, "OrgNameBlank" );
    }

    @Test ( priority = 211 )
    public void checkAddingDistrictWithNoDistrictID() {
        String retAlert = addOrganization( "MyOrganization", "", 1, -1 );
        verifyAddOrganizationScenario( retAlert, "OrgIdBlank" );
    }

    @Test ( priority = 212 )
    public void checkSchoolIsSuccessfullyAdded() {
        ( (ManageOrganizationPage) page ).selectDeploymentDropdownOption( "School" );
        orgHelper.deleteOrganization( OrganizationConstants.ADD_ORGANIZATION_NAME );
        String retAlert = addOrganization( OrganizationConstants.ADD_ORGANIZATION_NAME, OrganizationConstants.ADD_ORGANIZATION_ID, 0, 1 );
        Assert.assertTrue( retAlert.trim().replaceAll( "\n", " " ).equals( OrganizationConstants.ADD_ORGANIZATION_NAME + " has been added" ) );
        CommonHelper.nap();
    }

    public void checkOrganizationLandingPageNvaigated() {
        Assert.assertTrue( driver.getCurrentUrl().equals( appProperties.getProperty( "OrgPageUrl" ) ) );
    }

    public void checkOrganizationTabHighlightedPostAddingAnOrganization() {
        Assert.assertEquals( page.getOrgTabBtn().getAttribute( "class" ), "ng-binding active" );
    }

    @Test ( priority = 213 )
    public void checkOrganizationDropDownBoxPoupulatedWithNewlyAddedSchool() {
        CommonHelper.nap();
        Assert.assertTrue( ( (ManageOrganizationPage) page ).checkItemPresentInOrganizationDropdown( OrganizationConstants.ADD_ORGANIZATION_NAME ), "Newly added school not present in the dropdown - " + OrganizationConstants.ADD_ORGANIZATION_NAME );
        log.info( "Verified that new added school is present - " + OrganizationConstants.ADD_ORGANIZATION_NAME );
    }

    @Test ( priority = 214 )
    public void checkOrganizationDropdownOrderWithNewlyAddedOrganization() {
        Assert.assertTrue( ( (OrganizationPage) page ).checkOrganizationDropDownInAscendingOrder( appProperties.getProperty( "DefaultSelection" ) ), "Org dropdown not in ascending order post adding an school" );
        log.info( "Verified that organization dropdown is in ascending order post adding an school!" );
    }

    @Test ( priority = 215 )
    public void checkNewlyAddedOrganizationDetailsPopulatedInTheTable() {
        List<String> tableOrgNameList = ( (OrganizationPage) page ).getTableOrgNamesList();
        Assert.assertTrue( tableOrgNameList.contains( OrganizationConstants.ADD_ORGANIZATION_NAME ), "Newly added school not present in the table - " + OrganizationConstants.ADD_ORGANIZATION_NAME );
        log.info( "Verified that newly added school present in the organization landing page table - " + OrganizationConstants.ADD_ORGANIZATION_NAME );
    }

    @Test ( priority = 216 )
    public void checkNewlyAddedOrganizationHasServerSettingsLink() {
        ( (OrganizationPage) page ).selOrgPageOrgFromDropdown( OrganizationConstants.ADD_ORGANIZATION_NAME );
        Assert.assertTrue( ( (OrganizationPage) page ).getSerSetforSchoolAdmin().getAttribute( "class" ).contains( "linkStyle" ), "Server Setting link not enabled" );
        log.info( "Verified that Server Settings link is enabled for the newly added school - " + OrganizationConstants.ADD_ORGANIZATION_NAME );
    }

    @Test ( priority = 217 )
    public void checkNewlyAddedOrganizationIsPresentInHomePageOrganizationDropdwon() {
        page.getHomeTabBtn().click();
        CommonHelper.nap();
        Assert.assertTrue( ( (OrganizationPage) page ).getAllOrganizationDropDownValues().contains( OrganizationConstants.ADD_ORGANIZATION_NAME ), "Newly added school does not present in Homepage  org dropdown" );
        log.info( "Verified that newly added school present in homepage organization dropdown" );
    }

    @Test ( priority = 218 )
    public void checkAddingDuplicateOrganizationNameAndID() {
        page.getOrgTabBtn().click();
        ( (OrganizationPage) page ).getOrgLandAddOrgBtn().click();
        CommonHelper.nap();
        String retAlert = addOrganization( OrganizationConstants.ADD_ORGANIZATION_NAME, OrganizationConstants.ADD_ORGANIZATION_ID, 0, 1 );
        verifyAddOrganizationScenario( retAlert, "OrgExistingName" );
    }

    @Test ( priority = 219 )
    public void checkAddingDuplicateOrganizationID() {
        String retAlert = addOrganization( "NewOrganizationForAutomation", OrganizationConstants.ADD_ORGANIZATION_ID, 0, 1 );
        verifyAddOrganizationScenario( retAlert, "OrgExistingId" );
    }

    @Test ( priority = 220 )
    public void checkAddingDuplicateOrganizationName() {
        String retAlert = addOrganization( OrganizationConstants.ADD_ORGANIZATION_NAME, "NEWAUTO", 0, 1 );
        verifyAddOrganizationScenario( retAlert, "OrgExistingName" );
    }

    @Test ( priority = 221 )
    public void checkLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
    }
}