package es.uniovi.eii.ds.editor.macros;

import es.uniovi.eii.ds.editor.Document;

public class Insert implements Action{
    
    private String[] words;
    private Document doc;

    public Insert(Document doc, String[] words) {
        this.doc = doc;
        this.words = words;
    }

    @Override
    public void execute() {
        doc.insert(words);
    }
}
