package com.www.backend.domain.asset.mapper;

import com.www.backend.domain.asset.Asset;
import com.www.backend.domain.asset.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    AssetDto toDto(Asset asset);

    AssetRawDto toRawDto(Asset asset);

    Asset toEntity(AssetRawDto parameter);

    Asset toEntity(CreateAssetParameter parameter);

    Asset toEntity(AssetDto parameter);

    void updateToEntity(UpdateAssetParameter parameter, @MappingTarget Asset asset);
}
