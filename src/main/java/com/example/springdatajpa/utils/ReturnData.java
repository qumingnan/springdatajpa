package com.example.springdatajpa.utils;

import com.example.springdatajpa.entities.Classes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnData {
    private List<Classes> content;
    private boolean first;
    private boolean last;
    private int totalPages;
    private int size;
}