public class LoginCheck {
    public static void main(String[] args) {
        String username = "Sharon";
        String password = "411630097";

        String inputUsername = "Sharon";
        String inputPassword = "411630097";

        boolean loginSuccess = username.equals(inputUsername) && password.equals(inputPassword);

        System.out.println("Login success: " + loginSuccess);
    }
    
}
