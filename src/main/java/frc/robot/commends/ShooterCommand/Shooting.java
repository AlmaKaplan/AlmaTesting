
package frc.robot.commends.ShooterCommand;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.Shooter.Shooter;
import frc.robot.subsystem.Shooter.ShooterConstance;


public class Shooting extends Command {

  public static Shooter shooter = Shooter.getInstance();

  public Shooting() {
    addRequirements(shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    shooter.setVoltage(ShooterConstance.SHOOTING_VOLTAGE);
  }

  @Override
  public void end(boolean interrupted) {
    shooter.setVoltage(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
