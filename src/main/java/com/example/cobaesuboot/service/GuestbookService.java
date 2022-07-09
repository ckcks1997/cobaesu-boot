package com.example.cobaesuboot.service;

import com.example.cobaesuboot.dto.GuestbookDTO;
import com.example.cobaesuboot.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook build = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return build;
    }
}
