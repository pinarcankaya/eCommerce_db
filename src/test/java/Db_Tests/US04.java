package Db_Tests;

import org.junit.Assert;
import org.junit.Test;
import utilities.DatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class US04 {

    List<Map<String, String>> list = new ArrayList<>();

    //--tum personel listesinin maas ortalamasini bulunuz
    @Test
    public void TC0401() throws SQLException {
        String query = "select round (avg(MAAS)) as ORT\n" +
                "from HR.PERSONEL";
        list = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(list.get(0).get("ORT"));
        Assert.assertEquals("2247",list.get(0).get("ORT"));

    }
//--toplam personel sayisini bulunuz
    @Test
    public void TC0402() throws SQLException {
        String query = "select count(CALISAN_ID) as total\n" +
                "from personel";
        list = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(list.get(0).get("TOTAL"));
        Assert.assertEquals("28",list.get(0).get("TOTAL"));


    }
//--en dusuk - en yuksek maas arasindaki farki bulunuz
    @Test
    public void TC0403() throws SQLException {
        String query = "select max(MAAS)-min(MAAS)  as maasFark\n" +
                "from personel";

        list = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(list.get(0).get("MAASFARK"));
        Assert.assertEquals("3060",list.get(0).get("MAASFARK"));

    }


}
