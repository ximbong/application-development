package Database;

import Database.Message;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-04T20:22:03")
@StaticMetamodel(TaskMessage.class)
public class TaskMessage_ { 

    public static volatile SingularAttribute<TaskMessage, Integer> messageId;
    public static volatile SingularAttribute<TaskMessage, String> description;
    public static volatile SingularAttribute<TaskMessage, String> location;
    public static volatile SingularAttribute<TaskMessage, String> details;
    public static volatile SingularAttribute<TaskMessage, Message> message;

}