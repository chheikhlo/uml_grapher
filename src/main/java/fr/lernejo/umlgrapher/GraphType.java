package fr.lernejo.umlgrapher;

public enum GraphType {

    MERMAID("""
    classDiagram
    class Machin {
        <<interface>>
    }
    """);

    private final String typeGraph;

    GraphType(String s) {
        this.typeGraph = s;
    }

    public String getTypeGraph() {
        return typeGraph;
    }
}
