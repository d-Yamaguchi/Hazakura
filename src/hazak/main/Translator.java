package hazak.main;
import nez.ast.CommonTree;


public class Translator {
	public static CalcTree translate(CommonTree node){
		switch (node.getTag().getSymbol()) {
		case "Source":
			return new Source(node);
		case "NameBind":
			return new NameBind(translate(node.get(0)), translate(node.get(1)));
		case "Func":
			return new Func(translate(node.get(0)), translate(node.get(1)));
		case "Apply":
			return new Apply(translate(node.get(0)), translate(node.get(1)));
		case "Add":
			return new Add(translate(node.get(0)), translate(node.get(1)));
		case "Sub":
			return new Add(translate(node.get(0)), new Minus(translate(node.get(1))));
		case "Mul":
			return new Mul(translate(node.get(0)), translate(node.get(1)));
		case "LessThan":
			return new LessThan(translate(node.get(0)), translate(node.get(1)));
		case "GreaterThan":
			return new GreaterThan(translate(node.get(0)), translate(node.get(1)));
		case "LessThanEquals":
			return new LessThanEquals(translate(node.get(0)), translate(node.get(1)));
		case "GreaterThanEquals":
			return new GreaterThanEquals(translate(node.get(0)), translate(node.get(1)));
		case "Equals":
			return new Equals(translate(node.get(0)), translate(node.get(1)));
		case "NotEquals":
			return new NotEquals(translate(node.get(0)), translate(node.get(1)));
		case "And":
			return new And(translate(node.get(0)),translate(node.get(1)));
		case "Or":
			return new Or(translate(node.get(0)),translate(node.get(1)));
		case "Conditional":
			return new Conditional(node);
		case "Truereturn":
			return new Truereturn(translate(node.get(0)),translate(node.get(1)));
		case "Int":
			return new Int(Integer.parseInt(node.toText()));
		case "Name":
			return new Name(node.toText());
		case "True":
			return new Bool(Boolean.parseBoolean(node.toText()));
		case "False":
			return new Bool(Boolean.parseBoolean(node.toText()));
		case "Minus":
			return new Minus(translate(node.get(0)));
		case "In":
			return new In(translate(node.get(0)));
		case "Out":
			return new Out(translate(node.get(0)));
		default:
			break;
		}
		return null;
	}
}
