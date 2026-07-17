package com.spygamer.tontine_management_system_with_springboot.controller;

import com.spygamer.tontine_management_system_with_springboot.model.Member;
import com.spygamer.tontine_management_system_with_springboot.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMember(){

        return new ResponseEntity<>(memberRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getById(@PathVariable Long id){

        Optional<Member> member = memberRepository.findById(id);

        return member.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member){

        Member savedMember = memberRepository.save(member);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails){

        Optional<Member> member = memberRepository.findById(id);

        if (member.isPresent()){
            Member existingMember = member.get();
            existingMember.setEmail(memberDetails.getEmail());
            existingMember.setPhone(memberDetails.getPhone());

            Member updatedMember = memberRepository.save(existingMember);
            return  new ResponseEntity<>(updatedMember, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id){

        Optional<Member> member = memberRepository.findById(id);

        if (member.isPresent()){
            memberRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
