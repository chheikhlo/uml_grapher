package fr.lernejo.umlgrapher;

public class UmlGraph{


    public UmlGraph(Class<?> machinClass) {

    }

    public String as(GraphType mermai) {
        return mermai.MERMAID.getTypeGraph();
    }

}
