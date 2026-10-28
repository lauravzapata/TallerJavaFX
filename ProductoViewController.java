package co.edu.uniquindio.poo.empresajfx.viewController;

import co.edu.uniquindio.poo.empresajfx.App;
import co.edu.uniquindio.poo.empresajfx.controller.ProductoController;
import co.edu.uniquindio.poo.empresajfx.model.Producto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProductoViewController {

    ProductoController productoController;
    ObservableList<Producto> listProductos = FXCollections.observableArrayList();
    Producto selectedProducto;

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private Button btnAgregarProducto;
    @FXML
    private Button btnActualizarProducto;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnVolver;
    @FXML
    private TableView<Producto> tblListProducto;
    @FXML
    private TableColumn<Producto, String> tbcCodigo;
    @FXML
    private TableColumn<Producto, String> tbcNombre;
    @FXML
    private TableColumn<Producto, String> tbcPrecio;

    private App app;

    @FXML
    void onAgregarProducto() { agregarProducto(); }

    @FXML
    void onActualizarProducto() { actualizarProducto(); }

    @FXML
    void onLimpiar() { limpiarSeleccion(); }

    @FXML
    void onEliminar() { eliminarProducto(); }

    @FXML
    void onVolver() { app.openViewPrincipal(); }

    @FXML
    void initialize() {
        productoController = new ProductoController(App.empresa);
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerProductos();
        tblListProducto.getItems().clear();
        tblListProducto.setItems(listProductos);
        listenerSelection();
    }

    private void initDataBinding() {
        tbcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
    }

    private void obtenerProductos() {
        listProductos.addAll(productoController.obtenerListaProductos());
    }

    private void listenerSelection() {
        tblListProducto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedProducto = newSelection;
            mostrarInformacionProducto(selectedProducto);
        });
    }

    private void mostrarInformacionProducto(Producto producto) {
        if (producto != null) {
            txtCodigo.setText(producto.getCodigo());
            txtNombre.setText(producto.getNombre());
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
        }
    }

    private void agregarProducto() {
        Producto producto = buildProducto();
        if (producto == null) {
            return;
        }
        if (productoController.crearProducto(producto)) {
            listProductos.add(producto);
            limpiarCamposProducto();
        } else {
            mostrarAlerta("Ya existe un producto con ese código.");
        }
    }

    private Producto buildProducto() {
        try {
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            return new Producto(txtCodigo.getText().trim(), txtNombre.getText().trim(), precio);
        } catch (NumberFormatException e) {
            mostrarAlerta("El precio debe ser un número.");
            return null;
        }
    }

    private void eliminarProducto() {
        if (selectedProducto != null && productoController.eliminarProducto(selectedProducto.getCodigo())) {
            listProductos.remove(selectedProducto);
            limpiarSeleccion();
        }
    }

    private void actualizarProducto() {
        Producto nuevo = buildProducto();
        if (selectedProducto != null && nuevo != null &&
                productoController.actualizarProducto(selectedProducto.getCodigo(), nuevo)) {
            tblListProducto.refresh();
            limpiarSeleccion();
        }
    }

    private void limpiarSeleccion() {
        tblListProducto.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void limpiarCamposProducto() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
    }

    private void mostrarAlerta(String mensaje) {
        new Alert(Alert.AlertType.WARNING, mensaje).showAndWait();
    }

    public void setApp(App app) {
        this.app = app;
    }
}