package com.example.cobaesuboot.service;

import com.example.cobaesuboot.dto.GuestbookDTO;
import com.example.cobaesuboot.entity.Guestbook;
import com.example.cobaesuboot.repository.GeustbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GeustbookRepository repository;

    public Long register(GuestbookDTO dto){
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);
        log.info(entity);

        repository.save(entity);

        return entity.getGno();
    }
}
