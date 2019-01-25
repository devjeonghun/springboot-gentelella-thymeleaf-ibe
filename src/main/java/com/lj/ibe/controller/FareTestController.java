package com.lj.ibe.controller;

import com.lj.ibe.repository.FareControllListRepository;
import com.lj.ibe.service.FareService;
import com.lj.ibe.vo.Fare;
import com.lj.ibe.vo.FareControlledVO;
import com.lj.ibe.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lj.ibe.util.constant.SPECAILFAREGRP;

@Controller
public class FareTestController {
    @Autowired
    private FareService fareService;

    @Autowired
    private FareControllListRepository fareControllListRepository;

    @RequestMapping("/")
    public String setDefaultData() {
        return "redirect:/test";
    }

    @RequestMapping("/add")
    public String add(FareControlledVO fareControlledVO) {
        return "add";
    }

    @RequestMapping("/save")
    public String saveToFareList(FareControlledVO fareControlledVO) {
        fareControllListRepository.save(fareControlledVO);
        return "redirect:/list";
    }

    @RequestMapping("/modify")
    public String modify(FareControlledVO fareControlledVO) {
        fareControllListRepository.save(fareControlledVO);
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String fareControllList(Model model) {
        List<FareControlledVO> fareControlledVOList = fareControllListRepository.findAll(new Sort(Sort.Direction.ASC, "step"));
        if (fareControlledVOList != null) {
            model.addAttribute("fareControlledVOList", fareControlledVOList);
        }
        return "list";
    }

    @RequestMapping("/view")
    public String fareControllList(@RequestParam Long num, Model model) {
        FareControlledVO vo = fareControllListRepository.findById(num).get();
        model.addAttribute("vo", vo);
        return "view";
    }

    @RequestMapping("/test")
    public String test(Model model) {
        List<FareControlledVO> fareControlledVOList = fareControllListRepository.findAll(new Sort(Sort.Direction.ASC, "step"));
        if (fareControlledVOList != null) {
            model.addAttribute("fareControlledVOList", fareControlledVOList);
        }
        return "test";
    }

    @RequestMapping("/getTestFare")
    @ResponseBody
    public List<Fare> getTestFare() {
        List<String> specailFareGrp = SPECAILFAREGRP;
        List<Fare> testFareList = new ArrayList<>();
        SecureRandom sc = new SecureRandom();
        int thisInt = sc.nextInt(50);
        for (int i = 0; i <= thisInt; i++) {
            Fare fare = new Fare();
            fare.setFareType(specailFareGrp.get(sc.nextInt(specailFareGrp.size())));
            fare.setSeatAvailablity(sc.nextInt(10));
            fare.setAppliedFareAmount(sc.nextInt(90000));
            testFareList.add(fare);
        }
        return testFareList;
    }

    @RequestMapping("/start")
    @ResponseBody
    public List<Fare> start(@RequestBody List<Fare> fareList) {
        List<FareControlledVO> fareControlledVOList = fareControllListRepository.findAll(new Sort(Sort.Direction.ASC, "step"));
        UserInfo loginUserInfo = new UserInfo();
        int numberOfSeats = 3;
        List<Fare> result = fareService.fareEventCheck(numberOfSeats, loginUserInfo, fareList, fareControlledVOList);
        return result;
    }
}
