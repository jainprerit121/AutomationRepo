package com.pearson.uitest.testobjects.loginpage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.AdminUIConstants;

/**
 * Login tests for District Admin login
 * 
 * @author vgaddra
 *
 */
public class DistrictAdminLoginTest extends LoginPageBaseTest {

    public DistrictAdminLoginTest() {
        log = Logger.getLogger( DistrictAdminLoginTest.class );
    }

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        super.initialize( browser );
    }

    @Test ( priority = 101 )
    public void checkSuccessfulDistrictAdminLogin() {
        loginPage.login( AdminUIConstants.DISTRICT_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        Assert.assertEquals( loginPage.getAdminHomeTitle().getText(), appProperties.getProperty( "HomeLeftTitle" ) );
    }

    @Test ( priority = 102 )
    public void checkSuccessfulDistrictAdminLogout() {
        loginPage.logoutAdmin();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

    @Test ( priority = 103, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid district admin credentials for both Username and Password fields and click Sign In, click Close in alert<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInDistrictAdminLoginConfirmClose() {
        checkAlreadyLoggedInUseCloseStillInLoginPage( AdminUIConstants.DISTRICT_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 104, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid district admin credentials for both Username and Password fields and click Sign In, click No in alert<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInDistrictAdminLoginConfirmNo() {
        checkAlreadyLoggedInUseNoStillInLoginPage( AdminUIConstants.DISTRICT_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
    }

    @Test ( priority = 105, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid district admin credentials for both Username and Password fields and click Sign In, click Yes in alert<br/>"
            + "Exit Criteria: You will be taken to DistrictAdmin Home Page after closing 'User is already signed in..' message." )
    public void checkAlreadyLoggedInDistrictAdminLoginConfirmYes() {
        checkAlreadyLoggedInUseYesShowsHomePage( AdminUIConstants.DISTRICT_ADMIN_USERNAME, AdminUIConstants.DEFAULT_PASSWORD );
        Assert.assertEquals( loginPage.getAdminHomeTitle().getText(), appProperties.getProperty( "HomeLeftTitle" ) );
    }

    @Test ( priority = 106, description = "Entry Criteria: District admin user will be in District admin Home Page.<br/>" + "Execution Criteria: You will be taken to District Admin Home Page, click Logout there.<br/>"
            + "Exit Criteria: You will be back in SuccessMaker Login Page after logout" )
    public void districtAdminLogout() {
        loginPage.logoutAdmin();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
        loginPage.releaseDriverForIE( browser );
    }

}
