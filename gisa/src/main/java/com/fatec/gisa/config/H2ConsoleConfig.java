package com.fatec.gisa.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2ConsoleConfig {

    private static final Logger logger = LoggerFactory.getLogger(H2ConsoleConfig.class);

    @Bean(destroyMethod = "stop")
    public Server h2WebConsoleServer() throws SQLException {
        Server server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        try {
            server.start();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 90061) {
                logger.warn("Port 8082 is already in use. Starting H2 web console on a random free port.");
                server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "0");
                server.start();
            } else {
                throw ex;
            }
        }
        logger.info("H2 web console started on port {}", server.getPort());
        return server;
    }
}
