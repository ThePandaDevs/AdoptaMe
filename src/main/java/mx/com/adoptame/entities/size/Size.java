package mx.com.adoptame.entities.size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.com.adoptame.entities.pet.entities.Pet;

@Entity
@Table(name = "TBL_SIZES")
@Data
@NoArgsConstructor
@ToString
public class Size implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sizes")
    private Integer id;

    @Column(nullable = false, unique = true, columnDefinition = "varchar(30)")
    private String name;

    @Column(name="size_range",nullable = false, columnDefinition = "varchar(30)")
    private String range;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false, columnDefinition="TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition="TIMESTAMP")
    private LocalDateTime updatedAt;
    
    // Relationships

    @OneToMany(mappedBy="size", cascade = CascadeType.PERSIST)
    private Set<Pet> pets;
}
