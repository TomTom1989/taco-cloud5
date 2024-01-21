package tacos;

<<<<<<< HEAD
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


=======
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
//import java.util.List;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient implements Serializable {
	   private String name;
	    private Type type;
	    private Long appUserId;
	    @Id
	    //@GeneratedValue(strategy = GenerationType.IDENTITY) 
	    private String id;
	 
	   
	    /*@ManyToOne
	    @JoinColumn(name = "appUserId")
	    private AppUser appUser;*/
	
  

    public Ingredient(String name,Type sauce,Long appUserid, String id ) {
        this.id = id;
        this.name = name;
        this.type = sauce;
        this.appUserId= appUserid;
        
    }
    
    public Ingredient(String id, String name) {
    	this.id=id;
    	this.name=name;
    }
    
    public Ingredient(String name,Type sauce, String id ) {
    	this.id=id;
    	this.name=name;
    	this.type=sauce;
    }

    public String getCode() {
        return this.id;
    }

   
    

    @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.ALL)
    private List<Taco> tacos;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }



	

	
}
>>>>>>> d0c2e872bb7301d9fae5afb0e76d33c7f5b5df78
