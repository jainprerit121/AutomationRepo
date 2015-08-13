package com.pearson.uitest.testobjects.loginpage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;

/**
 * Login tests for School Admin login
 * 
 * @author vgaddra
 *
 */

public class SchoolAdminLoginTest extends LoginPageBaseTest {

    public SchoolAdminLoginTest() {
        log = Logger.getLogger( SchoolAdminLoginTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        super.initialize( browser );
    }

    @Test ( priority = 101 )
    public void checkSuccessfulSchoolAdminLogin() {
        loginPage.login( AdminUIConstants.SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        Assert.assertEquals( loginPage.getAdminHomeTitle().getText(), appProperties.getProperty( "HomeLeftTitle" ) );
    }

    @Test ( priority = 102 )
    public void checkSuccessfulSchoolAdminLogout() {
        loginPage.logoutAdmin();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

    @Test ( priority = 103, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid school admin credentials for both Username and Password fields and click Sign In, click Close in alert<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInSchoolAdminLoginConfirmClose() {
        checkAlreadyLoggedInUseCloseStillInLoginPage( AdminUIConstants.SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 104, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid school admin credentials for both Username and Password fields and click Sign In, click No in alert<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInSchoolAdminLoginConfirmNo() {
        checkAlreadyLoggedInUseNoStillInLoginPage( AdminUIConstants.SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 105, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid school admin credentials for both Username and Password fields and click Sign In, click Yes in alert<br/>"
            + "Exit Criteria: You will be taken to SchoolAdmin Home Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInSchoolAdminLoginConfirmYes() {
        checkAlreadyLoggedInUseYesShowsHomePage( AdminUIConstants.SCHOOL_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        Assert.assertEquals( loginPage.getAdminHomeTitle().getText(), appProperties.getProperty( "HomeLeftTitle" ) );
    }

    @Test ( priority = 106, description = "Entry Criteria: School admin user will be in School admin Home Page.<br/>" + "Execution Criteria: You will be taken to School Admin Home Page, click Logout there.<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after logout" )
    public void teacherLogout() {
        loginPage.logoutAdmin();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

}
