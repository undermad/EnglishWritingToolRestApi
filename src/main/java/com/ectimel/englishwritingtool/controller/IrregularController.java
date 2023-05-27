package com.ectimel.englishwritingtool.controller;

import com.ectimel.englishwritingtool.dto.IrregularVerbDto;
import com.ectimel.englishwritingtool.dto.PageableResponse;
import com.ectimel.englishwritingtool.service.IrregularVerbService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/irregular-verbs")
public class IrregularController {

    private final IrregularVerbService irregularVerbService;

    public IrregularController(IrregularVerbService irregularVerbService) {
        this.irregularVerbService = irregularVerbService;
    }

    @GetMapping
    public ResponseEntity<PageableResponse<IrregularVerbDto>> getAllIrregularVerbs(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false)
            int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "15", required = false)
            int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "firstForm", required = false)
            String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc", required = false)
            String sortDirection) {

        return ResponseEntity.ok(irregularVerbService.getAllIrregularVerbs(sortBy,sortDirection,pageNo, pageSize));
    }

    @GetMapping("/{verb}")
    public ResponseEntity<IrregularVerbDto> getIrregularVerb(@PathVariable String verb) {
        return ResponseEntity.ok(irregularVerbService.getIrregularVerb(verb));
    }

}
