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
import com.pearson.uitest.pageobjects.OrganizationPage;

/**
 * Organization landing page tests for School Admin
 * 
 * @author Nagarajan Ganesan
 *
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public class SchoolAdminOrganizationPageTest extends OrganizationPageBaseTest {

    public SchoolAdminOrganizationPageTest() {
        log = Logger.getLogger( SchoolAdminOrganizationPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        loginPage.login( AdminUIConstants.SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 201 )
    // TO DO Data test
    public void checkDefaultOrgSelected() {
        Assert.assertTrue( ( (OrganizationPage) page ).getDefaultOrgSelection( AdminUIConstants.NEW_SCHOOL_NAME ) );
    }

    @Test ( priority = 202 )
    public void checkAddOrgButtonDisabled() {
        checkAddOrganizationButtonPresence();
        Assert.assertTrue( ( (OrganizationPage) page ).getOrgLandAddOrgBtn().isDisplayed(), "Add Organization Button is not disabled" );
    }

    @Test ( priority = 203 )
    public void checkServerSettingDisabled() {
        Assert.assertTrue( ( (OrganizationPage) page ).getSerSetforSchoolAdmin().getAttribute( "class" ).contains( "linkDisable" ), "Server Setting link not disabled" );
        log.info( "Server Settings Disabled" );
    }

    @Test ( priority = 204 )
    public void checkOrganizationDropDownValues() {
        List<String> expectedOrgNames = orgHelper.getOrganizationNames( AdminUIConstants.SCHOOL_ADMIN_USERNAME );
        List<String> actualOrgNames = ( (OrganizationPage) page ).getAllOrgPageOrganizationDropDownValues();
        Assert.assertEquals( actualOrgNames.size(), 1 );
        actualOrgNames.removeAll( expectedOrgNames );
        Assert.assertEquals( actualOrgNames.size(), 0 );
    }

    @Test ( priority = 205 )
    public void checkOrgLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
    }

}