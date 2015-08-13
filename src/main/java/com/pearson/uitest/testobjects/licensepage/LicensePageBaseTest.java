package com.pearson.uitest.testobjects.licensepage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import com.pearson.uitest.pageobjects.LicenseBasePage;
import com.pearson.uitest.testobjects.AdminBaseTest;


/**
 * Tests for Column and Data verification of the License Page table
 * Also, captured License table elements here.
 */

public class LicensePageBaseTest extends AdminBaseTest{

	    public void initialize( @Optional ( "FireFox-Win7" ) String browser ) {
	        super.initialize( browser );      
	       
	    }
	    	   	    
	    @Test ( priority = 101, dataProvider = "checkLicPageElementsDataProvider" )
		public void checkElementTest(String actualText, String expectedText, String testDesc) {
		    log.info( "Test for " + testDesc);
		    Assert.assertEquals( actualText, expectedText, "Failed in verifying  " + testDesc + ", actual = " + actualText + ", expected = " + expectedText );
		}
		 
		@DataProvider ( name = "checkLicPageElementsDataProvider" )
		public Object[][] checkLicPageElementsDataProvider() {
			((LicenseBasePage) page).getLicenseTabBtn().click();
		    Object[][] inputData = {		  
		    { ( (LicenseBasePage) page ).getLicenseColumnNameHeader().getText(), appProperties.getProperty( "LicenseColumnHeader" ), "Verified License column header" },
		    { ( (LicenseBasePage) page ).getExpDateColumnHeader().getText(), appProperties.getProperty( "ExpDateColumnHeader" ), "Verified Exp. Date column header" },
		    { ( (LicenseBasePage) page ).getSeatsColumnHeader().getText(), appProperties.getProperty( "SeatsColumnHeader" ), "Verified Number of Seats column header"  },
	        { ( (LicenseBasePage) page ).getInUseColumnHeader().getText(), appProperties.getProperty( "InUseColumnHeader" ), "Verified In Use column header"},
	        { ( (LicenseBasePage) page ).getDenialsColumnHeader().getText(), appProperties.getProperty( "DenialsHeaderColumn" ), "Verified Denials column header"   },
	        { ( (LicenseBasePage) page ).getOptionsColumnHeader().getText(), appProperties.getProperty( "OptionsColumnHeader" ), "Verified Options column header"   }      
		    };
		     return inputData;         
		}
	    	        
}
