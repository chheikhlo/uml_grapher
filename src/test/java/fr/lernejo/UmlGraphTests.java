package fr.lernejo;

import fr.lernejo.umlgrapher.GraphType;
import fr.lernejo.umlgrapher.UmlGraph;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class UmlGraphTests {

    @Test
    void empty_interface_with_no_relation() {

        UmlGraph graph = new UmlGraph(Machin.class);

        String output = String.valueOf(graph.as(GraphType.MERMAID));

        Assertions.assertThat(output).isEqualTo("""
            classDiagram
            class Machin {
                <<interface>>
            }
            """
        );
    }

    interface Machin {

    }
}

