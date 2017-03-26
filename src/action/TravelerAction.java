package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Traveler;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by liweihao on 3/9/17.
 */
public class TravelerAction extends ActionSupport{


    private List<Traveler> travelerList;

    public List<Traveler> getTravelerList() {
        return travelerList;
    }

    public void setTravelerList(List<Traveler> travelerList) {
        this.travelerList = travelerList;
    }

    public String add() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("travelerList", travelerList);
        return SUCCESS;
    }


//    public static String RandomString(int length) {
//        String str = "0123456789";
//        Random random = new Random();
//        StringBuffer buf = new StringBuffer();
//        for (int i = 0; i < length; i++) {
//            int num = random.nextInt(10);
//            buf.append(str.charAt(num));
//        }
//        return buf.toString();
//    }


//    public void validateAdd(){
//        for (Traveler t: travelerList) {
//
//
//            if (null == t.getGender()) {
//                this.addActionError("Gender can not be empty");
//            }
//
//            if (null == t.getLastname() || t.getLastname().length() < 1) {
//                this.addActionError("Last Name can not be empty");
//            }
//
//            if (null == t.getLastname() || t.getLastname().length() < 1) {
//                this.addActionError("First Name can not be empty");
//            }
//
//            if (null == t.getPhone() || t.getPhone().length() != 10 || !isNumeric(t.getPhone())) {
//                this.addActionError("This is not a valid phone number");
//            }
//
//            if (!t.getEmail().contains("@") || !t.getEmail().split("@")[1].contains(".")) {
//                this.addActionError("Invalid email address");
//            }
//
//            try {
//                Date validDate = new Date(t.getDob());
//            } catch (Exception ex) {
//                this.addActionError("Invalid  Date");
//            }
//        }
//    }
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }



}
