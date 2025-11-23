package edu.easternflorida.villegas;

import java.math.BigDecimal;
import edu.easternflorida.cantres.TPC_DBCommencing;
import java.util.HashMap;
import edu.easternflorida.villegas.interfaces.Part;

public class Main {

public static void main(String[] args) {
 try {
   TPC_DBCommencing starter = new TPC_DBCommencing();
   starter.initDatabase();   // creates DB + tables if needed

   TPC_DBAPI api = new TPC_DBAPI();

   HashMap<Integer, Part> parts = api.readAllParts();

   Part magicPart = new Part(
       1000000000,
       "yeaa",
       "yeaa",
       "yeaa",
       "yeaa",
       0,
       "yeaa",
       BigDecimal.ONE,
       "yeaa"
   );

   System.out.println("has record before insert: " + parts.containsKey(magicPart.getP_PARTKEY()));

   try {
     api.insertPart(magicPart);
   } catch (Exception err) {
     err.printStackTrace();
   }

   parts = api.readAllParts();
   System.out.println("has record after insert: " + parts.containsKey(magicPart.getP_PARTKEY()));

 } catch (Exception e) {
   e.printStackTrace();
 }
}
}

