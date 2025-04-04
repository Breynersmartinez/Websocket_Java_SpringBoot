package com.pardhu.demoWebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * Configuración principal para WebSocket y STOMP.
 * STOMP: Protocolo simple de mensajería sobre WebSocket
 */
@Configuration
@EnableWebSocketMessageBroker
public class WSConfig implements WebSocketMessageBrokerConfigurer {


    /**
   * Configura el broker de mensajes.
   * Broker: Intermediario que gestiona la distribución de mensajes
   */
  @Override
  public void configureMessageBroker(final MessageBrokerRegistry registry){
   
    /*
     Habilita un broker simple en memoria para enviar mensajes a clientes
     */
    registry.enableSimpleBroker("/receive"); // "recibe"
   
    /* 
     *  Prefijo para destinos manejados por @MessageMapping
     */
    registry.setApplicationDestinationPrefixes("/ws"); //"WebSocket"
  }



  
  /**
   * Registra los endpoints WebSocket.
   */
  @Override
  public void registerStompEndpoints(final StompEndpointRegistry registry) {
    registry.addEndpoint("/websocket") // Endpoind principal
        .setHandshakeHandler(new ClientHandshakeHandler()) // Usa nuestro handler

        .withSockJS(); // Habilita fallback SockJS
        /*
         * SockJS: Biblioteca que proporciona
         *  fallback para navegadores sin soporte WebSocket
         */
  }
}
