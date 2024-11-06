package com.p8labs.reactive.compareCode;

import com.p8labs.reactive.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
public class CouponService {

    public boolean isGoldMember(Member member) throws InterruptedException {
        Thread.sleep(3000L);
        log.info("CHECK GRADE");
        return member.getGrade().equalsIgnoreCase("GOLD");
    }

    public boolean isGreaterThan500point(Member member) throws InterruptedException {
        Thread.sleep(2000L);
        log.info("CHECK POINT");
        return member.getPoint() >= 500;
    }

    public boolean checkRegisterPeriod(Member member) {
        try {
            Thread.sleep(1000L);
            log.info("CHECK PERIOD");
            LocalDate registerDate = member.getRegisterDttm().toLocalDate();
            LocalDate conditionDate = LocalDateTime.now().minusYears(5L).toLocalDate();
            return registerDate.isBefore(conditionDate) || registerDate.equals(conditionDate);
        } catch (Exception e) {
            return false;
        }
    }
}
