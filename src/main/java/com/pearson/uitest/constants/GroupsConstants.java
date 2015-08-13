/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : GroupsConstants.java
 * 
 * Description : Constants related to GROUPS page are defined here.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/
package com.pearson.uitest.constants;

/**
 * All the constant related to Groups page are defined here.
 */
public interface GroupsConstants {

    public static final String GROUP_NAME_INPUT_EXCEEDING_35_CHAR = "Group name exceeding 35 characters limit";
    public static final int GROUP_NAME_LIMIT = 35;

    public static final String GROUP_NAME = "Automation Group";
    public static final String GROUP_NAME_SECOND = "Automation Group Second";
    public static final int GROUP_TYPE_ID = 1;
    public static final String GROUP_DESCRIPTION = "Automation group descritpion";
    public static final String GROUP_DESCRIPTION_SECOND = "Automation group description second";
    public static final String GROUP_SCHOOL_ADMIN_USERNAME = "GroupSchoolAutomated";
    public static final String GROUP_SCHOOL_ADMIN_FIRSTNAME = "GroupSchool";
    public static final String GROUP_SCHOOL_ADMIN_LASTNAME = "Group Admin";
    public static final String GROUP_LESS_SCHOOL_NAME = "Group Less School";
    public static final String GROUP_LESS_SCHOOL_ID = "GS01";
    public static final String GROUP_NAME_SPECIAL = "TestGroup$%^";
    public static final String TEACHER_FIRSTNAME_NEW = "Teacher";
    public static final String TEACHER_LASTNAME_NEW = "Second";
    public static final String TEACHER_USERNAME_NEW = "AutomatedTeacher";

    public static final String VERIFICATION_SUCCESS = "Verified the scenario!";
    public static final String RECORD_NOT_FOUND = "No Records Found";

    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_OWNER = "Owner";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN1 = "1";
    public static final String COLUMN2 = "2";
    public static final String COLUMN3 = "3";

    public static final boolean SORT_ASCENDING = false;
    public static final boolean SORT_DESCENDING = true;

    public static final String TEACHER_FIRSTNAME_SAME_ORG = "TeacherSameOrg";
    public static final String TEACHER_LASTNAME_SAME_ORG = "Automated";
    public static final String TEACHER_USERNAME_SAME_ORG = "TeacherSameOrg";

    public static final String TEACHER_FIRSTNAME_DIFF_ORG = "TeacherDiffOrg";
    public static final String TEACHER_LASTNAME_DIFF_ORG = "Automated";
    public static final String TEACHER_USERNAME_DIFF_ORG = "TeacherDiffOrg";

    public static final String ADD_GROUP_NAME = "Test1011";
    public static final String ADD_GROUP_DESCRIPTION = "Test Group Added";
    public static final String GROUP_TITLE = "Mr. ";

}
