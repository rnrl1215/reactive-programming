package com.p8labs.reactive.combination;

import com.p8labs.reactive.combination.repository.MemberRepository;
import com.p8labs.reactive.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.Executor;

@RequiredArgsConstructor
@Service
public class CombinationService {
    private final MemberRepository memberRepository;

    private final Executor customExecutor;

    public Mono<List<Member>> findUserWithReward() {

        Flux<Long> userIds = Flux.just(1L,2L,3L);
        Mono<List<Member>> listMono = userIds.flatMap(
                i -> {
                    Mono<Member> memberMono = Mono.fromCallable(() -> memberRepository.findUser(i));
                    Mono<Integer> integerMono = Mono.fromCallable(() -> memberRepository.findRewardByGrade(i));
                    return memberMono.zipWith(integerMono, (member, reward) -> {
                        member.setReward(reward);
                        return member;
                    });
                }
        )
                .subscribeOn(Schedulers.fromExecutor(customExecutor))
                .collectList();
        return listMono;
    }


    public Mono<List<Member>> findUserWithRewardWithExecutor() {

        Flux<Long> userIds = Flux.just(1L,2L,3L);
        Mono<List<Member>> listMono = userIds.flatMap(
                        i -> {
                            Mono<Member> memberMono = Mono.fromCallable(() -> memberRepository.findUser(i))
                                    .subscribeOn(Schedulers.fromExecutor(customExecutor));
                            Mono<Integer> integerMono = Mono.fromCallable(() -> memberRepository.findRewardByGrade(i))
                                    .subscribeOn(Schedulers.fromExecutor(customExecutor));
                            return memberMono.zipWith(integerMono, (member, reward) -> {
                                member.setReward(reward);
                                return member;
                            });
                        }
                )
                .collectList();
        return listMono;
    }
}
