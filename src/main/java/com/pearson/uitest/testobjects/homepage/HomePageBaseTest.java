package com.pearson.uitest.testobjects.homepage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.HomePage;
import com.pearson.uitest.testobjects.AdminBaseTest;

/**
 * Common home page tests across the user types
 * 
 * @author vgaddra
 *
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public abstract class HomePageBaseTest extends AdminBaseTest {

    protected static final String LINK_ALERT = "LinkAlertText";

    @Override
    protected void initialize( String browser ) {
        super.initialize( browser );
        page = new HomePage( driver );
    };

    @Test ( priority = 101 )
    public void checkHelpPageLaunched() {
        page.getHomeTabBtn().click();
        Map<String, String> actualPageDetails = page.launchHelp();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "HomeHelpTitle" ), appProperties.getProperty( "HelpUrl1" ) );
    }

    @Test ( priority = 102 )
    public void checkOrganizationPanelDescription() {
        ( (HomePage) page ).clickHomeTabBtn();
        Assert.assertEquals( ( (HomePage) page ).getOrganizationTableTitle(), appProperties.getProperty( "OrgDesc" ) );
    }

    @Test ( priority = 103 )
    public void checkHomePageCopyRight() {
        Assert.assertEquals( ( (HomePage) page ).homeCopyrgt(), appProperties.getProperty( "HomeCpyRgt" ) );
    }

    @Test ( priority = 104 )
    public void checkOrganizationDropDownTitle() {
        Assert.assertEquals( ( (HomePage) page ).getOrgDropTitle(), appProperties.getProperty( "OrgDropTitle" ) );
    }

    @Test ( priority = 105 )
    public void checkProductDropDownTitle() {
        Assert.assertEquals( ( (HomePage) page ).getProdDropTitle(), appProperties.getProperty( "ProdDropTitle" ) );
    }

    @Test ( priority = 106 )
    public void checkOrganizationPanelTitle() {
        Assert.assertEquals( ( (HomePage) page ).orgTitle(), appProperties.getProperty( "OrgPanelTitle" ) );
    }

    @Test ( priority = 107 )
    public void checkOrganizationPanelEditServerSettingsButtonText() {
        Assert.assertEquals( ( (HomePage) page ).orgEditServerSettingBtn().getText(), appProperties.getProperty( "OrgEditServerSetBtn" ) );
    }

    @Test ( priority = 108 )
    public void checkUserPanelTitle() {
        Assert.assertEquals( ( (HomePage) page ).usrPanelTitle(), appProperties.getProperty( "UsrPanelTitle" ) );
    }

    @Test ( priority = 109 )
    public void checkUserPanelImportButtonPresent() {
        Assert.assertEquals( ( (HomePage) page ).usrImportBtn().getText(), appProperties.getProperty( "UsrImportBtn" ) );
    }

    @Test ( priority = 110 )
    public void checkUserPanelImportButtonClick() {
        String usrImportText = ( (HomePage) page ).clickUsrImportBtn();
        assertBasedOnBrowser( usrImportText, LINK_ALERT );
    }

    @Test ( priority = 111 )
    public void checkUserPanelViewLogButtonText() {
        Assert.assertEquals( ( (HomePage) page ).usrViewLogBtn().getText(), appProperties.getProperty( "UsrViewLogBtn" ) );
    }

    @Test ( priority = 112 )
    public void checkUserPanelViewLogButtonClick() {
        String viewLogText = ( (HomePage) page ).clickUsrViewLogBtn();
        assertBasedOnBrowser( viewLogText, LINK_ALERT );
    }

    @Test ( priority = 113 )
    public void checkUserPanelDescription() {
        Assert.assertEquals( ( (HomePage) page ).usrDescText(), appProperties.getProperty( "UsrDesc" ) );
    }

    @Test ( priority = 114 )
    public void checkRepoprtPanelTitle() {
        Assert.assertEquals( ( (HomePage) page ).repPanelTitle(), appProperties.getProperty( "RepPanelTitle" ) );
    }

    @Test ( priority = 115 )
    public void checkReportPanelAODReportLinkText() {
        Assert.assertEquals( ( (HomePage) page ).repAODRepLink().getText(), appProperties.getProperty( "RepAODRep" ) );
    }

    @Test ( priority = 116 )
    public void checkReportPanelAODReportLinkClick() {
        String aodRepText = ( (HomePage) page ).clickRepAODRepLink();
        assertBasedOnBrowser( aodRepText, LINK_ALERT );
    }

    @Test ( priority = 117 )
    public void checkReportPanelCPReportLinkText() {
        Assert.assertEquals( ( (HomePage) page ).repCPRepLink().getText(), appProperties.getProperty( "RepCPRep" ) );
    }

    @Test ( priority = 118 )
    public void checkReportPanelCPReportLinkClick() {
        String cpRepText = ( (HomePage) page ).clickRepCPRepLink();
        assertBasedOnBrowser( cpRepText, LINK_ALERT );
    }

    @Test ( priority = 119 )
    public void checkReportPanelLSReportLinkText() {
        Assert.assertEquals( ( (HomePage) page ).repLSRepLink().getText(), appProperties.getProperty( "RepLSRep" ) );
    }

    @Test ( priority = 120 )
    public void checkReportPanelLSReportLinkClick() {
        String lsRepText = ( (HomePage) page ).clickRepLSRepLink();
        assertBasedOnBrowser( lsRepText, LINK_ALERT );
    }

    @Test ( priority = 121 )
    public void checkReportPanelPSReportLinkText() {
        Assert.assertEquals( ( (HomePage) page ).repPSRepLink().getText(), appProperties.getProperty( "RepPSRep" ) );
    }

    @Test ( priority = 122 )
    public void checkReportPanelPSReportLinkClick() {
        String psRepText = ( (HomePage) page ).clickRepPSRepLink();
        assertBasedOnBrowser( psRepText, LINK_ALERT );
    }

    @Test ( priority = 123 )
    public void checkReportPanelSPReportLinkText() {
        Assert.assertEquals( ( (HomePage) page ).repSPRepLink().getText(), appProperties.getProperty( "RepSPRep" ) );
    }

    @Test ( priority = 124 )
    public void checkReportPanelSPReportLinkClick() {
        String spRepText = ( (HomePage) page ).clickRepSPRepLink();
        assertBasedOnBrowser( spRepText, LINK_ALERT );
    }

    @Test ( priority = 125 )
    public void checkReportPanelCPAReportLinkText() {
        Assert.assertEquals( ( (HomePage) page ).repCPARepLink().getText(), appProperties.getProperty( "RepCPARep" ) );
    }

    @Test ( priority = 126 )
    public void checkReportPanelCPAReportLinkClick() {
        String cpaRepText = ( (HomePage) page ).clickRepCPARepLink();
        assertBasedOnBrowser( cpaRepText, LINK_ALERT );
    }

    @Test ( priority = 127 )
    public void checkReportPanelPSABTReportLinkText() {
        Assert.assertEquals( ( (HomePage) page ).repPSABTRepLink().getText(), appProperties.getProperty( "RepPSABTRep" ) );
    }

    @Test ( priority = 128 )
    public void checkReportPanelPSABTReportLinkClick() {
        String psabRepText = ( (HomePage) page ).clickRepPSABTRepLink();
        assertBasedOnBrowser( psabRepText, LINK_ALERT );
    }

    @Test ( priority = 129 )
    public void checkReportPanelPSAReportLinkText() {
        Assert.assertEquals( ( (HomePage) page ).repPSARepLink().getText(), appProperties.getProperty( "RepPSARep" ) );
    }

    @Test ( priority = 130 )
    public void checkReportPanelPSAReportLinkClick() {
        String psaRepText = ( (HomePage) page ).clickRepPSARepLink();
        assertBasedOnBrowser( psaRepText, LINK_ALERT );
    }

    @Test ( priority = 131 )
    public void checkReportPanelSEUReportLinkText() {
        Assert.assertEquals( ( (HomePage) page ).repSEURepLink().getText(), appProperties.getProperty( "RepSEURep" ) );
    }

    @Test ( priority = 132 )
    public void checkReportPanelSEUReportLinkClick() {
        String seuRepText = ( (HomePage) page ).clickRepSEURepLink();
        assertBasedOnBrowser( seuRepText, LINK_ALERT );
    }

    @Test ( priority = 133 )
    public void checkMessagePanelTitle() {
        Assert.assertEquals( ( (HomePage) page ).msgPanelTitle(), appProperties.getProperty( "MsgPanelTitle" ) );
    }

    @Test ( priority = 134 )
    public void checkMessagePanelPostMessageButtonClick() {
        String postMsgText = ( (HomePage) page ).clickMsgPostMsgBtn();
        assertBasedOnBrowser( postMsgText, LINK_ALERT );
    }

    @Test ( priority = 135 )
    public void checkMessagePanelPostMessageButtonText() {
        Assert.assertEquals( ( (HomePage) page ).msgPostMsgBtn().getText(), appProperties.getProperty( "MsgPostMsg" ) );
    }

    @Test ( priority = 136 )
    public void checkProductDropDownExists() {
        Assert.assertNotNull( ( (HomePage) page ).getProdDropDown() );
    }

    @Test ( priority = 137 )
    public void checkOrganizationDropDownExists() {
        Assert.assertNotNull( ( (HomePage) page ).getOrganizationDropDown() );
    }

    @Test ( priority = 138 )
    public void checkProductDropDownDefaultSelection() {
        Assert.assertEquals( ( (HomePage) page ).getDefaultProdSelection(), "SuccessMaker" );
    }

    @Test ( priority = 139 )
    public void checkAllProductDropDownValues() {
        List<String> actualDropDownValues = ( (HomePage) page ).getAllProductDropDownValues();
        List<String> expectedDropDownValues = new ArrayList<String>();
        expectedDropDownValues.add( "SuccessMaker" );
        Assert.assertEquals( actualDropDownValues.size(), expectedDropDownValues.size() );
        expectedDropDownValues.removeAll( actualDropDownValues );
        Assert.assertEquals( expectedDropDownValues.size(), 0 );
    }

    @Test ( priority = 140 )
    public void checkOrganizationDropDownValuesDisplayedInAscendingOrder() {
        List<String> actualOrgNames = ( (HomePage) page ).getAllOrganizationDropDownValues();
        List<String> expectedOrgNames = new ArrayList<String>( actualOrgNames );
        CommonHelper.sortAscendingWithIgnoreCase( expectedOrgNames );
        Assert.assertEquals( actualOrgNames, expectedOrgNames );
    }

}
