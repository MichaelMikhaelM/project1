package frontcontroller;

import controller.UsersController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.path;
import io.javalin.Javalin;
import io.javalin.http.Context;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Dispatcher {

    public Dispatcher(Javalin app)
    {
        app.routes(() ->
        {
            path("users", () ->
            {
                get(UsersController::getAllUsers);
                path("{user_id}", () ->{
                    get(UsersController::getUserNameByid);
                });
            });
            path("reimbursments", () ->
            {
                get(UsersController::getAllReimbursments);
                path("{id}", () ->
                {
                    get(UsersController::getEmplReimbursments);
                    post(UsersController::createReimb);
                });
                path("approve", () ->
                {
                    path("{id1}", ()->
                    {
                      path("{id2}", ()->
                      {
                          patch(UsersController::approveReimb);
                      });
                    });
                });
                path("deny", () ->
                {
                    path("{id1}", ()->
                    {
                        path("{id2}", ()->
                        {
                            patch(UsersController::denyReimb);
                        });
                    });
                });
            });
            path("login", () ->
            {
                get(UsersController::login);
            });
            path("logout", () ->
            {
                get(UsersController::logout);
            });
            path("check-session", () ->
            {
                get(UsersController::checkSession);
            });
            path("user/{id}", () ->{
                get(UsersController::getOneUser);
            });
            path("reimbstatid/{id}", () ->{
                get(UsersController::reimbStatusId);
            });
            path("reimbstat/{id}", () ->{
                get(UsersController::reimbStatus);
            });

        });
    }

}