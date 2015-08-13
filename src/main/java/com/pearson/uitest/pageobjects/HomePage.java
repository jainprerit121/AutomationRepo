package com.pearson.uitest.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.pearson.uitest.helper.CommonHelper;

/**
 * This class defines the page objects of the home page
 * 
 * @author vgaddra
 *
 */
public class HomePage extends AdminBasePage {

    private By prodDropdown = By.id( "homeProductfilterOption" );
    private By orgPanelDesc = By.id( "homePanelTextFirst" );
    private By homeCpyRgt = By.id( "homeFooter" );
    private By welcomeText = By.id( "contentBar" );
    private By orgDropdownTitle = By.id( "homeorganisationfilterText" );
    private By prodDropdownTitle = By.id( "homeProductfilterText" );
    private By orgPanelTitle = By.id( "homePanelTitleFirst" );
    private By orgManageLic = By.linkText( "Manage licenses" );
    private By orgEditDataSet = By.linkText( "Edit data settings" );
    private By orgEditOrgDet = By.linkText( "Edit organization details" );
    private By orgMDHS = By.linkText( "Manage district holiday schedule" );
    private By orgEditServSetBtn = By.linkText( "Edit Server Settings" );
    private By usrPanelTitle = By.id( "homePanelHeaderTextSecond" );
    private By usrPanelImportBtn = By.id( "PanelSecondlinkSecond" );
    private By usrPanelViewLogBtn = By.id( "PanelSecondlinkOne" );
    private By usrPanelDesc = By.id( "homePanelTextSecond" );
    private By usrTransStud = By.linkText( "Transfer Students" );
    private By usrMangeGrp = By.linkText( "Manage groups" );
    private By usrAddUsr = By.linkText( "Add users" );
    private By repPanelTitle = By.id( "homePanelTitleThird" );
    private By repPanelAODReport = By.id( "HomePanelThirdLinks0" );
    private By repPanelCPReport = By.id( "HomePanelThirdLinks1" );
    private By repPanelLSReport = By.id( "HomePanelThirdLinks2" );
    private By repPanelPSReport = By.id( "HomePanelThirdLinks3" );
    private By repPanelSPReport = By.id( "HomePanelThirdLinks4" );
    private By repPanelCPAReport = By.id( "HomePanelThirdLinks5" );
    private By repPanelPSABTReport = By.id( "HomePanelThirdLinks6" );
    private By repPanelPSAReport = By.id( "HomePanelThirdLinks7" );
    private By repPanelSEUReport = By.id( "HomePanelThirdLinks8" );
    private By msgPanelTitle = By.id( "homePanelTitleFourth" );
    private By msgPanelPostMsgBtn = By.id( "homePanelTitleLinkThird" );
    private By orgAddOrg = By.linkText( "Add organization" );
    private By orgDropDown = By.id( "homeOrganisationFilterSelect" );

    public HomePage( WebDriver driver ) {
        super( driver );
        log = Logger.getLogger( HomePage.class );
    }

    public String getOrganizationTableTitle() {
        return getElement( orgPanelDesc ).getText();
    }

    public WebElement getOrganizationDropDown() {
        return getElement( orgDropDown );
    }

    public String homeCopyrgt() {
        return getElement( homeCpyRgt ).getText();
    }

    public String getWelcomeMsg() {
        return getElement( welcomeText ).getText();
    }

    public String getOrgDropTitle() {
        return getElement( orgDropdownTitle ).getText();
    }

    public String getProdDropTitle() {
        return getElement( prodDropdownTitle ).getText();
    }

    public String orgTitle() {
        return getElement( orgPanelTitle ).getText();
    }

    public WebElement getOrgManageLicLink() {
        return getElement( orgManageLic );
    }

    public Map<String, String> clickOrgManageLicLink() {
        return getPageDetailsAfterClick( getOrgManageLicLink() );
    }

    public WebElement getOrgEditDatSetLink() {
        return getElement( orgEditDataSet );
    }

    public Map<String, String> clickOrgEditDataSetLink() {
        return getPageDetailsAfterClick( getOrgEditDatSetLink() );
    }

    public WebElement getOrgEditOrgDetLink() {
        return getElement( orgEditOrgDet );
    }

    public Map<String, String> clickOrgEditOrgDetLink() {
        return getPageDetailsAfterClick( getOrgEditOrgDetLink() );
    }

    public WebElement getOrgMDHSLink() {
        return getElement( orgMDHS );
    }

    public String clickOrgMDHSLink() {
        return clickOnButton( getOrgMDHSLink() );
    }

    public WebElement orgEditServerSettingBtn() {
        return getElement( orgEditServSetBtn );
    }

    public String usrPanelTitle() {
        return getElement( usrPanelTitle ).getText();
    }

    public WebElement usrImportBtn() {
        return getElement( usrPanelImportBtn );
    }

    public String clickUsrImportBtn() {
        return clickOnButton( usrImportBtn() );
    }

    public WebElement usrViewLogBtn() {
        return getElement( usrPanelViewLogBtn );
    }

    public String clickUsrViewLogBtn() {
        return clickOnButton( usrViewLogBtn() );
    }

    public String usrDescText() {
        return getElement( usrPanelDesc ).getText();
    }

    public WebElement getUsrTransStudLink() {
        return getElement( usrTransStud );
    }

    public String clickUsrTransStudLink() {
        return clickOnButton( getUsrTransStudLink() );
    }

    public WebElement getUserMangeGrpLink() {
        return getElement( usrMangeGrp );
    }

    public WebElement getUsrAddUsrLink() {
        return getElement( usrAddUsr );
    }

    public String repPanelTitle() {
        return getElement( repPanelTitle ).getText();
    }

    // Get areas of diff report link
    public WebElement repAODRepLink() {
        return getElement( repPanelAODReport );
    }

    public WebElement repCPRepLink() {
        return getElement( repPanelCPReport );
    }

    public WebElement repLSRepLink() {
        return getElement( repPanelLSReport );
    }

    public WebElement repPSRepLink() {
        return getElement( repPanelPSReport );
    }

    public WebElement repSPRepLink() {
        return getElement( repPanelSPReport );
    }

    public WebElement repCPARepLink() {
        return getElement( repPanelCPAReport );
    }

    public WebElement repPSABTRepLink() {
        return getElement( repPanelPSABTReport );
    }

    public WebElement repPSARepLink() {
        return getElement( repPanelPSAReport );
    }

    public WebElement repSEURepLink() {
        return getElement( repPanelSEUReport );
    }

    public String msgPanelTitle() {
        return getElement( msgPanelTitle ).getText();
    }

    public WebElement msgPostMsgBtn() {
        return getElement( msgPanelPostMsgBtn );
    }

    public WebElement getOrgAddOrgLink() {
        return getElement( orgAddOrg );
    }

    public String clickUsrManageGrpLink() {
        return clickOnButton( getUserMangeGrpLink() );
    }

    public String clickUsrAddUsrLink() {
        return clickOnButton( getUsrAddUsrLink() );
    }

    public String clickRepAODRepLink() {
        return clickOnButton( repAODRepLink() );
    }

    public String clickRepCPRepLink() {
        return clickOnButton( repCPRepLink() );
    }

    public String clickRepLSRepLink() {
        return clickOnButton( repLSRepLink() );
    }

    public String clickRepPSRepLink() {
        return clickOnButton( repPSRepLink() );
    }

    public String clickRepSPRepLink() {
        return clickOnButton( repSPRepLink() );
    }

    public String clickRepCPARepLink() {
        return clickOnButton( repCPARepLink() );
    }

    public String clickRepPSABTRepLink() {
        return clickOnButton( repPSABTRepLink() );
    }

    public String clickRepPSARepLink() {
        return clickOnButton( repPSARepLink() );
    }

    public String clickMsgPostMsgBtn() {
        return clickOnButton( msgPostMsgBtn() );
    }

    public String clickRepSEURepLink() {
        return clickOnButton( repSEURepLink() );
    }

    public Map<String, String> clickOrgEditServerSettingBtn() {
        orgEditServerSettingBtn().click();
        CommonHelper.nap();
        return getNewPageDetails();
    }

    public Map<String, String> clickOrgAddOrgLink() {
        return getPageDetailsAfterClick( getOrgAddOrgLink() );
    }

    public WebElement getProdDropDown() {
        return getElement( prodDropdown );
    }

    public String getDefaultProdSelection() {
        return getDefaultSelectedOption( getProdDropDown() );
    }

    public List<String> getAllProductDropDownValues() {
        return getDropDownValues( getProdDropDown() );
    }

    public String getOrganizationDropDownDefaultSelection() {
        return getDefaultSelectedOption( getOrganizationDropDown() );
    }

    public List<String> getAllOrganizationDropDownValues() {
        return getDropDownValues( getOrganizationDropDown() );
    }
}
