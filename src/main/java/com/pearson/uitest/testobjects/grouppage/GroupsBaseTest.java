/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : GroupsBaseTest.java
 * 
 * Description : TestObject for groups Landing page UI functionalities.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/
package com.pearson.uitest.testobjects.grouppage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pearson.uitest.constants.GroupsConstants;
import com.pearson.uitest.constants.UsersConstants;
import com.pearson.uitest.enums.UsersTypeDropDown;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.GroupsLandingPage;
import com.pearson.uitest.pageobjects.UsersBasePage;
import com.pearson.uitest.testobjects.AdminBaseTest;

/**
 * Groups landing page UI functionalities will be tested using this class.
 */
public abstract class GroupsBaseTest extends AdminBaseTest {

    @Override
    protected void initialize( String browser ) {
        super.initialize( browser );
        page = new GroupsLandingPage( driver );
    };

    @Test ( priority = 201, description = "Entry Criteria: User is in landing page.</br>Execution Criteria: Navigate to groups landing page and checkbox column is verified.</br>Exit Criteria: User is in groups landing page." )
    public void checkPresenceOfCheckBoxColumnInTable() {
        page.clickUsrTabBtn();
        ( (UsersBasePage) page ).clickUsersGoBtn();
        CommonHelper.nap();
        Assert.assertNotNull( ( (GroupsLandingPage) page ).getGroupSelectAllCheckBox(), "Checkbox column header is missing!!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 202, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: Name column is verified in groups landing page.</br>Exit Criteria: User is in groups landing page." )
    public void checkNameColumnTitleInGroupTable() {
        Assert.assertEquals( ( (GroupsLandingPage) page ).getGroupsNameHeader().getText(), appProperties.getProperty( "UserGroupNameColumn" ), "Mismatch is observed in Name column header!!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 203, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: Owner column is verified in groups landing page.</br>Exit Criteria: User is in groups landing page." )
    public void checkOwnerColumnTitleInGroupTable() {
        Assert.assertEquals( ( (GroupsLandingPage) page ).getGroupsOwnerHeader().getText(), appProperties.getProperty( "UserGroupOwnerColumn" ), "Mismatch is observed in Owner column header!!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 204, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: Description column is verified in groups landing page.</br>Exit Criteria: User is in groups landing page." )
    public void checkDescriptionColumnTitleInGroupTable() {
        Assert.assertEquals( ( (GroupsLandingPage) page ).getGroupsDescriptionHeader().getText(), appProperties.getProperty( "UserGroupDescriptionColumn" ), "Mismatch is observed in Description column header!!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 210, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: User Type dropdown is verified after seacrch for group.</br>Exit Criteria: User is in groups landing page." )
    public void checkAllGroupChangesToAllUserTypesOnSearchWithAllGroupsSelected() {
        ( (GroupsLandingPage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_GROUPS.getType() );
        ( (GroupsLandingPage) page ).enterSearchTextInSearchInput( UsersConstants.SEARCH_TEXT );
        ( (GroupsLandingPage) page ).getUsersSearchBtn().click();
        Assert.assertEquals( ( (UsersBasePage) page ).getDefaultSelectedItemInUsersTypeDropDown(), UsersTypeDropDown.ALL_USER_TYPE.getType(), "Mismatch in the Type dropdown value!!" );
        log.info( UsersConstants.VERIFICATION_SUCCESS );
    }

    @Test ( priority = 211, description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: Footer text is verified from groups landing page.</br>Exit Criteria: User is in groups landing page." )
    public void checkNoOfGroupsFoundFooterTextForGroupAvaialableInGroupsTable() {
        ( (GroupsLandingPage) page ).selectItemFromOrgDropDown( UsersConstants.USER_ORG_NAME );
        ( (GroupsLandingPage) page ).selectItemFromUsersTypeDropDown( UsersTypeDropDown.ALL_GROUPS.getType() );
        ( (GroupsLandingPage) page ).clickUsersGoBtn();
        CommonHelper.nap();
        Assert.assertEquals( ( (GroupsLandingPage) page ).getRowsCountFromGroupsTable(), ( (GroupsLandingPage) page ).getRowsCountFromFooter(), "Mismatch in row count and no of rows displayed." );
    }

    @Test ( priority = 212, dataProvider = "sortColumn", description = "Entry Criteria: User is in groups landing page.</br>Execution Criteria: Sorting is verified from groups landing page.</br>Exit Criteria: User is in groups landing page." )
    public void checkSortingOfColumnsInGroupsTable( String colName, boolean ascDesc, String colNum, String failMsg ) {
        CommonHelper.nap();
        ( (GroupsLandingPage) page ).sortGroupsTableColumn( colName, ascDesc );
        Assert.assertEquals( ( (GroupsLandingPage) page ).getSortedValuesOfTableColumn( colNum, ascDesc ), 1, failMsg );
        log.info( GroupsConstants.VERIFICATION_SUCCESS );
    }

    @DataProvider ( name = "sortColumn" )
    public Object[][] sortColumnData() {
        Object[][] sortData = { { GroupsConstants.COLUMN_NAME, GroupsConstants.SORT_ASCENDING, GroupsConstants.COLUMN1, "Groups table is not sorted by Name column!!" },
                { GroupsConstants.COLUMN_NAME, GroupsConstants.SORT_DESCENDING, GroupsConstants.COLUMN1, "Name column is not sorted in descending order!!" },
                { GroupsConstants.COLUMN_OWNER, GroupsConstants.SORT_ASCENDING, GroupsConstants.COLUMN2, "Owner column is not sorted in ascending order!!" },
                { GroupsConstants.COLUMN_OWNER, GroupsConstants.SORT_DESCENDING, GroupsConstants.COLUMN2, "Owner column is not sorted in descending order!!" },
                { GroupsConstants.COLUMN_DESCRIPTION, GroupsConstants.SORT_ASCENDING, GroupsConstants.COLUMN3, "Description column is not sorted in ascending order!!" },
                { GroupsConstants.COLUMN_DESCRIPTION, GroupsConstants.SORT_DESCENDING, GroupsConstants.COLUMN3, "Description column is not sorted in descending order!!" } };
        return sortData;
    }
}
