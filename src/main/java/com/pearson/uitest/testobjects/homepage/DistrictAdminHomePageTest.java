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
 * Home page tests pertaining to District Admin User
 * 
 * @author vgaddra
 *
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public class DistrictAdminHomePageTest extends HomePageBaseTest {

    public DistrictAdminHomePageTest() {
        log = Logger.getLogger( DistrictAdminHomePageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        loginPage.login( AdminUIConstants.DISTRICT_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 301 )
    public void checkLicensesTabMenuButtonNotPresent() {
        Assert.assertTrue( page.getLicenseTabBtn() == null, "Unexpected 'Licenses' button is present." );
    }

    @Test ( priority = 302 )
    public void checkWelcomeMessage() {
        Assert.assertEquals( ( (HomePage) page ).getWelcomeMsg(), appProperties.getProperty( "Welcome" ) + " " + AdminUIConstants.DISTRICT_ADMIN_FIRSTNAME + " " + AdminUIConstants.DISTRICT_ADMIN_LASTNAME );
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
    public void checkEditServerSettingsButtonDisabled() {
        Assert.assertEquals( ( (HomePage) page ).orgEditServerSettingBtn().getCssValue( "pointer-events" ), "none" );
    }

    @Test ( priority = 306 )
    public void checkOranizationTabMenuButtonNotPresent() {
        Assert.assertTrue( ( (HomePage) page ).getOrgTabBtn() == null, "Unexpected Users button is present" );
    }

    @Test ( priority = 307 )
    public void checkEditOranizationDataSettingsLinkNotPresent() {
        Assert.assertTrue( ( (HomePage) page ).getOrgEditDatSetLink() == null, "Unexpected 'Edit data settings' link is present" );
    }

    @Test ( priority = 308 )
    public void checkEditOranizationLinkNotPresent() {
        Assert.assertTrue( ( (HomePage) page ).getOrgEditOrgDetLink() == null, "Unexpected 'Edit organization details' link is present." );
    }

    @Test ( priority = 309 )
    public void checkUsersManageGroupNotPresent() {
        Assert.assertTrue( ( (HomePage) page ).getUserMangeGrpLink() == null, "Unexpected 'Manage groups' link in present." );
    }

    @Test ( priority = 310 )
    public void checkUsersAddUsersLinkNotPresent() {
        Assert.assertTrue( ( (HomePage) page ).getUsrAddUsrLink() == null, "Unexpected 'Add users' link is present." );
    }

    @Test ( priority = 311 )
    public void checkOrgManageDistHolidayLink() {
        Assert.assertEquals( ( (HomePage) page ).getOrgMDHSLink().getText(), appProperties.getProperty( "OrgManageDistHolidaySchedule" ) );
    }

    @Test ( priority = 312 )
    public void checkOrgManageDistHolidayClick() {
        String orgMDHSText = ( (HomePage) page ).clickOrgMDHSLink();
        assertBasedOnBrowser( orgMDHSText, LINK_ALERT );
    }

    @Test ( priority = 313 )
    public void checkUsrTransferStudent() {
        Assert.assertEquals( ( (HomePage) page ).getUsrTransStudLink().getText(), appProperties.getProperty( "UsrTransferStudents" ) );
    }

    @Test ( priority = 314 )
    public void usrTransferStudentClick() {
        String transStudentText = ( (HomePage) page ).clickUsrTransStudLink();
        assertBasedOnBrowser( transStudentText, LINK_ALERT );
    }

    @Test ( priority = 315 )
    public void checkOrganizationDropDownValues() {
        List<String> expectedOrgNames = orgHelper.getOrganizationNames( null );
        List<String> actualOrgNames = ( (HomePage) page ).getAllOrganizationDropDownValues();
        Assert.assertEquals( actualOrgNames.size(), expectedOrgNames.size() );
        actualOrgNames.removeAll( expectedOrgNames );
        Assert.assertEquals( actualOrgNames.size(), 0 );
    }

    @Test ( priority = 316 )
    public void checkOrganizationDropDownDefaultSelection() {
        Assert.assertEquals( ( (HomePage) page ).getOrganizationDropDownDefaultSelection(), "District" );
    }

    @AfterClass
    public void logout() {
        page.logoutAdminPanel( browser );
    }

}
