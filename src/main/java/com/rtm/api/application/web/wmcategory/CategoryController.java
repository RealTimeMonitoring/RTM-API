package com.rtm.api.application.web.wmcategory;

import com.rtm.api.application.dto.response.WMCategoryResponse;
import com.rtm.api.domain.model.WMCategory;
import com.rtm.api.domain.service.WMCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController
	implements
		CategoryApi
{
	private final WMCategoryService service;

	@Override
	public List<WMCategoryResponse> getCategories()
	{
		return service.getCategories();
	}
}
