package com.pearson.uitest.props;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.testng.log4testng.Logger;

/**
 * This is a singleton class and loads the property file once and serves to other classes
 * 
 * @author vgaddra
 *
 */
public class ApplicationProperties {

    private static final Logger log = Logger.getLogger( ApplicationProperties.class );
    private static ApplicationProperties appProperties;

    private Properties properties;

    private ApplicationProperties() {
        properties = loadProperties();
    }

    private Properties loadProperties() {
        File file = new File( "./src/main/resources/config.properties" );
        FileInputStream fileInput = null;
        Properties props = new Properties();
        try {
            fileInput = new FileInputStream( file );
            props.load( fileInput );
            fileInput.close();
        } catch ( FileNotFoundException e ) {
            log.error( "config.properties is missing or corrupt : " + e.getMessage() );

        } catch ( IOException e ) {
            log.error( "read failed due to: " + e.getMessage() );
        }
        Enumeration<?> e = props.propertyNames();
        while ( e.hasMoreElements() ) {
            String key = (String) e.nextElement();
            if ( props.getProperty( key ).contains( "/lms/sm.view" ) || props.getProperty( key ).startsWith( "/successmaker/" ) || props.getProperty( key ).contains( "/managementConsole/" ) ) {
                props.setProperty( key, props.getProperty( "envUrl" ) + props.getProperty( key ) );
            }
        }
        return props;
    }

    public static ApplicationProperties getInstance() {
        if ( appProperties == null ) {
            appProperties = new ApplicationProperties();
        }
        return appProperties;
    }

    public String getProperty( String key ) {
        return properties.getProperty( key );

    }

}
