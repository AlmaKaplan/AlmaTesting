
package frc.robot.commends.ShooterCommand;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.Shooter.Shooter;
import frc.robot.subsystem.Shooter.ShooterConstance;

public class IjectOn extends Command {

  public static Shooter Iject = Shooter.getInstance();

  public IjectOn() {
    addRequirements(Iject);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Iject.setVoltage(ShooterConstance.IJECT_VOLTAGE);
  }

  @Override
  public void end(boolean interrupted) {
    Iject.setVoltage(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
