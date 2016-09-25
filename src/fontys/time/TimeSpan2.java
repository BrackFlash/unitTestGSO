/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

/**
 *
 * @author Paul Pham
 */
public class TimeSpan2 implements ITimeSpan {

    private ITime beginTime;
    private long duration;

    public TimeSpan2(ITime beginTime, ITime endTime) {
        if (beginTime.compareTo(endTime) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + beginTime + " must be earlier than end time " + endTime);
        }
        this.beginTime = beginTime;
        this.duration = beginTime.difference(endTime);
    }

    @Override
    public ITime getBeginTime() {
        return beginTime;
    }

    @Override
    public ITime getEndTime() {
        ITime endTime = this.beginTime.plus((int) duration);
        return endTime;
    }

    @Override
    public int length() {
        return this.getEndTime().difference(beginTime);
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        if (beginTime.compareTo(getEndTime()) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + beginTime + " must be earlier than end time " + getEndTime());
        }

        this.beginTime = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(beginTime) > 0) {
            throw new IllegalArgumentException("end time "
                    + endTime + " must be later then begin time " + beginTime);
        }
        this.duration = beginTime.difference(endTime);

    }

    @Override
    public void move(int minutes) {
        this.beginTime.plus(minutes);
        this.duration += minutes;
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
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

        return new TimeSpan(begintime, endtime);
    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {
        ITime begintime, endtime;
		if (beginTime.compareTo(timeSpan.getBeginTime()) > 0) {
			begintime = beginTime;
		} else {
			begintime = timeSpan.getBeginTime();
		}

		if (getEndTime().compareTo(timeSpan.getEndTime()) < 0) {
			endtime = timeSpan.getEndTime();

		} else {
			endtime = getEndTime();
		}

		return new TimeSpan(begintime, endtime);
    }

}
