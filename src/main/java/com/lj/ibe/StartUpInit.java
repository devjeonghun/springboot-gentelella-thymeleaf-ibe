package com.lj.ibe;

import com.lj.ibe.repository.FareControllListRepository;
import com.lj.ibe.vo.FareControlledVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StartUpInit {

    @Autowired
    private FareControllListRepository fareControllListRepository;

    @PostConstruct
    public void init(){
        FareControlledVO presale = new FareControlledVO();
        presale.setEventName("프리세일");
        presale.setStep(1);
        presale.setEventFareList("\"JINMARKETK1\",\"JINMARKETK2\",\"JINMARKETK3\",\"IJINMKTK1\",\"IJINMKTK2\",\"IJINMKTK3\",\"ONPROMOK1\",\"ONPROMOK2\",\"ONPROMOK3\",\"IPROMOK1\",\"IPROMOK2\",\"IPROMOK3\"");
        presale.setEventOpenTime("2019-01-22-16-00");
        presale.setEventOffTime("2019-01-22-16-10");
        presale.setMemberOnly(true);
        presale.setPresaleOnly(true);
        FareControlledVO time1 = new FareControlledVO();
        time1.setEventName("1차오픈");
        time1.setStep(2);
        time1.setEventFareList("\"JINMARKET\",\"JINMARKETK1\",\"JINMARKETW1\",\"IJINMKT\",\"IJINMKTK1\",\"IJINMKTW1\",\"ONPROMO\",\"ONPROMOK1\",\"ONPROMOW1\",\"IPROMO\",\"IPROMOK1\",\"IPROMOW1\"");
        time1.setEventOpenTime("2019-01-22-16-10");
        time1.setEventOffTime("2019-01-22-17-30");
        time1.setMemberOnly(true);
        time1.setPresaleOnly(true);
        FareControlledVO time2 = new FareControlledVO();
        time2.setEventName("차오픈");
        time2.setStep(3);
        time2.setEventFareList("\"JINMARKET\",\"JINMARKETK1\",\"JINMARKETK2\",\"JINMARKETK3\",\"JINMARKETW1\",\"JINMARKETW2\",\"JINMARKETW3\",\"IJINMKT\",\"IJINMKTK1\",\"IJINMKTK2\",\"IJINMKTK3\",\"IJINMKTW1\",\"IJINMKTW2\",\"IJINMKTW3\",\"ONPROMO\",\"ONPROMOK1\",\"ONPROMOK2\",\"ONPROMOK3\",\"ONPROMOW1\",\"ONPROMOW2\",\"ONPROMOW3\",\"IPROMO\",\"IPROMOK1\",\"IPROMOK2\",\"IPROMOK3\",\"IPROMOW1\",\"IPROMOW2\",\"IPROMOW3\"");
        time2.setEventOpenTime("2019-01-22-16-20");
        time2.setEventOffTime("2019-01-22-17-30");
        time2.setMemberOnly(true);
        time2.setPresaleOnly(true);

        fareControllListRepository.save(presale);
        fareControllListRepository.save(time1);
        fareControllListRepository.save(time2);
    }
}
