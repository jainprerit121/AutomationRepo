package com.pearson.uitest.testobjects.orgpage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.pageobjects.EditDataSettingsPage;

/**
 * 
 * This class is used to test edit data settings feature using school admin
 * login.
 * 
 * @author Ramakrishna Gaddam
 *
 */
public class SchoolAdminEditDataSettingsPageTest extends EditDataSettingsBaseTest {

    public SchoolAdminEditDataSettingsPageTest() {
        log = Logger.getLogger( SchoolAdminEditDataSettingsPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        gotoOrganizationTab( AdminUIConstants.SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD, AdminUIConstants.NEW_SCHOOL_NAME );
    }

    @Test ( priority = 401 )
    public void checkLicenseTabButtonIsNotPresent() {
        ( (EditDataSettingsPage) page ).clickOrgTabBtn();
        ( (EditDataSettingsPage) page ).clickOrganizationNameLink( dataSettingsSchoolSelection );
        ( (EditDataSettingsPage) page ).clickDataSettingsTab();
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLicenseTabBtn() == null, "'License' Tab button on top menu is present" );
    }

    @Test ( priority = 401 )
    public void checkLicenseTabIsNotPresent() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getLicenseTab(), "class" ), "displayNone" );
    }

    @AfterClass
    public void logout() {
        page.logoutAdminPanel( browser );
    }

}
