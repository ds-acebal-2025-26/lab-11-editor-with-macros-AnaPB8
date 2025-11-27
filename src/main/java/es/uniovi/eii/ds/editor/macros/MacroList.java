package es.uniovi.eii.ds.editor.macros;

import java.util.ArrayList;
import java.util.List;

public class MacroList implements Action{

    private List<Action> macros = new ArrayList<>();

    @Override
    public void execute() {
        for (Action action : macros) {
            action.execute();
        }
    }

    public void add(Action macro) {
        macros.add(macro);
    }

    public List<Action> getMacros() {
        return macros;
    }
}
