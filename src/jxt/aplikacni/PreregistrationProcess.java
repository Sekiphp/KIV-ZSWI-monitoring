package jxt.aplikacni;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import jxtsp.EventType;
import jxtsp.PreregistrationEventsType;

/**
 *  Třída pro zpracování předzápisových akcí portálu Západočeské univerzity v 
 * Plzni
 *
 * @author Jakub Daněk
 */
public class PreregistrationProcess {
    /**
     * Název balíku obsahující třídy pro zpracování xml souboru obsahujícího data.
     * Vytvořen pomocí nástroje xjt (technologie JAXB).
     */
    private static final String BALIK = "jxtsp";

    /**
     * Metoda pro zisk platných předzápisových akcí studenta s daným
     *  identifikačním číslem.
     * @param inputFile XML soubor se vstupními daty
     * @param studentsId ID studenta
     * @return Seznam platných akcí studenta
     */
    public static List<EventType> getActionList(String inputFile, String studentsId){
        List temp = readData(inputFile);
        temp = findStudentsActions(temp, studentsId);
        return findActiveActions(temp);
    }

    /**
     * Metoda načte data ze vstupního souboru
     * @param inputFile Název vstupního souboru
     * @return Seznam načtených dat.
     */
    private static List<EventType> readData(String inputFile){
        try {
            JAXBContext jc = JAXBContext.newInstance(BALIK);
            Unmarshaller um = jc.createUnmarshaller();
            JAXBElement<?> element = (JAXBElement<?>) um.unmarshal(new File(inputFile));
            PreregistrationEventsType event = (PreregistrationEventsType) element.getValue();
            return event.getEvent();
        } catch (JAXBException ex) {
            Logger.getLogger(PreregistrationProcess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Metoda z množiny dat vytřídí předzápisové akce daného studenta
     * @param events Všechna data
     * @param studentsId ID studenta
     * @return Seznam akcí studenta
     */
    private static ArrayList<EventType> findStudentsActions(List<EventType> events, String studentsId){
        ArrayList<EventType> temp = new ArrayList<EventType>();
        for(EventType singleEvent: events){
            if(studentsId.equals(singleEvent.getParameters().getActor().getPersonalNumber())){
                temp.add(singleEvent);
            }
        }
        return temp;
    }

    /**
     * Metoda zachová jen platné předzápisové akce.
     * @param events Data
     * @return Seznam platných akcí
     */
    private static ArrayList<EventType> findActiveActions(List<EventType> events){

        HashMap<Integer, EventType> actions = new HashMap<Integer, EventType>();
        int temp;
        String action;
        for(EventType singleEvent: events){
            temp = singleEvent.getProcessedData().getTimetableAction().getId().intValue();
            action = singleEvent.getProcessedData().getActivity().value();
            if(action.equals("insert")){
                actions.put(temp, singleEvent);
            }
            else if(action.equals("delete")){
                actions.remove(temp);
            }
        }

        ArrayList<EventType> output = new ArrayList<EventType>();
        for(EventType singleEvent: actions.values()){
            output.add(singleEvent);
        }

        return output;
    }
}
