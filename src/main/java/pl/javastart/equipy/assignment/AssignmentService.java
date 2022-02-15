package pl.javastart.equipy.assignment;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.asset.Asset;
import pl.javastart.equipy.asset.AssetRepository;
import pl.javastart.equipy.user.User;
import pl.javastart.equipy.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AssignmentService {

    AssignmentRepository assignmentRepository;
    AssetRepository assetRepository;
    UserRepository userRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, AssetRepository assetRepository, UserRepository userRepository) {
        this.assignmentRepository = assignmentRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    public AssignmentDto createAssignment(AssignmentDto assignmentDto) {
        Optional<Assignment> existingAssignment = assignmentRepository.findByAsset_IdAndAndEndIsNull(assignmentDto.getAssetId());
        existingAssignment.ifPresent(a -> {
            throw new AssetInUseException("Urządzenie jest już przypisane do użytkownika");
        });
        Optional<User> existingUser = userRepository.findById(assignmentDto.getUserId());
        Optional<Asset> existingAsset = assetRepository.findById(assignmentDto.getAssetId());
        Assignment assignmentToSave = new Assignment();
        assignmentToSave.setStart(LocalDateTime.now());
        assignmentToSave.setEnd(null);
        assignmentToSave.setUser(existingUser.orElseThrow(() -> new AssetInUseException("Brak użytkownika o takim id")));
        assignmentToSave.setAsset(existingAsset.orElseThrow(() -> new AssetInUseException("Brak urządzenia o takim id")));
        Assignment savedAssignment = assignmentRepository.save(assignmentToSave);
        return AssignmentMapper.toDto(savedAssignment);
    }
}
