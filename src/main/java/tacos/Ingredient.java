package tacos;

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
