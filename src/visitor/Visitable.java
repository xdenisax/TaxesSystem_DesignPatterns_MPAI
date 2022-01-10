package visitor;

public interface Visitable {
    String accept(Visitor visitor);
}
