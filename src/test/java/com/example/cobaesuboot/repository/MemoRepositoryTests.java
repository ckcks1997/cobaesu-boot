package com.example.cobaesuboot.repository;

import com.example.cobaesuboot.entity.Memo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.stream.IntStream;

@SpringBootTest
@Transactional()
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void testClass(){

        IntStream.rangeClosed(1,100).forEach(i-> {
            Memo m = Memo.builder().memoText("test"+i).build();
            memoRepository.save(m);
        });

        em.flush();
        em.clear();

        Assertions.assertThat(memoRepository.count()).isEqualTo(100);
    }

    @Test
    @Rollback(value = false)
    public void edit(){

        Memo m = Memo.builder().mno(300L).memoText("edited").build();

        memoRepository.save(m);
    }

    @Test
    public void testPaging(){
        Sort sort = Sort.by("mno").descending();
        Pageable p = PageRequest.of(0,10, sort);
        Page<Memo> result = memoRepository.findAll(p);
        System.out.println("===========");
        System.out.println(result.getContent());
        System.out.println(result.isFirst());
        System.out.println(result.hasNext());
    }

    @Test
    @Rollback(value = false)
    public void deleteTest(){
        memoRepository.deleteMemoByMno(301L);
    }

}
