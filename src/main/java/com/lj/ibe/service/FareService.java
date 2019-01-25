package com.lj.ibe.service;

import com.lj.ibe.vo.Fare;
import com.lj.ibe.vo.FareControlledVO;
import com.lj.ibe.vo.UserInfo;

import java.util.List;

public interface FareService {
    List<Fare> fareEventCheck(int numberOfSeats, UserInfo loginUserInfo, List<Fare> fareList, List<FareControlledVO> fareControlledVOList);
}
