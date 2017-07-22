import model.*;

import java.sql.*;

public class MySQLAccess {
    private Connection connection;

    private String name;
    private float rate;
    private String targetMuscle;
    private String instruction;
    private String equipment;
    private String type;

    public MySQLAccess() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    }

    public static void main(String[] args) throws Exception {
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            System.out.println("Good to go");
//        } catch (Exception E) {
//            System.out.println("JDBC Driver error");
//        }
        MySQLAccess hello = new MySQLAccess();
        hello.connectToDB();
        hello.executeSQL();
    }

    private void displaySQLErrors(SQLException e) {
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }

    public void connectToDB() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/usergymmaster1000?user=root&password=Britocom.123");
        } catch (SQLException e) {
            displaySQLErrors(e);
        }
    }

    public void executeSQL() {
        try {
            Statement statement = connection.createStatement();
            ResultSet ex = statement.executeQuery(
                    "SELECT * FROM ex_registry");
            while (ex.next()) {
                name = ex.getString(2);
                rate = ex.getFloat(3);
                targetMuscle = ex.getString(4);
                equipment = ex.getString(5);
                type = ex.getString(6);
                instruction = ex.getString(7);

                Exercise e = new Exercise(name, rate, targetMuscle, equipment, type, instruction);
                System.out.println(e.toString());
            }
            ex.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            displaySQLErrors(e);
        }
    }
}
