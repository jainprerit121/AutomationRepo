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
public class SystemAdminEditOrganizationPageTest extends EditOrganizationBaseTest {

    public SystemAdminEditOrganizationPageTest() {
        log = Logger.getLogger( SystemAdminEditOrganizationPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( OrganizationConstants.UPDATE_EDIT_ORG_NAME );
        orgHelper.deleteOrganization( OrganizationConstants.ADDTIONAL_ORG_NAME );
        orgHelper.createOrganization( OrganizationConstants.ADDTIONAL_ORG_NAME, OrganizationConstants.ADDITIONAL_ORG_ID );
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        currentUserLogin = AdminUIConstants.SYSTEM_ADMIN_USERNAME;
        editTest = true;
        CommonHelper.nap();
        page.getOrgTabBtn().click();
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
    }

    @Test ( priority = 400 )
    public void checkUpdatedOrganizationHasServerSettingsLink() {
        ( (OrganizationPage) page ).selOrgPageOrgFromDropdown( AdminUIConstants.NEW_SCHOOL_NAME );
        Assert.assertFalse( ( (OrganizationPage) page ).getSerSetforSchoolAdmin().getAttribute( "class" ).contains( "linkDisable" ), "Server settings disabled" );
        log.info( "Server Settings enabled!" );
    }

    @Test ( priority = 401 )
    public void checkClickLicenseTabCancelOptionAfterChangingName() {
        page.getOrgTabBtn().click();
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LICENSE_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 402 )
    public void checkClickLicenseTabCancelOptionAfterChangingID() {
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_LICENSE_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 403 )
    public void checkClickLicenseTabCancelOptionAfterChangingNameAndID() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_LICENSE_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 404 )
    public void checkClickLicenseTabNoOptionAfterChangingName() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LICENSE_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 405 )
    public void checkSchoolNameNotAlteredInOrgTablePostLicenseTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 406 )
    public void checkClickLicenseTabNoOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        CommonHelper.nap();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_LICENSE_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 407 )
    public void checkSchoolIDNotAlteredInOrgTablePostLicenseTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 408 )
    public void checkClickLicenseTabNoOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_LICENSE_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 409 )
    public void checkSchoolNameAndIDNotAlteredInOrgTablePostLicenseTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 410 )
    public void checkClickLicenseTabYesOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LICENSE_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 411 )
    public void checkSchoolNameAlteredInOrgTablePostLicenseTabNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 412 )
    public void checkClickLicenseTabYesOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_LICENSE_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 413 )
    public void checkSchoolIDAlteredInOrgTablePostLicenseTabNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 414 )
    public void checkOldSchoolDoesNotExistInOrganizationPostLicenseTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ), OrganizationConstants.ERROR_MESSAGE_OLD_ORG_EXIST + AdminUIConstants.NEW_SCHOOL_NAME );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 415 )
    public void checkClickLicenseTabYesOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LICENSE_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    //License Setttings
    @Test ( priority = 416 )
    public void checkClickLicenseSettingsCancelOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LICENSE_SETTINGS_LINK, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 417 )
    public void checkClickLicenseSettingsCancelOptionAfterChangingID() {
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_LICENSE_SETTINGS_LINK, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 418 )
    public void checkClickLicenseSettingsCancelOptionAfterChangingNameAndID() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_LICENSE_SETTINGS_LINK, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 419 )
    public void checkClickLicenseSettingsNoOptionAfterChangingName() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LICENSE_SETTINGS_LINK, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 420 )
    public void checkSchoolNameNotAlteredInOrgTablePostPostLicenseSettingssNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 421 )
    public void checkClickLicenseSettingsNoOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        CommonHelper.nap();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_LICENSE_SETTINGS_LINK, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 422 )
    public void checkSchoolIDNotAlteredInOrgTablePostPostLicenseSettingssNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 423 )
    public void checkClickLicenseSettingsNoOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_LICENSE_SETTINGS_LINK, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 424 )
    public void checkSchoolNameAndIDNotAlteredInOrgTablePostPostLicenseSettingssNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 425 )
    public void checkClickLicenseSettingsYesOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LICENSE_SETTINGS_LINK, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 426 )
    public void checkSchoolNameAlteredInOrgTablePostLicenseSettingsNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 427 )
    public void checkClickLicenseSettingsYesOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_LICENSE_SETTINGS_LINK, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 428 )
    public void checkSchoolIDAlteredInOrgTablePostLicenseSettingssNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 429 )
    public void checkOldSchoolDoesNotExistInOrganizationPostHomeTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ), OrganizationConstants.ERROR_MESSAGE_OLD_ORG_EXIST + AdminUIConstants.NEW_SCHOOL_NAME );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 430 )
    public void checkClickLicenseSettingsYesOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LICENSE_SETTINGS_LINK, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LICENSE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 431 )
    public void checkEditDistrictNameAndID() {
        String dist = orgHelper.getAllDistrictsList().get( 0 );
        ( (OrganizationPage) page ).getOrganizationLink( dist ).click();
        CommonHelper.nap();
        CommonHelper.nap();
        Assert.assertTrue( editDistrict( "MyDistAuto1234", "DIAU897" ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 432 )
    public void checkOrganizationDropDownBoxPoupulatedWithUpdatedDistrict() {
        CommonHelper.nap();
        Assert.assertTrue( ( (OrganizationPage) page ).getAllOrgPageOrganizationDropDownValues().contains( "MyDistAuto1234" ), "Updated district not populated in org dropdown" );
        log.info( "Verified that updaed district is populated in org dropdown!" );
    }

    @Test ( priority = 433 )
    public void checkEditDistrictNameAndIDBack() {
        String dist = orgHelper.getAllDistrictsList().get( 0 );
        ( (OrganizationPage) page ).getOrganizationLink( dist ).click();
        CommonHelper.nap();
        CommonHelper.nap();
        Assert.assertTrue( editDistrict( "District", "Dist123" ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 434 )
    public void checkOrganizationDropdownOrderWithUpdatedDistrict() {
        CommonHelper.nap();
        Assert.assertTrue( ( (OrganizationPage) page ).checkOrganizationDropDownInAscendingOrder( "--All Schools--" ), "Not in order" );
        log.info( "Verified that org names are in order!" );
    }

    @Test ( priority = 435 )
    public void checkOrgLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
    }

    public boolean editDistrict( String distName, String orgId ) {
        ( (ManageOrganizationPage) page ).getOrganizationNameInput().clear();
        ( (ManageOrganizationPage) page ).getOrganizationNameInput().sendKeys( distName );
        ( (ManageOrganizationPage) page ).getOrganizationIdInput().clear();
        ( (ManageOrganizationPage) page ).getOrganizationIdInput().sendKeys( orgId );
        ( (ManageOrganizationPage) page ).clickSaveButton();
        return true;
    }
}
