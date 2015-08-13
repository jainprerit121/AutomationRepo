/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : SystemDistrictAdminUsersBaseTest.java
 * 
 * Description : TestObject for Users Landing page UI functionalities which are
 * common for System and District admins.
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

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.enums.UsersTypeDropDown;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.BasePage;
import com.pearson.uitest.pageobjects.UsersBasePage;

/**
 * Users landing page UI functionalities are tested for System and District
 * Administrator.
 */
public abstract class SystemDistrictAdminUsersBaseTest extends UsersBaseTest {
    private String districtName;

    @Test ( priority = 201, description = "Entry Criteria: User is Users landing page.</br>Execution Criteria: Default organization selected is validated.</br>Exit Criteria: User is in Users landing page. " )
    public void checkDefaultOrgSelectedInDisplayDropDown() {
    	page.clickHomeTabBtn();
    	CommonHelper.nap();
    	page.clickUsrTabBtn();
    	CommonHelper.nap();
        districtName = orgHelper.getAllDistrictsList().get( 0 );
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInOrgDropDown(), districtName, "Default selected org is not equals " + districtName + "" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 202, description = "Entry Criteria: User is in Userslanding page</br>Execution Criteria: Welcome message is verified for logged in User.</br>Exit Criteria: User is in Users landing page." )
    public void checkWelComeMessage() {
        String welcomeText = appProperties.getProperty( "UsersWelcomeText" );
        welcomeText = welcomeText.replaceFirst( "\"Dynamic\"", UsersConstants.STRING_DELIM + districtName + UsersConstants.STRING_DELIM );
        Assert.assertEquals( ( (UsersBasePage) page ).getUsersWelcomeText(), welcomeText, "Expected Welcome message is not displayed!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 203, description = "Entry Criteria: User is in Users landing page after logging in as System Admin.</br>Execution Criteria: User is navigated to Homepage and Organization is selected in Homepage. Organization selection in"
            + " Display dropdown is verified after navigating to Users landing page.</br>Exit Criteria: User is Users landing page." )
    public void checkDisplayListBoxItemsAfterSelectingSchoolInHome() {
        page.clickHomeTabBtn();
        ( (UsersBasePage) page ).selectItemFromDropDown( ( (UsersBasePage) page ).getOrganizationDropDown(), UsersConstants.USER_ORG_NAME );
        page.clickUsrTabBtn();
        CommonHelper.nap();
        List<String> expList = Lists.newArrayList( UsersConstants.USER_ORG_NAME );
        List<String> actList = ( (UsersBasePage) page ).getAllDropDownItemsInOrgDropDown();
        Assert.assertEquals( actList, expList, "Mismatch is observed in the display list box items!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 204, description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: User is navigated to Homepage and District is selected in Homepage. Organization selection in Display dropdown"
            + " is verified after navigating to Users landing page.<Exit Criteria: User is Users landing page." )
    public void checkDefaultOrgSelectionInDisplayDropDownAfterSelectingDistrictInOrgDropdownInHome() {
        page.clickHomeTabBtn();
        ( (BasePage) page ).selectItemFromDropDown( ( (UsersBasePage) page ).getOrganizationDropDown(), districtName );
        page.clickUsrTabBtn();
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInOrgDropDown(), districtName, "Default value in Display list box is not " + districtName + "" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 205, description = "Entry Criteria: User is in Users landing page after logging in as System Admin.</br>Execution Criteria: User is navigated to Homepage and District is selected in Organization dropdown Homepage."
            + " Organization selection in Display dropdown is verified after navigating to Users landing page.<Exit Criteria: User is Users landing page." )
    public void checkUserTypeDropDownAfterSelectingDistrictInOrgDropDown() {
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( districtName );
        List<String> expUserTypeValues = UsersTypeDropDown.getUserTypes();
        expUserTypeValues.remove( UsersTypeDropDown.UNASSIGNED_STUDENTS.getType() );
        List<String> actUserTypeValues = ( (UsersBasePage) page ).getAllDropDownItemsInUsersTypeDropDown();
        Assert.assertEquals( expUserTypeValues, actUserTypeValues, "Mismatch is observed in the User Type dropdown" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 206, description = "Entry Criteria: User is in Users landing page.</br>Execution: Display list box values are verified.</br>Exit Criteria: User is in Users landing page." )
    public void checkDisplayDropDownValues() {
        List<String> expectedOrgNames = orgHelper.getOrganizationNames( null );
        List<String> actualOrgNames = ( (UsersBasePage) page ).getAllDropDownItemsInOrgDropDown();
        Assert.assertEquals( actualOrgNames.size(), expectedOrgNames.size(), "Mismatch in number of organizations listed and actual number of organizations." );
        actualOrgNames.removeAll( expectedOrgNames );
        Assert.assertEquals( actualOrgNames.size(), 0, "Extra organizations are present - " + actualOrgNames.toString() );
    }

    @Test ( priority = 207, dataProvider = "organizationGradeGRoupSelection", description = "Entry Criteria: User is in Users landing page.</br>Execution Criteria: Resetting of Grade and Group dropdown values"
            + " is verified after selecting an organization.</br>Exit Criteria: User is Users landing page." )
    public void checkUserTypeAndGradeAfterSelectingOrgInDropDown( String gradeSelection, String groupSelection, String organizationName ) {
        log.info( "Test for selecting organization ->" + organizationName + " from organization drop down, grade -> " + gradeSelection + " from grade drop down and group ->" + groupSelection + " from group selection" );
        ( (UsersBasePage) page ).selectItemFromUsersTypeDropDown( groupSelection );
        ( (UsersBasePage) page ).selectItemFromGradeDropDown( gradeSelection );
        ( (UsersBasePage) page ).selectItemFromOrgDropDown( organizationName );
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInGradeDropDown(), UsersConstants.USER_GRADE_DEFAULT_SELECTED, "Grade dropdown is not reset to default." );
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInUsersTypeDropDown(), UsersTypeDropDown.ALL_GROUPS.getType(), "User type dropdown is not reset to default." );
    }

    @DataProvider ( name = "organizationGradeGRoupSelection" )
    public Object[][] organizationGradeGRoupSelection() {
        List<String> gradeList = ( (UsersBasePage) page ).getAllDropDownItemsInGradeDropDown();
        gradeList.remove( UsersTypeDropDown.UNASSIGNED_STUDENTS );
        int k = 0;
        String[] orgList = { districtName, UsersConstants.USER_ORG_NAME };
        int combinationSize = ( orgList.length * UsersTypeDropDown.values().length * gradeList.size() ) - ( orgList.length * gradeList.size() );
        Object[][] inputData = new Object[combinationSize][3];
        for ( int i = 0; i < orgList.length; i++ ) {
            for ( UsersTypeDropDown userType : UsersTypeDropDown.values() ) {
                for ( String grade : gradeList ) {
                    if ( userType != UsersTypeDropDown.UNASSIGNED_STUDENTS ) {
                        inputData[k][0] = grade;
                        inputData[k][1] = userType.getType();
                        inputData[k++][2] = k % 2 == 0 ? UsersConstants.USER_ORG_NAME : districtName;
                    }
                }
            }
        }
        return inputData;
    }

    //TODO:
    public void navigateUserLandingPagePostEditOrganizationYesConfirmation() {

    }

}
