package com.rtm.api.domain.service;

import com.rtm.api.application.dto.response.CategoryResponseDTO;
import com.rtm.api.domain.mapper.CategoryMapper;
import com.rtm.api.infra.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService
{
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper = Mappers.getMapper( CategoryMapper.class );
	
	
	public List<CategoryResponseDTO> getCategories()
	{
		return categoryRepository.findAll().stream().map(categoryMapper::entityToDto).toList();
	}
}
