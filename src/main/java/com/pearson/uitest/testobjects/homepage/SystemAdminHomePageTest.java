package com.pearson.uitest.testobjects.homepage;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.pageobjects.HomePage;

/**
 * Home page tests pertaining to System Admin User
 * 
 * @author vgaddra
 *
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public class SystemAdminHomePageTest extends SystemSchoolAdminHomePageTest {

    public SystemAdminHomePageTest() {
        log = Logger.getLogger( SystemAdminHomePageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 301 )
    public void checkLicensesBtnPresent() {
        Assert.assertNotNull( page.getLicenseTabBtn(), appProperties.getProperty( "HomeLicensesBtnMissingError" ) );
    }

    @Test ( priority = 302 )
    public void checkLicensesBtn() {
        Assert.assertEquals( page.getLicenseTabBtn().getText(), appProperties.getProperty( "HomeLicensesBtn" ) );
    }

    @Test ( priority = 303 )
    public void checkLicTabBtnClick() {
        Map<String, String> actualPageDetails = page.clickLicTabBtn();
        ( (HomePage) page ).clickHomeTabBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LicPageUrl" ) );

    }

    @Test ( priority = 304 )
    public void checkWelcomeMsg() {
        Assert.assertEquals( ( (HomePage) page ).getWelcomeMsg(), appProperties.getProperty( "Welcome" ) + " " + AdminUIConstants.SYSTEM_ADMIN_FIRSTNAME + " " + AdminUIConstants.SYSTEM_ADMIN_LASTNAME );
    }

    @Test ( priority = 305 )
    public void checkOrgManageLicLink() {
        Assert.assertEquals( ( (HomePage) page ).getOrgManageLicLink().getText(), appProperties.getProperty( "OrgManageLic" ) );
    }

    @Test ( priority = 306 )
    public void checkOrgManageLicClick() {
        Map<String, String> actualPageDetails = ( (HomePage) page ).clickOrgManageLicLink();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LicenseURL" ) );
        ( (HomePage) page ).clickHomeTabBtn();
    }

    @Test ( priority = 307 )
    public void checkOrgAddOrgLink() {
        Assert.assertEquals( ( (HomePage) page ).getOrgAddOrgLink().getText(), appProperties.getProperty( "OrgAddOrg" ) );
    }

    @Test ( priority = 308 )
    public void orgAddORgLinkClick() {
        Map<String, String> actualPageDetails = ( (HomePage) page ).clickOrgAddOrgLink();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "AddOrgUrl" ) );
        ( (HomePage) page ).clickHomeTabBtn();
    }

    @Test ( priority = 308 )
    public void checkOrgManageDistHolidayLink() {
        Assert.assertEquals( ( (HomePage) page ).getOrgMDHSLink().getText(), appProperties.getProperty( "OrgManageDistHolidaySchedule" ) );
    }

    @Test ( priority = 309 )
    public void checkOrgManageDistHolidayClick() {
        String orgMDHSText = ( (HomePage) page ).clickOrgMDHSLink();
        assertBasedOnBrowser( orgMDHSText, LINK_ALERT );
    }

    @Test ( priority = 310 )
    public void checkUsrTransferStudent() {
        Assert.assertEquals( ( (HomePage) page ).getUsrTransStudLink().getText(), appProperties.getProperty( "UsrTransferStudents" ) );
    }

    @Test ( priority = 311 )
    public void usrTransferStudentClick() {
        String transStudentText = ( (HomePage) page ).clickUsrTransStudLink();
        assertBasedOnBrowser( transStudentText, LINK_ALERT );
    }

    @Test ( priority = 312 )
    public void orgEditServSetBtnClick() {
        Map<String, String> actualPageDetails = ( (HomePage) page ).clickOrgEditServerSettingBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "ServerSettingsPageTitle" ), appProperties.getProperty( "OrgSerSetUrl" ) );
    }

    @Test ( priority = 313 )
    public void checkOrganizationDropDownValues() {
        List<String> expectedOrgNames = orgHelper.getOrganizationNames( null );
        List<String> actualOrgNames = ( (HomePage) page ).getAllOrganizationDropDownValues();
        Assert.assertEquals( actualOrgNames.size(), expectedOrgNames.size() );
        actualOrgNames.removeAll( expectedOrgNames );
        Assert.assertEquals( actualOrgNames.size(), 0 );
    }

    @Test ( priority = 314 )
    public void checkOrganizationDropDownDefaultSelection() {
        Assert.assertEquals( ( (HomePage) page ).getOrganizationDropDownDefaultSelection(), "District" );
    }

    @AfterClass
    public void logout() {
        page.logoutAdminPanel( browser );
    }
}
