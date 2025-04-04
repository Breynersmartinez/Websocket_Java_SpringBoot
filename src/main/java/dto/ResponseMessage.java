package dto;

import lombok.Data;


/**
 * DTO para mensajes de respuesta.
 */
 
 // Lombok - genera getters, setters, toString, etc.
 @Data  
public class ResponseMessage {
    private String content;

     // constructores
    public ResponseMessage() {
    }

    public ResponseMessage(String content) {
        this.content = content;
    }
}
