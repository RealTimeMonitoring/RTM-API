package com.rtm.api.domain.service;

import com.rtm.api.domain.model.WMCategory;
import com.rtm.api.infra.repository.WMCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WMCategoryService
{
	private final WMCategoryRepository categoryRepository;

	public List<WMCategory> getCategories()
	{
		return categoryRepository.findAll();
	}
}
