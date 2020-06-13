import java.util.List;

/**
 *  SILab2 code group 4
 *  Author: Zorica Koceva
 *  Software Testing with jUnit Java Graddle
 */
class User {
    String username;
    String password;
    String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
}

public class SILab2 {

    public static boolean function(User user, List<String> allUsers) { //node _1
        String specialCharacters="!#$%&'()*+,-./:;<=>?@[]^_`{|}"; // node specialC _2
        if (user!=null) { // node user != null _3
            if (user.getUsername()!=null && user.getPassword()!=null) { // check usr/pass _4
                String password = user.getPassword(); // node pass _5
                String passwordLower = password.toLowerCase(); // node passlow _6
                if (!passwordLower.contains(user.getUsername().toLowerCase()) && password.length()>=8) { // node allLower _7
                    boolean digit = false, upper = false, special = false; // node boolsF _8
                    for (int i=0;i<password.length();i++) { //node/s checking.1 checking.2 checking.3 _9.1 _9.2 _9.3
                        if (Character.isDigit(password.charAt(i)))// node isDigit _10
                            digit = true; //node digitT _11
                        if (Character.isUpperCase(password.charAt(i)))// node isUpperC _12
                            upper = true; // node upperT _13
                        if (specialCharacters.contains(String.valueOf(password.charAt(i))))// node isSpecChar _14
                            special = true; // node specT _15
                    }
                    if (digit && upper && special)//node caseComplete _16
                        return true; // node accepted _17
                }
            }
        }
        return false; // node denied _18
    }//node done _19

    public static void main(String[] args) {
//        User user = new User("Zorica", "*ZORICA09" , "zorica.koceva@students.finki.ukim.mk");
//        System.out.println(function(user, null));
    }
}