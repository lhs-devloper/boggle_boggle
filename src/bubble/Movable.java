package bubble;

public interface Movable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	// default 문법 사용해보기
	// 후크 메서드(구현부만 있고 로직은 없다.)
	default public void down() {};
}
