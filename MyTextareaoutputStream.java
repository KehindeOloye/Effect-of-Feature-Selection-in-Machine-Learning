

	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */

	import java.io.IOException;
	import java.io.OutputStream;
	import javax.swing.JTextArea;

	/**
	 *
	 * @author Kehinde
	 */
	public class MyTextareaoutputStream  extends OutputStream{
	    private final JTextArea mytextArea;

	    private final StringBuilder mystringbuilder = new StringBuilder();

	    public MyTextareaoutputStream(final JTextArea textArea) {
	        this.mytextArea = textArea;
	    }

	    public void flush() {
	    }

	    public void close() {
	    }

	    public void write(int escapespecifier) throws IOException {

	        if (escapespecifier == '\r') {
	            return;
	        }

	        if (escapespecifier == '\n') {
	            final String text = mystringbuilder.toString() + "\n";

	            mytextArea.append(text);
	            mystringbuilder.setLength(0);
	        } else {
	            mystringbuilder.append((char) escapespecifier);
	        }
	    }
}
