package COMS309.Final.WebSocket;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String username;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent")
    private Date sent = new Date();

    public Message(String username, String content){
        this.username = username;
        this.content = content;
    }

    public Message(){

    }

    public String getUsername() {
        return username;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent(){
        return content;
    }
}
