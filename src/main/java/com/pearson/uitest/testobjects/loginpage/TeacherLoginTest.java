package com.pearson.uitest.testobjects.loginpage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;

/**
 * Login tests for Teacher login
 * 
 * @author vgaddra
 *
 */
public class TeacherLoginTest extends LoginPageBaseTest {

    public TeacherLoginTest() {
        log = Logger.getLogger( TeacherLoginTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        super.initialize( browser );
    }

    @Test ( priority = 101 )
    public void checkSuccessfulTeacherLogin() {
        loginPage.login( AdminUIConstants.TEACHER_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        Assert.assertTrue( loginPage.getTeacherHomePageUrl().contains( "ManagementConsole" ) );
    }

    @Test ( priority = 102 )
    public void checkSuccessfulTeacherLogout() {
        loginPage.logoutTeacher();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

    @Test ( priority = 103, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid teacher credentials for both Username and Password fields and click Sign In, click Close in alert<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInTeacherLoginConfirmClose() {
        checkAlreadyLoggedInUseCloseStillInLoginPage( AdminUIConstants.TEACHER_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 104, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid teacher credentials for both Username and Password fields and click Sign In, click No in alert<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInTeacherLoginConfirmNo() {
        checkAlreadyLoggedInUseNoStillInLoginPage( AdminUIConstants.TEACHER_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 105, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid teacher credentials for both Username and Password fields and click Sign In, click Yes in alert<br/>"
            + "Exit Criteria: You will be taken to Teacher Home Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInTeacherLoginConfirmYes() {
        checkAlreadyLoggedInUseYesShowsHomePage( AdminUIConstants.TEACHER_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        Assert.assertTrue( loginPage.getTeacherHomePageUrl().contains( "ManagementConsole" ) );
    }

    @Test ( priority = 106, description = "Entry Criteria: Teacher user will be in Teacher Home Page.<br/>" + "Execution Criteria: You will be taken to Teacher Home Page, click Logout there.<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after logout" )
    public void teacherLogout() {
        loginPage.logoutTeacher();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

}
