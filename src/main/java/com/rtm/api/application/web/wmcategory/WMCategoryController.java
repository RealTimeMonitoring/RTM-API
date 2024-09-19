package com.rtm.api.application.web.wmcategory;

import com.rtm.api.domain.model.WMCategory;
import com.rtm.api.domain.service.WMCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WMCategoryController
	implements
		WMCategoryApi
{
	private final WMCategoryService service;

	@Override
	public List<WMCategory> getCategory()
	{
		return service.getCategories();
	}
}
