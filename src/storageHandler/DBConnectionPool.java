package storageHandler;

import static application.MainLogger.getLogger;
import static application.Settings.DB_DRIVERCLASS;
import static application.Settings.DB_PASS;
import static application.Settings.DB_URL;
import static application.Settings.DB_USER;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import org.apache.commons.dbcp.BasicDataSource;

public class DBConnectionPool {

  static {
    try {
      System.setProperty("sun.net.http.errorstream.enableBuffering", "true");
      System.out.println("CHECKING JDBC_DRIVER: " + DB_DRIVERCLASS);
      Class.forName(DB_DRIVERCLASS);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("SERIOUS: NO DRIVER FOR SELECTED DATABASE!");
    }
  }

  private static DBConnectionPool conPool;

  // represents the database
  private static BasicDataSource  ds;

  public DBConnectionPool()
      throws IOException, SQLException, PropertyVetoException {
    ds = new BasicDataSource();
    ds.setDriverClassName(DB_DRIVERCLASS);
    ds.setUsername(DB_USER);
    ds.setPassword(DB_PASS);
    ds.setUrl(DB_URL);
    ds.addConnectionProperty("cacheState", "false");
    getLogger().log(Level.INFO, "DB Connection Pool started");
  }

  public static synchronized DBConnectionPool getInstance() {
    try {
      if (conPool == null) {
        conPool = new DBConnectionPool();
      }
      return conPool;

    } catch (Exception e) {
      getLogger().log(Level.SEVERE,
          "Cannot initiate ConnectionPool!", e);
      throw new RuntimeException("Error initiating ConnectionPool", e);
    }
  }

  public Connection getConnection() throws SQLException {
    try{
      return ds.getConnection();
    } catch (SQLException e){
      getLogger().log(Level.SEVERE , "Cannot get a DB Connection from Pool!" , e);
      throw e;
    }
  }

}
