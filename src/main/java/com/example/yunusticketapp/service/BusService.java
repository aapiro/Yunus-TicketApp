package com.example.yunusticketapp.service;


import com.example.yunusticketapp.entity.Bus;
import com.example.yunusticketapp.repository.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    // Testing Autowired is hard. Legacy.
    private final BusRepository busRepository;

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> getByName(String name) {
        return busRepository.findByName(name);
    }
}
