module com.example.dictapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires cmu.time.awb;
    requires cmu.us.kal;
    requires en.us;
    requires freetts;
    requires org.json;

    opens com.example.dictapp to javafx.fxml;
    exports com.example.dictapp;
}