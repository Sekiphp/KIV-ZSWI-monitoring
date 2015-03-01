package jxt.aplikacni;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import jxtrozvrh.MyDayType;
import jxtrozvrh.MyKindType;
import jxtrozvrh.MyLocationType;
import jxtrozvrh.MySemesterType;
import jxtrozvrh.MySubjectType;
import jxtrozvrh.MyTimeTableDayType;
import jxtrozvrh.MyTimeTableItemType;
import jxtrozvrh.MyTimeTableSemesterType;
import jxtrozvrh.MyTimeTableType;
import jxtsp.DayType;
import jxtsp.EventType;
import jxtsp.PlaceType;
import jxtsp.TimeType;
import jxtsp.TimetableActivitiesOfFAVStudentsType;
import jxtsp.TimetableActivityType;

/**
 *  Třída pro vytvoření rozvrhu studenta na základě platných rozvrhových a
 * předzápisových akcích studenta.
 * @author Jakub Daněk
 */
public class TimetableActionsProcess {
    private static final String BALIK_IN = "jxtsp";
    // Použito volání včetně názvu balíku vzhledem ke kolizi s ObjectFactory v
    // balíku jxtsp
    private static final jxtrozvrh.ObjectFactory of = new jxtrozvrh.ObjectFactory();
    private static HashMap<Integer, TimetableActivityType> subjectData =
            new HashMap<Integer, TimetableActivityType>();

    /**
     * Metoda vytvoří instanci třídy MyTimeTableType obsahující popis rozvrhu
     * studenta
     * @param inputFile Soubor obsahující rozvrhové akce studenta
     * @param actions Seznam platných předzápisových akcí studenta
     * @param studentId ID studenta
     * @return Objekt studentova rozvrhu
     */
    public static MyTimeTableType createTimetable(String inputFile,List<EventType> actions, String studentId){

        readData(inputFile);
        MyTimeTableType result = of.createMyTimeTableType();
        result.setStudentID(studentId);
        result.getTerm().add(createSemester(actions, MySemesterType.ZS));
        result.getTerm().add(createSemester(actions, MySemesterType.LS));
        return result;
    }

    /**
     * Načte ze souboru rozvrhové akce
     * @param inputFile Vstupní soubor
     */
    private static void readData(String inputFile){
        try {
            JAXBContext jc = JAXBContext.newInstance(BALIK_IN);
            Unmarshaller um = jc.createUnmarshaller();
            JAXBElement<?> element = (JAXBElement<?>) um.unmarshal(new File(inputFile));
            TimetableActivitiesOfFAVStudentsType activities = (TimetableActivitiesOfFAVStudentsType) element.getValue();
            for(TimetableActivityType temp: activities.getTimetableActivity()){
                subjectData.put(temp.getId().intValue(), temp);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(PreregistrationProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Uspořádá data o rozvrhu na jeden semestr
     * @param actions Seznam platných akcí studenta
     * @param type Upřesnění semestru (letní, zimní)
     * @return Objekt popisující rozvrh na jeden semestr.
     */
    private static MyTimeTableSemesterType createSemester(List<EventType> actions, MySemesterType type){
        MyTimeTableSemesterType term = of.createMyTimeTableSemesterType();
        term.setSemester(type);
        ArrayList<EventType> termActions = new ArrayList<EventType>();

        for(EventType singleEvent: actions){
            if(singleEvent.getProcessedData().getTerm().value().equals(type.value())){
                termActions.add(singleEvent);
            }
        }

        for(MyDayType weekDay: MyDayType.values()){
            term.getWorkDay().add(createDay(termActions, weekDay));
        }
        
        return term;
    }

    /**
     * Uspořádá data o rozvrhu na jeden den
     * @param actions Seznam platných akcí studenta
     * @param type Upřesnění dne
     * @return Objekt popisující rozvrh na jeden den.
     */
    private static MyTimeTableDayType createDay(List<EventType> actions, MyDayType day){
        MyTimeTableDayType newDay = of.createMyTimeTableDayType();
        newDay.setDay(day);
        Iterator<EventType> iterator = actions.iterator();
        EventType singleEvent;
        while(iterator.hasNext()){
            singleEvent = iterator.next();
            //ID rozvrhové akce singleEvent
            int eventID = singleEvent.getProcessedData().getTimetableAction().getId().intValue();
            TimetableActivityType activity = subjectData.get(eventID);
            
            //Den, na který je rozvrhová akce naplánována
            DayType actionDay = activity.getTime().getDay();

            //Zpracování jen akcí z vybraného dne
            MyTimeTableItemType newItem;
            if((actionDay != null) && (actionDay.value().equals(day.value()))){
                newItem = createItem(activity);
                newDay.getItem().add(createItem(activity));
                //Již zpracovaná akce není již více třeba. Předáno odkazem, lze takto upravit
                iterator.remove();
            }
            
        }
        return newDay;
    }

    /**
     * Vytvoří instanci třídy MyTimeTableItemType pro reprezentaci jedné položky
     * v rozvrhu na základě dat o rozvrhové akci.
     * @param activity Rozvrhová akce
     * @return Objekt třídy MyTimeTableItemType odpovídající dané rozvrhové akci
     */
    private static MyTimeTableItemType createItem(TimetableActivityType activity){
        MyTimeTableItemType output = of.createMyTimeTableItemType();
        
        //Nastavení počátečního a koncového času akce. Pokud není, akce se nezapisuje
        TimeType time = activity.getTime();
        if(time == null) return null;
        output.setStartTime(activity.getTime().getStartTime());
        output.setEndTime(activity.getTime().getEndTime());

        //Nastavení výukové místnosti. Pokud není, akce se nezapisuje
        PlaceType place = activity.getPlace();
        if(place == null) return null;
        MyLocationType location = of.createMyLocationType();
        location.setBuilding(place.getBuilding());
        location.setRoom(place.getRoomNumber());
        output.setLocation(location);

        //Nastavení typu rozvrhové akce
        String type = activity.getSubject().getKind().value();        
        if(type.equals(MyKindType.PŘ.value())){
            output.setType(MyKindType.PŘ);
        }
        else if(type.equals(MyKindType.CV.value())){
            output.setType(MyKindType.CV);
        }
        else if(type.equals(MyKindType.SE.value())){
            output.setType(MyKindType.SE);
        }

        //Nastavení názvu a katedry předmětu
        MySubjectType subject = of.createMySubjectType();
        subject.setDepartment(activity.getDepartment());
        subject.setSubjectName(activity.getSubject().getValue());
        output.setSubject(subject);
        
        return output;

    }
}
