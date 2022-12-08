package com.www.backend.domain.file.mapper;

import com.www.backend.domain.file.File;
import com.www.backend.domain.file.dto.FileDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {
    File toEntity(FileDetail fileDetail);
}
