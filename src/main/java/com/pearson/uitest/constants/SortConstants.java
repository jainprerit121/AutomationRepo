/*****************************************************************************
 * Project : Robot / SM Admin Applet Removal
 * 
 * Module : applet removal UI tests.
 * 
 * Source file name : LocatorConstants.java
 * 
 * Description : Constants related to locators are defined here.
 * 
 * Written By : Harisha Prabhu, Gurunandan Bhat
 * 
 * Copyright : Copyright (c) 2014, Pearson PLC.
 * 
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 * 
 *******************************************************************************/

package com.pearson.uitest.constants;

/**
 * All the constant related to sorting are defined here.
 */
public interface SortConstants {

    public static final String CASE_SENSITIVE_SORTRULE_ASCENDING = "< z,Z < y,Y < x,X < w,W < v,V < u,U < t,T < s,S < r,R < q,Q < p,P < o,O < n,N < m,M < l,L < k,K < j,J < i,I < h,H < g,G < f,F < e,E < d,D < c,C < b,B < a,A < 9 < 8 < 7 < 6 < 5 < 4 < 3 < 2 < 1 < 0";
    public static final String CASE_SENSITIVE_SORTRULE_DESCENDING = "< 0 < 1 < 2 < 3 < 4 < 5 < 6 < 7 < 8 < 9 < a,A < b,B < c,C < d,D < e,E < f,F < g,G < h,H < i,I < j,J < k,K < l,L < m,M < n,N < o,O < p,P < q,Q < r,R < s,S < t,T < u,U < v,V < w,W < x,X < y,Y < z,Z";

    // INFO on above sort rules.
    /*
     */

    // This rules can be changed as per what case-insensitive,
    // case-sensitive changes
    //        if ( !ascendFalseDescendTrue ) { // false=ascending
    //            // < 9 ... < 1 < 0 < a,A < ... < z,Z
    //            for ( i = 122; i >= 97; i-- ) {
    //                rules += " < " + (char) i + "," + (char) ( i - 32 );
    //            }
    //            for ( i = 9; i >= 0; i-- ) {
    //                numRules += " < " + i;
    //            }
    //            rules += numRules;
    //
    //        } else {
    //            // < z,Z ... < b,B < a,A < 9 < ... < 0
    //            for ( i = 97; i < 123; i++ ) {
    //                rules += " < " + (char) i + "," + (char) ( i - 32 );
    //            }
    //            for ( i = 0; i <= 9; i++ ) {
    //                numRules += " < " + i;
    //            }
    //            rules = numRules + rules;
    //        }
    //        log.info( rules );

    /* 
     */

}
