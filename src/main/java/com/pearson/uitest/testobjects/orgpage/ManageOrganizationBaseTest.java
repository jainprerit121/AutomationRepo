package com.pearson.uitest.testobjects.orgpage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.uitest.constants.AdminUIConstants;
import com.pearson.uitest.constants.OrganizationConstants;
import com.pearson.uitest.enums.DeploymentTypes;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.ManageOrganizationPage;
import com.pearson.uitest.pageobjects.OrganizationPage;
import com.pearson.uitest.testobjects.AdminBaseTest;

/**
 * 
 * @author vgaddra
 *
 */
public abstract class ManageOrganizationBaseTest extends AdminBaseTest {

    protected static String currentUserLogin;
    protected static boolean editTest;

    protected void initialize( String browser ) {
        super.initialize( browser );
        page = new ManageOrganizationPage( driver );
    }

    public ManageOrganizationBaseTest() {
        page = new ManageOrganizationPage( driver );
    }

    @Test ( priority = 102 )
    public void checkOrganizationsTabBtnHighlighted() {
        page.getOrgTabBtn().click();
        if ( !editTest ) {
            ( (OrganizationPage) page ).getOrgLandAddOrgBtn().click();
        } else {
            ( (OrganizationPage) page ).getOrganizationLink( AdminUIConstants.NEW_SCHOOL_NAME ).click();
        }
        Assert.assertEquals( page.getOrgTabBtn().getAttribute( "class" ), "ng-binding active" );
        log.info( "Verified that Organization tab is highlighted!" );
    }

    @Test ( priority = 103 )
    public void checkRequiredFieldText() {
        Assert.assertTrue( ( (ManageOrganizationPage) page ).getRequiredField().getText().contains( "Required field" ) );
    }

    @Test ( priority = 104 )
    public void checkRequiredFieldStarSymbol() {
        Assert.assertEquals( ( (ManageOrganizationPage) page ).getRequiredFieldStar().getText(), appProperties.getProperty( "MandatorySymbol" ) );
    }

    @Test ( priority = 105 )
    public void checkOrganizationNameLabel() {
        Assert.assertTrue( ( (ManageOrganizationPage) page ).getOrganizationNameLabel().getText().contains( appProperties.getProperty( "OrgNameLabel" ) ) );
    }

    @Test ( priority = 106 )
    public void checkOrganizationNameMandatorySymbol() {
        Assert.assertEquals( ( (ManageOrganizationPage) page ).getOrganizationNameStar().getText(), appProperties.getProperty( "MandatorySymbol" ) );
    }

    @Test ( priority = 107 )
    public void checkOrganizationNameInput() {
        Assert.assertNotNull( ( (ManageOrganizationPage) page ).getOrganizationNameInput(), "Organization Name Input text box is missing" );
    }

    @Test ( priority = 108 )
    public void checkOrganizationIdLabel() {
        log.info( "Actual Label: " + ( (ManageOrganizationPage) page ).getOrganizationIdLabel().getText() );
        log.info( "Expected Label: " + appProperties.getProperty( "OrgIdLabel" ) );
        Assert.assertEquals( ( (ManageOrganizationPage) page ).getOrganizationIdLabel().getText(), appProperties.getProperty( "OrgIdLabel" ) );
    }

    @Test ( priority = 109 )
    public void checkOrganizationIdMandatorySymbol() {
        Assert.assertEquals( ( (ManageOrganizationPage) page ).getOrganizationIdStar().getText(), appProperties.getProperty( "MandatorySymbol" ) );
    }

    @Test ( priority = 110 )
    public void checkOrganizationIDInput() {
        Assert.assertNotNull( ( (ManageOrganizationPage) page ).getOrganizationIdInput(), "Organization ID Input text box is missing" );
    }

    @Test ( priority = 111 )
    public void checkOrganizationIDNote() {
        Assert.assertNotNull( ( (ManageOrganizationPage) page ).getOrganizationIdNote(), "Note text is missing" );
    }

    @Test ( priority = 112 )
    public void checkOrganizationIDNoteText() {
        Assert.assertTrue( ( (ManageOrganizationPage) page ).getOrganizationIdNote().getText().contains( appProperties.getProperty( "OrgIdNote" ) ) );
    }

    @Test ( priority = 113 )
    public void checkOrganizationDeploymentTypeLabel() {
        Assert.assertEquals( ( (ManageOrganizationPage) page ).getOrganizationTypeLabel().getText(), appProperties.getProperty( "OrgDepTypeLabel" ) );
    }

    @Test ( priority = 114 )
    public void checkOrganizationDeploymentDropDown() {
        Assert.assertNotNull( ( (ManageOrganizationPage) page ).getOrganizationTypeDropdown(), "Organization deployment dropdown is missing" );
    }

    @Test ( priority = 115 )
    public void checkOrganizationDistrictLabel() {
        Assert.assertEquals( ( (ManageOrganizationPage) page ).getOrganizationDistrictLabel().getText(), appProperties.getProperty( "OrgDistLabel" ) );
    }

    @Test ( priority = 116 )
    public void checkOrganizationDistrictMandatorySymbol() {
        Assert.assertEquals( ( (ManageOrganizationPage) page ).getOrganizationDistrictStar().getText(), appProperties.getProperty( "MandatorySymbol" ) );
    }

    @Test ( priority = 117 )
    public void checkOrganizationDistrictDropDown() {
        Assert.assertNotNull( ( (ManageOrganizationPage) page ).getOrganizationDistrictDropdown(), "Organization distric dropdown is missing" );
    }

    @Test ( priority = 118 )
    public void checkOrganizationSaveButtton() {
        Assert.assertNotNull( ( (ManageOrganizationPage) page ).getOrganizationSaveButton(), "Save Button is not present" );
    }

    @Test ( priority = 119 )
    public void checkOrganizationSaveButttonText() {
        Assert.assertEquals( ( (ManageOrganizationPage) page ).getOrganizationSaveButton().getText(), appProperties.getProperty( "OrgSaveBtn" ) );
    }

    @Test ( priority = 120 )
    public void checkOrganizationCancelButtton() {
        Assert.assertNotNull( ( (ManageOrganizationPage) page ).getOrganizationCancelButton(), "Cancel Button is not present" );
    }

    @Test ( priority = 121 )
    public void checkOrganizationDitrictDropDownFound() {
        List<String> actualDistDropdown = ( (ManageOrganizationPage) page ).getOrganizationDistrictDropdownValues();
        Assert.assertTrue( actualDistDropdown.size() > 0 );
    }

    @Test ( priority = 123 )
    public void checkOrganizationDitrictDropDown() {
        List<String> actualDistDropdown = ( (ManageOrganizationPage) page ).getOrganizationDistrictDropdownValues();
        if ( "-- Select a District --".equals( actualDistDropdown.get( 0 ) ) ) {
            actualDistDropdown.remove( 0 );
        }

        List<String> expectedList = orgHelper.getAllDistrictsList();
        Assert.assertEquals( actualDistDropdown, expectedList );
    }

    @Test ( priority = 124 )
    public void checkAddOrgSaveWithAllRequiredFieldsEmpty() {
        String retAlert = addOrganization( "", "", 0, 0 );
        verifyAddOrganizationScenario( retAlert, "OrgAllFieldsEmpty" );
    }

    @Test ( priority = 125 )
    public void checkSavingOrganizationWithOrganizationNameOnly() {
        String retAlert = addOrganization( "My Organiztion", "", 0, 0 );
        verifyAddOrganizationScenario( retAlert, "OrgIdDistBlank" );
    }

    @Test ( priority = 126 )
    public void checkSavingOrganizationWithNODistrict() {
        String retAlert = addOrganization( "My Organiztion", "MyID", 0, 0 );
        verifyAddOrganizationScenario( retAlert, "OrgDistBlank" );
    }

    @Test ( priority = 127 )
    public void checkSavingOrganizationWithOrganizationIDOnly() {
        String retAlert = addOrganization( "", "MyID", 0, 0 );
        verifyAddOrganizationScenario( retAlert, "OrgNameDistBlank" );
    }

    @Test ( priority = 128 )
    public void checkSavingOrganizationWithOnlyDistrictSelection() {
        String retAlert = addOrganization( "", "", 0, 1 );
        verifyAddOrganizationScenario( retAlert, "OrgNameIdBlank" );
    }

    @Test ( priority = 130 )
    public void checkSavingOrganizationWithNOOrganizationName() {
        String retAlert = addOrganization( "", "MyID", 0, 1 );
        verifyAddOrganizationScenario( retAlert, "OrgNameBlank" );
    }

    @Test ( priority = 131 )
    public void checkSavingOrganizationWithNOOrganizationID() {
        String retAlert = addOrganization( "MyOrganization", "", 0, 1 );
        verifyAddOrganizationScenario( retAlert, "OrgIdBlank" );
    }

    @Test ( priority = 132 )
    public void checkSavingOrganizationWithMorethan75charactersofName() {
        String retAlert = addOrganization( appProperties.getProperty( "OrgNameLimit" ), "MyID", 0, 1 );
        verifyAddOrganizationScenario( retAlert, "OrgNameLimitExceeds" );
    }

    @Test ( priority = 133 )
    public void checkSavingOrganizationWithMorethan7charactersofID() {
        String retAlert = addOrganization( "MyOrganization", appProperties.getProperty( "OrgIdLimitExceeds" ), 0, 1 );
        verifyAddOrganizationScenario( retAlert, "OrgIdLimitExceeds" );
    }

    @Test ( priority = 134 )
    public void checkPresenceofDistrictDropdownWhenSelectSchoolDeploymentType() {
        ( (ManageOrganizationPage) page ).selectDeploymentDropdownOption( "School" );
        Assert.assertNotNull( ( (ManageOrganizationPage) page ).getOrganizationDistrictDropdown(), "District Dropdown not present" );
    }

    @Test ( priority = 135 )
    public void checkOrganizationDeploymentDefaultSelection() {
        String actualSelection = ( (ManageOrganizationPage) page ).getOrganizationDeploymentDefaultSelection();
        Assert.assertEquals( actualSelection, DeploymentTypes.SCHOOL.getName() );
    }

    protected String addOrganization( String orgNameToAdd, String orgIdToAdd, int selDepIndex, int selDistIndex ) {
        ( (ManageOrganizationPage) page ).getOrganizationNameInput().clear();
        ( (ManageOrganizationPage) page ).getOrganizationNameInput().sendKeys( orgNameToAdd );
        ( (ManageOrganizationPage) page ).getOrganizationIdInput().clear();
        ( (ManageOrganizationPage) page ).getOrganizationIdInput().sendKeys( orgIdToAdd );

        log.info( "Entered Org Name: " + orgNameToAdd );
        log.info( "Entered Org ID: " + orgIdToAdd );

        Select selDepType = new Select( ( (ManageOrganizationPage) page ).getOrganizationTypeDropdown() );
        selDepType.selectByIndex( selDepIndex );

        if ( selDistIndex != -1 ) {
            Select selDistrictType = new Select( ( (ManageOrganizationPage) page ).getOrganizationDistrictDropdown() );
            selDistrictType.selectByIndex( selDistIndex );
        }

        return ( (ManageOrganizationPage) page ).clickSaveButton();
    }

    protected void verifyAddOrganizationScenario( String retAlert, String verifyProperty ) {
        log.info( "Alert Text: " + retAlert.trim().replaceAll( "\n", " " ) );
        log.info( "Expected  :" + appProperties.getProperty( verifyProperty ) );
        Assert.assertTrue( retAlert.trim().replaceAll( "\n", " " ).equals( appProperties.getProperty( verifyProperty ) ) );
        if ( !editTest ) {
            Assert.assertTrue( driver.getCurrentUrl().equals( appProperties.getProperty( "AddOrgUrl" ) ) );
        } else {
            Assert.assertTrue( driver.getCurrentUrl().equals( appProperties.getProperty( "EditOrgDetailsUrl" ) ) );
        }

        log.info( "Verified the alert and add org page URL is the current page!" );
    }

    public boolean verifyEditExitScenarios( String orgName, String orgID, String methodName, String confirmMethod, String pageURL ) {
        try {
            CommonHelper.nap();
            return verifyExitScenario( orgName, orgID, methodName, confirmMethod, pageURL );
        } catch ( Exception e ) {
            return false;
        }
    }

    public boolean verifyExitScenario( String orgName, String orgID, String methodNameInput, String confirmMethod, String pageURL ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    	CommonHelper.nap();
    	( (ManageOrganizationPage) page ).getOrganizationNameInput().clear();
        ( (ManageOrganizationPage) page ).getOrganizationIdInput().clear();
        ( (ManageOrganizationPage) page ).getOrganizationNameInput().sendKeys( orgName );
        ( (ManageOrganizationPage) page ).getOrganizationIdInput().sendKeys( orgID );

        log.info( "Entered Org Name: " + orgName );
        log.info( "Entered Org ID: " + orgID );
        String methodName = methodNameInput;
        //Do the Operation
        WebElement returnElement;
        returnElement = (WebElement) invokeOperationMethod( methodName );
        returnElement.click();
        //CommonHelper.nap();

        //Choose Confirmation dialog option
        returnElement = (WebElement) invokeOperationMethod( confirmMethod );
        returnElement.click();
        //CommonHelper.nap();

        //Alert
        if ( OrganizationConstants.GET_CONFIRM_YES_BUTTON.equals( confirmMethod ) ) {
        	CommonHelper.nap();
        	CommonHelper.nap();
            String alertText = page.switchToAlert();
            log.info( "Alert Text: " + alertText );
            CommonHelper.nap();
        }

        //Check corresponding tab highlighted
        if ( OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON.equals( confirmMethod ) || OrganizationConstants.GET_EDIT_ORG_CANCEL_BUTTON.equals( methodName ) || OrganizationConstants.GET_DATA_SETTINGS_TAB_LINK.equals( methodName )
                || OrganizationConstants.GET_LICENSE_SETTINGS_LINK.equals( methodName ) ) {
            methodName = OrganizationConstants.GET_ORG_TAB_BUTTON;
        }
        if ( !OrganizationConstants.GET_LOGOUT_BUTTON.equals( methodName ) ) {
            returnElement = (WebElement) invokeOperationMethod( methodName );
            Assert.assertTrue( returnElement.getAttribute( "class" ).toLowerCase().contains( "active" ), "The expected tab is not active" );
            log.info( "The expected tab is active!" );
        }

        //Verify the url of the landing page after confirmation
        Assert.assertEquals( driver.getCurrentUrl(), pageURL, "Did not navigate to the expected page - " + pageURL );
        log.info( "Navigated to the expected page - " + pageURL );

        //Login if logged out
        if ( OrganizationConstants.GET_LOGOUT_BUTTON.equals( methodName ) && !OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON.equals( confirmMethod ) ) {
            loginPage.login( currentUserLogin, AdminUIConstants.DEFAULT_PASSWORD );
            CommonHelper.nap();
        }

        //Back to the Organization Landing page    	
        //CommonHelper.nap();
        if ( OrganizationConstants.GET_LICENSE_TAB_BUTTON.equals( methodName ) ) {
            CommonHelper.nap();
            CommonHelper.nap();
            CommonHelper.nap();
        }
        page.getOrgTabBtn().click();
        if ( OrganizationConstants.GET_CONFIRM_CANCEL_BUTTON.equals( confirmMethod ) ) {
            ( (ManageOrganizationPage) page ).getConfirmationCancelButton().click();
        }

        return true;
    }

    public Object invokeOperationMethod( String methodName ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.info( "Invoking Method: " + methodName );
        Method m = ManageOrganizationPage.class.getMethod( methodName, new Class[] {} );
        return m.invoke( (ManageOrganizationPage) page, new Object[] {} );
    }

}
