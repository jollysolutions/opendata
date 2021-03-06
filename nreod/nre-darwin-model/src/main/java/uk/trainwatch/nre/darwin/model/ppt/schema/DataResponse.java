//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.09 at 07:06:13 PM BST 
//


package uk.trainwatch.nre.darwin.model.ppt.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import uk.trainwatch.nre.darwin.model.ppt.alarms.RTTIAlarm;
import uk.trainwatch.nre.darwin.model.ppt.forecasts.TS;
import uk.trainwatch.nre.darwin.model.ppt.schedules.Association;
import uk.trainwatch.nre.darwin.model.ppt.schedules.DeactivatedSchedule;
import uk.trainwatch.nre.darwin.model.ppt.schedules.Schedule;
import uk.trainwatch.nre.darwin.model.ppt.stationmessages.StationMessage;
import uk.trainwatch.nre.darwin.model.ppt.tddata.TrackingID;
import uk.trainwatch.nre.darwin.model.ppt.trainalerts.TrainAlert;
import uk.trainwatch.nre.darwin.model.ppt.trainorder.TrainOrder;


/**
 * <p>Java class for DataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="schedule" type="{http://www.thalesgroup.com/rtti/PushPort/Schedules/v1}Schedule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="deactivated" type="{http://www.thalesgroup.com/rtti/PushPort/Schedules/v1}DeactivatedSchedule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="association" type="{http://www.thalesgroup.com/rtti/PushPort/Schedules/v1}Association" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TS" type="{http://www.thalesgroup.com/rtti/PushPort/Forecasts/v2}TS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OW" type="{http://www.thalesgroup.com/rtti/PushPort/StationMessages/v1}StationMessage" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="trainAlert" type="{http://www.thalesgroup.com/rtti/PushPort/TrainAlerts/v1}TrainAlert" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="trainOrder" type="{http://www.thalesgroup.com/rtti/PushPort/TrainOrder/v1}TrainOrder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="trackingID" type="{http://www.thalesgroup.com/rtti/PushPort/TDData/v1}TrackingID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="alarm" type="{http://www.thalesgroup.com/rtti/PushPort/Alarms/v1}RTTIAlarm" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataResponse", propOrder = {
    "schedule",
    "deactivated",
    "association",
    "ts",
    "ow",
    "trainAlert",
    "trainOrder",
    "trackingID",
    "alarm"
})
@XmlSeeAlso({
    uk.trainwatch.nre.darwin.model.ppt.schema.Pport.UR.class
})
public class DataResponse {

    protected List<Schedule> schedule;
    protected List<DeactivatedSchedule> deactivated;
    protected List<Association> association;
    @XmlElement(name = "TS")
    protected List<TS> ts;
    @XmlElement(name = "OW")
    protected List<StationMessage> ow;
    protected List<TrainAlert> trainAlert;
    protected List<TrainOrder> trainOrder;
    protected List<TrackingID> trackingID;
    protected List<RTTIAlarm> alarm;

    /**
     * Gets the value of the schedule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the schedule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSchedule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Schedule }
     * 
     * 
     */
    public List<Schedule> getSchedule() {
        if (schedule == null) {
            schedule = new ArrayList<Schedule>();
        }
        return this.schedule;
    }

    public boolean isSetSchedule() {
        return ((this.schedule!= null)&&(!this.schedule.isEmpty()));
    }

    public void unsetSchedule() {
        this.schedule = null;
    }

    /**
     * Gets the value of the deactivated property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deactivated property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeactivated().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeactivatedSchedule }
     * 
     * 
     */
    public List<DeactivatedSchedule> getDeactivated() {
        if (deactivated == null) {
            deactivated = new ArrayList<DeactivatedSchedule>();
        }
        return this.deactivated;
    }

    public boolean isSetDeactivated() {
        return ((this.deactivated!= null)&&(!this.deactivated.isEmpty()));
    }

    public void unsetDeactivated() {
        this.deactivated = null;
    }

    /**
     * Gets the value of the association property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the association property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Association }
     * 
     * 
     */
    public List<Association> getAssociation() {
        if (association == null) {
            association = new ArrayList<Association>();
        }
        return this.association;
    }

    public boolean isSetAssociation() {
        return ((this.association!= null)&&(!this.association.isEmpty()));
    }

    public void unsetAssociation() {
        this.association = null;
    }

    /**
     * Gets the value of the ts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TS }
     * 
     * 
     */
    public List<TS> getTS() {
        if (ts == null) {
            ts = new ArrayList<TS>();
        }
        return this.ts;
    }

    public boolean isSetTS() {
        return ((this.ts!= null)&&(!this.ts.isEmpty()));
    }

    public void unsetTS() {
        this.ts = null;
    }

    /**
     * Gets the value of the ow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOW().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StationMessage }
     * 
     * 
     */
    public List<StationMessage> getOW() {
        if (ow == null) {
            ow = new ArrayList<StationMessage>();
        }
        return this.ow;
    }

    public boolean isSetOW() {
        return ((this.ow!= null)&&(!this.ow.isEmpty()));
    }

    public void unsetOW() {
        this.ow = null;
    }

    /**
     * Gets the value of the trainAlert property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trainAlert property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrainAlert().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrainAlert }
     * 
     * 
     */
    public List<TrainAlert> getTrainAlert() {
        if (trainAlert == null) {
            trainAlert = new ArrayList<TrainAlert>();
        }
        return this.trainAlert;
    }

    public boolean isSetTrainAlert() {
        return ((this.trainAlert!= null)&&(!this.trainAlert.isEmpty()));
    }

    public void unsetTrainAlert() {
        this.trainAlert = null;
    }

    /**
     * Gets the value of the trainOrder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trainOrder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrainOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrainOrder }
     * 
     * 
     */
    public List<TrainOrder> getTrainOrder() {
        if (trainOrder == null) {
            trainOrder = new ArrayList<TrainOrder>();
        }
        return this.trainOrder;
    }

    public boolean isSetTrainOrder() {
        return ((this.trainOrder!= null)&&(!this.trainOrder.isEmpty()));
    }

    public void unsetTrainOrder() {
        this.trainOrder = null;
    }

    /**
     * Gets the value of the trackingID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trackingID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrackingID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrackingID }
     * 
     * 
     */
    public List<TrackingID> getTrackingID() {
        if (trackingID == null) {
            trackingID = new ArrayList<TrackingID>();
        }
        return this.trackingID;
    }

    public boolean isSetTrackingID() {
        return ((this.trackingID!= null)&&(!this.trackingID.isEmpty()));
    }

    public void unsetTrackingID() {
        this.trackingID = null;
    }

    /**
     * Gets the value of the alarm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alarm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlarm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RTTIAlarm }
     * 
     * 
     */
    public List<RTTIAlarm> getAlarm() {
        if (alarm == null) {
            alarm = new ArrayList<RTTIAlarm>();
        }
        return this.alarm;
    }

    public boolean isSetAlarm() {
        return ((this.alarm!= null)&&(!this.alarm.isEmpty()));
    }

    public void unsetAlarm() {
        this.alarm = null;
    }

}
