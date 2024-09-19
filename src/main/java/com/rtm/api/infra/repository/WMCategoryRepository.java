package com.rtm.api.infra.repository;

import com.rtm.api.domain.model.WMCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WMCategoryRepository extends JpaRepository<WMCategory, Long>
{
}
