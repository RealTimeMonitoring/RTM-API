package com.rtm.api.domain.service;

import com.rtm.api.application.dto.request.DataRequestDTO;
import com.rtm.api.application.dto.response.DataResponseDTO;
import com.rtm.api.domain.mapper.CategoryMapper;
import com.rtm.api.domain.mapper.DataMapper;
import com.rtm.api.domain.model.Category;
import com.rtm.api.domain.model.Data;
import com.rtm.api.infra.repository.CategoryRepository;
import com.rtm.api.infra.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataService {
    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
    private final DataMapper dataMapper = Mappers.getMapper(DataMapper.class);
    
    private final DataRepository wMDataRepository;
    private final CategoryRepository categoryRepository;
    private final DataRepository dataRepository;
    
    public List<DataResponseDTO> findAll( Pageable pageable )
    {
        Map<Long, Category> wmCategoryMap = loadCategoryMap();
        
        return mapDataToResponseDTO( wMDataRepository.findAll( pageable ).stream().toList(), wmCategoryMap );
    }
    
    public List<DataResponseDTO> findAll()
    {
        Map<Long, Category> wmCategoryMap = loadCategoryMap();
        
        return mapDataToResponseDTO( dataRepository.findAll(), wmCategoryMap );
    }
    
    public void save( DataRequestDTO dto ) 
    {
         try 
         {
             dataRepository.save( dataMapper.dtoToEntity(dto) ); 
         }
         
         catch ( Exception e )
         {
             throw new RuntimeException( "Error saving the insurance" );
         }
    }
    
    private Map<Long, Category> loadCategoryMap() 
    {
        return categoryRepository.findAll()
                                 .stream()
                                 .collect( Collectors.toMap( Category::getId, c -> c ) );
    }
    
   private List<DataResponseDTO> mapDataToResponseDTO( List<Data> dataList, Map<Long, Category> wmCategoryMap ) 
   {
        return dataList.stream()
                       .map( data -> 
                             {
                                 String productId = data.getProductId();
                                 Category category = null;
                         
                                 try 
                                 {
                                     category = wmCategoryMap.get( Long.parseLong( productId ) );
                                 } 
                                 
                                 catch ( Exception e )
                                 {
                                     System.out.println("Wolf ainda não limpou o db, ta cheio de lixo...");
                                 }
                        
                                 return new DataResponseDTO( data.getId(),
                                                             data.getVendorId(),
                                                             data.getLatitude(),
                                                             data.getLongitude(),
                                                             data.getValue(),
                                                             data.getDescription(),
                                                             data.getStatus(),
                                                             data.getDtInsert(),
                                                             category == null ? null 
                                                                              : categoryMapper.entityToDto( category )
                                                           );
                            } )
                       .toList();
   }
}