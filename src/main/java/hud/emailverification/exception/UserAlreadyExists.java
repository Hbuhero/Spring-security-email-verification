package hud.emailverification.exception;

public class UserAlreadyExists extends RuntimeException{
    private String errorMsg;

    public UserAlreadyExists(String errorMsg){
        this.errorMsg = errorMsg;
    }
}
