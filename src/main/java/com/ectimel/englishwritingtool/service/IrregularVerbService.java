package com.ectimel.englishwritingtool.service;

import com.ectimel.englishwritingtool.dto.IrregularVerbDto;
import com.ectimel.englishwritingtool.dto.PageableResponse;

public interface IrregularVerbService {
    IrregularVerbDto getIrregularVerb(String verb);
    PageableResponse<IrregularVerbDto> getAllIrregularVerbs(String sortBy, String sortDirection, int pageNo, int pageSize);
}
