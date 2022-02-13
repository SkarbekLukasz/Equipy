package pl.javastart.equipy.asset;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetService {

    AssetRepository assetRepository;
    AssetMapper assetMapper;

    public AssetService(AssetRepository assetRepository, AssetMapper assetMapper) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
    }

    public List<AssetDto> findAll() {
        List<AssetDto> assets = assetRepository.findAll()
                .stream()
                .map(assetMapper::toDto)
                .collect(Collectors.toList());
        return assets;
    }

    public List<AssetDto> findByNameOrSerialNo(String text) {
        List<AssetDto> assets = assetRepository.findAllByNameOrSerialNumber(text)
                .stream()
                .map(assetMapper::toDto)
                .collect(Collectors.toList());
        return assets;
    }

    public AssetDto save(AssetDto assetDto) {
        Optional<Asset> asset = assetRepository.findBySerialNumber(assetDto.getSerialNumber());
        asset.ifPresent(a -> {
            if(a.getId().equals(assetDto.getId())) {
            throw new DuplicateSerialNoException();
            }
        });
        Asset assetToSave = assetMapper.toEntity(assetDto);
        Asset assetSaved = assetRepository.save(assetToSave);
        return assetMapper.toDto(assetToSave);
    }

    public Optional<AssetDto> findById(Long id) {
        return assetRepository.findById(id).map(assetMapper::toDto);
    }

    public AssetDto update(AssetDto asset) {
        Optional<Asset> assetCheck = assetRepository.findBySerialNumber(asset.getSerialNumber());
        assetCheck.ifPresent(u -> {
            if(u.getId().equals(asset.getId())) {
            throw new DuplicateSerialNoException();
            }
        });
        Asset assetTosave = assetMapper.toEntity(asset);
        Asset assetSaved = assetRepository.save(assetTosave);
        return assetMapper.toDto(assetSaved);
    }
}
