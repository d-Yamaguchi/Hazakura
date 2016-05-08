package hazakura;

import java.io.IOException;

import nez.ast.Source;
import nez.lang.Grammar;
import nez.main.Command;
import nez.parser.Parser;
import nez.parser.io.CommonSource;
import nez.util.ConsoleUtils;

public class Main extends nez.main.Command {
	

	@Override
	public void exec() throws IOException {
		Command.displayVersion("Hazakura", "0.1");
		p("Enter an input string to eval.");
		p("TIPS: For multiple lines, enclose two empty lines");
		ConsoleUtils.println("");
		Grammar grammar = getSpecifiedGrammar("konoha.nez");
		String start = grammar.getStartProduction().getLocalName();
		Parser p = strategy.newParser(grammar);

		int startline = linenum;
		String text = null;
		while ((text = readInput(start + ">>> ")) != null) {
			if (checkEmptyInput(text)) {
				continue;
			}
			Source sc = CommonSource.newStringSource("<stdio>", startline, text);
			startline = linenum;
			MyTree node = p.parse(sc, new MyTree());
			if (p.hasErrors()) {
				p.showErrors();
				if (node == null) {
					p("Tips: To enter multiple lines, start and end an empty line.");
				}
			}
			if (node != null) {
				p("[Parsed]");
				ConsoleUtils.println("  " + node);
				p("[Evaluated]");
				MyTree cnode = Translator.translate(node);
				Evaluator evaluator = new Evaluator();
				evaluator.eval(cnode);
			}
		}
	}

}
