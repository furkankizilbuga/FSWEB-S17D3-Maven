package com.workintech.zoo.exceptions;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZooErrorResponse {

    private int status;
    private String message;
    private long timestamp;



}
