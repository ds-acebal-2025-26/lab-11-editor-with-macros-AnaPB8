package es.uniovi.eii.ds.editor.macros;

import es.uniovi.eii.ds.editor.Document;

public class Replace implements Action{

    private String toBeReplaced;
    private String newTxt;
    private Document doc;
    
    public Replace(Document doc, String toBeReplaced, String newTxt) {
        this.doc = doc;
        this.toBeReplaced = toBeReplaced;
        this.newTxt = newTxt;        
    }

    @Override
    public void execute() {
        doc.replace(toBeReplaced, newTxt);
    }
}
