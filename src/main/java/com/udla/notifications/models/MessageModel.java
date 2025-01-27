package com.udla.notifications.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel {
    private String idClient;
    private String name;
    private String productName;
    private int productQuantity;
    private String description;
}