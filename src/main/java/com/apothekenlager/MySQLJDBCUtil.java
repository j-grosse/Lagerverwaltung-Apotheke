package com.apothekenlager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;

import static com.apothekenlager.LagerApp.path;

    /**
     *
     * @author mysqltutorial.org
     */
    public class MySQLJDBCUtil {

        /**
         * Get database connection
         *
         * @return a Connection object
         * @throws SQLException
         */
        public static Connection getConnection() throws SQLException {
            Connection conn = null;

            try (FileInputStream f = new FileInputStream(path + "db.properties")) {

                // load the properties file
                Properties pros = new Properties();
                pros.load(f);

                // assign db parameters
                String url = pros.getProperty("url");
                String user = pros.getProperty("user");
                String password = pros.getProperty("password");

                // create a connection to the database
                conn = DriverManager.getConnection(url, user, password);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }



        public void update() {

            String sqlUpdate = "UPDATE candidates "
                    + "SET last_name = ? "
                    + "WHERE id = ?";

            try (Connection conn = MySQLJDBCUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

                // prepare data for update
                String lastName = "William";
                int id = 100;
                pstmt.setString(1, lastName);
                pstmt.setInt(2, id);

                int rowAffected = pstmt.executeUpdate();
                System.out.println(String.format("Row affected %d", rowAffected));

                // reuse the prepared statement
                lastName = "Grohe";
                id = 101;
                pstmt.setString(1, lastName);
                pstmt.setInt(2, id);

                rowAffected = pstmt.executeUpdate();
                System.out.println(String.format("Row affected %d", rowAffected));

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }


    }