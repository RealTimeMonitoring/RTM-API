package com.rtm.api.application.web.category;

import com.rtm.api.application.dto.response.CategoryResponseDTO;

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
	List<CategoryResponseDTO> getCategories();
	
	@GetMapping("/heatmap")
	@ResponseStatus( HttpStatus.OK )
	List<CategoryResponseDTO> getCategoriesHeatMap();
}
