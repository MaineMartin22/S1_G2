package com.sprint1.AgenciaDeTurismo.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "metodo_pago")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tipo_tarjeta")
    private String type;
    @Column(name = "numero_tarjeta")
    private String number;
    @Column(name = "cuotas")
    private Integer dues;
}
