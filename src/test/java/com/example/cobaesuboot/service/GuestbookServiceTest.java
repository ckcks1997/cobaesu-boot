package com.example.cobaesuboot.service;

import com.example.cobaesuboot.dto.GuestbookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class GuestbookServiceTest {

    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister(){ //dto->엔티티 변환 테스트
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("title")
                .content("content")
                .writer("writer00")
                .build();

        System.out.println(service.register(guestbookDTO));

    }
}