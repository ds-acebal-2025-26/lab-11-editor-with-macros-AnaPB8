package es.uniovi.eii.ds.editor.macros;

import java.io.IOException;
import java.io.UncheckedIOException;

import es.uniovi.eii.ds.editor.Document;

public class Open implements Action{

    private String file;
    private Document doc;

    public Open(Document document, String filename) {
        this.doc = document;
        this.file = filename;
    }

    @Override
    public void execute() {
        try {
            doc.open(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
