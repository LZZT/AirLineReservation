package util;


public class emailValidation {
    public static boolean emailValidate(String email){

        if(!email.contains("@")) return false;


        try{
            String s = email.split("@")[1];
            if(!s.contains(".")) return false;

        }catch(Exception ex){
            return false;

        }
        return true;
    }
}
