package fr.lernejo.umlgrapher;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

class Launcher implements Callable<Integer> {
    private final List<String> myGraph = new ArrayList<>();
    @Option(names = {"-c", "--classes"}, required = true, description = "renseigner les classes d'où faire partir l'analyse, option obligatoire")
    private final Class<?>[] classes = {};
    @Option(names = {"-g", "--graph-type"},description = "sélectionner le type de graph que l'on souhaite en sortie")
    private final GraphType typeGraph = GraphType.Mermaid;
    @Override
    public Integer call() throws Exception { // your business logic goes here...
        UmlGraph graph = new UmlGraph(classes);
        System.out.println(graph.as(typeGraph));
        return 0;
    }
    public static void main(String... args) {
        System.exit(new CommandLine(new Launcher()).execute(args));
    }}
