package com.pearson.uitest.testobjects.loginpage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;

/**
 * Login tests for System Admin login
 * 
 * @author vgaddra
 *
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public class SystemAdminLoginTest extends LoginPageBaseTest {

    public SystemAdminLoginTest() {
        log = Logger.getLogger( SystemAdminLoginTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        super.initialize( browser );
    }

    @Test ( priority = 101 )
    public void checkSuccessfulSystemAdminLogin() {
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        Assert.assertEquals( loginPage.getAdminHomeTitle().getText(), appProperties.getProperty( "HomeLeftTitle" ) );
    }

    @Test ( priority = 102 )
    public void checkSuccessfulSystemAdminLogout() {
        loginPage.logoutAdmin();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

    @Test ( priority = 103, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid system admin credentials for both Username and Password fields and click Sign In, click Close in alert<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInSystemAdminLoginConfirmClose() {
        checkAlreadyLoggedInUseCloseStillInLoginPage( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 104, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid system admin credentials for both Username and Password fields and click Sign In, click No in alert<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInSystemAdminLoginConfirmNo() {
        checkAlreadyLoggedInUseNoStillInLoginPage( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 105, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid system admin credentials for both Username and Password fields and click Sign In, click Yes in alert<br/>"
            + "Exit Criteria: You will be taken to SystemAdmin Home Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInSystemAdminLoginConfirmYes() {
        checkAlreadyLoggedInUseYesShowsHomePage( AdminUIConstants.SYSTEM_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        Assert.assertEquals( loginPage.getAdminHomeTitle().getText(), appProperties.getProperty( "HomeLeftTitle" ) );
    }

    @Test ( priority = 106, description = "Entry Criteria: System admin user will be in system admin Home Page.<br/>" + "Execution Criteria: You will be taken to System Admin Home Page, click Logout there.<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after logout" )
    public void systemAdminLogout() {
        loginPage.logoutAdmin();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

}
