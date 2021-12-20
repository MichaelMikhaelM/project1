package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Util {

    public static String url = "jdbc:h2:./h2/db";
    public static String username = "sa";
    public static String password = "sa";

    public static void createTable()
    {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "CREATE TABLE role_id(role_id int NOT NULL PRIMARY KEY,user_role varchar(10));CREATE TABLE users(user_id serial PRIMARY KEY,username varchar(50) NOT NULL,user_password varchar(50) NOT NULL,first_name varchar(100) NOT NULL,last_name varchar(100) NOT NULL,email varchar(150) NOT NULL,role_id int NOT NULL REFERENCES role_id(role_id));CREATE TABLE reimbursement_status(status_id int NOT NULL PRIMARY KEY,status varchar(10));CREATE TABLE reimbursement_type(type_id int NOT NULL PRIMARY KEY,reim_type varchar(10));CREATE TABLE reimbursement(reimb_id serial PRIMARY KEY,amount int NOT NULL,date_submitted timestamp DEFAULT null,date_resolved timestamp DEFAULT NULL,description varchar(250),receipt varchar(100) DEFAULT NULL,author int REFERENCES users(user_id),resolver int REFERENCES users(user_id),reimb_stat_id int REFERENCES reimbursement_status(status_id),reimb_stat_type int REFERENCES reimbursement_type(type_id));";


            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }


    }
    public static void dropTable(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "DROP TABLE reimbursement;DROP TABLE users;DROP TABLE reimbursement_status;DROP TABLE reimbursement_type;DROP TABLE role_id;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void insertData(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "       INSERT INTO reimbursement_status VALUES (1,'PENDING');\n" +
                    "            INSERT INTO reimbursement_status VALUES (2,'APPROVED');\n" +
                    "            INSERT INTO reimbursement_status VALUES (3,'DENIED');\n" +
                    "\n" +
                    "            INSERT INTO reimbursement_type VALUES (1,'LODGING');\n" +
                    "            INSERT INTO reimbursement_type VALUES (2,'TRAVEL');\n" +
                    "            INSERT INTO reimbursement_type VALUES (3,'FOOD');\n" +
                    "            INSERT INTO reimbursement_type VALUES (4,'OTHER');\n" +
                    "\n" +
                    "            INSERT INTO role_id VALUES (1,'MANAGER');\n" +
                    "            INSERT INTO role_id VALUES (2,'EMPLOYEE');INSERT INTO users VALUES (DEFAULT,'dale_rosa','drosa','Dale','Rosa','dale.rosa@company.com',1);\n" +
                    "            INSERT INTO users VALUES (DEFAULT,'harold_walker','hwalker','Harold','Walker','harold.walker@company.com',2);INSERT INTO users VALUES (DEFAULT,'chanice_salinas','csalinas','Chanice','Salinas','chanice.salinas@company.com',2);INSERT INTO reimbursement VALUES (DEFAULT,45,DEFAULT,DEFAULT,'food expenses',DEFAULT,2,DEFAULT,1,3);\n" +
                    "INSERT INTO reimbursement VALUES (DEFAULT,450,DEFAULT,DEFAULT,'other',DEFAULT,3,DEFAULT,2,4);";



            PreparedStatement ps = conn.prepareStatement(sql);



            ps.execute();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
