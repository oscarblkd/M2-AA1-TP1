package fac.luminy.m2.aa1.tp1.archunit;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe de verification du respect des patterns de développement
 */
public class ArchitectureTest {

    /**
     * Vérifie que toutes les classes de repository se trouvent dans le package correct.
     * Cette règle s'assure que toutes les classes dont le nom se termine par "Repository"
     * résident dans un package contenant "repository".
     *
     * @throws AssertionError si une classe de repository n'est pas dans le package correct
     */
    @Test
    public void repositoriesShouldBeInRepositoryPackage() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("fac.luminy.m2.aa1.tp1");

        ArchRule rule = ArchRuleDefinition.classes()
                .that().haveSimpleNameEndingWith("Repository")
                .should().resideInAPackage("..repository..");

        rule.check(importedClasses);
    }

    /**
     * Vérifie qu'aucune classe étendant JpaRepository ne se trouve en dehors du package 'toto'.
     *
     * @throws AssertionError si une classe étendant JpaRepository est en dehors du package 'toto'
     */
    @Test
    public void jpaRepositoriesShouldBeInRepositoryPackage() {

    }

    /**
     * Vérifie que les classes de la couche controller ne dépendent pas des classes de la couche repository.
     *
     * @throws AssertionError
     */
    @Test
    public void controllersShouldNotDependOnRepositories() {
        //TODO à implementer
    }


}
