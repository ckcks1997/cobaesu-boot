package com.example.cobaesuboot.repository;

import com.example.cobaesuboot.entity.Guestbook;
import com.example.cobaesuboot.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class GeustbookRepositoryTest {
@Autowired
    private GeustbookRepository geustbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,100).forEach( i ->{
            Guestbook guestbook = Guestbook.builder()
                    .title("title"+i)
                    .content("content"+i)
                    .writer("user"+(i%10))
                    .build();
            System.out.println(geustbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest(){
        Optional<Guestbook> result = geustbookRepository.findById(1301L);
        if(result.isPresent()){
            Guestbook guestbook = result.get();
            guestbook.changeContent("changed");
            guestbook.changeTitle("title changed");

        }
    }

    @Test
    public void tesetQUery1(){
        PageRequest page = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qGuestbook.title.contains(keyword);
        builder.and(expression);
        Page<Guestbook> result = geustbookRepository.findAll(builder, page);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });

    }

    @Test
    public void tesetQUery2(){
        PageRequest page = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";
        String keyword2 = "2";
        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression1 = qGuestbook.title.contains(keyword);
        BooleanExpression expression2 = qGuestbook.content.contains(keyword2);
        BooleanExpression expressionAll = expression1.or(expression2);

        builder.and(expressionAll);
        Page<Guestbook> result = geustbookRepository.findAll(builder, page);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });

    }
}