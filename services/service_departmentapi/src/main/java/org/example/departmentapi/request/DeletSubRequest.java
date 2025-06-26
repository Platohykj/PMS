package org.example.departmentapi.request;

import lombok.Data;

@Data
public class DeletSubRequest {
    String parent;
    String child;
}
