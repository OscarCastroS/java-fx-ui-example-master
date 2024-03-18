module mx.arturogil.helloworldfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens mx.arturogil.helloworldfx to javafx.fxml;
    exports mx.arturogil.helloworldfx;
}