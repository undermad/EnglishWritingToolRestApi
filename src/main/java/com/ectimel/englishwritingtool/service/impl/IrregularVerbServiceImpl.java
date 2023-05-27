package com.ectimel.englishwritingtool.service.impl;

import com.ectimel.englishwritingtool.dto.IrregularVerbDto;
import com.ectimel.englishwritingtool.dto.PageableResponse;
import com.ectimel.englishwritingtool.entity.IrregularVerb;
import com.ectimel.englishwritingtool.exception.ResourceNotFoundException;
import com.ectimel.englishwritingtool.repository.IrregularVerbRepository;
import com.ectimel.englishwritingtool.service.IrregularVerbService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IrregularVerbServiceImpl implements IrregularVerbService {

    private final IrregularVerbRepository irregularVerbRepository;
    private final ModelMapper modelMapper;

    public IrregularVerbServiceImpl(IrregularVerbRepository irregularVerbRepository, ModelMapper modelMapper) {
        this.irregularVerbRepository = irregularVerbRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public IrregularVerbDto getIrregularVerb(String verb) {
        Optional<IrregularVerb> irregularVerbAsOptional = irregularVerbRepository
                .findByFirstFormOrSecondFormOrThirdFormOrPolishTranslation(verb, verb, verb, verb);
        if (irregularVerbAsOptional.isEmpty()) {
            throw new ResourceNotFoundException("Irregular verb", verb);
        }
        IrregularVerb irregularVerb = irregularVerbAsOptional.get();

        return modelMapper.map(irregularVerb, IrregularVerbDto.class);
    }

    @Override
    public PageableResponse<IrregularVerbDto> getAllIrregularVerbs(String sortBy,
                                                                   String sortDirection,
                                                                   int pageNo,
                                                                   int pageSize) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<IrregularVerb> irregularVerbsAsPage = irregularVerbRepository.findAll(pageable);
        List<IrregularVerb> irregularVerbsAsList = irregularVerbsAsPage.getContent();
        List<IrregularVerbDto> irregularVerbs = irregularVerbsAsList
                .stream()
                .map(verb -> modelMapper.map(verb, IrregularVerbDto.class))
                .toList();



        return PageableResponse.<IrregularVerbDto>builder()
                .resourceName("Irregular verbs")
                .content(irregularVerbs)
                .pageNo(irregularVerbsAsPage.getNumber())
                .pageSize(irregularVerbsAsPage.getSize())
                .totalPages(irregularVerbsAsPage.getTotalPages())
                .totalElements(irregularVerbsAsPage.getTotalElements())
                .sortBy(sortBy)
                .build();
    }

}
