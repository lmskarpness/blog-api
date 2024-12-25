package com.skarpness.blog.mappers.impl;

import com.skarpness.blog.domain.PostDto;
import com.skarpness.blog.domain.PostEntity;
import com.skarpness.blog.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements Mapper<PostEntity, PostDto> {

    private ModelMapper modelMapper;

    public PostMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto mapTo(PostEntity postEntity) {
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostEntity mapFrom(PostDto postDto) {
        return modelMapper.map(postDto, PostEntity.class);
    }
}
