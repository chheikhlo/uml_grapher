package fr.lernejo.umlgrapher;



import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;


public class UmlGraph {
    private final List<String> myGraph = new ArrayList<>();
    private final GraphType mermai = GraphType.Mermaid;
    private Class<?>[] myClass;

    public UmlGraph(Class<?>... myClass) {
        this.myClass= myClass;
        for (int i = 0; i < myClass.length; i++) {
            if (myClass[i].isInterface()) {
                isClassForInterface(myClass[i]);
            } else{
                isClassForClass(myClass[i]);
            }
        }
        if(myClass.length>1){
            this.myGraph.add(this.withRelation());
        }
    }

    public void isClassForInterface(Class<?> myClass){
        this.myGraph.add("class " + myClass.getSimpleName() + " {\n    <<interface>>\n}\n");
        if(myClass.getInterfaces().length != 0)
            isClassForInterface(myClass.getInterfaces()[0]);
    }

    public void isClassForClass(Class<?> myClass){
        if(!(myClass.isInterface())){
            this.myGraph.add("class " + myClass.getSimpleName() + "\n");
        }else
            this.myGraph.add("class " + myClass.getSimpleName() + " {\n    <<interface>>\n}\n");

        if(myClass.getInterfaces().length != 0)
            isClassForClass(myClass.getInterfaces()[0]);
    }



    public String withRelation(){
        UmlType graphType = new UmlType(this.myClass);
        return graphType.asForRelation(mermai);
    }

    /*
    public void forSingleton(Class<?>... myClass){
        for (int i = 0; i < myClass.length; i++) {
                this.myGraph.add("class " + myClass[i].getSimpleName() + " {\n");
                for (int j = 0; j < myClass[i].getDeclaredFields().length; j++) {
                    if(Modifier.isPrivate(myClass[i].getDeclaredFields()[j].getModifiers())){
                        this.myGraph.add("    -" + myClass[i].getDeclaredFields()[j].getType().getSimpleName() + " " + myClass[i].getDeclaredFields()[j].getName() + "$\n");
                    }else
                        this.myGraph.add("    +" + myClass[i].getDeclaredFields()[j].getType().getSimpleName() + " " + myClass[i].getDeclaredFields()[j].getName() + "$\n");
                }
                inForSingleton(i,myClass);
                this.myGraph.add("}\n");
                this.myGraph.add(myClass[i].getSimpleName() + " <-- " + myClass[i].getSimpleName() + " : returns\n");
        }
    }

    public void inForSingleton(int i,Class<?>... myClass){
        for (int j = 0; j < myClass[i].getDeclaredMethods().length; j++) {
            if (myClass[i].getDeclaredMethods()[j].getParameters().length >= 1) {
                for (int k = 0; k < myClass[i].getDeclaredMethods()[j].getParameters().length; k++) {
                    if (Modifier.isPrivate(myClass[i].getDeclaredMethods()[j].getModifiers())) {
                        this.myGraph.add("    -" + myClass[i].getDeclaredMethods()[j].getName() + "(" + myClass[i].getDeclaredMethods()[j].getParameters()[k].getType() + " " + myClass[i].getDeclaredMethods()[j].getParameters()[k].getName() + ") " + myClass[i].getDeclaredMethods()[j].getReturnType().getSimpleName() + "\n");
                    } else
                        this.myGraph.add("    +" + myClass[i].getDeclaredMethods()[j].getName() + "(" + myClass[i].getDeclaredMethods()[j].getParameters()[k].getType() + " " + myClass[i].getDeclaredMethods()[j].getParameters()[k].getName() + ") " + myClass[i].getDeclaredMethods()[j].getReturnType().getSimpleName() + "\n");
                }
            } else {
                if (Modifier.isPrivate(myClass[i].getDeclaredMethods()[j].getModifiers())) {
                    this.myGraph.add("    -" + myClass[i].getDeclaredMethods()[j].getName() + "()$ " + myClass[i].getDeclaredMethods()[j].getReturnType().getSimpleName() + "\n");
                } else
                    this.myGraph.add("    +" + myClass[i].getDeclaredMethods()[j].getName() + "()$ " + myClass[i].getDeclaredMethods()[j].getReturnType().getSimpleName() + "\n");
            }
        }
    }
    */
    public String as(GraphType mermai){
        String monGraph = "classDiagram\n";
        for (int i = 0; i<myGraph.size() ; i++) {
            for (int j = 0; j < myGraph.size() ; j++){
                if( (i!=j) && myGraph.get(i).equals(myGraph.get(j))){
                    myGraph.remove(myGraph.get(i));
                }
            }
        }
        if (mermai == GraphType.Mermaid) {
            for (int i = 0; i < myGraph.size(); i++)
                monGraph += myGraph.get(i);
        }
        return monGraph;
    }
}
