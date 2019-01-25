package com.lj.ibe.vo;

import javax.persistence.*;

@Entity
public class FareControlledVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public int step;
    public String eventName;
    @Column(length= 5000)
    public String eventFareList;
    public String eventOpenTime;
    public String eventOffTime;
    public boolean isMemberOnly;
    public boolean isPresaleOnly;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventFareList() {
        return eventFareList;
    }

    public void setEventFareList(String eventFareList) {
        this.eventFareList = eventFareList;
    }

    public String getEventOpenTime() {
        return eventOpenTime;
    }

    public void setEventOpenTime(String eventOpenTime) {
        this.eventOpenTime = eventOpenTime;
    }

    public String getEventOffTime() {
        return eventOffTime;
    }

    public void setEventOffTime(String eventOffTime) {
        this.eventOffTime = eventOffTime;
    }

    public boolean isMemberOnly() {
        return isMemberOnly;
    }

    public void setMemberOnly(boolean memberOnly) {
        isMemberOnly = memberOnly;
    }

    public boolean isPresaleOnly() {
        return isPresaleOnly;
    }

    public void setPresaleOnly(boolean presaleOnly) {
        isPresaleOnly = presaleOnly;
    }
}
