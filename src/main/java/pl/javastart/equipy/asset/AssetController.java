package pl.javastart.equipy.asset;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("")
    public List<AssetDto> findAll(@RequestParam(required = false) String text) {
        if(text != null) {
            return assetService.findByNameOrSerialNo(text);
        } else {
            return assetService.findAll();
        }
    }

    @PostMapping("")
    public ResponseEntity<AssetDto> save(@RequestBody AssetDto assetDto) {
        if (assetDto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie może mieć ustawionego id");
        }
        AssetDto asset = assetService.save(assetDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(asset.getId())
                .toUri();
        return ResponseEntity.created(location).body(asset);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> findById(@PathVariable Long id) {
        return assetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetDto> updateById(@PathVariable Long id, @RequestBody AssetDto assetDto) {
        if(!id.equals(assetDto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Numer ID obiektu nie zgadza się z numerem ID ze ścieżki zasobu");
        }
        return ResponseEntity.ok(assetService.update(assetDto));
    }

    @GetMapping("/{assetId}/assignments")
    public List<AssetAssignmentDto> getAssetAssignments(@PathVariable Long assetId) {
        return assetService.getAssetAssignments(assetId);
    }
}
