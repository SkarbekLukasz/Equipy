package pl.javastart.equipy.asset;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.category.Category;
import pl.javastart.equipy.category.CategoryRepository;

import java.util.Optional;

@Service
public class AssetMapper {

    CategoryRepository categoryRepository;

    public AssetMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public AssetDto toDto(Asset asset) {
        AssetDto assetDto = new AssetDto();
        assetDto.setId(asset.getId());
        assetDto.setName(asset.getName());
        assetDto.setDescription(asset.getDescription());
        assetDto.setSerialNumber(asset.getSerialNumber());
        if(asset.getCategory() != null) { assetDto.setCategory(asset.getCategory().getName()); }
        return assetDto;
    }

    public Asset toEntity(AssetDto assetDto) {
        Asset asset = new Asset();
        asset.setId(assetDto.getId());
        asset.setName(assetDto.getName());
        asset.setSerialNumber(assetDto.getSerialNumber());
        asset.setDescription(assetDto.getDescription());
        Optional<Category> category = categoryRepository.findByNameIgnoreCase(assetDto.getCategory());
        category.ifPresent(asset::setCategory);
        return asset;
    }
}
