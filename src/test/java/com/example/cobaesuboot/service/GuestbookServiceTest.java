package com.example.cobaesuboot.service;

import com.example.cobaesuboot.dto.GuestbookDTO;
import com.example.cobaesuboot.dto.PageRequestDTO;
import com.example.cobaesuboot.dto.PageResultDTO;
import com.example.cobaesuboot.entity.Guestbook;
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

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<GuestbookDTO, Guestbook> list = service.getList(pageRequestDTO);
        for (GuestbookDTO guestbookDTO : list.getDtoList()) {
            System.out.println("guestbookDTO = " + guestbookDTO);
        }


    }
}