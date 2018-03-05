/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PLH
 */
@Entity
@Table(name = "text_message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TextMessage.findAll", query = "SELECT t FROM TextMessage t")
    , @NamedQuery(name = "TextMessage.findByMessageId", query = "SELECT t FROM TextMessage t WHERE t.messageId = :messageId")})
public class TextMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "message_id")
    private Integer messageId;
    @Lob
//    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "message_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Message message;

    public TextMessage() {
    }

    public TextMessage(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TextMessage)) {
            return false;
        }
        TextMessage other = (TextMessage) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.TextMessage[ messageId=" + messageId + " ]";
    }
    
}
