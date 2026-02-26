package org.stdsec.controller.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {
    // vide, sert juste à définir le prefix global
}