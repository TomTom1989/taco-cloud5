package tacos;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("ingredients")
public class Ingredient {
    @Id
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String type;

  
}


