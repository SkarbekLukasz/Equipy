package pl.javastart.equipy.asset;

import pl.javastart.equipy.assignment.Assignment;

public class AssetAssignmentMapper {

    static AssetAssignmentDto assetAssignmentToDto(Assignment assignment) {
        AssetAssignmentDto assignmentDto = new AssetAssignmentDto();
        assignmentDto.setId(assignmentDto.getId());
        assignmentDto.setStart(assignment.getStart());
        assignmentDto.setEnd(assignment.getEnd());
        assignmentDto.setUserId(assignment.getUser().getId());
        assignmentDto.setFirstName(assignment.getUser().getFirstName());
        assignmentDto.setLastName(assignment.getUser().getLastName());
        assignmentDto.setPesel(assignment.getUser().getPesel());
        return assignmentDto;
    }
}
