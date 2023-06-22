package com.example.yunusticketapp.service;

import com.example.yunusticketapp.entity.Passenger;
import com.example.yunusticketapp.repository.BusRepository;
import com.example.yunusticketapp.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final BusRepository busRepository;

    public PassengerService(PassengerRepository passengerRepository, BusRepository busRepository) {
        this.passengerRepository = passengerRepository;
        this.busRepository = busRepository;
    }

    public void payForBus(String pageName, Long userId) {

        double linePrice = busRepository.findByName(pageName).get(0).getLinePrice();
        passengerRepository.payForBusByIdAndBusName(pageName, linePrice, userId);
    }

    public double getCash(Long userId) {
        return passengerRepository.findByUserId(userId).getPrice();
    }

    public String getBusName(Long userId) {
        return passengerRepository.findByUserId(userId).getLastBus();
    }

    public void save(Passenger samplePassenger) {
        passengerRepository.save(samplePassenger);
    }

    public Passenger getById(Long id) {
        return passengerRepository.getPassengerById(id);
    }

    public boolean checkAmount(Long userId, String pageName) {
        Passenger passenger = passengerRepository.checkDB(userId, pageName);
        if (passenger == null) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional
    public void loadMoney(Long userId) {
        passengerRepository.loadMoney(userId);
    }
}
