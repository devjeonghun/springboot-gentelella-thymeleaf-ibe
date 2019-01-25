package com.lj.ibe.service.impl;

import com.lj.ibe.service.FareService;
import com.lj.ibe.util.DateUtil;
import com.lj.ibe.vo.Fare;
import com.lj.ibe.vo.FareControlledVO;
import com.lj.ibe.vo.UserInfo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.lj.ibe.util.constant.SPECAILFAREGRP;

@Service("FareService")
public class FareServiceImpl implements FareService {

    @Override
    public List<Fare> fareEventCheck(int numberOfSeats, UserInfo loginUserInfo, List<Fare> fareList, List<FareControlledVO> fareControlledVOList) {
        List<String> specailFareGrp = SPECAILFAREGRP;

        List<Fare> resultList = new ArrayList<Fare>();

        Fare specialSegmentAvailabilityVO = null;
        for (Fare temp : fareList) {
            if (check(loginUserInfo, temp.getFareType(), fareControlledVOList)) {
                resultList.add(temp);
                if (null == specialSegmentAvailabilityVO) {
                    specialSegmentAvailabilityVO = temp;
                } else {
                    // 잔여석 - 요청수
                    int seatAvailablity = temp.getSeatAvailablity();
                    seatAvailablity = seatAvailablity - numberOfSeats;

                    if (seatAvailablity >= 0) {
                        // 이전 값과 가격비교 후 저가Fare만 저장
                        if (temp.getSeatAvailablity() > 0 && temp.getAppliedFareAmount() <= specialSegmentAvailabilityVO.getAppliedFareAmount()) {
                            specialSegmentAvailabilityVO = temp;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).getFareType().equalsIgnoreCase(specialSegmentAvailabilityVO.getFareType())) {
                resultList.get(i).setBestFare(true);
            }
        }
        return resultList;
    }

    public boolean check(UserInfo loginUserInfo, String fareType, List<FareControlledVO> fareControlledVOList) {
        // 로그인 설정
        loginUserInfo = new UserInfo();
        loginUserInfo.setMbrNo("12345678");

        // step 순으로 정렬
        List<FareControlledVO> sortFareControlledVOList =
                fareControlledVOList.stream().sorted(Comparator.comparing(FareControlledVO::getStep)).collect(Collectors.toList());

        List<String> allEventFareList = new ArrayList<>(); // 제어할 전체 운임 리스트

        boolean isNonMemberLogin = loginUserInfo != null && loginUserInfo.getMbrNo() == null; // 회원 로그인 여부
        boolean result = false;

        fareLoop:
        for (FareControlledVO fareControlVO : sortFareControlledVOList) {
            String[] eventFareList = fareControlVO.getEventFareList().split(",");
            for (String eventFare : eventFareList) {
                if (eventFare.equalsIgnoreCase(fareType)) {
                    boolean isSaleUser = fareControlVO.isMemberOnly() && isNonMemberLogin ? false : true;
                    boolean isEventTime = DateUtil.isControlledTime(fareControlVO.getEventOpenTime(), fareControlVO.getEventOffTime());

                    if (fareControlVO.isPresaleOnly()) {
                        boolean getDaoResult = true;
                        isSaleUser = isSaleUser && getDaoResult;
                    }
                    result = isSaleUser && isEventTime;
                    break fareLoop;
                }
            }
        }
        continue fareLoop;
    }
        return result;
}
}
