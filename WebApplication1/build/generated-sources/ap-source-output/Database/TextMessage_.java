package Database;

import Database.Message;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T14:35:29")
@StaticMetamodel(TextMessage.class)
public class TextMessage_ { 

    public static volatile SingularAttribute<TextMessage, Integer> messageId;
    public static volatile SingularAttribute<TextMessage, Message> message;
    public static volatile SingularAttribute<TextMessage, String> content;

}