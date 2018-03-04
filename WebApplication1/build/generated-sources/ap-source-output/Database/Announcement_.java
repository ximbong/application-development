package Database;

import Database.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-04T20:22:03")
@StaticMetamodel(Announcement.class)
public class Announcement_ { 

    public static volatile SingularAttribute<Announcement, Users> creatorId;
    public static volatile SingularAttribute<Announcement, String> description;
    public static volatile SingularAttribute<Announcement, Integer> id;
    public static volatile SingularAttribute<Announcement, String> title;

}