package pl.javastart.equipy.user;

import pl.javastart.equipy.assignment.Assignment;

public class UserAssignmentMapper {

    public static UserAssignmentDto userAssignmentToDto(Assignment assignment) {
        UserAssignmentDto userAssignmentDto = new UserAssignmentDto();
        userAssignmentDto.setId(assignment.getId());
        userAssignmentDto.setStart(assignment.getStart());
        userAssignmentDto.setEnd(assignment.getEnd());
        userAssignmentDto.setAssetId(assignment.getAsset().getId());
        userAssignmentDto.setAssetName(assignment.getAsset().getName());
        userAssignmentDto.setAssetSerialNumber(assignment.getAsset().getSerialNumber());
        return userAssignmentDto;
    }
}
