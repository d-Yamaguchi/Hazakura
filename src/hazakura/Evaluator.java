package hazakura;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Evaluator extends CalcVisitor {

	HashMap<String, Object> record;
	HashMap<String, Object> recordPrime;//Func内部のみ有効
	int checkSum;//0のとき関数外
	Stack<Object> argument;//関数の引数を受け渡す場所
	Stack<HashMap<String,Object>> escape_Buffer;
	
	public Object eval(MyTree node) {
		return node.accept(this);
	}
	
	@Override
	public Object visit(MySource node) {
		//initializer////////////////////
		checkSum = 0;
		record = new HashMap<String, Object>();
		recordPrime = new HashMap<String, Object>();
		argument = new Stack<Object>();
		escape_Buffer = new Stack<HashMap<String,Object>>();
		recordPrime.put("f", null);
		//////////////////////////////////
		for (int i = 0; i < node.size(); i++) {
			Object buff = node.get(i).accept(this);
			if (buff != null)
				System.exit(-1);
		}
		return null;
	}
	@Override
	public Object visit(NameBind node) {
		String id = (String)node.get(0).accept(this);
		if (!record.containsKey(id))
			record.put(id, node.get(1));//Bindをレコードに保存する
		else {
			System.err.println("you can't do destructive assignment");
			System.exit(-1);
		}
		return null;
	}
	
	@Override
	public Object visit(Func node) {
		checkSum++;
		recordPrime.put("f", node);
		recordPrime.put((String) node.get(0).accept(this), argument.pop());
		MyTree rightNode = node.get(1);
		MyTree rightNode_cnode = rightNode.get(rightNode.size()-1);
		if (rightNode_cnode instanceof Func) {
			return rightNode;//子ノードにFunc型が含まれる場合
		}else {
			//funcが含まれないときここから
			Object returnVal = node.get(1).accept(this);
			checkSum--;
			if (checkSum == 0)
				recordPrime.clear();
			return returnVal;
		}
	}
	@Override
	public Object visit(Apply node) {
		argument.push(node.get(1).accept(this));//右ノード引数を取得(Eval)してArg置き場にセットしておく
		MyTree leftNode = node.get(0);
		
		if (leftNode instanceof Name) {
			//左ノードが環境を参照する必要があるとき
			Object id_val = leftNode.accept(this);
			if (id_val instanceof String) {
				System.err.println(id_val +  "is undefined function");
				return null;
			}else if (id_val instanceof Func) {
				if (checkSum!=0) {
					HashMap<String, Object> buff = new HashMap<String,Object>();//関数レコードを退避．
					buff.putAll(recordPrime);
					escape_Buffer.push(buff);
					recordPrime.clear();
					Object retVal= ((Func) id_val).accept(this);//funcNodeがEvalされる!!
					recordPrime = escape_Buffer.pop();
					return retVal;
				}
				return ((Func) id_val).accept(this);//ここでFuncノードがevalされます
			}
		}else if (leftNode instanceof Apply) {
			MyTree func = (MyTree) leftNode.accept(this);//左ノードを評価するとFunc型のtreeが戻ってくるはずです
			return func.accept(this);
		}
		System.err.println("error at applying");
		return null;
	}
	@Override
	public Object visit(And node) {
		Boolean left = (Boolean)node.get(0).accept(this);
		Boolean right = (Boolean)node.get(1).accept(this);
		return left && right;
	}
	@Override
	public Object visit(Or node) {
		Boolean left = (Boolean)node.get(0).accept(this);
		Boolean right = (Boolean)node.get(1).accept(this);
		return left || right;
	}
	@Override
	public Object visit(Equals node) {
		return node.get(0).accept(this) == node.get(1).accept(this);
	}
	@Override
	public Object visit(NotEquals node) {
		return node.get(0).accept(this) != node.get(1).accept(this);
	}
	@Override
	public Object visit(GreaterThan node) {
		Integer left = (Integer)node.get(0).accept(this);
		Integer right = (Integer)node.get(1).accept(this);
		return left > right;
	}
	@Override
	public Object visit(GreaterThanEquals node) {
		Integer left = (Integer)node.get(0).accept(this);
		Integer right = (Integer)node.get(1).accept(this);
		return left >= right;
	}
	@Override
	public Object visit(LessThan node) {
		Integer left = (Integer)node.get(0).accept(this);
		Integer right = (Integer)node.get(1).accept(this);
		return left < right;
	}
	@Override
	public Object visit(LessThanEquals node) {
		Integer left = (Integer)node.get(0).accept(this);
		Integer right = (Integer)node.get(1).accept(this);
		return left <= right;
	}
	@Override
	public Object visit(Add node) {
		Integer left = (Integer)node.get(0).accept(this);
		Integer right = (Integer)node.get(1).accept(this);
		return left + right;
	}
	@Override
	public Object visit(Mul node) {
		Integer left = (Integer)node.get(0).accept(this);
		Integer right = (Integer)node.get(1).accept(this);
		return left + right;
	}
	@Override
	public Object visit(Minus node) {
		return -1 * (Integer)node.get(0).accept(this);
	}
	@Override
	public Object visit(Conditional node) {
		for (int i = 0; i < node.size(); i++) {
			Object cvalue = node.get(i).accept(this);
			if (cvalue != null){//True条件の場合はnull以外の何かが帰ってくる
				return cvalue;
				}
		}
		return null;
	}
	@Override
	public Object visit(Truereturn node) {
		if ((boolean) node.get(1).accept(this)) {
			return node.get(0).accept(this);
		} else {
			return null;
		}
	}
	@Override
	public Object visit(Int node) {
		return node.val;
	}
	@Override
	public Object visit(Bool node) {
		return node.bool;
	}
	@Override
	public Object visit(Name node) {
		String nodeStr = node.str;
		if (record.containsKey(nodeStr) && checkSum==0)
			return record.get(nodeStr);
		else if (checkSum!=0 && recordPrime.containsKey(nodeStr))
			return recordPrime.get(nodeStr);
		return nodeStr;
	}
	
	@Override
	public Object visit(In node) {
		checkSum = -1;
		String id = (String) node.get(0).accept(this);
		checkSum = 0;
		if (record.containsKey(id)) {
			System.err.println("you can't do destructive assignment");
			System.exit(-1);
		}
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.err.println("please input \"" + id + "\"");
		String in = scan.next();
		switch (in) {
		case "true":
			record.put(id, true);
			break;
		case "false":
			record.put(id, false);
			break;
		default:
			record.put(id, Integer.parseInt(in));
			break;
		}
		return null;
	}

	@Override
	public Object visit(Out node) {
		System.out.println(node.get(0).accept(this));
		return null;
	}
}