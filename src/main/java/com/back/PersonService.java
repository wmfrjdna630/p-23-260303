package com.back;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private int version = 1;
//    private ArrayList<Integer> nums;

    public PersonService(int version) {
        this.version = version;
    }

    public int count() {
        System.out.println("v%d 버전의 count() 호출".formatted(version));
        return 3;
    }

}