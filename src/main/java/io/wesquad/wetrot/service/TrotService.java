package io.wesquad.wetrot.service;

import io.wesquad.wetrot.model.Trot;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrotService {
    public Trot create(Trot trot) {
        trot.setUid(UUID.randomUUID());
        return trot;
    }
}
