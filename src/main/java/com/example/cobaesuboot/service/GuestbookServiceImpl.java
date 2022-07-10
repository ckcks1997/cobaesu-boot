package com.example.cobaesuboot.service;

import com.example.cobaesuboot.dto.GuestbookDTO;
import com.example.cobaesuboot.dto.PageRequestDTO;
import com.example.cobaesuboot.dto.PageResultDTO;
import com.example.cobaesuboot.entity.Guestbook;
import com.example.cobaesuboot.repository.GeustbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

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

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        Page<Guestbook> result = repository.findAll(pageable);

        Function<Guestbook, GuestbookDTO> fn = (e -> entityToDto(e));
        return new PageResultDTO<>(result, fn);
    }


}
