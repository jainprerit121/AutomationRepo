package com.pearson.uitest.testobjects.homepage;

import java.util.List;

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
 * Home page tests pertaining to School Admin User
 * 
 * @author vgaddra
 *
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public class SchoolAdminHomePageTest extends SystemSchoolAdminHomePageTest {

    public SchoolAdminHomePageTest() {
        log = Logger.getLogger( SchoolAdminHomePageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        loginPage.login( AdminUIConstants.SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 301 )
    public void checkLicensesTabMenuButtonNotPresent() {
        Assert.assertTrue( page.getLicenseTabBtn() == null, "Unexpected 'Licenses' button is present." );
    }

    @Test ( priority = 302 )
    public void checkWelcomeMessage() {
        Assert.assertEquals( ( (HomePage) page ).getWelcomeMsg(), appProperties.getProperty( "Welcome" ) + " " + AdminUIConstants.SCHOOL_ADMIN_FIRSTNAME + " " + AdminUIConstants.SCHOOL_ADMIN_LASTNAME );
    }

    @Test ( priority = 303 )
    public void checkAddOrganizationLinkNotPresent() {
        Assert.assertTrue( ( (HomePage) page ).getOrgAddOrgLink() == null, "Unexpected 'Add organization' link is present" );
    }

    @Test ( priority = 304 )
    public void checkManageLicenseLinkNotPresent() {
        Assert.assertTrue( ( (HomePage) page ).getOrgManageLicLink() == null, "Unexpected 'Manage Licenses' link is present" );
    }

    @Test ( priority = 305 )
    public void checkManageDistrictHolidayScheduleLinkNotPresent() {
        Assert.assertTrue( ( (HomePage) page ).getOrgMDHSLink() == null, "Unexpected 'Manage district holiday schedule' link is present" );
    }

    @Test ( priority = 306 )
    public void checkTransferStudentLinkNotPresent() {
        Assert.assertTrue( ( (HomePage) page ).getUsrTransStudLink() == null, "Unexpected 'Transfer Students' link is present" );
    }

    @Test ( priority = 307 )
    public void checkEditServerSettingsButtonDisabled() {
        Assert.assertEquals( ( (HomePage) page ).orgEditServerSettingBtn().getCssValue( "pointer-events" ), "none" );
    }

    @Test ( priority = 308 )
    public void checkOrganizationDropDownValues() {
        List<String> expectedOrgNames = orgHelper.getOrganizationNames( AdminUIConstants.SCHOOL_ADMIN_USERNAME );
        List<String> actualOrgNames = ( (HomePage) page ).getAllOrganizationDropDownValues();
        Assert.assertEquals( actualOrgNames.size(), 1 );
        actualOrgNames.removeAll( expectedOrgNames );
        Assert.assertEquals( actualOrgNames.size(), 0 );
    }

    @Test ( priority = 309 )
    public void checkOrganizationDropDownDefaultSelection() {
        Assert.assertEquals( ( (HomePage) page ).getOrganizationDropDownDefaultSelection(), AdminUIConstants.NEW_SCHOOL_NAME );
    }

    @AfterClass
    public void logout() {
        page.logoutAdminPanel( browser );
    }

}
