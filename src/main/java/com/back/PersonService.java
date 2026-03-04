package com.back;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private int version = 1;

    public PersonService(int version) {
        this.version = version;
    }

    @Transactional
    public int count() {
        System.out.println("v%d 버전의 count() 호출".formatted(version));
        return 3;
    }

}