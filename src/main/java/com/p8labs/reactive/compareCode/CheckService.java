package com.p8labs.reactive.compareCode;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CheckService {

    public boolean isGoldMember(Member member) {
        return member.getGrade().equalsIgnoreCase("GOLD");
    }

    public boolean isGreaterThan500point(Member member) {
        return member.getPoint() >= 500;
    }

    public boolean CheckToMeetSubscriptionPeriod(Member member) {
        LocalDate registerDate = member.getRegisterDttm().toLocalDate();
        LocalDate conditionDate = LocalDateTime.now().plusYears(5L).toLocalDate();
        return registerDate.isAfter(conditionDate) || registerDate.equals(conditionDate);
    }
}
