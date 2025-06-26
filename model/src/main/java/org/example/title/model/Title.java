package org.example.title.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "titles")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String level;
}
