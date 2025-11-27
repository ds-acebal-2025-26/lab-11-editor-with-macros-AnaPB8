package es.uniovi.eii.ds.editor.macros;

import es.uniovi.eii.ds.editor.Document;

public class Delete implements Action{

    private Document doc;

    public Delete(Document document) {
        this.doc = document;
    }

    @Override
    public void execute() {
        doc.delete();
    }

}
