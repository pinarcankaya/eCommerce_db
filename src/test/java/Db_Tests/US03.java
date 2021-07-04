package Db_Tests;

import org.junit.Assert;
import org.junit.Test;
import utilities.DatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class US03 {

    List<Map<String, String>> list = new ArrayList<>();

    //Merkez ulke adina gore abone sayilarinin toplamini gruplandiriniz.Abone sayisi en fazla olan ulkeyi bulunuz
    @Test
    public void TC0301() throws SQLException {
        String query = "  select MERKEZ_ULKE,sum(ABONE_SAYISI) as abone\n" +
                "from SIRKETLER\n" +
                "where ABONE_SAYISI is not null\n" +
                "group by MERKEZ_ULKE\n" +
                "order by sum(ABONE_SAYISI) desc";
        list = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(list.get(0).get("ABONE"));
        System.out.println(list.get(0).get("MERKEZ_ULKE"));
        Assert.assertEquals("325502500",list.get(0).get("ABONE"));
        Assert.assertEquals("Usa",list.get(0).get("MERKEZ_ULKE"));


    }

   // Sirket id'si 1210 olan sirketin ismini "Trendyol' olarak guncelleyiniz
    @Test
    public void TC0302() throws SQLException {
        String query = "update SIRKETLER set SIRKET_ADI='Trendyol'\n" +
                "where SIRKET_ID=1210;";



    }
    // Gunumuze en yakin tarihte kurulan sirketin merkez ulkesini bulunuz
    @Test
    public void TC0303() throws SQLException {
        String query = "select MERKEZ_ULKE,KURULUS_TARIHI\n" +
                "from SIRKETLER\n" +
                "where KURULUS_TARIHI<'29.Jun.2021'\n" +
                "order by KURULUS_TARIHI desc";
        list = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(list.get(0).get("MERKEZ_ULKE"));
        Assert.assertEquals("Turkey",list.get(0).get("MERKEZ_ULKE"));
    }

}
