package com.pearson.uitest.testutils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.pearson.uitest.constants.AdminUIConstants;

/**
 * Retry rule for testclasses which are failed because of wait timed out No of
 * retries can be specified in the TestBaseClass @Rule annotated place
 * 
 * @author Nagarajan Ganesan
 * 
 */
public class RetryRule implements IRetryAnalyzer {
    private int retryCount=1;
    private int maxRetryCount = AdminUIConstants.MAX_RETRY_COUNT;

    public boolean retry( ITestResult result ) {
        if ( maxRetryCount >= retryCount ) {
            retryCount++;
            return true;
        }
        return false;
    }

}
