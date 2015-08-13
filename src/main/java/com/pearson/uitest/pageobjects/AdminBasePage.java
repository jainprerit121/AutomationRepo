package com.pearson.uitest.pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pearson.uitest.helper.CommonHelper;

/**
 * This defines the page objects of the home page elements which is common for
 * all type of logins.
 * 
 * @author vgaddra
 * 
 */
public abstract class AdminBasePage extends BasePage {

    private By pearsonTitle = By.id( "appTitle" );
    private By homeTabBtn = By.linkText( "Home" );
    private By licenseTabBtn = By.linkText( "Licenses" );
    private By orgTabBtn = By.linkText( "Organizations" );
    private By usersTabBtn = By.linkText( "Users" );
    private By announcementBtn = By.linkText( "Announcements" );
    private By printBtn = By.linkText( "Print" );
    private By helpBtn = By.linkText( "Help" );
    private By logoutBtn = By.id( "logoutBtn" );
    private By homePearsonImg = By.id( "appLogo" );    
    private By alertPopUp = By.id("confirmBox ngModal");
    private By alertPopUpText = By.id("popUpMsg");
    private By alertPopUpOKBtn = By.xpath("//*[@id='popUpFooter']/button[@id='okBtn']");

    public AdminBasePage( WebDriver driver ) {
        super( driver );
    }

    public WebElement getPearsonTitle() {
        return getElement( pearsonTitle );
    }

    public WebElement getHomeTabBtn() {
        return getElement( homeTabBtn );
    }

    public WebElement getLicenseTabBtn() {
        return getElement( licenseTabBtn );
    }

    public WebElement getOrgTabBtn() {
        return getElement( orgTabBtn );
    }

    public WebElement getUsersTabBtn() {
        return getElement( usersTabBtn );
    }

    public WebElement getAnnouncementBtn() {
        return getElement( announcementBtn );
    }

    public WebElement getPrintBtn() {
        return getElement( printBtn );
    }

    public WebElement getHelpBtn() {
        return getElement( helpBtn );
    }
    
    public WebElement getLogoutBtn() {
        return getElement( logoutBtn );
    }

    public WebElement getPearsonImg() {
        return getElement( homePearsonImg );
    }

    public Map<String, String> launchAnnouncements() {
        getAnnouncementBtn().click();
        CommonHelper.nap();
        return getNewPageDetails();
    }

    public Map<String, String> launchHelp() {
        getHelpBtn().click();
        CommonHelper.nap();
        return getNewPageDetails();
    }
    
    

    public Map<String, String> clickHomeTabBtn() {
        return getPageDetailsAfterClick( getHomeTabBtn() );
    }

    public Map<String, String> clickOrgTabBtn() {
        return getPageDetailsAfterClick( getOrgTabBtn() );
    }

    public Map<String, String> clickLicTabBtn() {
        return getPageDetailsAfterClick( getLicenseTabBtn() );
    }

    public Map<String, String> clickUsrTabBtn() {
        return getPageDetailsAfterClick( getUsersTabBtn() );
    }

    public Map<String, String> logoutAdminPanel( String browser ) {
        getElement( logoutBtn ).click();
        CommonHelper.nap();
        Map<String, String> pageDetails = getPageDetails();
        releaseDriverForIE( browser );
        return pageDetails;
    }

    public String switchToAlert() {
        CommonHelper.nap();
        CommonHelper.nap();
        Boolean isAlertBoxPresent = getElement(alertPopUp).isDisplayed();            
        if ( isAlertBoxPresent ) {
        	log.warn( "Alert Popup Displayed!" );
        	String alertText = getElement(alertPopUpText).getText();
            getElement(alertPopUpOKBtn).click();
            return alertText;
        }
        else
        {
        	log.warn( "Alert Popup NOT Displayed!" );
        	return "";
        }         
    }

    protected String clickOnButton( WebElement element ) {
        element.click();
        return switchToAlert();
    }

    protected List<String> getTableData( By webTable ) {
        List<WebElement> webTableRows = driver.findElements( webTable );
        List<String> webRowListData = new ArrayList<String>();
        java.util.Iterator<WebElement> i = webTableRows.iterator();
        while ( i.hasNext() ) {
            WebElement webTabRow = i.next();
            if ( !"".equals( webTabRow.getText() ) ) {
                webRowListData.add( webTabRow.getText() );
            }
        }
        return webRowListData;
    }

    protected String selectDropdownValue( WebElement element, String value ) {
        Select dropdown = new Select( element );
        dropdown.selectByVisibleText( value );
        return dropdown.getFirstSelectedOption().getText();
    }

}
