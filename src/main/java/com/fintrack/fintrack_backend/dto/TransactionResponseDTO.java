package com.fintrack.fintrack_backend.dto;

import java.time.LocalDate;

    public class TransactionResponseDTO {
    
        private Long id;
        private String description;
        private Double amount;
        private String category;
        private String type;
        private LocalDate date;

    public TransactionResponseDTO(Long id, String description, Double amount, String category, String type, LocalDate date){
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = date;
    }
    
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
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

