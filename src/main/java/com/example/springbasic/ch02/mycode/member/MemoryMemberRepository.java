package com.example.springbasic.ch02.mycode.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static long GENERATED_ID = 0L;
    private static Map<Long, Member> map = new HashMap<>();

    @Override
    public void save(Member member) {
        map.put(member.getId(), member);
    }

    @Override
    public Member select(Long memberId) {
        return map.get(memberId);
    }
}
