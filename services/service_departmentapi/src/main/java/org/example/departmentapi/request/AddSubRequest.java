package org.example.departmentapi.request;

import lombok.Data;

@Data
public class AddSubRequest {
    String parent;
    String child;
}
