package com.p8labs.reactive.compareCode;

import com.p8labs.reactive.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executor;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReactorCheckService {

    private final CouponService couponService;

    private final Executor customExecutor;

    public boolean blocking(Member member) {
        try {
            boolean checkRegisterPeriod = couponService.checkRegisterPeriod(member);
            boolean isGoldMember = couponService.isGoldMember(member);
            boolean isGreaterThan500point = couponService.isGreaterThan500point(member);
            return checkRegisterPeriod && isGoldMember && isGreaterThan500point;
        } catch (Exception e) {
            log.info("ERROR!!!!");
            return false;
        }
    }

    public Mono<Boolean> monoBlocking(Member member) {
        // 각 조건을 Flux로 변환하여 비동기 호출
        return Mono.zip(
                        Mono.fromCallable(() -> couponService.checkRegisterPeriod(member)),
                        Mono.fromCallable(() -> couponService.isGoldMember(member)),
                        Mono.fromCallable(() -> couponService.isGreaterThan500point(member))
                )
                .map(tuple -> {
                    Boolean isRegisterPeriodValid = tuple.getT1();
                    Boolean isGoldMemberValid = tuple.getT2();
                    Boolean isGreaterThan500pointValid = tuple.getT3();

                    // 모든 조건이 참인지 확인
                    return isRegisterPeriodValid && isGoldMemberValid && isGreaterThan500pointValid;
                });
    }

    public Mono<Boolean> mono(Member member) {
        // 각 조건을 Flux로 변환하여 비동기 호출
        return Mono.zip(
                        Mono.fromCallable(() -> couponService.checkRegisterPeriod(member))
                                .subscribeOn(Schedulers.fromExecutor(customExecutor)),
                        Mono.fromCallable(() -> couponService.isGoldMember(member))
                                .subscribeOn(Schedulers.fromExecutor(customExecutor)),
                        Mono.fromCallable(() -> couponService.isGreaterThan500point(member))
                                .subscribeOn(Schedulers.fromExecutor(customExecutor))
                )
                .map(tuple -> {
                    Boolean isRegisterPeriodValid = tuple.getT1();
                    Boolean isGoldMemberValid = tuple.getT2();
                    Boolean isGreaterThan500pointValid = tuple.getT3();

                    // 모든 조건이 참인지 확인
                    return isRegisterPeriodValid && isGoldMemberValid && isGreaterThan500pointValid;
                });
    }

}
