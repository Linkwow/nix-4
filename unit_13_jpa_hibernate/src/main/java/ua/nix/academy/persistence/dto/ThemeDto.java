package ua.nix.academy.persistence.dto;

public class ThemeDto extends AbstractDto {
    private String name;

    public ThemeDto(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
