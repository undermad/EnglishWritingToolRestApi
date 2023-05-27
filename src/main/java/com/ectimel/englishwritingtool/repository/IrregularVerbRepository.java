package com.ectimel.englishwritingtool.repository;

import com.ectimel.englishwritingtool.entity.IrregularVerb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IrregularVerbRepository extends JpaRepository<IrregularVerb, Long> {

    Optional<IrregularVerb> findByFirstFormOrSecondFormOrThirdFormOrPolishTranslation(String verb, String verb2, String verb3, String verb4);
}
