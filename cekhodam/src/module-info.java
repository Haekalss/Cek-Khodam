module GenNamaKarakter {
	requires javafx.controls;
	requires java.base;
	requires java.sql;
	requires java.logging;
	requires java.prefs;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}
