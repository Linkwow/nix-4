package ua.nix.academy.service.impl;

import ua.nix.academy.persistence.dto.GroupDto;
import ua.nix.academy.repository.impl.GroupRepositoryImpl;
import ua.nix.academy.service.Service;

import java.util.List;

public class GroupService implements Service<GroupDto> {
    private static GroupService instance;
    private GroupRepositoryImpl groupRepository;

    private GroupService(){
    }

    @Override
    public void create(List<GroupDto> dtoList) throws Exception {
        groupRepository.create(dtoList);
    }

    public static GroupService getInstance() {
        if(instance == null){
            instance = new GroupService();
        }
        return instance;
    }
}
