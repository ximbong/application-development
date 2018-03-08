package Database;

import Database.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-08T07:01:05")
@StaticMetamodel(Announcement.class)
public class Announcement_ { 

    public static volatile SingularAttribute<Announcement, Users> creatorId;
    public static volatile SingularAttribute<Announcement, String> description;
    public static volatile SingularAttribute<Announcement, Integer> id;
    public static volatile SingularAttribute<Announcement, Date> timemade;
    public static volatile SingularAttribute<Announcement, String> title;

}