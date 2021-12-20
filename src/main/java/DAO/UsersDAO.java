package DAO;

import models.Reimbursments;
import models.Users;

import java.util.List;

public interface UsersDAO {
    List<Users> getALLUsers();
    List<Reimbursments> getAllReimbursments();
    boolean login(String username, String user_password);
    Integer getUserType(String username, String user_password);
    String getUserNameByid(Integer user_id);
    List<Users> getOneUser(String username);
    List<Reimbursments> getEmplReimbursments(Integer author);
    String reimbStatusId(Integer id);
    String reimbStatus(Integer id);
    void approveReimb(Integer reimb_id, Integer resolver);
    void approveReimbT(Integer reimb_id, Integer resolver);
    void denyReimb(Integer reimb_id, Integer resolver);
    void denyReimbT(Integer reimb_id, Integer resolver);
    //Integer getReimbIdByName(String reimb);
    void createReimb(Integer id, Reimbursments reimb);
}
