package Database;

import Database.Department;
import Database.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-07T13:31:21")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, Users> senderId;
    public static volatile SingularAttribute<Message, Boolean> isTask;
    public static volatile SingularAttribute<Message, Department> departmentId;
    public static volatile SingularAttribute<Message, String> description;
    public static volatile SingularAttribute<Message, String> details;
    public static volatile SingularAttribute<Message, String> place;
    public static volatile SingularAttribute<Message, Integer> id;
    public static volatile SingularAttribute<Message, Date> sendtime;
    public static volatile SingularAttribute<Message, String> content;
    public static volatile SingularAttribute<Message, String> status;

}