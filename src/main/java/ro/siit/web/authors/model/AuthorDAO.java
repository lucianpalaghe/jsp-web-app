package ro.siit.web.authors.model;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e){
            System.err.println("Can’t load driver. Verify CLASSPATH");
            System.err.println(e.getMessage());
        }
    }

    public List<Author> getAllAuthors(){
        Connection conn = getConnection("mysql", "localhost", 3306, "books", "root", "root");
        if (conn == null) return null;
        Statement st = null;
        ResultSet rs = null;
        List<Author> authors = null;
        try {
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("select id, name, email, birthdate from authors");
            boolean hasResults = rs.next();
            if (hasResults) {
                authors = new ArrayList<Author>();
                do {
                    Author a = new Author();
                    a.setId(rs.getInt("id"));
                    a.setName(rs.getString("name"));
                    a.setEmail(rs.getString("email"));
                    a.setBirthDate(rs.getDate("birthdate").toLocalDate());
                    authors.add(a);
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.err.println("Cannot execute query: " + e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {  }
            if (st != null) try { st.close(); } catch (SQLException e) {  }
            try { conn.close(); } catch (SQLException e) { }
        }
        return authors;
    }

    public int removeAuthorById(int id){
        Connection conn = getConnection("mysql", "localhost", 3306, "books", "root", "root");
        if (conn == null) return -1;
        PreparedStatement st = null;
        int rowsAffected = 0;
        try {
            st = conn.prepareStatement("delete from authors where id = ?");
            st.setInt(1, id);
            rowsAffected = st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Cannot execute query: " + e.getMessage());
        } finally {
            if (st != null) try { st.close(); } catch (SQLException e) {  }
            try { conn.close(); } catch (SQLException e) { }
        }
        return rowsAffected;
    }

    private static Connection getConnection(String type, String host, int port, String dbName, String user, String pw){
        Connection conn = null;
        DriverManager.setLoginTimeout(60);  // wait 1 min; optional: DB may be busy, good to set a higher timeout
        try {
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append(type)       // “mysql” / “db2” / “mssql” / “oracle” / ...
                    .append("://")
                    .append(host)
                    .append(":")
                    .append(port)
                    .append("/")
                    .append(dbName)
                    .append("?user=")
                    .append(user)
                    .append("&password=")
                    .append(pw).toString();
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("Cannot connect to the database: " + e.getMessage());
        }
        return null;
    }
}
