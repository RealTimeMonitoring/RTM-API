package com.rtm.api.application.web.wmcategory;

import com.rtm.api.application.dto.response.CategoryResponseDTO;
import com.rtm.api.domain.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController
	implements
		CategoryApi
{
	private final CategoryService service;

	@Override
	public List<CategoryResponseDTO> getCategories()
	{
		return service.getCategories();
	}
}
