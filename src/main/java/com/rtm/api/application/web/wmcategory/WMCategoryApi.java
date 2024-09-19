package com.rtm.api.application.web.wmcategory;

import com.rtm.api.domain.model.WMCategory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/category")
public interface WMCategoryApi
{
	@GetMapping
	@ResponseStatus( HttpStatus.OK )
	List<WMCategory> getCategory();
}
