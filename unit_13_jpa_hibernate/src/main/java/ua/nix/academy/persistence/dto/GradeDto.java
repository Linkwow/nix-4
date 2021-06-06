package ua.nix.academy.persistence.dto;

public class GradeDto extends AbstractDto{
    private Integer value;

    public GradeDto(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
