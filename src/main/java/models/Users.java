package models;

public class Users {
    private Integer user_id;
    private String username;
    private String user_password;
    private String first_name;
    private String last_name;
    private String email;
    private Integer role_id;

    Users()
    {}
    public Users(Integer user_id,String username,String user_password,String first_name,String last_name,String email,Integer role_id)
    {
        this.user_id = user_id;
        this.username = username;
        this.user_password = user_password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.role_id = role_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", user_password='" + user_password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", role_id=" + role_id +
                '}';
    }
}
