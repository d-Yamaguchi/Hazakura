package hazak.main;

import java.io.IOException;

import nez.Parser;
import nez.ast.CommonTree;
import nez.main.CommandContext;


public class Main {

	public final static void main(String[] args) {
		try {
			long start = System.currentTimeMillis();
			CommandContext c = new CommandContext();
			c.parseCommandOption(args, false/* nezCommand */);
			Parser p = c.newParser();
			CommonTree node = p.parseCommonTree(c.nextInput());
			if(node != null){
				CalcTree cnode = Translator.translate(node);
				Evaluator evaluator = new Evaluator();
				evaluator.eval(cnode);
				System.err.println("finish!");
			}
			long end = System.currentTimeMillis();
			System.err.println((end - start)  + "ms");
		} catch (IOException e) {
			System.exit(1);
		}
	}

}