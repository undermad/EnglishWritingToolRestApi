package com.ectimel.englishwritingtool.dto;


import java.util.Date;

public record ErrorDetails(String message, Date date, String description) {
}
