package main.java.com.javatesting.kinalproyect.controller;

import main.java.com.javatesting.kinalproyect.database.DBConnection;
import main.java.com.javatesting.kinalproyect.model.Producto;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controlador de main-view.fxml.
 * Conecta la vista (TextField/Button/TableView/Label) con la base de datos vía JDBC.
 */
public class MainController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private Button btnBuscar;

    @FXML
    private Label lblResultado;

    @FXML
    private TableView<Producto> tablaResultado;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    /**
     * Se ejecuta automáticamente cuando el FXMLLoader termina de inyectar
     * los @FXML. Aquí se configuran las columnas de la tabla: cada columna
     * necesita saber de qué getter del modelo (Producto) sacar su valor.
     */
    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getCodigo()));
        colNombre.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));
        colPrecio.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getPrecio()));

        cargarProductos(""); // "" no filtra nada -> muestra todos los productos al abrir la app
    }

    @FXML
    private void handleBuscar() {
        cargarProductos(txtCodigo.getText());
    }

    /**
     * Consulta productos cuyo id_producto contenga "filtro" y llena la tabla.
     * filtro = "" trae todos los registros (LIKE '%%' matchea cualquier fila).
     */
    private void cargarProductos(String filtro) {
        String sql = "SELECT id_producto, nombre_producto, precio FROM productos WHERE id_producto LIKE ?";

        ObservableList<Producto> resultados = FXCollections.observableArrayList();

        // La conexión es un Singleton compartido por toda la app: no se cierra aquí,
        // solo el PreparedStatement/ResultSet de esta consulta puntual.
        Connection conn = DBConnection.getInstance().getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + filtro + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultados.add(new Producto(
                            rs.getString("id_producto"),
                            rs.getString("nombre_producto"),
                            rs.getDouble("precio")
                    ));
                }
            }

            tablaResultado.setItems(resultados);
            lblResultado.setText(resultados.isEmpty()
                    ? "Producto no encontrado."
                    : resultados.size() + " producto(s) encontrado(s).");

        } catch (SQLException e) {
            lblResultado.setText("Error de conexión: " + e.getMessage());
        }
    }
}

