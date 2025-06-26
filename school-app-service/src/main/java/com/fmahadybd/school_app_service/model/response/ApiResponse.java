package com.fmahadybd.school_app_service.model.response;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    
    private String message; // Message to be returned in the response
    private boolean success; // Indicates whether the operation was successful
    private Object data; // Data to be returned in the response, can be any type
    private int statusCode; // HTTP status code for the response

   
}
