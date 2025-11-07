module main.dusza {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires MaterialFX;
    requires main.dusza;

    opens main.dusza to javafx.fxml;
    exports main.dusza;
}