package Database;

import Database.Coversation;
import Database.TaskMessage;
import Database.TextMessage;
import Database.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T14:35:29")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, TextMessage> textMessage;
    public static volatile SingularAttribute<Message, Users> senderId;
    public static volatile SingularAttribute<Message, Coversation> coversationId;
    public static volatile SingularAttribute<Message, TaskMessage> taskMessage;
    public static volatile SingularAttribute<Message, Integer> id;
    public static volatile SingularAttribute<Message, Boolean> isGroupChat;
    public static volatile SingularAttribute<Message, Date> timestamp;

}