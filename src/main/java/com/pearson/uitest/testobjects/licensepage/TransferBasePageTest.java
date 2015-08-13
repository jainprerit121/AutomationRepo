package com.pearson.uitest.testobjects.licensepage;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.pearson.uitest.constants.LicenseConstants;
import com.pearson.uitest.database.LicenseSqlHelper;
import com.pearson.uitest.helper.CommonHelper;
import com.pearson.uitest.pageobjects.TransferLicensePage;
import com.pearson.uitest.testobjects.AdminBaseTest;

/**
 * Base class contains common methods related to Transfer Page
 * @author Prerit Jain
 *
 */
public abstract class TransferBasePageTest extends AdminBaseTest{
    
    protected List<String> orgNamesFromTransferToDropdown = new ArrayList<String>();
    
    protected void transferLicense(int transferValue, String destOrg){
        ((TransferLicensePage) page).getTransferNumberOfUsersTextBox().clear();
        ((TransferLicensePage) page).getTransferNumberOfUsersTextBox().sendKeys(String.valueOf( transferValue ));
        
        CommonHelper.selectDropDownValueByVisibleText(((TransferLicensePage) page).getTransferOrgSelectDropDown(), destOrg );
        ((TransferLicensePage) page).getTransferOkBtn().click();
        log.info( "Clicked on Transfer button for transferring the users" );
        CommonHelper.nap();
        CommonHelper.nap();
        log.info("Transfer " + transferValue + " number of user(s) to Org: " + destOrg);  
    }
    
    protected void checkSourceOrgNameNotInTransferToDropdown(String orgName){
        int flag=0;
        orgNamesFromTransferToDropdown = ((TransferLicensePage) page).getTransferToDropdownValues();
        for(int i=0; i<orgNamesFromTransferToDropdown.size();i++)
        {
            log.info("Organization names list: " + orgNamesFromTransferToDropdown.get(i).toString());
            if(orgName.equals( orgNamesFromTransferToDropdown.get(i)) || (appProperties.getProperty( "OrgNameExisting")).equals( orgNamesFromTransferToDropdown.get( i )))
            {   
                flag=1;
                log.error("Transfer To dropdown contains the origin Organization value");
                break;
            }
        }
        Assert.assertEquals( 0, flag ,"Failed: Transfer To dropdown contains the source Organization value");
        log.info("Test case: checkOrgNameNotInTransferToDropdown - PASSED: Transfer To dropdown doesn't contain the source Organization value nor District value");
    }
    
    protected void checkTransferErrorPopUpMsg(String popUpMsgKey){
        ((TransferLicensePage) page).getTransferOkBtn().click();
        Assert.assertEquals(((TransferLicensePage) page).getPopUpMsg().getText().replaceAll("\n", "").replaceAll("  ",  " ").trim(), appProperties.getProperty(popUpMsgKey));
        Assert.assertNotNull(((TransferLicensePage) page).getPopUpOkBtn(), "Ok button under Select an Organization for the Transfer dialog window is present" );
        ((TransferLicensePage) page).getPopUpOkBtn().click();
        CommonHelper.nap();
    }
    
    protected void checkTransferToExceedValue(){
        log.info( "Verification for checking whether Number of users to transfer text box accepts more than total number of users" );  
        ((TransferLicensePage) page).getTransferNumberOfUsersTextBox().sendKeys(LicenseConstants.CONCURRENT_USER + String.valueOf(100));
        Assert.assertEquals(((TransferLicensePage) page).getTransferNumberOfUsersTextBox().getText(), "", "Transfer To text box is able to accept more than total number of users that are transferrable");     
    }
    
    protected void checkTransferToNonNumericValue(){
        log.info( "Verification for checking whether Number of users to transfer text box accepts any non-numeric value" );        
        ((TransferLicensePage) page).getTransferNumberOfUsersTextBox().sendKeys(appProperties.getProperty( "AlphNumericValue" ));
        Assert.assertEquals(((TransferLicensePage) page).getTransferNumberOfUsersTextBox().getText(), "", "Transfer To text box is able to accept alphanumeric values");
    }
    
    protected void checkOrgNamesAlphanumericallyListedUnderTransferToDropDown(String orgName){
        log.info( "Verification for checking whether the list of organization names are listed alphanumerically under Transfer To drop-down" );
        List<Object[]> orgNamesFromDB = new ArrayList<Object[]>();
        
        orgNamesFromDB = LicenseSqlHelper.getOrgNamesInAlphanumericOrderExceptSourceOrg(orgName);
        orgNamesFromTransferToDropdown.remove( appProperties.getProperty( "DefaultTransferToSelection" ) );
        int i=0;
        for(Object[] orgArr:orgNamesFromDB){
            
                Assert.assertEquals(orgNamesFromTransferToDropdown.get( i ),  orgArr[0], "Organization comparison failed");
                log.info("Actual Org: " + orgNamesFromTransferToDropdown.get( i ) + ":: Expected Org: " + orgArr[0]);
                i++; 
        }
    }
    
    protected void verifyPrepopulatedTextOnTransferLincensePage(){

        ((TransferLicensePage) page).getTransferLink(LicenseConstants.LICENSE_ORG_FLEX2_PERPETUAL).click();
        Assert.assertEquals( ((TransferLicensePage) page).getTransferExpDateValueFromDialog(), LicenseConstants.PERPETUAL_LICENSE_EXP_DATE );
        Assert.assertEquals( ((TransferLicensePage) page).getTotalNumberOfUsersFromDialog(), String.valueOf( LicenseConstants.CONCURRENT_USER ));
        Assert.assertEquals( ((TransferLicensePage) page).getTransferNumberOfUsersTextBox().getAttribute( "value" ), String.valueOf( LicenseConstants.CONCURRENT_USER ));
        ((TransferLicensePage) page).getTransferCancelBtn().click();
        
        ((TransferLicensePage) page).getTransferLink(LicenseConstants.LICENSE_FLEX1_SUBSCRIPTION).click();
        Assert.assertEquals( ((TransferLicensePage) page).getTransferExpDateValueFromDialog(), CommonHelper.getExpirationDate() );
        ((TransferLicensePage) page).getTransferCancelBtn().click();
    }
}
