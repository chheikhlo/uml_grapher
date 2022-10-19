package fr.lernejo.umlgrapher;



import java.util.ArrayList;
import java.util.List;

public class UmlType {

    private final List<String> myGraph = new ArrayList<>();
    public UmlType(Class<?>... myClass) {
         parcourirTypeParent(0,myClass);
    }

    public void parcourirTypeParent(int i,Class<?>... myClass){
        Class<?> mySuperClass;
        while (i<myClass.length) {
            if (myClass[i].isInterface() && myClass[i].getInterfaces().length != 0) {
                isHeritBeetwenInterface(myClass[i]);
            } else if (!(myClass[i].isInterface()) && myClass[i].getSuperclass() != null && !(myClass[i].getSuperclass().getSimpleName().equals("Object"))) {
                mySuperClass = myClass[i].getSuperclass();
                isHeritBeetwenClasse(myClass[i],mySuperClass);
            } else if (!(myClass[i].isInterface()) && myClass[i].getInterfaces().length != 0) {
                isImplement(myClass[i]);
            }
            i++;
        }
    }
    public void isHeritBeetwenInterface(Class<?> myClass){
        for (int j = 0; j < myClass.getInterfaces().length; j++) {
            this.myGraph.add(myClass.getInterfaces()[j].getSimpleName() + " <|-- " + myClass.getSimpleName() + " : extends\n");
            isHeritBeetwenInterface(myClass.getInterfaces()[j]);
        }
    }
    public void isHeritBeetwenClasse(Class<?> myClass,Class<?> mySuperClass){
       int k = 0;
        while(mySuperClass != null) {
            this.myGraph.add(mySuperClass.getSimpleName() + " <|-- " + myClass.getSimpleName() + " : extends\n");
            mySuperClass = mySuperClass.getSuperclass();
            isHeritBeetwenClasse(mySuperClass, mySuperClass);
            k++;
        }
    }
    public void isImplement(Class myClass){
        for (int j = 0; j < myClass.getInterfaces().length ; j++) {
            this.myGraph.add(myClass.getInterfaces()[j].getSimpleName() + " <|.. " + myClass.getSimpleName() + " : implements\n");
        }
    }
    public String asForRelation(GraphType mermai){
        String monGraph = "";
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


