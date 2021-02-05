package com.vlkn.apachecamel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressList {
    private String status;
    private int code;
    private int total;
    private List<Address> data;
}
