package com.p8labs.reactive.compareCode;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CouponServiceFlux {

    public boolean isGoldMember(Member member) throws InterruptedException {
        Thread.sleep(3000L);
        return member.getGrade().equalsIgnoreCase("GOLD");
    }

    public boolean isGreaterThan500point(Member member) throws InterruptedException {
        Thread.sleep(2000L);
        return member.getPoint() >= 500;
    }

    public boolean checkRegisterPeriod(Member member) throws InterruptedException {
        Thread.sleep(1000L);
        LocalDate registerDate = member.getRegisterDttm().toLocalDate();
        LocalDate conditionDate = LocalDateTime.now().plusYears(5L).toLocalDate();
        return registerDate.isAfter(conditionDate) || registerDate.equals(conditionDate);
    }
}
