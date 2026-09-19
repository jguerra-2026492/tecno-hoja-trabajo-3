package main.java.com.javatesting.kinalproyect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto de entrada de la aplicación JavaFX.
 *
 * NOTA: src.dir=src en este proyecto Ant, y el build copia los recursos
 * conservando su ruta relativa a src.dir (excluye solo .java/.form).
 * Por eso el FXML, ubicado en src/main/resources/view/main-view.fxml,
 * termina en build/classes/main/resources/view/main-view.fxml y debe
 * cargarse con esa misma ruta absoluta de classpath.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/main-view.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Abarrotería Kinal - Consulta de productos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
