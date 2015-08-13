package com.pearson.uitest.pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.log4testng.Logger;

import com.pearson.uitest.helper.CommonHelper;

/**
 * 
 * This class captures all data elements for both Add and Edit organization.
 * pages Since all the fields are common, added all elements in this class.
 *
 */
public class ManageOrganizationPage extends OrganizationPage {

    private By orgNameLabel = By.id( "orgNameLabel" );
    private By orgNameStar = By.id( "orgNameSpan" );
    private By orgNameInput = By.id( "orgNameInput" );

    private By orgIdLabel = By.id( "orgIdLabel" );
    private By orgIdStar = By.id( "orgIdSpan" );
    private By orgIdInput = By.id( "orgIdInput" );

    private By orgIdNote = By.id( "orgIdNote" );

    private By orgTypeLabel = By.id( "orgTypeLabel" );
    private By orgDepTypeDrop = By.id( "orgTypeSelect" );

    private By orgDistLabel = By.id( "orgDistrictLabel" );
    private By orgDistLabelStar = By.id( "orgDistrictSpan" );
    private By orgDistDrop = By.id( "orgDistrictSelect" );

    private By requireFieldStar = By.id( "orgMandatory" );
    private By requireField = By.id( "requireFieldDiv" );
    
    private By detailsTab = By.id( "editTabs1" );
    private By licensesTab = By.id( "editTabs2" );
    private By dataSettingsTab = By.id( "editTabs3" );
    private By detailsTabLink = By.xpath( "//*[@id='editTabs1']/a" );
    private By licenseSettingsLink = By.id( "editTabs2Link" );
    private By dataSettingsTabLink = By.id( "editTabs3Link" );
    
    private By orgSaveBtn = By.id( "orgSave" );
    private By cancelBtn = By.id( "orgCancel" );
    
    private By confirmationYesButton = By.id( "confirmBtnYes" );
    private By confirmationNoButton = By.id( "confirmBtnNo" );
    private By confirmationCancelButton = By.id( "confirmBtnCancel" );

    public ManageOrganizationPage( WebDriver driver ) {
        super( driver );
        log = Logger.getLogger( ManageOrganizationPage.class );
    }

    public WebElement getDetailsTab() {
    	return getElement(detailsTab);
    }
    
    public WebElement getLicenseSettingLink() {
    	return getElement(licenseSettingsLink);
    }
 
    public WebElement getDetailsTabLink() {
    	return getElement(detailsTabLink);
    }
    
    public WebElement getConfirmationYesButton() {
    	return getElement(confirmationYesButton);
    }

    public WebElement getConfirmationNoButton() {
    	return getElement(confirmationNoButton);
    }
    
    public WebElement getConfirmationCancelButton() {
    	return getElement(confirmationCancelButton);
    }
    
    public WebElement getLicensesTab() {
    	return getElement(licensesTab);
    }
    
    public WebElement getDataSettingsTab() {
    	return getElement(dataSettingsTab);
    }
    
    public WebElement getEditOrgCancelButton() {
    	return getElement(cancelBtn);
    }

    public WebElement getOrganizationNameLabel() {
        return getElement( orgNameLabel );
    }

    public WebElement getOrganizationNameStar() {
        return getElement( orgNameStar );
    }

    public WebElement getOrganizationNameInput() {
        return getElement( orgNameInput );
    }

    public WebElement getOrganizationIdLabel() {
        return getElement( orgIdLabel );
    }

    public WebElement getOrganizationIdStar() {
        return getElement( orgIdStar );
    }

    public WebElement getOrganizationIdInput() {
        return getElement( orgIdInput );
    }

    public WebElement getOrganizationIdNote() {
        return getElement( orgIdNote );
    }

    public WebElement getOrganizationTypeLabel() {
        return getElement( orgTypeLabel );
    }

    public WebElement getOrganizationTypeDropdown() {
        return getElement( orgDepTypeDrop );
    }

    public WebElement getOrganizationDistrictLabel() {
        return getElement( orgDistLabel );
    }

    public WebElement getOrganizationDistrictStar() {
        return getElement( orgDistLabelStar );
    }

    public WebElement getOrganizationDistrictDropdown() {
        return getElement( orgDistDrop );
    }

    public WebElement getRequiredFieldStar() {
        return getElement( requireFieldStar );
    }

    public WebElement getRequiredField() {
        return getElement( requireField );
    }

    public WebElement getOrganizationSaveButton() {
        return getElement( orgSaveBtn );
    }

    public WebElement getOrganizationCancelButton() {
        return getElement( cancelBtn );
    }

    public List<String> getOrganizationDistrictDropdownValues() {
        return getDropDownValues( getElement( orgDistDrop ) );
    }

    public List<String> getOrganizationDeploymentDropdownValues() {
        return getDropDownValues( getElement( orgDepTypeDrop ) );
    }

    public String getOrganizationDeploymentDefaultSelection() {
        Select orgDrop = new Select( getElement( orgDepTypeDrop ) );
        return orgDrop.getFirstSelectedOption().getText();
    }

    public String getOrganizationDistricttDefaultSelection() {
        Select orgDrop = new Select( getElement( orgDistDrop ) );
        return orgDrop.getFirstSelectedOption().getText();
    }
    
    public String getOrganizationDistrictDefaultSelection() {
        Select orgDrop = new Select( getElement( orgDistDrop ) );
        return orgDrop.getFirstSelectedOption().getText();
    }

    public Map<String, String> clickCancelButtonButton() {
        return getPageDetailsAfterClick( getOrganizationCancelButton() );
    }

    public Map<String, String> clickLicenseTab() {
        return getPageDetailsAfterClick( getLicensesTab() );
    }
    
    public WebElement getDataSettingsTabLink() {
    	return getElement(dataSettingsTabLink);
    }
    
    public Map<String, String> clickDataSettingsTab() {
        return getPageDetailsAfterClick( getDataSettingsTabLink() );
    }
    
    public void selectDeploymentDropdownOption( String item ) {
        CommonHelper.nap();
        Select orgDrop = new Select( getElement( orgDepTypeDrop ) );
        orgDrop.selectByVisibleText( item );
    }
    
    public boolean checkItemPresentInOrganizationDropdown(String item) {
    	return checkItemPresentInDropdown(item, getOrganizationPageOrgdropdown());
    }

    public boolean checkItemPresentInDropdown(String item, WebElement dropdownElement) {
    	 Select elementDropdown = new Select( dropdownElement );
    	 boolean optionFound = false;
    	 List<WebElement> allOptions = elementDropdown.getOptions();
    	 for (int objIndex = 0; objIndex <= allOptions.size(); objIndex++) {
    		 if (allOptions.get(objIndex).getText().equals(item)) {
    			 optionFound = true;
    			 break;
    		 }
    	 }    	 
    	 return optionFound;
    }
    
    public String clickSaveButton() {
        try {
            getOrganizationSaveButton().click();
            log.info("Clicked Save Button");
            return switchToAlert();
        } catch ( Exception e ) {
            log.info( "Alert suppresed in browser " );
            return "";
        }
    }
}
