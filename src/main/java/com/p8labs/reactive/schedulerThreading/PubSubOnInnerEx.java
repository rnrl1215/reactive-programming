package com.p8labs.reactive.schedulerThreading;

import com.p8labs.reactive.entity.Member;
import com.p8labs.reactive.filter.MemberFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.Executors;

public class PubSubOnInnerEx {

    public Flux<Flux<Member>> testV1(List<Member> members1, List<Member> members2) {
        Flux<Member> memberFlux1 = Flux.fromIterable(members1);
        Flux<Member> memberFlux2 = Flux.fromIterable(members2);
        Flux<Flux<Member>> memberInnerFlux = Flux.just(memberFlux1, memberFlux2);

        return memberInnerFlux.map(
                flux -> {
                    System.out.println("MAP FIRST THREAD: " + Thread.currentThread().getName());
                    return flux.map(
                            i -> {
                                System.out.println("INNER MAP FIRST THREAD: " + Thread.currentThread().getName() + " Member ID: " + i.getId());
                                return i;
                            }
                    ).map(i -> {
                        System.out.println("INNER MAP SECOND THREAD: " + Thread.currentThread().getName() + " Member ID: " + i.getId());
                        return i;
                    }).filter(i -> checkGradeAndIsBanned(i, "GOLD"));
                });
    }


    public Flux<Flux<Member>> testV2(List<Member> members1, List<Member> members2) {
        Flux<Member> memberFlux1 = Flux.fromIterable(members1);
        Flux<Member> memberFlux2 = Flux.fromIterable(members2);
        Flux<Flux<Member>> memberInnerFlux = Flux.just(memberFlux1, memberFlux2);

        Scheduler scheduler1 = Schedulers.newSingle("first-schd");
        Scheduler scheduler2 = Schedulers.newSingle("second-schd");
        Scheduler scheduler3 = Schedulers.newSingle("third-schd");

        return memberInnerFlux.map(
                flux -> {
                    System.out.println("MAP FIRST THREAD: " + Thread.currentThread().getName());
                    return flux.map(
                            i -> {
                                System.out.println("INNER MAP FIRST THREAD: " + Thread.currentThread().getName() + " Member ID: " + i.getId());
                                return i;
                            }
                    ).publishOn(scheduler3).map(i -> {
                        System.out.println("INNER MAP SECOND THREAD: " + Thread.currentThread().getName() + " Member ID: " + i.getId());
                        return i;
                    }).filter(i -> checkGradeAndIsBanned(i, "GOLD"))
                            .subscribeOn(scheduler2);
                }).subscribeOn(scheduler1);
    }


    public boolean checkGradeAndIsBanned(Member m, String grade) {
        try {
            System.out.println("FILTER THREAD " + Thread.currentThread().getName() + "Member ID: " + m.getId());
            if (m == null) return false;
            Thread.sleep(1000);
            return MemberFilter.checkBannedAndGrade(grade).test(m);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
