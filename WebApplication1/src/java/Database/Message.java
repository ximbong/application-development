/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PLH
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")
    , @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id")})
public class Message implements Serializable {

    @Column(name = "timestamp")
    private Date timestamp;
    
    @Column(name = "isGroupChat")
    private Boolean isGroupChat;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @OneToOne(mappedBy = "message")
    private TaskMessage taskMessage;
    
    @OneToOne(mappedBy = "message")
    private TextMessage textMessage;
    
    @JoinColumn(name = "coversation_id", referencedColumnName = "id")
    @ManyToOne
    private Coversation coversationId;
    
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    @ManyToOne
    private Users senderId;

    public Message() {
    }

    public Message(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskMessage getTaskMessage() {
        return taskMessage;
    }

    public void setTaskMessage(TaskMessage taskMessage) {
        this.taskMessage = taskMessage;
    }

    public TextMessage getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(TextMessage textMessage) {
        this.textMessage = textMessage;
    }

    public Coversation getCoversationId() {
        return coversationId;
    }

    public void setCoversationId(Coversation coversationId) {
        this.coversationId = coversationId;
    }

    public Users getSenderId() {
        return senderId;
    }

    public void setSenderId(Users senderId) {
        this.senderId = senderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id >0 ? id : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id <0 && other.id >0) || (this.id >0 && this.id != other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Message[ id=" + id + " ]";
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getIsGroupChat() {
        return isGroupChat;
    }

    public void setIsGroupChat(Boolean isGroupChat) {
        this.isGroupChat = isGroupChat;
    }
    
}
