package com.example.bimi.service;

import com.example.bimi.model.Direction;
import com.example.bimi.model.ServiceM;
import com.example.bimi.repository.DirectionRepository;
import com.example.bimi.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectionService {
    @Autowired
    private DirectionRepository directionRepository;

    public Direction enregistrerDirection(Direction direction) {
        return this.directionRepository.save(direction);
    }
    public Optional<Direction> select_Direction_By_id(int id) {
        return this.directionRepository.findById(id);
    }

    public void delete_Direction_By_id(int id) {
        this.directionRepository.deleteById(id);
    }
    public List<Direction> selectAll_Direction() {
        return this.directionRepository.findAll();
    }
}
