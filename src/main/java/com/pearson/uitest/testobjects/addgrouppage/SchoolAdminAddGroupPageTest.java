/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : SchoolAdminAddGroupPageTest.java
 * 
 * Description : TestObject for add group page UI functionalities for School
 * admin.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/

package com.pearson.uitest.testobjects.addgrouppage;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.GroupsConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.pageobjects.GroupDetailsPage;

/**
 * This class tests the UI functionalities of Add Group page after logging in as
 * School Admin.
 */
public class SchoolAdminAddGroupPageTest extends GroupDetailsBaseTest {

    public SchoolAdminAddGroupPageTest() {
        log = Logger.getLogger( SchoolAdminAddGroupPageTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        initialize( browser );
        orgHelper.deleteOrganization( UsersConstants.USER_ORG_NAME );
        Integer orgId1 = orgHelper.createOrganization( UsersConstants.USER_ORG_NAME, UsersConstants.USER_ORG_ID );
        userHelper.createTeacher( AdminUIConstants.TEACHER_FIRSTNAME, AdminUIConstants.TEACHER_LASTNAME, AdminUIConstants.TEACHER_USERNAME, AdminUIConstants.DEFAULT_PASSWORD, orgId1 );
        userHelper.createTeacher( GroupsConstants.TEACHER_FIRSTNAME_SAME_ORG, GroupsConstants.TEACHER_LASTNAME_SAME_ORG, GroupsConstants.TEACHER_USERNAME_SAME_ORG, AdminUIConstants.DEFAULT_PASSWORD, orgId1 );
        userHelper.createSchoolAdmin( AdminUIConstants.SCHOOL_ADMIN_FIRSTNAME, AdminUIConstants.SCHOOL_ADMIN_LASTNAME, UsersConstants.USER_SCHOOL_ADMIN, AdminUIConstants.DEFAULT_ENCRYPTED_PASSWORD, orgId1 );
        loginPage.login( UsersConstants.USER_SCHOOL_ADMIN, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 96, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Default value in name input is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkNameInputDefaultValue() {
        Assert.assertEquals( ( (GroupDetailsPage) page ).getElementAttribute( ( (GroupDetailsPage) page ).getGroupNameInput(), "value" ), "", "Mismatch in the default value!!" );
    }

    @Test ( priority = 97, description = "Entry Criteria: User is in Add Group page.</br>Execution Criteria: Default value in owner dropdown is verified.</br>Exit Criteria: User is in Add Group page." )
    public void checkOwnerDropDownDefaultValue() {
        Assert.assertEquals( ( (GroupDetailsPage) page ).getDefaultSelectedInGroupOwnerDropDown(), appProperties.getProperty( "AddGroupDefaultDropValue" ), "Mismatch in default dropdown value!!" );
    }

    @Test ( priority = 98, description = "Entry Criteria: User is in Add Group page</br>Execution Criteria: Help page is launched in new page and verifed.</br>Exit Criteria: User is in Add Group page." )
    public void checkHelp() {
        Map<String, String> actualPageDetails = page.launchHelp();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "HelpTitle" ), appProperties.getProperty( "AddGroupHelp" ) );
    }

    @Test ( priority = 401, description = "Entry Criteria: User is in add group page.</br>Execution Criteria: Logout button is clicked and navigation is verified from groups landing page.</br>Exit Criteria: User is in Login page." )
    public void checkLogout() {
        Map<String, String> actualLogoutPageDetails = page.logoutAdminPanel( browser );
        checkPageDetails( actualLogoutPageDetails, appProperties.getProperty( "AdminPageTitle" ), appProperties.getProperty( "LogoutUrl" ) );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

}
