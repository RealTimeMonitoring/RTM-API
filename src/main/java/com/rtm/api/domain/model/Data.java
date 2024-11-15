package com.rtm.api.domain.model;

import com.rtm.api.domain.enums.RegisterStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Data 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String vendorId;
    private String productId;
    private String latitude;
    private String longitude;
    @Column(columnDefinition = "TEXT")
    private String value;
    @Column(length = 4000)
    private String description = "";
    @Enumerated(EnumType.STRING)
    private RegisterStatus status = RegisterStatus.OPEN;
    private LocalDateTime dtInsert;
    
    @PrePersist
    protected void onCreate()
    {
        dtInsert = LocalDateTime.now();
        
        if ( vendorId != null && vendorId.isBlank() )
        {
            vendorId = "6018590627095";
        }
    }
}
