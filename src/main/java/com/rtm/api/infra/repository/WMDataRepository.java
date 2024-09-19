package com.rtm.api.infra.repository;

import com.rtm.api.domain.model.WMData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WMDataRepository extends JpaRepository<WMData, Long> 
{
}
