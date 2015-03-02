//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.15 at 06:38:10 odp. CEST 
//


package jxtrozvrh;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for myTimeTableType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="myTimeTableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="term" type="{}myTimeTableSemesterType" maxOccurs="2"/>
 *       &lt;/sequence>
 *       &lt;attribute name="studentID" use="required" type="{}myStudentsIdType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "myTimeTableType", propOrder = {
    "term"
})
public class MyTimeTableType {

    @XmlElement(required = true)
    protected List<MyTimeTableSemesterType> term;
    @XmlAttribute(required = true)
    protected String studentID;

    /**
     * Gets the value of the term property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the term property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MyTimeTableSemesterType }
     * 
     * 
     */
    public List<MyTimeTableSemesterType> getTerm() {
        if (term == null) {
            term = new ArrayList<MyTimeTableSemesterType>();
        }
        return this.term;
    }

    /**
     * Gets the value of the studentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the value of the studentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentID(String value) {
        this.studentID = value;
    }

}