package org.example.departmentapi.request;

import lombok.Data;

@Data
public class RenameParentRequest {
    String oldName;
    String newName;
}
