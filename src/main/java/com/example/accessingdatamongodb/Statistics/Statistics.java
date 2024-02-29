package com.example.accessingdatamongodb.Statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    @Id
    private String id;
    private double maxGrade;
    private double minGrade;
    private double averageGrade;

}
