package com.rtm.api.infra.repository;

import com.rtm.api.domain.model.OffsetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OffsetEntityRepository extends JpaRepository<OffsetEntity, Long> 
{
  @Query("SELECT o.offset from OffsetEntity o order by o.id desc limit 1")
  Optional<Integer> findLastOffSet();
}