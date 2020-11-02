package ru.bulat.resume_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.bulat.resume_service.model.Resume;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDto {

    /** Primary key for an entity */
    @Id
    private Long id;

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

    public Resume toResume() {
        return Resume.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .email(email)
                .description(description)
                .skill(skill)
                .created(new Date())
                .updated(new Date())
                .build();
    }

}
