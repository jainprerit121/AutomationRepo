package com.pearson.uitest.pageobjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.pearson.uitest.driver.AdminWebDriver;
import com.pearson.uitest.helper.CommonHelper;

/**
 *
 * This class defines all login page elements and their actions
 */
public class LoginPage extends LauncherPage {

    private By userNameTitle = By.xpath( "//html/body/div[3]/form/div[2]/div" );
    private By passwordTitle = By.xpath( "//html/body/div[3]/form/div[3]/div" );
    private By userField = By.name( "user" );
    private By passwordField = By.name( "pwd" );
    private By launchButton = By.id( "launchButton" );

    private By smVersionClose = By.xpath( "//html/body/div[2]/div/div/a" );
    private By loginError = By.className( "login-error" );
    private By loginLogo = By.xpath( "//*[@id='opener']/div[3]/div[2]/img" );
    private By pearsonImage = By.xpath( "//*[@id='sm-header-left']/img" );
    private By learningImage = By.xpath( "//*[@id='sm-header-right']/img" );

    private By signInButton = By.xpath( "/html/body/div[3]/form/div[4]/button" );
    private By signInAlertYes = By.xpath( "//div[(contains(@class,'ui-dialog-buttonpane'))]/button[1]" );
    private By signInAlertNo = By.xpath( "//div[(contains(@class,'ui-dialog-buttonpane'))]/button[2]" );
    private By signInAlertClose = By.xpath( "//a[(contains(@class,'ui-dialog-titlebar-close'))]" );

    private By studentLogoutButton = By.className( "sm-logoutbtn" );
    private By teacherLogoutButton = By.className( "topPanelSignoutButton" );
    private By adminLogoutButton = By.linkText( "Log out" );

    private By supportLink = By.xpath( "/html/body/div[3]/div[3]/a" );
    private By trainingConnect = By.xpath( "/html/body/div[3]/div[4]/a" );
    private By smVersionInfo = By.xpath( "/html/body/div[3]/div[5]/a" );
    private By privacyPolicy = By.xpath( "/html/body/div[3]/div[6]/a[1]" );
    private By tof = By.xpath( "/html/body/div[3]/div[6]/a[2]" );
    private By smVersionTitle = By.xpath( "/html/body/div[2]/div/h1" );
    private By copyRight = By.xpath( "/html/body/div[3]/div[7]" );

    private By pearsonTitle = By.id( "appTitle" );

    public LoginPage( WebDriver driver ) {
        super( driver );
        log = Logger.getLogger( LoginPage.class );
    }

    public WebElement getUserNameTitle() {
        return getElement( userNameTitle );
    }

    public WebElement getPasswordTitle() {
        return getElement( passwordTitle );
    }

    public WebElement getUserName() {
        return getElement( userField );
    }

    public WebElement getPassword() {
        return getElement( passwordField );
    }

    public WebElement getLanunchButton() {
        return getElement( launchButton );
    }

    public WebElement getSignInButton() {
        return getElement( signInButton );
    }

    public WebElement getSignInAlertClose() {
        return getElement( signInAlertClose );
    }

    public WebElement getSignInYesButton() {
        return getElement( signInAlertYes );
    }

    public WebElement getSignInNoButton() {
        return getElement( signInAlertNo );
    }

    public WebElement getStudentLogoutButton() {
        return getElement( studentLogoutButton );
    }

    public WebElement getTeacherLogoutButton() {
        return getElement( teacherLogoutButton );
    }

    public WebElement getAdminLogoutButton() {
        return getElement( adminLogoutButton );
    }

    public WebElement getSupportLink() {
        return getElement( supportLink );
    }

    public WebElement getTrainingConentLink() {
        return getElement( trainingConnect );
    }

    public WebElement getSmVersionInfoLink() {
        return getElement( smVersionInfo );
    }

    public WebElement getPrivacyPolicyLink() {
        return getElement( privacyPolicy );
    }

    public WebElement getSmVersionTitle() {
        return getElement( smVersionTitle );
    }

    public WebElement getTOULink() {
        return getElement( tof );
    }

    public WebElement getCopyRight() {
        return getElement( copyRight );
    }

    public WebElement getSmVersionClose() {
        return getElement( smVersionClose );
    }

    public WebElement getLoginError() {
        return getElement( loginError );
    }

    public WebElement getLoginLogo() {
        return getElement( loginLogo );
    }

    public WebElement getPearsonImage() {
        return getElement( pearsonImage );
    }

    public WebElement getLearningImage() {
        return getElement( learningImage );
    }

    public Map<String, String> launchPrivacyPolicyLink() {
        return getLaunchPageDetails( getPrivacyPolicyLink() );
    }

    public Map<String, String> launchTrainingConnectLink() {
        return getLaunchPageDetails( getTrainingConentLink() );
    }

    public Map<String, String> launchTermsOfUseLink() {
        return getLaunchPageDetails( getTOULink() );
    }

    public Map<String, String> launchSupportLink() {
        return getLaunchPageDetails( getSupportLink() );
    }

    public String launchSMVersionLink() {
        getElement( smVersionInfo ).click();
        return getElement( smVersionTitle ).getText();
    }

    public String getStudentHomePageTitle() {
        return driver.getTitle();
    }

    public String getTeacherHomePageUrl() {
        return driver.getCurrentUrl();
    }

    public void logoutStudent() {
        getStudentLogoutButton().click();
    }

    public void logoutTeacher() {
        getTeacherLogoutButton().click();
    }

    public void logoutAdmin() {
        getAdminLogoutButton().click();
    }

    private Map<String, String> getLaunchPageDetails( WebElement element ) {
        element.click();
        CommonHelper.nap();
        CommonHelper.nap();
        return getNewPageDetails();
    }

    public WebElement getAdminHomeTitle() {
        return getElement( pearsonTitle );
    }

    public boolean login( String userName, String password ) {
        if ( !isLoginPageLaunched ) {
            launchLoginPage();
            isLoginPageLaunched = true;
        }
        log.info( "Username to login ---------------->" + userName );
        getUserName().sendKeys( userName );
        getPassword().sendKeys( password );
        getSignInButton().click();
        CommonHelper.nap();
        CommonHelper.nap();
        //validLoginConfirmLogin();
        return true;
    }

    public boolean validLoginConfirmLogin() {
        if ( getElement( signInAlertYes ) != null ) {
            getElement( signInAlertYes ).click();
            CommonHelper.nap();
        }
        return true;
    }

    public void preLogin( String username, String password ) {
        login( username, password );
        AdminWebDriver.getInstance( "" ).releaseDriver();
        isLoginPageLaunched = false;
    }

    public void closeReloginAlertConfirm() {
        reloginOption( getElement( signInAlertClose ) );
        CommonHelper.nap();
    }

    public void clickReloginAlertNoOption() {
        reloginOption( getElement( signInAlertNo ) );
        CommonHelper.nap();
    }

    public void clickReloginAlertYesOption() {
        reloginOption( getElement( signInAlertYes ) );
        CommonHelper.nap();
    }

    protected void reloginOption( WebElement element ) {
        CommonHelper.nap();
        element.click();
        CommonHelper.nap();
    }
}
