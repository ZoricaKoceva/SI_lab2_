import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class SILab2Test {

    // defining the conditions for program to successfully pass tests
    private String specialCharacters="!#$%&'()*+,-./:;<=>?@[]^_`{|}";
    private String digit="0123456789";
    private String upperCase="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final SILab2 testing = new SILab2();
    private List<String> listOfUserNames(String... allUserNames) {
        return new ArrayList<>(Arrays.asList(allUserNames));
    }


    @Test
    public void EveryStatement() {
        System.out.println("Testing the functionality of given function");

        // initializing user, there is given user parameter in function
        User user = new User(null,null,null);
        assertFalse("empty null parameters, nothing's given user has null value", testing.function(null,null));

        user = new User("Zorica",null,null);
        assertFalse("only username entered", testing.function(user,null));

        user = new User("Zorica","*Z#$1999","zorica.koceva@students.finki.ukim.mk");
        assertTrue("matching",testing.function(user,null));
        assertEquals(true,testing.function(user,null));

        user=new User("Zorica","*Zorica","zorica.koceva@students.finki.ukim.mk");
        assertFalse("password doesn't contain number, found lower case, less then 8 characters",testing.function(user,null));

        user = new User("Zorica","Zorica09","zorica.koceva@students.finki.ukim.mk");
        assertFalse(false);
        assertFalse(user.getPassword().contains(specialCharacters));
        assertFalse("password doesn't contain special character, found lowercase",testing.function(user, listOfUserNames()));

        user=new User("Zorica","1999","zorica.koceva@students.finki.ukim.mk");
        assertFalse(user.getPassword().contains(specialCharacters) && user.getPassword().contains(upperCase) && user.getPassword().length()>=8);
        assertFalse("password contain only digits, less or equal than 8",testing.function(user,null));

        user=new User("Zorica","!%^#*$","zorica.koceva@students.finki.ukim.mk");
        assertFalse(user.getPassword().contains(digit) && user.getPassword().contains(upperCase) && user.getPassword().length()>=8);
        assertFalse("password contain only special characters, less or equal than 8",testing.function(user,null));

        user=new User("Zorica","zoricakoceva","zorica.koceva@students.finki.ukim.mk");
        assertFalse(user.getPassword().contains(specialCharacters) && user.getPassword().contains(digit));
        assertFalse("password contain only special characters, less or equal than 8",testing.function(user,null));

    }


    @Test
    public void EveryPath() {
        /**
         * From Control Flow Graph we can choose paths which will be possible to pass lines in code
         * Also there are impossible paths from the cases that code contains:
         * ex. if everything is OK with our given password and passes all cases and loops,
         * we can't expect from the program to finish with false output
         *
         * It's clear that some paths are repeating, but here are some of them that should be happen
         *
         * Author: ..:Zorica~Koceva:..
         */

        //1,2-3-4-5-6-7-8-9.1-9.2-10-11-12-13-14-15-9.3-9.2-16-17-19
        User user = new User("Zorica", "*Z#$1999", "zorica.koceva@students.finki.ukim.mk");
//        assertTrue("matching", testing.function(user, null));
        assertEquals(true, testing.function(user, null));

        //1,2-3-18-19
        user = new User(null, null, null);
        assertFalse("empty null parameters, nothing's given user has null value", testing.function(null, null));

        //1,2-3-4-18-19
        user = new User("Zorica", null, null);
        assertFalse("only usernme entered", testing.function(null, null));

        //1,2-3-4-5-6-7-18-19
        user = new User("Zorica", "*Zorica", "zorica.koceva@students.finki.ukim.mk");
        assertFalse("password doesn't contain number, found lowercase, less then 8 characters", testing.function(user, null));
        assertFalse(user.getPassword().contains(digit) && user.getPassword().contains(upperCase) && user.getPassword().length() >= 8);

        //1,2-3-4-5-6-7-18-19
        user = new User("Zorica", "^Zorica09", "zorica.koceva@students.finki.ukim.mk");
        assertFalse(user.getPassword().contains(upperCase));
        assertFalse("password contain lowercase", testing.function(user, listOfUserNames()));

        //1,2-3-4-5-6-7-18-19
        user = new User("Zorica", "1999", "zorica.koceva@students.finki.ukim.mk");
        assertFalse(user.getPassword().contains(specialCharacters) && user.getPassword().contains(upperCase) && user.getPassword().length() >= 8);
        assertFalse("password contain only digits, no special character and uppercase, less or equal than 8", testing.function(user, null));

        //1,2-3-4-5-6-7-8-9.1-9.2-10-12-14-15-9.3-9.2-16-18-19
        user = new User("Zorica", "!%^#*$&!", "zorica.koceva@students.finki.ukim.mk");
        assertFalse(user.getPassword().contains(digit) && user.getPassword().contains(upperCase));
        assertFalse("password contain only special characters", testing.function(user, null));

        //1,2-3-4-5-6-7-8-9.1-9.2-10-11-12-14-9.3-9.2-(10-12-13-14-9.3-9.2)-16-18-19
        user = new User("Zorica", "0ZORICA7", "zorica.koceva@students.finki.ukim.mk");
        assertFalse(user.getPassword().contains(specialCharacters));
        assertFalse("password contain only special characters, less or equal than 8", testing.function(user, null));

        //1,2-3-4-5-6-7-8-9.1-9.2-10-12-14-15-9.3-9.2-(10-12-13-14-9.3-9.2)-16-18-19
        user = new User("Zorica", "(ZO&!C@)", "zorica.koceva@students.finki.ukim.mk");
        assertFalse(user.getPassword().contains(digit));
        assertFalse("password doesn't contain digit", testing.function(user, null));

        //1,2–3-4-5-6-7-8-9.1-9.2-10-11-12-13-14-15- 9.3-9.2-16-18-19
        /**
         * This is example of impossible path
         *
         * Also it's impossible to miss a digit, upper case or special character and program to enter
         * in contition and give true to function
         */

        //1,2-3-4-5-6-7-8-9.1-9.2-10-12-14-15-9.3-9.2-(10-12-13-14-9.3-9.2)-16-17-19
        /**
         * Impossible path
         * Here in password is missing digit
         */

        //1,2-3-4-5-6-7-8-9.1-9.2-10-11-12-14-9.3-9.2-(10-12-13-14-9.3-9.2)-16-17-19
        /**
         * Impossible path
         * Here in password is missing special character
         */

        //1,2-3-4-5-6-7-8-9.1-9.2-10-11-12-14-15-9.3-9.2-(10-12-14-15-9.3-9.2)-16-17-20
        /**
         * Impossible path
         * Here in password is missing special upperCase
         */
    }
}