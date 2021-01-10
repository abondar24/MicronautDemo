package org.abondar.experimental.data.repo;


import io.micronaut.context.annotation.Executable;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import org.abondar.experimental.data.model.Person;
import org.abondar.experimental.data.model.PersonNameDto;


@JdbcRepository(dialect = Dialect.POSTGRES)
public interface PersonRepository extends CrudRepository<Person,Long> {

    @Executable
    Person find(Long id);

    String findPhoneById(Long id);

    PersonNameDto findOne(Long id);
}
