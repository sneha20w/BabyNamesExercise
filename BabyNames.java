import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
    public class BabyNames {
        public static void main (String args[]) throws Exception{

            final String DATABASE_URL = "jdbc:derby:C:/Users/bhalu/Downloads/babynames-derby/babynames";
            final String QUERY1 = "SELECT NAME, DATE_YEAR, GENDER, US_STATE, NUM_BABIES FROM BABYNAMES WHERE DATE_YEAR = 1991 AND US_STATE = 'MD' ORDER BY NUM_BABIES DESC FETCH FIRST 1 ROW ONLY";
            final String QUERY2 = "SELECT NAME, DATE_YEAR, GENDER, NUM_BABIES FROM BABYNAMES WHERE NAME = 'Christopher' AND GENDER = 'M' ORDER BY NUM_BABIES DESC FETCH FIRST 1 ROW ONLY";
            final String QUERY3 = "SELECT NAME, DATE_YEAR, GENDER, NUM_BABIES FROM BABYNAMES WHERE NAME = 'Rosemary' AND GENDER = 'F' ORDER BY NUM_BABIES DESC FETCH FIRST 1 ROW ONLY";
            final String QUERY4 = "SELECT NAME, DATE_YEAR, GENDER, US_STATE, NUM_BABIES FROM BABYNAMES WHERE NUM_BABIES > 500 AND DATE_YEAR = 2000 AND US_STATE = 'MD'";
            final String QUERY5 = "SELECT NAME, DATE_YEAR, GENDER, US_STATE, NUM_BABIES FROM BABYNAMES WHERE NAME = 'Xavier' AND GENDER = 'M' AND DATE_YEAR = 2014 ORDER BY NUM_BABIES ASC FETCH FIRST 1 ROW ONLY";
            final String QUERY6 = "SELECT NAME, DATE_YEAR, GENDER, US_STATE, NUM_BABIES FROM BABYNAMES WHERE NAME = 'Hannah' AND GENDER = 'F' AND DATE_YEAR = 1997 ORDER BY NUM_BABIES DESC FETCH FIRST 1 ROW ONLY";
            final String QUERY7 = "INSERT INTO BABYNAMES (ID, NAME, DATE_YEAR, GENDER, US_STATE, NUM_BABIES) VALUES (10000000, 'Joseph', 2016, 'M', 'PA', 476)";
            final String QUERY8 = "DELETE FROM BABYNAMES WHERE ID = 10000000";	
           
            try (
                Connection connection = DriverManager.getConnection(DATABASE_URL);
 
        		Statement statement = connection.createStatement())
 
           {
            	String query = null;
            	for (int i = 0; i < 5; i++) {
            	switch (i) {
            	case 0:
            	query = new String(QUERY1);
            	break; 
            	
            	case 1:
            	query = new String(QUERY2);
            	break;
            	
            	case 2:
                query = new String(QUERY3);
                break; 
                	
            	case 3:
                query = new String(QUERY4);
                break; 
            	
            	case 4:
                query = new String(QUERY5);
                break; 
            	
            	case 5:
                query = new String(QUERY6);
                break; 
            	
            	}
            	try (
            		ResultSet resultSet = statement.executeQuery(query)) {
            		ResultSetMetaData metaData = resultSet.getMetaData();
                    int numberOfColumns = metaData.getColumnCount();
                    for (int j = 1; j<= numberOfColumns; j++) {
                        System.out.printf("%-8s\t", metaData.getColumnName(j));
                    }
                    System.out.println();
                   while (resultSet.next()) {
                       for (int k = 1; k <= numberOfColumns;k++) {
                           System.out.printf("%-8s\t", resultSet.getObject(k) );
                       }
                       System.out.println();
            		
            	}
            	}
                catch (SQLException sqlException) {
                    sqlException.printStackTrace();

                }
            	}   
            	statement.executeUpdate(QUERY7);
            	statement.executeUpdate(QUERY8);
           }
           catch (SQLException sqlException) {
               sqlException.printStackTrace();

            }
        }
    }

