/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.GregorianCalendar;

/**
 *
 * @author Paul Pham
 */
public class TimeSpan2 implements ITimeSpan {

    private ITime beginTime;
    private long duration;

    public TimeSpan2(ITime beginTime, ITime endTime) {
        if (beginTime.compareTo(endTime) >= 0) {
            throw new IllegalArgumentException("begin time "
                    + beginTime + " must be earlier than end time " + endTime);
        }
        this.beginTime = (Time) beginTime;
        this.duration = this.beginTime.difference(endTime);
    }

    @Override
    public ITime getBeginTime() {
        return beginTime;
    }

    @Override
    public ITime getEndTime() {
        return beginTime.plus((int) duration);
    }

    @Override
    public int length() {
        return getEndTime().difference(this.beginTime);
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        if (beginTime.compareTo(getEndTime()) >= 0) {
            throw new IllegalArgumentException("begin time "
                    + beginTime + " must be earlier than end time " + getEndTime());
        }

        this.beginTime = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(beginTime) <= 0) {
            throw new IllegalArgumentException("end time "
                    + endTime + " must be later then begin time " + beginTime);
        }
        this.duration = beginTime.difference(endTime);

    }

    @Override
    public void move(int minutes) {
        this.beginTime = beginTime.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes + length() <= 0) {
            throw new IllegalArgumentException("lenght of period must be positive");
        }
        this.duration += minutes;
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getBeginTime()) >= 0 && getEndTime().compareTo(timeSpan.getEndTime()) <= 0);
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        if (beginTime.compareTo(timeSpan.getEndTime()) > 0 || getEndTime().compareTo(timeSpan.getBeginTime()) < 0) {
            return null;
        }

        ITime begintime, endtime;
        if (beginTime.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = beginTime;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (getEndTime().compareTo(timeSpan.getEndTime()) > 0) {
            endtime = getEndTime();
        } else {
            endtime = timeSpan.getEndTime();
        }

        return new TimeSpan2(begintime, endtime);
    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {
        ITime begintime, endtime;

        if (beginTime.compareTo(timeSpan.getBeginTime()) > 0) {
            begintime = beginTime;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (this.getEndTime().compareTo(timeSpan.getEndTime()) < 0) {
            endtime = this.getEndTime();
        } else {
            endtime = timeSpan.getEndTime();
        }

        if (begintime.compareTo(this.getEndTime()) >= 0) {
            return null;
        }

        return new TimeSpan2(begintime, endtime);
    }

}
