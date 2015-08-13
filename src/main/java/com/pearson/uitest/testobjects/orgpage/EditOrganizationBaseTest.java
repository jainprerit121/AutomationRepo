package com.pearson.uitest.testobjects.orgpage;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.OrganizationConstants;
import com.pearson.uitest.enums.DeploymentTypes;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.ManageOrganizationPage;
import com.pearson.uitest.pageobjects.OrganizationPage;

/**
 * 
 * @author vgaddra
 * 
 */
public abstract class EditOrganizationBaseTest extends ManageOrganizationBaseTest {

    protected void initialize( String browser ) {
        super.initialize( browser );
        page = new ManageOrganizationPage( driver );
    }

    public EditOrganizationBaseTest() {
        page = new ManageOrganizationPage( driver );
    }

    @Test ( priority = 201 )
    public void checkURLPostOrgPageHelpButtonClick() {
        page.getOrgTabBtn().click();
        CommonHelper.nap();
        CommonHelper.nap();
        ( (ManageOrganizationPage) page ).getConfirmationNoButton().click();
        CommonHelper.nap();
        CommonHelper.nap();
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        CommonHelper.nap();
        Map<String, String> actualPageDetails = ( (OrganizationPage) page ).clickOrgPageHelpButton();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "EditOrgHelpTitle" ), appProperties.getProperty( "EditOrgHelpUrl" ) );
    }

    @Test ( priority = 202 )
    public void clickExitButtonWithNoModificationinEditOrgPage() {
        ( (ManageOrganizationPage) page ).getEditOrgCancelButton().click();
        Assert.assertEquals( driver.getCurrentUrl(), appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ), "URL String is not Org landing Page URL" );
        log.info( "Verified that Organization landing page URL navigated!" );
    }

    @Test ( priority = 203 )
    public void clickExitButtonAfterRevertChangesEditOrgPage() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        CommonHelper.nap();
        ( (ManageOrganizationPage) page ).getOrganizationNameInput().sendKeys( "Editing" );
        ( (ManageOrganizationPage) page ).getOrganizationNameInput().clear();
        ( (ManageOrganizationPage) page ).getOrganizationNameInput().sendKeys( AdminUIConstants.NEW_SCHOOL_NAME );
        ( (ManageOrganizationPage) page ).getEditOrgCancelButton().click();
        log.info( "Current URL: " + driver.getCurrentUrl() );
        log.info( "Expected URL: " + appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) );
        Assert.assertEquals( driver.getCurrentUrl(), appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ), "URL String is not Org landing Page URL" );
        log.info( "Verified that Organization landing page URL navigated!" );
    }

    @Test ( priority = 204, enabled = false )
    public void checkOrganizationNameDefaultContent() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        log.info( "Editing the organization - " + AdminUIConstants.NEW_SCHOOL_NAME );
        CommonHelper.nap();
        log.info( "Actual Org Name content: " + ( (ManageOrganizationPage) page ).getOrganizationNameInput().getText() );
        log.info( "Expected content: " + AdminUIConstants.NEW_SCHOOL_NAME );
        Assert.assertTrue( AdminUIConstants.NEW_SCHOOL_NAME.equals( ( (ManageOrganizationPage) page ).getOrganizationNameInput().getText() ), "Organization Name input field is not populated with " + AdminUIConstants.NEW_SCHOOL_NAME );
        log.info( "Verified the default content of Org Name - " + AdminUIConstants.NEW_SCHOOL_NAME );
    }

    @Test ( priority = 205, enabled = false )
    public void checkOrganizationIDDefaultContent() {
        Assert.assertTrue( AdminUIConstants.NEW_SCHOOL_ID.equals( ( (ManageOrganizationPage) page ).getOrganizationIdInput().getText() ), "Organization ID input field is not populated with " + AdminUIConstants.NEW_SCHOOL_ID );
        log.info( "Verified the default content of Org ID - " + AdminUIConstants.NEW_SCHOOL_ID );
    }

    @Test ( priority = 206 )
    public void checkOrganizationCancelButttonText() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        log.info( "Editing the organization - " + AdminUIConstants.NEW_SCHOOL_NAME );
        CommonHelper.nap();
        Assert.assertEquals( ( (ManageOrganizationPage) page ).getOrganizationCancelButton().getText(), appProperties.getProperty( "EditOrgExitBtn" ) );
        log.info( "Verified the cancel button text of Edit Org page!" );
    }

    @Test ( priority = 207 )
    public void checkOrganizationDeploymentTypeDropdown() {
        List<String> actualList = ( (ManageOrganizationPage) page ).getOrganizationDeploymentDropdownValues();
        List<String> expectedList = DeploymentTypes.getDeploymentTypeNames();
        expectedList.remove( DeploymentTypes.DISTRICT.getName() );
        Assert.assertEquals( actualList, expectedList );
        log.info( "Verified the deployment dropdown items" );
    }

    @Test ( priority = 208 )
    public void checkOrganizationDistrictDefaultSelection() {
        String actualSelection = ( (ManageOrganizationPage) page ).getOrganizationDistrictDefaultSelection();
        log.info( "Actual default district: " + actualSelection );
        log.info( "Expected: " + DeploymentTypes.DISTRICT.getName() );
        Assert.assertEquals( actualSelection, orgHelper.getAllDistrictsList().get( 0 ) );
        log.info( "Verified the default district option!" );
    }

    @Test ( priority = 209 )
    public void checkOrganizationDistrictDropDownFirstOption() {
        String actualSelection = ( (ManageOrganizationPage) page ).getOrganizationDistrictDefaultSelection();
        Assert.assertEquals( actualSelection, orgHelper.getAllDistrictsList().get( 0 ) );
    }

    @Test ( priority = 210 )
    public void editOrganizationPageURL() {
        Assert.assertEquals( driver.getCurrentUrl(), appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ), "URL String is not Edit Org Page URL" );
        log.info( "Verified the URL of Edit organization page!" );
    }

    @Test ( priority = 211 )
    public void checkDetailsTabPresent() {
        Assert.assertNotNull( ( (ManageOrganizationPage) page ).getDetailsTab(), "Details tab is not present" );
        log.info( "Verified that Details tab is present" );
    }

    @Test ( priority = 212 )
    public void checkDetailsTabHighlighted() {
        Assert.assertEquals( ( (ManageOrganizationPage) page ).getDetailsTab().getAttribute( "class" ), appProperties.getProperty( "tabActiveStatus" ) );
        log.info( "Verified that Details tab is highlighted!" );
    }

    @Test ( priority = 213 )
    public void checkLicenseTabPresent() {
        Assert.assertNotNull( ( (ManageOrganizationPage) page ).getLicensesTab(), "License tab is not present" );
        log.info( "Verified that License tab is present" );

    }

    @Test ( priority = 214 )
    public void checkDataSettingsTabPresent() {
        Assert.assertNotNull( ( (ManageOrganizationPage) page ).getDataSettingsTab(), "Data Settings tab is not present" );
        log.info( "Verified that Data Settings tab is present" );
    }

    @Test ( priority = 216 )
    public void checkClickDataSettingsTab() {
        Map<String, String> actualPageDetails = ( (ManageOrganizationPage) page ).clickDataSettingsTab();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "datasettingsurl" ) );
        log.info( "Verified the data settings tab url in Edit Org!" );
    }

    @Test ( priority = 217 )
    public void checkEditOrganizationContentBarText() {
        ( (ManageOrganizationPage) page ).getDetailsTabLink().click();
        CommonHelper.nap();
        log.info( "Actual Msg: " + ( (OrganizationPage) page ).getOrgWelcomeMsg() );
        log.info( "Expected: " + "Edit \"" + AdminUIConstants.NEW_SCHOOL_NAME + "\" for \"SuccessMaker\"" );
        Assert.assertEquals( ( (OrganizationPage) page ).getOrgWelcomeMsg(), "Edit \"" + AdminUIConstants.NEW_SCHOOL_NAME + "\" for \"SuccessMaker\"", "Content Title is not as eexpcted" );
        log.info( "Verified that the content title is as expected!" );
    }

    @Test ( priority = 218 )
    public void checkUpdateWithExistingSchoolName() {
        String retAlert = addOrganization( OrganizationConstants.ADDTIONAL_ORG_NAME, OrganizationConstants.MY_ORG_ID, 0, 1 );
        Assert.assertTrue( retAlert.trim().replaceAll( "\n", " " ).equals( appProperties.getProperty( "EditExistingName" ) ), "Application accepts to edit an org name with an existing org name" );
        log.info( "Verified that application does not allow to edit an organization with an existing name" );
    }

    @Test ( priority = 219 )
    public void checkUpdateWithExistingSchoolID() {
        String retAlert = addOrganization( OrganizationConstants.MY_ORGANIZATION_NAME, OrganizationConstants.ADDITIONAL_ORG_ID, 0, 1 );
        Assert.assertTrue( retAlert.trim().replaceAll( "\n", " " ).equals( appProperties.getProperty( "EditExistingId" ) ), "Application accepts to edit an org name with an existing org name" );
        log.info( "Verified that application does not allow to edit an organization with an existing name" );

    }

    @Test ( priority = 220 )
    public void checkUpdateWithExistingSchoolNameAndID() {
        String retAlert = addOrganization( OrganizationConstants.ADDTIONAL_ORG_NAME, OrganizationConstants.ADDITIONAL_ORG_ID, 0, 1 );
        Assert.assertTrue( retAlert.trim().replaceAll( "\n", " " ).equals( appProperties.getProperty( "EditExistingId" ) ), "Application accepts to edit an org name with an existing org name" );
        log.info( "Verified that application does not allow to edit an organization with an existing name" );
    }

    //Exit organization
    @Test ( priority = 221 )
    public void checkClickExitCancelOptionAfterChangingName() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_EDIT_ORG_CANCEL_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 222 )
    public void checkClickExitCancelOptionAfterChangingID() {
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_EDIT_ORG_CANCEL_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 223 )
    public void checkClickExitCancelOptionAfterChangingNameAndID() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_EDIT_ORG_CANCEL_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 224 )
    public void checkClickExitNoOptionAfterChangingName() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_EDIT_ORG_CANCEL_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 225 )
    public void checkSchoolNameNotAlteredInOrgTable() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 226 )
    public void checkClickExitNoOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        CommonHelper.nap();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_EDIT_ORG_CANCEL_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 227 )
    public void checkSchoolIDNotAlteredInOrgTable() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 228 )
    public void checkClickExitNoOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_EDIT_ORG_CANCEL_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 229 )
    public void checkSchoolNameAndIDNotAlteredInOrgTable() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 230 )
    public void checkClickExitYesOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_EDIT_ORG_CANCEL_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 231 )
    public void checkSchoolNameAlteredInOrgTable() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 232 )
    public void checkClickExitYesOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_EDIT_ORG_CANCEL_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 233 )
    public void checkSchoolIDAlteredInOrgTable() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 234 )
    public void checkOldSchoolDoesNotExistInOrganization() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ), OrganizationConstants.ERROR_MESSAGE_OLD_ORG_EXIST + AdminUIConstants.NEW_SCHOOL_NAME );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 235 )
    public void checkClickExitYesOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_EDIT_ORG_CANCEL_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    //Edit Setttings
    @Test ( priority = 236 )
    public void checkClickEditSettingsCancelOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_DATA_SETTINGS_TAB_LINK, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 237 )
    public void checkClickEditSettingsCancelOptionAfterChangingID() {
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_DATA_SETTINGS_TAB_LINK, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 238 )
    public void checkClickEditSettingsCancelOptionAfterChangingNameAndID() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_DATA_SETTINGS_TAB_LINK, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 239 )
    public void checkClickEditSettingsNoOptionAfterChangingName() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_DATA_SETTINGS_TAB_LINK, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_DATA_SETTINGS_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 240 )
    public void checkSchoolNameNotAlteredInOrgTablePostPostDataSettingsNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 241 )
    public void checkClickEditSettingsNoOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        CommonHelper.nap();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_DATA_SETTINGS_TAB_LINK, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_DATA_SETTINGS_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 242 )
    public void checkSchoolIDNotAlteredInOrgTablePostPostDataSettingsNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 243 )
    public void checkClickEditSettingsNoOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_DATA_SETTINGS_TAB_LINK, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_DATA_SETTINGS_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 244 )
    public void checkSchoolNameAndIDNotAlteredInOrgTablePostPostDataSettingsNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 245 )
    public void checkClickEditSettingsYesOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_DATA_SETTINGS_TAB_LINK, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_DATA_SETTINGS_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 246 )
    public void checkSchoolNameAlteredInOrgTablePostDataSettingNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 247 )
    public void checkClickEditSettingsYesOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_DATA_SETTINGS_TAB_LINK, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_DATA_SETTINGS_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 248 )
    public void checkSchoolIDAlteredInOrgTablePostDataSettingsNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 249 )
    public void checkOldSchoolDoesNotExistInOrganizationPostDataSettingsTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ), OrganizationConstants.ERROR_MESSAGE_OLD_ORG_EXIST + AdminUIConstants.NEW_SCHOOL_NAME );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 250 )
    public void checkClickEditSettingsYesOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_DATA_SETTINGS_TAB_LINK, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_DATA_SETTINGS_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    //Home page
    @Test ( priority = 251 )
    public void checkClickHomeTabCancelOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_HOME_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 252 )
    public void checkClickHomeTabCancelOptionAfterChangingID() {
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_HOME_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 253 )
    public void checkClickHomeTabCancelOptionAfterChangingNameAndID() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_HOME_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 254 )
    public void checkClickHomeTabNoOptionAfterChangingName() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_HOME_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.HOME_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 255 )
    public void checkSchoolNameNotAlteredInOrgTablePostHomeTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 256 )
    public void checkClickHomeTabNoOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        CommonHelper.nap();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_HOME_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.HOME_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 257 )
    public void checkSchoolIDNotAlteredInOrgTablePostHomeTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 258 )
    public void checkClickHomeTabNoOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_HOME_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.HOME_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 259 )
    public void checkSchoolNameAndIDNotAlteredInOrgTablePostHomeTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 260 )
    public void checkClickHomeTabYesOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_HOME_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.HOME_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 261 )
    public void checkSchoolNameAlteredInOrgTablePostHomeTabNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 262 )
    public void checkClickHomeTabYesOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_HOME_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.HOME_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 263 )
    public void checkSchoolIDAlteredInOrgTablePostHomeTabNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 264 )
    public void checkOldSchoolDoesNotExistInOrganizationPostHomeTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ), OrganizationConstants.ERROR_MESSAGE_OLD_ORG_EXIST + AdminUIConstants.NEW_SCHOOL_NAME );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 265 )
    public void checkClickHomeTabYesOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_HOME_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.HOME_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    //Organization Tab
    @Test ( priority = 266 )
    public void checkClickOrganizationTabCancelOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_ORG_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 267 )
    public void checkClickOrganizationTabCancelOptionAfterChangingID() {
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_ORG_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 268 )
    public void checkClickOrganizationTabCancelOptionAfterChangingNameAndID() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_ORG_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 269 )
    public void checkClickOrganizationTabNoOptionAfterChangingName() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_ORG_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 270 )
    public void checkSchoolNameNotAlteredInOrgTablePostOrganizationTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 271 )
    public void checkClickOrganizationTabNoOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        CommonHelper.nap();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_ORG_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 272 )
    public void checkSchoolIDNotAlteredInOrgTablePostOrganizationTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 273 )
    public void checkClickOrganizationTabNoOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_ORG_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 274 )
    public void checkSchoolNameAndIDNotAlteredInOrgTablePostOrganizationTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 275 )
    public void checkClickOrganizationTabYesOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_ORG_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 276 )
    public void checkSchoolNameAlteredInOrgTablePostOrganizationTabNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 277 )
    public void checkClickOrganizationTabYesOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_ORG_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 278 )
    public void checkSchoolIDAlteredInOrgTablePostOrganizationTabNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 279 )
    public void checkOldSchoolDoesNotExistInOrganizationPostOrganizationTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ), OrganizationConstants.ERROR_MESSAGE_OLD_ORG_EXIST + AdminUIConstants.NEW_SCHOOL_NAME );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 280 )
    public void checkClickOrganizationTabYesOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_ORG_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.ORG_LANDING_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    //user tab
    @Test ( priority = 281 )
    public void checkClickUserTabCancelOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_USERS_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 282 )
    public void checkClickUserTabCancelOptionAfterChangingID() {
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_USERS_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 283 )
    public void checkClickUserTabCancelOptionAfterChangingNameAndID() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_USERS_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 284 )
    public void checkClickUserTabNoOptionAfterChangingName() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_USERS_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.USER_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 285 )
    public void checkSchoolNameNotAlteredInOrgTableUserTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 286 )
    public void checkClickUserTabNoOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        CommonHelper.nap();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_USERS_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.USER_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 287 )
    public void checkSchoolIDNotAlteredInOrgTableUserTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 288 )
    public void checkClickUserTabNoOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_USERS_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.USER_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 289 )
    public void checkSchoolNameAndIDNotAlteredInOrgTableUserTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 290 )
    public void checkClickUserTabYesOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_USERS_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.USER_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 291 )
    public void checkSchoolNameAlteredInOrgTableUserTabNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 292 )
    public void checkOrganizationDropDownBoxPoupulatedWithUpdatedSchool() {
        Assert.assertTrue( ( (OrganizationPage) page ).getAllOrgPageOrganizationDropDownValues().contains( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), "Updated school not populated in org dropdown" );
        log.info( "Verified that updaed school populated with updated school" );
    }

    @Test ( priority = 293 )
    public void checkOrganizationDropdownOrderWithUpdatedOrganization() {
        Assert.assertTrue( ( (OrganizationPage) page ).checkOrganizationDropDownInAscendingOrder( "--All Schools--" ), "Not in order" );
        log.info( "Verified that org names are in order!" );
    }

    @Test ( priority = 295 )
    public void checkClickUserTabYesOptionAfterChangingID() {
        page.getOrgTabBtn().click();
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_USERS_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.USER_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 296 )
    public void checkSchoolIDAlteredInOrgTablePostUserTabNavigation() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 297 )
    public void checkOldSchoolDoesNotExistInOrganizationPostUserTabNavigation() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ), OrganizationConstants.ERROR_MESSAGE_OLD_ORG_EXIST + AdminUIConstants.NEW_SCHOOL_NAME );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 298 )
    public void checkClickUserTabYesOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( "MySchool1234Auto", OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_USERS_TAB_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON, appProperties.getProperty( OrganizationConstants.USER_PAGE_URL ) ),
                OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 299 )
    public void checkEditOrgByClickSaveButton() {
        ( (OrganizationPage) page ).getOrganizationLink( "MySchool1234Auto" ).click();
        CommonHelper.nap();
        String retAlert = addOrganization( AdminUIConstants.NEW_SCHOOL_NAME, AdminUIConstants.NEW_SCHOOL_ID, 0, 1 );
        Assert.assertTrue( retAlert.trim().replaceAll( "\n", " " ).equals( appProperties.getProperty( "EditOrgSuccess" ) ), "Org not updated after edit" );
    }

    //Logout
    @Test ( priority = 300 )
    public void checkClickLogoutCancelOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LOGOUT_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 301 )
    public void checkClickLogoutCancelOptionAfterChangingID() {
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_LOGOUT_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 302 )
    public void checkClickLogoutCancelOptionAfterChangingNameAndID() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.MY_ORGANIZATION_NAME, OrganizationConstants.MY_ORG_ID, OrganizationConstants.GET_LOGOUT_BUTTON, OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON,
                        appProperties.getProperty( OrganizationConstants.EDIT_ORG_PAGE_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 303 )
    public void checkClickLogoutNoOptionAfterChangingName() {
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LOGOUT_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LOGOUT_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 304 )
    public void checkSchoolNameNotAlteredInOrgTablePostLogout() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 305 )
    public void checkClickLogoutNoOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        CommonHelper.nap();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_LOGOUT_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LOGOUT_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 306 )
    public void checkSchoolIDNotAlteredInOrgTablePostLogout() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 307 )
    public void checkClickLogoutNoOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_LOGOUT_BUTTON, OrganizationConstants.GET_CONFIRM_NO_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LOGOUT_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 308 )
    public void checkSchoolNameAndIDNotAlteredInOrgTablePostLogout() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_NO_OPTION );
        Assert.assertNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_NO_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 309 )
    public void checkClickLogoutYesOptionAfterChangingName() {
        ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LOGOUT_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LOGOUT_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 310 )
    public void checkSchoolNameAlteredInOrgTablePostLogout() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ), OrganizationConstants.ERROR_MESSAGE_NAME_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 311 )
    public void checkClickLogoutYesOptionAfterChangingID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( OrganizationConstants.UPDATE_EDIT_ORG_NAME, OrganizationConstants.EDIT_MY_ORD_ID, OrganizationConstants.GET_LOGOUT_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON,
                        appProperties.getProperty( OrganizationConstants.LOGOUT_URL ) ), OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 312 )
    public void checkSchoolIDAlteredInOrgTablePostLogout() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgIdTableElement( OrganizationConstants.EDIT_MY_ORD_ID ), OrganizationConstants.ERROR_MESSAGE_ID_YES_OPTION );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 313 )
    public void checkOldSchoolDoesNotExistInOrganizationPostLogout() {
        Assert.assertNull( ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ), OrganizationConstants.ERROR_MESSAGE_OLD_ORG_EXIST + AdminUIConstants.NEW_SCHOOL_NAME );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }

    @Test ( priority = 314 )
    public void checkClickLogoutYesOptionAfterChangingNameAndID() {
        ( (OrganizationPage) page ).getOrganizationLink( OrganizationConstants.UPDATE_EDIT_ORG_NAME ).click();
        Assert.assertTrue(
                verifyEditExitScenarios( AdminUIConstants.NEW_SCHOOL_NAME, AdminUIConstants.NEW_SCHOOL_ID, OrganizationConstants.GET_LOGOUT_BUTTON, OrganizationConstants.GET_CONFIRM_YES_BUTTON, appProperties.getProperty( OrganizationConstants.LOGOUT_URL ) ),
                OrganizationConstants.VERIFICATION_FAILED_MESSAGE );
        log.info( OrganizationConstants.VERIFICATION_SUCCESS_MESSAGE );
    }
}