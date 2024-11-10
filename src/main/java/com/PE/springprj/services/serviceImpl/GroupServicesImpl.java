package com.PE.springprj.services.serviceImpl;

import com.PE.springprj.entities.Group;
import com.PE.springprj.repositories.GroupRepository;
import com.PE.springprj.services.GroupServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServicesImpl implements GroupServices {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public List<Group> getAllGroups() {
        return (List<Group>) groupRepository.findAll();
    }

    @Override
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public void update(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void delete(long id) {
        groupRepository.deleteById(id);
    }
}
