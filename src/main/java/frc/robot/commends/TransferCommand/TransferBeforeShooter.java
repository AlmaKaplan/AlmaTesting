
package frc.robot.commends.TransferCommand;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.Transfer.Transfer;
import frc.robot.subsystem.Transfer.TransferConstance;


public class TransferBeforeShooter extends Command {

  public static Transfer transfer = Transfer.getInstance();

  public TransferBeforeShooter() {
    addRequirements(transfer);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    transfer.setVoltage(TransferConstance.TRANSPORT_VOLTAGE_FOR_SHOOTING);
  }

  @Override
  public void end(boolean interrupted) {
    transfer.setVoltage(0);
  }

  @Override
  public boolean isFinished() {
    if (transfer.BallCounter() == 0){
      return true;
    }
    return false;
  }
}
