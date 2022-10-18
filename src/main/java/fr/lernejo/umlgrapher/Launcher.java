package fr.lernejo.umlgrapher;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "Launcher", mixinStandardHelpOptions = true, version = "launcher 4.0",
    description = "Prints to STDOUT.")
class Launcher implements Callable<Integer> {

    

    @Option(names = {"-c", "--classes"}, required = true, description = "renseigner les classes d'où faire partir l'analyse, option obligatoire")
    private final Class[] classes = new Class[9];

    @Option(names = {"-g", "--graph-type"}, defaultValue = "GraphType.Mermaid", description = "sélectionner le type de graph que l'on souhaite en sortie")
    private final String[] typeGraph = new String[5];

    
    @Override
    public Integer call() throws Exception { // your business logic goes here...
        //for(int i=0;i<classes.length;i++)
         //   System.out.println(classes[i]);
          //System.out.println(classes);

        return 0;

    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new Launcher()).execute(args);
        System.exit(exitCode);
    }
}
