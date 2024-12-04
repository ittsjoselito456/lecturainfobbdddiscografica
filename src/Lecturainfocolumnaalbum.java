//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.*;
public class Lecturainfocolumnaalbum {
    private static final String user = "postgres";
    private static final String password = "root";
    private static final String url = "jdbc:postgresql://localhost:5432/DISCOGRAFICA";

    public static void leerinfocolumnalbums(String user, String password, String url) {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver no encontrado");
            }
            Connection con = null;
            con = DriverManager.getConnection(url, user, password);

            DatabaseMetaData dbmd = con.getMetaData();
            ResultSet rs = dbmd.getColumns(null, null, "albums",null);

            System.out.println("Info sobre les columnes de albums");
            System.out.println("-----------------------------");
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String columnType = rs.getString("TYPE_NAME");
                String columnSize = rs.getString("COLUMN_SIZE");
                String esnula = rs.getString("IS_NULLABLE");
                String esautoincrement = rs.getString("IS_AUTOINCREMENT");
                System.out.printf("Nom: %s, Tipus: %s, Mida: %s, Es nula: %s, Autoincremental: %s%n", columnName, columnType, columnSize, esnula, esautoincrement);
            }
        }catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        leerinfocolumnalbums(user , password, url);
    }

}