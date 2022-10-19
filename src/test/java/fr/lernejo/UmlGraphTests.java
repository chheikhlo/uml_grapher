package fr.lernejo;

import fr.lernejo.umlgrapher.GraphType;
import fr.lernejo.umlgrapher.UmlGraph;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UmlGraphTests {

    @Test
    void empty_interface_with_no_relation() {
        UmlGraph graph = new UmlGraph(Machin.class);
        String output = graph.as(GraphType.Mermaid);
        Assertions.assertThat(output).isEqualTo("""
            classDiagram
            class Machin {
                <<interface>>
            }
            """
        );
    }

    @Test
    void empty_Classes_with_relation() {
        UmlGraph graph = new UmlGraph(Living.class,Living.Animal.class,Living.Animal.Ant.class,Living.Animal.Cat.class,Living.Plant.class,Living.Plant.Tree.class,Living.Plant.Tree.Alder.class/*,Living.Orange.class,Living.Orange.Banane.class,Living.Orange.Banane.Mangue.class*/);
        String output = graph.as(GraphType.Mermaid);

        Assertions.assertThat(output).isEqualTo("""
            classDiagram
            class Ant
            class Cat
            class Animal {
                <<interface>>
            }
            class Alder
            class Tree {
                <<interface>>
            }
            class Plant {
                <<interface>>
            }
            class Living {
                <<interface>>
            }
            Living <|-- Animal : extends
            Animal <|.. Ant : implements
            Animal <|.. Cat : implements
            Plant <|-- Tree : extends
            Living <|-- Plant : extends
            Tree <|.. Alder : implements
            """
        );
    }

    /*
    @Test
    void singleton_graph() {
        UmlGraph graph = new UmlGraph(Singleton.class);
        String output = graph.as(GraphType.Mermaid);
        Assertions.assertThat(output).isEqualTo("""
            classDiagram
            class Singleton {
                -Singleton instance$
                +getInstance()$ Singleton
                +supplySomeStr(int offset) String
            }
            Singleton <-- Singleton : returns
            """
        );
    }
    */
    //*********************************************
    /*
    public static class Singleton {

        private static final Singleton instance = new Singleton();

        public static Singleton getInstance() {
            return instance;
        }

        public String supplySomeStr(int offset) {
            return String.valueOf(43 + offset);
        }
    }
    //***********************************************************
    */
    public interface Machin {

    }

    public sealed interface Living {
        sealed interface Animal extends Living {
            final class Ant implements Animal {
            }

            final class Cat implements Animal  {
            }
        }

        sealed interface Plant extends Living{
            sealed interface Tree extends Plant {
                final class Alder implements Tree {
                }
            }
        }

        /*sealed interface Orange extends Living,Plant,Animal {
            final class Banane {
                final class Mangue implements Living,Orange,Plant.Tree {

                }

            }
        }*/

    }
}

