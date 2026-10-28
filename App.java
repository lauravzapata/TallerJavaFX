package co.edu.uniquindio.poo.empresajfx;

import co.edu.uniquindio.poo.empresajfx.model.Cliente;
import co.edu.uniquindio.poo.empresajfx.model.Empresa;
import co.edu.uniquindio.poo.empresajfx.model.Producto;
import co.edu.uniquindio.poo.empresajfx.viewController.ClienteViewController;
import co.edu.uniquindio.poo.empresajfx.viewController.PrimaryController;
import co.edu.uniquindio.poo.empresajfx.viewController.ProductoViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;
    public static Empresa empresa = new Empresa("UQ");

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion de Clientes y Productos");
        inicializarData();
        openViewPrincipal();
    }

    public void openViewPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("primary.fxml"));
            VBox rootLayout = (VBox) loader.load();
            PrimaryController primaryController = loader.getController();
            primaryController.setApp(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public void openCrudCliente() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("crudCliente.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            ClienteViewController clienteViewController = loader.getController();
            clienteViewController.setApp(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openCrudProducto() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("crudProducto.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            ProductoViewController productoViewController = loader.getController();
            productoViewController.setApp(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inicializarData() {
        empresa.agregarCliente(new Cliente("12233", "juan", "apellido"));
        empresa.agregarProducto(new Producto("P001", "Teclado", 85000));
    }
}