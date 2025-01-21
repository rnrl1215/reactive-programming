package com.p8labs.reactive.filter;

import com.p8labs.reactive.entity.Member;
import io.netty.util.internal.StringUtil;

import java.util.function.Predicate;

public class MemberFilter {
    public static Predicate<Member> isBanned() {
        return i -> i.isBanned();
    }

    public static Predicate<Member> isEqual(String grade) {
        if (StringUtil.isNullOrEmpty(grade)) return null;
        return i -> i.getGrade().equals(grade);
    }

    public static Predicate<Member> checkBannedAndGrade(String grade) {
        if (StringUtil.isNullOrEmpty(grade)) return null;
        return isBanned().and(isEqual(grade));
    }
}
