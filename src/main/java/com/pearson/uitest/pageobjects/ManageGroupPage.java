package com.pearson.uitest.pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.pearson.uitest.helper.CommonHelper;

/**
 * 
 * @author vgaddra
 *
 */
public class ManageGroupPage extends GroupsLandingPage {

    private By groupWelcomeLabel = By.id( "contentBar" );
    private By groupRequiredFieldLabel = By.id( "requireFieldDiv" );
    private By groupNameLabel = By.id( "groupNameLabel" );
    private By groupNameInput = By.id( "groupNameInput" );
    private By groupOwnerLabel = By.id( "addGroupOwnerLabel" );
    private By groupOwnerDropDown = By.id( "addGroupOwnerSelect" );
    private By groupDescriptionLabel = By.id( "addGroupDescLabel" );
    private By groupDescriptionInput = By.id( "addGroupDescInput" );
    private By groupSaveBtn = By.id( "addGroupSave" );
    private By groupCancelBtn = By.id( "addGroupCancel" );

    public ManageGroupPage( WebDriver driver ) {
        super( driver );
        log = Logger.getLogger( ManageGroupPage.class );
    }

    /**
     * Get Group welcome message
     */
    public String getGroupWelcomeText() {
        return getElement( groupWelcomeLabel ).getText();
    }

    /**
     * Get Group required field text
     */
    public String getGroupRequiredFieldText() {
        return getElement( groupRequiredFieldLabel ).getText();
    }

    /**
     * Get group name label
     */
    public String getGroupNameLabel() {
        return getElement( groupNameLabel ).getText();
    }

    /**
     * Get Group name input
     */
    public WebElement getGroupNameInput() {
        return getElement( groupNameInput );
    }

    /**
     * Get Group owner label
     */
    public String getGroupOwnerLabel() {
        return getElement( groupOwnerLabel ).getText();
    }

    /**
     * Get group owner dropdown
     */
    public WebElement getGroupOwnerDropDown() {
        return getElement( groupOwnerDropDown );
    }

    /**
     * Get default selected value in group owner dropdown
     */
    public String getDefaultSelectedInGroupOwnerDropDown() {
        return getDefaultSelectedOption( getGroupOwnerDropDown() );
    }

    /**
     * Select an Item from Group owner dropdown
     */
    public void selectAnItemFromGroupDropDown( String ownerToSelect ) {
        selectItemFromDropDown( getGroupOwnerDropDown(), ownerToSelect );
    }

    /**
     * Get Group description label
     */
    public String getGroupDescriptionLabel() {
        return getElement( groupDescriptionLabel ).getText();
    }

    /**
     * Get group description input
     */
    public WebElement getGroupDescriptionInput() {
        return getElement( groupDescriptionInput );
    }

    /**
     * Get Group Save Button
     */
    public WebElement getGroupSaveBtn() {
        return getElement( groupSaveBtn );
    }

    /**
     * Click on Save button
     */
    public Map<String, String> clickGroupSaveBtn() {
        return getPageDetailsAfterClick( getGroupSaveBtn() );
    }

    /**
     * Get Group cancel button
     */
    public WebElement getGroupCancelBtn() {
        return getElement( groupCancelBtn );
    }

    /**
     * Click on Cancel button
     */
    public Map<String, String> clickGroupCancelBtn() {
        return getPageDetailsAfterClick( getGroupCancelBtn() );
    }

    /**
     * Check sorting of Owner list is sorted alphabetically by second name.
     */
    public boolean checkSortingOfOwnersListByLastName( WebElement element ) {
        List<String> ownerList = getDropDownValues( element );
        List<String> ownerListLastName = new ArrayList<String>();
        for ( String owner : ownerList ) {
            ownerListLastName.add( owner.split( " " )[1] );
        }
        List<String> expectedOwnerList = new ArrayList<String>( ownerListLastName );
        CommonHelper.sortAscendingWithIgnoreCase( expectedOwnerList );
        log.info( "Expected: " + expectedOwnerList.toString() );
        log.info( "Actual: " + ownerListLastName.toString() );
        return expectedOwnerList.equals( ownerListLastName );
    }

}
