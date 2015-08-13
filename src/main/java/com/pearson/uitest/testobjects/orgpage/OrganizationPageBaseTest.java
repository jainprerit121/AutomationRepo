package com.pearson.uitest.testobjects.orgpage;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.OrganizationPage;
import com.pearson.uitest.testobjects.AdminBaseTest;

/**
 * Common tests of organization landing page for System and School admin
 * 
 * @author Nagarajan Ganesan
 *
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public abstract class OrganizationPageBaseTest extends AdminBaseTest {

    @Override
    protected void initialize( String browser ) {
        super.initialize( browser );
        page = new OrganizationPage( driver );
    };

    @Test ( priority = 101 )
    public void checkOrgWelcomeMsg() {
        page.getOrgTabBtn().click();
        CommonHelper.nap();
        log.info( "Actual Welcome Message: " + ( (OrganizationPage) page ).getOrgWelcomeMsg() );
        log.info( "Expected Welcome Message: " + appProperties.getProperty( "OrgWelcome" ) );
        Assert.assertEquals( ( (OrganizationPage) page ).getOrgWelcomeMsg(), appProperties.getProperty( "OrgWelcome" ) );
    }

    @Test ( priority = 102 )
    public void checkOrganizationsTabBtnHighlighted() {
        Assert.assertEquals( page.getOrgTabBtn().getAttribute( "class" ), "ng-binding active" );
    }

    @Test ( priority = 103 )
    public void checkOrgDisplayDropDownLabel() {
        Assert.assertEquals( ( (OrganizationPage) page ).getOrgLandDisplayDropTitle(), appProperties.getProperty( "OrgDispDropLabel" ) );
    }

    @Test ( priority = 104 )
    public void checkOrgDisplayDropDownList() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgDisplayDropdown(), "Organization dropdown list is not present" );
    }

    @Test ( priority = 105 )
    public void checkOrgLandingPageTableHeaderName() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgLandTableNameHeader(), "Organization dropdown list is not present" );
    }

    @Test ( priority = 106 )
    public void checkOrgLandingPageTableHeaderID() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgLandTableIDHeader(), "Organization dropdown list is not present" );
    }

    @Test ( priority = 107 )
    public void checkOrgLandingPageTableHeaderOption() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgLandTableOptionHeader(), "Organization dropdown list is not present" );
    }

    @Test ( priority = 108 )
    public void checkAddOrganizationButtonPresence() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgLandAddOrgBtn(), "Add Organization Button is not present" );
    }

    @Test ( priority = 109 )
    public void checkAddOrganizationButtonText() {
        Assert.assertNotNull( ( (OrganizationPage) page ).getOrgLandAddOrgBtn().getText(), appProperties.getProperty( "OrgAddOrg" ) );
    }

    @Test ( priority = 110 )
    public void checkURLPostOrgPageHelpButtonClick() {
        Map<String, String> actualPageDetails = ( (OrganizationPage) page ).launchHelp();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "ViewOrgHelpTitle" ), appProperties.getProperty( "OrgHelpUrl2" ) );
    }

    @Test ( priority = 111 )
    public void checkOrganizationDropDownValuesDisplayedInAscendingOrder() {
        Assert.assertTrue( ( (OrganizationPage) page ).checkOrganizationDropDownInAscendingOrder( appProperties.getProperty( "DefaultSelection" ) ), "Org dropdown not in ascending order" );
        log.info( "Verified that organization dropdown is in ascending order!" );
    }
}
