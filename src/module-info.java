module monopoly_team01 {
    requires java.desktop;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.params;

    exports app.controller;
    exports app.engine;
    exports app.engine.space;
    exports app.views.IViews;
    exports app.views.popups;
    exports app.views.spaces;
    exports app.views.utility;
}