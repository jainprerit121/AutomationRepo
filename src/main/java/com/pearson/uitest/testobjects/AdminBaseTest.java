package com.pearson.uitest.testobjects;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pearson.uitest.database.OrganizationSqlHelper;
import com.pearson.uitest.database.UserSqlHelper;
import com.pearson.uitest.pageobjects.AdminBasePage;

/**
 * Tests for common elements of the home page a across the users
 * 
 * @author vgaddra
 * 
 */
@Listeners ( com.pearson.uitest.testutils.FixRetryListener.class )
public abstract class AdminBaseTest extends BaseTest {
    protected static final String ATTRIBUTE_VALUE_NAME = "value";
    protected static final String NG_SRC_ATTRIBUTE_NAME = "ng-src";
    protected static final String ADMIN_PAGE_TITLE = "AdminPageTitle";
    protected static final String ORG_PAGE_URL = "OrgPageUrl";

    protected AdminBasePage page;
    protected OrganizationSqlHelper orgHelper;
    protected UserSqlHelper userHelper;

    /**
     * @BeforeTest ( alwaysRun = true ) public void setRetryAnalyser(
     *             ITestContext context ) { for ( ITestNGMethod method :
     *             context.getAllTestMethods() ) { //method.setRetryAnalyzer(
     *             new RetryRule() ); } }
     **/

    protected void initialize( String browser ) {
        super.initialize( browser );
        orgHelper = new OrganizationSqlHelper();
        userHelper = new UserSqlHelper();
    }

    public AdminBaseTest() {}

    @Test ( priority = 1 )
    public void checkPearsonImage() {
        Assert.assertNotNull( page.getPearsonImg(), appProperties.getProperty( "PearsonImgMissingError" ) );
    }

    @Test ( priority = 2 )
    public void checkHomeTabMenuButtonHighlighted() {
        page.getHomeTabBtn().click();
        Assert.assertEquals( page.getHomeTabBtn().getAttribute( "class" ), "ng-binding active" );
    }

    @Test ( priority = 3 )
    public void checkPearsonTitle() {
        Assert.assertEquals( page.getPearsonTitle().getText(), appProperties.getProperty( "HomeLeftTitle" ) );
    }

    @Test ( priority = 4 )
    public void checkHomeTabMenuButtonPresent() {
        Assert.assertNotNull( page.getHomeTabBtn(), "Home Tab button is not present." );
    }

    @Test ( priority = 5 )
    public void checkHomeTabMenuButtonText() {
        Assert.assertEquals( page.getHomeTabBtn().getText(), appProperties.getProperty( "HomeBtn" ) );
    }

    @Test ( priority = 6 )
    public void checkUsersTabMenuButtonPresent() {
        Assert.assertNotNull( page.getUsersTabBtn(), "Users Tab button is not present." );
    }

    @Test ( priority = 7 )
    public void checkUsersTabMenuButtonText() {
        Assert.assertEquals( page.getUsersTabBtn().getText(), appProperties.getProperty( "HomeUsersBtn" ) );
    }

    @Test ( priority = 8 )
    public void checkAnnouncementButtonPresent() {
        Assert.assertNotNull( page.getAnnouncementBtn(), "Announcements button is not present." );
    }

    @Test ( priority = 9 )
    public void checkAnnouncementButtonText() {
        Assert.assertEquals( page.getAnnouncementBtn().getText(), appProperties.getProperty( "HomeAnnounceBtn" ) );
    }

    @Test ( priority = 10 )
    public void checkPrintButtonPresent() {
        Assert.assertNotNull( page.getPrintBtn(), "Print button is not present." );
    }

    @Test ( priority = 11 )
    public void checkPrintButtonText() {
        Assert.assertEquals( page.getPrintBtn().getText(), appProperties.getProperty( "HomePrintBtn" ) );
    }

    @Test ( priority = 12 )
    public void checkLogoutButtonPresent() {
        Assert.assertNotNull( page.getLogoutBtn(), "Logout button is not present." );
    }

    @Test ( priority = 13 )
    public void checkLogoutButtonText() {
        Assert.assertEquals( page.getLogoutBtn().getText(), appProperties.getProperty( "HomeLogoutBtn" ) );
    }

    @Test ( priority = 14 )
    public void checkAnnouncementsPageLaunched() {
        Map<String, String> actualPageDetails = page.launchAnnouncements();
        checkPageDetails( actualPageDetails, appProperties.getProperty( "HomeAnnouncementTitle" ), appProperties.getProperty( "AnnouncementUrl" ) );
    }

    @Test ( priority = 15 )
    public void checkHomeTabMenuButtonClick() {
        getHomePageDetails();
    }

    protected void getHomePageDetails() {
        Map<String, String> actualPageDetails = page.clickHomeTabBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( ADMIN_PAGE_TITLE ), appProperties.getProperty( "HomePageUrl" ) );
    }

    @Test ( priority = 16 )
    public void checkUserTabMenuButtonClick() {
        Map<String, String> actualPageDetails = page.clickUsrTabBtn();
        checkPageDetails( actualPageDetails, appProperties.getProperty( ADMIN_PAGE_TITLE ), appProperties.getProperty( "UsersPageUrl" ) );
    }

    protected void assertBasedOnBrowser( String value, String key ) {
        if ( browser.contains( "Safari" ) ) {
            Assert.assertTrue( "".equals( value ) );
        } else {
            Assert.assertTrue( value.equals( appProperties.getProperty( key ) ) );
        }
    }

    @BeforeMethod
    public void logBefore( Method method ) {
        log.info( "Started Test: " + method.getName() );
    }

    @AfterMethod
    public void logAfter( Method method ) {
        log.info( "Executed Test: " + method.getName() + "\n" );
    }

}
