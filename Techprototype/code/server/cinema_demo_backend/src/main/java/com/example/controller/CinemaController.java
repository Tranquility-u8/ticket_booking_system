package com.example.controller;

import com.example.entity.CinemaEntity;
import com.example.entity.MovieEntity;
import com.example.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cinema")
@CrossOrigin(origins = "http://localhost:3000")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private EntityManager em;

    @RequestMapping("/create")
    public CinemaEntity create(@ModelAttribute CinemaEntity formData,
                               @RequestBody(required = false) CinemaEntity jsonData) {
        CinemaEntity cinema = (jsonData != null) ? jsonData : formData;
        return cinemaService.save(cinema);
    }

    @RequestMapping("/delete")
    public void deleteById(@RequestParam int id) {
        cinemaService.deleteById(id);
    }

    @RequestMapping("/getById")
    public ResponseEntity<CinemaEntity> getById(@RequestParam int id) {
        Optional<CinemaEntity> cinema = cinemaService.findById(id);
        if (cinema.isPresent()) {
            return ResponseEntity.ok(cinema.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/getAll")
    public List<CinemaEntity> getAll() {
        return cinemaService.findAll();
    }

    // 覆盖更新值
    @RequestMapping("/setById")
    public ResponseEntity<CinemaEntity> set(@RequestBody CinemaEntity newCinema) {
        Optional<CinemaEntity> cinema = cinemaService.findById(newCinema.getId());
        if (cinema.isPresent()) {
            CinemaEntity updatedCinema = cinema.get();
            // 更新 updatedCinema 的属性
            cinemaService.save(updatedCinema);
            return ResponseEntity.ok(updatedCinema);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/updateById")
    public ResponseEntity<CinemaEntity> updateCinema(@RequestBody Map<String, Object> newCinema) {
        int id = (int) newCinema.get("id");
        Optional<CinemaEntity> cinema = cinemaService.findById(id);
        if (cinema.isPresent()) {
            CinemaEntity updatedCinema = cinema.get();
            // 检查请求体中是否包含某个字段的值
            if (newCinema.containsKey("name")) {
                String name = (String) newCinema.get("name");
                if (name != null) {
                    updatedCinema.setName(name);
                }
            }
            if (newCinema.containsKey("address")) {
                String address = (String) newCinema.get("address");
                if (address != null) {
                    updatedCinema.setAddress(address);
                }
            }
            if (newCinema.containsKey("phone")) {
                String phone = (String) newCinema.get("phone");
                if (phone != null) {
                    updatedCinema.setPhone(phone);
                }
            }
            if (newCinema.containsKey("posterUrl")) {
                String posterUrl = (String) newCinema.get("posterUrl");
                if (posterUrl != null) {
                    updatedCinema.setPosterUrl(posterUrl);
                }
            }
            // 更新其他字段
            // ...
            cinemaService.save(updatedCinema);
            return ResponseEntity.ok(updatedCinema);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping("/search")
    public List<CinemaEntity> searchCinemas(@RequestParam(required = false) String name,
                                            @RequestParam(required = false) String address) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CinemaEntity> query = cb.createQuery(CinemaEntity.class);
        Root<CinemaEntity> root = query.from(CinemaEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            predicates.add(cb.equal(root.get("name"), name));
        }
        if (address != null) {
            predicates.add(cb.equal(root.get("address"), address));
        }
        query.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(query).getResultList();
    }

}
