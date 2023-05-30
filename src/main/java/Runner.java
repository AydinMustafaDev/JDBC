public class Runner {

    public static void main(String[] args) {
        //DatBase'e bağlan
        JDBCUtils.connectToDataBase();

        //Statement oluştur
        JDBCUtils.createStatement();

        //Query çalıştır
        String sql = "CREATE TABLE workers(worker_id VARCHAR(20), worker_name VARCHAR (20), worker_salary INT);";
        JDBCUtils.execute(sql);




    }
}