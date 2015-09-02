/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.web.ldb;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import uk.trainwatch.nre.darwin.reference.DarwinReferenceManager;
import uk.trainwatch.web.ldb.model.ForecastEntry;
import uk.trainwatch.web.ldb.model.TimetableEntry;
import uk.trainwatch.web.ldb.model.Train;

/**
 *
 * @author peter
 */
public class LDBTrain
        implements Serializable,
                   LDB
{

    private static final long serialVersionUID = 1L;

    private final LDB.Type type;
    private final Train train;

    private final boolean terminated;
    private final boolean delayUnknown;
    private final int length;

    private final Collection<CallingPoint> points;
    private final ForecastEntry fe;

    public LDBTrain( LDB.Type type, Train train,
                     Predicate<Integer> crsFilter,
                     DarwinReferenceManager darwinReferenceManager )
    {
        this.type = type;
        this.train = train;

        TimetableEntry te = train.findTime( crsFilter );
        fe = te instanceof ForecastEntry ? (ForecastEntry) te : null;

        // Duplicates darwinimport.sql
        if( fe.getDep() != null ) {
            terminated = false;
            delayUnknown = false;
        }
        else if( fe.getArr() != null ) {
            terminated = fe.getWtd() == null;
            delayUnknown = false;
        }
        else if( fe.getPass() != null ) {
            terminated = false;
            delayUnknown = false;
        }
        else if( fe.getEtdep() != null ) {
            terminated = false;
            delayUnknown = false;// depdel
        }
        else if( fe.getEtarr() != null ) {
            terminated = fe.getWtd() == null;
            delayUnknown = false;// depdel
        }
        else if( fe.getEtpass() != null ) {
            terminated = false;
            delayUnknown = false;// depdel
        }
        else {
            terminated = false;
            delayUnknown = false;
        }

        // The calling points
        points = train.forecastEntries().
                filter( ForecastEntry::isCallingPoint ).
                map( fe -> new CallingPoint(
                                fe.getTpl(), fe.getTm(), fe.isReport(),
                                false,
                                darwinReferenceManager.getLocationRefFromTiploc( fe.getTpl() )
                        ) ).
                collect( Collectors.toList() );

        // FIXME add length to Train
        length = 0;
    }

    public Train getTrain()
    {
        return train;
    }

    public ForecastEntry getForecastEntry()
    {
        return fe;
    }

    /**
     * The type of entry
     * <p>
     * @return
     */
    @Override
    public LDB.Type getType()
    {
        return type;
    }

    @Override
    public Timestamp getTs()
    {
        return train.getForecast().getTs();
    }

    @Override
    public LocalDateTime getTsDT()
    {
        return train.getForecast().getTs().toLocalDateTime();
    }

    @Override
    public Collection<CallingPoint> getPoints()
    {
        return points;
    }

    @Override
    public int getCancReason()
    {
        return train.isSchedulePresent() ? train.getSchedule().getCancReason() : 0;
    }

    @Override
    public int getLateReason()
    {
        return 0;
    }

    @Override
    public boolean isCanc()
    {
        return fe.isScheduleEntryPresent() ? fe.getScheduleEntry().isCan() : false;
    }

    @Override
    public void setCanc( boolean canc )
    {
        //this.canc = canc;
    }

    @Override
    public int getVia()
    {
        return train.isSchedulePresent() ? train.getSchedule().getVia() : 0;
    }

    /**
     * Terminated.
     *
     * If {@link #isArrived()} is true then the train has terminated. If not then it's due to terminate here.
     *
     * @return
     */
    @Override
    public boolean isTerminated()
    {
        return terminated;
    }

    /**
     * Has this train arrived.
     *
     * Note to see if the train has arrived but not yet departed use {@link #isOnPlatform()}
     *
     * @return
     */
    @Override
    public boolean isArrived()
    {
        return fe.getArr() != null;
    }

    /**
     * Has the train departed
     *
     * @return
     */
    @Override
    public boolean isDeparted()
    {
        return fe.getDep() != null;
    }

    /**
     * Is the train on the platform.
     *
     * This is defined as having an arrival but no departure time. However a cancelled, terminated or non-timetabled (working)
     * train will not show regardless of the times.
     *
     * @return
     */
    @Override
    public boolean isOnPlatform()
    {
        // Cancelled, terminated or not timetabled then no
        if( isCanc() || isTerminated() || !isTimetabled() ) {
            return false;
        }

        // True if arrived but not departed
        return isArrived() && !isDeparted();
    }

    /**
     * Is the train on time. This is defined as being within ±1 minute of the timetable,
     *
     * @return
     */
    @Override
    public boolean isOntime()
    {
        return fe.getDelay().isZero() || Math.abs( fe.getDelay().getSeconds() ) < 60;
    }

    /**
     * No report. This is defined as having no reported arrival nor departure times
     *
     * @return
     */
    @Override
    public boolean isNoReport()
    {
        return !isArrived() && !isDeparted();
    }

    /**
     * The recorded time for this entry as defined by the database.
     *
     * This is the first value in the following sequence that is present: dep, arr, detet, arret, ptd, pta, wtd, wta or wtp.
     *
     * @return
     */
    @Override
    public LocalTime getTime()
    {
        return fe.getTm();
    }

    @Override
    public long getId()
    {
        return train.getForecastId();
    }

    @Override
    public String getRid()
    {
        return train.getRid();
    }

    @Override
    public String getUid()
    {
        return train.isSchedulePresent() ? train.getSchedule().getUid() : "";
    }

    @Override
    public LocalTime getArr()
    {
        return fe.getArr();
    }

    @Override
    public LocalTime getDep()
    {
        return fe.getDep();
    }

    @Override
    public LocalTime getEta()
    {
        return fe.getEtarr();
    }

    @Override
    public LocalTime getEtd()
    {
        return fe.getEtdep();
    }

    @Override
    public LocalTime getPta()
    {
        return fe.getPta();
    }

    @Override
    public LocalTime getPtd()
    {
        return fe.getPtd();
    }

    /**
     * Is this timetabled. A working train will return false here
     *
     * @return
     */
    @Override
    public boolean isTimetabled()
    {
        return getPta() != null || fe.getPtd() != null;
    }

    @Override
    public String getPlat()
    {
        return fe.getPlat();
    }

    /**
     * Is this entry suppressed.
     *
     * Licence restriction means that if this returns true then this entry must not be displayed to the general public.
     *
     * @return
     */
    @Override
    public boolean isSup()
    {
        return fe.isSup();
    }

    /**
     * Is this entry public.
     *
     * License restriction means that if this returns false then the entry must not be displayed to the general public.
     *
     * @return
     */
    @Override
    public boolean isPublic()
    {
        return !isSup();
    }

    /**
     * Platform suppressed.
     *
     * Licence restriction means that if this returns true then the platform must not be displayed.
     *
     * @return
     */
    @Override
    public boolean isPlatSup()
    {
        return fe.isPlatsup();
    }

    /**
     * Platform suppressed manually from a CIS terminal.
     *
     * Licence restriction means that if this returns true then the platform must not be displayed.
     *
     * @return
     */
    @Override
    public boolean isCisPlatSup()
    {
        return fe.isCisplatsup();
    }

    /**
     * Can the platform be displayed
     *
     * Licence restriction means that if this returns false (i.e. platSup or cisPlatSup is true) then the platform must not be
     * displayed.
     *
     * @return
     */
    @Override
    public boolean isDisplayPlatform()
    {
        return !(isPlatSup() || isCisPlatSup());
    }

    @Override
    public Duration getDelay()
    {
        return fe.getDelay();
    }

    @Override
    public boolean isDelayed()
    {
        return getDelay() != null && !(getDelay().isNegative() || getDelay().isZero());
    }

    /**
     * Is the service delayed. This will be true if the delay is unknown and "Delayed" should be shown on any display boards.
     *
     * @return
     */
    @Override
    public boolean isDelayUnknown()
    {
        return delayUnknown;
    }

    @Override
    public int getScheduleId()
    {
        return 0;
    }

    public boolean isSchedulePresent()
    {
        return train.isSchedulePresent();
    }

    @Override
    public String getOrigin()
    {
        return train.getOrigin();
    }

    @Override
    public String getDest()
    {
        return train.getDest();
    }

    @Override
    public String getToc()
    {
        return isSchedulePresent() ? train.getSchedule().getToc() : "";
    }

    /**
     * The train length at this location
     *
     * @return number of carriages, 0 for unknown
     */
    @Override
    public int getLength()
    {
        return length;
    }

    @Override
    public String getCurloc()
    {
        ForecastEntry last = train.getLastReport();
        return last == null ? "" : last.getTpl();
    }

    /**
     * Duration from now until the expected time. If the expected time has passed
     * then this returns 0
     * <p>
     * @return Duration, never negative
     */
    @Override
    public Duration getTimeUntil()
    {
        LocalDateTime now = getTsDT();
        LocalDateTime time = now.toLocalDate().atTime( getTime() );

        if( time == null ) {
            return Duration.ZERO;
        }

        if( time.isBefore( now ) && Math.abs( time.getHour() - now.getHour() ) > 18 ) {
            time = time.plusDays( 1 );
        }

        return Duration.between( now, time );
    }

    @Override
    public String toString()
    {
        return "LDBTrain{" + "type=" + type + ", train=" + train + ", terminated=" + terminated + ", delayUnknown=" + delayUnknown + ", length=" + length + ", points=" + points + ", fe=" + fe + '}';
    }

}
