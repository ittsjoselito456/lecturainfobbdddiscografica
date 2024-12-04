//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.*;
public class Lectura_DataBaseMetaData {
    private static final String user = "postgres";
    private static final String password = "root";
    private static final String url = "jdbc:postgresql://localhost:5432/DISCOGRAFICA";

    public static void leerDataBaseMetaData(String user, String password, String url) {
    try {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado");
        }
        Connection con = null;
        con = DriverManager.getConnection(url, user, password);

        DatabaseMetaData dbmd = con.getMetaData();
        System.out.println("Base de datos" + dbmd.getDatabaseProductName());
        System.out.println("Version de la bbdd: " + dbmd.getDatabaseProductVersion());
        System.out.println("Driver de la bbdd: " + dbmd.getDriverName());

        ResultSet rs = dbmd.getTables(null, null, null, new String[] { "TABLE" });
        System.out.println("Tablas de bbdd:");

        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            System.out.println("-" + tableName);

            ResultSet columnas = dbmd.getColumns(null, null, tableName, null);
            System.out.println("Columnas:");
            while (columnas.next()) {
                String columnName = columnas.getString("COLUMN_NAME");
                String columnType = columnas.getString("TYPE_NAME");
                System.out.println("- " + columnName + " - " + columnType);
            }
        }
    }catch (SQLException e) {
        System.out.println("Error al conectar con la base de datos" + e.getMessage());
    }
    }

    public static void main(String[] args) {
        leerDataBaseMetaData(user , password, url);
    }

}