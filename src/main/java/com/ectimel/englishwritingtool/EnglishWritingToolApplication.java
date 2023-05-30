package com.ectimel.englishwritingtool;

import com.ectimel.englishwritingtool.repository.IrregularVerbRepository;
import com.ectimel.englishwritingtool.security.Role;
import com.ectimel.englishwritingtool.security.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EnglishWritingToolApplication implements CommandLineRunner {

    private final IrregularVerbRepository irregularVerbRepository;
    private final RoleRepository roleRepository;

    public EnglishWritingToolApplication(IrregularVerbRepository irregularVerbRepository, RoleRepository roleRepository) {
        this.irregularVerbRepository = irregularVerbRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(EnglishWritingToolApplication.class, args);
    }

    //LOAD NECESSARY DATA INTO DATABASE IF IT'S EMPTY

    @Override
    public void run(String... args) throws Exception {

        // LOAD ROLES TO DATABASE IF TABLE DOESN'T EXIST - check id in db
        // make sure role name is prefixed with ROLE_

//        Role roleAdmin = new Role();
//        roleAdmin.setRoleName("ROLE_ADMIN");
//        roleRepository.save(roleAdmin);
//
//        Role roleUser = new Role();
//        roleUser.setRoleName("ROLE_USER");
//        roleRepository.save(roleUser);

        // LOAD IRREGULAR VERBS TO DATABASE ON FIRST DEPLOY

//        try (FileReader irregularVerbs = new FileReader(".\\irregular-verbs-pl.txt");
//             BufferedReader br = new BufferedReader(irregularVerbs)) {
//
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] lineAsArray = line.split(" ");
//
//                String firstForm = formatStringThatContainsComma(lineAsArray[0]);
//                String secondForm = formatStringThatContainsComma(lineAsArray[1]);
//                String thirdForm = formatStringThatContainsComma(lineAsArray[2]);
//                String polishTranslation = lineAsArray[3];
//
//
//                if (lineAsArray[3].contains(".")) {
//                    String[] polishTranslationAsArray = lineAsArray[3].split("\\.");
//
//                    StringBuilder polishTranslationWithDot = new StringBuilder();
//                    for (int i = 0; i < polishTranslationAsArray.length; i++) {
//                        polishTranslationWithDot.append(polishTranslationAsArray[i]);
//                        polishTranslationWithDot.append(" ");
//                    }
//
//                    polishTranslation = String.valueOf(polishTranslationWithDot).trim();
//                }
//
//                IrregularVerb irregularVerb = new IrregularVerb();
//                irregularVerb.setFirstForm(firstForm);
//                irregularVerb.setSecondForm(secondForm);
//                irregularVerb.setThirdForm(thirdForm);
//                irregularVerb.setPolishTranslation(polishTranslation);
//
//                irregularVerbRepository.save(irregularVerb);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    private String formatStringThatContainsComma(String stringWithComma) {

        if (!stringWithComma.contains(",")) {
            return stringWithComma;
        }

        StringBuilder sb = new StringBuilder();
        String[] array = stringWithComma.split(",");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            sb.append(", ");
        }
        return sb.toString().trim().substring(0, sb.length() - 2);
    }

}
