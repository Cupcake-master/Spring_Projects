package ru.bulat.resume_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Resume model for candidate
 * @author Bulat Bilalov
 * @version v1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Resume")
public class Resume {

    /** Primary key for an entity */
    @Id
    private Long id;

    /** Entity creation date */
    @CreatedDate
    @Field(name = "created")
    private Date created;

    /** Date of entity update */
    @LastModifiedDate
    @Field(name = "updated")
    private Date updated;

    /** The name of the candidate */
    @Field(name = "first_name")
    private String firstName;

    /** The surname of the candidate*/
    @Field(name = "last_name")
    private String lastName;

    /** Number of years */
    @Field(name = "age")
    private Integer age;

    /** Contact (email) */
    @Field(name = "email")
    private String email;

    /** Candidate description */
    @Field(name = "description")
    private String description;

    /** Candidate skill */
    @Field(name = "skill")
    private String skill;
}
