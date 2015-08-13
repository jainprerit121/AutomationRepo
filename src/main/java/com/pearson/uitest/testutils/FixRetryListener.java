package com.pearson.uitest.testutils;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Retry listener
 * 
 * @author vgaddra
 *
 */
public class FixRetryListener extends TestListenerAdapter {
    //private static final Logger log = Logger.getLogger( FixRetryListener.class );
    //private static int count;
    //private static final int MAX_COUNT = AdminUIConstants.MAX_RETRY_COUNT;;

    @Override
    public void onTestFailure(ITestResult tr) {   
    /**    if(count < MAX_COUNT) {
            count++;
            log.info("Retrying the test case " + tr.getMethod().getMethodName() + " : Retry attempt -> " + count);
            tr.setThrowable(null);
        } else  {
        	log.error("Maximun retry attempts are done! Still test case " + tr.getMethod().getMethodName() + " is FAILING..");
            tr.setStatus(ITestResult.FAILURE);
            // reset count
            count = 0; 
        }
        
       //Reporter.setCurrentTestResult(null);
        super.onTestFailure(tr); **/
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
     /**   super.onTestSuccess(tr);
        count = 0; **/
    }    
    
    @Override
    public void onFinish(ITestContext context) { /**
        for (int i = 0; i < context.getAllTestMethods().length; i++) {
            if (context.getAllTestMethods()[i].getCurrentInvocationCount() > 1) {
            	int testFailSize = context.getFailedTests().getResults(context.getAllTestMethods()[i]).size();
                if (testFailSize <= 4) {
                    for (int rind = 0; rind < testFailSize ; rind++ ) {
                    	context.getFailedTests().removeResult( context.getAllTestMethods()[i]);
                    }
                }
            }
        } **/
    }    	
}