package Db_Tests;

import org.junit.Assert;

import org.junit.Test;
import utilities.DatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class US02 {

    List<Map<String, String>> list = new ArrayList<>();

    //merkez ulkesi Turkey ve Usa olan ulkelerin 2000 yilindan sonra kurulanlarin sayisini bulunuz
    @Test
    public void soru06() throws SQLException {
        String query = "select count(*)\n" +
                "from sirketler\n" +
                "where merkez_ulke in('Turkey','Usa') and kurulus_tarihi>'1 Jan 2000'";

        list = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(list.get(0));
        Assert.assertEquals("5", list.get(0).get("COUNT(*)"));
    }

    //sirket isminde a harfi bulunmayanlarin sayisini bulunuz
    @Test
    public void soru07() throws SQLException {
//        String query = "select *\n" +
//                "from SIRKETLER\n" +
//                "where REGEXP_LIKE (sirket_adi, '[Aa](*)')";

        String query2 = "select count(*)\n" +
                "from SIRKETLER\n" +
                "where SIRKET_ADI not like '%a%' and  SIRKET_ADI not like  'A%'";

        list = DatabaseConnector.getQueryAsAListOfMaps(query2);
        System.out.println(list);
        Assert.assertEquals("7", list.get(0).get("COUNT(*)"));
    }

    //kurulus yili cift sayi olanlarin sayisini bulunuz
    @Test
    public void soru08() throws SQLException {
        String query="select KURULUS_TARIHI\n" +
                "from SIRKETLER";
        list = DatabaseConnector.getQueryAsAListOfMaps(query);
      //  System.out.println(list);

        for (Map<String,String> w:list){
            String year=w.get("KURULUS_TARIHI").substring(0,4);
            System.out.println(year);
        }

    }

    //abone sayisi  5 haneli olan ulkerin isimlerini ve abone sayisini bulunuz ///toplam 3 ulke
    @Test
    public void soru09() throws SQLException {
        String query = "select MERKEZ_ULKE,ABONE_SAYISI\n" +
                "from SIRKETLER\n" +
                "where ABONE_SAYISI like '_____'";
        list = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(list.size());
        Assert.assertEquals(3,list.size());

    }

    @Test
    public void soru10() throws SQLException {
        String query="select max(ABONE_SAYISI),min(ABONE_SAYISI)\n" +
                "from SIRKETLER\n" +
                "where ODEME_TURU='nakit'";
        list = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(list);
        Assert.assertTrue(list.get(0).get("MAX(ABONE_SAYISI)").equals("20000000") && list.get(0).get("MIN(ABONE_SAYISI)").equals("1"));


    }

    //her bir odeme turunden kac tane oldugunu bulunuz--odeme turu en fazla olan turun 'hepsi' oldugunu dogrulayiniz
    @Test
    public void soru11() throws SQLException {
        String query = " SELECT ODEME_TURU,count(*)\n" +
                " FROM SIRKETLER\n" +
                " GROUP BY ODEME_TURU";
        list = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println(list);
        int buyuksayi=0;

        for (Map<String,String> w:list){
            int sayi=Integer.parseInt(w.get("count(*)"));
            if(buyuksayi<sayi) {
                buyuksayi = sayi;
            }
        }
        System.out.println("En buyuk sayi " + buyuksayi);
    }


}
