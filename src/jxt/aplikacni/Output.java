package jxt.aplikacni;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import jxt.Hlavni;
import jxtrozvrh.MyDayType;
import jxtrozvrh.MyTimeTableDayType;
import jxtrozvrh.MyTimeTableItemType;
import jxtrozvrh.MyTimeTableSemesterType;
import jxtrozvrh.MyTimeTableType;
import jxtrozvrh.ObjectFactory;

/**
 *  Třída obsahující metody pro výstup dat do souboru - xml nebo txt
 *
 * @author Jakub Daněk
 */
public class Output {
    private static final String BALIK_OUT = "jxtrozvrh";
    private static final String SCHEMA = "myTimetable.xsd";

    /**
     * Metoda pro zápis dat do XML souboru pomocí JAXB.
     * @param outputDir Název adresáře pro výstup
     * @param timeTable Data k zápisu
     */
    public static void writeToXml(String outputDir, MyTimeTableType timeTable){
        try {
            JAXBContext jc = JAXBContext.newInstance(BALIK_OUT);
            Marshaller mm = jc.createMarshaller();
            mm.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
            mm.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mm.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, SCHEMA);
            ObjectFactory of = new ObjectFactory();
            JAXBElement<?> element = of.createTimeTable(timeTable);

            try {
                File outputFile = new File(outputDir);
                if(outputFile.exists() && !outputFile.isDirectory()) {
                    Logger.getLogger(Output.class.getName()).log(Level.SEVERE, "The output path is not a directory!");
                    System.exit(-1);
                }
                if(!outputFile.exists()) {
                    if(!outputFile.mkdirs()) {
                        Logger.getLogger(Output.class.getName()).log(Level.SEVERE, "Could not create directory!");
                        System.exit(-1);
                    };
                }
                String name = "rozvrh_" + timeTable.getStudentID() + ".xml";
                outputFile = new File(outputFile, name);
                mm.marshal(element, new FileOutputStream(outputFile));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Output.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (JAXBException ex) {
            Logger.getLogger(Output.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
    }

    /**
     * Metoda pro zápis dat do textového souboru.
     * @param outputDir Název adresáře pro zápis
     * @param timeTable Data k zápisu
     */
    public static void writeToTxt(String outputDir, MyTimeTableType timeTable){
        try{
            File outputFile = new File(outputDir);
            String name = "rozvrh_" + timeTable.getStudentID() + ".txt";
            outputFile = new File(outputFile, name);
            FileOutputStream fos = new FileOutputStream(outputFile);
            OutputStreamWriter osw = new OutputStreamWriter(fos, Hlavni.POUZITE_KODOVANI);
            PrintWriter pw = new PrintWriter(osw);
            List<MyTimeTableSemesterType> terms = timeTable.getTerm();
            Collections.sort(terms, new CompareTerms());
            for(MyTimeTableSemesterType term: terms){
                List<MyTimeTableDayType> days = term.getWorkDay();
                Collections.sort(days, new CompareDays());
                for(MyTimeTableDayType day: days){
                    List<MyTimeTableItemType> items = day.getItem();
                    Collections.sort(items, new CompareItems());
                    pw.print(createOutputLine(items, day.getDay()));
                }
            }
            pw.close();
        }
        catch(FileNotFoundException fne){
            System.out.println("Chyba při zápisu do souboru.");
            System.exit(1);
        }
        catch(UnsupportedEncodingException uee){
            System.out.println("Nepodporovane kodovani.");
            System.exit(2);
        }

    }

    /**
     * Metoda pro vytvoření formátovaného výstupu (textový soubor)
     * @param list Data k formátování
     * @param day Den, se kterým jsou data spjata
     * @return Instance třídy StringBuffer
     */
    private static StringBuffer createOutputLine(List<MyTimeTableItemType> list, MyDayType day){
        Iterator<MyTimeTableItemType> iter = list.iterator();
        StringBuffer sbf = new StringBuffer();

        MyTimeTableItemType temp;
        while(iter.hasNext()){
            temp = iter.next();
            sbf.append(temp.getSubject().getSubjectName());
            sbf.append(';');
            sbf.append(temp.getType().value());
            sbf.append(';');
            sbf.append(day.value());
            sbf.append(';');
            sbf.append(temp.getStartTime());
            sbf.append(';');
            sbf.append(temp.getEndTime());
            sbf.append(';');
            sbf.append(temp.getLocation().getBuilding());
            sbf.append(';');
            sbf.append(temp.getLocation().getRoom());
            sbf.append(String.format("%n"));
        }
        return sbf;
    }

    /**
     * Komparator, porovnává objekty třídy MyTimeTableSemesterType
     */
    private static final class CompareTerms implements Comparator<MyTimeTableSemesterType>{

        public int compare(MyTimeTableSemesterType o1, MyTimeTableSemesterType o2) {
           return o1.getSemester().compareTo(o2.getSemester());
        }
        
    }

    /**
     * Komparator, porovnává objekty třídy MyTimeTableDayType
     */
    private static final class CompareDays implements Comparator<MyTimeTableDayType> {
        public int compare(MyTimeTableDayType o1, MyTimeTableDayType o2) {
            return o1.getDay().compareTo(o2.getDay());
        }
    }

    /**
     * Komparator, porovnává objekty třídy MyTimeTableItemType podle hodnoty
     * StartTime, v případě shody dle názvu, abecedně.
     */
    private static final class CompareItems implements Comparator<MyTimeTableItemType>{

        public int compare(MyTimeTableItemType o1, MyTimeTableItemType o2) {
            if(o1.getStartTime().compare(o2.getStartTime()) > 0){
                return 1;
            }
            else if(o1.getStartTime().compare(o2.getStartTime()) < 0){
                return -1;
            }
            else{
                int cmp = o1.getType().compareTo(o2.getType());
                if(cmp != 0){
                    return cmp;
                }
                else{
                    String s1 = o1.getSubject().getSubjectName();
                    String s2 = o2.getSubject().getSubjectName();
                    return CESKY_ABECEDNI_KOMPARATOR.compare(s1, s2);
                }
            }
        }

   /**
     * komparator pro české řazení
     */
    private static final Comparator<String> CESKY_ABECEDNI_KOMPARATOR =
                  new Comparator<String>() {
       private Collator col = Collator.getInstance(new Locale("cs", "CZ"));
       public int compare(String s1, String s2) {
         return col.compare(s1, s2);
       }
    };
        
    }
}
