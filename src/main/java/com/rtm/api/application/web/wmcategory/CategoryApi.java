package com.rtm.api.application.web.wmcategory;

import com.rtm.api.domain.model.WMCategory;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/category")
public interface CategoryApi
{
	@GetMapping()
	@ResponseStatus( HttpStatus.OK )
	List<WMCategory> getCategories();
}
