/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : UsersBaseTest.java
 * 
 * Description : TestObject for Users Landing page UI functionalities.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/
package com.pearson.uitest.testobjects.userspage;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.pearson.uitest.constants.LocatorConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.enums.UsersTypeDropDown;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.UsersBasePage;
import com.pearson.uitest.testobjects.AdminBaseTest;

/**
 * Users landing page UI functionalities will be tested using this class.
 */
public abstract class UsersBaseTest extends AdminBaseTest {

    @Override
    protected void initialize( String browser ) {
        super.initialize( browser );
        page = new UsersBasePage( driver );
    };

    @Test ( priority = 101, description = "Entry Criteria: User is in Homepage</br>Execution Criteria: Navigated to Users page and highlighting of Users tab is verified.</br>Exit Criteria: User is in Users landing page" )
    public void checkPageNavigationAndTabHighlight() {
        page.clickUsrTabBtn();
        CommonHelper.nap();
        Assert.assertEquals( page.getElementAttribute( page.getUsersTabBtn(), "class" ), LocatorConstants.HIGHLIGHT_BUTTON_CLASS, "Users button is not highligted." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 103, description = "Entry Criteria: User in Users landing page</br>Execution Criteria: Help page is launched and help url is verified.</br>Exit Criteria: User is navigated back to Users landing page" )
    public void checkUsersHelp() {
        Map<String, String> actualPageDetails = page.launchHelp();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "SearchUserHelpTitle" ), appProperties.getProperty( "UsersHelp" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 104, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Display label is verified in Users landing page.</br>Exit Criteria: User is in Users landing page" )
    public void checkUsersDisplayLabel() {
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersDisplayLabel(), appProperties.getProperty( "OrgDispDropLabel" ), "Mismatch is observed in the Display label." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 105, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Presence of Display dropdown in Users landing page is verified.</br>Exit Criteria: User is Users landing page" )
    public void checkUsersDisplayDropDown() {
        Assert.assertNotNull( ( (UsersBasePage) page ).getUsersOrgDropDown(), "Users page Display dropdown is not present." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 107, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Organization dropdown sorting is verified.</br>Exit Criteria: User is in Users landing page." )
    public void checkOrganizationsDropDownValuesInAscendingOrder() {
        Assert.assertTrue( ( (UsersBasePage) page ).checkOrgDropdownValuesInAscendingOrder( ( (UsersBasePage) page ).getUsersOrgDropDown() ), "Org dropdown not in ascending order" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 108, description = "Entry Criteria: User is in Users landing page,</br>Execution Criteria: Presence of Search button is verified in Users landing page.</br>Exit Criteria: User is in users landing page." )
    public void checkPresenceOfSearchBtn() {
        Assert.assertNotNull( ( (UsersBasePage) page ).getUsersSearchBtn(), "Users page Search button is not present" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 109, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Presence of Users Grade dropdown is verified.</br>Exit Criteria: User is Users landing page." )
    public void checkUsersGrade() {
        Assert.assertNotNull( ( (UsersBasePage) page ).getUsersGradeDropDown(), "Users page Users grade dropdown is not present." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 110, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Default selection is verified in Grades dropdown.</br>Exit Criteria: User is Users landing page" )
    public void checkDefaultSelectionInGradesDropDown() {
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInGradeDropDown(), UsersConstants.USER_GRADE_DEFAULT_SELECTED, "Default grade selected is not equals " + UsersConstants.USER_GRADE_DEFAULT_SELECTED + "" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 111, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Grades dropdown values are verified in Users landing page.</br>Exit Criteria: User is in Users landing page." )
    public void checkUsersGradesDropDownValues() {
        List<String> expGradeValues = userHelper.getGradeListFromDB();
        expGradeValues.add( 0, UsersConstants.USER_GRADE_DEFAULT_SELECTED );
        List<String> actGradeValues = ( (UsersBasePage) page ).getAllDropDownItemsInGradeDropDown();
        Assert.assertEquals( expGradeValues, actGradeValues, "Mismatch is observed in grade dropdown values" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 112, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Presence of user Type dropdown is verified.</br>Exit Criteria: User is Users landing page" )
    public void checkUsersTypeDropDown() {
        Assert.assertNotNull( ( (UsersBasePage) page ).getUsersTypeDropDown(), "Users page User Type dropdown is not present." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 113, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Default selection in User Type dropdown is verified.</br>Exit Criteria: User is in Users landing page" )
    public void checkUsersDropDownDefaultSelection() {
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInUsersTypeDropDown(), UsersTypeDropDown.ALL_GROUPS.getType(), "Default selected item in user type dropdown is not equals " + UsersTypeDropDown.ALL_GROUPS.getType() + "" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 115, description = "Entry Criteria: User is in Users landing page</br>Execution Criteria: User Type dropdown values are verified after selecting any organization in Display listbox.</br>Exit Criteria: User is in Users landing page." )
    public void checkUsersTypeDropDownAfterSelectingSchoolInOrgDropDown() {
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( UsersConstants.USER_ORG_NAME );
        List<String> expUserTypeValues = UsersTypeDropDown.getUserTypes();
        List<String> actUserTypeValues = ( (UsersBasePage) page ).getAllDropDownItemsInUsersTypeDropDown();
        Assert.assertEquals( expUserTypeValues, actUserTypeValues, "Mismatch is observed in Users Type dropdown after school is selected in homepage." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 117, dataProvider = "gradeGroupSelection", description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Default selection of Type and Grades is verified when selecting Grade/Type in dropdown.</br>Exit Criteria: User is Users landing page." )
    public void checkDefaultSelectedInGradesAndUserTypeDropDownOnSelectingDiffrentGradeAndTypes( String groupSelection, String gradeSelection, String expectedGroupSelection, String expectedGradeSelection ) {
        log.info( "Test for selecting grade -> " + gradeSelection + " from grade drop down and group ->" + groupSelection + " from group selection" );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( groupSelection );
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInGradeDropDown(), expectedGradeSelection, "Default selected item in user type dropdown is not equals " + expectedGradeSelection + "" );
        ( (UsersBasePage) page ).selectItemFromGradeDropDown( gradeSelection );
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInUsersTypeDropDown(), expectedGroupSelection, "Default selected item in user type dropdown is not equals " + expectedGroupSelection + "" );
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInGradeDropDown(), gradeSelection, "Default selected item in grade dropdown is not equals " + gradeSelection + "" );
    }

    @DataProvider ( name = "gradeGroupSelection" )
    public Object[][] gradeGroupSelection() {
        List<String> gradeList = ( (UsersBasePage) page ).getAllDropDownItemsInGradeDropDown();
        gradeList.remove( UsersConstants.USER_GRADE_DEFAULT_SELECTED );
        Object[][] inputData = new Object[UsersTypeDropDown.values().length * gradeList.size()][4];
        int k = 0;
        String prevGrade = null;
        for ( UsersTypeDropDown userType : UsersTypeDropDown.values() ) {
            for ( String grade : gradeList ) {
                inputData[k][0] = userType.getType();
                inputData[k][1] = grade;
                inputData[k][2] = ( userType == UsersTypeDropDown.ALL_ACTIVE_STUDENTS || userType == UsersTypeDropDown.UNASSIGNED_STUDENTS ) ? userType.getType() : UsersTypeDropDown.ALL_ACTIVE_STUDENTS.getType();
                inputData[k++][3] = ( userType == UsersTypeDropDown.ALL_ACTIVE_STUDENTS || userType == UsersTypeDropDown.UNASSIGNED_STUDENTS ) ? prevGrade : UsersConstants.USER_GRADE_DEFAULT_SELECTED;
                prevGrade = grade;
            }
        }
        return inputData;
    }

    @Test ( priority = 118, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Presence of Go button is verified in Users landing page.</br>Exit Criteria: User is Users landing page." )
    public void checkPresenceOfGoBtn() {
        Assert.assertNotNull( ( (UsersBasePage) page ).getUsersGoBtn(), "Users page Go button is not present." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 119, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Go button is verified in Users landing page.</Exit Criteria: User is in Users landing page." )
    public void checkGoBtnText() {
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersGoBtn().getText(), appProperties.getProperty( "UsersGoBtnText" ), "Mismatch is observed in Go button text." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 120, description = "Entry Criteria: User is Users landing page.</br>Execution Criteria: Search label is verified in Users landing page.</br>Exit Criteria: User in Users landing page." )
    public void checkSearchUsersLabel() {
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersSearchLabel(), appProperties.getProperty( "UsersSearchLabel" ), "Mismatch is observed in Search Users label" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 121, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Search Input is verified in Users landing page.</br>Exit Criteria: User is in Users landing page." )
    public void checkPresenceOfUsersSearchInput() {
        Assert.assertNotNull( ( (UsersBasePage) page ).getUsersSearchInput(), "Users page Search input is not present." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 122, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Characters allowed in Search input are verified here.</br>Exit Criteria: User is Users landing page." )
    public void checkAllowedCharactersInSearchInput() {
        ( (UsersBasePage) page ).enterSearchTextInSearchInput( UsersConstants.USER_ALPHA_NUM_SPECIAL_CHAR_SEARCH );
        Assert.assertEquals( ( (UsersBasePage) page ).getEnteredTextInSearchInput(), UsersConstants.USER_ALPHA_NUM_SPECIAL_CHAR_SEARCH, "Retrieved value from Search Input doesn't match with actual value entered." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 123, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: User is navigated to Homepage and Organization is selected in Homepage. Organization selection in Display dropdown"
            + " is verified after navigating to Users landing page.<Exit Criteria: User is Users landing page." )
    public void checkDefaultOrgSelectionInDisplayDropDownAfterSelectingOrgInHome() {
        page.clickHomeTabBtn();
        ( (UsersBasePage) page ).selectItemFromDropDown( ( (UsersBasePage) page ).getOrganizationDropDown(), UsersConstants.USER_ORG_NAME );
        page.clickUsrTabBtn();
        CommonHelper.nap();
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInOrgDropDown(), UsersConstants.USER_ORG_NAME, "Default org is not equals to " + UsersConstants.USER_ORG_NAME + "" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 124, description = "Entry Criteria: User is Users landing page.</br>Execution Criteria: Welcome text is verified in Users landing page.</br>Exit Criteria: User is in Users landing page." )
    public void checkWelcomeMessageAfterSelectingOrgInHomeAndNavigatingToUsers() {
        String welcomeText = appProperties.getProperty( "UsersWelcomeText" );
        welcomeText = welcomeText.replaceFirst( "\"Dynamic\"", UsersConstants.STRING_DELIM + UsersConstants.USER_ORG_NAME + UsersConstants.STRING_DELIM );
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersWelcomeText(), welcomeText, "Mismatch in the Welcome message." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 125, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Organization dropdown is verified after selecting organization in Home.</br>Exit Criteria: User is in Users landing page." )
    public void checkUsersPageOrgDropDownHasOnlySelectedOrgInHome() {
        List<String> expList = Lists.newArrayList( UsersConstants.USER_ORG_NAME );
        List<String> actList = ( (UsersBasePage) page ).getAllDropDownItemsInOrgDropDown();
        Assert.assertEquals( actList, expList, "Mismatch is observed in Display list box items." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 126, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Search button is verifed.</br>Exit Criteria: User is in Users landing page." )
    public void checkUsersSearchBtnText() {
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersSearchBtn().getText(), appProperties.getProperty( "UsersSearchBtnText" ), "Mismatch is observed in Search Button label" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 127, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Presence of transfer Students button is verified in Users landing page.</br>Exit Criteria: User is Users landing page." )
    public void checkPresenceOfTransferStudentsBtn() {
        Assert.assertNotNull( ( (UsersBasePage) page ).getUsersTransferStudentBtn(), "Users page Transfer Students button is not present." );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 128, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Transfer Students button is verified.</br>Exit Criteria: User is in Users landing page." )
    public void checkUsersTransferStudentsBtnText() {
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersTransferStudentBtn().getText(), appProperties.getProperty( "UsersTransferStudentsBtnText" ), "Mismatch is observed in Transfer Students button label" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

}
