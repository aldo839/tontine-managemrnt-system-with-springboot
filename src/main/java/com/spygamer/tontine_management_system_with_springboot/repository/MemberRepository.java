package com.spygamer.tontine_management_system_with_springboot.repository;

import com.spygamer.tontine_management_system_with_springboot.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
