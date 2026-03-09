package com.fintrack.fintrack_backend.dto;

public class DashboardResponse {
    private double Income;
    private double Expense;
    private double Balance;

    public DashboardResponse(double balance, double income, double expense) {
        this.Balance = balance;
        this.Income = income;
        this.Expense = expense;
    }

    public double getIncome() {
        return Income;
    }

    public void setIncome(double income) {
        Income = income;
    }

    public double getExpense(){
        return Expense;
    }

    public void setExpense(double expense){
        Expense = expense;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }
    
}
