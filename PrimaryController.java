package co.edu.uniquindio.poo.empresajfx.viewController;

import co.edu.uniquindio.poo.empresajfx.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    App app;

    @FXML
    private Button primaryButton;

    @FXML
    private Button btnProductos;

    @FXML
    void onOpenCrudCliente() {
        app.openCrudCliente();
    }

    @FXML
    void onOpenCrudProducto() {
        app.openCrudProducto();
    }

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    void initialize() {
    }
}