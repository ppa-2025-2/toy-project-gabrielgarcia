package com.example.demo.service;

import org.springframework.lang.NonNull;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.IslandRepository;
import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.service.IslandDomainService;

public class IslandService {

    private final UserRepository userRepository;
    private final IslandRepository islandRepository;
    private final IslandDomainService islandDomainService;

    public IslandService(UserRepository userRepository,
            IslandRepository islandRepository,
            IslandDomainService islandDomainService) {
        this.userRepository = userRepository;
        this.islandRepository = islandRepository;
        this.islandDomainService = islandDomainService;
    }

    public void alocarWorkstationDisponivel(@NonNull Integer userId) {

        final var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException());

        // POO: agregação vs composição (aggregation vs composition)
        final var islands = islandRepository.findIslandWithAvailableWorkstations();

        if (islands.isEmpty()) {
            throw new IllegalStateException("Workstations not available");
        }

        final var islandComWorkstationAlocada = islandDomainService.alocarWorkstation(islands, user);

        islandRepository.save(islandComWorkstationAlocada);
    }
}