package com.pardhu.demoWebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Anotación que combina:
/*
 * @Configuration: Clase de configuración

* @EnableAutoConfiguration: Configuración automática de Spring Boot

* @ComponentScan: Escaneo de componentes en el paquete actual y subpaquetes 
 */

public class DemoWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebsocketApplication.class, args);
	}

}
