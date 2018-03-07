package Database;

import Database.Announcement;
import Database.Department;
import Database.Message;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-07T14:21:43")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> password;
    public static volatile CollectionAttribute<Users, Announcement> announcementCollection;
    public static volatile CollectionAttribute<Users, Message> messageCollection;
    public static volatile SingularAttribute<Users, Department> departmentId;
    public static volatile SingularAttribute<Users, String> phonenumber;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> username;
    public static volatile SingularAttribute<Users, Integer> statusCode;

}