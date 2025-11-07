module main.dusza {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires MaterialFX;
    requires atlantafx.base;
    requires javafx.graphics;

    opens main.dusza to javafx.fxml;
    opens main.dusza.gameElements to javafx.fxml, org.junit.platform.commons;
    exports main.dusza;
    opens main.dusza.render to javafx.fxml;
}