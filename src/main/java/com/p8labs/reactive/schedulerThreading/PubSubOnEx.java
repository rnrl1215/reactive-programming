package com.p8labs.reactive.schedulerThreading;

import com.p8labs.reactive.entity.Member;
import com.p8labs.reactive.filter.MemberFilter;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class PubSubOnEx {

    public Flux<Member> testV1(List<Member> members) {
        Flux<Member> memberFlux = Flux.fromIterable(members);
        return memberFlux
                .map(i -> {
                    System.out.println("MAP THREAD: "+ Thread.currentThread().getName() + " Member ID: " + i.getId());
                    return i;
                })
                .filter(i -> checkGradeAndIsBanned(i, "GOLD"));
    }

    public Flux<Member> testV2(List<Member> members) {
        Flux<Member> memberFlux = Flux.fromIterable(members);
        return memberFlux
                .map(i -> {
                    System.out.println("MAP THREAD: "+ Thread.currentThread().getName() + " Member ID: " + i.getId());
                    return i;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .filter(i -> checkGradeAndIsBanned(i, "GOLD"));
    }

    public Flux<Member> testV3(List<Member> members) {
        Flux<Member> memberFlux = Flux.fromIterable(members);
        return memberFlux
                .map(i -> {
                    System.out.println("MAP THREAD: "+ Thread.currentThread().getName() + " Member ID: " + i.getId());
                    return i;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.newParallel("parallel", 4))
                .filter(i -> {
                    return checkGradeAndIsBanned(i, "GOLD");
                });
    }

    public boolean checkGradeAndIsBanned(Member m, String grade) {
        try {
            System.out.println("FILTER THREAD " + Thread.currentThread().getName());
            if (m == null) return false;
            Thread.sleep(1000);
            return MemberFilter.checkBannedAndGrade(grade).test(m);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
