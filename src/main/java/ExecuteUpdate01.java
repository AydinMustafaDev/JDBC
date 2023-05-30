import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        Statement statement = connection.createStatement();

        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.
        String sql = "UPDATE companies SET number_of_employees = 16000 WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies)";
        int guncellenenSatirSayisi = statement.executeUpdate(sql);//executeUpdate() methodu güncellenen satır sayısını int değer olarak döner
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        //Güncelleme sonrası datayı okumak için DQL(Select) kullanıyoruz:
        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet = statement.executeQuery(sql2);

        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1) + "--" + resultSet.getObject(2) + "--" + resultSet.getObject(3));
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

--1. Örnek:  region_id'si 1 olan "country_name" değerlerini çağırın.;

SELECT country_name FROM countries WHERE region_id = 1

--2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.

SELECT country_id, country_name FROM countries WHERE region_id > 2



CREATE TABLE companies
(
  company_id SMALLINT,
  company VARCHAR(20),
  number_of_employees SMALLINT
);

INSERT INTO companies VALUES(100, 'IBM', 12000);
INSERT INTO companies VALUES(101, 'GOOGLE', 18000);
INSERT INTO companies VALUES(102, 'MICROSOFT', 10000);
INSERT INTO companies VALUES(103, 'APPLE', 21000);
INSERT INTO companies VALUES(104, 'CASPER', 18000);

SELECT * FROM companies;


--3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.

SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)

--1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

--1. Yol OFFSET ve LIMIT kullanarak
SELECT company, number_of_employees  FROM companies ORDER BY number_of_employees DESC OFFSET 1 LIMIT 1



--2. Yol: Sub Query
SELECT company, number_of_employees  FROM companies
WHERE number_of_employees = (SELECT MAX(number_of_employees) FROM companies
							 WHERE number_of_employees < (SELECT MAX(number_of_employees) FROM companies))

							 -

-- 1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

UPDATE companies SET number_of_employees = 16000 WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies)

-- 1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

UPDATE companies SET number_of_employees = 999 WHERE company = 'IBM'
         */
    }
}