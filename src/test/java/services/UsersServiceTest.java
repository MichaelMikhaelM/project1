package services;

import DAO.UsersDAO;
import models.Reimbursments;
import models.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UsersServiceTest {

    UsersDAO usersDAO = Mockito.mock(UsersDAO.class);
    UsersService usersService;

    public UsersServiceTest()
    {
        this.usersService = new UsersService(usersDAO);
    }


    @Test
    void getAllUsers() {
        List<Users> expected = new ArrayList<>();
        expected.add(new Users(1,"dale_rosa","drosa","Dale","Rosa","dale.rosa@company.com",1));
        expected.add(new Users(2,"harold_walker","hwalker","Harold","Walker","harold.walker@company.com",2));
        expected.add(new Users(3,"chanice_salinas","csalinas","Chanice","Salinas","chanice.salinas@company.com",2));

        Mockito.when(usersDAO.getALLUsers()).thenReturn(expected);

        List<Users> actual = usersService.getAllUsers();

        assertEquals(expected.toString(),actual.toString());

    }

    @Test
    void getAllReimbursments() {
        List<Reimbursments> expected = new ArrayList<>();
        expected.add(new Reimbursments(1,45,null,null,"food expenses",null,2,0,1,3));
        expected.add(new Reimbursments(2,450,null,null,"other",null,3,0,2,4));

        Mockito.when(usersDAO.getAllReimbursments()).thenReturn(expected);

        List<Reimbursments> actual = usersService.getAllReimbursments();
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void login() {
        usersService.login("dale_rosa","drosa");

        Mockito.verify(usersDAO,Mockito.times(1)).login("dale_rosa","drosa");
    }

    @Test
    void getUserType() {
        Integer expected = 1;
        Mockito.when(usersDAO.getUserType("dale_rosa","drosa")).thenReturn(1);
        Integer actual = usersService.getUserType("dale_rosa","drosa");

        assertEquals(actual,expected);
    }

    @Test
    void getUserNameByid() {
        String expected = "Dale Rosa";
        Mockito.when(usersDAO.getUserNameByid(1)).thenReturn(expected);
        String actual = usersService.getUserNameByid(1);
        assertEquals(expected,actual);
    }

    @Test
    void getOneUser() {
        List<Users> expected = new ArrayList<>();
        expected.add(new Users(1, "dale_rosa", "drosa", "Dale","Rosa", "dale.rosa@company.com",1));
        Mockito.when(usersDAO.getOneUser("dale_rosa")).thenReturn(expected);

        List<Users> actual = usersService.getOneUser("dale_rosa");
        assertEquals(expected.toString(),actual.toString());

    }

    @Test
    void getEmplReimbursments() {
        List<Reimbursments> expected = new ArrayList<>();
        expected.add(new Reimbursments(1, 45, null, null, "food expenses", null, 2, 0, 1, 3));
        Mockito.when(usersDAO.getEmplReimbursments(2)).thenReturn(expected);

        List<Reimbursments> actual = usersService.getEmplReimbursments(2);

        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void reimbStatusId() {
        String expected_l = "LODGING";
        String expected_t = "TRAVEL";
        String expected_f = "FOOD";
        String expected_o = "OTHER";

        Mockito.when(usersDAO.reimbStatusId(1)).thenReturn(expected_l);
        Mockito.when(usersDAO.reimbStatusId(2)).thenReturn(expected_t);
        Mockito.when(usersDAO.reimbStatusId(3)).thenReturn(expected_f);
        Mockito.when(usersDAO.reimbStatusId(4)).thenReturn(expected_o);

        String actual_l = usersService.reimbStatusId(1);
        String actual_t = usersService.reimbStatusId(2);
        String actual_f = usersService.reimbStatusId(3);
        String actual_o = usersService.reimbStatusId(4);

        assertEquals(expected_l,actual_l);
        assertEquals(expected_t,actual_t);
        assertEquals(expected_f,actual_f);
        assertEquals(expected_o,actual_o);
    }

    @Test
    void reimbStatus() {
        String expected_p = "PENDING";
        String expected_a = "APPROVED";
        String expected_d = "DENIED";

        Mockito.when(usersDAO.reimbStatus(1)).thenReturn(expected_p);
        Mockito.when(usersDAO.reimbStatus(2)).thenReturn(expected_a);
        Mockito.when(usersDAO.reimbStatus(3)).thenReturn(expected_d);

        String actual_p = usersService.reimbStatus(1);
        String actual_a = usersService.reimbStatus(2);
        String actual_d = usersService.reimbStatus(3);

        assertEquals(expected_p,actual_p);
        assertEquals(expected_a,actual_a);
        assertEquals(expected_d,actual_d);
    }

    @Test
    void approveReimb() {
        usersService.approveReimb(1,1);

        Mockito.verify(usersDAO,Mockito.times(1)).approveReimb(1,1);
    }

    @Test
    void denyReimb() {
        usersService.denyReimb(1,1);

        Mockito.verify(usersDAO,Mockito.times(1)).denyReimb(1,1);
    }

    @Test
    void createReimb() {
        List<Reimbursments> expected = new ArrayList<>();
        expected.add(new Reimbursments(1, 45, null, null, "food expenses", null, 2, 0, 1, 3));
        Reimbursments r = new Reimbursments();

        r.setAmount(45);
        r.setDescription("test");
        r.setReimb_stat_type(4);

        usersService.createReimb(2,(Reimbursments)r);
        Mockito.verify(usersDAO,Mockito.times(1)).createReimb(2,(Reimbursments)r);

    }
}