package com.example.demo.application.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Island;
import com.example.demo.repository.IslandRepository;
import com.example.demo.domain.exceptions.NotFoundException;

@Service
public class IslandApplicationService {

    private final UserRepository userRepository;
    private final IslandRepository islandRepository;

    public IslandApplicationService(UserRepository userRepository,
            IslandRepository islandRepository) {
        this.userRepository = userRepository;
        this.islandRepository = islandRepository;
    }

    public void alocarWorkstationDisponivel(@NonNull Integer userId) {

        final var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException());

        final var islands = islandRepository.findIslandWithAvailableWorkstations();

        if (islands.isEmpty()) {
            throw new IllegalStateException("Workstations not available");
        }

        final var island = Island.encontrarIslandParaAlocacao(islands);
        island.alocarWorkstation(user);

        islandRepository.save(island);
    }
}