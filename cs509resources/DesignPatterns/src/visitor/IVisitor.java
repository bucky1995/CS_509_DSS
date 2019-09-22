package visitor;

/**
 * The purpose of the Visitor Pattern is to encapsulate an operation that
 * you want to perform on the elements of a data structure. In this way,
 * you can change the operation being performed on a structure without the
 * need of changing the classes of the elements that you are operating on.
 * <p>
 * Using a Visitor pattern allows you to decouple the classes for the data
 * structure and the algorithms used upon them.
 * <p>
 * For more references, see:
 * <p>
 *  http://exciton.cs.oberlin.edu/javaresources/DesignPatterns/VisitorPattern.htm
 * <p>
 * Note: In this final visitor pattern, the AVisitor class has been made totally abstract
 * and all attempts to provide 'shared' code have been disbanded into the appropriate subclasses.
 *
 */
public interface IVisitor {
    /**
     * Accept a concrete visitor at this Element. 
     */
    void visitNumber (Number n);

    /**
     * Accept a concrete visitor at this Set. 
     * <p>
     * Note that subclass must provide the logic.
     */
    void visitSet (Set s);

}
