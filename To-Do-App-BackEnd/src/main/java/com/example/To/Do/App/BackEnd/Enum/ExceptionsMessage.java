package com.example.To.Do.App.BackEnd.Enum;

public enum ExceptionsMessage {
    USER_NOT_FOUND("User not found"),
    TASK_NOT_FOUND("Task not found !"),
    USER_INPUT_NOT_VALID("User Input Not Valid ! "),
    TASK_INPUT_NOT_VALID("Task Input Not Valid !"),
    INTERNAL_SERVER_ERROR("Error server"),
    USER_NOT_AUTHORIZED("Non authorized"),
    EMAIL_ALREADY_EXIST("Mail adress exist"),
    EMAIL_INVALID("Mail adress invalid."),
    FIRSTNAME_INVALID("FirstName  invalid . It has to contain characters."),
    LASTNAME_INVALID("LastName invalid, It has to contain characters."),
    PASSWORD_MATCH_ERROR("Password incorrect ."),
    PASSWORD_LENGTH_ERROR("Password has to contain at least 8 characters."),
    PHONE_INVALID("Phone number Invalid."),
    ROLE_ALREADY_EXIST("This role already exist");
    private final String msg;
    ExceptionsMessage(String msg){
        this.msg = msg;
    }
    public String getMessage(){
        return this.msg;
    }
}
