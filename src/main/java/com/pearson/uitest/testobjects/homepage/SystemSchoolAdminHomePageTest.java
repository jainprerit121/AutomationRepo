package com.pearson.uitest.testobjects.homepage;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pearson.uitest.pageobjects.HomePage;

/**
 * Common functional tests applicable for School and System Admin
 * 
 * @author vgaddra
 *
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public abstract class SystemSchoolAdminHomePageTest extends HomePageBaseTest {

    @Test ( priority = 201 )
    public void checkOrgBtnPresent() {
        Assert.assertNotNull( page.getOrgTabBtn(), "Organizations Tab button is not present." );
    }

    @Test ( priority = 202 )
    public void checkOrgBtn() {
        Assert.assertEquals( page.getOrgTabBtn().getText(), appProperties.getProperty( "HomeOrgBtn" ) );
    }

    @Test ( priority = 203 )
    public void checkOrgTabBtnClick() {
        Map<String, String> actualPageDetails = page.clickOrgTabBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "OrgPageUrl" ) );
        ( (HomePage) page ).clickHomeTabBtn();
    }

    @Test ( priority = 204 )
    public void checkOrgEditDataSettingLink() {
        Assert.assertEquals( ( (HomePage) page ).getOrgEditDatSetLink().getText(), appProperties.getProperty( "OrgEditDataSetting" ) );
    }

    @Test ( priority = 205 )
    public void checkOrgEditDataSettingClick() {
        Map<String, String> actualPageDetails = ( (HomePage) page ).clickOrgEditDataSetLink();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "EditOrgDataSetUrl" ) );
        ( (HomePage) page ).clickHomeTabBtn();
    }

    @Test ( priority = 206 )
    public void checkOrgEditOrgDetailsLink() {
        Assert.assertEquals( ( (HomePage) page ).getOrgEditOrgDetLink().getText(), appProperties.getProperty( "OrgEditOrgDetails" ) );
    }

    @Test ( priority = 207 )
    public void checkOrgEditOrgDetailsClick() {
        Map<String, String> actualPageDetails = ( (HomePage) page ).clickOrgEditOrgDetLink();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "EditOrgDetailsUrl" ) );
        ( (HomePage) page ).clickHomeTabBtn();
    }

    @Test ( priority = 208 )
    public void checkUsrManageGrp() {
        Assert.assertEquals( ( (HomePage) page ).getUserMangeGrpLink().getText(), appProperties.getProperty( "UsrManageGrp" ) );
    }

    @Test ( priority = 209 )
    public void usrManageGrpClick() {
        String manageGrpText = ( (HomePage) page ).clickUsrManageGrpLink();
        assertBasedOnBrowser( manageGrpText, LINK_ALERT );
    }

    @Test ( priority = 210 )
    public void checkUsrAddUser() {
        Assert.assertEquals( ( (HomePage) page ).getUsrAddUsrLink().getText(), appProperties.getProperty( "UsrAddUsr" ) );
    }

    @Test ( priority = 211 )
    public void usrAddUserClick() {
        String addUsrText = ( (HomePage) page ).clickUsrAddUsrLink();
        assertBasedOnBrowser( addUsrText, LINK_ALERT );
    }
}
