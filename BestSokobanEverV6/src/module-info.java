module bestSokobanEverV6 {
    
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;
    requires java.base;
    requires java.desktop;
    requires java.logging;
	requires org.apache.commons.lang3;
	requires junit;
	
    opens sokodan;
    opens sokodan.MVCHighScore;
    opens sokodan.MVCStartScreen;
    opens sokodan.MVCMainGameScreen;
    opens sokodan.basic;
    opens sokodan.basic.Info;
    opens sokodan.basic.Location;
    opens test;
    opens sokodan.basic.Adapter;
    opens sokodan.basic.FactoryPattern;
    opens sokodan.basic.Singleton;
    opens sokodan.basic.Location.IteratorPattern;
}