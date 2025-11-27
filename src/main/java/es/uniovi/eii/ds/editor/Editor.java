package es.uniovi.eii.ds.editor;

import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;

import es.uniovi.eii.ds.editor.macros.Delete;
import es.uniovi.eii.ds.editor.macros.Insert;
import es.uniovi.eii.ds.editor.macros.Action;
import es.uniovi.eii.ds.editor.macros.MacroList;
import es.uniovi.eii.ds.editor.macros.Open;
import es.uniovi.eii.ds.editor.macros.Replace;

public class Editor {

    private MacroList macroList;
    private String macroListName;
    private Map<String, MacroList> macros = new HashMap<>();
    private Document doc;

    public Editor() {
        doc = new Document();
    }

    public Document getDoc() {
        return doc;
    }

    public void delete() {
        if (!isRecordingMacro())
            executeMacro(new Delete(doc));
    }

    public void open(String filename) {
        try {
            executeMacro(new Open(doc, filename));
        } catch (UncheckedIOException e) {
            System.out.println("Could not read the file");
        }
    }

    public void replace(String find, String replace) {
        if (!isRecordingMacro())
            executeMacro(new Replace(doc, find, replace));
    }

    public void insert(String[] words) {
        if (!isRecordingMacro())
            executeMacro(new Insert(doc, words));
    }

    public void recordMacro(String name) {
        macroList = new MacroList();
        macroListName = name;
    }

    public boolean isRecordingMacro() {
        return macroList != null;
    }

    public void stopRecording() {
        if (!isRecordingMacro())
            return;
        macros.put(macroListName, macroList);
        macroListName = null;
        macroList = null;
    }

    public void executeMacro(String name) {
       MacroList listM = macros.get(name);
        if (listM == null) {
            System.out.println("Macro not found");
            return;
        }
        for (Action m : listM.getMacros()) {
            m.execute();
        }
    }

    private void executeMacro(Action macro) {
        macro.execute();
        if (isRecordingMacro()) {
            macroList.add(macro);
        }
    }
}
