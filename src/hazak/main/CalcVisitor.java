package hazak.main;


public abstract class CalcVisitor {
	public abstract Object visit(Source node);
	
	public abstract Object visit(NameBind node);
	
	public abstract Object visit(Func node);
	
	public abstract Object visit(Apply node);
	
	public abstract Object visit(And node);

	public abstract Object visit(Or node);
	
	public abstract Object visit(Equals node);

	public abstract Object visit(NotEquals node);

	public abstract Object visit(GreaterThan node);

	public abstract Object visit(GreaterThanEquals node);

	public abstract Object visit(LessThan node);

	public abstract Object visit(LessThanEquals node);

	public abstract Object visit(Add node);

	public abstract Object visit(Mul node);
	
	public abstract Object visit(Minus node);
	
	public abstract Object visit(Conditional node);
	
	public abstract Object visit(Truereturn node);
	
	public abstract Object visit(Int node);
	
	public abstract Object visit(Bool node);//trueとfalseを統合

	public abstract Object visit(In node);

	public abstract Object visit(Out node);

	public abstract Object visit(Name node);
}