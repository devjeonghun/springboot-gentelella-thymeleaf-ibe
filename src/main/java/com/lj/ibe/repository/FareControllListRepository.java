package com.lj.ibe.repository;

import com.lj.ibe.vo.FareControlledVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FareControllListRepository extends JpaRepository<FareControlledVO,Long> {
}
