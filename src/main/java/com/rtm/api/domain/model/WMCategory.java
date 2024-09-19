package com.rtm.api.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WMCategory
{
	@Id
	private Long id;

	private Long productId;
	private String description;
	private String type;
	private String example;
	private String validateExpression;
}
