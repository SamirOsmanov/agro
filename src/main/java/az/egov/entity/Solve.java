package az.egov.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by admin on 19.09.2018.
 */

@Data
@Entity
@Table(name = "solve", schema = "List")
public class Solve {


   @Id
   @Column
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id ;

   @Column
   private String name ;

   public Solve(Integer id)
   {
       this.id = id ;
   }
}
