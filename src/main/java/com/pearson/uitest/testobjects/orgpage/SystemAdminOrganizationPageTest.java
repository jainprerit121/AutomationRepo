package com.pearson.uitest.testobjects.orgpage;

import java.util.ArrayList;
import java.util.Collections;
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
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.OrganizationPage;

/**
 * Organization landing page tests for System Admin
 * 
 * @author Nagarajan Ganesan
 *
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public class SystemAdminOrganizationPageTest extends OrganizationPageBaseTest {

    public SystemAdminOrganizationPageTest() {
        log = Logger.getLogger( SystemAdminOrganizationPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        page.getOrgTabBtn().click();
    }

    @Test ( priority = 201 )
    public void checkDefaultOrgSelected() {
        Assert.assertTrue( ( (OrganizationPage) page ).getDefaultOrgSelection( appProperties.getProperty( "DefaultSelection" ) ) );
    }

    @Test ( priority = 202 )
    public void checkAddOrganizationButtonEnabled() {
        checkAddOrganizationButtonPresence();
        Assert.assertTrue( ( (OrganizationPage) page ).getOrgLandAddOrgBtn().isEnabled() );
    }

    @Test ( priority = 203 )
    public void checkAddOrganizationButtonClick() {
        Map<String, String> actualAddOrgPageDetails = ( (OrganizationPage) page ).clickAddOrganizationButton();
        ( (OrganizationPage) page ).getOrgLandAddOrgCancelBtn().click();
        checkPageDetails( actualAddOrgPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "AddOrgUrl" ) );
    }

    @Test ( priority = 204 )
    public void checkAddedSchool() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ), AdminUIConstants.NEW_SCHOOL_NAME + " link is not present" );
    }

    @Test ( priority = 205 )
    public void checkDefaultOrgSelectedAfterSelectingOrgInHome() {
        page.getHomeTabBtn().click();
        ( (OrganizationPage) page ).selOrgFromDropdown( AdminUIConstants.NEW_SCHOOL_NAME );
        page.getOrgTabBtn().click();
        //  Assert.assertTrue("1".equals(((OrganizationPage)page).getOrgDisplayDropdownSize()));
        Assert.assertTrue( ( (OrganizationPage) page ).getDefaultOrgSelection( AdminUIConstants.NEW_SCHOOL_NAME ) );
    }

    @Test ( priority = 206 )
    public void checkOrganizationDropDownValues() {
        page.getHomeTabBtn().click();
        CommonHelper.nap();
        page.getOrgTabBtn().click();
        CommonHelper.nap();
        List<String> expectedOrgNames = orgHelper.getOrganizationNames( null );
        List<String> actualOrgNames = ( (OrganizationPage) page ).getAllOrgPageOrganizationDropDownValues();
        actualOrgNames.remove( 0 );
        Assert.assertEquals( actualOrgNames.size(), expectedOrgNames.size() );
        actualOrgNames.removeAll( expectedOrgNames );
        Assert.assertEquals( actualOrgNames.size(), 0 );
    }

    @Test ( priority = 207 )
    public void verifySelectedOrganizationDisplayed() {
        page.clickOrgTabBtn();
        ( (OrganizationPage) page ).selOrgPageOrgFromDropdown( AdminUIConstants.NEW_SCHOOL_NAME );
        Assert.assertEquals( ( (OrganizationPage) page ).getOrgTableSize(), "1" );
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ), AdminUIConstants.NEW_SCHOOL_NAME + " link is not present" );
    }

    @Test ( priority = 208 )
    public void checkServerSettingEnabled() {
        Assert.assertTrue( ( (OrganizationPage) page ).getSerSetforSchoolAdmin().getAttribute( "class" ).contains( "linkStyle" ), "Server Setting link not enabled" );
    }

    @Test ( priority = 209 )
    public void checkServerSettingslinkClick() {
        Map<String, String> actualPageDetails = ( (OrganizationPage) page ).clickServerSettings();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "ServerSettingsPageTitle" ), appProperties.getProperty( "OrgSerSetUrl" ) );
    }

    @Test ( priority = 210 )
    public void verifyOrgTableData() {
        ( (OrganizationPage) page ).selOrgPageOrgFromDropdown( appProperties.getProperty( "DefaultSelection" ) );
        List<String> expectedOrgNames = orgHelper.getOrganizationNames( null );
        List<String> actualOrgNames = ( (OrganizationPage) page ).getTableOrgNamesList();
        Assert.assertEquals( expectedOrgNames.size(), actualOrgNames.size() );
        actualOrgNames.removeAll( expectedOrgNames );
        Assert.assertEquals( actualOrgNames.size(), 0 );
    }

    @Test ( priority = 211 )
    public void verifyOrderOrgNameAscending() {
        List<String> actualOrgNames = ( (OrganizationPage) page ).getTableOrgNamesList();
        List<String> expectedOrgNames = new ArrayList<String>( actualOrgNames );
        CommonHelper.sortAscendingWithIgnoreCase( expectedOrgNames );
        log.info( "Expected: " + expectedOrgNames.toString() );
        log.info( "Actual: " + actualOrgNames.toString() );
        Assert.assertEquals( actualOrgNames, expectedOrgNames );
    }

    @Test ( priority = 212 )
    public void verifyOrderOrgNameDecending() {
        do {
            ( (OrganizationPage) page ).getOrgNameRowHeader().click();
            CommonHelper.nap();
            CommonHelper.nap();
            CommonHelper.nap();
            CommonHelper.nap();
        } while ( !( (OrganizationPage) page ).getOrgNameRowHeader().getAttribute( "class" ).endsWith( "descending" ) );

        List<String> actualOrgNames = ( (OrganizationPage) page ).getTableOrgNamesList();
        List<String> expectedOrgNames = new ArrayList<String>( actualOrgNames );
        CommonHelper.sortAscendingWithIgnoreCase( expectedOrgNames );
        Collections.sort( expectedOrgNames, Collections.reverseOrder( String.CASE_INSENSITIVE_ORDER ) );
        log.info( "Expected: " + expectedOrgNames.toString() );
        log.info( "Actual: " + actualOrgNames.toString() );
        Assert.assertEquals( actualOrgNames, expectedOrgNames );
    }

    @Test ( priority = 213 )
    public void verifyOrderOrgIDAscending() {
        do {
            ( (OrganizationPage) page ).getOrgIDRowHeader().click();
            CommonHelper.nap();
            CommonHelper.nap();
            CommonHelper.nap();
            CommonHelper.nap();
        } while ( !( (OrganizationPage) page ).getOrgIDRowHeader().getAttribute( "class" ).endsWith( "ascending" ) );
        CommonHelper.nap();
        List<String> actualOrgNames = ( (OrganizationPage) page ).getTableOrgIDsList();
        List<String> expectedOrgNames = new ArrayList<String>( actualOrgNames );
        CommonHelper.sortAscendingWithIgnoreCase( expectedOrgNames );
        log.info( "Expected: " + expectedOrgNames.toString() );
        log.info( "Actual: " + actualOrgNames.toString() );
        Assert.assertEquals( actualOrgNames, expectedOrgNames );
    }

    @Test ( priority = 214 )
    public void verifyOrderOrgIDDecending() {
        do {
            ( (OrganizationPage) page ).getOrgIDRowHeader().click();
            CommonHelper.nap();
        } while ( !( (OrganizationPage) page ).getOrgIDRowHeader().getAttribute( "class" ).endsWith( "descending" ) );

        CommonHelper.nap();
        List<String> actualOrgNames = ( (OrganizationPage) page ).getTableOrgIDsList();
        List<String> expectedOrgNames = new ArrayList<String>( actualOrgNames );
        CommonHelper.sortAscendingWithIgnoreCase( expectedOrgNames );
        Collections.sort( expectedOrgNames, Collections.reverseOrder( String.CASE_INSENSITIVE_ORDER ) );
        log.info( "Expected: " + expectedOrgNames.toString() );
        log.info( "Actual: " + actualOrgNames.toString() );
        Assert.assertEquals( actualOrgNames, expectedOrgNames );
    }

    @Test ( priority = 215 )
    public void checkOrgLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
    }
}
