package com.www.backend.domain.asset.mapper;

import com.www.backend.domain.asset.Asset;
import com.www.backend.domain.asset.dto.AssetDto;
import com.www.backend.domain.asset.dto.CreateAssetParameter;
import com.www.backend.domain.asset.dto.UpdateAssetParameter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    AssetDto toDto(Asset asset);

    Asset toEntity(CreateAssetParameter parameter);

    Asset toEntity(AssetDto parameter);

    void updateToEntity(UpdateAssetParameter parameter, @MappingTarget Asset asset);
}
