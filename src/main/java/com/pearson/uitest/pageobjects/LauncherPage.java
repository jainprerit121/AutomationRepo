package com.pearson.uitest.pageobjects;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.pearson.uitest.helper.CommonHelper;

/**
 * This class defines the page objects of the SuccessMaker app launcher page
 * 
 * @author vgaddra
 *
 */
public class LauncherPage extends BasePage {

    private By launcherCopyright = By.xpath( "/html/body/div/div[5]" );
    private By launcherLogo = By.xpath( "/html/body/div/div[2]/img" );
    private By launcherLearningImg = By.xpath( "//*[@id='sm-header-right']/img" );
    private By launcherPearsonImg = By.xpath( "//*[@id='sm-header-left']/img" );
    private By launchSignInBtn = By.id( "launchButton" );

    public LauncherPage( WebDriver driver ) {
        super( driver );
        log = Logger.getLogger( LauncherPage.class );
    }

    public String getLauncherPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getLaunchPageTitle() {
        return driver.getTitle();
    }

    // Get SuccessMaker logo
    public WebElement getSMLogo() {
        return getElement( launcherLogo );
    }

    // Get Always Learning Iage
    public WebElement getAlwaysLearningImg() {
        return getElement( launcherLearningImg );
    }

    // Get Pearson Image
    public WebElement getPearsonImg() {
        return getElement( launcherPearsonImg );
    }

    // Get Click to Sign In button
    public WebElement getLauncherSignInBtn() {
        return getElement( launchSignInBtn );
    }

    // Get copyright information
    public String getCopyrightTextLauncher() {
        return getElement( launcherCopyright ).getText();
    }

    public String launchLoginPage() {
        if ( !isLoginPageLaunched ) {
            getLauncherSignInBtn().click();
            CommonHelper.nap();
            CommonHelper.nap();
            Set<String> handles = driver.getWindowHandles();
            driver.switchTo().window( driver.getWindowHandles().iterator().next() );
            driver.switchTo().defaultContent();
            String winHandle = driver.getWindowHandle();
            log.info( "Number of windows " + handles.size() );
            if ( handles.size() > 1 ) {
                driver.close();
            }
            for ( String index : handles ) {
                if ( !index.equals( winHandle ) ) {
                    driver.switchTo().window( index );
                    break;
                }
            }
            ( (JavascriptExecutor) driver ).executeScript( "if(window.screen){window.moveTo(0, 0); window.resizeTo(window.screen.availWidth, window.screen.availHeight);};" );
            isLoginPageLaunched = true;
        }
        return driver.getCurrentUrl();
    }

    public String getLoginPageTitle() {
        return driver.getTitle();
    }
}
