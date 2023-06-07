package com.ectimel.englishwritingtool.utility;

import com.ectimel.englishwritingtool.consume.DictionaryWord;
import com.fasterxml.jackson.databind.ObjectReader;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public class Test {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        ModelMapper modelMapper = new ModelMapper();

//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("user"));
//        System.out.println(passwordEncoder.encode("admin"));

        List<Object> dictionaryWord = restTemplate
                .getForObject("https://api.dictionaryapi.dev/api/v2/entries/en/friend", ArrayList.class);


        DictionaryWord dictionaryWord1 = modelMapper.map(dictionaryWord.get(0), DictionaryWord.class);

        System.out.println(dictionaryWord1.getWord());
        System.out.println(dictionaryWord1.getMeanings().get(0).getPartOfSpeech());
        dictionaryWord1.getMeanings().get(0).getDefinitions().forEach(word -> System.out.println(word.getDefinition()));
        dictionaryWord1.getMeanings().get(0).getDefinitions().forEach(word -> System.out.println(word.getExample()));




    }
}
