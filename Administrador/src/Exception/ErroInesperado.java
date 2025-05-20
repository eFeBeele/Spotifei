/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Exception;

/**
 *
 * @author Arthur
 */
public class ErroInesperado extends Exception {

    /**
     * Creates a new instance of <code>ErroInesperado</code> without detail
     * message.
     */
    public ErroInesperado() {
    }

    /**
     * Constructs an instance of <code>ErroInesperado</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ErroInesperado(String msg) {
        super(msg);
    }
}
