package repository;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public abstract class Repository {
    protected int maxBatchSize = 100;
    protected Connection connectionSQL;
    protected static String delimiter = ",";

    public void createConnection() throws SQLException {
        connectionSQL = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "sa");
    }

    //public abstract Object Mapper();

    public ResultSet findAllResults(String findAllRows) {
        try {
            createConnection();
            PreparedStatement findStatement = connectionSQL.prepareStatement(findAllRows);
            ResultSet result = findStatement.executeQuery();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db!");
        }
    }

    public ArrayList<String> readFileLines(String importFile) throws FileNotFoundException, IOException {
        System.out.println("File path = " + importFile);
        File fp = new File(importFile);
        FileReader fr = new FileReader(fp);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        fr.close();
        return lines;
    }
}
