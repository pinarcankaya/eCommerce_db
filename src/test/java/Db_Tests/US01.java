package Db_Tests;

import org.junit.Assert;
import org.junit.Test;
import utilities.DatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class US01 {

    List<Map<String, String>> list1 = new ArrayList<>();


    @Test//sirketler tablosunda toplam kac sirket ismi var
    public void soru01() throws SQLException {

        String query1 = "select count(sirket_adi) as totalSirket\n" +
                "from SIRKETLER";
        list1= DatabaseConnector.getQueryAsAListOfMaps(query1);
        System.out.println(list1.get(0).get("TOTALSIRKET"));
        Assert.assertEquals(list1.get(0).get("TOTALSIRKET"),"15");



    }
    @Test//almanya merkezi sirketlerin sayisi kac
    public void soru02() throws SQLException {

        String query1 = "select count(*) as merkezGermany\n" +
                "from SIRKETLER\n" +
                "where MERKEZ_ULKE='Germany'";
        list1= DatabaseConnector.getQueryAsAListOfMaps(query1);
        System.out.println(list1.get(0).get("MERKEZGERMANY"));
        Assert.assertEquals(list1.get(0).get("MERKEZGERMANY"),"4");



    }
    @Test//turkiye sirketlerinin toplam kac obonesi var
    public void soru03() throws SQLException {

        String query1 = "select sum(ABONE_SAYISI) as aboneSayisi\n" +
                "from SIRKETLER\n" +
                "where MERKEZ_ULKE='Turkey'";
        list1= DatabaseConnector.getQueryAsAListOfMaps(query1);
        System.out.println(list1.get(0).get("ABONESAYISI"));
        Assert.assertEquals(list1.get(0).get("ABONESAYISI"),"50750000");


    }
    @Test//alman sirketlerinin ortalama kac obonesi var (veri yok ise hesaplamaya dahil etmeyin)
    public void soru04() throws SQLException {

        String query1 = "select round (avg(ABONE_SAYISI)) as OrtAboneSayisi\n" +
                "from SIRKETLER\n" +
                "where MERKEZ_ULKE='Germany'";   /* sum(abone_sayisi)/3 olarak da yapilabilir */
        list1= DatabaseConnector.getQueryAsAListOfMaps(query1);
        System.out.println(list1.get(0).get("ORTABONESAYISI"));
        Assert.assertEquals(list1.get(0).get("ORTABONESAYISI"),"3351667");



    }
    @Test//en cok abonesi olan 2. sirket hangisi
    public void soru05() throws SQLException {

        String query1 = "select abone_sayisi\n" +
                "from SIRKETLER\n" +
                "where abone_sayisi<(select max(ABONE_SAYISI) from SIRKETLER)\n" +
                "order by  abone_sayisi desc";
        list1= DatabaseConnector.getQueryAsAListOfMaps(query1);
        System.out.println(list1.get(0).get("ABONE_SAYISI"));
        Assert.assertEquals(list1.get(0).get("ABONE_SAYISI"),"250000000");



    }









/*

---merkez ulkesi Turkey ve Usa olan ulkelerin 2000 yilindan sonra kurulanlarin sayisini bulunuz//

---sirket isminde a harfi bulunmayanlarin sayisini bulunuz

--kurulus yilinin son iki hanesi cift sayi olanlarin sayisini bulunuz

--abone sayisi  5 haneli olan ulkerin isimlerini ve abone sayisini bulunuz

--odeme turu nakit olanlarin en fazla ve en az abone sayisini bulunuz

--her bir odeme turunden kac tane oldugunu bulunuz--odeme turu en fazla olan turun 'hepsi' oldugunu dogrulayiniz
*/

}
