package services;
import DAO.UsersDAO;
import models.Reimbursments;
import models.Users;

import java.util.List;

public class UsersService {
    UsersDAO usersDAO;

    public UsersService(UsersDAO usersDAO)
    {
        this.usersDAO  = usersDAO;
    }

    public List<Users> getAllUsers()
    {
        return usersDAO.getALLUsers();
    }

    public List<Reimbursments> getAllReimbursments()
    {
        return usersDAO.getAllReimbursments();
    }

    public boolean login(String username,String user_password)
    {return usersDAO.login(username,user_password);}

    public Integer getUserType(String username, String user_password)
    {return usersDAO.getUserType(username,user_password);}

    public String getUserNameByid(Integer user_id)
    {return usersDAO.getUserNameByid(user_id);}

    public List<Users> getOneUser(String username)
    {return usersDAO.getOneUser(username);}

    public List<Reimbursments> getEmplReimbursments(Integer author)
    {return usersDAO.getEmplReimbursments(author);}

    public String reimbStatusId(Integer id)
    {return usersDAO.reimbStatusId(id);}

    public String reimbStatus(Integer id)
    {return usersDAO.reimbStatus(id);}

    public void approveReimb(Integer reimb_id, Integer resolver)
    {usersDAO.approveReimb(reimb_id,resolver);}

    public void denyReimb(Integer reimb_id, Integer resolver)
    {usersDAO.denyReimb(reimb_id,resolver);}

    public void createReimb(Integer id, Reimbursments reimb)
    {usersDAO.createReimb(id,reimb);}
}
