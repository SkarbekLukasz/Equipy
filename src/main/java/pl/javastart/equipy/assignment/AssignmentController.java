package pl.javastart.equipy.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponents;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("")
    public ResponseEntity<AssignmentDto> saveAssignment(@RequestBody AssignmentDto assignmentDto) {
        AssignmentDto responseAssignment;
        try{
            responseAssignment = assignmentService.createAssignment(assignmentDto);
        }
        catch (AssetInUseException e) {
            throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(responseAssignment)
                .toUri();
        return ResponseEntity.created(location).body(responseAssignment);
    }

    @PostMapping("/{assignmentId}/end")
    public ResponseEntity<LocalDateTime> endAssignment(@PathVariable Long assignmentId) {
        return ResponseEntity.accepted().body(assignmentService.endAssignment(assignmentId));
    }
}
