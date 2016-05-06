package hazak.main;
import java.util.ArrayList;
import java.util.List;

import nez.ast.CommonTree;

public abstract class CalcTree {
	List<CalcTree> child;

	public CalcTree() {
		this.child = new ArrayList<CalcTree>();
	}

	public abstract Object accept(CalcVisitor visitor);

}

abstract class BinaryExpr extends CalcTree {

	public BinaryExpr(CalcTree left, CalcTree right) {
		super();
		this.child.add(left);
		this.child.add(right);
	}
}

class Source extends CalcTree {
	
	public Source(CommonTree node) {
		super();
		for (int i = 0; i < node.size(); i++) {
			this.child.add(Translator.translate(node.get(i)));
		}
	}
	
	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class NameBind extends BinaryExpr{
	public NameBind(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Func extends BinaryExpr{
	public Func(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Apply extends BinaryExpr{
	public Apply(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Add extends BinaryExpr {

	public Add(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class Mul extends BinaryExpr {

	public Mul(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class Equals extends BinaryExpr {

	public Equals(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class NotEquals extends BinaryExpr {

	public NotEquals(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class GreaterThan extends BinaryExpr {

	public GreaterThan(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class GreaterThanEquals extends BinaryExpr {

	public GreaterThanEquals(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class LessThan extends BinaryExpr {

	public LessThan(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class LessThanEquals extends BinaryExpr {

	public LessThanEquals(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class And extends BinaryExpr {
	public And(CalcTree left, CalcTree right){
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Or extends BinaryExpr {
	public Or(CalcTree left, CalcTree right){
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
	
}

class Conditional extends CalcTree{
	public Conditional(CommonTree node) {
		super();
		for (int i = 0; i < node.size(); i++) {
			this.child.add(Translator.translate(node.get(i)));
		}
	}
	
	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Truereturn extends BinaryExpr{
	public Truereturn(CalcTree left, CalcTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Int extends CalcTree {
	int val;

	public Int(int val) {
		this.val = val;
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class In extends CalcTree {
	public In(CalcTree target){
		this.child.add(target);
	}
	
	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
	
}

class Out extends CalcTree{
	public Out(CalcTree target){
		this.child.add(target);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
	
}

class Name extends CalcTree{
	String str;
	public Name(String str) {
		this.str = str;
	}
	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
	
}

class Bool extends CalcTree{
	Boolean bool;
	public Bool(Boolean bool){
		this.bool=bool;
	}
	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}


class Minus extends CalcTree{
	public Minus(CalcTree ret){
		this.child.add(ret);
	}
	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}