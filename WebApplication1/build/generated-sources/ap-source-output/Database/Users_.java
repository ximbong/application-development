package Database;

import Database.Message;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-03T21:00:19")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, Boolean> isBusy;
    public static volatile SingularAttribute<Users, String> role;
    public static volatile CollectionAttribute<Users, Message> messageCollection;
    public static volatile SingularAttribute<Users, String> phonenumber;
    public static volatile SingularAttribute<Users, Boolean> isOnline;
    public static volatile SingularAttribute<Users, Boolean> isIdle;
    public static volatile SingularAttribute<Users, Boolean> isOffline;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> username;

}