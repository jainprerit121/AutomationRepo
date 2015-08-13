package com.pearson.uitest.testobjects.loginpage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;

/**
 * Login tests for student login
 * 
 * @author vgaddra
 *
 */
public class StudentLoginTest extends LoginPageBaseTest {

    StudentLoginTest() {
        log = Logger.getLogger( StudentLoginTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        super.initialize( browser );
    }

    @Test ( priority = 101 )
    public void checkSuccessfulStudentLogin() {
        loginPage.login( AdminUIConstants.STUDENT_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        Assert.assertEquals( loginPage.getStudentHomePageTitle(), appProperties.getProperty( "studentHomePageTitle" ) );
    }

    @Test ( priority = 102 )
    public void checkSuccessfulStudentLogout() {
        loginPage.logoutStudent();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

    @Test ( priority = 103, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid student credentials for both Username and Password fields and click Sign In, click Close in alert<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInStudentLoginConfirmClose() {
        checkAlreadyLoggedInUseCloseStillInLoginPage( AdminUIConstants.STUDENT_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 104, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid student credentials for both Username and Password fields and click Sign In, click No in alert<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInStudentLoginConfirmNo() {
        checkAlreadyLoggedInUseNoStillInLoginPage( AdminUIConstants.STUDENT_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 105, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid student credentials for both Username and Password fields and click Sign In, click Yes in alert<br/>"
            + "Exit Criteria: You will be taken to Student Home Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInStudentLoginConfirmYes() {
        checkAlreadyLoggedInUseYesShowsHomePage( AdminUIConstants.STUDENT_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        Assert.assertEquals( loginPage.getStudentHomePageTitle(), appProperties.getProperty( "studentHomePageTitle" ) );
    }

    @Test ( priority = 106, description = "Entry Criteria: Student user will be in Student Home Page.<br/>" + "Execution Criteria: You will be taken to Student Home Page, click Logout there.<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after logout" )
    public void studentLogout() {
        loginPage.logoutStudent();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

}
