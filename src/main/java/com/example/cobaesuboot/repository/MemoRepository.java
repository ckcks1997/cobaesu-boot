package com.example.cobaesuboot.repository;

import com.example.cobaesuboot.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    void deleteMemoByMno(Long num);
}
