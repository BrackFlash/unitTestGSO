/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

/**
 *
 * @author Frank Peeters, Nico Kuijpers
 *
 * LET OP: De klasse TimeSpan bevat enkele fouten.
 *
 */
public class TimeSpan implements ITimeSpan {

	/* class invariant: 
     * A stretch of time with a begin time and end time.
     * The end time is always later then the begin time; the length of the time span is
     * always positive
     * 
	 */
	private ITime beginTime, endTime;

	/**
	 *
	 * @param bt must be earlier than et
	 * @param et
	 */
	public TimeSpan(ITime bt, ITime et) {
		if (bt.compareTo(et)>= 0) {
			throw new IllegalArgumentException("begin time "
				+ bt + " must be earlier than end time " + et);
		}

		this.beginTime = bt;
		this.endTime = et;
	}

	@Override
	public ITime getBeginTime() {
		return beginTime;
	}

	@Override
	public ITime getEndTime() {
		return endTime;
	}

	@Override
	public int length() {
		return endTime.difference(beginTime);
	}

	@Override
	public void setBeginTime(ITime beginTime) {
		if (beginTime.compareTo(endTime) <= 0) {
			throw new IllegalArgumentException("begin time "
				+ beginTime + " must be earlier than end time " + endTime);
		}

		this.beginTime = beginTime;
	}

	@Override
	public void setEndTime(ITime endTime) {
		if (endTime.compareTo(beginTime) > 0) {
			throw new IllegalArgumentException("end time "
				+ endTime + " must be later then begin time " + beginTime);
		}

		this.endTime = endTime;
	}

	@Override
	public void move(int minutes) {
		beginTime = beginTime.plus(minutes);
		endTime = endTime.plus(minutes);
	}

	@Override
	public void changeLengthWith(int minutes) {
		if (minutes + length()<= 0) {
			throw new IllegalArgumentException("length of period must be positive");
		}

		this.endTime = endTime.plus(minutes);
	}

	@Override
	public boolean isPartOf(ITimeSpan timeSpan) {
		return (getBeginTime().compareTo(timeSpan.getBeginTime()) >= 0
			&& getEndTime().compareTo(timeSpan.getEndTime()) <= 0);
	}

	
    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        if (beginTime.compareTo(timeSpan.getEndTime()) > 0 || endTime.compareTo(timeSpan.getBeginTime()) < 0) {
            return null;
        }
        
        ITime begintime, endtime;
        if (beginTime.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = this.beginTime;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (endTime.compareTo(timeSpan.getEndTime()) > 0) {
            endtime = this.endTime;
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

		if (endTime.compareTo(timeSpan.getEndTime()) < 0) {
			endtime = timeSpan.getEndTime();

		} else {
			endtime = endTime;
		}

		if (begintime.compareTo(endtime) <= 0) {
			return null;
		}
		return new TimeSpan(begintime, endtime);
	}
}
