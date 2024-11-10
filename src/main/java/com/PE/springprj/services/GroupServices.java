package com.PE.springprj.services;


import com.PE.springprj.entities.Group;
import java.util.List;
import java.util.Optional;

public interface GroupServices {

    void save(Group group);

    List<Group> getAllGroups();

    Optional<Group> findById(Long id);

    void update(Group group);

    void delete(long id);
}
