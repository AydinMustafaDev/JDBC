import java.sql.*;  //import java.sql.*; ==> buradaki yıldız sql importlarının tamamını temsil eder.

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        Statement statement = connection.createStatement();
        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //PreparedStatement oluşturmak için:

        //1. Adım: PreparedStatement query'si oluştur --> Paremetrelendirme yapılacak yerlere ? gir
        String sql = "UPDATE companies SET number_of_employees = ? WHERE company = ?";//? --> Paremetrelendirme

        //2. Adım: PreparedStatement objesi oluştur
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //3. Adım: PreparedStatement objesi ile setInt gibi methodlarla Paremetrelendirmelerin yerine koymak
        // istediğim değerleri yerleştir
        preparedStatement.setInt(1, 9999);
        preparedStatement.setString(2, "IBM");

        //4. Adım: Query'yi çalıştır
        int guncellenenSatirSayisi = preparedStatement.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        //Güncelleme sonrası datayı okumak için DQL(Select) kullanıyoruz:
        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet = statement.executeQuery(sql2);

        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1) + "--" + resultSet.getObject(2) + "--" + resultSet.getObject(3));
        }

        //2. Örnek: Prepared statement kullanarak company adı CASPER olan number_of_employees değerini 10000 olarak güncelleyin.
        preparedStatement.setInt(1, 10000);
        preparedStatement.setString(2, "CASPER");

        int guncellenenSatirSayisi2 = preparedStatement.executeUpdate();
        System.out.println("guncellenenSatirSayisi2 = " + guncellenenSatirSayisi2);

        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getObject(1) + "--" + resultSet2.getObject(2) + "--" + resultSet2.getObject(3));
        }

        connection.close();
        statement.close();
    }
}