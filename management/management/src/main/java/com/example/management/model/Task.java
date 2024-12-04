package com.example.management.model;

import com.example.management.enums.Priority;
import com.example.management.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;

    @NotNull(message = "Priority cannot be null")
    @Enumerated(EnumType.STRING)
    private Priority priority; //LOW, MEDIUM, HIGH

    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    private Status status; //TO_DO, IN_PROGRESS, COMPLETED

    private Long assignedUserId;
    private Long projectId;
}
