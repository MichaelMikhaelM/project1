package controller;

import DAO.UsersDAOImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import models.Reimbursments;
import models.Users;
import services.UsersService;

import java.util.ArrayList;
import java.util.List;

public class UsersController {

    static UsersService usersService = new UsersService(new UsersDAOImpl());

    public static void getAllUsers(Context ctx) throws JsonProcessingException {
        ctx.contentType("application/json");

        List<Users> users = usersService.getAllUsers();

        String jstring = new ObjectMapper().writeValueAsString(users);
        ctx.result(jstring);
    }
    public static void getAllReimbursments(Context ctx) throws JsonProcessingException {
        ctx.contentType("application/json");

        List<Reimbursments> reimbursments = usersService.getAllReimbursments();

        String jstring = new ObjectMapper().writeValueAsString(reimbursments);

        ctx.result(jstring);
    }
    public static void login(Context ctx) throws JsonProcessingException {
        //Users users = ctx.bodyAsClass(Users.class);
        String username = ctx.queryParam("username");
        String password = ctx.queryParam("user_password");

        boolean b = usersService.login(username,password);

        if(b==true)
        {
            //System.out.println(usersService.getUserType(username,password));
            if(usersService.getUserType(username,password).equals(1)) {


                //ctx.result(jstring);
                ctx.sessionAttribute("user-session",username);
                ctx.redirect("/manager");

            }
            else
            {
                ctx.sessionAttribute("user-session",username);
                ctx.redirect("/employee");
            }

        }

    }
    public static void getUserNameByid(Context ctx)
    {
        Integer id = Integer.parseInt(ctx.pathParam("user_id"));
        String username = usersService.getUserNameByid(id);
        ctx.result(username);
    }
    public static void checkSession(Context ctx)
    {
        String user = ctx.sessionAttribute("user-session");
        if(user==null)
            ctx.result("no session found");
        else
        ctx.result(user);
    }
    public static void logout(Context ctx)
    {
        ctx.sessionAttribute("user-session",null);
        //System.out.printf("Bingoo!!");
        //ctx.redirect("/");
    }
    public static void getOneUser(Context ctx) throws JsonProcessingException {
        String username = ctx.pathParam("id");
        List<Users> l = usersService.getOneUser(username);
        String jstring = new ObjectMapper().writeValueAsString(l);
        ctx.result(jstring);
        //return usersService.getOneUser(username);
    }
    public static void  getEmplReimbursments(Context ctx) throws JsonProcessingException {
        Integer i = Integer.parseInt(ctx.pathParam("id"));
        List<Reimbursments> l = usersService.getEmplReimbursments(i);

        String jstring = new ObjectMapper().writeValueAsString(l);
        ctx.result(jstring);
    }
    public static void reimbStatusId(Context ctx)
    {
        Integer i = Integer.parseInt(ctx.pathParam("id"));

        String s = usersService.reimbStatusId(i);

        ctx.result(s);
    }
    public static void reimbStatus(Context ctx)
    {
        Integer i = Integer.parseInt(ctx.pathParam("id"));

        String s = usersService.reimbStatus(i);

        ctx.result(s);
    }

    public static void approveReimb(Context ctx)
    {
        Integer reimb_id = Integer.parseInt(ctx.pathParam("id1"));
        Integer resolver = Integer.parseInt(ctx.pathParam("id2"));

        usersService.approveReimb(reimb_id,resolver);
    }
    public static void denyReimb(Context ctx)
    {
        Integer reimb_id = Integer.parseInt(ctx.pathParam("id1"));
        Integer resolver = Integer.parseInt(ctx.pathParam("id2"));

        usersService.denyReimb(reimb_id,resolver);
    }
    public static void createReimb(Context ctx)
    {
        Integer id = Integer.parseInt(ctx.pathParam("id"));

        Reimbursments reimb = ctx.bodyAsClass(Reimbursments.class);

        usersService.createReimb(id,reimb);
    }
}
