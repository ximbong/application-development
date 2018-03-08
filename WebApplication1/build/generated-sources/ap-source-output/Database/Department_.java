package Database;

import Database.Message;
import Database.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-08T03:02:42")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile CollectionAttribute<Department, Message> messageCollection;
    public static volatile SingularAttribute<Department, String> name;
    public static volatile SingularAttribute<Department, Integer> id;
    public static volatile CollectionAttribute<Department, Users> usersCollection;

}