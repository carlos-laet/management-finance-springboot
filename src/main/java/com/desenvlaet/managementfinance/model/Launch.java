package com.desenvlaet.managementfinance.model;

import com.desenvlaet.managementfinance.enums.StatusLaunch;
import com.desenvlaet.managementfinance.enums.TypeLaunch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "lancamento", schema = "finances")
public class Launch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "descricao")
    private String description;

    @Column(name = "mes")
    private Integer month;

    @Column(name = "ano")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    @Column(name = "valor")
    private BigDecimal value;

    @Column(name = "data_cadastro")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dateRegistration;

    @Column(name = "tipo")
    @Enumerated(value = EnumType.STRING)
    private TypeLaunch typeLaunch;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusLaunch statusLaunch;

}
