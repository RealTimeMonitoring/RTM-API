package com.rtm.api.infra.repository.specification;

import com.rtm.api.application.dto.filter.DataFilterDTO;
import com.rtm.api.domain.model.Data;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;

public class DataSpecification 
{
    public static Specification<Data> filterBy(DataFilterDTO filter) 
    {
        return (root, query, builder) ->
        {
            Predicate predicate = builder.conjunction();
            
            if ( filter.category() != null && !filter.category().isEmpty() )
            {
                predicate = builder.and( predicate, builder.equal( root.get( "productId" ), filter.category() ) );
            }
            
            if ( filter.vendorId() != null && !filter.vendorId().isEmpty() )
            {
                predicate = builder.and( predicate, builder.equal( root.get( "vendorId" ), filter.vendorId() ) );
            }
            
            if ( filter.startDate() != null )
            {
                predicate = builder.and( predicate, builder.greaterThanOrEqualTo( root.get( "dtInsert" ), filter.startDate().atStartOfDay() ) );
            }
            
            if ( filter.endDate() != null )
            {
                predicate = builder.and( predicate, builder.lessThanOrEqualTo( root.get( "dtInsert" ), filter.endDate().atTime( LocalTime.MAX ) ) );
            }
            
            if ( filter.status() != null )
            {
                predicate = builder.and( predicate, builder.equal( root.get( "status" ), filter.status() ) );
            }
            
            return predicate;
        };
    }
}
