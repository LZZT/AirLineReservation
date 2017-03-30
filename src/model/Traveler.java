package model;
import java.util.Set;
/**
 * Created by liweihao on 3/9/17.
 */
public class Traveler {

    private String lastname;
    private String firstname;
    private String dob;
    private String email;
    private String phone;
    private String gender;
    private Set<Customer> customerSet;

//    public void setCustomer(Customer customer){
//        this.customerSet.add(customer);
//    }


    public Traveler() {

    }

    public  Traveler(String phone){
        this.phone=phone;
    }
//    @ManyToMany(cascade=CascadeType.ALL)
//    @JoinTable(
////      设置中间表名
//            name="CustomerOwnsTraveler",
////      指定当前对象的外键,本表在中间表的外键名称
//            joinColumns={@JoinColumn(name="sid")},
////      指定关联对象的外键,另一个表在中间表的外键名称。
//            inverseJoinColumns={@JoinColumn(name="tid")}
//    )

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }

    public String getLastname() {return lastname;}

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getDob() { return dob; }

    public void setDob(String dob) { this.dob = dob; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }
}
