import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        Statement statement = connection.createStatement();

        //1. Örnek:  region_id'si 1 olan "country_name" değerlerini çağırın.;
        String sql1 = "SELECT country_name FROM countries WHERE region_id = 1";
        boolean r1 = statement.execute(sql1);
        System.out.println("r1 = " + r1);//true ==> DQL ile data çağırıyoruz

        //Datayı çağırıp okumak için executeQuery methodunu kullanmalıyız. execute() methodu sadece truE yada false döner
        ResultSet resultSet = statement.executeQuery(sql1);

        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        System.out.println("\n===== 2. Örnek =====\n");
        String sql2 = "SELECT country_id, country_name FROM countries WHERE region_id > 2";

        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getObject(1) + "-- " + resultSet2.getObject(2));
        }

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        System.out.println("\n===== 3. Örnek =====\n");
        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet3 = statement.executeQuery(sql3);

        while (resultSet3.next()) {
            System.out.println(resultSet3.getObject(1) + "--" + resultSet3.getObject(2) + "--" + resultSet3.getObject(3));
        }

        connection.close();
        statement.close();


        /*
        CREATE TABLE countries
(
    country_id CHAR(3),
    country_name VARCHAR(50),
    region_id SMALLINT
);
Insert into countries values ('AR','Argentina',2);
Insert into countries values ('AU','Australia',3);
Insert into countries values ('BE','Belgium',1);
Insert into countries values ('BR','Brazil',2);
Insert into countries values ('CA','Canada',2);
Insert into countries values ('CH','Switzerland',1);
Insert into countries values ('CN','China',3);
Insert into countries values ('DE','Germany',1);
Insert into countries values ('DK','Denmark',1);
Insert into countries values ('EG','Egypt',4);
Insert into countries values ('FR','France',1);
Insert into countries values ('IL','Israel',4);
Insert into countries values ('IN','India',3);
Insert into countries values ('IT','Italy',1);
Insert into countries values ('JP','Japan',3);
Insert into countries values ('KW','Kuwait',4);
Insert into countries values ('ML','Malaysia',3);
Insert into countries values ('MX','Mexico',2);
Insert into countries values ('NG','Nigeria',4);
Insert into countries values ('NL','Netherlands',1);
Insert into countries values ('SG','Singapore',3);
Insert into countries values ('UK','United Kingdom',1);
Insert into countries values ('US','United States of America',2);
Insert into countries values ('ZM','Zambia',4);
Insert into countries values ('ZW','Zimbabwe',4);

SELECT * FROM countries

--//1. Örnek:  region_id'si 1 olan "country_name" değerlerini çağırın.

SELECT country_name FROM countries WHERE region_id = 1
         */
    }
}