package org.abondar.experimental.data.repo;


import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import org.abondar.experimental.data.model.Person;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface PersonRepository extends CrudRepository<Person,Long> {

    @Executable
    Person find(Long id);
}
