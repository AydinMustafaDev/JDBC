import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Mustafa3342.");
        Statement statement = connection.createStatement();

        //1. Örnek: Selamlama yapan bir function'ı Callable Statement ile çağırınız
        //Callable Statement adımları:

        //1. Adım: Function'ı oluşturan kodu yazınız.
        String sql = "CREATE OR REPLACE FUNCTION selamlama(x TEXT) RETURNS TEXT AS $$ \n" +
                "BEGIN RETURN 'Merhaba '|| x ||', nasılsın?'; END; $$ LANGUAGE plpgsql;";

        // plpgsql ==> Procedural language postgre sql

        //2. Adım: Function kodunu çalıştırınız.
        statement.execute(sql);

        //3. Adım: Function'ı çağır:
        CallableStatement callableStatement = connection.prepareCall("{? = call selamlama(?)}");

        //4. Adım: Return için registerOutParameter() methodunu, parametreler için ise setString,
        // setInt gibi methodları kullanınız
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setString(2, "Ayşe");

        //5. Adım: execute() methodu ile callableStatement'ı çalıştırınız
        callableStatement.execute();

        //6. Adım: Sonucu okumak için callableStatement'tan data türünü çağır
        //callableStatement'ta dönen data resultset içinde dönmez. Direkt callableStatement içerinden alınır.
        System.out.println(callableStatement.getObject(1));

        //2. Örnek: İki sayıyı toplayan bir function yazınız ve Callable Statement ile çağırınız.

        //1. Adım: Function'ı oluşturan kodu yazınız.
        String sql2 = "CREATE OR REPLACE FUNCTION toplama(x INT, y INT) RETURNS NUMERIC \n" +
                "AS $$ BEGIN RETURN x + y; END; $$ LANGUAGE plpgsql;";

        //2. Adım: Function kodunu çalıştırınız.
        statement.execute(sql2);

        //3. Adım: Function'ı çağır:
        CallableStatement callableStatement2 = connection.prepareCall("{? = call toplama(?, ?)}");

        //4. Adım: Return için registerOutParameter() methodunu, parametreler için ise setString,
        // setInt gibi methodları kullanınız
        callableStatement2.registerOutParameter(1, Types.NUMERIC);
        callableStatement2.setInt(2, 5);
        callableStatement2.setInt(3, 15);

        //5. Adım: execute() methodu ile callableStatement'ı çalıştırınız
        callableStatement2.execute();

        //6. Adım: Sonucu okumak için callableStatement'tan data türünü çağır
        System.out.println(callableStatement2.getObject(1));

        connection.close();
        statement.close();
    }
}