package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-07T22:04:50")
@StaticMetamodel(LibraryUser.class)
public class LibraryUser_ { 

    public static volatile SingularAttribute<LibraryUser, String> firstName;
    public static volatile SingularAttribute<LibraryUser, String> lastName;
    public static volatile SingularAttribute<LibraryUser, String> password;
    public static volatile SingularAttribute<LibraryUser, Boolean> isBlocked;
    public static volatile SingularAttribute<LibraryUser, Long> id;
    public static volatile SingularAttribute<LibraryUser, Boolean> isAdmin;
    public static volatile SingularAttribute<LibraryUser, String> email;
    public static volatile SingularAttribute<LibraryUser, String> username;

}