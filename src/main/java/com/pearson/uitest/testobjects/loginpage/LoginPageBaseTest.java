package com.pearson.uitest.testobjects.loginpage;

import static org.testng.Assert.assertNotNull;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.testobjects.BaseTest;

/**
 * Common login page tests
 * 
 * @author vgaddra
 *
 */
public abstract class LoginPageBaseTest extends BaseTest {

    public void initialize( String browser ) {
        super.initialize( browser );
        loginPage.launchLoginPage();
    }

    @Test ( priority = 1, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Page is launched and SuccessMaker Logo is seen<br/>" + "Exit Criteria: You are in SuccessMaker Login Page." )
    public void checkSMLogo() {
        assertNotNull( loginPage.getSMLogo(), "Success Maker logo is missing." );
    }

    @Test ( priority = 2, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Page is launched and Pearson Image is seen<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkPearsonImage() {
        assertNotNull( loginPage.getPearsonImg(), "PEARSON image is missing." );
    }

    @Test ( priority = 3, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Page is launched and Always Learning Image is seen<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkAlwaysLearningImage() {
        assertNotNull( loginPage.getLearningImage(), "ALWAYS LEARNING image is missing." );
    }

    @Test ( priority = 4, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Username text field is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkUserNameLabel() {
        Assert.assertNotNull( loginPage.getUserName(), "Username field is not present." );
    }

    @Test ( priority = 5, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Username text field title is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkUserNameLabelText() {
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

    @Test ( priority = 6, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Password text field is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkPasswordLabel() {
        Assert.assertNotNull( loginPage.getPassword(), "Password field is not present." );
    }

    @Test ( priority = 7, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Password text field title is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkPasswordLabelText() {
        Assert.assertEquals( loginPage.getPasswordTitle().getText(), appProperties.getProperty( "PassFieldTitle" ) );
    }

    @Test ( priority = 8, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Sign In button is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkSignInButtonPresent() {
        Assert.assertNotNull( loginPage.getSignInButton(), "SignIn Button is not present." );
    }

    @Test ( priority = 9, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Sign In button text is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkSignInButton() {
        Assert.assertEquals( loginPage.getSignInButton().getText(), appProperties.getProperty( "LoginSignInBtn" ) );
    }

    @Test ( priority = 10, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Pearson copyright is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkCopyRight() {
        Assert.assertEquals( loginPage.getCopyRight().getText(), appProperties.getProperty( "LoginCopyRight" ) );
    }

    @Test ( priority = 11, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Link MyTrainingConnection is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkTrainingConnectLinkText() {
        Assert.assertEquals( loginPage.getTrainingConentLink().getText(), appProperties.getProperty( "LoginTrainingLink" ) );
    }

    @Test ( priority = 12, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Click on MyTrainingConnection link<br/>"
            + "Exit Criteria: You are in My Training Connection page in new browser window and back to SuccessMaker Login Page" )
    public void launchTrainingConnectLink() {
        Map<String, String> actualPageDetails = loginPage.launchTrainingConnectLink();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "LoginTrainingLinkTitle" ), appProperties.getProperty( "LoginTrainingLinkUrl" ) );
    }

    @Test ( priority = 13, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Link Privacy Policy is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkPrivacyPolicyLinkText() {
        Assert.assertEquals( loginPage.getPrivacyPolicyLink().getText(), appProperties.getProperty( "LoginPrivacyPolicy" ) );
    }

    @Test ( priority = 14, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Click on Privacy Policy link<br/>"
            + "Exit Criteria: You are in SuccesMaker Privary Statement page in new browser window and then back to SuccessMaker Login Page" )
    public void launchPrivacyPolicyLink() {
        Map<String, String> actualPageDetails = loginPage.launchPrivacyPolicyLink();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "LoginPrivacyPolicyTitle" ), appProperties.getProperty( "LoginPrivacyPolicyUrl" ) );
    }

    @Test ( priority = 15, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Link Terms of Use is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkTermsofUseLink() {
        Assert.assertEquals( loginPage.getTOULink().getText(), appProperties.getProperty( "LoginTermsOfUse" ) );
    }

    @Test ( priority = 16, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Click on Terms of Use link<br/>"
            + "Exit Criteria: You are in Terms of User page in new browser window and then back to SuccessMaker Login Page" )
    public void launchTermsOfUseLink() {
        Map<String, String> actualPageDetails = loginPage.launchTermsOfUseLink();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "LoginTermsOfUseTitle" ), appProperties.getProperty( "LoginTermsOfUseLink" ) );
    }

    @Test ( priority = 17, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Link SuccessMaker Version Info is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkSMVersionLink() {
        Assert.assertEquals( loginPage.getSmVersionInfoLink().getText(), appProperties.getProperty( "LoginSMVersionText" ) );
    }

    @Test ( priority = 18, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Click on SuccessMaker Version Info link<br/>"
            + "Exit Criteria: You will see SuccessMaker Version Information in SuccessMaker Login Page" )
    public void launchSMVersionLink() {
        Assert.assertEquals( loginPage.launchSMVersionLink(), appProperties.getProperty( "LoginSMVersionTitle" ) );
        loginPage.getSmVersionClose().click();
    }

    @Test ( priority = 19, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Link Get help at Pearson Community Connection is visible<br/>" + "Exit Criteria: You are in SuccessMaker Login Page" )
    public void checkSupportLink() {
        Assert.assertEquals( loginPage.getSupportLink().getText(), appProperties.getProperty( "LoginSupportLink" ) );
    }

    @Test ( priority = 20, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Click on Get help at Pearson Community Connection link<br/>"
            + "Exit Criteria: You are in Community Connection page in new browser window and then back to SuccessMaker Login Page" )
    public void launchSupportLink() {
        Map<String, String> actualPageDetails = loginPage.launchSupportLink();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "LoginSupportLinkTitle" ), appProperties.getProperty( "LoginSupportLinkUrl" ) );
    }

    @Test ( priority = 21, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set empty text for both credential fields and click Sign In<br/>"
            + "Exit Criteria: You will see 'Enter a valid user name and password. If you need assistance, contact your administrator.' message in SuccessMaker Login Page" )
    public void loginWithEmptyCredentials() {
        loginPage.login( "", "" );
        Assert.assertEquals( loginPage.getLoginError().getText(), appProperties.getProperty( "LoginError" ) );
    }

    @Test ( priority = 22, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set empty text for Username field and valid text for Password field and click Sign In<br/>"
            + "Exit Criteria: You will see 'Enter a valid user name and password. If you need assistance, contact your administrator.' message in SuccessMaker Login Page" )
    public void loginWithEmptyUsername() {
        loginPage.login( "", AdminUIConstants.DEFAULT_PASSWORD );
        Assert.assertEquals( loginPage.getLoginError().getText(), appProperties.getProperty( "LoginError" ) );
    }

    @Test ( priority = 23, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set valid text for Username field and empty text for Password field and click Sign In<br/>"
            + "Exit Criteria: You will see 'Enter a valid user name and password. If you need assistance, contact your administrator.' message in SuccessMaker Login Page" )
    public void loginWithEmptyPassword() {
        loginPage.login( AdminUIConstants.SYSTEM_ADMIN_USERNAME, "" );
        Assert.assertEquals( loginPage.getLoginError().getText(), appProperties.getProperty( "LoginError" ) );
    }

    @Test ( priority = 24, description = "Entry Criteria: User will be in SuccessMaker Login Page.<br/>" + "Execution Criteria: Set invalid credentials for both Username and Password fields and click Sign In<br/>"
            + "Exit Criteria: You will see 'Enter a valid user name and password. If you need assistance, contact your administrator.' message in SuccessMaker Login Page" )
    public void invalidLogin() {
        loginPage.login( "abc", "bcd" );
        Assert.assertEquals( loginPage.getLoginError().getText(), appProperties.getProperty( "LoginError" ) );
    }

    protected void checkAlreadyLoggedInUseCloseStillInLoginPage( String username, String password ) {
        loginPage.preLogin( username, password );
        initialize( browser );
        loginPage.login( username, password );
        loginPage.closeReloginAlertConfirm();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

    protected void checkAlreadyLoggedInUseNoStillInLoginPage( String username, String password ) {
        loginPage.login( username, password );
        loginPage.clickReloginAlertNoOption();
        Assert.assertEquals( loginPage.getUserNameTitle().getText(), appProperties.getProperty( "UserFieldTitle" ) );
    }

    protected void checkAlreadyLoggedInUseYesShowsHomePage( String username, String password ) {
        loginPage.login( username, password );
        loginPage.clickReloginAlertYesOption();
    }

}
