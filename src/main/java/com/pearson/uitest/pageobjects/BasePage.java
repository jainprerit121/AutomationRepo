package com.pearson.uitest.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.log4testng.Logger;

import com.pearson.uitest.driver.AdminWebDriver;
import com.pearson.uitest.helper.CommonHelper;

/**
 * This class is not specific to any page and can be accessed by all other pages
 * It contains generic methods which can be played around on the web elements
 * 
 * @author vgaddra
 * 
 */
public abstract class BasePage {
    protected Logger log;
    protected static boolean isLoginPageLaunched;
    protected static final int DELAY = 1000;
    protected WebDriver driver;

    public BasePage( WebDriver driver ) {
        this.driver = driver;
    }

    protected WebElement getElement( By elementName ) {
        WebElement element = null;
        try {
            element = driver.findElement( elementName );
            log.info( elementName.toString() + " is present " );
        } catch ( NoSuchElementException e ) {
            log.warn( elementName.toString() + " is not present " );
        }
        return element;
    }

    protected List<WebElement> getElements( By elementName ) {
        List<WebElement> elements = null;
        elements = driver.findElements( elementName );
        if(elements.size()>0){
            log.info( elementName.toString() + " is present " );
        } else {
            log.warn( elementName.toString() + " is not present " );
        }
        return elements;
    }

    public String getElementAttribute( WebElement element, String attributeName ) {
        return element.getAttribute( attributeName );
    }

    protected Map<String, String> getPageDetailsAfterClick( WebElement button ) {
        button.click();
        CommonHelper.nap();
        return getPageDetails();
    }

    protected Map<String, String> getPageDetails() {
        Map<String, String> pageDetailsMap = new HashMap<String, String>();
        CommonHelper.nap();
        pageDetailsMap.put( "PAGE_TITLE", driver.getTitle() );
        pageDetailsMap.put( "PAGE_URL", driver.getCurrentUrl() );
        return pageDetailsMap;

    }

    protected Map<String, String> getNewPageDetails() {
        Map<String, String> pageDetailsMap = new HashMap<String, String>();
        driver.switchTo().defaultContent();
        String winHandle = driver.getWindowHandle();
        for ( String index : driver.getWindowHandles() ) {
            if ( !index.equals( winHandle ) ) {
                driver.switchTo().window( index );
                try {
                    pageDetailsMap = getPageDetails();
                    driver.close();
                } catch ( TimeoutException e ) {
                    //Close the newly opened window if Timeout exception occurs to prevent the failure of subsequent test cases.
                    log.warn( "Unable to load new page on click of a button " + e.getMessage() );
                    driver.close();
                }
                break;
            }
        }
        driver.switchTo().window( winHandle );
        ( (JavascriptExecutor) driver ).executeScript( "if(window.screen){window.moveTo(0, 0); window.resizeTo(window.screen.availWidth, window.screen.availHeight);};" );
        return pageDetailsMap;
    }

    protected Map<String, String> getNewPageDetails( WebElement element ) {
        element.click();
        CommonHelper.nap();
        return getNewPageDetails();
    }

    protected List<String> getDropDownValues( WebElement dropDown ) {
        List<String> dropDownValues = new ArrayList<String>();
        Select prodDrop = new Select( dropDown );
        for ( WebElement element : prodDrop.getOptions() ) {
            if ( !element.getAttribute( "class" ).toLowerCase().contains( "hide" ) ) {
                dropDownValues.add( element.getText() );
            }
        }
        return dropDownValues;
    }

    public String getDefaultSelectedOption( WebElement element ) {
        return new Select( element ).getFirstSelectedOption().getText();
    }

    public void selectItemFromDropDown( WebElement element, String selValue ) {
        Select dropDown = new Select( element );
        dropDown.selectByVisibleText( selValue );
    }

    /**
     * This is work around for IE browser to delete cookies. Otherwise previous
     * login is retained and next login is used same cookie and showing
     * incorrect homepage of admin.
     * 
     * @param browser
     */
    public void releaseDriverForIE( String browser ) {
        if ( isLoginPageLaunched && ( "IE-Win7".equals( browser ) || "IE-Win8".equals( browser ) ) ) {
            AdminWebDriver.getInstance( browser ).releaseDriver();
            isLoginPageLaunched = false;
        }
    }
}
