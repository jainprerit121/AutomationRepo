package com.pearson.uitest.pageobjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

/**
 * 
 * @author vgaddra
 *
 */
public class GroupDetailsPage extends ManageGroupPage {

    private By groupDetailsWelocmeLabel = By.id( "contentBar" );
    private By groupDetailsNameLabel = By.id( "groupDetailsNameLabel" );
    private By groupDetailsNameText = By.id( "groupDetailsNameText" );
    private By groupDetailsOwnerLabel = By.id( "addGroupOwnerLabel" );
    private By groupDetailsOwnerText = By.id( "addGroupDetailsOwnerInputDiv" );
    private By groupDetailsDescriptionLabel = By.id( "addGroupDetailsDescLabel" );
    private By groupDetailsDescriptionText = By.id( "addGroupDetailsDescInputDiv" );
    private By groupDetailsEditGroupBtn = By.id( "addGroupDetailsEditGroup" );
    private By groupDetailsAddUsersToGroupBtn = By.id( "addGroupDetailsAddUser" );
    private By groupDetailsAddAnotherGroupBtn = By.id( "addGroupDetailsAnother" );
    private By groupDetailsExitAddGroupBtn = By.id( "addGroupDetailsExit" );

    public GroupDetailsPage( WebDriver driver ) {
        super( driver );
        log = Logger.getLogger( GroupDetailsPage.class );
    }

    /**
     * Get Group details welcome text
     */
    public String getGroupDetailsWelcomeText() {
        return getElement( groupDetailsWelocmeLabel ).getText();
    }

    /**
     * Get group details name label
     */
    public String getGroupDetailsNameLabel() {
        return getElement( groupDetailsNameLabel ).getText();
    }

    /**
     * Get group details name text
     */
    public String getGroupDetailsNameText() {
        return getElement( groupDetailsNameText ).getText();
    }

    /**
     * Get group details owner label
     */
    public String getGroupDetailsOwnerLabel() {
        return getElement( groupDetailsOwnerLabel ).getText();
    }

    /**
     * Get group details owner text
     */
    public String getGroupDetailsOwnerText() {
        return getElement( groupDetailsOwnerText ).getText();
    }

    /**
     * Get Group details description label
     */
    public String getGroupDetailsDescriptionLabel() {
        return getElement( groupDetailsDescriptionLabel ).getText();
    }

    /**
     * Get group details description text
     */
    public String getGroupDetailsDescriptionText() {
        return getElement( groupDetailsDescriptionText ).getText();
    }

    /**
     * Get Edit Group button in group details text
     */
    public WebElement getGroupDetailsEditGroupBtn() {
        return getElement( groupDetailsEditGroupBtn );
    }

    /**
     * Click on Edit Group button
     */
    //TODO : Needs to be modified once actual implementation is completed.
    public String clickGroupEditGroupBtn() {
        return clickOnButton( getGroupDetailsEditGroupBtn() );
    }

    /**
     * Get group details Add Users to Group btn
     */
    public WebElement getGroupDetailsAddUsersToGroupBtn() {
        return getElement( groupDetailsAddUsersToGroupBtn );
    }

    /**
     * Click on Add Users to Group btn
     */
    //TODO : Needs to be modified once actual implementation is completed.
    public String clickGroupDetailsAddUsersToGroupBtn() {
        return clickOnButton( getGroupDetailsAddUsersToGroupBtn() );
    }

    /**
     * Get Add Another User button in Group details page
     */
    public WebElement getGroupDetailsAddAnotherGroupBtn() {
        return getElement( groupDetailsAddAnotherGroupBtn );
    }

    /**
     * Click on Add Another Group btn
     */
    public Map<String, String> clickGroupDetailsAddAnotherGroupBtn() {
        return getPageDetailsAfterClick( getGroupDetailsAddAnotherGroupBtn() );
    }

    /**
     * Get Exit Add Group btn
     */
    public WebElement getGroupDetailsExitAddGroupBtn() {
        return getElement( groupDetailsExitAddGroupBtn );
    }

    /**
     * Click on Exit Add Group btn
     */
    public Map<String, String> clickGroupDetailsExitAddGroupBtn() {
        return getPageDetailsAfterClick( getGroupDetailsExitAddGroupBtn() );
    }

}
