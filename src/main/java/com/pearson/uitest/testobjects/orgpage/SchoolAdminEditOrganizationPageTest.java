package com.pearson.uitest.testobjects.orgpage;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.OrganizationConstants;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.ManageOrganizationPage;
import com.pearson.uitest.pageobjects.OrganizationPage;

/**
 * 
 * @author vgaddra
 *
 */
public class SchoolAdminEditOrganizationPageTest extends EditOrganizationBaseTest {

    public SchoolAdminEditOrganizationPageTest() {
        log = Logger.getLogger( SchoolAdminEditOrganizationPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( OrganizationConstants.UPDATE_EDIT_ORG_NAME );
        orgHelper.deleteOrganization( OrganizationConstants.ADDTIONAL_ORG_NAME );
        orgHelper.createOrganization( OrganizationConstants.ADDTIONAL_ORG_NAME, OrganizationConstants.ADDITIONAL_ORG_ID );
        loginPage.login( AdminUIConstants.SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        currentUserLogin = AdminUIConstants.SCHOOL_ADMIN_USERNAME;
        editTest = true;
        page.getOrgTabBtn().click();
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
    }

    @Test ( priority = 401 )
    public void checkLicenseTabNotPresent() {
        page.getOrgTabBtn().click();
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        CommonHelper.nap();
        Assert.assertFalse( ( (ManageOrganizationPage) page ).getLicenseSettingLink().isDisplayed(), "License Settings tab present which is unexpected." );
        log.info( "Verified the license settings tab not present!" );
    }

    @Test ( priority = 402 )
    public void checkUpdatedOrganizationHasServerSettingsLink() {
        page.getOrgTabBtn().click();
        Assert.assertTrue( ( (OrganizationPage) page ).getSerSetforSchoolAdmin().getAttribute( "class" ).contains( "linkDisable" ), "Server settings enabled!" );
        log.info( "Server Settings disabled!" );
    }

    @Test ( priority = 403 )
    public void checkOrgLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
    }
}
