package Db_Tests;

import org.junit.Test;
import utilities.DatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class US01 {

    List<Map<String, String>> list1 = new ArrayList<>();


    @Test
    public void soru01() throws SQLException {

        String query1 = "select *\n" +
                "from SIRKETLER";
        list1= DatabaseConnector.getQueryAsAListOfMaps(query1);
        System.out.println(list1);



    }
}
