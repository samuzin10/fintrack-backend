package com.fintrack.fintrack_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class CreateTransactionDTO {
    @NotBlank
    private String description;
    @NotNull @Positive
    private Double amount;
    @NotBlank
    private String category;
    @NotBlank
    private String type;
    @NotNull
    private LocalDate date;

    public CreateTransactionDTO(String description, Double amount, String category, String type, LocalDate date){
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = date;
    }
    
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Double getAmount(){
        return amount;
    }

    public void setAmount(Double amount){
        this.amount = amount;
    }

    public String getCategory(){
        return category;
    }
    
    public void setCategory(String category){
        this.category = category;
    }

    public String getType(){
    return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public LocalDate getDate(){
    return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }
}