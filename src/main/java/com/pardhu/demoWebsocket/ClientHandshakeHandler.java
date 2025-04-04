package com.pardhu.demoWebsocket;

import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/*
* Maneja el proceso de handshake que es (El apreton de manos)
  para las conexiones de los websockets
* Asigna un Id unico a cada cliente que se conecta
*/
public class ClientHandshakeHandler extends DefaultHandshakeHandler {

  private final Logger logger = LoggerFactory.getLogger(ClientHandshakeHandler.class);

  @Override
  protected Principal determineUser(ServerHttpRequest req, WebSocketHandler weHandler, Map<String, Object> attributes) {

    /* Genera el Id unico para el usuario */
    final String randId = UUID.randomUUID().toString();


    /* Registra la info de la conexion */
    logger.info("{}",attributes.get("name"));
    logger.info("User opened client unique ID {}, ipAddress {}",randId,req.getRemoteAddress());

    /* Retorna un usuario principal con el Id generado */
    return new UserPrincipal(randId);
  }

}
