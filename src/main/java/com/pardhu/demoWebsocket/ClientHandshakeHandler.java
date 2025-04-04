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
* Handshake: Proceso inicial de establecimiento de conexión WebSocket



* Asigna un Id unico a cada cliente que se conecta
*/
public class ClientHandshakeHandler extends DefaultHandshakeHandler {


   // Logger para registrar eventos
  private final Logger logger = LoggerFactory.getLogger(ClientHandshakeHandler.class);




    /*
   * Determina el usuario asociado a la conexión WebSocket.
   * 
   * @param req Objeto ServerHttpRequest con información de la solicitud HTTP
   * @param weHandler Manejador WebSocket
   * @param attributes Atributos de la sesión
   * @return Principal que identifica al usuario
   */

  @Override
  protected Principal determineUser(ServerHttpRequest req, WebSocketHandler weHandler, Map<String, Object> attributes) {

    /* Genera el Id unico para el usuario */
    final String randId = UUID.randomUUID().toString();


    /* Registra la info de la conexion */
    logger.info("{}",attributes.get("nombre"));
    logger.info("el Usuario abrió ID unica del cliente {}, direccion Ip {}",randId,req.getRemoteAddress());

    /* Retorna un usuario principal con el Id generado */
    return new UserPrincipal(randId);
  }

}
