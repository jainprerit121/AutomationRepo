package com.pearson.uitest.testobjects.orgpage;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.uitest.enums.Level5DataSettings;
import com.pearson.uitest.enums.ProficiencyScaleSettings;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.EditDataSettingsPage;
import com.pearson.uitest.testobjects.AdminBaseTest;

/**
 * 
 * This is base class which contains common methods used for both system and
 * school admin login.
 * 
 * @author Ramakrishna Gaddam
 *
 */
public abstract class EditDataSettingsBaseTest extends AdminBaseTest {

    protected String dataSettingsSchoolSelection;

    protected void initialize( String browser ) {
        super.initialize( browser );
        page = new EditDataSettingsPage( driver );
    }

    protected void gotoOrganizationTab( String userName, String password, String dataSettingSchoolSelection ) {
        this.dataSettingsSchoolSelection = dataSettingSchoolSelection;
        loginPage.login( userName, password );
        CommonHelper.nap();
        page.getOrgTabBtn().click();
    }

    @Test ( priority = 101 )
    public void checkOrganizationsTabBtnHighlighted() {
        page.getOrgTabBtn().click();
        Assert.assertEquals( page.getOrgTabBtn().getAttribute( "class" ), "ng-binding active" );
    }

    @Test ( priority = 102 )
    public void checkEditOrganizationPageNavigated() {
        Map<String, String> actualPageDetails = ( (EditDataSettingsPage) page ).clickOrganizationNameLink( dataSettingsSchoolSelection );
        checkPageDetails( actualPageDetails, appProperties.getProperty( ADMIN_PAGE_TITLE ), appProperties.getProperty( "EditOrgDetailsUrl" ) );
    }

    @Test ( priority = 103 )
    public void checkDetailsTabIsActive() {
        Assert.assertEquals( page.getElementAttribute( ( (EditDataSettingsPage) page ).getDetailsTab(), "class" ), appProperties.getProperty( "tabActiveStatus" ) );
    }

    @Test ( priority = 104 )
    public void checkDataSettingsTabIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getDataSettingsTab() != null, "Missing 'Data Settings' tab." );
    }

    @Test ( priority = 105 )
    public void checkDataSettingsPageNavigated() {
        Map<String, String> actualPageDetails = ( (EditDataSettingsPage) page ).clickDataSettingsTab();
        checkPageDetails( actualPageDetails, appProperties.getProperty( ADMIN_PAGE_TITLE ), appProperties.getProperty( "EditOrgDataSetUrl" ) );
    }

    @Test ( priority = 106 )
    public void checkDataSettingsTabIsActive() {
        Assert.assertEquals( page.getElementAttribute( ( (EditDataSettingsPage) page ).getDataSettingsTab(), "class" ), appProperties.getProperty( "tabActiveStatus" ) );
    }

    @Test ( priority = 107 )
    public void checkDataSettingsHeader() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getEditDataSettingsOrganizationHeading().getText(), "Edit \"" + dataSettingsSchoolSelection + "\" for \"SuccessMaker\"" );
    }

    @Test ( priority = 108 )
    public void checkMandatorySymbolIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getRequireFieldStar() != null, "Missing mandatory symbol (*) tab." );
    }

    @Test ( priority = 109 )
    public void checkRequiredFieldStar() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getRequireFieldStar().getText(), appProperties.getProperty( "MandatorySymbol" ) );
    }

    @Test ( priority = 110 )
    public void checkRequiredFieldIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getEditRequireField() != null, "Missing 'Required field' label text." );
    }

    @Test ( priority = 111 )
    public void checkRequiredFieldText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getEditRequireField().getText(), "* = " + appProperties.getProperty( "OrgReqField" ) );
    }

    @Test ( priority = 112 )
    public void checkProficiencyScaleSettingsLabelIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getProficiencyScaleSettingsLabel() != null, "Missing 'Proficiency Scale Settings' label text." );
    }

    @Test ( priority = 113 )
    public void checkProficiencyScaleSettingsLabel() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getProficiencyScaleSettingsLabel().getText(), appProperties.getProperty( "Proficiency" ) );
    }

    @Test ( priority = 114 )
    public void checkProficiencyScaleSettingsDetailsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getProficiencySettingContentDetails() != null, "Missing 'Proficiency Scale Settings Details' label text." );
    }

    @Test ( priority = 115 )
    public void checkProficiencyScaleSettingsDetailsText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getProficiencySettingContentDetails().getText(), appProperties.getProperty( "settingsContentHeader" ) );
    }

    @Test ( priority = 116 )
    public void checkProficiencyScaleSettingsDropdownPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getProficiencyScaleSettingsDropDown() != null, "Missing 'Proficiency Scale Settings Details' label text." );
    }

    @Test ( priority = 117 )
    public void checkProficiencyScaleSettingsDropdownValues() {
        List<String> actualValues = ( (EditDataSettingsPage) page ).getProficiencyScaleSettingsDropdownValues();
        actualValues.removeAll( ProficiencyScaleSettings.getProficiencyTypes() );
        Assert.assertTrue( actualValues.size() == 0, "Dropdown values are incorrect" );
    }

    @Test ( priority = 118 )
    public void checkOrderOfProficiencyScaleSettingsDropdownValues() {
        List<String> actualValues = ( (EditDataSettingsPage) page ).getProficiencyScaleSettingsDropdownValues();
        Assert.assertEquals( actualValues, ProficiencyScaleSettings.getProficiencyTypes() );
    }

    @Test ( priority = 119 )
    public void checkProficiencyScaleSettingsDefaultSelection() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getProficiencyScaleSettingsDropdownDefaultSelection(), ProficiencyScaleSettings.LEVEL_5_PERFORMANCE.getPerformanceType() );
    }

    @Test ( priority = 120 )
    public void checkLabelIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLabelLabel() != null, "Missing 'Label' label text." );
    }

    @Test ( priority = 121 )
    public void checkLabelText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getLabelLabel().getText(), appProperties.getProperty( "labelText" ) );
    }

    @Test ( priority = 122 )
    public void checkPercentScoreRangeIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getPercentScoreRangeLabel() != null, "Missing 'Percent Score Range' label text." );
    }

    @Test ( priority = 123 )
    public void checkPercentScoreRangeText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getPercentScoreRangeLabel().getText(), appProperties.getProperty( "percentScore" ) );
    }

    @Test ( priority = 124 )
    public void checkGraphColorIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColorLabel() != null, "Missing 'Graph Color' label text." );
    }

    @Test ( priority = 125 )
    public void checkGraphColorText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getGraphColorLabel().getText(), appProperties.getProperty( "graphColor" ) );
    }

    @Test ( priority = 126 )
    public void checkRequiredStudentDemographicDataLabelIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getRequireStudentDemographicLabel() != null, "Missing 'Require student demographic data' label text." );
    }

    @Test ( priority = 127 )
    public void checkRequiredStudentDemographicDataLabelText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getRequireStudentDemographicLabel().getText(), appProperties.getProperty( "demographicData" ) );
    }

    @Test ( priority = 128 )
    public void checkRequiredDemographicDataYesButtonIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getDemographicYesRadioButton() != null, "Missing Demographic data 'Yes' button." );
    }

    @Test ( priority = 129 )
    public void checkRequiredDemographicDataYesButtonText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getDemographicYesLabel().getText(), appProperties.getProperty( "demographicYes" ) );
    }

    @Test ( priority = 130 )
    public void checkRequiredDemographicDataNoButtonIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getDemographicNoRadioButton() != null, "Missing Demographic data 'No' button." );
    }

    @Test ( priority = 131 )
    public void checkRequiredDemographicDataNoButtonText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getDemographicNoLabel().getText(), appProperties.getProperty( "demographicNo" ) );
    }

    @Test ( priority = 132 )
    public void checkCaptureResearchDataLabelIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getCaptureResearchDataLabel() != null, "Missing 'Capture research data' label text." );
    }

    @Test ( priority = 133 )
    public void checkCaptureResearchDataLabelText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getCaptureResearchDataLabel().getText(), appProperties.getProperty( "capture" ) );
    }

    @Test ( priority = 134 )
    public void checkCaptureResearchYesButtonIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getCaptureYesRadioButton() != null, "Missing Capture research data 'Yes' button." );
    }

    @Test ( priority = 135 )
    public void checkCaptureResearchYesButtonText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getCaptureYesLabel().getText(), appProperties.getProperty( "demographicYes" ) );
    }

    @Test ( priority = 136 )
    public void checkCaptureResearchDataNoButtonIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getCaptureNoRadioButton() != null, "Missing Capture research data 'No' button." );
    }

    @Test ( priority = 137 )
    public void checkCaptureResearchDataNoButtonText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getCaptureNoLabel().getText(), appProperties.getProperty( "demographicNo" ) );
    }

    @Test ( priority = 138 )
    public void checkSaveButtonIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getDataSettingsSaveButton() != null, "Missing 'Save' button." );
    }

    @Test ( priority = 139 )
    public void checkSaveButtonText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getDataSettingsSaveButton().getText(), appProperties.getProperty( "saveBtnText" ) );
    }

    @Test ( priority = 140 )
    public void checkExitEditOrganizationButtonIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getExitDataSettingsButton() != null, "Missing 'Exit Edit Organization' button." );
    }

    @Test ( priority = 141 )
    public void checkExitEditOrganizationButtonText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getExitDataSettingsButton().getText(), appProperties.getProperty( "exitEditOrg" ) );
    }

    @Test ( priority = 142 )
    public void checkEditServerSettingseButtonIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getOrgEditServerSettings() != null, "Missing 'Edit Server Settings' button." );
    }

    @Test ( priority = 143 )
    public void checkEditServerSettingsButtonText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getOrgEditServerSettings().getText(), appProperties.getProperty( "editServerSettings" ) );
    }

    @Test ( priority = 144 )
    public void checkGradeType0TextBoxIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput0() != null, "Missing '90-100%' text box." );
    }

    @Test ( priority = 145 )
    public void checkGradeType0DefaultValueForLevel5() {
        Assert.assertEquals( page.getElementAttribute( ( (EditDataSettingsPage) page ).getLevelInput0(), ATTRIBUTE_VALUE_NAME ), Level5DataSettings.LEVEL5.getGradeType() );
    }

    @Test ( priority = 146 )
    public void checkGradeType1TextBoxIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput1() != null, "Missing '85-89%' text box." );
    }

    @Test ( priority = 147 )
    public void checkGradeType1DefaultValueForLevel5() {
        Assert.assertEquals( page.getElementAttribute( ( (EditDataSettingsPage) page ).getLevelInput1(), ATTRIBUTE_VALUE_NAME ), Level5DataSettings.LEVEL4.getGradeType() );
    }

    @Test ( priority = 148 )
    public void checkGradeType2TextBoxIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput2() != null, "Missing '65-84%' text box." );
    }

    @Test ( priority = 149 )
    public void checkGradeType2DefaultValueForLevel5() {
        Assert.assertEquals( page.getElementAttribute( ( (EditDataSettingsPage) page ).getLevelInput2(), ATTRIBUTE_VALUE_NAME ), Level5DataSettings.LEVEL3.getGradeType() );
    }

    @Test ( priority = 150 )
    public void checkGradeType3TextBoxIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput3() != null, "Missing '60-64%' text box." );
    }

    @Test ( priority = 151 )
    public void checkGradeType3DefaultValueForLevel5() {
        Assert.assertEquals( page.getElementAttribute( ( (EditDataSettingsPage) page ).getLevelInput3(), ATTRIBUTE_VALUE_NAME ), Level5DataSettings.LEVEL2.getGradeType() );
    }

    @Test ( priority = 152 )
    public void checkGradeType4TextBoxIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput4() != null, "Missing '0-59%' text box." );
    }

    @Test ( priority = 153 )
    public void checkGradeType4DefaultValueForLevel5() {
        Assert.assertEquals( page.getElementAttribute( ( (EditDataSettingsPage) page ).getLevelInput4(), ATTRIBUTE_VALUE_NAME ), Level5DataSettings.LEVEL1.getGradeType() );
    }

    @Test ( priority = 154 )
    public void checkStartPercent0TextBoxIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput0() != null, "Missing 'Start Percent' first row text box." );
    }

    @Test ( priority = 155 )
    public void checkStartPercent0DefaultValueForLevel5() {
        Assert.assertEquals( page.getElementAttribute( ( (EditDataSettingsPage) page ).getScoreStartInput0(), ATTRIBUTE_VALUE_NAME ), String.valueOf( Level5DataSettings.LEVEL5.getStartPercent() ) );
    }

    @Test ( priority = 156 )
    public void checkStartPercent1TextBoxIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput1() != null, "Missing 'Start Percent' second row  text box." );
    }

    @Test ( priority = 157 )
    public void checkStartPercent1DefaultValueForLevel5() {
        Assert.assertEquals( page.getElementAttribute( ( (EditDataSettingsPage) page ).getScoreStartInput1(), ATTRIBUTE_VALUE_NAME ), String.valueOf( Level5DataSettings.LEVEL4.getStartPercent() ) );
    }

    @Test ( priority = 158 )
    public void checkStartPercent2TextBoxIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput2() != null, "Missing 'Start Percent' third row text box." );
    }

    @Test ( priority = 159 )
    public void checkStartPercent2DefaultValueForLevel5() {
        Assert.assertEquals( page.getElementAttribute( ( (EditDataSettingsPage) page ).getScoreStartInput2(), ATTRIBUTE_VALUE_NAME ), String.valueOf( Level5DataSettings.LEVEL3.getStartPercent() ) );
    }

    @Test ( priority = 160 )
    public void checkStartPercent3TextBoxIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput3() != null, "Missing 'Start Percent' forth row  text box." );
    }

    @Test ( priority = 161 )
    public void checkStartPercent3DefaultValueForLevel5() {
        Assert.assertEquals( page.getElementAttribute( ( (EditDataSettingsPage) page ).getScoreStartInput3(), ATTRIBUTE_VALUE_NAME ), String.valueOf( Level5DataSettings.LEVEL2.getStartPercent() ) );
    }

    @Test ( priority = 162 )
    public void checkStartPercent4TextBoxIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput4() != null, "Missing 'Start Percent' fifth row label." );
    }

    @Test ( priority = 163 )
    public void checkStartPercent4DefaultValueForLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getScoreStartInput4().getText(), String.valueOf( Level5DataSettings.LEVEL1.getStartPercent() ) );
    }

    @Test ( priority = 164 )
    public void checkGraphColor0IsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor0() != null, "Missing 'Purple' color image." );
    }

    @Test ( priority = 165 )
    public void checkGraphColor0ImageForLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor0(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph0.png" );
    }

    @Test ( priority = 166 )
    public void checkGraphColor1ImageIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor1() != null, "Missing 'Blue' color image." );
    }

    @Test ( priority = 167 )
    public void checkGraphColor1ImageForLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor1(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph1.png" );
    }

    @Test ( priority = 168 )
    public void checkGraphColor2ImageIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor2() != null, "Missing 'Green' color image." );
    }

    @Test ( priority = 169 )
    public void checkGraphColor2ImageForLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor2(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph2.png" );
    }

    @Test ( priority = 170 )
    public void checkGraphColor3TextBoxIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor3() != null, "Missing 'Yellow' color image." );
    }

    @Test ( priority = 171 )
    public void checkGraphColor3ImageForLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor3(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph3.png" );
    }

    @Test ( priority = 172 )
    public void checkGraphColor4ImageIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor4() != null, "Missing 'Red' color image." );
    }

    @Test ( priority = 173 )
    public void checkGraphColor4ImageForLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor4(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph4.png" );
    }

    @Test ( priority = 174 )
    public void checkEndPercent0LabelIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput0() != null, "Missing 'End Percent' first row label." );
    }

    @Test ( priority = 175 )
    public void checkEndPercent0DefaultValueForLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getScoreEndInput0().getText(), String.valueOf( Level5DataSettings.LEVEL5.getEndPercent() ) );
    }

    @Test ( priority = 176 )
    public void checkEndPercent1LabelIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput1() != null, "Missing 'End Percent' second row  label." );
    }

    @Test ( priority = 177 )
    public void checkEndPercent1DefaultValueForLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getScoreEndInput1().getText(), String.valueOf( Level5DataSettings.LEVEL4.getEndPercent() ) );
    }

    @Test ( priority = 178 )
    public void checkEndPercent2LabelIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput2() != null, "Missing 'End Percent' third row label." );
    }

    @Test ( priority = 179 )
    public void checkEndPercent2DefaultValueForLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getScoreEndInput2().getText(), String.valueOf( Level5DataSettings.LEVEL3.getEndPercent() ) );
    }

    @Test ( priority = 180 )
    public void checkEndPercent3LabelIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput3() != null, "Missing 'End Percent' forth row label." );
    }

    @Test ( priority = 181 )
    public void checkEndPercent3DefaultValueForLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getScoreEndInput3().getText(), String.valueOf( Level5DataSettings.LEVEL2.getEndPercent() ) );
    }

    @Test ( priority = 182 )
    public void checkEndPercent4LabelIsPresentForLevel5() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput4() != null, "Missing 'End Percent' fifth row label." );
    }

    @Test ( priority = 183 )
    public void checkEndPercent4DefaultValueForLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getScoreEndInput4().getText(), String.valueOf( Level5DataSettings.LEVEL1.getEndPercent() ) );
    }

    @Test ( priority = 184 )
    public void checkCaptureResearchDataLabelLink() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getResearchDataHelpLabel() != null, "Missing 'What is the Research Data Option?' label." );
    }

    @Test ( priority = 185 )
    public void checkCaptureResearchDataLabelLinkText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getResearchDataHelpLabel().getText(), appProperties.getProperty( "researchDataHelpLabel" ) );
    }

    @Test ( priority = 186 )
    public void checkCaptureResearchDataPopupOpened() {
        ( (EditDataSettingsPage) page ).clickResearchDataHelpLabel();
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getResearchDataOptionPopUp() != null, "Missing 'What is the Research Data Option?' popup." );
    }

    @Test ( priority = 187 )
    public void checkWhatIsTheResearchDataOptionPopupTextQuestion() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getResearchDataOptionPopUp().getText(), appProperties.getProperty( "WhatistheResearchDataOptionPopUptextQuestion" ) );
    }

    @Test ( priority = 188 )
    public void checkWhatIsTheResearchDataOptionPopupTextFirst() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getResearchDataOptionPopUpDetailsFirst().getText(), appProperties.getProperty( "WhatistheResearchDataOptionPopUptextFstAns" ) );
    }

    @Test ( priority = 189 )
    public void checkWhatIsTheResearchDataOptionPopupTextSecond() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getResearchDataOptionPopUpDetailsSecond().getText(), appProperties.getProperty( "WhatistheResearchDataOptionPopUptextSndAns" ) );
    }

    @Test ( priority = 190 )
    public void checkWhatIsTheResearchDataOptionPopupURLIsPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getOrganizationResearchLink() != null, "Missing Organization Research Link" );
    }

    @Test ( priority = 191 )
    public void checkWhatIsTheResearchDataOptionPopupURLText() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getOrganizationResearchLink().getText(), appProperties.getProperty( "popUpURL" ) );
    }

    @Test ( priority = 192 )
    public void checkWhatIsTheResearchDataOptionPopupTextURLClick() {
        Map<String, String> actualPageDetails = ( (EditDataSettingsPage) page ).clickOrganizationResearchLink();
        checkPageDetails( actualPageDetails, appProperties.getProperty( ADMIN_PAGE_TITLE ), appProperties.getProperty( "popUpURL" ) );
    }

    @Test ( priority = 193 )
    public void checkWhatIsTheResearchDataOptionPopupCloseButtonPresent() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getCloseResearchDataInformation() != null, "Missing 'Close' button on Organization Research popup" );
    }

    @Test ( priority = 194 )
    public void checkWhatIsTheResearchDataOptionPopupClosed() {
        ( (EditDataSettingsPage) page ).clickCloseResearchDataInformation();
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getCloseResearchDataInformation() != null, "Research data information popup still opened." );
    }

    @Test ( priority = 195 )
    public void checkDataSettingsHelpButtonLaunched() {
        Map<String, String> actualPageDetails = page.launchHelp();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "EditOrgHelpTitle" ), appProperties.getProperty( "editDataHelpUrl" ) );
    }

    @Test ( priority = 196 )
    public void checkPerformanceLevelSettingsAfterSelectingLevel4() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).selectProficiencyScaleSettings( ProficiencyScaleSettings.LEVEL_4_PERFORMANCE ), ProficiencyScaleSettings.LEVEL_4_PERFORMANCE.getPerformanceType() );
    }

    @Test ( priority = 197 )
    public void checkGradeType0TextBoxIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput0() != null, "Missing 'Label Input 0' text box for Level 4." );
    }

    @Test ( priority = 198 )
    public void checkGradeType1TextBoxIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput1() != null, "Missing 'Label Input 1' text box for Level 4." );
    }

    @Test ( priority = 199 )
    public void checkGradeType2TextBoxIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput2() != null, "Missing 'Label Input 2' text box for Level 4 ." );
    }

    @Test ( priority = 200 )
    public void checkGradeType3TextBoxIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput3() != null, "Missing 'Label Input 3' text box for Level 4." );
    }

    @Test ( priority = 201 )
    public void checkGradeType4TextBoxIsNotPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput4() == null, "'Label Input 4' text box is present  ." );
    }

    @Test ( priority = 202 )
    public void checkStartPercent0TextBoxIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput0() != null, "Missing 'Start Percent 0'  text box for Level 4." );
    }

    @Test ( priority = 203 )
    public void checkStartPercent1TextBoxIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput1() != null, "Missing 'Start Percent 1'  text box for Level 4." );
    }

    @Test ( priority = 204 )
    public void checkStartPercent2TextBoxIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput2() != null, "Missing 'Start Percent 2' text box for level 4." );
    }

    @Test ( priority = 205 )
    public void checkStartPercent3TextBoxIsNotPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput3() != null, "Missing 'Start Percent 3' label for Level 4." );
    }

    @Test ( priority = 206 )
    public void checkStartPercent3LabelPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput4() == null, "'Start Percent 5' input field is present for Level 4." );
    }

    @Test ( priority = 207 )
    public void checkGraphColor0IsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor0() != null, "Missing 'Purple' color image for Level 4." );
    }

    @Test ( priority = 208 )
    public void checkGraphColor0ImageForLevel4() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor0(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph0.png" );
    }

    @Test ( priority = 209 )
    public void checkGraphColor1ImageIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor1() != null, "Missing 'Blue' color image for Level 4." );
    }

    @Test ( priority = 210 )
    public void checkGraphColor1ImageForLevel4() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor1(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph1.png" );
    }

    @Test ( priority = 211 )
    public void checkGraphColor2ImageIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor2() != null, "Missing 'Green' color image for Level 4." );
    }

    @Test ( priority = 212 )
    public void checkGraphColor2ImageForLevel4() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor2(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph2.png" );
    }

    @Test ( priority = 213 )
    public void checkGraphColor3ImageIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor3() != null, "Missing 'Red' color image for Level 4." );
    }

    @Test ( priority = 214 )
    public void checkGraphColor3ImageForLevel4() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor3(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph4.png" );
    }

    @Test ( priority = 215 )
    public void checkGraphColor4ImageIsNotPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor4() == null, "'Yellow' color image is present for Level 4." );
    }

    @Test ( priority = 216 )
    public void checkEndPercent0LabelIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput0() != null, "Missing 'End Percent 0' label for Level 4." );
    }

    @Test ( priority = 217 )
    public void checkEndPercent1LabelIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput1() != null, "Missing 'End Percent 1' label for Level 4." );
    }

    @Test ( priority = 218 )
    public void checkEndPercent2LabelIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput2() != null, "Missing 'End Percent 2' label for Level 4." );
    }

    @Test ( priority = 219 )
    public void checkEndPercent3LabelIsPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput3() != null, "Missing 'End Percent 3' label for Level 4." );
    }

    @Test ( priority = 220 )
    public void checkEndPercent4LabelIsNotPresentForLevel4() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput4() == null, "'End Percent 4' label is present for Level 4." );
    }

    @Test ( priority = 221 )
    public void checkPerformanceLevelSettingsAfterSelectingLevel3() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).selectProficiencyScaleSettings( ProficiencyScaleSettings.LEVEL_3_PERFORMANCE ), ProficiencyScaleSettings.LEVEL_3_PERFORMANCE.getPerformanceType() );
    }

    @Test ( priority = 222 )
    public void checkGradeType0TextBoxIsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput0() != null, "Missing 'Label Input 0' text box for Level 3." );
    }

    @Test ( priority = 223 )
    public void checkGradeType1TextBoxIsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput1() != null, "Missing 'Label Input 1' text box for Level 3." );
    }

    @Test ( priority = 224 )
    public void checkGradeType2TextBoxIsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput2() != null, "Missing 'Label Input 2' text box for Level 3 ." );
    }

    @Test ( priority = 225 )
    public void checkGradeType3TextBoxIsNotPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput3() == null, "'Label Input 3' text box is present." );
    }

    @Test ( priority = 226 )
    public void checkGradeType4TextBoxIsNotPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput4() == null, "'Label Input 4' text box is present  ." );
    }

    @Test ( priority = 227 )
    public void checkStartPercent0TextBoxIsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput0() != null, "Missing 'Start Percent 0' text box for Level 3." );
    }

    @Test ( priority = 228 )
    public void checkStartPercent1TextBoxIsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput1() != null, "Missing 'Start Percent 1'  text box for Level 3." );
    }

    @Test ( priority = 229 )
    public void checkStartPercent2TextBoxIsNotPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput2() != null, "Missing 'Start Percent 3' label for Level 3." );
    }

    @Test ( priority = 230 )
    public void checkStartPercent3TextBoxIsNotPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput3() == null, "'Start Percent 4' text box is present for Level 3." );
    }

    @Test ( priority = 231 )
    public void checkStartPercent3LabelPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput4() == null, "'Start Percent 5' text box is present for Level 3." );
    }

    @Test ( priority = 232 )
    public void checkGraphColor0IsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor0() != null, "Missing 'Purple' color image for Level 3." );
    }

    @Test ( priority = 233 )
    public void checkGraphColor0ImageForLevel3() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor0(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph0.png" );
    }

    @Test ( priority = 234 )
    public void checkGraphColor1ImageIsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor1() != null, "Missing 'Green' color image for Level 3." );
    }

    @Test ( priority = 235 )
    public void checkGraphColor1ImageForLevel3() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor1(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph2.png" );
    }

    @Test ( priority = 236 )
    public void checkGraphColor2ImageIsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor2() != null, "Missing 'Red' color image for Level 3." );
    }

    @Test ( priority = 237 )
    public void checkGraphColor2ImageForLevel3() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor2(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph4.png" );
    }

    @Test ( priority = 238 )
    public void checkGraphColor3ImageIsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor3() == null, "'Blue' color image is present for Level 3." );
    }

    @Test ( priority = 240 )
    public void checkGraphColor4ImageIsNotPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor4() == null, "'Yellow' color image is present for Level 3." );
    }

    @Test ( priority = 241 )
    public void checkEndPercent0LabelIsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput0() != null, "Missing 'End Percent 0' label for Level 3." );
    }

    @Test ( priority = 242 )
    public void checkEndPercent1LabelIsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput1() != null, "Missing 'End Percent 1' label for Level 3." );
    }

    @Test ( priority = 243 )
    public void checkEndPercent2LabelIsPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput2() != null, "Missing 'End Percent 2' label for Level 3." );
    }

    @Test ( priority = 244 )
    public void checkEndPercent3LabelIsNotPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput3() == null, "'End Percent 3' label is present for Level 3." );
    }

    @Test ( priority = 245 )
    public void checkEndPercent4LabelIsNotPresentForLevel3() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput4() == null, "'End Percent 4' label is present for Level 3." );
    }

    @Test ( priority = 246 )
    public void checkPerformanceLevelSettingsAfterSelectingLevel2() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).selectProficiencyScaleSettings( ProficiencyScaleSettings.LEVEL_2_PERFORMANCE ), ProficiencyScaleSettings.LEVEL_2_PERFORMANCE.getPerformanceType() );
    }

    @Test ( priority = 247 )
    public void checkGradeType0TextBoxIsPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput0() != null, "Missing 'Label Input 0' text box for Level 2." );
    }

    @Test ( priority = 248 )
    public void checkGradeType1TextBoxIsPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput1() != null, "Missing 'Label Input 1' text box for Level 2." );
    }

    @Test ( priority = 249 )
    public void checkGradeType2TextBoxIsPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput2() == null, "'Label Input 2' text box is present for Level 2 ." );
    }

    @Test ( priority = 250 )
    public void checkGradeType3TextBoxIsNotPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput3() == null, "'Label Input 3' text box is present for Level 2." );
    }

    @Test ( priority = 251 )
    public void checkGradeType4TextBoxIsNotPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getLevelInput4() == null, "'Label Input 4' text box is present for Level 2." );
    }

    @Test ( priority = 252 )
    public void checkStartPercent0TextBoxIsPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput0() != null, "Missing 'Start Percent 0' text box for Level 2." );
    }

    @Test ( priority = 253 )
    public void checkStartPercent1TextBoxIsNotPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput1() != null, "Misssing 'Start Percent 1' text box for Level 2." );
    }

    @Test ( priority = 254 )
    public void checkStartPercent2TextBoxIsNotPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput2() == null, "'Start Percent 2' text box is present for Level 2." );
    }

    @Test ( priority = 255 )
    public void checkStartPercent3TextBoxIsNotPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput3() == null, "'Start Percent 3' text box is present for Level 2." );
    }

    @Test ( priority = 256 )
    public void checkStartPercent3LabelPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreStartInput4() == null, "'Start Percent 4' input field is present for Level 2." );
    }

    @Test ( priority = 257 )
    public void checkGraphColor0IsPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor0() != null, "Missing 'Purple' color image for Level 2." );
    }

    @Test ( priority = 258 )
    public void checkGraphColor0ImageForLevel2() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor0(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph0.png" );
    }

    @Test ( priority = 259 )
    public void checkGraphColor1ImageIsPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor1() != null, "Missing 'Red' color image for Level 2." );
    }

    @Test ( priority = 260 )
    public void checkGraphColor1ImageForLevel2() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).getElementAttribute( ( (EditDataSettingsPage) page ).getGraphColor1(), NG_SRC_ATTRIBUTE_NAME ), "/successmaker/admin/assets/img/graph4.png" );
    }

    @Test ( priority = 261 )
    public void checkGraphColor2ImageIsPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor2() == null, "'Blue' color image is present for Level 2." );
    }

    @Test ( priority = 262 )
    public void checkGraphColor3ImageIsPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor3() == null, "'Green' color image is present for Level 2." );
    }

    @Test ( priority = 263 )
    public void checkGraphColor4ImageIsNotPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getGraphColor4() == null, "'Yellow' color image is present for Level 2." );
    }

    @Test ( priority = 264 )
    public void checkEndPercent0LabelIsPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput0() != null, "Missing 'End Percent 0' label for Level 2." );
    }

    @Test ( priority = 265 )
    public void checkEndPercent1LabelIsPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput1() != null, "Missing 'End Percent 1' label for Level 2." );
    }

    @Test ( priority = 266 )
    public void checkEndPercent2LabelIsNotPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput2() == null, "'End Percent 2' label is present for Level 2." );
    }

    @Test ( priority = 267 )
    public void checkEndPercent3LabelIsNotPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput3() == null, "'End Percent 3' label is present for Level 2." );
    }

    @Test ( priority = 268 )
    public void checkEndPercent4LabelIsNotPresentForLevel2() {
        Assert.assertTrue( ( (EditDataSettingsPage) page ).getScoreEndInput4() == null, "'End Percent 4' label is present for Level 2." );
    }

    @Test ( priority = 269 )
    public void checkPerformanceLevelSettingsAfterSelectingLevel5() {
        Assert.assertEquals( ( (EditDataSettingsPage) page ).selectProficiencyScaleSettings( ProficiencyScaleSettings.LEVEL_5_PERFORMANCE ), ProficiencyScaleSettings.LEVEL_5_PERFORMANCE.getPerformanceType() );
    }

    @Test ( priority = 270 )
    public void checkServerSettingsPageLaunched() {
        Map<String, String> pageDetails = ( (EditDataSettingsPage) page ).clickEditServerSettings();
        checkPageDetails( pageDetails, appProperties.getProperty( "ServerSettingsPageTitle" ), appProperties.getProperty( "OrgSerSetUrl" ) );
    }

    @Test ( priority = 271 )
    public void checkClickingExitDataSettingsNavigatedToOrganizationLandingPageWithNoChanges() {
        Map<String, String> pageDetails = ( (EditDataSettingsPage) page ).clickExitDataSettings();
        checkPageDetails( pageDetails, appProperties.getProperty( ADMIN_PAGE_TITLE ), appProperties.getProperty( "OrgPageUrl" ) );
    }

    @Test ( priority = 272 )
    public void checkClickingHomeTabNavingatedToHomePageWithoutChangesOnDataSettings() {
        ( (EditDataSettingsPage) page ).clickOrganizationNameLink( dataSettingsSchoolSelection );
        ( (EditDataSettingsPage) page ).clickDataSettingsTab();
        Map<String, String> pageDetails = ( (EditDataSettingsPage) page ).clickHomeTabBtn();
        checkPageDetails( pageDetails, appProperties.getProperty( ADMIN_PAGE_TITLE ), appProperties.getProperty( "HomePageUrl" ) );
    }

    @Test ( priority = 273 )
    public void checkClickingOrganizationTabNavingatedToHomePageWithoutChangesOnDataSettings() {
        ( (EditDataSettingsPage) page ).clickOrgTabBtn();
        ( (EditDataSettingsPage) page ).clickOrganizationNameLink( dataSettingsSchoolSelection );
        ( (EditDataSettingsPage) page ).clickDataSettingsTab();
        Map<String, String> pageDetails = ( (EditDataSettingsPage) page ).clickOrgTabBtn();
        checkPageDetails( pageDetails, appProperties.getProperty( ADMIN_PAGE_TITLE ), appProperties.getProperty( "OrgPageUrl" ) );
    }

    @Test ( priority = 274 )
    public void checkClickingUsersTabNavingatedToHomePageWithoutChangesOnDataSettings() {
        ( (EditDataSettingsPage) page ).clickOrganizationNameLink( dataSettingsSchoolSelection );
        ( (EditDataSettingsPage) page ).clickDataSettingsTab();
        //TODO remove once user tab implemented and uncomment the next lines
        ( (EditDataSettingsPage) page ).clickUsrTabBtn();
        //Map<String, String> pageDetails = ( (EditDataSettingsPage) page ).clickUsrTabBtn();
        //checkPageDetails( pageDetails, appProperties.getProperty( ADMIN_PAGE_TITLE ), appProperties.getProperty( "OrgPageUrl" ) );
    }
}
