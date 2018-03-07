/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    , @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id")
    , @NamedQuery(name = "Message.findBySendtime", query = "SELECT m FROM Message m WHERE m.sendtime = :sendtime")})
public class Message implements Serializable {

    @Lob
//    @Size(max = 65535)
    @Column(name = "place")
    private String place;
    @Lob
//    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Column(name = "isTask")
    private Boolean isTask;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "sendtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendtime;
    @Lob
//    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Lob
//    @Size(max = 65535)
    @Column(name = "status")
    private String status;
    @Lob
//    @Size(max = 65535)
    @Column(name = "details")
    private String details;
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department departmentId;
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users senderId;

    public Message() {
    }

    public Message(Integer id) {
        this.id = id;
    }

    public Message(Integer id, Date sendtime) {
        this.id = id;
        this.sendtime = sendtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Message[ id=" + id + " ]";
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsTask() {
        return isTask;
    }

    public void setIsTask(Boolean isTask) {
        this.isTask = isTask;
    }
    
}
