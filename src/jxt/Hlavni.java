package jxt;

import jxt.aplikacni.Output;
import jxt.aplikacni.PreregistrationProcess;
import jxt.aplikacni.TimetableActionsProcess;
import jxtrozvrh.MyTimeTableType;

/**
 * Hlavní třída - vstupní bod aplikace
 *
 * @author Jakub Daněk
 */
public class Hlavni {
    public static final String POUZITE_KODOVANI = "utf-8";
    private static String preregistrationFile;
    private static String timeTableActionsFile;
    private static String personalNb;
    private static String outputDir;

    /**
     * Vstupní bod programu
     * @param args parametry z příkazové řádky
     */
    public static void main(String[] args){
        parameters(args);
        System.out.print("Ahoj");
        MyTimeTableType timetable = TimetableActionsProcess.createTimetable(
                timeTableActionsFile, PreregistrationProcess.getActionList(
                preregistrationFile, personalNb), personalNb);
        Output.writeToXml(outputDir, timetable);
        Output.writeToTxt(outputDir, timetable);
    }

    /**
     * Metoda zajišťující zpracování parametrů z příkazové řádky
     * @param args parametry z příkazové řádky
     */
    private static void parameters(String[] args){
        for(String arg: args){
            if(arg.startsWith("-x")){
                preregistrationFile = arg.substring(2);
            }
            else if(arg.startsWith("-y")){
                timeTableActionsFile = arg.substring(2);
            }
            else if(arg.startsWith("-i")){
                personalNb = arg.substring(2);
            }
            else if(arg.startsWith("-d")){
                outputDir = arg.substring(2);
            }
        }
    }
}
