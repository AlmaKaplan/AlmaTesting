
package frc.robot.commends.IntakeCommand;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.Intake.Intake;
import frc.robot.subsystem.Intake.IntakeConstance;

public class IntakeIn extends Command {

  public static Intake intake = Intake.getInstance();

  public IntakeIn() {

    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intake.setVoltage(IntakeConstance.INTAKE_IN_VOLTAGE);
  }

  @Override
  public void end(boolean interrupted) {
    intake.setVoltage(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
