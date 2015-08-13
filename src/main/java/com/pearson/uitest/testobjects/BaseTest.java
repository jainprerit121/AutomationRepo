package com.pearson.uitest.testobjects;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.pearson.uitest.driver.AdminWebDriver;
import com.pearson.uitest.pageobjects.LoginPage;
import com.pearson.uitest.props.ApplicationProperties;

/**
 * BaseTest to initialize the browser and loads the properties
 * 
 * @author vgaddra
 *
 */
public abstract class BaseTest {

    protected Logger log;
    protected WebDriver driver;
    protected String browser;
    protected LoginPage loginPage;
    protected ApplicationProperties appProperties;

    protected void initialize( String browserInput ) {
        this.browser = browserInput;
        if ( System.getProperty( "BROWSER" ) != null ) {
            browser = System.getProperty( "BROWSER" );
        }
        driver = AdminWebDriver.getInstance( browser ).getDriver();
        loginPage = new LoginPage( driver );
    }

    public BaseTest() {
        appProperties = ApplicationProperties.getInstance();
    }

    protected void checkPageDetails( Map<String, String> pageDetails, String pageTitle, String pageUrl ) {
        log.info( "Expected page title: " + pageTitle );
        log.info( "Actual page title: " + pageDetails.get( "PAGE_TITLE" ) );
        log.info( "Expected page URL: " + pageUrl );
        log.info( "Actual page URL: " + pageDetails.get( "PAGE_URL" ) );

        Assert.assertTrue( pageDetails.get( "PAGE_TITLE" ).equals( pageTitle ) );
        Assert.assertTrue( pageDetails.get( "PAGE_URL" ).equals( pageUrl ) );
    }
}
