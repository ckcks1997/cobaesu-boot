package com.example.cobaesuboot.service;

import com.example.cobaesuboot.dto.GuestbookDTO;
import com.example.cobaesuboot.dto.PageRequestDTO;
import com.example.cobaesuboot.dto.PageResultDTO;
import com.example.cobaesuboot.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);
    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook build = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return build;
    }

    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO build = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return build;

    }
}
