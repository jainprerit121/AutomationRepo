package com.pearson.uitest.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.log4testng.Logger;

import com.pearson.uitest.constants.LicenseConstants;
import com.pst.core.licensing.Constants;
import com.pst.core.licensing.DateUtils;
import com.pst.core.licensing.LicenseKeyEncoder;

/**
 * This is common helper class and can hold non application specific generic
 * methods
 * 
 * @author vgaddra
 *
 */
public class CommonHelper {

    private static final String EXP_DATE_VAL_PERP = "04/04/2015";

    protected static Logger log = Logger.getLogger( CommonHelper.class );

    public static void sortAscendingWithIgnoreCase( List<String> list ) {
        Collections.sort( list, new Comparator<String>() {
            @Override
            public int compare( String o1, String o2 ) {
                return o1.compareToIgnoreCase( o2 );
            }
        } );
    }

    public static void sortDescendingtWithIgnoreCase( List<String> list ) {
        Collections.sort(list);
        Collections.sort( list, Collections.reverseOrder( String.CASE_INSENSITIVE_ORDER ) );
    }

    public static void sortAscendingIntegerList(List<String> listSeats){
        List<Integer> intList = new ArrayList<Integer>();

        for(String o : listSeats){
            intList.add(new Integer(o));
        }

        Collections.sort(intList);
        log.info("Sorted :"+intList);
    }

    public static void sortDescendingIntegerList(List<String> listSeats){
        List<Integer> intList = new ArrayList<Integer>();

        for(String o : listSeats){
            intList.add(new Integer(o));
        }

        Collections.sort(intList);
        Collections.sort( intList, Collections.reverseOrder() );
        log.info("Sorted :"+intList);
    }

    public static void nap() {
        try {
            Thread.sleep( 1000 );
        } catch ( Exception e ) {
            log.info( "interrupted " );
        }
    }

    //This code generates the License Key
    public static String generateLicenseKey( int noOfBundleUsers, String[] productList, int startGrade, int endGrade, int type, int entity, int concurrentUser, String expirationDate ) {
        LicenseKeyEncoder keyEncoder = new LicenseKeyEncoder();
        keyEncoder.setConcurrentUseForBundle( noOfBundleUsers );
        for ( int i = 0; i < productList.length; i++ ) {
            String product = productList[i];
            keyEncoder.addProduct( product );
            keyEncoder.setGradeRangeForProduct( startGrade, endGrade, product );

            keyEncoder.setEntityTypeForProduct( product, entity );
            keyEncoder.setConcurrentUseForProduct( product, concurrentUser );
            if(!"".equals( expirationDate )){
                keyEncoder.setExpirationForProduct( product, getExpirationDate( expirationDate ) );
                keyEncoder.setTypeForProduct( product, type );
            }else{
                keyEncoder.setTypeForProduct( product, type );
            }
        }
        keyEncoder.encode();
        return keyEncoder.getKey();
    }

    /**
     * Utility method to validate the date format and returns a <code>Calendar
     * </code> object
     * 
     * @param date <code>String</code> value indicates a date passed in
     *            mm/dd/yyyy format
     * @return cal <code>Calendar</code> object for the date passed as
     *         <code>String</code>
     */
    private static Calendar getExpirationDate( String date ) {
        Calendar locCalendar = null;
        if ( "".equals( date ) || Constants.INPUT_STR_PER.equals( date ) ) {
            locCalendar = DateUtils.getDateAsCalender( Constants.EXP_DATE_VAL_PERP );
        } else {
            boolean isValid = DateUtils.isValidDate( date ) && DateUtils.isValidExpirationDate( date );
            if ( isValid ) {
                locCalendar = DateUtils.getDateAsCalender( date );
            }
        }
        return locCalendar;
    }

    public static String generateKey(String[] productList, int type){        
        String licenseKey=null;
        String expirationDate = null;
        int licenceType = 1;

        if ( type == 0 ) {
            expirationDate = EXP_DATE_VAL_PERP;
            licenceType = LicenseConstants.PERPETUAL_LICENSE_TYPE;
        } 
        else if ( type == 2 ) {
            expirationDate = "";
            licenceType = LicenseConstants.PERPETUAL_LICENSE_TYPE;
        }
        else if( type == 1 ){

            expirationDate = getExpirationDate();
            log.info( "Date: " + expirationDate );
            licenceType = LicenseConstants.SUBSCRIPTION_LICENSE_TYPE;
        }
        licenseKey = generateLicenseKey( LicenseConstants.CONCURRENT_USER, productList,  LicenseConstants.START_GRADE, LicenseConstants.END_GRADE,licenceType , LicenseConstants.ENTITY, LicenseConstants.CONCURRENT_USER, expirationDate );    
        return licenseKey;
    }

    public static String getExpirationDate(){
        SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
        Date today = new Date();
        return sdf.format( new Date(today.getTime() + 10*(1000 * 60 * 60 * 24)));
    }

    public static By getLocatorFormatInt(By locator, int index) {
        String xpath = String.format(locator.toString(), ""+index);
        String newXpath = xpath.substring(xpath.indexOf('/'));
        return By.xpath(newXpath);
    }

    public static By getLocatorFormatString(By locator, String sub) {
        String xpath = String.format(locator.toString(), ""+sub);
        String newXpath = xpath.substring(xpath.indexOf('/'));
        return By.xpath(newXpath);
    }

    public static int getTableColumnHeaderIndex(List<WebElement> webElements, String strColumnName){
        int col=1;
        for(WebElement ele : webElements){
            if(ele.getText().trim().equals(strColumnName)){
                return col;
            }
            col++;
        }
        return -1;
    }

    public static void selectDropDownValueByVisibleText(WebElement element, String strValue){
        Select tempVal = new Select(element);
        tempVal.selectByVisibleText( strValue );
    }

    public static String getFirstSelectedOptionFromDropDown(WebElement ele){
        Select tempVal = new Select(ele);
        String strName = tempVal.getFirstSelectedOption().getText();
        return strName;
    }

    public static String getTableColumnValue(int tableSize, List<String> lstStringNames, List <String> lstStringSeats, String licenseName){
        String tableVal = "";
        for ( int i = 0; i < tableSize; i++ ) {
            if ( lstStringNames.get( i ).toString().equals( licenseName ) ) {
                tableVal = lstStringSeats.get( i ).toString();
                log.info( tableVal );                
                break;
            }
        }
        return tableVal;
    }

    public static boolean compareLists(List<String> firstList,List<String> secondList){
        boolean flag = false;
        if(firstList.size()==secondList.size()){
            for(int counter = 0; counter < firstList.size(); counter++) {
                if(secondList.contains(firstList.get(counter))) {
                    flag=true;
                }else{
                    flag=false;
                    break;
                }
            }
        }else{
            flag = false;
        }
        return flag;
    }
    
    public static String formatDateAsPerDB(String strDate){
        String format="";
        try{
        Date date = new SimpleDateFormat("mm/dd/yyyy").parse(strDate);
        format = new SimpleDateFormat("yyyy/mm/dd").format(date);        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return format;
    }
}
