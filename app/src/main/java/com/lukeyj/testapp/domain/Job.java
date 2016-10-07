package com.lukeyj.testapp.domain;

public class Job {
    private String role;

    private String company;

     public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Job{" +
                "role='" + role + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}