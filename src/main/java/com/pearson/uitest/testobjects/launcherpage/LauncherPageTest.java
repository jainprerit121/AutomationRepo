package com.pearson.uitest.testobjects.launcherpage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pearson.uitest.pageobjects.LauncherPage;
import com.pearson.uitest.testobjects.BaseTest;

/**
 * Tests pertaining to the launcher page
 * 
 * @author vgaddra
 *
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public class LauncherPageTest extends BaseTest {

    public LauncherPageTest() {
        log = Logger.getLogger( LauncherPageTest.class );
    }

    private LauncherPage launchPage;

    @BeforeClass
    @Parameters ( "browser" )
    public void init( @Optional ( "FireFox-Win7" ) String browser ) {
        super.initialize( browser );
        launchPage = new LauncherPage( driver );
    }

    @Test ( priority = 1, description = "Entry Criteria: Admin user should launch the browser. Enter SuccessMaker Admin URL<br/>" + "Execution Criteria: URL should be launched successfully<br/>"
            + "Exit Criteria: User should see 'Sign in' button in Launcher Page.<br/>" )
    public void checkLauncherPageUrl() {
        assertEquals( launchPage.getLauncherPageUrl(), appProperties.getProperty( "LauncherUrl" ) );
    }

    @Test ( priority = 2, description = "Entry Criteria: Admin user should launch the browser. Enter SuccessMaker Admin URL<br/>" + "Execution Criteria: URL should be launched successfully<br/>"
            + "Exit Criteria: User should see 'SuccessMaker' page title in Launcher Page.<br/>" )
    public void checkLauncherPageTitle() {
        assertEquals( launchPage.getLaunchPageTitle(), appProperties.getProperty( "AdminPageTitle" ) );
    }

    @Test ( priority = 3, description = "Entry Criteria: Admin user should be in Sign in launcher page.<br/>" + "Execution Criteria: 'Click to Sign In' button exists.<br/>"
            + "Exit Criteria: 'Click to Sign In' button is verified and user is retained in same page.<br/>" )
    public void checkSignInBtnPresent() {
        assertNotNull( launchPage.getLauncherSignInBtn(), "Sign In button is not present" );
    }

    @Test ( priority = 4, description = "Entry Criteria: Admin user should be in Sign in launcher page.<br/>"
            + "Execution Criteria: Admin User should be able to see 'Copyright © 2007-2014 Pearson Education, Inc. or its affiliates. All rights reserved.'<br/>" + "Exit Criteria: Copyright message is verified and user is retained in same page." )
    public void checkCopyrightLauncher() {
        assertEquals( launchPage.getCopyrightTextLauncher(), appProperties.getProperty( "LauncherCopyRight" ) );
    }

    @Test ( priority = 5, description = "Entry Criteria: Admin user should be in Sign in launcher page.<br/>" + "Execution Criteria: Admin user should be able to see the 'SuccessMaker' logo.<br/>"
            + "Exit Criteria: 'SuccessMaker' logo is verified and user is retained in same page." )
    public void checkSMLogo() {
        assertNotNull( launchPage.getSMLogo(), appProperties.getProperty( "SMLogoMissingError" ) );
    }

    @Test ( priority = 6, description = "Entry Criteria: Admin user should be in Sign in launcher page.<br/>" + "Execution Criteria: Admin user should be able to see 'PEARSON' image.<br/>"
            + "Exit Criteria: 'PEARSON' image is verified and user is retained in same page." )
    public void checkPearsonImage() {
        assertNotNull( launchPage.getPearsonImg(), appProperties.getProperty( "PearsonImgMissingError" ) );
    }

    @Test ( priority = 7, description = "Entry Criteria: Admin user should be in Sign in launcher page.<br/>" + "Execution Criteria: Admin user should be able to see 'ALWAYS LEARNING' image.<br/>"
            + "Exit Criteria: 'ALWAYS LEARNING' image is verified and user is retained in same page." )
    public void checkAlwaysLearningImage() {
        assertNotNull( launchPage.getAlwaysLearningImg(), appProperties.getProperty( "LearningImgMissing" ) );
    }

    @Test ( priority = 8, description = "Entry Criteria: Admin user should be in Sign in launcher page.<br/>" + "Execution Criteria: 'Click to Sign In' button exists.<br/>"
            + "Exit Criteria: 'Click to Sign In' button is verified and user is retained in same page." )
    public void checkSignInBtn() {
        assertEquals( launchPage.getLauncherSignInBtn().getText(), "Click to Sign In" );
    }

    @Test ( priority = 9, description = "Entry Criteria: Admin user should be in Sign in launcher page.<br/>" + "Execution Criteria: Admin user should be able to click on 'Click to Sign In' button.<br/>"
            + "Exit Criteria: User should be navigated to Sign In page." )
    public void clickSignInBtn() {
        assertEquals( launchPage.launchLoginPage(), appProperties.getProperty( "LoginPageUrl" ) );

    }

    @Test ( priority = 10, description = "Entry Criteria: Admin user should be in Sign in launcher page.<br/>" + "Execution Criteria: Admin user should be able to click on 'Click to Sign In' button.<br/>"
            + "Exit Criteria: User should see 'SuccessMaker' page title in Login Page.<br/>" )
    public void checkLoginPageTitle() {
        assertEquals( launchPage.getLoginPageTitle(), appProperties.getProperty( "AdminPageTitle" ) );

    }

}
