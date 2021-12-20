package DAO;

import login.Cred;
import models.Reimbursments;
import models.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class UsersDAOImpl implements UsersDAO{
    String urlp;
    String usernamep;
    String passwordp;

    public UsersDAOImpl()
    {
        this.urlp = Cred.urlp;
        this.usernamep = Cred.usernamep;
        this.passwordp = Cred.passwordp;
    }

    public UsersDAOImpl(String urlp, String usernamep, String passwordp){
        this.urlp = urlp;
        this.usernamep = usernamep;
        this.passwordp = passwordp;
    }

    Logger logger = Logger.getLogger(UsersDAOImpl.class);
    @Override
    public List<Users> getALLUsers() {

        List<Users> tr = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="SELECT * FROM users;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                tr.add(new Users(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getInt(7)));
            }
        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }

        return tr;

    }

    @Override
    public List<Reimbursments> getAllReimbursments() {

        List<Reimbursments> tr = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="SELECT * FROM reimbursement;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                tr.add(new Reimbursments(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getInt(7),rs.getInt(8), rs.getInt(9),rs.getInt(10)));
                //System.out.println(tr);
            }
        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }

        return tr;
    }


    @Override
    public boolean login(String username, String user_password)
    {
        boolean flg=false;
        List<Users> tr = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="SELECT * FROM users WHERE username=? AND user_password=?;";
            //String sqlCheckidAccount ="SELECT * FROM users WHERE user_password=?;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setString(1,username);
            ps.setString(2,user_password);

            ResultSet rs = ps.executeQuery();
            //System.out.println(username);
            while(rs.next()) {
                flg = true;
                tr.add(new Users(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getInt(7)));
                //System.out.println(tr);
            }
        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }

        return flg;
    }


    @Override
    public Integer getUserType(String username, String user_password) {
        Integer type=null;
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="SELECT * FROM users WHERE username=? AND user_password=?;";
            //String sqlCheckidAccount ="SELECT * FROM users WHERE user_password=?;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setString(1,username);
            ps.setString(2,user_password);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                type = rs.getInt(7);

            }
        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }

        return type;
    }

    @Override
    public String getUserNameByid(Integer user_id)
    {
        String name="";
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="SELECT * FROM users WHERE user_id=?;";
            //String sqlCheckidAccount ="SELECT * FROM users WHERE user_password=?;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setInt(1,user_id);


            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                name = rs.getString(4) + " "+ rs.getString(5);

            }
        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }

        return name;
    }

    @Override
    public List<Users> getOneUser(String username)
    {
        List<Users> tr = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="SELECT * FROM users WHERE username=?;";
            //String sqlCheckidAccount ="SELECT * FROM users WHERE user_password=?;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setString(1,username);


            ResultSet rs = ps.executeQuery();
            //System.out.println(username);
            while(rs.next()) {
                tr.add(new Users(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getInt(7)));
                //System.out.println(tr);
            }
        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }

        return tr;
    }

    @Override
    public List<Reimbursments> getEmplReimbursments(Integer author)
    {
        List<Reimbursments> tr = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="SELECT * FROM reimbursement WHERE author=?;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setInt(1,author);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                tr.add(new Reimbursments(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getInt(7),rs.getInt(8), rs.getInt(9),rs.getInt(10)));
                //System.out.println(tr);
            }
        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }

        return tr;
    }

    @Override
    public String reimbStatusId(Integer reimb_stat_id)
    {
        String s="";

        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="SELECT * FROM reimbursement_type WHERE type_id=?;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setInt(1,reimb_stat_id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                    s = rs.getString(2);
            }
        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }
        return s;
    }

    @Override
    public String reimbStatus(Integer status_id)
    {
        String s="";

        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="SELECT * FROM reimbursement_status WHERE status_id=?;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setInt(1, status_id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                s = rs.getString(2);
            }
        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }
        return s;
    }

    @Override
    public void approveReimb(Integer reimb_id, Integer resolver)
    {
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="UPDATE reimbursement SET resolver = ?, date_resolved=current_timestamp, reimb_stat_id=2 WHERE reimb_id=?;";
            //String sqlCheckidAccount ="UPDATE reimbursement SET resolver = ?, DEFUALT, reimb_stat_id=2 WHERE reimb_id=?;";
            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setInt(1,resolver);
            ps.setInt(2,reimb_id);

            ps.executeUpdate();

        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }

    }
    @Override
    public void approveReimbT(Integer reimb_id, Integer resolver)
    {
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            //String sqlCheckidAccount ="UPDATE reimbursement SET resolver = ?, date_resolved=current_timestamp, reimb_stat_id=2 WHERE reimb_id=?;";
            String sqlCheckidAccount ="UPDATE reimbursement SET resolver = ?, reimb_stat_id=2 WHERE reimb_id=?;";
            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setInt(1,resolver);
            ps.setInt(2,reimb_id);

            ps.executeUpdate();

        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }

    }
    @Override
    public void denyReimb(Integer reimb_id, Integer resolver)
    {
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="UPDATE reimbursement SET resolver = ?, date_resolved=current_timestamp, reimb_stat_id=3 WHERE reimb_id=?;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setInt(1,resolver);
            ps.setInt(2,reimb_id);

            ps.executeUpdate();

        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }
    }
    @Override
    public void denyReimbT(Integer reimb_id, Integer resolver)
    {
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="UPDATE reimbursement SET resolver = ?, reimb_stat_id=3 WHERE reimb_id=?;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setInt(1,resolver);
            ps.setInt(2,reimb_id);

            ps.executeUpdate();

        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }
    }
    /*
    @Override
    public Integer getReimbIdByName(String reimb)
    {
        Integer type=0;
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="SELECT * FROM reimbursement_type WHERE reim_type=?;";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setString(1,reimb);


            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                type = rs.getInt(1);
            }

        }catch (SQLException e)
        {
            //logger.error(e);
            e.printStackTrace();
        }

        return type;
    }
     */
    @Override
    public void createReimb(Integer id, Reimbursments reimb)
    {
        try(Connection con = DriverManager.getConnection(urlp,usernamep,passwordp))
        {
            String sqlCheckidAccount ="INSERT INTO reimbursement VALUES (DEFAULT,?,DEFAULT,DEFAULT,?,DEFAULT,?,DEFAULT,1,?);";

            PreparedStatement ps = con.prepareStatement(sqlCheckidAccount);

            ps.setInt(1,reimb.getAmount());
            ps.setString(2,reimb.getDescription());
            ps.setInt(3,id);
            ps.setInt(4,reimb.getReimb_stat_type());
            //ps.setString(1,reimb);
             ps.executeUpdate();


        }catch (SQLException e)
        {
            logger.error(e);
            //e.printStackTrace();
        }
    }

}
