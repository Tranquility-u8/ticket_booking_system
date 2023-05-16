package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ticket", schema = "cinema_demo_data", catalog = "")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TicketEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "screening_id")
    private int screeningId;
    @Basic
    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id", insertable = false, updatable = false)
    private ScreeningEntity screening;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        return id == that.id && screeningId == that.screeningId && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, screeningId, price);
    }
}
