package commands;

import java.io.IOException;

/**
 * Abstract class for the processing of commands.
 */
abstract public class AbstractCommand implements ProcessCommand{
  private final Appendable append;

  /**
   * Constructor for an abstract command.
   * @param append is where the success message will be outputed to.
   */
  public AbstractCommand(Appendable append){
    this.append = append;
  }
  protected void successMessage(String command){
    try{
      append.append(command + " Successful!\n");
    }catch (IOException e){
      throw new IllegalStateException();
    }
  }
}
