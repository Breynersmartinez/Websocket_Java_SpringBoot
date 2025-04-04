package com.pardhu.demoWebsocket;

import dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Servicio para enviar mensajes a través de WebSocket.
 */
@Service
public class WSService {

  private final SimpMessagingTemplate messagingTemplate;

  @Autowired
  public WSService(SimpMessagingTemplate messagingTemplate){
    this.messagingTemplate = messagingTemplate;
  }

   /**
   * Envía un mensaje público a todos los suscriptores.
   */
  public void sendMessage(final String message){
    ResponseMessage res = new ResponseMessage(message);

    //convertAndSend: Envía mensaje a un destino específico
    messagingTemplate.convertAndSend("/receive/message",res);
  }

   /**
   * Envía un mensaje privado a un usuario específico.
   */
  public void sendPrivateMessage(final String message, final String id){
    ResponseMessage res = new ResponseMessage(message);

    // convertAndSendToUser: Envía mensaje a un usuario específico
    messagingTemplate.convertAndSendToUser(id,"/receive/private-message", res);
   }

   // metodo para majeo de notificaciones
   public void sendNotification(final String message) {
     ResponseMessage res = new ResponseMessage(message);
     messagingTemplate.convertAndSend("/receive/global-notification",res);
   }
  public void sendPrivateNotification(final String message,final String id) {
    ResponseMessage res = new ResponseMessage(message);
    messagingTemplate.convertAndSendToUser(id,"/receive/private-notification",res);
  }

}
