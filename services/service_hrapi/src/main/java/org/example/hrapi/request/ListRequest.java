package org.example.hrapi.request;

import lombok.Data;

@Data
public class ListRequest {
    String department;
    String page;
    String size;
}
