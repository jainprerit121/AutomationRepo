package com.pearson.uitest.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.pearson.uitest.enums.ProficiencyScaleSettings;

/**
 * 
 * This class is defined data setting page elements.
 * 
 * @author Ramakrishna Gaddam
 *
 */
public class EditDataSettingsPage extends OrganizationPage {

    private By editDataSettingsOrgHeading = By.id( "contentBar" );

    private By requireFieldStar = By.id( "addOrgMandatory" );
    private By editRequireFieldDiv = By.id( "editRequireFieldDiv" );

    //Tab details
    private By detailsTab = By.id( "editTabs1" );
    private By licenseTab = By.id( "editTabs2" );
    private By dataSettingsTab = By.id( "editTabs3" );
    private By allOrganizationTabs = By.id( "editTabs" );

    private By detailsLink = By.id( "editTabs1Link" );
    private By licensesLink = By.id( "editTabs2Link" );
    private By dataSettingsLink = By.id( "editTabs3Link" );

    //Proficiency data input
    private By proScaleSettingsLabel = By.id( "proScaleSettingsLabel" );
    private By settingContentHeader = By.id( "settingContentHeader" );
    private By proScaleSettingsDropDown = By.id( "levelfilterSelect" );

    //
    private By labelLabel = By.id( "levelLabel" );
    private By percentScoreRangeLabel = By.id( "PercentLabel" );
    private By graphColorLabel = By.id( "GraphLabel" );

    //level input
    private By levelInput0 = By.id( "levelInput0" );
    private By levelInput1 = By.id( "levelInput1" );
    private By levelInput2 = By.id( "levelInput2" );
    private By levelInput3 = By.id( "levelInput3" );
    private By levelInput4 = By.id( "levelInput4" );

    //Score input
    private By scoreStartInput0 = By.id( "scoreStartInput0" );
    private By scoreStartInput1 = By.id( "scoreStartInput1" );
    private By scoreStartInput2 = By.id( "scoreStartInput2" );
    private By scoreStartInput3 = By.id( "scoreStartInput3" );
    private By scoreStartInput4 = By.id( "scoreStartInput4" );

    private By scoreEndInput0 = By.id( "scoreEnd0" );
    private By scoreEndInput1 = By.id( "scoreEnd1" );
    private By scoreEndInput2 = By.id( "scoreEnd2" );
    private By scoreEndInput3 = By.id( "scoreEnd3" );
    private By scoreEndInput4 = By.id( "scoreEnd4" );

    //Graph colors
    private By graphColor0 = By.id( "graphColor0" );
    private By graphColor1 = By.id( "graphColor1" );
    private By graphColor2 = By.id( "graphColor2" );
    private By graphColor3 = By.id( "graphColor3" );
    private By graphColor4 = By.id( "graphColor4" );

    //Demographic input field
    private By requireStudentDemographicLabel = By.id( "requireStudentLabel0" );
    private By demographicYesRadioButton = By.id( "requireYesLabel0" );
    private By demographicYesLabel = By.xpath( "//*[@id='requireYesDiv0']/span" );
    private By demographicNoRadioButton = By.id( "requireNoLabel0" );
    private By demographicNoLabel = By.xpath( "//*[@id='requireNoDiv0']/span" );

    private By captureResearchDataLabel = By.id( "requireStudentLabel1" );
    private By captureYesRadioButton = By.id( "requireYesLabel1" );
    private By captureYesLabel = By.xpath( "//*[@id='requireYesDiv1']/span" );
    private By captureNoRadioButton = By.id( "requireNoLabel1" );
    private By captureNoLabel = By.xpath( "//*[@id='requireNoDiv1']/span" );
    private By researchDataHelpLabel = By.id( "researchDataHelpLabel" );

    //Capture Research Data Input
    private By researchDataOptionPopUp = By.id( "orgDataRes5" );
    private By researchDataOptionPopUpDetailsFirst = By.cssSelector( "p#orgDataRes0" );
    private By researchDataOptionPopUpDetailsSecond = By.cssSelector( "p#orgDataRes1" );
    private By closeResearchDataInformation = By.cssSelector( "button#orgDataResBtns0" );
    private By orgDataReslink = By.xpath( "//div[@id='orgDataReslink']/a" );

    private By orgEditServerSettings = By.id( "orgEditServerSettings" );
    private By dataSettingsSaveButton = By.id( "orgDataSettingsSave" );
    private By exitDataSettingsButton = By.id( "orgDataSettingsExit" );

    //private By saveChangesNo = By.cssSelector( "button#orgDataConfirmBtnNo" );

    public EditDataSettingsPage( WebDriver driver ) {
        super( driver );
        log = Logger.getLogger( EditDataSettingsPage.class );
    }

    public WebElement getEditDataSettingsOrganizationHeading() {
        return getElement( editDataSettingsOrgHeading );
    }

    public WebElement getEditRequireField() {
        return getElement( editRequireFieldDiv );
    }

    public WebElement getRequireFieldStar() {
        return getElement( requireFieldStar );
    }

    public WebElement getDetailsTab() {
        return getElement( detailsTab );
    }

    public WebElement getDetailsLink() {
        return getElement( detailsLink );
    }

    public WebElement getLicenseTab() {
        return getElement( licenseTab );
    }

    public WebElement getLicensesLink() {
        return getElement( licensesLink );
    }

    public WebElement getDataSettingsTab() {
        return getElement( dataSettingsTab );
    }

    public WebElement getDataSettingsLink() {
        return getElement( dataSettingsLink );
    }

    public WebElement getAllOrganizationTabs() {
        return getElement( allOrganizationTabs );
    }

    public WebElement getProficiencyScaleSettingsLabel() {
        return getElement( proScaleSettingsLabel );
    }

    public WebElement getProficiencyScaleSettingsDropDown() {
        return getElement( proScaleSettingsDropDown );
    }

    public WebElement getGraphColorLabel() {
        return getElement( graphColorLabel );
    }

    public WebElement getLevelInput0() {
        return getElement( levelInput0 );
    }

    public WebElement getLevelInput1() {
        return getElement( levelInput1 );
    }

    public WebElement getLevelInput2() {
        return getElement( levelInput2 );
    }

    public WebElement getLevelInput3() {
        return getElement( levelInput3 );
    }

    public WebElement getLevelInput4() {
        return getElement( levelInput4 );
    }

    public WebElement getScoreStartInput0() {
        return getElement( scoreStartInput0 );
    }

    public WebElement getScoreStartInput1() {
        return getElement( scoreStartInput1 );
    }

    public WebElement getScoreStartInput2() {
        return getElement( scoreStartInput2 );
    }

    public WebElement getScoreStartInput3() {
        return getElement( scoreStartInput3 );
    }

    public WebElement getScoreStartInput4() {
        return getElement( scoreStartInput4 );
    }

    public WebElement getScoreEndInput0() {
        return getElement( scoreEndInput0 );
    }

    public WebElement getScoreEndInput1() {
        return getElement( scoreEndInput1 );
    }

    public WebElement getScoreEndInput2() {
        return getElement( scoreEndInput2 );
    }

    public WebElement getScoreEndInput3() {
        return getElement( scoreEndInput3 );
    }

    public WebElement getScoreEndInput4() {
        return getElement( scoreEndInput4 );
    }

    public WebElement getGraphColor0() {
        return getElement( graphColor0 );
    }

    public WebElement getGraphColor1() {
        return getElement( graphColor1 );
    }

    public WebElement getGraphColor2() {
        return getElement( graphColor2 );
    }

    public WebElement getGraphColor3() {
        return getElement( graphColor3 );
    }

    public WebElement getGraphColor4() {
        return getElement( graphColor4 );
    }

    public WebElement getPercentScoreRangeLabel() {
        return getElement( percentScoreRangeLabel );
    }

    public WebElement getLabelLabel() {
        return getElement( labelLabel );
    }

    public WebElement getProficiencySettingContentDetails() {
        return getElement( settingContentHeader );
    }

    public WebElement getCaptureResearchDataLabel() {
        return getElement( captureResearchDataLabel );
    }

    public WebElement getCaptureYesRadioButton() {
        return getElement( captureYesRadioButton );
    }

    public WebElement getCaptureYesLabel() {
        return getElement( captureYesLabel );
    }

    public WebElement getCaptureNoRadioButton() {
        return getElement( captureNoRadioButton );
    }

    public WebElement getCaptureNoLabel() {
        return getElement( captureNoLabel );
    }

    public WebElement getResearchDataHelpLabel() {
        return getElement( researchDataHelpLabel );
    }

    public WebElement getResearchDataOptionPopUp() {
        return getElement( researchDataOptionPopUp );
    }

    public WebElement getResearchDataOptionPopUpDetailsFirst() {
        return getElement( researchDataOptionPopUpDetailsFirst );
    }

    public WebElement getResearchDataOptionPopUpDetailsSecond() {
        return getElement( researchDataOptionPopUpDetailsSecond );
    }

    public WebElement getCloseResearchDataInformation() {
        return getElement( closeResearchDataInformation );
    }

    public WebElement getOrganizationResearchLink() {
        return getElement( orgDataReslink );
    }

    public WebElement getRequireStudentDemographicLabel() {
        return getElement( requireStudentDemographicLabel );
    }

    public WebElement getDemographicYesRadioButton() {
        return getElement( demographicYesRadioButton );
    }

    public WebElement getDemographicYesLabel() {
        return getElement( demographicYesLabel );
    }

    public WebElement getDemographicNoRadioButton() {
        return getElement( demographicNoRadioButton );
    }

    public WebElement getDemographicNoLabel() {
        return getElement( demographicNoLabel );
    }

    public WebElement getOrgEditServerSettings() {
        return getElement( orgEditServerSettings );
    }

    public WebElement getDataSettingsSaveButton() {
        return getElement( dataSettingsSaveButton );
    }

    public WebElement getExitDataSettingsButton() {
        return getElement( exitDataSettingsButton );
    }

    public WebElement getOrganizationNameLink( String orgName ) {
        return getElement( By.linkText( orgName ) );
    }

    public Map<String, String> clickOrganizationNameLink( String orgName ) {
        return getPageDetailsAfterClick( getOrganizationNameLink( orgName ) );
    }

    public Map<String, String> clickDataSettingsTab() {
        return getPageDetailsAfterClick( getDataSettingsLink() );
    }

    public List<String> getProficiencyScaleSettingsDropdownValues() {
        return getDropDownValues( getProficiencyScaleSettingsDropDown() );
    }

    public String getProficiencyScaleSettingsDropdownDefaultSelection() {
        return getDefaultSelectedOption( getProficiencyScaleSettingsDropDown() );
    }

    public void clickResearchDataHelpLabel() {
        getResearchDataHelpLabel().click();
    }

    public Map<String, String> clickOrganizationResearchLink() {
        return getNewPageDetails( getOrganizationResearchLink() );
    }

    public Map<String, String> clickEditServerSettings() {
        return getNewPageDetails( getOrgEditServerSettings() );
    }

    public Map<String, String> clickExitDataSettings() {
        return getPageDetailsAfterClick( getExitDataSettingsButton() );
    }

    public void clickCloseResearchDataInformation() {
        getCloseResearchDataInformation().click();
    }

    public String selectProficiencyScaleSettings( ProficiencyScaleSettings setting ) {
        return selectDropdownValue( getProficiencyScaleSettingsDropDown(), setting.getPerformanceType() );
    }

}
