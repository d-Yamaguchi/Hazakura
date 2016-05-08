package hazakura;


import nez.ast.CommonTree;
import nez.ast.Source;
import nez.ast.Symbol;
import nez.ast.Tree;

public abstract class MyTree extends Tree<MyTree> {

	MyTree() {
		super();
	}

	public MyTree(Symbol tag, Source source, long pos, int len, int size, Object value) {
		super(tag, source, pos, len, size > 0 ? new MyTree[size] : null, value);
	}
	
	@Override
	protected MyTree dupImpl() {
		MyTree t = new MyTree(this.getTag(), this.getSource(), this.getSourcePosition(), this.getLength(), this.size(), getValue());
		return t;
	}

	@Override
	public void link(int arg0, Symbol arg1, Object arg2) {
		this.set(arg0, arg1, (MyTree) arg2);
		
	}
	
	@Override
	public MyTree newInstance(Symbol arg0, int arg1, Object arg2) {
		return new MyTree(arg0, this.getSource(), this.getSourcePosition(), 0, arg1, arg2);
	}

	@Override
	public MyTree newInstance(Symbol arg0, Source arg1, long arg2, int arg3, int arg4, Object arg5) {
		return new MyTree(arg0, arg1, arg2, arg3, arg4, arg5);
	}
	
	public abstract Object accept(CalcVisitor visitor);
}

abstract class BinaryExpr extends MyTree{
	public BinaryExpr(MyTree left, MyTree right) {
		super();
		this.set(0, left);
		this.set(1, right);
	}
}

class MySource extends MyTree{
	public MySource(CommonTree node) {
		super();
		for (int i = 0; i < node.size(); i++) {
			this.set(i, Translator.translate(node.get(i)));
		}
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class NameBind extends BinaryExpr{
	public NameBind(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Func extends BinaryExpr{
	public Func(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Apply extends BinaryExpr{
	public Apply(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Add extends BinaryExpr {

	public Add(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class Mul extends BinaryExpr {

	public Mul(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class Equals extends BinaryExpr {

	public Equals(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class NotEquals extends BinaryExpr {

	public NotEquals(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class GreaterThan extends BinaryExpr {

	public GreaterThan(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class GreaterThanEquals extends BinaryExpr {

	public GreaterThanEquals(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class LessThan extends BinaryExpr {

	public LessThan(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class LessThanEquals extends BinaryExpr {

	public LessThanEquals(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class And extends BinaryExpr {
	public And(MyTree left, MyTree right){
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Or extends BinaryExpr {
	public Or(MyTree left, MyTree right){
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
	
}

class Conditional extends MyTree{
	public Conditional(CommonTree node) {
		super();
		for (int i = 0; i < node.size(); i++) {
			this.set(i, Translator.translate(node.get(i)));
		}
	}
	
	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Truereturn extends BinaryExpr{
	public Truereturn(MyTree left, MyTree right) {
		super(left, right);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}

class Int extends MyTree {
	int val;

	public Int(int val) {
		this.val = val;
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}

}

class In extends MyTree {
	public In(MyTree target){
		this.set(0,target);
	}
	
	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
	
}

class Out extends MyTree{
	public Out(MyTree target){
		this.set(0,target);
	}

	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
	
}

class Name extends MyTree{
	String str;
	public Name(String str) {
		this.str = str;
	}
	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
	
}

class Bool extends MyTree{
	Boolean bool;
	public Bool(Boolean bool){
		this.bool=bool;
	}
	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}


class Minus extends MyTree{
	public Minus(MyTree ret){
		this.set(0,ret);
	}
	@Override
	public Object accept(CalcVisitor visitor) {
		return visitor.visit(this);
	}
}
