package com.pearson.uitest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

/**
 * This class defines the Licenses table elements 
 */

public class LicenseBasePage extends HomePage {
	
	private By licenseColumn = By.id( "lsTableHeaderth1" );
	private By expDateColumn = By.id( "lsTableHeaderth2" );
	private By seatsColumn = By.id( "lsTableHeaderth3" );
	private By inUseColumn = By.id( "lsTableHeaderth4" );
	private By denialsColumn = By.id( "lsTableHeaderth5" );
	private By optionsColumn = By.id( "lsTableHeaderth6" );	

	public LicenseBasePage(WebDriver driver) {
		super(driver);
		log = Logger.getLogger( LicenseBasePage.class );
	}

	//Get License Table header
	public WebElement getLicenseColumnNameHeader() {
        return getElement( licenseColumn );
    }

	//Get Expiry Date Table header
	public WebElement getExpDateColumnHeader(){
		return getElement( expDateColumn );
	}
	
	//Get Number of Seats Table header
	public WebElement getSeatsColumnHeader(){
		return getElement( seatsColumn );
	}
	
	//Get In use number of Seats Table header
	public WebElement getInUseColumnHeader(){
		return getElement( inUseColumn );
	}
	
	//Get Denials Table header
	public WebElement getDenialsColumnHeader(){
		return getElement( denialsColumn );
	}
	
	//Get Options Table header
	public WebElement getOptionsColumnHeader(){
		return getElement( optionsColumn );
	}
}
