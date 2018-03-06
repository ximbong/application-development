package Database;

import Database.Message;
import Database.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T14:35:29")
@StaticMetamodel(Coversation.class)
public class Coversation_ { 

    public static volatile CollectionAttribute<Coversation, Message> messageCollection;
    public static volatile SingularAttribute<Coversation, Integer> id;
    public static volatile CollectionAttribute<Coversation, Users> usersCollection;

}