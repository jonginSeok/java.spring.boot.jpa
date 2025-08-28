/**
 * 
 */
package com.ngins.spring.jpa.postgresql.jpa.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Trainingsession {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
	
    @Column(name = "model_name")
    private String model_name;
    
    @Column
    private String version;
    
    @Column
    private String status;
    
    @Column
    private String dataset_name;
    
    @Column
    private String gpu_info;
    
    @Column
    private String memory_info;
    
    @Column
    private int total_epochs;
    
    @Column
    private int current_epoch;
    
    @Column
    private int batch_size;
    
    @Column
    private double learning_rate;
    
    @Column
    private int image_size;
    
    @Column
    private String optimizer;
    
    @Column
    private boolean augmentation; // int
    
    @Column
    private int patience;
    
    @Column
    private boolean early_stopping;
    
    @Column
    private int rotation_angle;
    
    @Column
    private int train_percent;
    
    @Column
    private int valid_percent;
    
    @Column
    private int test_percent;
    
    @Column
    private String description;
    
    @Column
    private String notify_method;
    
    @Column
    private String notify_email;
    
    @Column
    private String dataset_path;
    
    @Column
    private String config;
    
    @Column
    private LocalDateTime start_time;
    
    @Column
    private LocalDateTime end_time;
    
    @Column
    private LocalDateTime created_at;
    
    @Column
    private String created_id;
    
    @Column
    private LocalDateTime updated_at;
    
    @Column
    private String updated_id;
}
