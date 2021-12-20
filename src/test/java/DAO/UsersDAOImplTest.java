package DAO;

import models.Reimbursments;
import models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersDAOImplTest {

    UsersDAO usersDAO = new UsersDAOImpl();

    public UsersDAOImplTest()
    {
        this.usersDAO = new UsersDAOImpl(H2Util.url,H2Util.username,H2Util.password);
    }

    @BeforeEach
    void setUp() {
        H2Util.createTable();
    }


    @AfterEach
    void tearDown() {
        H2Util.dropTable();
    }


    @Test
    void getALLUsers() {
        //arrange
        H2Util.insertData();
        List<Users> expected = new ArrayList<>();
        expected.add(new Users(1,"dale_rosa","drosa","Dale","Rosa","dale.rosa@company.com",1));
        expected.add(new Users(2,"harold_walker","hwalker","Harold","Walker","harold.walker@company.com",2));
        expected.add(new Users(3,"chanice_salinas","csalinas","Chanice","Salinas","chanice.salinas@company.com",2));
        //act
        List<Users> actual = usersDAO.getALLUsers();

        //assert
        assertEquals(expected.toString(),actual.toString());

    }

    @Test
    void getAllReimbursments() {
        //arrange
        H2Util.insertData();
        List<Reimbursments> expected = new ArrayList<>();
        expected.add(new Reimbursments(1,45,null,null,"food expenses",null,2,0,1,3));
        expected.add(new Reimbursments(2,450,null,null,"other",null,3,0,2,4));

        //act
        List<Reimbursments> actual = usersDAO.getAllReimbursments();

        //assert
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void login() {
        //arrange
        String username = "dale_rosa";
        String password = "drosa";
        H2Util.insertData();

        //assert
        assertTrue(usersDAO.login(username,password));

    }

    @Test
    void getUserType() {
        //arrange
        String username = "dale_rosa";
        String password = "drosa";
        Integer expected = 1;
        H2Util.insertData();
        //act
        Integer actual = usersDAO.getUserType(username,password);
        //assert
        assertEquals(expected,actual);
    }

    @Test
    void getUserNameByid() {
        //arrange
        String expected = "Dale Rosa";
        H2Util.insertData();
        //act
        String actual = usersDAO.getUserNameByid(1);
        //assert
        assertEquals(expected,actual);
    }

    @Test
    void getOneUser() {
        //arrange
        H2Util.insertData();
        List<Users> expected = new ArrayList<>();
        //System.out.println(usersDAO.getALLUsers());
        expected.add(new Users(1, "dale_rosa", "drosa", "Dale","Rosa", "dale.rosa@company.com",1));
        //act
        List<Users> actual = usersDAO.getOneUser("dale_rosa");
        //assert
        assertEquals(expected.toString(),actual.toString());

    }

    @Test
    void getEmplReimbursments() {
        //arrange
        H2Util.insertData();
        List<Reimbursments> expected = new ArrayList<>();
        expected.add(new Reimbursments(1, 45, null, null, "food expenses", null, 2, 0, 1, 3));
        //System.out.println(usersDAO.getAllReimbursments());

        //act
        List<Reimbursments> actual = usersDAO.getEmplReimbursments(2);
        //assert
        assertEquals(expected.toString(),actual.toString());

    }

    @Test
    void reimbStatusId() {
        //arrange
        H2Util.insertData();
        String expected_l = "LODGING";
        String expected_t = "TRAVEL";
        String expected_f = "FOOD";
        String expected_o = "OTHER";
        //act
        String actual_l = usersDAO.reimbStatusId(1);
        String actual_t = usersDAO.reimbStatusId(2);
        String actual_f = usersDAO.reimbStatusId(3);
        String actual_o = usersDAO.reimbStatusId(4);
        //assert
        assertEquals(expected_l,actual_l);
        assertEquals(expected_t,actual_t);
        assertEquals(expected_f,actual_f);
        assertEquals(expected_o,actual_o);
    }

    @Test
    void reimbStatus() {
        H2Util.insertData();
        String expected_p = "PENDING";
        String expected_a = "APPROVED";
        String expected_d = "DENIED";
        //act
        String actual_p = usersDAO.reimbStatus(1);
        String actual_a = usersDAO.reimbStatus(2);
        String actual_d = usersDAO.reimbStatus(3);

        //assert
        assertEquals(expected_p,actual_p);
        assertEquals(expected_a,actual_a);
        assertEquals(expected_d,actual_d);

    }

    @Test
    void approveReimb() {
        //arrange
        H2Util.insertData();
        List<Reimbursments> expected = new ArrayList<>();
        expected.add(new Reimbursments(1, 45, null, null, "food expenses", null, 2, 1, 2, 3));
        //act
        usersDAO.approveReimbT(1,1);
        List<Reimbursments> actual = usersDAO.getEmplReimbursments(2);
        //assert
        assertEquals(expected.toString(),actual.toString());

    }

    @Test
    void denyReimb() {
        H2Util.insertData();
        List<Reimbursments> expected = new ArrayList<>();
        expected.add(new Reimbursments(1, 45, null, null, "food expenses", null, 2, 1, 3, 3));
        //act
        usersDAO.denyReimbT(1,1);
        List<Reimbursments> actual = usersDAO.getEmplReimbursments(2);
        //assert
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void createReimb() {
        H2Util.insertData();
        List<Reimbursments> expected = new ArrayList<>();
        expected.add(new Reimbursments(1, 45, null, null, "food expenses", null, 2, 0, 1, 3));
        expected.add(new Reimbursments(3, 45, null, null, "test", null, 2, 0, 1, 4));
        Reimbursments r = new Reimbursments();

        r.setAmount(45);
        r.setDescription("test");
        r.setReimb_stat_type(4);

        usersDAO.createReimb(2,(Reimbursments)r);

        List<Reimbursments> actual = usersDAO.getEmplReimbursments(2);
        //System.out.println(usersDAO.getAllReimbursments());
        assertEquals(expected.toString(),actual.toString());
    }
}
