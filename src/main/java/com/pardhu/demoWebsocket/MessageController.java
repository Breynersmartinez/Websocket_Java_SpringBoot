package com.pardhu.demoWebsocket;

import dto.Message;
import dto.ResponseMessage;
import java.security.Principal;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;



/**
 * Controlador que maneja los mensajes entrantes a través de WebSocket.
 */
@Controller
public class MessageController {




   /**
   * Maneja mensajes públicos enviados al destino "/ws/message".
   * Los envía a todos los suscriptores de "/receive/message".
   */
  @MessageMapping("/message")
  @SendTo("/receive/message") // Especifica dónde enviar la respuesta (broadcast)
  public ResponseMessage getMessage(final Message message) throws InterruptedException {
    Thread.sleep(1000); /* Simula el procesamiento del mensaje */
    System.out.println("Mensaje recibido");

    /*  
    * Escapa contenido HTML para prevenir XSS: 
     * El escape HTML es una técnica que previene 
      los ataques XSS (Cross Site Scripting)
      inyectando código malicioso en páginas web
    */
    return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
  }




   /**
   * Maneja mensajes privados enviados al destino "/ws/private-message".
   * Los envía solo al usuario especificado en "/receive/private-message".
   */

   
  @MessageMapping("/private-message") // Mapea mensajes a métodos basados en su destino
  
  @SendToUser("/receive/private-message") // Especifica dónde enviar la respuesta (usuario específico)
  
  public ResponseMessage getPrivateMessage(final Message mess, final Principal principal) throws InterruptedException {
    
    /* Simula el procesamiento del mensaje  */
    Thread.sleep(1000);
    System.out.println(" Mensaje privado recibido "+principal.getName());
    return new ResponseMessage(HtmlUtils.htmlEscape("Enviar mensaje personal al usuario "+principal.getName()+": "+ mess.getMessageContent()));
  }

}
