package com.pearson.uitest.testobjects.orgpage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.OrganizationConstants;
import com.pearson.uitest.pageobjects.EditDataSettingsPage;

/**
 * 
 * This class is used to test edit data settings feature using system admin
 * login.
 * 
 * @author Ramakrishna Gaddam
 *
 */
public class SystemAdminEditDataSettingsPageTest extends EditDataSettingsBaseTest {

    public SystemAdminEditDataSettingsPageTest() {
        log = Logger.getLogger( SystemAdminEditDataSettingsPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( OrganizationConstants.DATA_SETTINGS_ORGANIZATION_NAME );
        orgHelper.createOrganization( OrganizationConstants.DATA_SETTINGS_ORGANIZATION_NAME, OrganizationConstants.DATA_SETTINGS_ORGANIZATION_ID );
        gotoOrganizationTab( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD, OrganizationConstants.DATA_SETTINGS_ORGANIZATION_NAME );
    }

    @Test ( priority = 401 )
    public void checkLicenseTabButtonIsPresent() {
        ( (EditDataSettingsPage) page ).clickOrgTabBtn();
        ( (EditDataSettingsPage) page ).clickOrganizationNameLink( dataSettingsSchoolSelection );
        ( (EditDataSettingsPage) page ).clickDataSettingsTab();
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLicenseTabBtn() != null, "'License' Tab button on top menu is not present" );
    }

    @Test ( priority = 401 )
    public void checkLicenseTabIsPresent() {
        Assert.assertNotEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getLicenseTab(), "class" ), "displayNone" );
    }

    @AfterClass
    public void logout() {
        page.logoutAdminPanel( browser );
    }

}
