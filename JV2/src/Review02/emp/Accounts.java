package Review02.emp;

public class Accounts extends Staffs{
    private String user;
    private String password;

    public Accounts(String user, String password){
        this.user = user;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public Accounts(){
        this.user = "";
        this.password = "";
    }


    @Override
    void screenInfo() {
        System.out.printf("Accounts {user = %15s, pass = %10s}\n", this.user, this.password);
    }
}
