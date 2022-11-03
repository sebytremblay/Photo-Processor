package commands;

import java.io.IOException;

abstract public class AbstractCommand implements ProcessCommand{
  private final Appendable append;
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
