package jdbc3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
	// connections properties
	private final String DRIVER = "org.apache.derby.jdbc.EmbededDriver"; // connection
	private final String URL = "jdbc:derby:sampleDB;create=true";
	private final String USER = "user";
	private final String PASSWORD = "user";

    Connection conn = null; // the bridge
    Statement cStatement = null; // the transporter

    public DB() { // in constructor we define a connection and fill data if is empty we can create one, so initialize

	    try {
			// connection from DriverManager
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("the connection was built");
		} catch (SQLException e) {
			// exception handling
			Logger.getLogger(DB.class.getName()).log(Level.SEVERE,null,e);
		}


		// ste statement comes from our connection
        // create table if not exist
	    if(conn != null){

            try {
                cStatement = conn.createStatement(); // connection i need a statement transporter
            } catch (SQLException e) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE,null,e);
            }
        }

        // data base meta datas
        try {
            String sqlCREATE = "create table users( name varchar(30), email varchar(60) )";

            DatabaseMetaData dbmd = null;
            dbmd = conn.getMetaData();
            ResultSet rs1 = dbmd.getTables(null,"APP","USERS",null);

            if( !rs1.next()){ // if table is not exist, create it
                cStatement.execute(sqlCREATE);
            }

        } catch (SQLException e) {
            //Logger.getLogger(DB.class.getName()).log(Level.SEVERE,null,e);
        }

    }


    //SQL methods and functions

    public void addUser(User u){
        String sqlAdd = "insert into users values ( ?, ? )";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlAdd);
            preparedStatement.setString(1,u.getName());
            preparedStatement.setString(2,u.getEmail());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void allUser(){
        String sqlQuery = "select * from users";

        try {
            ResultSet rs = cStatement.executeQuery(sqlQuery);

            while(rs.next()){
                System.out.println(rs.getString(1)+" | "+ rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showUsersMeta(){
        String sql = "select * from users";

        try {
            ResultSet rs = cStatement.executeQuery(sql);
            ResultSetMetaData rsmt = rs.getMetaData();

            System.out.println(rsmt.getColumnCount());
            System.out.println(rsmt.getColumnName(1) +" | "+ rsmt.getColumnName(2));


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // function
    public List<User> getAllUsers(){
        String sql = "select * from users";
        List<User> list = null;

        try {
            ResultSet sr = cStatement.executeQuery(sql);
            list = new ArrayList<>();

            while(sr.next()){
                User u = new User(sr.getString(1),15, sr.getString("email"));
                list.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


}
