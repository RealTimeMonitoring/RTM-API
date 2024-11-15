package com.rtm.api.infra.repository;

import com.rtm.api.domain.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> 
{
}
