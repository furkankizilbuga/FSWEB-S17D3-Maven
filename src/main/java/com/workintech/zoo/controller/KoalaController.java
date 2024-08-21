package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
        koalas.put(1, new Koala(1, "Uykucu", 50, 22.10, "female"));
    }

    @GetMapping
    public List<Koala> getAll() {
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getById(@PathVariable Integer id) {

        if(!koalas.containsKey(id)) {
            throw new ZooException("Belirtilen id bulunamadı.", HttpStatus.NOT_FOUND);
        }

        if(id <= 0) {
            throw new ZooException("ID 0'dan büyük olmalıdır.", HttpStatus.BAD_REQUEST);
        }

        return koalas.get(id);
    }

    @PostMapping
    public Koala addNewKoala(@RequestBody Koala koala) {

        if(koala == null || koala.getId() == null || koala.getName() == null || koala.getWeight() == 0.0
                || koala.getGender() == null) {
            throw new ZooException("Lütfen geçerli bir koala giriniz", HttpStatus.BAD_REQUEST);
        }

        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable Integer id,@RequestBody Koala koala) {

        if(!koalas.containsKey(id)) {
            throw new ZooException("Belirtilen id bulunamadı.", HttpStatus.NOT_FOUND);
        }

        if(id <= 0) {
            throw new ZooException("ID 0'dan büyük olmalıdır.", HttpStatus.BAD_REQUEST);
        }

        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable Integer id) {

        if(!koalas.containsKey(id)) {
            throw new ZooException("Belirtilen id bulunamadı.", HttpStatus.NOT_FOUND);
        }

        if(id <= 0) {
            throw new ZooException("ID 0'dan büyük olmalıdır.", HttpStatus.BAD_REQUEST);
        }

        Koala deletedOne = koalas.get(id);
        koalas.remove(id);
        return deletedOne;
    }
}
