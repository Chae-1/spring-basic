package com.example.springbasic.ch02.excode.member;

public class MemberServiceImpl implements MemberService{
    private final MemberRepository repository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        repository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return repository.findById(memberId);
    }
}
