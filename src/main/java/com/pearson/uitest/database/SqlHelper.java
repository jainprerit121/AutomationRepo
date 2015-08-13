package com.pearson.uitest.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.log4testng.Logger;

import com.pearson.uitest.props.ApplicationProperties;

/**
 * This is generic SQL helper class provides methods to
 * make postgreSQL connection and execute queries
 * 
 * @author Nagarajan Ganesan
 *
 */
public class SqlHelper {

    private static final Logger log = Logger.getLogger( SqlHelper.class );
    private static ApplicationProperties appProperties;
    private static final String POST_GRES_DRIVER = "org.postgresql.Driver";
    private static StringBuilder dbUrl;

    static {
        try {
            Class.forName( POST_GRES_DRIVER );
            appProperties = ApplicationProperties.getInstance();
            dbUrl = new StringBuilder();
            dbUrl.append( "jdbc:postgresql://" ).append( appProperties.getProperty( "dbHost" ) ).append( ":" ).append( appProperties.getProperty( "dbPort" ) ).append( "/" ).append( appProperties.getProperty( "dbName" ) ).append( "?user=" ).append(
                    appProperties.getProperty( "dbUser" ) ).append( "&password=" ).append( appProperties.getProperty( "dbPass" ) );
        } catch ( Exception e ) {
            log.error( "Error while loading database driver." );
        }
    }

    private static Connection getConnection() {
        Connection queryConnection = null;
        try {
            log.info( "Establishing connection to the database: " + appProperties.getProperty( "dbHost" ) );
            queryConnection = DriverManager.getConnection( dbUrl.toString() );
        } catch ( Exception e ) {
            log.error( "Error in establishing DB connection" );
        }
        return queryConnection;
    }

    private static Statement createStmt( Connection queryConnection ) throws SQLException {
        Statement qSt = queryConnection.createStatement( ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY );
        return qSt;
    }

    // To execute stored procedure
    public static Integer executeFunction( String functionStatement, Object[] parameters ) {
        Connection queryConnection = getConnection();
        CallableStatement cst = null;
        ResultSet rs = null;
        Integer statusCode = 0;
        try {
            cst = queryConnection.prepareCall( functionStatement );
            for ( int i = 0; i < parameters.length; i++ ) {
                if ( parameters[i] instanceof String ) {
                    cst.setString( i + 1, parameters[i].toString() );
                } else if ( parameters[i] instanceof Integer ) {
                    cst.setInt( i + 1, ( (Integer) parameters[i] ).intValue() );
                } else if ( parameters[i] instanceof Long ) {
                    cst.setLong( i + 1, ( (Long) parameters[i] ).longValue() );
                } else if ( parameters[i] instanceof Double ) {
                    cst.setDouble( i + 1, ( (Float) parameters[i] ).doubleValue() );
                } else if ( parameters[i] instanceof Float ) {
                    cst.setFloat( i + 1, ( (Float) parameters[i] ).floatValue() );
                }
            }
            cst.execute();
            rs = cst.getResultSet();
            if ( rs != null ) {
                while ( rs.next() ) {
                    statusCode = rs.getInt( 1 );
                }
            }
        } catch ( SQLException sqe ) {
            sqe.printStackTrace();
            log.error( "Error while executing function." );
        } finally {

            try {
                //Close the result set
                if ( rs != null ) {
                    rs.close();
                    rs = null;
                }
                //Close the statement
                if ( cst != null ) {
                    cst.close();
                    cst = null;
                }
                if ( queryConnection != null ) {
                    queryConnection.close();
                    queryConnection = null;
                }
            } catch ( SQLException sqe ) {
                log.error( "Error while releasing resources" );
            }
        }
        return statusCode;
    }

    public static List<Object[]> executeQuery( String queryString ) {
        Connection queryConnection = getConnection();
        ResultSet resultSet = null;
        Statement qSt = null;
        List<Object[]> records = new ArrayList<Object[]>();
        try {
            qSt = createStmt( queryConnection );
            log.info( "Executing Query: " + queryString );
            resultSet = qSt.executeQuery( queryString );
            log.info( "Query executed!" );

            // call only once
            int cols = resultSet.getMetaData().getColumnCount();
            while ( resultSet.next() ) {
                Object[] arr = new Object[cols];
                for ( int i = 0; i < cols; i++ ) {
                    arr[i] = resultSet.getObject( i + 1 );
                }
                records.add( arr );
            }
        } catch ( SQLException e ) {
            log.error( "Error in executing the query" );
        } finally {
            try {
                //Close the result set
                if ( resultSet != null ) {
                    resultSet.close();
                    resultSet = null;
                }
                //Close the statement
                if ( qSt != null ) {
                    qSt.close();
                    qSt = null;
                }
                if ( queryConnection != null ) {
                    queryConnection.close();
                    queryConnection = null;
                }
            } catch ( SQLException sqe ) {
                log.error( "Error while releasing resources" );
            }
        }
        return records;
    }
    
    public static boolean executeUpdateQuery(String queryString){
        Connection queryConnection = getConnection();
        Statement qSt = null;
        try {
            qSt = createStmt( queryConnection );
            log.info( "Executing Query: " + queryString );
            qSt.executeUpdate( queryString );
            log.info( "Query executed!" );
        } catch ( SQLException e ) {
            log.error( "Error in executing the query" );
            return false;
        } finally {
            try {
                //Close the statement
                if ( qSt != null ) {
                    qSt.close();
                    qSt = null;
                }
                if ( queryConnection != null ) {
                    queryConnection.close();
                    queryConnection = null;
                }
            } catch ( SQLException sqe ) {
                log.error( "Error while releasing resources" );
            }
        }
        return true;
    }

    public static boolean executeInsDelQuery( String queryString ) {
        Connection queryConnection = getConnection();
        Statement qSt = null;
        try {
            qSt = createStmt( queryConnection );
            log.info( "Executing Query: " + queryString );
            qSt.executeQuery( queryString );
            log.info( "Query executed!" );
        } catch ( SQLException e ) {
            log.error( "Error in executing the query" );
            return false;
        } finally {
            try {
                //Close the statement
                if ( qSt != null ) {
                    qSt.close();
                    qSt = null;
                }
                if ( queryConnection != null ) {
                    queryConnection.close();
                    queryConnection = null;
                }
            } catch ( SQLException sqe ) {
                log.error( "Error while releasing resources" );
            }
        }
        return true;
    }

}
