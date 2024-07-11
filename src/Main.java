import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/ESFOT";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Main app = new Main();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la edad del estudiante: ");
        int edad = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese la nota 1: ");
        double nota1 = scanner.nextDouble();

        System.out.print("Ingrese la nota 2: ");
        double nota2 = scanner.nextDouble();

        Estudiante estudiante = new Estudiante(nombre, edad, nota1, nota2);
        app.insertarEstudiante(estudiante);
    }

    public void insertarEstudiante(Estudiante estudiante) {
        String sql = "INSERT INTO Estudiante (nombre, edad, nota1, nota2) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, estudiante.getNombre());
            pstmt.setInt(2, estudiante.getEdad());
            pstmt.setDouble(3, estudiante.getNota1());
            pstmt.setDouble(4, estudiante.getNota2());
            pstmt.executeUpdate();
            System.out.println("Estudiante insertado con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error al insertar el estudiante en la base de datos: " + e.getMessage());
        }
    }

    public Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Conectado a la base de datos");
        return conn;
    }
}

